package bestcase.capp;

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

public class MenuInstructions extends AppCompatActivity {
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
                Intent about = new Intent(MenuInstructions.this, About.class);
                startActivity(about);
                break;
            case R.id.menu_instructions:
                Intent instructions = new Intent(MenuInstructions.this, MenuInstructions.class);
                startActivity(instructions);
                break;
            case R.id.menu_settings:
                Intent settings = new Intent(MenuInstructions.this, Settings.class);
                startActivity(settings);
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_instructions);
        // enable the back button in the app bar
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        TextView menu_instruct = (TextView) findViewById(R.id.menu_instruct);
        menu_instruct.setMovementMethod(new ScrollingMovementMethod());
        Spanned instructs = Html.fromHtml("The goal of this application is to simplify the computing process of solving constant acceleration problems." +
                "In solving constant acceleration problems, there are five equations that are used. The terms are explained later." +
                "<br><br>v = v\u2080 + at<br>" +
                "\u0394x = v\u2080t + \u00B9/\u2082at\u00B2<br>" +
                "v\u00B2 = v\u2080\u00B2 + 2a\u0394x<br>" +
                "\u0394x = vt - \u00B9/\u2082at\u00B2<br>" +
                "\u0394x = \u00B9/\u2082(v + v\u2080)t<br><br>" +
                "You will see these equations being used and manipulated in the explanation of answers when you solve problems.<br><br>" +
                "Now, let's explain what each of the terms mean.<br><br>" +
                "<em><b>Initial Velocity (v\u2080)</em></b><br><br>" +
                "Velocity describes the speed of an object. In this case, the speed is determined in meters per second. " +
                "This means that every second, our object is moving a certain number of meters forward or backward. If our velocity is positive, our object is moving forward, " +
                "and if our velocity is negative, that means our object is moving backward.<br><br>"
                + "Initial velocity is a speed too. What does 'initial' mean? In this app, you're determining data from two points in time - from when you start recording data to when you stop recording data. " +
                "Initial velocity describes the speed of the object when you first started recording data. In other words, the moment you look at an object, whether it's moving or not, and make a note of its speed, " +
                "you've just recorded the initial velocity of the object.<br><br>"
                + "<em><b>Final Velocity (v)</em></b><br><br>" +
                "Velocity describes the speed of an object. In this case, the speed is determined in meters per second. " +
                "This means that every second, our object is moving a certain number of meters forward or backward. If our velocity is positive, our object is moving forward, " +
                "and if our velocity is negative, that means our object is moving backward.<br><br>"
                + "Final velocity, or just velocity, is a speed too. What does 'final' mean? In this app, you're determining data from two points in time - from when you start recording data to when you stop recording data. " +
                "Final velocity describes the speed of the object when you stopped recording data. In other words, the moment you stopped looking at an object, whether it's moving or not, and made a note of its speed, " +
                "you've just recorded the final velocity of the object.<br><br>"
                + "<em><b>Acceleration (a)</em></b><br><br>" +
                "Acceleration describes the speed at which an object's speed increases or decreases. Acceleration is generally measured in meters per second squared. " +
                "In other words, acceleration illustrates a change in velocity over a period of time. <br><br>" +
                "To simplify even further, imagine an object moving at 5 meters per second, without stopping. It travels on forever at 5 meters per second." +
                "The acceleration is zero, since the object is neither decreasing nor increasing velocity. What if that object starts moving faster?" +
                "If the object increases its speed to 10 meters per second in 5 seconds (from 5 meters per second), its acceleration would be 1 meter per second squared." +
                "In simpler terms, every second, the object moves 1 meter per second faster than it did before. " +
                "<br><br>The same is true for a decrease in speed. If the object has -1 meter per second acceleration, it will move 1 meter slower every second until it finally stops." +
                "If the deceleration continues, the object will eventually move backwards." +
                "<br><br>This simple example illustrates how acceleration is used and how useful it can be in representing the motion of an object.<br><br>"
                + "<em><b>Time (t)</em></b><br><br>" +
                "Time is relatively self-explanatory. In this app, time is generally measured in seconds, but depending on your problem, it can be minutes, hours, years, or anything else." +
                "<br><br>Measuring elapsed time is useful because events occur in a period of time. In order to measure these events, the time itself must also be measured." +
                "<br><br>For instance, if an object is moving at 5 meters per second with an acceleration of 1 meter per second, it is impossible to know how far the object travelled" +
                "without knowing how much time the object travelled for. It can be very difficult to gather data without measuring time, and it is advised to take caution" +
                "when solving for problems that have zero elapsed time.<br><br>"
                + "<em><b>Displacement (\u0394x)</em></b><br><br>" +
                "Displacement is a way of measuring the movement of an object over a period of time. (If no time has elapsed, an object cannot move.) " +
                "Displacement is generally measured in meters and says how far an object moved, or whether it did not move at all. " +
                "If an object is moving 5 meters per second and 1 second has passed, the object moved 5 meters forward - the displacement is equal to 5 meters." +
                "This term is relatively simple.<br><br>" +
                "If you have any concerns, give the app a try - and if you still don't get it, re-read these instructions. If you really don't understand, you can always send your questions" +
                "to constant.acceleration.application@gmail.com, and we will try to help you out. Good luck and thanks for using our app!");

        menu_instruct.setText(instructs);
    }
}
