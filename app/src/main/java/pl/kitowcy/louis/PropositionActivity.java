package pl.kitowcy.louis;

import android.content.Context;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.daprlabs.aaron.swipedeck.SwipeDeck;
import com.daprlabs.aaron.swipedeck.layouts.SwipeFrameLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import pl.kitowcy.louis.proposition.PropItem;
import pl.kitowcy.louis.proposition.PropositionItemsProvider;
import pl.kitowcy.louis.proposition.SwipeDeckAdapter;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;
import xyz.hanks.library.SmallBang;

public class PropositionActivity extends AppCompatActivity {

    @BindView(R.id.swipeLayout) SwipeFrameLayout swipeFrameLayout;
    @BindView(R.id.swipe_deck) SwipeDeck cardStack;
    @BindView(R.id.button) ImageView btn;
    @BindView(R.id.button2) ImageView btn2;

    List<PropItem> propItemList;
    SwipeDeckAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_proposition);
        ButterKnife.bind(this);

        propItemList = PropositionItemsProvider.getItems();

        adapter = new SwipeDeckAdapter(propItemList, this);
        if (cardStack != null) {
            cardStack.setAdapter(adapter);
        }
        cardStack.setCallback(new SwipeDeck.SwipeDeckCallback() {
            @Override
            public void cardSwipedLeft(long stableId) {
                Log.i("MainActivity", "card was swiped left, position in adapter: " + stableId);

            }

            @Override
            public void cardSwipedRight(long stableId) {
                Log.i("MainActivity", "card was swiped right, position in adapter: " + stableId);
            }

        });

//        cardStack.setLeftImage(R.id.left_image);
//        cardStack.setRightImage(R.id.right_image);

        //example of buttons triggering events on the deck
        SmallBang smallBang = SmallBang.attach2Window(this);

        btn.setOnClickListener(v -> cardStack.swipeTopCardLeft(180));
        btn2.setOnClickListener(v -> {
            smallBang.bang(v);
            cardStack.swipeTopCardRight(180);
        });

//        btn3.setOnClickListener(v -> adapter.notifyDataSetChanged());
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
}
