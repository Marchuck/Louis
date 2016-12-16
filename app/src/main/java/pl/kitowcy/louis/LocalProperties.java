package pl.kitowcy.louis;

import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Project "Louis"
 * <p>
 * Created by Lukasz Marczak
 * on 16.12.2016.
 */

public class LocalProperties {


    public static void install(Context context) {

        Properties localProperties = new Properties();
        try {
            //access to the folder ‘assets’
            AssetManager am = context.getAssets();
            //opening the file
            InputStream inputStream = am.open("local.properties");
            //loading of the properties
            localProperties.load(inputStream);
            inputStream.close();
        } catch (IOException e) {
            Log.e("LocalProperties", e.toString());
        }
        System.setProperties(localProperties);
    }

}

