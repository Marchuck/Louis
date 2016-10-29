package pl.kitowcy.louis.facedetection;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import pl.kitowcy.louis.R;
import pl.kitowcy.louis.mapsy.MyLocationFragment;

public class TestFragmentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_fragment);


        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.rootLayoutTest, MyLocationFragment.newInstance())

                .commitAllowingStateLoss();
    }
}
