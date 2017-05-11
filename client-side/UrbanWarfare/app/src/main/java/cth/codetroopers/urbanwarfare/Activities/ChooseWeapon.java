package cth.codetroopers.urbanwarfare.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

import cth.codetroopers.urbanwarfare.ClientSide.ConnectivityLayer;
import cth.codetroopers.urbanwarfare.Model.ClientModel;
import cth.codetroopers.urbanwarfare.Model.WeaponSkeleton;
import cth.codetroopers.urbanwarfare.R;

public class ChooseWeapon extends AppCompatActivity {

    List<WeaponSkeleton> weapons= new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_weapon);


        weapons= ClientModel.getInstance().mPlayer.getWeapons();

        ListAdapter listAdapter = new WeaponViewAdapter(this,weapons);

        ListView listView = (ListView) findViewById(R.id.weaponList);

        listView.setAdapter(listAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                WeaponSkeleton weapon = (WeaponSkeleton) adapterView.getItemAtPosition(i);

                Intent data = new Intent();
                data.putExtra("weapon-id",weapon.getId());
                setResult(RESULT_OK,data);
                finish();

            }
        });

    }

}
