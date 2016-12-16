package pl.kitowcy.louis;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import pl.kitowcy.louis.facedetection.GetMoodFragment;
import pl.kitowcy.louis.facedetection.GetMoodFragmentBase;
import pl.kitowcy.louis.mapsy.MyLocationFragment;
import pl.kitowcy.louis.meetup.MeetupClient;
import pl.kitowcy.louis.meetup.model.EventsResponse;
import pl.kitowcy.louis.utils.Is;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

import com.github.paolorotolo.appintro.AppIntro;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;

import java.util.List;

public class MainActivity extends AppIntro {

    public static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate: ");

        addSlide(NameFragment.newInstance());
        addSlide(GetMoodFragmentBase.newInstance());
        addSlide(MyLocationFragment.newInstance());
        addSlide(CategoryFragment.newInstance());

        showSkipButton(false);

        Dexter.checkPermissions(new MultiplePermissionsListener() {
                                    @Override
                                    public void onPermissionsChecked(MultiplePermissionsReport report) {
                                        startService(new Intent(getApplication(), LocationService.class));
                                    }

                                    @Override
                                    public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {
                                        finish();
                                    }
                                },
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.CAMERA,
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_WIFI_STATE);

        MeetupClient client = new MeetupClient();
        client.getEvents("ny-tech", true)
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(eventsResponse -> {
                    Log.i(TAG, "call: " + eventsResponse);
                    Toast.makeText(MainActivity.this, eventsResponse.eventResults.get(0).name,
                            Toast.LENGTH_SHORT).show();
                }, throwable -> {
                    Log.e(TAG, "error: ", throwable);
                    Toast.makeText(MainActivity.this, "error", Toast.LENGTH_SHORT).show();
                });

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

        int position = App.currentPage;
        if (getSupportFragmentManager() != null)
            if (Is.nonEmpty(getSupportFragmentManager().getFragments()))
                for (Fragment fr : getSupportFragmentManager().getFragments()) {
                    if (position == 1 && fr instanceof GetMoodFragment) {
                        GetMoodFragment frag = ((GetMoodFragment) fr);

                        frag.onActivityResult(requestCode, resultCode, data);

                    }
                }
    }

    public void onPageSelected() {


        int position = App.currentPage;
        Log.d(TAG, "onPageSelected: " + position);
        if (getSupportFragmentManager() != null)
            if (Is.nonEmpty(getSupportFragmentManager().getFragments()))
                for (Fragment fr : getSupportFragmentManager().getFragments()) {
                    if (position == 1 && fr instanceof GetMoodFragment) {
                        GetMoodFragment frag = ((GetMoodFragment) fr);
                        frag.setupLouis();


                    } else if (position == 2 && fr instanceof MyLocationFragment) {
                        MyLocationFragment frag = ((MyLocationFragment) fr);
                        frag.initOnce();
                    }
                }
    }


    @Override
    public void onPageSelected(int position) {
        super.onPageSelected(position);
        App.currentPage = position;
        onPageSelected();

    }
}