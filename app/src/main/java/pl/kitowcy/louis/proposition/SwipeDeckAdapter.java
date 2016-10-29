package pl.kitowcy.louis.proposition;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import pl.kitowcy.louis.R;

/**
 * Created by Patryk Mieczkowski on 29.10.2016
 */

public class SwipeDeckAdapter extends BaseAdapter {

    private List<String> data;
    private Context context;
    private Activity activity;

    public SwipeDeckAdapter(List<String> data, Context context, Activity activity) {
        this.data = data;
        this.context = context;
        this.activity = activity;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        View v = convertView;
        if (v == null) {
            LayoutInflater inflater = activity.getLayoutInflater();
            // normally use a viewholder
            v = inflater.inflate(R.layout.proposition_card_view, parent, false);
        }

        ImageView imageView = (ImageView) v.findViewById(R.id.offer_image);
        Picasso.with(context).load(R.drawable.beksinski).fit().centerCrop().into(imageView);
        TextView textView = (TextView) v.findViewById(R.id.sample_text);
        String item = (String) getItem(position);
        textView.setText(item);

        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("Layer type: ", Integer.toString(v.getLayerType()));
                Log.i("Hardware Accel type:", Integer.toString(View.LAYER_TYPE_HARDWARE));
            }
        });
        return v;
    }
}
