package cth.codetroopers.urbanwarfare.Views;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import cth.codetroopers.urbanwarfare.R;

/**
 * Created by latiif on 5/16/17.
 */

public class AvatarSelectionViewImp implements IAvatarSelectionView {

    GridView avatarGrid;
    private View rootView;
    List<String> avatars;
    private AvatarSelectionListener mListener;

    public AvatarSelectionViewImp(LayoutInflater inflater, ViewGroup container) {
        rootView = inflater.inflate(R.layout.avatar_selection_view, container, false);

        initialize();

    }


    private void initialize(){
        avatarGrid= (GridView) rootView.findViewById(R.id.avatar_grid);
        avatars= new ArrayList<>();
        avatars.add("JIM"); avatars.add("WILLOW"); avatars.add("LOTUS"); avatars.add("KARMA"); avatars.add("KYLE"); avatars.add("SIBOAN"); avatars.add("TERRY");


        ArrayAdapter listAdapter = new AvatarViewAdapter(rootView.getContext(), avatars, mListener);

        avatarGrid.setAdapter(listAdapter);
    }

    @Override
    public void setListener(AvatarSelectionListener listener) {
        mListener=listener;
    }


    @Override
    public View getRootView() {
        return rootView;
    }
}
