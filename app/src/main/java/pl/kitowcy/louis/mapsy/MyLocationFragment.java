package pl.kitowcy.louis.mapsy;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import pl.kitowcy.louis.LocationService;
import pl.kitowcy.louis.R;
import pl.kitowcy.louis.utils.CalmSubscriber;
import pl.kitowcy.louis.utils.ViewBindings;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyLocationFragment extends Fragment implements MultiplePermissionsListener {
    public static final String TAG = MyLocationFragment.class.getSimpleName();

    public MyLocationFragment() {
    }

    public static MyLocationFragment newInstance() {
        return new MyLocationFragment();
    }

    Unbinder unbinder;

    SupportMapFragment mapFragment;
    GoogleMap googleMap;
    LatLng myPosition = null;
    boolean locationServiceRegistered;
    int meters = 10;
    public BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.d(TAG, "onReceive: ");


            progressbar_location.setVisibility(View.GONE);

            Log.d(TAG, "onReceive: new Location");
            Bundle extras = intent.getExtras();
            myPosition = new LatLng(extras.getDouble("Latitude"), extras.getDouble("Longitude"));

            CameraPosition cameraPosition = new CameraPosition(myPosition, 11, 60, 12);
            final CameraUpdate cameraUpdate = CameraUpdateFactory.newCameraPosition(cameraPosition);

            getGoogleMap().animateCamera(cameraUpdate);
            onRadiusChanged(meters);

            //  locationReallyChanged = (locationReallyChanged + 1) % 5;
        }
    };


    void onRadiusChanged(int meters) {
        Log.d(TAG, "onRadiusChanged: ");
        if (getGoogleMap() != null && myPosition != null) {
            Log.d(TAG, "onRadiusChanged, invoked ");
            getGoogleMap().clear();
            getGoogleMap().addCircle(
                    new CircleOptions()
                            .center(myPosition)
                            .fillColor(Color.argb(50, 0, 0, 255))
                            .radius(meters)
            );
        }
    }

    @BindView(R.id.radiusChanger)
    SeekBar seekBar;

    @BindView(R.id.title)
    TextView title;

    @BindView(R.id.description)
    TextView description;

    @BindView(R.id.progressbar_location)
    ProgressBar progressbar_location;

    @Override
    public void onPause() {
        Log.d(TAG, "onPause: ");
        if (locationServiceRegistered) {
            getActivity().unregisterReceiver(receiver);
            locationServiceRegistered = false;
        }
        super.onPause();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle sis) {
        View view = inflater.inflate(R.layout.fragment_my_location, container, false);
        unbinder = ButterKnife.bind(this, view);

        seekBar.setMax(10 * 1000);//10 km

        ViewBindings
                .seekBarProgress(seekBar)
                .map(progress -> {
                    meters = progress + 10;
                    return meters;
                })
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new CalmSubscriber<>(TAG));
        Dexter.checkPermissions(this,
                android.Manifest.permission.ACCESS_NETWORK_STATE,
                android.Manifest.permission.ACCESS_COARSE_LOCATION,
                android.Manifest.permission.ACCESS_FINE_LOCATION);
        return view;
    }

    private void setupBroadcastFromLocation() {
        Log.d(TAG, "setupBroadcastFromLocation: ");
        IntentFilter filter = new IntentFilter();
        filter.addAction(LocationService.BROADCAST_ACTION);
        getActivity().registerReceiver(receiver, filter);
        getActivity().startService(new Intent(getActivity(), LocationService.class));
        locationServiceRegistered = true;
    }

    private SupportMapFragment getSupportMapFragment() {
        if (mapFragment == null) mapFragment = SupportMapFragment.newInstance();
        return mapFragment;
    }

    @Override
    public void onDestroyView() {
        unbinder.unbind();
        super.onDestroyView();
    }

    public GoogleMap getGoogleMap() {
        return googleMap;
    }

    @Override
    public void onPermissionsChecked(MultiplePermissionsReport report) {
        Log.d(TAG, "onPermissionsChecked: ");
        progressbar_location.setVisibility(View.VISIBLE);

        getActivity().getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.map_layout, getSupportMapFragment())
                .commitAllowingStateLoss();

        MapUtils.geGoogleMap(getSupportMapFragment())
                .observeOn(AndroidSchedulers.mainThread())
                .flatMap(_googleMap -> {
                    googleMap = _googleMap;
                    return MapUtils.setupGoogleMap(googleMap, getActivity());
                })
                .subscribe(aBoolean -> {
                    Log.d(TAG, "done " + aBoolean);
                    setupBroadcastFromLocation();
                }, throwable -> {
                    Log.e(TAG, "onError: ", throwable);
                    progressbar_location.setVisibility(View.GONE);
                    Toast.makeText(getActivity(), throwable.getMessage(), Toast.LENGTH_SHORT).show();
                });
    }

    @Override
    public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {

    }
}
