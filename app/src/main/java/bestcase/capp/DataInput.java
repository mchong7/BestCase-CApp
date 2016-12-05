package bestcase.capp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import java.lang.String;
import java.lang.Double;
import static java.lang.Math.sqrt;
import java.io.*;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.app.Dialog;
import android.app.AlertDialog;
import java.lang.Object;
import android.content.Context;
import android.content.*;

public class DataInput extends AppCompatActivity {

    private boolean isEmpty(EditText etText) {
        return etText.getText().toString().trim().length() == 0;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_input);

        // initializing variables to non-numbers (NaN - Not A Number)
        /*double initial_vel = Double.NaN;
        double final_vel = Double.NaN;
        double accel = Double.NaN;
        double time = Double.NaN;
        double dist = Double.NaN;*/



        Button solve = (Button)findViewById(R.id.solve_button);

        solve.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                EditText text_initial_vel = (EditText) findViewById(R.id.initial_vel);
                EditText text_final_vel = (EditText) findViewById(R.id.final_vel);
                EditText text_accel = (EditText) findViewById(R.id.accel);
                EditText text_time = (EditText) findViewById(R.id.time);
                EditText text_disp = (EditText) findViewById(R.id.disp);

                double initial_vel;
                double final_vel;
                double accel;
                double time;
                double disp;



                // initialize variables to what was input in text boxes
                if(isEmpty(text_initial_vel))
                {
                    initial_vel = Double.parseDouble(text_initial_vel.getText().toString());
                }
                else
                {
                    initial_vel = Double.NaN;
                }
                if(isEmpty(text_final_vel))
                {
                    final_vel = Double.parseDouble(text_final_vel.getText().toString());
                }
                else
                {
                    final_vel = Double.NaN;
                }
                if(isEmpty(text_accel))
                {
                    accel = Double.parseDouble(text_accel.getText().toString());
                }
                else
                {
                    accel = Double.NaN;
                }
                if(isEmpty(text_accel))
                {
                    time = Double.parseDouble(text_time.getText().toString());
                }
                else
                {
                    time = Double.NaN;
                }
                if(isEmpty(text_disp))
                {
                    disp = Double.parseDouble(text_disp.getText().toString());
                }
                else
                {
                    disp = Double.NaN;
                }

                // BEGIN BINDER ALGORITHM
                // method implementation
                // variable declaration
                int Case = 0;
                double a = 0.0, vel = 0.0, vel2, v0 = 0.0, v02, dt = 0.0, dt2, dx = 0.0;
                String error;

                // figure out the unknown variables

                // checks if final velocity and time are the unknown variables
                if ((final_vel == Double.NaN) && (time == Double.NaN)) {
                    v0 = initial_vel;
                    a = accel;
                    dx = disp;
                    Case = 1;
                }
                // checks if initial velocity and time are the unknown variables
                else if ((initial_vel == Double.NaN) && (time == Double.NaN)) {
                    vel = final_vel;
                    a = accel;
                    dx = disp;
                    Case = 2;
                }
                // checks if acceleration and time are the unknown variables
                else if ((accel == Double.NaN) && (time == Double.NaN)) {
                    v0 = initial_vel;
                    vel = final_vel;
                    dx = disp;
                    Case = 3;
                }
                // checks if displacement and time are the unknown variables
                else if ((disp == Double.NaN) && (time == Double.NaN)) {
                    v0 = initial_vel;
                    vel = final_vel;
                    a = accel;
                    Case = 4;
                }
                // checks if displacement and acceleration are the unknown variables
                else if ((disp == Double.NaN) && (accel == Double.NaN)) {
                    v0 = initial_vel;
                    vel = final_vel;
                    dt = time;
                    Case = 5;
                }
                // checks if final velocity and displacement are the unknown variables
                else if ((final_vel == Double.NaN) && (disp == Double.NaN)) {
                    v0 = initial_vel;
                    a = accel;
                    dt = time;
                    Case = 6;
                }
                // checks if initial velocity and displacement are the unknown variables
                else if ((initial_vel == Double.NaN) && (disp == Double.NaN)) {
                    vel = final_vel;
                    a = accel;
                    dt = time;
                    Case = 7;
                }
                // checks if acceleration and final velocity are the unknown variables
                else if ((accel == Double.NaN) && (final_vel == Double.NaN)) {
                    v0 = initial_vel;
                    dt = time;
                    dx = disp;
                    Case = 8;
                }
                // checks if initial velocity and final velocity are the unknown variables
                else if ((initial_vel == Double.NaN) && (final_vel == Double.NaN)) {
                    a = accel;
                    dt = time;
                    dx = disp;
                    Case = 9;
                }
                // checks if initial velocity and acceleration are the unknown variables
                else if((initial_vel == Double.NaN) && (accel == Double.NaN)){
                    vel = final_vel;
                    dt = time;
                    dx = disp;
                    Case = 10;
                }

