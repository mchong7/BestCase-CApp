//Use ^[\r?$\r?\n] in the search and replace bar to get rid of whitespace lines
package bestcase.capp;
import android.app.Activity;
import android.app.Dialog;
import android.graphics.Color;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import java.awt.font.TextAttribute;
import java.lang.String;
import java.lang.Double;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.AttributedString;
import static java.lang.Math.*;
// import java.io.*;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
// import android.app.Dialog;
import android.app.AlertDialog;
// import java.lang.Object;
import android.text.TextUtils;
// import android.content.Context;
import android.content.*;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import com.flurry.android.FlurryAgent;
import android.content.Intent;
public class DataInput extends AppCompatActivity {
    private boolean isEmpty(EditText etText) {
        return etText.getText().toString().trim().length() == 0;
    }
    EditText text_initial_vel;
    EditText text_final_vel;
    EditText text_accel;
    EditText text_time;
    EditText text_disp;
    public final static String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";
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
                Intent about = new Intent(DataInput.this, About.class);
                startActivity(about);
                break;
            case R.id.menu_instructions:
                Intent instructions = new Intent(DataInput.this, MenuInstructions.class);
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
        setContentView(R.layout.activity_data_input);
        Button solve = (Button) findViewById(R.id.solve_button);
        Button clear = (Button) findViewById(R.id.clear_button);
        ImageButton help = (ImageButton) findViewById(R.id.imageButton);
        if (help != null)
            help.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    AlertDialog about = new AlertDialog.Builder(DataInput.this).create();
                    about.setTitle("How to use the Data Input Page");
                    about.setMessage("Fill in boxes with appropriate values. " + System.lineSeparator() + "You may only fill in three boxes." + System.lineSeparator() + System.lineSeparator() +
                            "The app will, based on your inputs, output values for all five values." + System.lineSeparator() + System.lineSeparator() + "Press the solve button once you're done, " +
                            "or press the clear button to reset your inputs." + System.lineSeparator() + System.lineSeparator() + "Units for each variable are as follows: "
                            + System.lineSeparator() + System.lineSeparator() + "Velocity (initial and final): meters per second" + System.lineSeparator() + System.lineSeparator()
                            + "Acceleration: meters per second squared" + System.lineSeparator() + System.lineSeparator() + "Time: Seconds" + System.lineSeparator() +
                            System.lineSeparator() + "Displacement: Meters" + System.lineSeparator());
                    about.setButton(AlertDialog.BUTTON_NEUTRAL, "OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
                    about.show();
                    Button aboutOkButton = about.getButton(AlertDialog.BUTTON_NEUTRAL);
                    aboutOkButton.setBackgroundColor(Color.rgb(214, 215, 215));
                    aboutOkButton.setTextColor(Color.BLACK);
                }
            });
        if (solve != null)
            solve.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    // variable declaration/initialization for the algorithm
                    Intent intent = new Intent(DataInput.this, DisplayResults.class);
                    Bundle bundle = new Bundle();
                    boolean twoUnknowns = false;
                    double initial_vel = 0.0;
                    double final_vel = 0.0;
                    double accel = 0.0;
                    double time = 0.0;
                    double disp = 0.0;
                    String initial_velocity = "";
                    String final_velocity = "";
                    String acceleration = "";
                    String Time = "";
                    String displacement = "";
                    String error;
                    text_initial_vel = (EditText) findViewById(R.id.initial_vel);
                    text_final_vel = (EditText) findViewById(R.id.final_vel);
                    text_accel = (EditText) findViewById(R.id.accel);
                    text_time = (EditText) findViewById(R.id.time);
                    text_disp = (EditText) findViewById(R.id.displace);
                    // Check to make sure that no less than three values are input
                    if ((isEmpty(text_initial_vel) && isEmpty(text_final_vel) && isEmpty(text_accel) && isEmpty(text_time) && isEmpty(text_disp)) ||
                            (isEmpty(text_final_vel) && isEmpty(text_accel) && isEmpty(text_time) && isEmpty(text_disp)) ||
                            (isEmpty(text_initial_vel) && isEmpty(text_accel) && isEmpty(text_time) && isEmpty(text_disp)) ||
                            (isEmpty(text_initial_vel) && isEmpty(text_final_vel) && isEmpty(text_time) && isEmpty(text_disp)) ||
                            (isEmpty(text_initial_vel) && isEmpty(text_final_vel) && isEmpty(text_accel) && isEmpty(text_disp)) ||
                            (isEmpty(text_initial_vel) && isEmpty(text_final_vel) && isEmpty(text_accel) && isEmpty(text_time)) ||
                            (isEmpty(text_accel) && isEmpty(text_time) && isEmpty(text_disp)) ||
                            (isEmpty(text_final_vel) && isEmpty(text_time) && isEmpty(text_disp)) ||
                            (isEmpty(text_final_vel) && isEmpty(text_accel) && isEmpty(text_disp)) ||
                            (isEmpty(text_final_vel) && isEmpty(text_accel) && isEmpty(text_time)) ||
                            (isEmpty(text_initial_vel) && isEmpty(text_accel) && isEmpty(text_time)) ||
                            (isEmpty(text_initial_vel) && isEmpty(text_final_vel) && isEmpty(text_time)) ||
                            (isEmpty(text_initial_vel) && isEmpty(text_final_vel) && isEmpty(text_accel)) ||
                            (isEmpty(text_initial_vel) && isEmpty(text_time) && isEmpty(text_disp)) ||
                            (isEmpty(text_initial_vel) && isEmpty(text_final_vel) && isEmpty(text_disp)) ||
                            (isEmpty(text_initial_vel) && isEmpty(text_accel) && isEmpty(text_disp)))
                    {
                        twoUnknowns = false;
                    }
                    // Case 1
                    else if (isEmpty(text_final_vel) && isEmpty(text_time))
                    {
                        if (text_initial_vel != null && !TextUtils.isEmpty(text_initial_vel.getText()))
                        {
                            initial_vel = Double.parseDouble(text_initial_vel.getText().toString());
                        }
                        if (text_accel != null && !TextUtils.isEmpty(text_accel.getText()))
                        {
                            accel = Double.parseDouble(text_accel.getText().toString());
                        }
                        if (text_disp != null && !TextUtils.isEmpty(text_disp.getText()))
                        {
                            disp = Double.parseDouble(text_disp.getText().toString());
                        }
                        initial_velocity = " ";
                        acceleration = " ";
                        displacement = " ";
                        twoUnknowns = true;
                    }
                    // Case 2
                    else if (isEmpty(text_initial_vel) && isEmpty(text_time))
                    {
                        if (text_final_vel != null && !TextUtils.isEmpty(text_final_vel.getText()))
                        {
                            final_vel = Double.parseDouble(text_final_vel.getText().toString());
                        }
                        if (text_accel != null && !TextUtils.isEmpty(text_accel.getText()))
                        {
                            accel = Double.parseDouble(text_accel.getText().toString());
                        }
                        if (text_disp != null && !TextUtils.isEmpty(text_disp.getText()))
                        {
                            disp = Double.parseDouble(text_disp.getText().toString());
                        }
                        final_velocity = " ";
                        acceleration = " ";
                        displacement = " ";
                        twoUnknowns = true;
                    }
                    // Case 3
                    else if (isEmpty(text_accel) && isEmpty(text_time))
                    {
                        if (text_initial_vel != null && !TextUtils.isEmpty(text_initial_vel.getText()))
                        {
                            initial_vel = Double.parseDouble(text_initial_vel.getText().toString());
                        }
                        if (text_final_vel != null && !TextUtils.isEmpty(text_final_vel.getText()))
                        {
                            final_vel = Double.parseDouble(text_final_vel.getText().toString());
                        }
                        if (text_disp != null && !TextUtils.isEmpty(text_disp.getText()))
                        {
                            disp = Double.parseDouble(text_disp.getText().toString());
                        }
                        initial_velocity = " ";
                        final_velocity = " ";
                        displacement = " ";
                        twoUnknowns = true;
                    }
                    // Case 4
                    else if (isEmpty(text_disp) && isEmpty(text_time))
                    {
                        if (text_initial_vel != null && !TextUtils.isEmpty(text_initial_vel.getText()))
                        {
                            initial_vel = Double.parseDouble(text_initial_vel.getText().toString());
                        }
                        if (text_final_vel != null && !TextUtils.isEmpty(text_final_vel.getText()))
                        {
                            final_vel = Double.parseDouble(text_final_vel.getText().toString());
                        }
                        if (text_accel != null && !TextUtils.isEmpty(text_accel.getText()))
                        {
                            accel = Double.parseDouble(text_accel.getText().toString());
                        }
                        initial_velocity = " ";
                        final_velocity = " ";
                        acceleration = " ";
                        twoUnknowns = true;
                    }
                    // Case 5
                    else if (isEmpty(text_disp) && isEmpty(text_accel))
                    {
                        if (text_initial_vel != null && !TextUtils.isEmpty(text_initial_vel.getText()))
                        {
                            initial_vel = Double.parseDouble(text_initial_vel.getText().toString());
                        }
                        if (text_final_vel != null && !TextUtils.isEmpty(text_final_vel.getText()))
                        {
                            final_vel = Double.parseDouble(text_final_vel.getText().toString());
                        }
                        if (text_time != null && !TextUtils.isEmpty(text_time.getText()))
                        {
                            time = Double.parseDouble(text_time.getText().toString());
                        }
                        initial_velocity = "";
                        final_velocity = " ";
                        Time = " ";
                        twoUnknowns = true;
                    }
                    // Case 6
                    else if (isEmpty(text_final_vel) && isEmpty(text_disp))
                    {
                        if (text_initial_vel != null && !TextUtils.isEmpty(text_initial_vel.getText()))
                        {
                            initial_vel = Double.parseDouble(text_initial_vel.getText().toString());
                        }
                        if (text_accel != null && !TextUtils.isEmpty(text_accel.getText()))
                        {
                            accel = Double.parseDouble(text_accel.getText().toString());
                        }
                        if (text_time != null && !TextUtils.isEmpty(text_time.getText()))
                        {
                            time = Double.parseDouble(text_time.getText().toString());
                        }
                        initial_velocity = " ";
                        acceleration = " ";
                        Time = " ";
                        twoUnknowns = true;
                    }
                    // Case 7
                    else if (isEmpty(text_initial_vel) && isEmpty(text_disp))
                    {
                        if (text_final_vel != null && !TextUtils.isEmpty(text_final_vel.getText()))
                        {
                            final_vel = Double.parseDouble(text_final_vel.getText().toString());
                        }
                        if (text_accel != null && !TextUtils.isEmpty(text_accel.getText()))
                        {
                            accel = Double.parseDouble(text_accel.getText().toString());
                        }
                        if (text_time != null && !TextUtils.isEmpty(text_time.getText()))
                        {
                            time = Double.parseDouble(text_time.getText().toString());
                        }
                        final_velocity = " ";
                        acceleration = " ";
                        Time = " ";
                        twoUnknowns = true;
                    }
                    // Case 8
                    else if (isEmpty(text_accel) && isEmpty(text_final_vel))
                    {
                        if (text_initial_vel != null && !TextUtils.isEmpty(text_initial_vel.getText()))
                        {
                            initial_vel = Double.parseDouble(text_initial_vel.getText().toString());
                        }
                        if (text_time != null && !TextUtils.isEmpty(text_time.getText()))
                        {
                            time = Double.parseDouble(text_time.getText().toString());
                        }
                        if (text_disp != null && !TextUtils.isEmpty(text_disp.getText()))
                        {
                            disp = Double.parseDouble(text_disp.getText().toString());
                        }
                        initial_velocity = " ";
                        Time = " ";
                        displacement = " ";
                        twoUnknowns = true;
                    }
                    // Case 9
                    else if (isEmpty(text_initial_vel) && isEmpty(text_final_vel))
                    {
                        if (text_time != null && !TextUtils.isEmpty(text_time.getText()))
                        {
                            time = Double.parseDouble(text_time.getText().toString());
                        }
                        if (text_accel != null && !TextUtils.isEmpty(text_accel.getText()))
                        {
                            accel = Double.parseDouble(text_accel.getText().toString());
                        }
                        if (text_disp != null && !TextUtils.isEmpty(text_disp.getText()))
                        {
                            disp = Double.parseDouble(text_disp.getText().toString());
                        }
                        acceleration = " ";
                        Time = " ";
                        displacement = " ";
                        twoUnknowns = true;
                    }
                    // Case 10
                    else if (isEmpty(text_initial_vel) && isEmpty(text_accel))
                    {
                        if (text_final_vel != null && !TextUtils.isEmpty(text_final_vel.getText()))
                        {
                            final_vel = Double.parseDouble(text_final_vel.getText().toString());
                        }
                        if (text_time != null && !TextUtils.isEmpty(text_time.getText()))
                        {
                            time = Double.parseDouble(text_time.getText().toString());
                        }
                        if (text_disp != null && !TextUtils.isEmpty(text_disp.getText()))
                        {
                            disp = Double.parseDouble(text_disp.getText().toString());
                        }
                        final_velocity = " ";
                        Time = " ";
                        displacement = " ";
                        twoUnknowns = true;
                    }
                    // variable declaration for Binder's algorithm
                    int Case = 0;
                    double a = 0.0, vel = 0.0, vel2 = 0.0, v0 = 0.0, v02 = 0.0, dt = 0.0, dt2 = 0.0, dx = 0.0;
                    // check to see how much unknown variables are being solved
                    if (twoUnknowns)
                    {
                        // figure out the unknown variables
                        // checks if final velocity and time are the unknown variables
                        if (final_velocity.equals("") && Time.equals("")) {
                            v0 = initial_vel;
                            a = accel;
                            dx = disp;
                            Case = 1;
                        }
                        // checks if initial velocity and time are the unknown variables
                        else if (initial_velocity.equals("") && Time.equals("")) {
                            vel = final_vel;
                            a = accel;
                            dx = disp;
                            Case = 2;
                        }
                        // checks if acceleration and time are the unknown variables
                        else if (acceleration.equals("") && Time.equals("")) {
                            v0 = initial_vel;
                            vel = final_vel;
                            dx = disp;
                            Case = 3;
                        }
                        // checks if displacement and time are the unknown variables
                        else if (displacement.equals("") && Time.equals("")) {
                            v0 = initial_vel;
                            vel = final_vel;
                            a = accel;
                            Case = 4;
                        }
                        // checks if displacement and acceleration are the unknown variables
                        else if (displacement.equals("") && acceleration.equals("")) {
                            v0 = initial_vel;
                            vel = final_vel;
                            dt = time;
                            Case = 5;
                        }
                        // checks if final velocity and displacement are the unknown variables
                        else if (final_velocity.equals("") && displacement.equals("")) {
                            v0 = initial_vel;
                            a = accel;
                            dt = time;
                            Case = 6;
                        }
                        // checks if initial velocity and displacement are the unknown variables
                        else if (initial_velocity.equals("") && displacement.equals("")) {
                            vel = final_vel;
                            a = accel;
                            dt = time;
                            Case = 7;
                        }
                        // checks if acceleration and final velocity are the unknown variables
                        else if (acceleration.equals("") && final_velocity.equals("")) {
                            v0 = initial_vel;
                            dt = time;
                            dx = disp;
                            Case = 8;
                        }
                        // checks if initial velocity and final velocity are the unknown variables
                        else if (initial_velocity.equals("") && final_velocity.equals("")) {
                            a = accel;
                            dt = time;
                            dx = disp;
                            Case = 9;
                        }
                        // checks if initial velocity and acceleration are the unknown variables
                        else if (initial_velocity.equals("") && acceleration.equals("")) {
                            vel = final_vel;
                            dt = time;
                            dx = disp;
                            Case = 10;
                        }
                        // BEGIN BINDER ALGORITHM ***********************************************************************************************************************
                        // Case 1: solve for final velocity and time
                        // Final velocity squared and time squared are also solved
                        if (Case == 1) {
                            error = "";
                            if (a == 0.0) {
                                vel = v0;
                                dt = dx / v0;
                                FlurryAgent.logEvent("Case 1.a");
                            } else {
                                if (v0 * v0 + 2 * a * dx < 0) {
                                    error = "The combination of input variables results in the square root of a negative number. " +
                                            "Please change the acceleration and/or displacement values.";
                                } else {
                                    vel = sqrt(v0 * v0 + 2 * a * dx);
                                    vel2 = -(sqrt(v0 * v0 + 2 * a * dx));   //Input variables result in a possible inverse value
                                    dt = (vel - v0) / a;
                                    dt2 = (vel2 - v0) / a;
                                    Case = 2;
                                    FlurryAgent.logEvent("Case 1.c");
                                }
                            }
                        }
                        // end of code segment for case 1
                        // Case 2 : solve for initial velocity and time
                        // initial velocity squared and time squared are also solved
                        else if (Case == 2) {
                            error = "";
                            if (a == 0.0) {
                                if ((vel == 0.0) && (dx == 0.0)) {
                                    v0 = 0.0;
                                    dt = 0.0;   //Arbitrary value
                                    Case = 3;
                                    FlurryAgent.logEvent("Case 2.a");
                                } else if ((vel == 0.0) && (dx != 0.0)) {
                                    error = "The values given are not possible, because an object cannot move if it has no acceleration and no velocity." +
                                            "Please change the given values.";
                                } else {
                                    v0 = vel;
                                    dt = dx / v0;
                                    Case = 4;
                                    FlurryAgent.logEvent("Case 2.c");
                                }
                            } else {
                                if (vel * vel - 2 * a * dx < 0) {
                                    error = "The combination of input variables results in the square root of a negative number. " +
                                            "Please change the acceleration and/or displacement values to be smaller numbers.";
                                } else {
                                    v0 = sqrt(vel * vel - 2 * a * dx);
                                    v02 = -(sqrt(vel * vel - 2 * a * dx));  //Input values result in a possible inverse result
                                    dt = (vel - v0) / a;
                                    dt2 = (vel - v02) / a;
                                    Case = 5;
                                    FlurryAgent.logEvent("Case 2.e");
                                }
                            }
                        }
                        // end of code segment for case 2
                        // Case 3: solve for acceleration and time
                        else if (Case == 3) {
                            error = "";
                            if (dx == 0) {  //If displacement is zero
                                if (vel == v0) {    //If both displacement and velocity are both equal
                                    if (vel == 0 && v0 == 0) {
                                        a = 0.0;
                                        dt = 0.0;    //arbitrary since object is stationary
                                        Case = 6;
                                        FlurryAgent.logEvent("Case 3.a");
                                    } else    //velocity and initial velocity are nonzero and equal (object is moving)
                                    {
                                        a = 0.0; // a can be anything
                                        dt = 0.0;   //no displacement but object is moving, therefore time must be zero
                                        Case = 7;
                                        FlurryAgent.logEvent("Case 3.b");
                                    }
                                } else if (v0 == -vel) {
                                    error = "There is no possible way to determine acceleration and time since there are infinite combinations of both." +
                                            "Either acceleration or time must be specified or any of the current inputs must be changed.";
                                } else {
                                    error = "Input variables result in division by zero. Displacement must be nonzero or velocities must be equal in magnitude.";
                                }
                            } else {
                                a = (vel * vel - v0 * v0) / (2.0 * dx);
                                if (a == 0.0) {
                                    dt = dx / v0;
                                    Case = 8;
                                    FlurryAgent.logEvent("Case 3.e");
                                } else {
                                    dt = (vel - v0) / a;
                                    Case = 9;
                                    FlurryAgent.logEvent("Case 3.f");
                                }
                            }
                        }
                        // end of code segment for case 3
                        // Case 4: solve for displacement and time
                        else if (Case == 4) {
                            error = "";
                            if (a == 0) {
                                if (vel != v0) {
                                    error = "If there is no acceleration, velocities must be equal or acceleration must be nonzero." +
                                            "However, making velocities equal results in an infinite number of solutions for displacement and time." +
                                            "Change acceleration to be a nonzero value.";
                                } else {
                                    error = "There are an infinite number of solutions for displacement and time, given the current inputs." +
                                            "Change acceleration to be a nonzero value.";
                                }
                            } else {
                                dx = (vel * vel - v0 * v0) / (2.0 * a);
                                dt = (vel - v0) / a;
                                Case = 10;
                                FlurryAgent.logEvent("Case 4.c");
                            }
                        }
                        // end of code segment for case 4
                        // Case 5: solve for displacement and acceleration
                        else if (Case == 5) {
                            error = "";
                            if (dt == 0) {
                                if (vel == v0) {
                                    dx = 0.0;
                                    a = 0.0;  // any a works
                                    Case = 11;
                                    FlurryAgent.logEvent("Case 5.a");
                                } else {
                                    error = "The given values imply infinite acceleration, since the velocities changed in zero time." +
                                            "Change the value of time to be nonzero, or make velocities equal.";
                                }
                            } else {
                                a = (vel - v0) / dt;
                                dx = v0 * dt + 0.5 * a * dt * dt;
                                Case = 12;
                                FlurryAgent.logEvent("Case 5.c");
                            }
                        }
                        // end of code segment for case 5
                        // Case 6: solve for final velocity and displacement
                        else if (Case == 6) {
                            error = "";
                            vel = v0 + a * dt;
                            dx = v0 * dt + 0.5 * a * dt * dt;
                            Case = 13;
                            FlurryAgent.logEvent("Case 6.a");
                        }
                        // end of code segment for case 6
                        // Case 7: solve for initial velocity and displacement
                        else if (Case == 7) {
                            error = "";
                            v0 = vel - a * dt;
                            dx = v0 * dt + 0.5 * a * dt * dt;
                            Case = 14;
                            FlurryAgent.logEvent("Case 7.a");
                        }
                        // end of code segment for case 7
                        // Case 8: solve for acceleration and final velocity
                        else if (Case == 8) {
                            error = "";
                            if (dt == 0.0) {
                                if (dx == 0.0) {
                                    a = 0.0;  // a can be anything
                                    vel = v0;
                                    Case = 15;
                                    FlurryAgent.logEvent("Case 8.a");
                                } else {
                                    error = "The given values imply infinite acceleration, since there was a displacement in zero time." +
                                            "Change the value of time to be nonzero, or make displacement zero.";
                                }
                            } else {
                                a = (2 * (dx - v0 * dt))/(dt * dt);
                                vel = v0 + a * dt;
                                Case = 16;
                                FlurryAgent.logEvent("Case 8.c");
                            }
                        }
                        // end of code segment for case 8
                        // Case 9: solve for initial velocity and final velocity
                        else if (Case == 9) {
                            error = "";
                            if (dt == 0.0) {
                                if (dx == 0.0) {
                                    error = "There's not enough information to determine initial and final velocities." +
                                            "Changing displacement will result in an error, so change time to be nonzero.";
                                } else {
                                    error = "The given values imply infinite acceleration, since there was a displacement in zero time." +
                                            "Change the value of time to be nonzero, or make displacement zero.";
                                }
                            } else {
                                //if x is 0, velocities should be inverse values of each other
                                v0 = 1.0 / dt * (dx - 0.5 * a * dt * dt);
                                vel = v0 + a * dt;
                                Case = 17;
                                FlurryAgent.logEvent("Case 9.c");
                            }
                        }
                        // end of code segment for case 9
                        // Case 10: solve for initial velocity and acceleration
                        else if (Case == 10) {
                            error = "";
                            if (dt == 0.0) {
                                if (dx == 0.0) {
                                    a = 0.0; // a can be anything
                                    v0 = vel;
                                    Case = 18;
                                    FlurryAgent.logEvent("Case 10.a");
                                    //error = "Initial velocity and acceleration cannot be found if time and displacement are equal to zero.";
                                } else {
                                    error = "The given values imply infinite acceleration, since there was a displacement in zero time." +
                                            "Change the value of time to be nonzero, or make displacement zero.";
                                }
                            } else {
                                a = 2.0 / (dt * dt) * (vel * dt - dx);
                                v0 = vel - a * dt;
                                Case = 19;
                                FlurryAgent.logEvent("Case 10.c");
                            }
                        }
                        // end of code segment for case 10
                        // this occurs if the users input doesn't relate to a specific case
                        else {
                            error = "Invalid case specified.";
                            //FlurryAgent.logEvent("Error: Invalid Case");
                        }
                        // END BINDER ALGORITHM *************************************************************************************************************************
                    }
                    // this else condition is met if an invalid number of variables are trying to be solved
                    else
                    {
                        error = "An invalid number of unknown variables are trying to be solved. You need to input 3 values - no more, no less.";
                        //FlurryAgent.logEvent("Error: Invalid number of unknown variables");
                    }
                    // output all the variables and corresponding explanation
                    if (error.equals("")) {
                        if (Case == 2) {
                            bundle.putString("initial", "Initial Velocity: " + String.valueOf(BigDecimal.valueOf(v0).setScale(5, RoundingMode.HALF_UP).doubleValue()) + " m/s"); // user
                            bundle.putString("velocity", "Final Velocity: \u00B1" + String.valueOf(BigDecimal.valueOf(vel).setScale(5, RoundingMode.HALF_UP).doubleValue()) + " m/s");
                            bundle.putString("accel", "Acceleration: " + String.valueOf(BigDecimal.valueOf(a).setScale(5, RoundingMode.HALF_UP).doubleValue()) + " m/s\u00B2"); //user
                            bundle.putString("time", "Time: \u00B1" + String.valueOf(BigDecimal.valueOf(dt).setScale(5, RoundingMode.HALF_UP).doubleValue()) + " s");
                            bundle.putString("disp", "Displacement: " + String.valueOf(BigDecimal.valueOf(dx).setScale(5, RoundingMode.HALF_UP).doubleValue()) + " m"); // user
                        }
                        else if (Case == 3) {
                            bundle.putString("initial", "Initial Velocity: " + String.valueOf(BigDecimal.valueOf(v0).setScale(5, RoundingMode.HALF_UP).doubleValue()) + " m/s"); // user
                            bundle.putString("velocity", "Final Velocity: " + String.valueOf(BigDecimal.valueOf(vel).setScale(5, RoundingMode.HALF_UP).doubleValue()) + " m/s");
                            bundle.putString("accel", "Acceleration: " + String.valueOf(BigDecimal.valueOf(a).setScale(5, RoundingMode.HALF_UP).doubleValue()) + " m/s\u00B2"); //user
                            bundle.putString("time", "Time: Arbitrary");
                            bundle.putString("disp", "Displacement: " + String.valueOf(BigDecimal.valueOf(dx).setScale(5, RoundingMode.HALF_UP).doubleValue()) + " m"); // user
                        }
                        else if (Case == 5) {
                            bundle.putString("initial", "Initial Velocity: \u00B1" + String.valueOf(BigDecimal.valueOf(v0).setScale(5, RoundingMode.HALF_UP).doubleValue()) + " m/s");
                            bundle.putString("velocity", "Final Velocity: " + String.valueOf(BigDecimal.valueOf(vel).setScale(5, RoundingMode.HALF_UP).doubleValue()) + " m/s"); // user
                            bundle.putString("accel", "Acceleration: " + String.valueOf(BigDecimal.valueOf(a).setScale(5, RoundingMode.HALF_UP).doubleValue()) + " m/s\u00B2"); // user
                            bundle.putString("time", "Time: \u00B1" + String.valueOf(BigDecimal.valueOf(dt).setScale(5, RoundingMode.HALF_UP).doubleValue()) + " s");
                            bundle.putString("disp", "Displacement: " + String.valueOf(BigDecimal.valueOf(dx).setScale(5, RoundingMode.HALF_UP).doubleValue()) + " m"); // user
                        }
                        else if (Case == 6) {
                            bundle.putString("initial", "Initial Velocity: " + String.valueOf(BigDecimal.valueOf(v0).setScale(5, RoundingMode.HALF_UP).doubleValue()) + " m/s"); // user
                            bundle.putString("velocity", "Final Velocity: " + String.valueOf(BigDecimal.valueOf(vel).setScale(5, RoundingMode.HALF_UP).doubleValue()) + " m/s");
                            bundle.putString("accel", "Acceleration: " + String.valueOf(BigDecimal.valueOf(a).setScale(5, RoundingMode.HALF_UP).doubleValue()) + " m/s\u00B2"); //user
                            bundle.putString("time", "Time: Arbitrary");
                            bundle.putString("disp", "Displacement: " + String.valueOf(BigDecimal.valueOf(dx).setScale(5, RoundingMode.HALF_UP).doubleValue()) + " m"); // user
                        }
                        else if (Case == 7){
                            bundle.putString("initial", "Initial Velocity: " + String.valueOf(BigDecimal.valueOf(v0).setScale(5, RoundingMode.HALF_UP).doubleValue()) + " m/s");
                            bundle.putString("velocity", "Final Velocity: " + String.valueOf(BigDecimal.valueOf(vel).setScale(5, RoundingMode.HALF_UP).doubleValue()) + " m/s");
                            bundle.putString("accel", "Acceleration: Arbitrary");
                            bundle.putString("time", "Time: " + String.valueOf(BigDecimal.valueOf(dt).setScale(5, RoundingMode.HALF_UP).doubleValue()) + " s");
                            bundle.putString("disp", "Displacement: " + String.valueOf(BigDecimal.valueOf(dx).setScale(5, RoundingMode.HALF_UP).doubleValue()) + " m");
                        }
                        else if (Case == 11){
                            bundle.putString("initial", "Initial Velocity: " + String.valueOf(BigDecimal.valueOf(v0).setScale(5, RoundingMode.HALF_UP).doubleValue()) + " m/s");
                            bundle.putString("velocity", "Final Velocity: " + String.valueOf(BigDecimal.valueOf(vel).setScale(5, RoundingMode.HALF_UP).doubleValue()) + " m/s");
                            bundle.putString("accel", "Acceleration: Arbitrary");
                            bundle.putString("time", "Time: " + String.valueOf(BigDecimal.valueOf(dt).setScale(5, RoundingMode.HALF_UP).doubleValue()) + " s");
                            bundle.putString("disp", "Displacement: " + String.valueOf(BigDecimal.valueOf(dx).setScale(5, RoundingMode.HALF_UP).doubleValue()) + " m");
                        }
                        else if (Case == 15){
                            bundle.putString("initial", "Initial Velocity: " + String.valueOf(BigDecimal.valueOf(v0).setScale(5, RoundingMode.HALF_UP).doubleValue()) + " m/s");
                            bundle.putString("velocity", "Final Velocity: " + String.valueOf(BigDecimal.valueOf(vel).setScale(5, RoundingMode.HALF_UP).doubleValue()) + " m/s");
                            bundle.putString("accel", "Acceleration: Arbitrary");
                            bundle.putString("time", "Time: " + String.valueOf(BigDecimal.valueOf(dt).setScale(5, RoundingMode.HALF_UP).doubleValue()) + " s");
                            bundle.putString("disp", "Displacement: " + String.valueOf(BigDecimal.valueOf(dx).setScale(5, RoundingMode.HALF_UP).doubleValue()) + " m");
                        }
                        else if (Case == 18){
                            bundle.putString("initial", "Initial Velocity: " + String.valueOf(BigDecimal.valueOf(v0).setScale(5, RoundingMode.HALF_UP).doubleValue()) + " m/s");
                            bundle.putString("velocity", "Final Velocity: " + String.valueOf(BigDecimal.valueOf(vel).setScale(5, RoundingMode.HALF_UP).doubleValue()) + " m/s");
                            bundle.putString("accel", "Acceleration: Arbitrary");
                            bundle.putString("time", "Time: " + String.valueOf(BigDecimal.valueOf(dt).setScale(5, RoundingMode.HALF_UP).doubleValue()) + " s");
                            bundle.putString("disp", "Displacement: " + String.valueOf(BigDecimal.valueOf(dx).setScale(5, RoundingMode.HALF_UP).doubleValue()) + " m");
                        }
                        else {
                            bundle.putString("initial", "Initial Velocity: " + String.valueOf(BigDecimal.valueOf(v0).setScale(5, RoundingMode.HALF_UP).doubleValue()) + " m/s");
                            bundle.putString("velocity", "Final Velocity: " + String.valueOf(BigDecimal.valueOf(vel).setScale(5, RoundingMode.HALF_UP).doubleValue()) + " m/s");
                            bundle.putString("accel", "Acceleration: " + String.valueOf(BigDecimal.valueOf(a).setScale(5, RoundingMode.HALF_UP).doubleValue()) + " m/s\u00B2");
                            bundle.putString("time", "Time: " + String.valueOf(BigDecimal.valueOf(dt).setScale(5, RoundingMode.HALF_UP).doubleValue()) + " s");
                            bundle.putString("disp", "Displacement: " + String.valueOf(BigDecimal.valueOf(dx).setScale(5, RoundingMode.HALF_UP).doubleValue()) + " m");
                        }
                        bundle.putString("i", String.valueOf(BigDecimal.valueOf(v0).setScale(5, RoundingMode.HALF_UP).doubleValue()));
                        bundle.putString("v", String.valueOf(BigDecimal.valueOf(vel).setScale(5, RoundingMode.HALF_UP).doubleValue()));
                        bundle.putString("a", String.valueOf(BigDecimal.valueOf(a).setScale(5, RoundingMode.HALF_UP).doubleValue()));
                        bundle.putString("t", String.valueOf(BigDecimal.valueOf(dt).setScale(5, RoundingMode.HALF_UP).doubleValue()));
                        bundle.putString("d", String.valueOf(BigDecimal.valueOf(dx).setScale(5, RoundingMode.HALF_UP).doubleValue()));
                        bundle.putInt("case", Case);
                        intent.putExtras(bundle);
                        startActivity(intent);
                    }
                    // output an error if one occurs
                    else {
                        AlertDialog errorAlert = new AlertDialog.Builder(DataInput.this).create();
                        errorAlert.setTitle("Error");
                        errorAlert.setMessage(error + System.lineSeparator());
                        FlurryAgent.logEvent("User error");
                        errorAlert.setButton(AlertDialog.BUTTON_NEUTRAL, "OK", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                        errorAlert.show();
                        Button errorOkButton = errorAlert.getButton(AlertDialog.BUTTON_NEUTRAL);
                        errorOkButton.setBackgroundColor(Color.rgb(214, 215, 215));
                        errorOkButton.setTextColor(Color.BLACK);
                    }
                }
            });
        if (clear != null)
            clear.setOnClickListener(new View.OnClickListener()
            {
                public void onClick(View v)
                {
                    text_initial_vel = (EditText) findViewById(R.id.initial_vel);
                    text_final_vel = (EditText) findViewById(R.id.final_vel);
                    text_accel = (EditText) findViewById(R.id.accel);
                    text_time = (EditText) findViewById(R.id.time);
                    text_disp = (EditText) findViewById(R.id.displace);
                    text_initial_vel.setText("");
                    text_final_vel.setText("");
                    text_accel.setText("");
                    text_time.setText("");
                    text_disp.setText("");
                }
            });
    }
}