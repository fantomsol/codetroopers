package cth.codetroopers.urbanwarfare.Model.Skeletons;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by latiif on 5/17/17.
 */

public class GameException {
    public GameException(JSONObject object){

        try {
            title=object.getString("name");
            msg=object.getString("message");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private String title;
    private String msg;

    public String getTitle(){
        return title;
    }

    public String getMsg(){
        return msg;
    }
}
