package bestcase.capp;

import android.app.Application;

import com.flurry.android.FlurryAgent;

/**
 * Created by Matt on 4/24/2017.
 */

public class Flurry extends Application {
    @Override
    public void onCreate(){
        super.onCreate();
        new FlurryAgent.Builder()
                .withLogEnabled(true)
                .build(this, "JHN4HF94B59QJXGBYQZZ");
    }
}
