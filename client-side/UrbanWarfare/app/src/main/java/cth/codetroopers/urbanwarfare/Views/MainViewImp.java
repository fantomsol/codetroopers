package cth.codetroopers.urbanwarfare.Views;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;

import cth.codetroopers.urbanwarfare.Activities.ChooseWeapon;
import cth.codetroopers.urbanwarfare.ClientSide.ClientController;
import cth.codetroopers.urbanwarfare.Model.PlayerSkeleton;
import cth.codetroopers.urbanwarfare.R;

/**
 * Created by latiif on 5/6/17.
 */

public class MainViewImp implements IMainView{

    private View rootView;
    private PanelControlInteraction mListener;

    public  View mapFragment;
    private FloatingActionButton fab;
    private TextView txtName;
    private ProgressBar progressHp;
    private ImageButton radarButton;



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
                mListener.onChangeWeapon();
            }
        });

    }

    private void initialize(){
        txtName = (TextView) rootView.findViewById(R.id.txtName);
        progressHp = (ProgressBar) rootView.findViewById(R.id.progressHp);
        radarButton= (ImageButton) rootView.findViewById(R.id.radarButton);
       fab = (FloatingActionButton) rootView.findViewById(R.id.fab);
        mapFragment = rootView.findViewById(R.id.map);

    }

    @Override
    public View getRootView() {
        return rootView;
    }

    @Override
    public void updateGUI(PlayerSkeleton player) {
        txtName.setText(player.getID());
        progressHp.setProgress(player.getHp().intValue());


        if (player.isOnline()){
            radarButton.setImageResource(R.drawable.visible);
        }
        else {
            radarButton.setImageResource(R.drawable.invisible);
        }
    }
}
