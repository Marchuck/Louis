package pl.kitowcy.louis.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Collection;
import java.util.Iterator;
import java.util.concurrent.Callable;

import pl.kitowcy.louis.R;
import pl.kitowcy.louis.facedetection.facedetection.EmotionRestClient;
import pl.kitowcy.louis.facedetection.facedetection.models.FaceAnalysis;
import rx.Observable;
import rx.Subscriber;

/**
 * Project "Next42Test"
 * <p/>
 * Created by Lukasz Marczak
 * on 20.10.16.
 */
public class Common {
    public static final String TAG = Common.class.getSimpleName();

    public static rx.Observable<Bitmap> drawableToBitmap(final Drawable drawable) {
        return Observable.fromCallable(new Callable<Bitmap>() {
            @Override
            public Bitmap call() throws Exception {
                return drawableToBitmapSynchronus(drawable);
            }
        });
    }

    public static rx.Observable<Bitmap> scaleBitmap(final Bitmap inputBitmap) {
        return Observable.fromCallable(new Callable<Bitmap>() {
            @Override
            public Bitmap call() throws Exception {
                return getResizedBitmap(inputBitmap);
            }
        });
    }


    public static rx.Observable<Bitmap> rotatedByAllAngles(final Bitmap bmp) {
        return Observable.create(new Observable.OnSubscribe<Bitmap>() {
            @Override
            public void call(Subscriber<? super Bitmap> subscriber) {
                float[] angles = new float[]{0, 90, 180, 270};
                for (float angle : angles) {
                    subscriber.onNext(
                            rotateBitmap(bmp, angle)
                    );
                }
            }
        });
    }


    public static Bitmap rotateBitmap(Bitmap source, float angle) {
        Matrix matrix = new Matrix();
        matrix.postRotate(angle);
        return Bitmap.createBitmap(source, 0, 0, source.getWidth(), source.getHeight(), matrix, true);
    }

    public static Bitmap getResizedBitmap(Bitmap bm) {
        int width = bm.getWidth();
        int height = bm.getHeight();

        int dstWidth = 500;
        int dtsHeight = dstWidth * (width) / (height);
        Bitmap resizedBitmap = Bitmap.createScaledBitmap(
                bm, dstWidth, dtsHeight, false);


//        Log.w(TAG, "getResizedBitmap: " + width + ", " + height);
//        float scaleWidth = ((float) dstWidth) / width;
//        float scaleHeight = ((float) dtsHeight) / height;
//        // CREATE A MATRIX FOR THE MANIPULATION
//        Matrix matrix = new Matrix();
//        // RESIZE THE BIT MAP
//        matrix.postScale(scaleWidth, scaleHeight);
//
//        // "RECREATE" THE NEW BITMAP
//        Bitmap resizedBitmap = Bitmap.createBitmap(
//                bm, 0, 0, width, height, matrix, false);
//        bm.recycle();
        return resizedBitmap;
    }

    public static rx.Observable<Drawable> uriToDrawable(final Context ctx, final Uri uri) {
        Log.d(TAG, "uriToDrawable: ");
        return Observable.fromCallable(new Callable<Drawable>() {
            @Override
            public Drawable call() throws Exception {

                Drawable yourDrawable;
                try {
                    InputStream inputStream = ctx.getContentResolver().openInputStream(uri);
                    yourDrawable = Drawable.createFromStream(inputStream, uri.toString());
                    return yourDrawable;
                } catch (FileNotFoundException e) {
                    yourDrawable = ctx.getResources().getDrawable(R.drawable.face);
                    return yourDrawable;
                }
            }
        });
    }

    public static <T> String printCollection(Collection<T> collection) {

        StringBuilder sb = new StringBuilder();
        Iterator<T> iterator = collection.iterator();
        sb.append("[ ");
        sb.append(iterator.next());
        while (iterator.hasNext()) {
            sb.append(", ").append(String.valueOf(iterator.next()));
        }
        return sb.append(" ]").toString();
    }

    public static Bitmap drawableToBitmapSynchronus(Drawable drawable) {
        Log.d(TAG, "drawableToBitmapSynchronus: ");
        Bitmap bitmap;

        if (drawable instanceof BitmapDrawable) {
            BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;
            if (bitmapDrawable.getBitmap() != null) {
                return bitmapDrawable.getBitmap();
            }
        }

        if (drawable.getIntrinsicWidth() <= 0 || drawable.getIntrinsicHeight() <= 0) {
            bitmap = Bitmap.createBitmap(1, 1, Bitmap.Config.ARGB_8888); // Single color bitmap will be created of 1x1 pixel
        } else {
            bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        }

        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);
        return bitmap;
    }

    public static byte[] bitmapToBytes(Bitmap bmp) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] byteArray = stream.toByteArray();
        return byteArray;
    }
}
