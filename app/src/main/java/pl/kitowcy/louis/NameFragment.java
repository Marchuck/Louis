package pl.kitowcy.louis;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Patryk Mieczkowski on 29.10.2016
 */

public class NameFragment extends Fragment {
    public static final String TAG = NameFragment.class.getSimpleName();

    public static NameFragment newInstance() {
        return new NameFragment();
    }

    Unbinder unbinder;
    float dx, dy;
    float DY = 10, DX = 20;

    boolean canLoop = true;

    public void loop() {
        louis.animate()
                .translationX(dx + DX)
                .translationY(dy)
                .translationYBy(dy - DY)
                .setDuration(300)
                .withEndAction(() -> {
                    Log.d(TAG, "run: " + louis.getX());
                    louis.animate()
                            .translationX(dx - DX)
                            .translationY(dy)
                            .translationYBy(dy + DY)
                            .setDuration(300)
                            .withEndAction(() -> {
                                Log.d(TAG, "run: " + louis.getX());
                                if (canLoop) loop();

                            }).start();
                }).start();
    }

    final int TIME = 260;


    @BindView(R.id.louis_pivot)
    ImageView louis;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_name, container, false);
        unbinder = ButterKnife.bind(this, view);


        dx = louis.getX();
        dy = louis.getY();
        startAnimation();
        return view;
    }

    private void startAnimation() {
        loop();
    }

    @Override
    public void onPause() {
        super.onPause();
        canLoop = false;

    }

    @Override
    public void onResume() {
        super.onResume();
        canLoop = true;
        loop();
    }
}
