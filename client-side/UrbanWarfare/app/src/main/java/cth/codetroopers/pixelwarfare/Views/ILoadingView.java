package cth.codetroopers.pixelwarfare.Views;

import cth.codetroopers.pixelwarfare.Model.EventChannels.ILoadUpdateListener;

/**
 * Created by latiif on 5/7/17.
 */

public interface ILoadingView  extends IView, ILoadUpdateListener {
    interface LoadingViewListener{
        void onFinishedLoading();
    }

    void setListener(LoadingViewListener listener);
}
