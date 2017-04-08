package bestcase.capp;

/*
 * Created by Matt on 11/20/2016.
 */

// import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;

import bestcase.capp.CApp.WelcomeActivity;

public class SplashPage extends DataInput{
    SharedPreferences mPrefs;
    final String welcomeScreenShownPref = "welcomeScreenShown";

    /** Called when the activity is first created. */

    @Override
    protected void onCreate(Bundle savedInstanceState){
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);

        setContentView(R.layout.splash);

        Thread timerThread = new Thread(){
            public void run() {
                try {
                    sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    Intent intent = new Intent(SplashPage.this, bestcase.capp.WelcomeActivity.class);
                    startActivity(intent);
                }
            }
        };
        timerThread.start();

    /**

        mPrefs = PreferenceManager.getDefaultSharedPreferences(this);

        // second argument is the default to use if the preference can't be found
        Boolean welcomeScreenShown = mPrefs.getBoolean(welcomeScreenShownPref, false);

        if (!welcomeScreenShown) {
            // here you can launch another activity if you like
            // the code below will display a popup

            String whatsNewTitle = getResources().getString(R.string.);
            String whatsNewText = getResources().getString(R.string.whatsNewText);
            new AlertDialog.Builder(this).setIcon(android.R.drawable.ic_dialog_alert).setTitle(whatsNewTitle).setMessage(whatsNewText).setPositiveButton(
                    R.string.ok, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    }).show();
            SharedPreferences.Editor editor = mPrefs.edit();
            editor.putBoolean(welcomeScreenShownPref, true);
            editor.commit(); // Very important to save the preference
        }
*/
    }

    @Override
    protected void onPause(){
        // TODO Auto-generated method stub
        super.onPause();
        finish();
    }
}

