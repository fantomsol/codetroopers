package cth.codetroopers.urbanwarfare.Views;

import android.app.Application;
import android.content.res.Resources;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import cth.codetroopers.urbanwarfare.R;

/**
 * Created by latiif on 5/7/17.
 */

public class LoadingViewImp implements ILoadingView {

    private LoadingViewListener mListener;

    private View rootView;

    private TextView textView;

    public LoadingViewImp(LayoutInflater inflater, ViewGroup container){
        rootView = inflater.inflate(R.layout.activity_loading, container, false);

        initialize();

    }

    private void initialize(){
        textView=(TextView) rootView.findViewById(R.id.txtProcess);
    }


    @Override
    public void setListener(LoadingViewListener listener) {
        this.mListener=listener;
    }

    @Override
    public void onConnecting() {
        setText(R.string.establish_connection);
    }

    @Override
    public void onSigningIn() {
        setText(R.string.signingin);
    }

    @Override
    public void onFetchingData() {
        setText(R.string.fetch_information);
    }

    @Override
    public void onLoadingCompleted() {
        mListener.onFinishedLoading();
    }


    private void setText(final int stringId){
        Handler handler = new Handler(Looper.getMainLooper());
        handler.post(new Runnable() {
            @Override
            public void run() {

                textView.setText("Wörking");
            }
        });
    }

    @Override
    public View getRootView() {
        return rootView;
    }
}
