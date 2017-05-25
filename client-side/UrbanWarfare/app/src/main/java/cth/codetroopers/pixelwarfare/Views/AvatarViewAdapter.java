package cth.codetroopers.pixelwarfare.Views;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import cth.codetroopers.pixelwarfare.GameUtils.ResourceDirectory;
import cth.codetroopers.pixelwarfare.R;

/**
 * Created by latiif on 5/16/17.
 */

public class AvatarViewAdapter extends ArrayAdapter<String> {

    IAvatarSelectionView.AvatarSelectionListener mListener;

    public AvatarViewAdapter(@NonNull Context context, List<String> avatarList, IAvatarSelectionView.AvatarSelectionListener listener) {
        super(context, R.layout.armor_view, avatarList);
        mListener=listener;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        final String avatar = getItem(position);

        LayoutInflater inflater = LayoutInflater.from(getContext());
        View customView = inflater.inflate(R.layout.avatar_view, parent, false);

        TextView textView= (TextView) customView.findViewById(R.id.avatar_name);
        ImageView imageView = (ImageView) customView.findViewById(R.id.avatar_image);
        Button button = (Button) customView.findViewById(R.id.button_select_avatar);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.onAvatarSelected(avatar);
            }
        });

        textView.setText(avatar);
        imageView.setImageResource(ResourceDirectory.getAvatarImage(avatar));



        return customView;

    }
}