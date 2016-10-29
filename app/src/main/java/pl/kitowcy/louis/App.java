package pl.kitowcy.louis;

import android.app.Application;
import android.content.Context;
import android.support.v4.app.Fragment;

import com.karumi.dexter.Dexter;

import pl.kitowcy.louis.facedetection.api.EmotionRestClient;
import pl.kitowcy.louis.facedetection.api.models.Scores;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;


/**
 * Project "Louis"
 * <p>
 * Created by Lukasz Marczak
 * on 28.10.16.
 */
public class App extends Application {
    public static int currentPage = 0;
    public static App getApp(Context c){
        return (App)c.getApplicationContext();
    }
    public static App getApp(Fragment c){
        return (App)c.getActivity().getApplicationContext();
    }
    public Scores scores;
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
