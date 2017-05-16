package cth.codetroopers.urbanwarfare.Views;

import android.graphics.Typeface;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import cth.codetroopers.urbanwarfare.Model.EventChannels.LoadingStates;
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

        Typeface myFont = Typeface.createFromAsset(getRootView().getContext().getAssets(),"fonts/SpecialElite.ttf");
        textView.setTypeface(myFont);
    }


    @Override
    public void setListener(LoadingViewListener listener) {
        this.mListener=listener;
    }

    private void setText(final int stringId){
        Handler handler = new Handler(Looper.getMainLooper());
        handler.post(new Runnable() {
            @Override
            public void run() {

                textView.setText(stringId);
            }
        });
    }

    @Override
    public View getRootView() {
        return rootView;
    }

    @Override
    public void updateLoadingStatus(LoadingStates state) {
        switch (state){
            case CONNECTING:
                setText(R.string.establish_connection);
                break;
            case SIGNINGIN:
                setText(R.string.signingin);
                break;
            case FETCHING:
                setText(R.string.fetch_information);
                break;
            case COMPLETE:
                mListener.onFinishedLoading();
        }
    }
}
