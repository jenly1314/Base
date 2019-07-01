/*
 Copyright © 2015, 2016 Jenly Yu <a href="mailto:jenly1314@gmail.com">Jenly</a>

 Licensed under the Apache License, Version 2.0 (the "License");
 you may not use this file except in compliance with the License.
 You may obtain a copy of the License at

 	http://www.apache.org/licenses/LICENSE-2.0

 Unless required by applicable law or agreed to in writing, software
 distributed under the License is distributed on an "AS IS" BASIS,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 See the License for the specific language governing permissions and
 limitations under the License.

 */
package com.king.base.util;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

import java.io.ByteArrayOutputStream;

/**
 * @author Jenly <a href="mailto:jenly1314@gmail.com">Jenly</a>
 */

public class ImageUtils {

    private ImageUtils() {
        throw new AssertionError();
    }

    /**
     * 图片转字节数组
     * @param bitmap
     * @return
     */
    public static byte[] bitmapToByte(Bitmap bitmap) {
        return bitmapToByte(bitmap, Bitmap.CompressFormat.JPEG);
    }

    /**
     * 图片转字节数组
     * @param bitmap
     * @param format
     * @return
     */
    public static byte[] bitmapToByte(Bitmap bitmap, Bitmap.CompressFormat format) {
        if (bitmap == null) {
            return null;
        }

        ByteArrayOutputStream o = new ByteArrayOutputStream();
        bitmap.compress(format, 100, o);
        return o.toByteArray();
    }

    /**
     * 字节数组转图片
     * @param data
     * @return
     */
    public static Bitmap byteToBitmap(byte[] data) {
        return (data == null || data.length == 0) ? null : BitmapFactory.decodeByteArray(data, 0, data.length);
    }

    /**
     * {@link Drawable} 转 {@link Bitmap}
     * @param drawable
     * @return
     */
    public static Bitmap drawableToBitmap(Drawable drawable) {
        return drawable == null ? null : ((BitmapDrawable)drawable).getBitmap();
    }

    /**
     * {@link Bitmap} 转 {@link Drawable}
     * @deprecated Use {@link #bitmapToDrawable(Resources, Bitmap)}
     * @param bitmap
     * @return
     */
    @Deprecated
    public static Drawable bitmapToDrawable(Bitmap bitmap) {
        return bitmap == null ? null : new BitmapDrawable(bitmap);
    }

    /**
     * {@link Bitmap} 转 {@link Drawable}
     * @param res
     * @param bitmap
     * @return
     */
    public static Drawable bitmapToDrawable(Resources res,Bitmap bitmap) {
        return bitmap == null ? null : new BitmapDrawable(res,bitmap);
    }

    /**
     * 图片转字节数组
     * @param d
     * @return
     */
    public static byte[] drawableToByte(Drawable d) {
        return bitmapToByte(drawableToBitmap(d));
    }

    /**
     * 字节数组转图片
     * @param data
     * @return
     */
    public static Drawable byteToDrawable(byte[] data) {
        return bitmapToDrawable(byteToBitmap(data));
    }


    /**
     * 缩放图片
     * @param bitmap
     * @param newWidth 缩放后图片新的宽度
     * @param newHeight 缩放后图片新的高度
     * @return
     */
    public static Bitmap scaleImageTo(Bitmap bitmap, int newWidth, int newHeight) {
        return scaleImage(bitmap, (float) newWidth / bitmap.getWidth(), (float) newHeight / bitmap.getHeight());
    }

    /**
     * 缩放图片
     * @param bitmap
     * @param scaleWidth 缩放宽
     * @param scaleHeight 缩放高
     * @return
     */
    public static Bitmap scaleImage(Bitmap bitmap, float scaleWidth, float scaleHeight) {
        if (bitmap == null) {
            return null;
        }

        Matrix matrix = new Matrix();
        matrix.postScale(scaleWidth, scaleHeight);
        return Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
    }

    /**
     * 旋转图片
     * @param bitmap
     * @param degree 旋转角度
     * @return
     */
    public static Bitmap rotate(Bitmap bitmap,int degree){
        Matrix matrix = new Matrix();
        matrix.postRotate(degree);
        matrix.postScale(-1,1);
        return Bitmap.createBitmap(bitmap,0,0,bitmap.getWidth(),bitmap.getHeight(),matrix,true);
    }

    /**
     * 镜像(像照镜子一样，左右互换)
     * @param bitmap
     * @return
     */
    public static Bitmap mirror(Bitmap bitmap){
        Matrix matrix = new Matrix();
        matrix.postScale(-1,1);
        return Bitmap.createBitmap(bitmap,0,0,bitmap.getWidth(),bitmap.getHeight(),matrix,true);
    }

}
