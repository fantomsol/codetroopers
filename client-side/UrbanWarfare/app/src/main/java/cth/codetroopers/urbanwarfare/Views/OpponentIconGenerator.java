package cth.codetroopers.urbanwarfare.Views;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.maps.android.ui.IconGenerator;

import cth.codetroopers.urbanwarfare.Model.ItemsDirectory;
import cth.codetroopers.urbanwarfare.Model.PlayerSkeleton;
import cth.codetroopers.urbanwarfare.R;

/**
 * Created by latiif on 4/14/17.
 *
 * This class servers as a some type of a factory.
 * We send in the JSON object of a player, and it returns a marker that can be placed on the map.
 * To do so, we use an external library that does have an IconGenerator class, and instead of passing it a simple image to be parsed as a maker, we pass it a view to be processed as a marker.
 *
 * To pass a view we first need to parse the xml file named opponent_info.xml to a View object.
 * To do so, we use something called LayoutInflater.
 *
 * For this class we use the Factory Pattern.
 */

/*
USEFUL READS:
*
* LayoutInflater:
* https://developer.android.com/reference/android/view/LayoutInflater.html
*
* A nice thread on StackOverflow: http://stackoverflow.com/questions/3477422/what-does-layoutinflater-in-android-do
*
*
*Google Maps Android API utility library:
* http://googlemaps.github.io/android-maps-utils/
*
* This library contains many useful classes to faciliate the manipulation of Google Maps in general and markers especially.
 * DO WATCH the example video on that page.
 */

public class OpponentIconGenerator {
    private static OpponentIconGenerator instance;

    private static Context context;
    private  IconGenerator iconGenerator;
    private static LayoutInflater inflater;

    private static View mapFragment;

    public static void setMapFragment(View view){
        mapFragment=view;
    }


    public static void setContext(final Context myContext){
        context=myContext;
        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    private OpponentIconGenerator(){
        iconGenerator= new IconGenerator(context);
        //Make background transparent
        iconGenerator.setBackground(null);
    }


    /**
     * We transform a player into a bitmap that can be placed on the map as a marker
     * @param player the JSON object containing the player's data.
     * @return A BitMapDescriptor object that can be placed on the map directly.
     * @throws Exception If the context has not been set yet.
     */
    public BitmapDescriptor generateFromPlayer(PlayerSkeleton player) throws Exception {
        if (context==null){
            throw new Exception("No context set");
        }

        //We inflate the xml file named opponent_info.xml into the map of MainActivity
        View view= inflater.inflate(R.layout.opponent_info, (ViewGroup) mapFragment,false);

        //Grab a handle of the elements in the xml file
        TextView txtName= (TextView)  view.findViewById(R.id.txtNameOpponent);
        TextView textView= (TextView) view.findViewById(R.id.txtHp);
        ImageView imgRank = (ImageView) view.findViewById(R.id.imgRank);
        ImageView imgWeapon = (ImageView) view.findViewById(R.id.imageWeaponequipped);
        ImageView imgAvatar = (ImageView) view.findViewById(R.id.image);

        //Fill in the elements with the corresponding information from the player JSON object
        txtName.setText(player.getID());
        textView.setText(String.valueOf(player.getHp()));

        imgRank.setImageResource(ItemsDirectory.getRank(player));
        imgWeapon.setImageResource(ItemsDirectory.getWeaponImage(player.getWeaponEquipped()));

        imgAvatar.setImageResource(ItemsDirectory.getAvatarImage(player.getAvatar()));
        //Prompt the iconGenerator to take the view as its content
        iconGenerator.setContentView(view);

        //Finally, we return the icon made by the icon generator as a BitMapDescriptor
        /*
        BitMap descriptor is the type that google maps uses to deal with bitmaps, they can be created by using the static method BitMapDescriptorFactory.fromBitmap(...)
         */
        return BitmapDescriptorFactory.fromBitmap(iconGenerator.makeIcon());

    }




    public static OpponentIconGenerator getInstance(){
        if (instance==null){
            instance = new OpponentIconGenerator();
        }

        return instance;
    }
}
