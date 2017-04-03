package bestcase.capp;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.Display;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import org.w3c.dom.Text;
import java.awt.font.TextAttribute;
import java.text.AttributedString;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
public class DisplayResults extends AppCompatActivity {
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_results);
        // enable the back button in the app bar
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        Button explain = (Button) findViewById(R.id.explain);
        final Bundle bundle = getIntent().getExtras();
        TextView initial_vel = (TextView) findViewById(R.id.velocity_0_num);
        TextView vel = (TextView) findViewById(R.id.velocity_num);
        TextView accel = (TextView) findViewById(R.id.accel_num);
        TextView time = (TextView) findViewById(R.id.time_num);
        TextView disp = (TextView) findViewById(R.id.displace_num);
        final TextView expl = (TextView) findViewById(R.id.explain);
        explain.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                AlertDialog explanations = new AlertDialog.Builder(DisplayResults.this).create();
                explanations.setTitle("Explanation of Answers");

                //Unicode characters:
                //  u00B2 = 2 squared
                //  u2080 = 0 subscript
                //  u0394 = delta
                //  u00B1 = plus minus sign
                //  u221A = square root
                //  u00B9 = 1 superscript (for fractions)
                //  u2082 = 2 subscript (for fractions)

                //Case 1.a
                if (bundle.getInt("case") == 1) {
                    explanations.setMessage("The given values are initial velocity, acceleration, and displacement. " +
                            "We're looking for velocity and time values. However, our process is made easy because acceleration is 0." +
                    System.lineSeparator() + System.lineSeparator() + "If acceleration is zero, we cannot possible have moved faster or slower from our initial speed" +
                            "to our final speed. Therefore, final velocity must be equal to initial velocity." + System.lineSeparator() + System.lineSeparator() +
                    "Final Velocity = Initial Velocity = " + bundle.getString("i") + System.lineSeparator() + System.lineSeparator() +
                    "From there, it's a piece of cake." + System.lineSeparator() + "We use one of the five equations: " + System.lineSeparator() + System.lineSeparator() +
                    "\u0394x = \u00B9/\u2082(v + v\u2080)t" + System.lineSeparator() + System.lineSeparator() + "Initial and final velocities are equal, " +
                            "so we end up with 2v or 2v\u2080, it doesn't matter, since they're equal." + System.lineSeparator() + "\u00B9/\u2082 and 2 straight up cancel. So now we have:" +
                    System.lineSeparator() + System.lineSeparator() + "\u0394x = vt" + System.lineSeparator() + System.lineSeparator() +
                    "If we just divide both side by velocity, we get: t = v\u0394x" + System.lineSeparator() + "Both velocity and displacement are known values, " +
                            "so we just fill in the blanks and solve for the final variable." + System.lineSeparator() + System.lineSeparator() +
                    "t = (" + bundle.getString("v") + ")(" + bundle.getString("d") + ") = " + bundle.getString("t") + System.lineSeparator());
                }
                //Case 1.c
                else if (bundle.getInt("case") == 2) {
                    explanations.setMessage("The given values are initial velocity, acceleration, and displacement. " +
                            "We're looking for velocity and time values, and we're going to use the following equations to solve for them." +
                            System.lineSeparator() + System.lineSeparator() + "It's important to note that both positive and negative values are possible " +
                                    "for this set of solutions, hence the plus minus sign." +
                            System.lineSeparator() + System.lineSeparator() + "v\u00B2 = v\u2080\u00B2 + 2a\u0394x" + System.lineSeparator() +
                            "v = v\u2080 + at" + System.lineSeparator() + System.lineSeparator() + "From these equations, we can derive the following to solve " +
                            "for our missing variables." + System.lineSeparator() + System.lineSeparator() + "v = \u00B1\u221A(v\u2080\u00B2) + 2a\u0394x +" +
                            System.lineSeparator() + "t = \u00B1(v - v\u2080)/a" + System.lineSeparator() + System.lineSeparator() +
                            "If we fill in the known values for these equations, we get: " +
                            System.lineSeparator() + System.lineSeparator() + "v = \u00B1\u221A(" + bundle.getString("i") + ")\u00B2 + 2(" +
                            bundle.getString("a") + ")(" + bundle.getString("d") + ") = \u00B1" + bundle.getString("v") + System.lineSeparator() + System.lineSeparator() +
                            "Once we have the value of velocity, we can get the value for time easily:" + System.lineSeparator() + System.lineSeparator() +
                            "t = \u221A((" + bundle.getString("v") + ") - (" + bundle.getString("i") + "))/(" + bundle.getString("a") +
                            ") = \u00B1" + bundle.getString("t") + System.lineSeparator());
                }
                //Case 2.a
                else if (bundle.getInt("case") == 3) {
                    explanations.setMessage("The given values are final velocity, acceleration, and displacement. " +
                            "We're looking for initial velocity and time values." + System.lineSeparator() + System.lineSeparator()
                            + "This case is easy. In this case, all the given inputs are 0. What does this mean?" + System.lineSeparator() + System.lineSeparator() +
                    "Well, this means that our object is not moving right now (zero velocity). It also means it never moved faster or slower to start with (zero acceleration)." +
                            "Additionally, our object moved zero distance (zero displacement). If we aren't moving and didn't move, our initial velocity is going to be zero too." +
                            System.lineSeparator() + System.lineSeparator() + "However, our time could be any number, because our object is completely stationary - it's not moving." +
                            "It could have been stationary for zero seconds or ten million years - we can't determine that information, " +
                            "because no matter what value time is, the rest of the variables remain the same. Therefore, time is arbitrary and all other values are 0.");
                }
                //Case 2.c
                else if (bundle.getInt("case") == 4) {
                    explanations.setMessage("The given values are final velocity, acceleration, and displacement. " +
                            "We're looking for initial velocity and time values. However, our process is made easy because acceleration is 0." +
                            System.lineSeparator() + System.lineSeparator() + "If acceleration is zero, we cannot possible have moved faster or slower than our final speed. " +
                            "Therefore, final velocity must be equal to initial velocity." + System.lineSeparator() + System.lineSeparator() +
                            "Initial Velocity = Final Velocity = " + bundle.getString("v") + System.lineSeparator() + System.lineSeparator() +
                            "From there, it's really quite simple." + System.lineSeparator() + "We use one of the five equations: " + System.lineSeparator() + System.lineSeparator() +
                            "\u0394x = \u00B9/\u2082(v + v\u2080)t" + System.lineSeparator() + System.lineSeparator() + "Initial and final velocities are equal, " +
                            "so we end up with 2v or 2v\u2080, it doesn't matter, since they're equal." + System.lineSeparator() + "\u00B9/\u2082 and 2 straight up cancel. So now we have:" +
                            System.lineSeparator() + System.lineSeparator() + "\u0394x = vt" + System.lineSeparator() + System.lineSeparator() +
                            "If we just divide both side by velocity, we get: t = v\u0394x" + System.lineSeparator() + "Both velocity and displacement are known values, " +
                            "so we just fill in the blanks and solve for the final variable." + System.lineSeparator() + System.lineSeparator() +
                            "t = (" + bundle.getString("v") + ")(" + bundle.getString("d") + ") = " + bundle.getString("t") + System.lineSeparator());
                }
                //Case 2.e
                else if (bundle.getInt("case") == 5) {
                    explanations.setMessage("The given values are final velocity, acceleration, and displacement. " +
                            "We're looking for initial velocity and time values, and we're going to use the following equations to solve for them." +
                            System.lineSeparator() + System.lineSeparator() + "It's important to note that both positive and negative values are possible " +
                            "for this set of solutions, hence the plus minus sign." +
                            System.lineSeparator() + System.lineSeparator() + "v\u00B2 = v\u2080\u00B2 + 2a\u0394x" + System.lineSeparator() +
                            "v = v\u2080 + at" + System.lineSeparator() + System.lineSeparator() + "From these equations, we can derive the following to solve " +
                            "for our missing variables." + System.lineSeparator() + System.lineSeparator() + "v\u2080 = \u00B1\u221A(v\u00B2) + 2a\u0394x +" +
                            System.lineSeparator() + "t = \u00B1(v - v\u2080)/a" + System.lineSeparator() + System.lineSeparator() +
                            "If we fill in the known values for these equations, we get: " +
                            System.lineSeparator() + System.lineSeparator() + "v\u2080 = \u00B1\u221A(" + bundle.getString("v") + ")\u00B2 - 2(" +
                            bundle.getString("a") + ")(" + bundle.getString("d") + ") = \u00B1" + bundle.getString("i") + System.lineSeparator() + System.lineSeparator() +
                            "Once we have the value of initial velocity, we can get the value for time easily:" + System.lineSeparator() + System.lineSeparator() +
                            "t = \u221A((" + bundle.getString("v") + ") - (" + bundle.getString("i") + "))/(" + bundle.getString("a") +
                            ") = \u00B1" + bundle.getString("t") + System.lineSeparator());
                }
                //Case 3.a
                else if (bundle.getInt("case") == 6) {
                    explanations.setMessage("The given values are initial velocity, final velocity, and displacement. " +
                            "We're looking for acceleration and time values." + System.lineSeparator() + System.lineSeparator() +
                            "This case is easy. In this case, all the given inputs are 0. What does this mean?" + System.lineSeparator() + System.lineSeparator() +
                            "Well, this means that our object started off by not moving (zero initial velocity). It also means it is still not moving (zero final velocity)." +
                            "Additionally, our object moved zero distance (zero displacement). If we aren't moving and didn't move, our acceleration is zero." +
                            System.lineSeparator() + System.lineSeparator() + "However, our time could be any number, because our object is completely stationary - it's not moving." +
                            "It could have been stationary for zero seconds or ten million years - we can't determine that information, " +
                            "because no matter what value time is, the rest of the variables remain the same. Therefore, time is arbitrary and all other values are 0.");
                }
                //Case 3.b
                else if (bundle.getInt("case") == 7) {
                    explanations.setMessage("The given values are initial velocity, final velocity, and displacement. " +
                            "We're looking for acceleration and time values." + System.lineSeparator() + System.lineSeparator() +
                            "In this case, our object didn't move (zero displacement), but it is still moving, because we defined nonzero (and equal) values " +
                            "for initial and final velocities." + System.lineSeparator() + System.lineSeparator() +
                            "This is not a paradox - we simply have to say that no time passed. That is, initial and final velocity occured in the same instant." +
                            "However, it's not possible to determine acceleration, since it has to occur in a given amount of time. Therefore, acceleration is arbitrary." + System.lineSeparator());
                }
                //Case 3.e
                else if (bundle.getInt("case") == 8) {
                    explanations.setMessage("");
                }
                //Case 3.f
                else if (bundle.getInt("case") == 9) {
                    explanations.setMessage("");
                }
                //Case 4.c
                else if (bundle.getInt("case") == 10) {
                    explanations.setMessage("");
                }
                //Case 5.a
                else if (bundle.getInt("case") == 11) {
                    explanations.setMessage("The given values are initial velocity, final velocity, and time. We're looking for acceleration and displacement."
                                           + System.lineSeparator() + System.lineSeparator()
                                           + "We're given that time is equal to zero, and initial velocity is equal to final velocity."
                                           + System.lineSeparator() + "If time is zero, then our object can't have moved. This means that displacement is equal to zero."
                                           + System.lineSeparator() + System.lineSeparator()
                                           + "However, we cannot determine acceleration, because no time has passed. Acceleration could be anything and it wouldn't change the results."
                                           + System.lineSeparator + System.lineSeparator()
                                           + "As a result, we cannot determine acceleration - it's an arbitrary number.");
                }
                //Case 5.c
                else if (bundle.getInt("case") == 12) {
                    explanations.setMessage("The given values are initial velocity, final velocity, and time. We're looking for acceleration and displacement."
                                           + System.lineSeparator() + System.lineSeparator()
                                           + "We will need the following equation to solve for acceleration:"
                                           + System.lineSeparator() + System.lineSeparator() 
                                           + "v = v\u2080 + at"
                                           + System.lineSeparator() + System.lineSeparator()
                                           + "If we rearrange this equation to solve for acceleration, we get:"
                                           + System.lineSeparator() + System.lineSeparator()
                                           + "a = (v - v\u2080)/t"
                                           + System.lineSeparator() + System.lineSeparator()
                                           + "If we fill in known values, we get:
                                           + System.lineSeparator() + System.lineSeparator()
                                           + "a = ((" + bundle.getString("v") + ") - (" + bundle.getString("i") + "))/(" + bundle.getString("t") + ") = " + bundle.getString("a")
                                           + System.lineSeparator() + System.lineSeparator()
                                           + "Using acceleration, we can solve for displacement now using this equation:"
                                           + System.lineSeparator() + System.lineSeparator()
                                           + "\u0394x = v\u2080t + \u00B9/\u2082at\u00B2"
                                           + System.lineSeparator() + System.lineSeparator()
                                           + "We don't have to rearrange this equation, so we fill in known values and get:"
                                           + System.lineSeparator() + System.lineSeparator()
                                           + "\u0394x = (" + bundle.getString("i") + ")(" + bundle.getString("t") + ") + \u00B9/\u2082(" + bundle.getString("a") + ")(" + bundle.getString("t") + "\u00B2) = " bundle.getString("d"));
                }
                //Case 6.a
                else if (bundle.getInt("case") == 13) {
                    explanations.setMessage("The given values are initial velocity, acceleration, and time. We're looking for final velocity and displacement."
                                           + System.lineSeparator() + System.lineSeparator()
                                           + "The following equation easily solves for final velocity:"
                                           + System.lineSeparator() + System.lineSeparator()
                                           + "v = v\u2080 + at"
                                           + System.lineSeparator() + System.lineSeparator()
                                           + "Filling in known values, we get:"
                                           + System.lineSeparator() + System.lineSeparator()
                                           + "v = (" + bundle.getString("i") + ") + (" + bundle.getString("a") + ")(" + bundle.getString("t") + ") = " + bundle.getString("v")
                                           + System.lineSeparator() + System.lineSeparator()
                                           + "To solve for displacement, we use this equation:"
                                           + System.lineSeparator() + System.lineSeparator()
                                           + "\u0394x = v\u2080t + \u00B9/\u2082at\u00B2"
                                           + System.lineSeparator() + System.lineSeparator()
                                           + "Next, we fill in known values and find displacement:
                                           + "\u0394x = (" + bundle.getString("i") + ")(" + bundle.getString("t") + ") + \u00B9/\u2082(" + bundle.getString("a") + ")(" + bundle.getString("t") + "\u00B2) = " bundle.getString("d"));
                }
                //Case 7.a
                else if (bundle.getInt("case") == 14) {
                    explanations.setMessage("The given values are final velocity, acceleration, and time. We're looking for initial velocity and displacement."
                                           + System.lineSeparator() + System.lineSeparator()
                                           + "The following equation will help us find initial velocity:"
                                           + System.lineSeparator() + System.lineSeparator()
                                           + "v = v\u2080 + at"
                                           + System.lineSeparator() + System.lineSeparator()
                                           + "We do have to rearrange slightly to solve for initial velocity:"
                                           + System.lineSeparator() + System.lineSeparator()
                                           + "v\u2080 = v - at"
                                           + System.lineSeparator() + System.lineSeparator()
                                           + "Filling in known values, we get:"
                                           + System.lineSeparator() + System.lineSeparator()
                                           + "v\u2080 = (" + bundle.getString("v") + ") - (" + bundle.getString("a") + ")(" + bundle.getString("t") + ") = " + bundle.getString("i")
                                           + System.lineSeparator() + System.lineSeparator()
                                           + "To solve for displacement, we use this equation:"
                                           + System.lineSeparator() + System.lineSeparator()
                                           + "\u0394x = v\u2080t + \u00B9/\u2082at\u00B2"
                                           + System.lineSeparator() + System.lineSeparator()
                                           + "Next, we fill in known values and find displacement:
                                           + "\u0394x = (" + bundle.getString("i") + ")(" + bundle.getString("t") + ") + \u00B9/\u2082(" + bundle.getString("a") + ")(" + bundle.getString("t") + "\u00B2) = " bundle.getString("d"));
                }
                //Case 8.a
                else if (bundle.getInt("case") == 15) {
                    explanations.setMessage("");
                }
                //Case 8.c
                else if (bundle.getInt("case") == 16) {
                    explanations.setMessage("");
                }
                //Case 9.c
                else if (bundle.getInt("case") == 17) {
                    explanations.setMessage("");
                }
                //Case 10.a
                else if (bundle.getInt("case") == 18) {
                    explanations.setMessage("");
                }
                //Case 10.c
                else if (bundle.getInt("case") == 19) {
                    explanations.setMessage("");
                }
                else {
                    explanations.setMessage("The mathematical procedure is not recognized."
                                            + System.lineSeparator() + System.lineSeparator()
                                            + "Error.");
                }
                explanations.setButton(AlertDialog.BUTTON_NEUTRAL, "OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                explanations.show();
                Button explanationsOkButton = explanations.getButton(AlertDialog.BUTTON_NEUTRAL);
                explanationsOkButton.setBackgroundColor(Color.rgb(214, 215, 215));
                explanationsOkButton.setTextColor(Color.BLACK);
            }
        });
        initial_vel.setText(bundle.getString("initial"));
        vel.setText(bundle.getString("velocity"));
        accel.setText(bundle.getString("accel"));
        time.setText(bundle.getString("time"));
        disp.setText(bundle.getString("disp"));
    }
}
