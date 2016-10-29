package pl.kitowcy.louis.facedetection;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import pl.kitowcy.louis.R;
import pl.kitowcy.louis.facedetection.api.EmotionRestClient;
import pl.kitowcy.louis.facedetection.api.models.FaceAnalysis;
import pl.kitowcy.louis.facedetection.api.models.Scores;
import pl.kitowcy.louis.utils.Common;
import rx.schedulers.Schedulers;


public class GetMoodFragment extends Fragment {

    public static final String TAG = GetMoodFragment.class.getSimpleName();
    public static final int PHOTO_TAKE = 2137;

    @BindView(R.id.fragment_get_mood_camera_fab)
    FloatingActionButton fab;

    @OnClick(R.id.fragment_get_mood_camera_fab)
    void onCameraClick() {
        Dexter.checkPermissions(new MultiplePermissionsListener() {
                                    @Override
                                    public void onPermissionsChecked(MultiplePermissionsReport report) {
                                        Intent takePicture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                                        getActivity().startActivityForResult(takePicture, PHOTO_TAKE);
                                        //zero can be replaced with any action code
                                    }

                                    @Override
                                    public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions,
                                                                                   PermissionToken token) {
                                        Log.d(TAG, "Grant permission to use this feature");
                                        enterStuffManually();
                                    }
                                },
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.CAMERA);
    }

    void enterStuffManually() {
        getActivity().getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.rootLayoutBase, ManuallyEnteredMoodFragment.newInstance())
                .commitAllowingStateLoss();
    }

    @Override
    public void onActivityResult(final int requestCode, int resultCode, Intent imageIntent) {
        super.onActivityResult(requestCode, resultCode, imageIntent);
        Log.d(TAG, "onActivityResult: ");
        if (requestCode == PHOTO_TAKE) {
            if (corruptedIntent(imageIntent)) {
                Log.e(TAG, "onActivityResult: no photo found");
                return;
            }
            android.net.Uri selectedImage = imageIntent.getData();
            Log.i(TAG, "onActivityResult: xDDD");


            Context ctx = getActivity();
            Common.uriToDrawable(ctx, selectedImage)
                    .flatMap(Common::drawableToBitmap)
                    .flatMap(Common::scaleBitmap)
                    .flatMap(Common::rotatedByAllAngles)
                    .flatMap(bitmap -> EmotionRestClient.getInstance().detectAsync(bitmap))
                    .filter(response -> response != null && response.length > 0)
                    .subscribeOn(Schedulers.newThread())
                    .subscribe(faceAnalysises -> {
                        Log.d(TAG, "onNext: ");
                        for (FaceAnalysis analysis : faceAnalysises) {
                            Log.d(TAG, "onSuccess: ");
                            Scores scores = analysis.getScores().scaleWith(1000);
                            if (scores != null) {
                                Log.d(TAG, "anger: " + scores.getAnger());
                                Log.d(TAG, "contempt: " + scores.getContempt());
                                Log.d(TAG, "disgust: " + scores.getDisgust());
                                Log.d(TAG, "happiness: " + scores.getHappiness());
                                Log.d(TAG, "sadness: " + scores.getSadness());
                                Log.d(TAG, "surprise: " + scores.getSurprise());
                            }
                        }
                    }, throwable -> Log.e(TAG, "onError: ", throwable));
        }
    }


    private boolean corruptedIntent(Intent imageReturnedIntent) {
        return imageReturnedIntent == null || imageReturnedIntent.getData() == null;
    }

    public GetMoodFragment() {

    }


    // TODO: Rename and change types and number of parameters
    public static GetMoodFragment newInstance() {
        GetMoodFragment fragment = new GetMoodFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_get_mood, container, false);
        ButterKnife.bind(this, view);
        //  fab = (FloatingActionButton)v.findViewById(R.id.fragment_get_mood_camera_fab);
        //   fab.setOnClickListener(v1 -> onCameraClick());
        return view;
    }

}
