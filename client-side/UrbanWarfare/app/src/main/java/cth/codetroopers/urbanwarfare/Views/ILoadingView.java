package cth.codetroopers.urbanwarfare.Views;

/**
 * Created by latiif on 5/7/17.
 */

public interface ILoadingView  extends IView{
    interface LoadingViewListener{
        void onFinishedLoading();
    }

    void setListener(LoadingViewListener listener);

    void onConnecting();
    void onSigningIn();
    void onFetchingData();
    void onLoadingCompleted();
}
