package pl.kitowcy.louis;

import android.app.Application;

import com.karumi.dexter.Dexter;

import pl.kitowcy.louis.facedetection.api.EmotionRestClient;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;


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
        EmotionRestClient.init(this, getString(R.string.microsoft_emotion_key));
        setFont();
    }

    private void setFont() {
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/rounded_elegance.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        );
    }
}
