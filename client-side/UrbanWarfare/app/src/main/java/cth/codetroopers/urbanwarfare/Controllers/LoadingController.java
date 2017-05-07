package cth.codetroopers.urbanwarfare.Controllers;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import cth.codetroopers.urbanwarfare.Model.ClientModel;
import cth.codetroopers.urbanwarfare.Views.ILoadingView;
import cth.codetroopers.urbanwarfare.Views.LoadingViewImp;
import cth.codetroopers.urbanwarfare.Views.LoginViewImp;

/**
 * Created by latiif on 5/7/17.
 */

public class LoadingController extends AppCompatActivity implements ILoadingView.LoadingViewListener{

    private ILoadingView loadingView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ViewGroup view = (ViewGroup) findViewById(android.R.id.content);
        loadingView= new LoadingViewImp((LayoutInflater) getSystemService( LAYOUT_INFLATER_SERVICE ),view);

        loadingView.setListener(this);

        ClientModel.subscribeLoadingView(loadingView);


        setContentView(loadingView.getRootView());

    }

    @Override
    protected void onStart() {
        super.onStart();
        ClientModel.commenceLogin();
    }

    @Override
    public void onFinishedLoading() {
        startActivity(new Intent(this,MainController.class));
        finish();
    }
}
