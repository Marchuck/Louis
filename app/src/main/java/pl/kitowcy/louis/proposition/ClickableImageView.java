package pl.kitowcy.louis.proposition;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.BounceInterpolator;
import android.widget.ImageView;

/**
 * Project "Louis"
 * <p>
 * Created by Lukasz Marczak
 * on 29.10.16.
 */
public class ClickableImageView extends ImageView implements View.OnClickListener {

    boolean isClicked;

    public ClickableImageView(Context context) {
        super(context);
        init();
    }

    public ClickableImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ClickableImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public ClickableImageView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    void init() {
        setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        isClicked = !isClicked;

        if (isClicked) {
            animate().scaleX(1.1f)
                    .scaleY(1.1f)
                    .setDuration(300)
                    .setInterpolator(new BounceInterpolator())
                    .start();
        } else {
            animate().scaleX(0.8f)
                    .scaleY(0.8f)
                    .setDuration(300)
                    .setInterpolator(new BounceInterpolator())
                    .start();

        }
    }
}
