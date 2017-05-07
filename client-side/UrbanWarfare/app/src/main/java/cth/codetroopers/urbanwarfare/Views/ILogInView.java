package cth.codetroopers.urbanwarfare.Views;

/**
 * Created by latiif on 5/7/17.
 */

public interface ILogInView extends IView{

    interface LoginViewListener{
        void onRequestLogin(String id);
    }

    void setListener(LoginViewListener listener);
}
