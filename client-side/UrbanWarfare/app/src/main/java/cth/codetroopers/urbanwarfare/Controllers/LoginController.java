package cth.codetroopers.urbanwarfare.Controllers;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import cth.codetroopers.urbanwarfare.Model.ClientModel;
import cth.codetroopers.urbanwarfare.Views.ILogInView;
import cth.codetroopers.urbanwarfare.Views.LoginViewImp;

/**
 * Created by latiif on 5/7/17.
 */

public class LoginController extends AppCompatActivity implements ILogInView.LoginViewListener{
    private ILogInView logInView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ViewGroup view = (ViewGroup) findViewById(android.R.id.content);
        logInView= new LoginViewImp((LayoutInflater) getSystemService( LAYOUT_INFLATER_SERVICE ),view);

        logInView.setListener(this);

        setContentView(logInView.getRootView());

    }

    @Override
    public void onRequestLogin(String id) {

        ClientModel.getInstance().setPlayerId(id);
        finish();
        startActivity(new Intent(this,LoadingController.class));
    }

    @Override
    public void onRequestSignup(String id) {
        ClientModel.getInstance().setPlayerId(id);
        ClientModel.getInstance().signIn=false;
        finish();
        startActivity(new Intent(this,MainController.class));
        startActivity(new Intent(this,AvatarSelectionController.class));
    }
}
