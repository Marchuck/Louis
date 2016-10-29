package pl.kitowcy.louis.mapsy;

import android.content.Context;
import android.content.res.Resources;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.MapStyleOptions;

import java.util.concurrent.Callable;

import pl.kitowcy.louis.R;
import rx.Observable;
import rx.Subscriber;

/**
 * Project "Louis"
 * <p>
 * Created by Lukasz Marczak
 * on 29.10.16.
 */
public class MapUtils {

    public static rx.Observable<GoogleMap> geGoogleMap(SupportMapFragment mapFragment) {
        return Observable.create(new Observable.OnSubscribe<GoogleMap>() {
            @Override
            public void call(Subscriber<? super GoogleMap> subscriber) {
                mapFragment.getMapAsync(subscriber::onNext);
            }
        });
    }

    public static rx.Observable<Boolean> setupGoogleMap(GoogleMap map, Context ctx) {
        return Observable.fromCallable(() -> {
            try {
                boolean success = map.setMapStyle(
                        MapStyleOptions.loadRawResourceStyle(ctx, R.raw.style_json));
                if (!success) {
                    // Handle map style load failure
                    return false;
                }
            } catch (Resources.NotFoundException e) {
                // Oops, looks like the map style resource couldn't be found!
                return false;
            }
            return true;
        });


    }
}
