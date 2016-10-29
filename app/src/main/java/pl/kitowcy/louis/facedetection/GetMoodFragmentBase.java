package pl.kitowcy.louis.facedetection;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import pl.kitowcy.louis.R;


public class GetMoodFragmentBase extends Fragment {

    public static final String TAG = GetMoodFragmentBase.class.getSimpleName();


    public GetMoodFragmentBase() {

    }

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
