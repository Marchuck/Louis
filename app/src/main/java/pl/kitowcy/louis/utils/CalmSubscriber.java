package pl.kitowcy.louis.utils;

import android.util.Log;

import rx.Subscriber;

/**
 * Project "Louis"
 * <p>
 * Created by Lukasz Marczak
 * on 29.10.16.
 */
public class CalmSubscriber<T> extends Subscriber<T> {
    final String TAG;

    public CalmSubscriber() {
        TAG = CalmSubscriber.class.getSimpleName();
    }

    public CalmSubscriber(String TAG) {
        this.TAG = TAG;
    }

    @Override
    public void onCompleted() {
        Log.d(TAG, "onCompleted: ");
    }

    @Override
    public void onError(Throwable e) {
        Log.e(TAG, "onError: ",e );
    }

    @Override
    public void onNext(T t) {
        Log.d(TAG, "onNext: "+String.valueOf(t));
    }
}
