package pl.kitowcy.louis;

import android.Manifest;
import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import android.util.Log;

import pl.kitowcy.louis.facedetection.GetMoodFragment;
import pl.kitowcy.louis.facedetection.GetMoodFragmentBase;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

import com.github.paolorotolo.appintro.AppIntro;
import com.github.paolorotolo.appintro.AppIntroFragment;

public class MainActivity extends AppIntro {
    public static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addSlide(NameFragment.newInstance());
        addSlide(GetMoodFragmentBase.newInstance());
        addSlide(
                AppIntroFragment.newInstance("Hello, I'm Louis", "We need to know each other C:",
                        android.R.drawable.ic_media_play, ContextCompat.getColor(this, android.R.color.holo_orange_dark)));
        addSlide(
                AppIntroFragment.newInstance("tutaj", "jest ostatni moj slajdzik opis",
                        android.R.drawable.arrow_up_float, ContextCompat.getColor(this, android.R.color.holo_red_dark)));
    }

    @Override
    public void onDonePressed(Fragment currentFragment) {
        super.onDonePressed(currentFragment);
        Intent intent = new Intent(this, PropositionActivity.class);
        startActivity(intent);
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        finish();
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
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