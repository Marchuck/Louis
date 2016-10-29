package pl.kitowcy.louis.facedetection;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bq.markerseekbar.MarkerSeekBar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import pl.kitowcy.louis.App;
import pl.kitowcy.louis.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ManuallyEnteredMoodFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ManuallyEnteredMoodFragment extends Fragment {

    Unbinder unbinder;


    @BindView(R.id.seekbar_surprise)
    MarkerSeekBar surprise;

    @BindView(R.id.seekbar_disgust)
    MarkerSeekBar disgust;

    @BindView(R.id.seekbar_happiness)
    MarkerSeekBar happiness;

    @BindView(R.id.seekbar_sadness)
    MarkerSeekBar sadness;


    public ManuallyEnteredMoodFragment() {
        // Required empty public constructor
    }

    public static ManuallyEnteredMoodFragment newInstance() {
        ManuallyEnteredMoodFragment fragment = new ManuallyEnteredMoodFragment();
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
        View view = inflater.inflate(R.layout.fragment_manually_entered_mood, container, false);
        unbinder = ButterKnife.bind(this, view);


        surprise.setProgress((int) App.getApp(this).scores.getSurprise());
        sadness.setProgress((int) App.getApp(this).scores.getSadness());
        happiness.setProgress((int) App.getApp(this).scores.getHappiness());
        disgust.setProgress((int) App.getApp(this).scores.getDisgust());

        return view;
    }

    @Override
    public void onDestroyView() {
        unbinder.unbind();
        super.onDestroyView();
    }
}
