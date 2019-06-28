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

    public static byte[] bitmapToByte(Bitmap bitmap) {
        return bitmapToByte(bitmap, Bitmap.CompressFormat.JPEG);
    }

    public static byte[] bitmapToByte(Bitmap bitmap, Bitmap.CompressFormat format) {
        if (bitmap == null) {
            return null;
        }

        ByteArrayOutputStream o = new ByteArrayOutputStream();
        bitmap.compress(format, 100, o);
        return o.toByteArray();
    }


    public static Bitmap byteToBitmap(byte[] b) {
        return (b == null || b.length == 0) ? null : BitmapFactory.decodeByteArray(b, 0, b.length);
    }


    public static Bitmap drawableToBitmap(Drawable drawable) {
        return drawable == null ? null : ((BitmapDrawable)drawable).getBitmap();
    }

    /**
     * @deprecated Use {@link #bitmapToDrawable(Resources, Bitmap)}
     * @param bitmap
     * @return
     */
    @Deprecated
    public static Drawable bitmapToDrawable(Bitmap bitmap) {
        return bitmap == null ? null : new BitmapDrawable(bitmap);
    }

    public static Drawable bitmapToDrawable(Resources res,Bitmap bitmap) {
        return bitmap == null ? null : new BitmapDrawable(res,bitmap);
    }

    public static byte[] drawableToByte(Drawable d) {
        return bitmapToByte(drawableToBitmap(d));
    }


    public static Drawable byteToDrawable(byte[] b) {
        return bitmapToDrawable(byteToBitmap(b));
    }


    public static Bitmap scaleImageTo(Bitmap bitmap, int newWidth, int newHeight) {
        return scaleImage(bitmap, (float) newWidth / bitmap.getWidth(), (float) newHeight / bitmap.getHeight());
    }


    public static Bitmap scaleImage(Bitmap bitmap, float scaleWidth, float scaleHeight) {
        if (bitmap == null) {
            return null;
        }

        Matrix matrix = new Matrix();
        matrix.postScale(scaleWidth, scaleHeight);
        return Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
    }

    public static Bitmap rotate(Bitmap bitmap,int degree){
        Matrix matrix = new Matrix();
        matrix.postRotate(270);
        matrix.postScale(-1,1);
        return Bitmap.createBitmap(bitmap,0,0,bitmap.getWidth(),bitmap.getHeight(),matrix,true);
    }

    public static Bitmap mirror(Bitmap bitmap,int degree){
        Matrix matrix = new Matrix();
        matrix.postScale(-1,1);
        return Bitmap.createBitmap(bitmap,0,0,bitmap.getWidth(),bitmap.getHeight(),matrix,true);
    }

}
