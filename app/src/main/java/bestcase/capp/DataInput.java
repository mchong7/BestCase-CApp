package bestcase.capp;
import android.app.Activity;
import android.app.Dialog;
import android.graphics.Color;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import java.lang.String;
import java.lang.Double;
import java.math.BigDecimal;
import java.math.RoundingMode;
import static java.lang.Math.*;
// import java.io.*;
import android.text.Html;
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
import android.content.Intent;
public class DataInput extends AppCompatActivity {
    private boolean isEmpty(EditText etText) {
        return etText.getText().toString().trim().length() == 0;
    }
    public class ViewDialog {
        public void showDialog(Activity activity, String msg) {
            final Dialog dialog = new Dialog(activity);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setCancelable(false);
            dialog.setContentView(R.layout.resultspage);
            TextView text = (TextView) dialog.findViewById(R.id.txt_dia);
            text.setText(msg);
            Button dialogButton = (Button) dialog.findViewById(R.id.btn_ok);
            dialogButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                }
            });
            dialog.show();
        }
    }
    EditText text_initial_vel;
    EditText text_final_vel;
    EditText text_accel;
    EditText text_time;
    EditText text_disp;
    public final static String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_input);
        Button solve = (Button) findViewById(R.id.solve_button);
        Button clear = (Button) findViewById(R.id.clear_button);
        ImageButton help = (ImageButton) findViewById(R.id.imageButton);
        help.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                AlertDialog about = new AlertDialog.Builder(DataInput.this).create();
                about.setTitle("How to use the Data Input Page");
                about.setMessage("Fill in boxes with appropriate values. " + System.lineSeparator() + "You may only fill in three boxes." + System.lineSeparator() + System.lineSeparator() +
                        "The app will, based on your inputs, output values for all five values." + System.lineSeparator() + System.lineSeparator() + "Press the solve button once you're done, " +
                        "or press the clear button to reset your inputs." + System.lineSeparator() + System.lineSeparator() + "Units for each variable are as follows: "
                        + System.lineSeparator() + "Velocity (initial and final): meters per second" + System.lineSeparator() + "Acceleration: meters per second squared"
                        + System.lineSeparator() + "Time: Seconds" + System.lineSeparator() + "Displacement: Meters" + System.lineSeparator());
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
                        // BEGIN BINDER ALGORITHM
                        // Case 1: solve for final velocity and time
                        // Final velocity squared and time squared are also solved
                        if (Case == 1) {
                            error = "";
                            if (a == 0.0) {
                                vel = v0;
                                dt = dx / v0;
                            } else {
                                if (v0 * v0 + 2 * a * dx < 0) {
                                    error = "The combination of input variables results in the square root of a negative number. " +
                                            "Please change the acceleration and/or displacement values.";
                                } else {
                                    vel = sqrt(v0 * v0 + 2 * a * dx);
                                    vel2 = -(sqrt(v0 * v0 + 2 * a * dx));   //Input variables result in a possible inverse value
                                    dt = (vel - v0) / a;
                                    dt2 = (vel2 - v0) / a;
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
                                    dt = 0.0;
                                } else if ((vel == 0.0) && (dx != 0.0)) {
                                    error = "The values given are not possible, because an object cannot move if it has no acceleration and no velocity." +
                                            "Please change the given values.";
                                } else {
                                    v0 = vel;
                                    dt = dx / v0;
                                }
                            } else {
                                if (vel * vel - 2 * a * dx < 0) {
                                    error = "The combination of input variables results in the square root of a negative number. " +
                                            "Please change the acceleration and/or displacement values.";
                                } else {
                                    v0 = sqrt(vel * vel - 2 * a * dx);
                                    v02 = -(sqrt(vel * vel - 2 * a * dx));  //Input values result in a possible inverse result
                                    dt = (vel - v0) / a;
                                    dt2 = (vel - v02) / a;
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
                                    } else    //velocity and initial velocity are nonzero (object is moving)
                                    {
                                        a = 0.0; // a can be anything
                                        dt = 0.0;   //no displacement but object is moving, therefore time must be zero
                                        // error = "Acceleration and time cannot be found if final velocity is equal to initial velocity and displacement is equal to zero.";
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
                                } else {
                                    dt = (vel - v0) / a;
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
                                    //error = "Acceleration and displacement cannot be found if final velocity is equal to initial velocity and time is equal to zero.";
                                } else {
                                    error = "The given values imply infinite acceleration, since the velocities changed in zero time." +
                                            "Change the value of time to be nonzero, or make velocities equal.";
                                }
                            } else {
                                a = (vel - v0) / dt;
                                dx = v0 * dt + 0.5 * a * dt * dt;
                            }
                        }
                        // end of code segment for case 5
                        // Case 6: solve for final velocity and displacement
                        else if (Case == 6) {
                            error = "";
                            vel = v0 + a * dt;
                            dx = v0 * dt + 0.5 * a * dt * dt;
                        }
                        // end of code segment for case 6
                        // Case 7: solve for initial velocity and displacement
                        else if (Case == 7) {
                            error = "";
                            v0 = vel - a * dt;
                            dx = v0 * dt + 0.5 * a * dt * dt;
                        }
                        // end of code segment for case 7
                        // Case 8: solve for acceleration and final velocity
                        else if (Case == 8) {
                            error = "";
                            if (dt == 0.0) {
                                if (dx == 0.0) {
                                    a = 0.0;  // a can be anything
                                    vel = v0;
                                    //error = "Final velocity and acceleration cannot be found if time and displacement are equal to zero.";
                                } else {
                                    error = "The given values imply infinite acceleration, since there was a displacement in zero time." +
                                            "Change the value of time to be nonzero, or make displacement zero.";
                                }
                            } else {
                                a = 2 / (dt * dt) * (dx - v0 * dt);
                                vel = v0 + a * dt;
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
                                    //error = "Initial velocity and acceleration cannot be found if time and displacement are equal to zero.";
                                } else {
                                    error = "The given values imply infinite acceleration, since there was a displacement in zero time." +
                                            "Change the value of time to be nonzero, or make displacement zero.";
                                }
                            } else {
                                a = 2.0 / (dt * dt) * (vel * dt - dx);
                                v0 = vel - a * dt;
                            }
                        }
                        // end of code segment for case 10
                        // this occurs if the users input doesn't relate to a specific case
                        else {
                            error = "Invalid case specified.";
                        }
                        // END BINDER ALGORITHM
                    }
                    // this else condition is met if an invalid number of variables are trying to be solved
                    else
                    {
                        error = "An invalid number of unknown variables are trying to be solved. Make sure you are solving for 2 unknown variables.";
                    }
                    // output all the variables and corresponding explanation
                    if (error.equals("")) {
                        ViewDialog dialog = new ViewDialog();
                        bundle.putString("velocity", String.valueOf(BigDecimal.valueOf(vel).setScale(3, RoundingMode.HALF_UP).doubleValue()));
                        bundle.putString("initial", String.valueOf(BigDecimal.valueOf(v0).setScale(3, RoundingMode.HALF_UP).doubleValue()));
                        bundle.putString("accel", String.valueOf(BigDecimal.valueOf(a).setScale(3, RoundingMode.HALF_UP).doubleValue()));
                        bundle.putString("time", String.valueOf(BigDecimal.valueOf(dt).setScale(3, RoundingMode.HALF_UP).doubleValue()));
                        bundle.putString("disp", String.valueOf(BigDecimal.valueOf(dx).setScale(3, RoundingMode.HALF_UP).doubleValue()));
                        bundle.putInt("case", Case);
                        //bundle.putString("initial", String.valueOf(initial_vel).substring(0,4));
                        intent.putExtras(bundle);
                        AlertDialog alertDialog = new AlertDialog.Builder(DataInput.this).create();
                        String v0Output = "Initial Velocity: " + v0 + " m/s.";
                        String v02Output = "Second Initial Velocity: " + v02 + " m/s.";
                        String velOutput = "Final Velocity: " + vel + " m/s.";
                        String vel2Output = "Second Final Velocity: " + vel2 + " m/s.";
                        String aOutput = "Acceleration: " + a + " m/s^2.";
                        String dtOutput = "Time: " + dt + " seconds.";
                        String dt2Output = "Second Time: " + dt2 + " seconds.";
                        String dxOutput5 = "Displacement: " + dx + " meters.";
                        alertDialog.setTitle("Results");
                        startActivity(intent);
/*
                        if (Case == 1) {
                            startActivity(intent);
                            //dialog.showDialog(DataInput.this, v0Output + "\n\n" + velOutput + "\n\n" + vel2Output + "\n\n" + aOutput + "\n\n" + dtOutput + "\n\n" + dt2Output + "\n\n" + dxOutput5);
                            // alertDialog.setMessage(v0Output + "\n\n" + velOutput + "\n\n" + vel2Output + "\n\n" + aOutput + "\n\n" + dtOutput + "\n\n" + dt2Output + "\n\n" + dxOutput5);
                        } else if (Case == 2)
                        {
                            //dialog.showDialog(DataInput.this, v0Output + "\n\n" + v02Output + "\n\n" + velOutput + "\n\n" + aOutput + "\n\n" + dtOutput + "\n\n" + dt2Output + "\n\n" + dxOutput5);
                        } else
                        {
                            dialog.showDialog(DataInput.this, v0Output + "\n\n" + velOutput + "\n\n" + aOutput + "\n\n" + dtOutput + "\n\n" + dxOutput5);
                        }
                    /*alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
                    alertDialog.show);
                    Button validInputButton = alertDialog.getButton(AlertDialog.BUTTON_NEUTRAL);
                    validInputButton.setBackgroundColor(Color.LTGRAY);
                    validInputButton.setTextColor(Color.BLUE);
*/
                    }
                    // output an error if one occurs
                    else {
                        AlertDialog errorAlert = new AlertDialog.Builder(DataInput.this).create();
                        errorAlert.setTitle("Error");
                        errorAlert.setMessage(error + System.lineSeparator());
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
