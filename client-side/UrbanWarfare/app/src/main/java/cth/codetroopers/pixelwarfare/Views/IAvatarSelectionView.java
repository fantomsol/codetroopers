package cth.codetroopers.pixelwarfare.Views;

/**
 * Created by latiif on 5/16/17.
 */

public interface IAvatarSelectionView extends IView {
    interface AvatarSelectionListener{
        void onAvatarSelected(String avatar);
    }

    void setListener(AvatarSelectionListener listener);
}
