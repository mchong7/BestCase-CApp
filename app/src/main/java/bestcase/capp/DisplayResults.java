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
public class DisplayResults extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_results);
        Button b = (Button) findViewById(R.id.back);
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

                //Case 1.a
                if (bundle.getInt("case") == 1) {
                    explanations.setMessage("The given values are initial velocity, acceleration, and displacement. " +
                            "We're looking for velocity and time values, and we're going to use the following equations to solve for them." +
                            System.lineSeparator() + System.lineSeparator() + "v\u00B2 = v\u2080\u00B2 + 2a\u0394x" + System.lineSeparator() +
                            "v = v\u2080 + at" + System.lineSeparator() + System.lineSeparator() + "From these equations, we can derive the following to solve" +
                            "for our missing variables." + System.lineSeparator() + System.lineSeparator() + "v = \u00B1\u221Av\u2080\u00B2 + 2a\u0394x +" +
                            System.lineSeparator() + "t = \u00B1(v - v\u2080)/a" + System.lineSeparator() + System.lineSeparator() +
                            "If we fill in the known values for these equations, we get: " +
                            System.lineSeparator() + System.lineSeparator() + "v = \u00B1\u221A(" + bundle.getString("initial") + ")\u00B2 + 2(" +
                            bundle.getString("accel") + ")(" + bundle.getString("disp") + ") = \u00B1" + bundle.getString("velocity") + System.lineSeparator() + System.lineSeparator() +
                            "Once we have the value of velocity, we can get the value for time easily:" + System.lineSeparator() + System.lineSeparator() +
                            "t = \u221A((" + bundle.getString("velocity") + ") - (" + bundle.getString("initial") + "))/(" + bundle.getString("accel") +
                            ") = \u00B1" + bundle.getString("time") + System.lineSeparator());

                }
                //Case 1.c
                if (bundle.getInt("case") == 2) {
                    explanations.setMessage("");
                }
                //Case 2.a
                if (bundle.getInt("case") == 3) {
                    explanations.setMessage("");
                }
                //Case 2.c
                if (bundle.getInt("case") == 4) {
                    explanations.setMessage("");
                }
                //Case 2.e
                if (bundle.getInt("case") == 5) {
                    explanations.setMessage("");
                }
                //Case 3.a
                if (bundle.getInt("case") == 6) {
                    explanations.setMessage("");
                }
                //Case 3.b
                if (bundle.getInt("case") == 7) {
                    explanations.setMessage("");
                }
                //Case 3.e
                if (bundle.getInt("case") == 8) {
                    explanations.setMessage("");
                }
                //Case 3.f
                if (bundle.getInt("case") == 9) {
                    explanations.setMessage("");
                }
                //Case 4.c
                if (bundle.getInt("case") == 10) {
                    explanations.setMessage("");
                }
                //Case 5.c
                if (bundle.getInt("case") == 11) {
                    explanations.setMessage("");
                }
                //Case 6.a
                if (bundle.getInt("case") == 12) {
                    explanations.setMessage("");
                }
                //Case 7.a
                if (bundle.getInt("case") == 13) {
                    explanations.setMessage("");
                }
                //Case 8.a
                if (bundle.getInt("case") == 14) {
                    explanations.setMessage("");
                }
                //Case 8.c
                if (bundle.getInt("case") == 15) {
                    explanations.setMessage("");
                }
                //Case 9.c
                if (bundle.getInt("case") == 16) {
                    explanations.setMessage("");
                }
                //Case 10.a
                if (bundle.getInt("case") == 17) {
                    explanations.setMessage("");
                }
                //Case 10.c
                if (bundle.getInt("case") == 18) {
                    explanations.setMessage("");
                }
                explanations.setMessage("");
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
        b.setOnClickListener(new View.OnClickListener()
                             {
                                 public void onClick(View view) {
                     Intent i = new Intent(DisplayResults.this, DataInput.class);
                     startActivity(i);
                 }
             }
        );
    }
}