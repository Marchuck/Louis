package pl.kitowcy.louis;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Patryk Mieczkowski on 29.10.2016
 */

public class NameFragment extends Fragment {

    public static NameFragment newInstance() {
        return new NameFragment();
    }

    Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_name, container, false);
        unbinder = ButterKnife.bind(this, view);

        return view;
    }
}
