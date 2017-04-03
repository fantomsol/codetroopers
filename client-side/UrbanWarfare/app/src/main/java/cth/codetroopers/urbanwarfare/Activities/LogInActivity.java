package cth.codetroopers.urbanwarfare.Activities;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.net.URISyntaxException;

import cth.codetroopers.urbanwarfare.ClientSide.ClientController;
import cth.codetroopers.urbanwarfare.R;

public class LogInActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        addListeners();
    }


    private void addListeners(){
        final Button btnLogIn= (Button) findViewById(R.id.buttonLogin);
        final EditText editTextId = (EditText) findViewById(R.id.editTextId);

        btnLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClientController.playerID=new String(editTextId.getText().toString());
                launchActivity();
            }
        });

    }

    private void launchActivity(){

        try {
            ClientController.Init();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        startActivity(new Intent(this,MainActivity.class));
        finish();
    }
}
