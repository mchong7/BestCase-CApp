package bestcase.capp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.text.Spanned;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

public class WelcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        final SharedPreferences pref = getSharedPreferences("LOAD", Context.MODE_PRIVATE);
        if (pref.getBoolean("activity_executed", false)) {
            Intent intent = new Intent(WelcomeActivity.this, DataInput.class);
            startActivity(intent);
            finish();
        }

            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_welcome);
            Button go = (Button) findViewById(R.id.getstarted);
            Button instruct_button = (Button) findViewById(R.id.instructions);
            TextView welcome = (TextView) findViewById(R.id.dothis);
            final CheckBox show = (CheckBox) findViewById(R.id.show_again);
            welcome.setMovementMethod(new ScrollingMovementMethod());
            Spanned text = Html.fromHtml("Welcome to CApp - an App that solves constant-acceleration physics problems. Pick any three: "
                    + "initial velocity, final velocity, acceleration, time elapsed and displacement, and CApp will calculate " +
        "the other two, or let you know if there are no solutions or multiple solutions.<br><br>" +
                " The app is free, " +
                    "but we ask for the right to collect statistics on the use of the different cases we consider.");
            welcome.setText(text);
            if (go != null) {
                go.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        if (show.isChecked()) {
                            SharedPreferences.Editor editor = pref.edit();
                            editor.putBoolean("activity_executed", true);
                            editor.commit();
                        }
                         
                        Intent intent = new Intent(WelcomeActivity.this, DataInput.class);
                        startActivity(intent);
                    }
                });
            }
            if (instruct_button != null) {
                instruct_button.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        Intent intent = new Intent(WelcomeActivity.this, Instructions.class);
                        startActivity(intent);
                    }
                });
            }
        }
    }

