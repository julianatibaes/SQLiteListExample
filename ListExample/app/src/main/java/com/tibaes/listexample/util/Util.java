package com.tibaes.listexample.util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.ImageView;

/**
 * Created by julia on 26/03/2017.
 */

public class Util {

    public static void uploadImage(String pathPhoto, ImageView imageView) {
        if (pathPhoto != null) {
            Bitmap bitmap = BitmapFactory.decodeFile(pathPhoto);
            Bitmap bitmapReduzido = Bitmap.createScaledBitmap(bitmap, 128, 128, true);
            imageView.setImageBitmap(bitmapReduzido);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            imageView.setTag(pathPhoto);
        }
    }
}
