package cth.codetroopers.urbanwarfare.Views;

import cth.codetroopers.urbanwarfare.Model.ILoadUpdateListener;

/**
 * Created by latiif on 5/7/17.
 */

public interface ILoadingView  extends IView, ILoadUpdateListener {
    interface LoadingViewListener{
        void onFinishedLoading();
    }

    void setListener(LoadingViewListener listener);
}
