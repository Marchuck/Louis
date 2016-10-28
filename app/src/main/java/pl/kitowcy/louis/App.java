package pl.kitowcy.louis;

import android.app.Application;

import com.karumi.dexter.Dexter;
import com.pixelcan.emotionanalysisapi.EmotionRestClient;

/**
 * Project "Louis"
 * <p/>
 * Created by Lukasz Marczak
 * on 28.10.16.
 */
public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Dexter.initialize(this);
        EmotionRestClient.init(this, "key");
    }
}
