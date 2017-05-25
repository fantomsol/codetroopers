package cth.codetroopers.pixelwarfare.Views;

import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import cth.codetroopers.pixelwarfare.R;

public class LoginViewImp implements ILogInView {


    private View rootView;
    private Button btnLogin;
    private Button btnSignup;

    private EditText editText;
    private LoginViewListener mListener;

    public LoginViewImp(LayoutInflater inflater, ViewGroup container){
        rootView = inflater.inflate(R.layout.activity_log_in, container, false);

        initialize();

    }

    private void initialize(){
        btnLogin= (Button) rootView.findViewById(R.id.buttonLogin);
        btnSignup= (Button) rootView.findViewById(R.id.buttonSignup);

        editText = (EditText) rootView.findViewById(R.id.editTextId);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onRequestLogin(editText.getText().toString());
            }
        });

        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onRequestSignup(editText.getText().toString());
            }
        });


        Typeface myFont = Typeface.createFromAsset(rootView.getContext().getAssets(),"fonts/SpecialElite.ttf");


        btnLogin.setTypeface(myFont);
        btnSignup.setTypeface(myFont);

        editText.setTypeface(myFont);


    }


    @Override
    public void setListener(LoginViewListener listener) {
        mListener=listener;
    }

    @Override
    public View getRootView() {
        return rootView;
    }
}
