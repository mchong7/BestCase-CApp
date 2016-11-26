package bestcase.capp;

/**
 * Created by Matt on 11/20/2016.
 */

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class SplashPage extends Splash{

    @Override
    protected void onCreate(Bundle savedInstanceState){
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Thread timerThread = new Thread(){
            public void run(){
                try{
                    sleep(3000);
                }catch(InterruptedException e){
                    e.printStackTrace();
                }finally{
                    Intent intent = new Intent(SplashPage.this,Splash.class);
                    startActivity(intent);
                }
            }
        };
        timerThread.start();
    }

    @Override
    protected void onPause(){
        // TODO Auto-generated method stub
        super.onPause();
        finish();
    }
}
