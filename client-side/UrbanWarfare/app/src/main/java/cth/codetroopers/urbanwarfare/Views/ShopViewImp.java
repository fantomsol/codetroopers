package cth.codetroopers.urbanwarfare.Views;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import cth.codetroopers.urbanwarfare.Model.Skeletons.PlayerSkeleton;
import cth.codetroopers.urbanwarfare.R;

/**
 * Created by latiif on 5/10/17.
 */

public class ShopViewImp implements IShopView {

    private View rootView;
    private Context context;

    private ListView listView;
    private ShopViewListener mListener;

    private List<Object> shopItems;

    private TextView txtGold;
    private TextView textShop;


    public ShopViewImp(LayoutInflater inflater, ViewGroup container) {
        rootView = inflater.inflate(R.layout.shop_view, container, false);

        initialize();

    }

    private void initialize() {
        txtGold=(TextView) rootView.findViewById(R.id.textGold);
        listView = (ListView) rootView.findViewById(R.id.shopitemslist);


        Typeface myFont = Typeface.createFromAsset(getRootView().getContext().getAssets(),"fonts/SpecialElite.ttf");

        txtGold.setTypeface(myFont);

        Typeface myTitleFont = Typeface.createFromAsset(getRootView().getContext().getAssets(),"fonts/PixelCombat.otf");

        textShop=(TextView) rootView.findViewById(R.id.textShop);
        textShop.setTypeface(myTitleFont);


    }


    @Override
    public View getRootView() {
        return rootView;
    }

    @Override
    public void updateItemsList(final List<Object> shopItems) {
        this.shopItems=shopItems;

        Handler handler = new Handler(Looper.getMainLooper());
        handler.post(new Runnable() {
            @Override
            public void run() {

                ListAdapter listAdapter = new ShopItemsAdapter(context, shopItems, mListener);

                listView.setAdapter(listAdapter);
            }
        });

    }

    @Override
    public void updateGUI(final PlayerSkeleton player) {
        Handler handler = new Handler(Looper.getMainLooper());
        handler.post(new Runnable() {
            @Override
            public void run() {
                txtGold.setText(String.valueOf(player.getGold()));
            }
        });
    }

    @Override
    public void setContext(Context context) {
        this.context=context;
    }

    @Override
    public void setListener(ShopViewListener listener) {
        mListener=listener;
    }
}
