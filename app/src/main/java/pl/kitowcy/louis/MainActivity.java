package pl.kitowcy.louis;

import android.Manifest;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.github.paolorotolo.appintro.AppIntro;
import com.github.paolorotolo.appintro.AppIntroFragment;

public class MainActivity extends AppIntro {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);

        addSlide(NameFragment.newInstance());
        addSlide(
                AppIntroFragment.newInstance("Hello, I'm Louis", "We need to know each other C:",
                        android.R.drawable.ic_media_play, ContextCompat.getColor(this, android.R.color.holo_orange_dark)));
        addSlide(
                AppIntroFragment.newInstance("tutaj", "jest ostatni moj slajdzik opis",
                        android.R.drawable.arrow_up_float, ContextCompat.getColor(this, android.R.color.holo_red_dark)));

    }
}