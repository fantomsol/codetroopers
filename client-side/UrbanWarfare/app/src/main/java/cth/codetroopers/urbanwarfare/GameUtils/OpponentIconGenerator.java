package cth.codetroopers.urbanwarfare.GameUtils;

import android.app.Application;
import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.maps.android.ui.IconGenerator;

import org.json.JSONObject;

import java.util.zip.Inflater;

import cth.codetroopers.urbanwarfare.Activities.MainActivity;
import cth.codetroopers.urbanwarfare.R;

/**
 * Created by latiif on 4/14/17.
 */

public class OpponentIconGenerator {
    private static OpponentIconGenerator instance;

    private static Context context;
    private  IconGenerator iconGenerator;
    private static LayoutInflater inflater;



    public static void setContext(final Context myContext){
        context=myContext;
        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    private OpponentIconGenerator(){
        iconGenerator= new IconGenerator(context);
        //Make background trasparent
        iconGenerator.setBackground(null);
    }



    public BitmapDescriptor generateFromPlayer(JSONObject player) throws Exception {
        if (context==null){
            throw new Exception("No context set");
        }

        View view= inflater.inflate(R.layout.opponent_info, (ViewGroup) MainActivity.mapFragment,false);

        TextView txtName= (TextView)  view.findViewById(R.id.txtNameOpponent);
        ProgressBar prgHp= (ProgressBar) view.findViewById(R.id.prgHp);

        txtName.setText(player.getString("id"));
        prgHp.setProgress(player.getInt("hp"));

        iconGenerator.setContentView(view);

        return BitmapDescriptorFactory.fromBitmap(iconGenerator.makeIcon());

    }




    public static OpponentIconGenerator getInstance(){
        if (instance==null){
            instance = new OpponentIconGenerator();
        }

        return instance;
    }
}
