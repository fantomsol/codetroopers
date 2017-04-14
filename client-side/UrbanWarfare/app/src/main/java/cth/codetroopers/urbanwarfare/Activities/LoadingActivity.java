package cth.codetroopers.urbanwarfare.Activities;

import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.net.URISyntaxException;

import cth.codetroopers.urbanwarfare.ClientSide.ClientController;
import cth.codetroopers.urbanwarfare.LoadingActivityInterface;
import cth.codetroopers.urbanwarfare.R;

public class LoadingActivity extends AppCompatActivity implements LoadingActivityInterface {

    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);
        ClientController.subscribeLoadingActivity(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        addListeners();

    }





    private void setText(final int stringId){
        Handler handler = new Handler(Looper.getMainLooper());
        handler.post(new Runnable() {
            @Override
            public void run() {

                textView.setText(getResources().getString(stringId));
            }
        });
    }

    private void addListeners(){
        textView= (TextView) findViewById(R.id.txtProcess);


        textView.setText(R.string.establish_connection);
        try {
            ClientController.Init();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onConnected() {

        setText(R.string.signingin);
        ClientController.signIn(ClientController.playerID);

    }

    @Override
    public void onSignedin() {

        setText(R.string.fetch_information);
        ClientController.requestPlayerInformation(ClientController.playerID);

    }

    @Override
    public void onDataFetched() {
        Intent intent= new Intent(this,MainActivity.class);
        startActivity(intent);
        finish();

    }
}
