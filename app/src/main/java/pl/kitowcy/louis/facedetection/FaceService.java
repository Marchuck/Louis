package pl.kitowcy.louis.facedetection;

import android.net.Uri;
import android.util.Log;

import pl.kitowcy.louis.facedetection.facedetection.EmotionRestClient;
import pl.kitowcy.louis.facedetection.facedetection.ResponseCallback;
import pl.kitowcy.louis.facedetection.facedetection.models.FaceAnalysis;
import pl.kitowcy.louis.facedetection.facedetection.models.Scores;

/**
 * Project "Louis"
 * <p/>
 * Created by Lukasz Marczak
 * on 28.10.16.
 */
public class FaceService {

    public static final String TAG = FaceService.class.getSimpleName();

    void test(Uri uri) {
        EmotionRestClient.getInstance().detect(uri, new ResponseCallback() {
            @Override
            public void onError(String errorMessage) {
                Log.e(TAG, "onError: " + errorMessage);
            }

            @Override
            public void onSuccess(FaceAnalysis[] response) {
                for (FaceAnalysis analysis : response) {
                    Log.d(TAG, "onSuccess: ");
                    Scores scores = analysis.getScores();
                    if (scores != null) {
                        Log.d(TAG, "anger: "+scores.getAnger());
                        Log.d(TAG, "contempt: "+scores.getContempt());
                        Log.d(TAG, "disgust: "+scores.getDisgust());
                        Log.d(TAG, "happiness: "+scores.getHappiness());
                        Log.d(TAG, "sadness: "+scores.getSadness());
                        Log.d(TAG, "surprise: "+scores.getSurprise());
                    }
                }
            }
        });
    }
}