                // Case 1: solve for final velocity and time
                // velocity squared and time squared are also solved
                if (Case == 1) {
                    error = "";
                    if (a == 0.0) {
                        vel = v0;
                        dt = dx / v0;
                    }
                    else {
                        if (v0*v0 + 2 * a*dx < 0) {
                            error = "Final velocity and time cannot be found because the product of initial velocity squared added by two and acceleration multiplied by displacement is less than zero.";
                        }
                        else {
                            vel = sqrt(v0*v0 + 2 * a*dx);
                            vel2 = -(sqrt(v0*v0 + 2 * a*dx));
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
                        }
                        else if ((vel == 0.0) && (dx != 0.0)) {
                            error = "Initial velocity and time cannot be found if final velocity is equal to zero and displacement is a nonzero number.";
                        }
                        else {
                            v0 = vel;
                            dt = dx / v0;
                        }
                    }
                    else {
                        if (vel*vel - 2 * a*dx < 0) {
                            error = "Initial velocity and time cannot be found because the product of final velocity squared subtracted by two and acceleration multiplied by displacement is less than zero.";
                        }
                        else {
                            v0 = sqrt(vel*vel - 2 * a*dx);
                            v02 = -(sqrt(vel*vel - 2 * a*dx));
                            dt = (vel - v0) / a;
                            dt2 = (vel - v02) / a;
                        }
                    }
                }
                // end of code segment for case 2

                // Case 3: solve for acceleration and time
                else if (Case == 3) {
                    error = "";
                    if (dx == 0) {
                        if (vel == v0) {
                            a = 0.0; // a can be anything
                            dt = 0.0;
                            error = "Acceleration and time cannot be found if final velocity is equal to initial velocity and displacement is equal to zero.";
                        }
                        else if (v0 == -vel) {
                            error = "Acceleration and time cannot be found if initial velocity is equal to negative final velocity and displacement is equal to zero.";
                        }
                        else {
                            error = "Acceleration and time cannot be found using the given input.";
                        }
                    }
                    else {
                        a = (vel*vel - v0*v0) / (2.0*dx);
                        if (a == 0.0) {
                            dt = dx / v0;
                        }
                        else {
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
                            error = "Displacement and time cannot be found if acceleration is equal to zero and final velocity is not equal to initial velocity.";
                        }
                        else {
                            error = "Displacement and time cannot be found using the given input.";
                        }
                    }
                    else {
                        dx = (vel*vel - v0*v0) / (2.0*a);
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
                            error = "Acceleration and displacement cannot be found if final velocity is equal to initial velocity and time is equal to zero.";
                        }
                        else {
                            error = "Displacement and acceleration cannot be found using the given input.";
                        }
                    }
                    else {
                        dx = v0*dt + 0.5*a*dt*dt;
                        a = (vel - v0) / dt;
                    }
                }
                // end of code segment for case 5

                // Case 6: solve for final velocity and displacement
                else if (Case == 6) {
                    error = "";
                    vel = v0 + a*dt;
                    dx = v0*dt + 0.5*a*dt*dt;
                }
                // end of code segment for case 6

                // Case 7: solve for initial velocity and displacement
                else if (Case == 7) {
                    error = "";
                    v0 = vel - a*dt;
                    dx = v0*dt + 0.5*a*dt*dt;
                }
                // end of code segment for case 7

                // Case 8: solve for acceleration and final velocity
                else if (Case == 8) {
                    error = "";
                    if (dt == 0.0) {
                        if (dx == 0.0) {
                            a = 0.0;  // a can be anything
                            vel = v0;
                            error = "Final velocity and acceleration cannot be found if time and displacement are equal to zero.";
                        }
                        else {
                            error = "Acceleration and velocity cannot be found using the given input.";
                        }
                    }
                    else {
                        a = 2 / (dt*dt)*(dx - v0*dt);
                        vel = v0 + a*dt;
                    }
                }
                // end of code segment for case 8

                // Case 9: solve for initial velocity and final velocity
                else if (Case == 9) {
                    error = "";
                    if (dt == 0.0) {
                        if (dx == 0.0) {
                            error = "Initial velocity and final velocity cannot be found if time and displacement are equal to zero.";
                        }
                        else {
                            error = "Initial velocity and final velocity cannot be found using the given input.";
                        }
                    }
                    else {
                        v0 = 1.0 / dt*(dx - 0.5*a*dt*dt);
                        vel = v0 + a*dt;
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
                            error = "Initial velocity and acceleration cannot be found if time and displacement are equal to zero.";
                        }
                        else {
                            error = "Initial velocity and acceleration cannot be found using the given input.";
                        }
                    }
                    else {
                        a = 2.0 / (dt*dt)*(vel*dt - dx);
                        v0 = vel - a*dt;
                    }
                }
                // end of code segment for case 10

                // this occurs if the users input doesn't relate to a specific case
                else {
                    error = "Invalid case specified";
                }

                // output all the variables
                // popup messages would be ideal
                if (error == "") {
                    System.out.println("\nInitial Velocity: " + v0 + " m/s.");
                    System.out.println("Velocity: " + vel + " m/s.");
                    System.out.println("Acceleration: " + a + " m/s^2.");
                    System.out.println("Time: " + dt + " seconds.");
                    System.out.println("Displacement: " + dx + " meters.");
                }
                // output an error if one occurs
                else {
                    //System.out.println("\nError: " + error);
                    AlertDialog error_alert = new AlertDialog.Builder(DataInput.this).create();
                    error_alert.setTitle("Error");
                    error_alert.setMessage(error);
                    error_alert.setButton(AlertDialog.BUTTON_NEUTRAL, "OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
                    error_alert.show();

                }

            }
        }
        );
    }




}
