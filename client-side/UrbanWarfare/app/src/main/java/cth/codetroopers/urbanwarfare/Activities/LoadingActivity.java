package cth.codetroopers.urbanwarfare.Activities;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.net.URISyntaxException;

import cth.codetroopers.urbanwarfare.ClientSide.ClientController;
import cth.codetroopers.urbanwarfare.R;

public class LoadingActivity extends AppCompatActivity {

    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        addListeners();

    }





    private void addListeners(){
        textView= (TextView) findViewById(R.id.txtProcess);


        textView.setText(R.string.establish_connection);
        try {
            ClientController.Init();
            textView.setText(R.string.signingin);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        Intent intent= new Intent(this,MainActivity.class);
        textView.setText(R.string.fetch_information);
        ClientController.requestPlayerInformation(ClientController.playerID);

        startActivity(intent);
        finish();


    }
}
