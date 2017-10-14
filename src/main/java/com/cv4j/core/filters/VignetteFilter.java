/*
 * Copyright (c) 2017-present, CV4J Contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.cv4j.core.filters;


import com.cv4j.core.datamodel.ColorProcessor;
import com.cv4j.core.datamodel.ImageProcessor;
import com.cv4j.core.datamodel.Scalar;
import com.cv4j.image.util.Tools;

/**
 * Vignette - a photograph whose edges shade off gradually
 * 
 */
public class VignetteFilter extends BaseFilter {
		
	private int vignetteWidth;
	private int fade;
	private Scalar vignetteColor;
	
	public VignetteFilter() {
		vignetteWidth = 50;
		fade = 35;
		vignetteColor = Scalar.rgb(0, 0, 0);
	}
	
	@Override
	public ImageProcessor doFilter(ImageProcessor src){

		int total = width*height;
		byte[][] output = new byte[3][total];

		int index = 0;
		for(int row=0; row<height; row++) {
			int ta = 0, tr = 0, tg = 0, tb = 0;
			for(int col=0; col<width; col++) {

				int dX = Math.min(col, width - col);
				int dY = Math.min(row, height - row);
				index = row * width + col;
				tr = R[index] & 0xff;
				tg = G[index] & 0xff;
				tb = B[index] & 0xff;
				if ((dY <= vignetteWidth) & (dX <= vignetteWidth))
				{
					double k = 1 - (double)(Math.min(dY, dX) - vignetteWidth + fade) / (double)fade;
					int[] rgb = superpositionColor(tr, tg, tb, k);
					output[0][index] = (byte)rgb[0];
					output[1][index] = (byte)rgb[1];
					output[2][index] = (byte)rgb[2];
					continue;
				}

				if ((dX < (vignetteWidth - fade)) | (dY < (vignetteWidth - fade)))
				{
					output[0][index] = (byte)vignetteColor.red;
					output[1][index] = (byte)vignetteColor.green;
					output[2][index] = (byte)vignetteColor.blue;
				}
				else
				{
					if ((dX < vignetteWidth)&(dY>vignetteWidth))
					{
						double k = 1 - (double)(dX - vignetteWidth + fade) / (double)fade;
						int[] rgb = superpositionColor(tr, tg, tb, k);
						output[0][index] = (byte)rgb[0];
						output[1][index] = (byte)rgb[1];
						output[2][index] = (byte)rgb[2];
					}
					else
					{
						if ((dY < vignetteWidth)&(dX > vignetteWidth))
						{
							double k = 1 - (double)(dY - vignetteWidth + fade) / (double)fade;
							int[] rgb = superpositionColor(tr, tg, tb, k);
							output[0][index] = (byte)rgb[0];
							output[1][index] = (byte)rgb[1];
							output[2][index] = (byte)rgb[2];
						}
						else
						{
							output[0][index] = (byte)tr;
							output[1][index] = (byte)tg;
							output[2][index] = (byte)tb;
						}
					}
				}
			}
		}
        
        ((ColorProcessor) src).putRGB(output[0], output[1], output[2]);
		output = null;
        return src;
	}
	
	public int[] superpositionColor(int red, int green, int blue, double k) {
		red = (int)(vignetteColor.red * k + red *(1.0-k));
		green = (int)(vignetteColor.green * k + green *(1.0-k));
		blue = (int)(vignetteColor.blue * k + blue *(1.0-k));
		return new int[]{Tools.clamp(red), Tools.clamp(green),Tools.clamp(blue)};
	}
	
	public int getVignetteWidth() {
		return vignetteWidth;
	}

	public void setVignetteWidth(int vignetteWidth) {
		this.vignetteWidth = vignetteWidth;
	}

	public int getFade() {
		return fade;
	}

	public void setFade(int fade) {
		this.fade = fade;
	}
	
	public Scalar getVignetteColor() {
		return vignetteColor;
	}

	public void setVignetteColor(Scalar vignetteColor) {
		this.vignetteColor = vignetteColor;
	}
	
}
