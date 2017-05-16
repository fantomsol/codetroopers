package cth.codetroopers.urbanwarfare.Views;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Handler;
import android.os.Looper;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.android.gms.maps.model.LatLng;

import java.util.List;

import cth.codetroopers.urbanwarfare.Model.ItemsDirectory;
import cth.codetroopers.urbanwarfare.Model.PlayerSkeleton;
import cth.codetroopers.urbanwarfare.R;

/**
 * Created by latiif on 5/6/17.
 */

public class MainViewImp implements IMainView{

    private View rootView;
    private PanelControlInteractionListener mListener;
    private MapListener mMapListener;

    private   View mapFragment;

    private IMapHandler mapHandler;
    private FloatingActionButton fab;
    private TextView txtName,txtHp,txtExp;
    private ProgressBar progressHp,progressExp;
    private ImageButton radarButton;
    private ImageView shopImg;
    private ImageView rankImage;

    private TextView txtGold;

    private FragmentActivity mContext;





    public MainViewImp(LayoutInflater inflater, ViewGroup container) {
        rootView = inflater.inflate(R.layout.main_actvitiy, container, false);

        initialize();

        radarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mListener != null) {
                    mListener.onRequestRadarStatusChange();
                }
            }
        });

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mListener!=null){
                    mListener.onChangeWeapon();
                }
            }
        });


        shopImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mListener!=null){
                    mListener.onStartShop();
                }
            }
        });

    }

    private void initialize(){
        Typeface myFont = Typeface.createFromAsset(getRootView().getContext().getAssets(),"fonts/SpecialElite.ttf");


        txtName = (TextView) rootView.findViewById(R.id.txtName);
        txtExp = (TextView) rootView.findViewById(R.id.txtExp);
        txtHp = (TextView) rootView.findViewById(R.id.txtHp);

        txtName.setTypeface(myFont);
        txtExp.setTypeface(myFont);
        txtHp.setTypeface(myFont);


        progressHp = (ProgressBar) rootView.findViewById(R.id.progressHp);

        progressExp = (ProgressBar) rootView.findViewById(R.id.progressExp);
        progressExp.setMax(2500);
        progressExp.getProgressDrawable().setColorFilter(
                Color.BLUE, android.graphics.PorterDuff.Mode.SRC_IN);


        radarButton= (ImageButton) rootView.findViewById(R.id.radarButton);

       fab = (FloatingActionButton) rootView.findViewById(R.id.fab);
        mapFragment = rootView.findViewById(R.id.map);

        txtGold= (TextView)rootView.findViewById(R.id.txtGold);
        txtGold.setTypeface(myFont);

        rankImage= (ImageView) rootView.findViewById(R.id.playerRank);
        shopImg=(ImageView) rootView.findViewById(R.id.imageShop);

    }

    @Override
    public View getRootView() {
        return rootView;
    }

    @Override
    public void updateGUI(final PlayerSkeleton player) {

        Handler handler = new Handler(Looper.getMainLooper());
        handler.post(new Runnable() {
            @Override
            public void run() {

                txtName.setText(player.getID());
                progressHp.setProgress(player.getHp().intValue());
                txtHp.setText(player.getHp().intValue()+"%");

                progressExp.setProgress(player.getExp()%2500);
                txtExp.setText(player.getExp()%2500+"\\2500");

                txtGold.setText(String.valueOf(player.getGold()));

                int hp=player.getHp().intValue();
                if (hp<33) {
                    progressHp.getProgressDrawable().setColorFilter(
                            Color.RED, android.graphics.PorterDuff.Mode.SRC_IN);
                }
                else if (hp<66){
                    progressHp.getProgressDrawable().setColorFilter(
                            Color.YELLOW, android.graphics.PorterDuff.Mode.SRC_IN);
                }
                else {
                    progressHp.getProgressDrawable().setColorFilter(
                            Color.GREEN, android.graphics.PorterDuff.Mode.SRC_IN);
                }





                fab.setImageResource(ItemsDirectory.getWeaponImage(player.getWeaponEquipped()));
                mapHandler.pinPlayer(player);
                rankImage.setImageResource(ItemsDirectory.getRank(player));

                if (player.isOnline()) {
                    radarButton.setImageResource(R.drawable.visible);
                } else {
                    radarButton.setImageResource(R.drawable.invisible);
                }
            }
        });
    }

    @Override
    public void updateOpponents(final List<PlayerSkeleton> playersNearby) {

        Handler handler = new Handler(Looper.getMainLooper());
        handler.post(new Runnable() {
            @Override
            public void run() {
                mapHandler.pinOpponents(playersNearby);
            }
        });
    }

    @Override
    public void setListener(PanelControlInteractionListener listener) {
        mListener=listener;
    }

    @Override
    public void setMapListener(MapListener listener) {
        mMapListener=listener;
        mapHandler.setMapListener(listener);
    }

    @Override
    public void updateLootboxes(List<LatLng> boxes) {
        mapHandler.pinLootboxes(boxes);
    }


    @Override
    public void setContext(FragmentActivity context) {
        mContext=context;
        mapHandler=new GoogleMapHandler(context,mMapListener);
        mapHandler.setMapFragment(mapFragment);
    }
}
