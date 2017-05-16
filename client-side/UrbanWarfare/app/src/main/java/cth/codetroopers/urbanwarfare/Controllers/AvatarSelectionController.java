package cth.codetroopers.urbanwarfare.Controllers;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import cth.codetroopers.urbanwarfare.Model.ClientModel;
import cth.codetroopers.urbanwarfare.Views.AvatarSelectionViewImp;
import cth.codetroopers.urbanwarfare.Views.IAvatarSelectionView;

/**
 * Created by latiif on 5/16/17.
 */

public class AvatarSelectionController extends AppCompatActivity implements IAvatarSelectionView.AvatarSelectionListener {


    private IAvatarSelectionView avatarSelectionView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ViewGroup view = (ViewGroup) findViewById(android.R.id.content);

        avatarSelectionView=new AvatarSelectionViewImp((LayoutInflater) getSystemService( LAYOUT_INFLATER_SERVICE ),view);


        avatarSelectionView.setListener(this);

        setContentView(avatarSelectionView.getRootView());
    }

    @Override
    public void onAvatarSelected(String avatar) {
        ClientModel.getInstance().changeAvatar(avatar);
    }
}
