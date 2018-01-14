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
package com.cv4j.core.datamodel;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;

import javax.imageio.ImageIO;

import com.cv4j.exception.CV4JException;


public class CV4JImage implements ImageData, Serializable{

    private static final long serialVersionUID = -8832812623741546452L;
    private int width;
    private int height;
    private ImageProcessor processor;
    private BufferedImage bitmap;
    
    public CV4JImage(ImageProcessor processor) {
    	if(processor == null) {
            throw new CV4JException("processor is null");
        }

        width = processor.getWidth();
        height = processor.getHeight();
        this.processor = processor;
        if(processor instanceof ColorProcessor) {
        	((ColorProcessor)processor).setCallBack(this);
        } else {
        	((ByteProcessor)processor).setCallBack(this);
        }
    }

    public CV4JImage(BufferedImage bitmap) {
        if (bitmap == null) {
            throw new CV4JException("bitmap is null");
        }
        width = bitmap.getWidth();
        height = bitmap.getHeight();
        int[] input = new int[width * height];
        getRGB(bitmap, 0, 0, width, height, input);
        processor = new ColorProcessor(input,width, height);
        ((ColorProcessor)processor).setCallBack(this);
        input = null;
    }

    public CV4JImage(byte[] bytes) {
        if (bytes == null) {
            throw new CV4JException("byte is null");
        }

        width = bitmap.getWidth();
        height = bitmap.getHeight();
        int[] input = new int[width * height];
        getRGB(bitmap, 0, 0, width, height, input);
        processor = new ColorProcessor(input,width, height);
        ((ColorProcessor)processor).setCallBack(this);
        input = null;
    }

    public CV4JImage(int width,int height) {

        this.width = width;
        this.height = height;
        processor = new ByteProcessor(new byte[width*height],width,height);
        ((ByteProcessor)processor).setCallBack(this);
    }

    public CV4JImage(int width, int height, int[] pixels) {

        this.width = width;
        this.height = height;

        processor = new ColorProcessor(pixels,width, height);
        ((ColorProcessor)processor).setCallBack(this);
        pixels = null;
    }

    @Override
    public CV4JImage convert2Gray() {
        if(processor instanceof ColorProcessor) {

            byte[] gray = new byte[width * height];
            int tr=0, tg=0, tb=0, c=0;
            byte[] R = ((ColorProcessor) processor).getRed();
            byte[] G = ((ColorProcessor) processor).getGreen();
            byte[] B = ((ColorProcessor) processor).getBlue();
            for (int i=0; i<gray.length; i++) {
                tr = R[i] & 0xff;
                tg = G[i] & 0xff;
                tb = B[i] & 0xff;
                c = (int) (0.299 * tr + 0.587 * tg + 0.114 * tb);
                gray[i] = (byte) c;
            }
            processor = new ByteProcessor(gray, width, height);
            ((ByteProcessor)processor).setCallBack(this);
        }

        return this;
    }

    @Override
    public BufferedImage toBitmap() {
    	int[] pixels = new int[width * height];
    	if(bitmap == null) { // suitable with JPG/PNG
    		bitmap = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
    	}
    	setRGB(width, height, pixels, processor.toByte(0), processor.toByte(1), processor.toByte(2));
    	setRGB(bitmap, 0, 0, width, height, pixels );
    	return bitmap;
    }

    @Override
    public void setBitmap(BufferedImage bitmap) {
        width = bitmap.getWidth();
        height = bitmap.getHeight();
        int[] input = new int[width * height];
        getRGB(bitmap, 0, 0, width, height, input);
        processor = new ColorProcessor(input,width, height);
        ((ColorProcessor)processor).setCallBack(this);
        input = null;
    }

    public void resetBitmap() {
        this.bitmap = null;
    }

    /**
     * 保存图片到指定路径
     * @param bitmap
     * @param path
     */
    public void savePic(BufferedImage bitmap, String path) {
        File file = new File(path);
        try {
            FileOutputStream out = new FileOutputStream(file);
            ImageIO.write(bitmap, "png", out);
            out.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    /**
	 * A convenience method for getting ARGB pixels from an image. This tries to avoid the performance
	 * penalty of BufferedImage.getRGB unmanaging the image.
	 */
	public static int[] getRGB( BufferedImage image, int x, int y, int width, int height, int[] pixels ) {
		int type = image.getType();
		if ( type == BufferedImage.TYPE_INT_ARGB || type == BufferedImage.TYPE_INT_RGB )
			return (int [])image.getRaster().getDataElements( x, y, width, height, pixels );
		return image.getRGB( x, y, width, height, pixels, 0, width );
    }

	/**
	 * A convenience method for setting ARGB pixels in an image. This tries to avoid the performance
	 * penalty of BufferedImage.setRGB unmanaging the image.
	 */
	public static void setRGB( BufferedImage image, int x, int y, int width, int height, int[] pixels ) {
		int type = image.getType();
		if ( type == BufferedImage.TYPE_INT_ARGB || type == BufferedImage.TYPE_INT_RGB )
			image.getRaster().setDataElements( x, y, width, height, pixels );
		else
			image.setRGB( x, y, width, height, pixels, 0, width );
    }
	
	public void setRGB(int width, int height, int[] pixels, byte[] R, byte[] G, byte[] B) {
		for (int i=0; i < width*height; i++)
			pixels[i] = 0xff000000 | ((R[i]&0xff)<<16) | ((G[i]&0xff)<<8) | B[i]&0xff;
	}

    /**
     * 释放资源
     */
    public void recycle() {
        processor = null;
        bitmap = null;
    }

	@Override
	public ImageProcessor getProcessor() {
		return this.processor;
	}
}
