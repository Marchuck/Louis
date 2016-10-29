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

    private List<PropItem> data;
    private Activity activity;

    public SwipeDeckAdapter(List<PropItem> data, Activity activity) {
        this.data = data;
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
        TextView title = (TextView) v.findViewById(R.id.title);
        TextView description = (TextView) v.findViewById(R.id.description);
        TextView adddress = (TextView) v.findViewById(R.id.address);
        TextView hour = (TextView) v.findViewById(R.id.hour);

        Picasso.with(activity.getBaseContext()).load(data.get(position).getImage()).fit().centerCrop().into(imageView);
        title.setText(data.get(position).getTitle());
        description.setText(data.get(position).getText());
        adddress.setText(data.get(position).getLocation());
        hour.setText(data.get(position).getHours());
//        String item = (String) getItem(position);

        v.setOnClickListener(v1 -> {
            Log.i("Layer type: ", Integer.toString(v1.getLayerType()));
            Log.i("Hardware Accel type:", Integer.toString(View.LAYER_TYPE_HARDWARE));
        });
        return v;
    }
}
