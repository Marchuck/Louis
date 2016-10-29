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

import agency.tango.materialintroscreen.animations.translations.EnterDefaultTranslation;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import pl.kitowcy.louis.R;
import pl.kitowcy.louis.facedetection.api.EmotionRestClient;
import pl.kitowcy.louis.facedetection.api.models.FaceAnalysis;
import pl.kitowcy.louis.facedetection.api.models.Scores;
import pl.kitowcy.louis.utils.Common;
import rx.schedulers.Schedulers;


public class GetMoodFragmentBase extends Fragment {

    public static final String TAG = GetMoodFragmentBase.class.getSimpleName();


    public GetMoodFragmentBase() {

    }


    // TODO: Rename and change types and number of parameters
    public static GetMoodFragmentBase newInstance() {
        GetMoodFragmentBase fragment = new GetMoodFragmentBase();
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
        View view = inflater.inflate(R.layout.fragment_get_mood_base, container, false);

        getActivity().getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.rootLayoutBase, GetMoodFragment.newInstance())
                .commitAllowingStateLoss();

        return view;
    }

}
