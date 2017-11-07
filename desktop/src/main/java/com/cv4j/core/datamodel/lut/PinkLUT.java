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
package com.cv4j.core.datamodel.lut;

/**
 * The filter which is contains pastel shades of pink.
 * The pink colormap provides sepia tone colorization of grayscale photographs.
 * <p>
 * For more information please see:
 * <a target="_blank" href="http://baike.baidu.com/link?url=kysXstK853g0mEbTgPIdkrqO5qTTbgfW-B0O1FJP4MYYzp
 * G5_6E_LlaP_6T9XTn2c97Ge6hJUojJzkppCdnO-mJxIee_XsNASTsy82RaQZe">About ColorMap</a> <p>
 * Or see:
 * <a target="_blank" href="http://matlab.izmiran.ru/help/techdoc/ref/colormap.html">MATLAB Function Reference - colormap</a>
 *
 */
public class PinkLUT {

    public static int[][] PINK_LUT = new int[][]{
            {1, 0, 0}, {10, 6, 5}, {19, 14, 11}, {29, 19, 18}, {38, 26, 26},
            {44, 30, 30}, {49, 31, 31}, {52, 34, 34}, {57, 37, 36}, {59, 39, 38},
            {63, 42, 41}, {65, 44, 43}, {69, 45, 45}, {71, 47, 47}, {74, 48, 49},
            {76, 50, 51}, {80, 52, 51}, {82, 54, 53}, {85, 55, 55}, {86, 56, 56},
            {88, 58, 58}, {90, 60, 60}, {93, 61, 62}, {94, 62, 63}, {98, 64, 63},
            {99, 65, 64}, {101, 65, 65}, {103, 67, 67}, {105, 69, 69}, {106, 70, 70},
            {109, 70, 71}, {111, 72, 73}, {113, 75, 74}, {114, 76, 75}, {115, 77, 76},
            {116, 78, 77}, {118, 78, 78}, {120, 80, 80}, {122, 80, 81}, {123, 81, 82},
            {126, 82, 81}, {127, 83, 82}, {129, 83, 83}, {131, 85, 85}, {134, 86, 86},
            {135, 87, 87}, {136, 88, 88}, {137, 89, 89}, {138, 90, 90}, {139, 91, 91},
            {141, 93, 93}, {142, 94, 94}, {143, 95, 95}, {144, 96, 96}, {146, 96, 97},
            {147, 97, 98}, {149, 98, 97}, {150, 99, 98}, {151, 100, 99}, {152, 101, 100},
            {153, 102, 101}, {154, 103, 102}, {157, 103, 103}, {157, 103, 103}, {158, 104, 104},
            {160, 106, 106}, {162, 106, 107}, {163, 107, 108}, {164, 108, 107}, {164, 108, 107},
            {165, 109, 108}, {166, 110, 109}, {169, 111, 110}, {170, 112, 111}, {172, 112, 112},
            {173, 113, 113}, {174, 114, 114}, {174, 114, 114}, {175, 115, 115}, {176, 116, 116},
            {177, 117, 117}, {178, 118, 118}, {180, 118, 119}, {181, 119, 120}, {181, 120, 119},
            {182, 121, 120}, {185, 121, 121}, {186, 122, 122}, {187, 121, 122}, {188, 122, 123},
            {189, 123, 124}, {190, 124, 125}, {191, 126, 124}, {192, 127, 125}, {193, 128, 126},
            {194, 129, 127}, {195, 130, 128}, {195, 130, 128}, {196, 131, 129}, {197, 132, 130},
            {197, 133, 131}, {199, 135, 133}, {198, 137, 132}, {198, 137, 132}, {199, 140, 132},
            {200, 141, 133}, {200, 143, 134}, {201, 144, 135}, {199, 145, 135}, {201, 147, 137},
            {201, 149, 136}, {201, 149, 136}, {201, 152, 138}, {201, 152, 138}, {202, 153, 139},
            {204, 155, 141}, {203, 156, 140}, {204, 157, 141}, {205, 159, 143}, {205, 159, 143},
            {204, 161, 142}, {205, 162, 143}, {206, 163, 144}, {207, 164, 145}, {207, 166, 144},
            {208, 167, 145}, {208, 167, 145}, {207, 169, 146}, {208, 170, 147}, {208, 172, 148},
            {209, 173, 149}, {210, 174, 150}, {210, 176, 149}, {210, 176, 149}, {211, 177, 150},
            {212, 178, 151}, {211, 180, 152}, {212, 181, 153}, {213, 182, 153}, {214, 183, 154},
            {213, 184, 154}, {214, 185, 155}, {215, 186, 156}, {216, 187, 157}, {214, 188, 155},
            {215, 189, 156}, {216, 190, 157}, {217, 191, 158}, {216, 192, 158}, {217, 193, 159},
            {217, 194, 160}, {218, 195, 161}, {217, 197, 160}, {218, 198, 161}, {219, 199, 162},
            {219, 199, 162}, {219, 201, 163}, {219, 201, 163}, {220, 202, 164}, {221, 203, 165},
            {220, 205, 164}, {220, 205, 164}, {221, 206, 165}, {222, 207, 166}, {222, 209, 167},
            {222, 209, 167}, {223, 210, 166}, {224, 211, 167}, {224, 213, 168}, {225, 214, 169},
            {225, 214, 169}, {226, 215, 170}, {225, 217, 171}, {225, 217, 171}, {226, 218, 172},
            {226, 218, 172}, {227, 219, 172}, {228, 220, 173}, {228, 222, 174}, {228, 222, 174},
            {227, 223, 175}, {228, 224, 176}, {229, 225, 177}, {229, 225, 177}, {230, 227, 176},
            {230, 227, 176}, {231, 228, 177}, {232, 229, 178}, {233, 230, 179}, {233, 230, 179},
            {233, 231, 180}, {233, 231, 180}, {233, 233, 181}, {233, 233, 181}, {234, 234, 184},
            {235, 235, 185}, {236, 235, 187}, {236, 235, 187}, {236, 235, 189}, {237, 236, 190},
            {235, 236, 192}, {235, 236, 192}, {236, 237, 195}, {236, 237, 195}, {237, 238, 198},
            {238, 239, 199}, {238, 238, 200}, {238, 238, 200}, {239, 239, 203}, {239, 239, 203},
            {240, 240, 206}, {240, 240, 206}, {240, 239, 208}, {241, 240, 209}, {241, 240, 210},
            {242, 241, 211}, {242, 243, 212}, {242, 243, 212}, {242, 242, 214}, {243, 243, 215},
            {243, 243, 217}, {243, 243, 217}, {244, 244, 220}, {244, 244, 220}, {244, 243, 222},
            {245, 244, 223}, {246, 245, 224}, {246, 245, 224}, {245, 247, 226}, {245, 247, 226},
            {246, 247, 229}, {246, 247, 229}, {246, 247, 231}, {248, 249, 233}, {247, 248, 234},
            {247, 248, 234}, {249, 249, 237}, {249, 249, 237}, {249, 249, 237}, {250, 250, 238},
            {252, 249, 240}, {253, 250, 241}, {251, 251, 243}, {251, 251, 243}, {251, 251, 243},
            {252, 252, 244}, {251, 252, 246}, {251, 252, 246}, {252, 253, 247}, {253, 254, 248},
            {253, 254, 249}, {254, 255, 250}, {254, 254, 252}, {254, 254, 252}, {255, 255, 255},
            {255, 255, 255}
    };
}