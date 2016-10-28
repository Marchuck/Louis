package pl.kitowcy.louis.facedetection.facedetection;

import pl.kitowcy.louis.facedetection.facedetection.models.FaceAnalysis;

/**
 * Created by David Pacioianu on 1/12/16.
 */
public interface ResponseCallback {

    void onError(String errorMessage);

    void onSuccess(FaceAnalysis[] response);

}
