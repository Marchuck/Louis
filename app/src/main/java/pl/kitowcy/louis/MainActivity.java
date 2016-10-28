package pl.kitowcy.louis;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import pl.kitowcy.louis.facedetection.GetMoodFragment;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.rootLayout, GetMoodFragment.newInstance(), GetMoodFragment.TAG)
                .commitAllowingStateLoss();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d(TAG, "onActivityResult: ");

        Fragment fragment = getSupportFragmentManager().findFragmentByTag(GetMoodFragment.TAG);
        if (fragment instanceof GetMoodFragment) {
            GetMoodFragment fragment1 = ((GetMoodFragment) fragment);
            fragment1.onActivityResult(requestCode, resultCode, data);
        }

    }
}
