package bestcase.capp;

/**
 * Created by dolen on 4/18/2017.
 */

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.text.Spanned;
import android.text.method.ScrollingMovementMethod;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class About extends AppCompatActivity {
    // creates the menu in the action bar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }
    // enables page navigation in the menu
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.menu_about:
                Intent about = new Intent(About.this, About.class);
                startActivity(about);
                break;
            case R.id.menu_instructions:
                Intent instructions = new Intent(About.this, MenuInstructions.class);
                startActivity(instructions);
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        TextView about = (TextView) findViewById(R.id.about);
        about.setText("CApp is based on an algorithm developed by Philippe Binder (University of Hawaii at Hilo) and Spencer Wheaton (University of Cape Town, South Africa), " +
                "published in the March, 2017 issue of Physics Education (an IOP Journal). " + System.lineSeparator() + System.lineSeparator() +
                "Software developers: " + System.lineSeparator() + System.lineSeparator() +
                "Jahnu Best" + System.lineSeparator() + System.lineSeparator() +
                "Dolen Case" + System.lineSeparator() + System.lineSeparator() +
                "Matthew Chong" + System.lineSeparator() + System.lineSeparator() +
                "Kayla Smallwood " + System.lineSeparator() + System.lineSeparator() +
                        "Supervised by Harry K. Edwards (University of Hawaii at Hilo).");
        // enable the back button in the app bar
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }
}
