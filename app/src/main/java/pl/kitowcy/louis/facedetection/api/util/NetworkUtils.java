package pl.kitowcy.louis.facedetection.api.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by David Pacioianu on 1/13/16.
 */
public class NetworkUtils {

    public static boolean hasInternetConnection(Context context){
        ConnectivityManager cm = (ConnectivityManager) context.getApplicationContext().
                getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnected();
    }

}
