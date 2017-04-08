package bestcase.capp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.text.Spanned;
import android.text.method.ScrollingMovementMethod;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class instructions extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instructions);
        TextView instruct = (TextView) findViewById(R.id.instruct);
        instruct.setMovementMethod(new ScrollingMovementMethod());
        Spanned instructs = Html.fromHtml("The goal of this application is to simplify the computing process of solving constant acceleration problems." +
                "In solving constant acceleration problems, there are five equations that are used. The terms are explained later." +
                "<br><br>v = v\u2080 + at<br>" +
                "\u0394x = v\u2080t + \u00B9/\u2082at\u00B2<br>" +
                "v\u00B2 = v\u2080\u00B2 + 2a\u0394x<br>" +
                "\u0394x = vt - \u00B9/\u2082at\u00B2<br>" +
                "\u0394x = \u00B9/\u2082(v + v\u2080)t<br><br>" +
                "You will see these equations being used and manipulated in the explanation of answers when you solve problems.<br><br>" +
                "Now, let's explain what each of the terms mean.<br><br><em><b>Initial Velocity (v\u2080)</em></b><br><br>" +
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
                + "Final velocity, or just velocity, velocity is a speed too. What does 'final' mean? In this app, you're determining data from two points in time - from when you start recording data to when you stop recording data. " +
                "Final velocity describes the speed of the object when you stopped recording data. In other words, the moment you stopped looking at an object, whether it's moving or not, and made a note of its speed, " +
                "you've just recorded the final velocity of the object.<br><br>"
        + "<em><b>Acceleration (a)</em></b><br><br>" +
                "[to be filled in]<br><br>"
                + "<em><b>Time (t)</em></b><br><br>" +
                "[to be filled in]<br><br>"
                + "<em><b>Displacement (\u0394x)</em></b><br><br>" +
                "[to be filled in]<br><br>");

        instruct.setText(instructs);
        Button go = (Button) findViewById(R.id.doit);
        go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(instructions.this, DataInput.class);
                startActivity(intent);
            }
        });
    }
}