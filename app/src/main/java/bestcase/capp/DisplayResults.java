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
        final TextView expl = (TextView) findViewById(R.id.explanation);
        explain.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                AlertDialog about = new AlertDialog.Builder(DisplayResults.this).create();
                about.setTitle("Explanation of Answers");
                //Case 1.a
                if (bundle.getInt("case") == 1) {
                    expl.setText("");
                }
                //Case 1.c
                if (bundle.getInt("case") == 2) {
                    expl.setText("");
                }
                //Case 2.a
                if (bundle.getInt("case") == 3) {
                    expl.setText("");
                }
                //Case 2.c
                if (bundle.getInt("case") == 4) {
                    expl.setText("");
                }
                //Case 2.e
                if (bundle.getInt("case") == 5) {
                    expl.setText("");
                }
                //Case 3.a
                if (bundle.getInt("case") == 6) {
                    expl.setText("");
                }
                //Case 3.b
                if (bundle.getInt("case") == 7) {
                    expl.setText("");
                }
                //Case 3.e
                if (bundle.getInt("case") == 8) {
                    expl.setText("");
                }
                //Case 3.f
                if (bundle.getInt("case") == 9) {
                    expl.setText("");
                }
                //Case 4.c
                if (bundle.getInt("case") == 10) {
                    expl.setText("");
                }
                //Case 5.c
                if (bundle.getInt("case") == 11) {
                    expl.setText("");
                }
                //Case 6.a
                if (bundle.getInt("case") == 12) {
                    expl.setText("");
                }
                //Case 7.a
                if (bundle.getInt("case") == 13) {
                    expl.setText("");
                }
                //Case 8.a
                if (bundle.getInt("case") == 14) {
                    expl.setText("");
                }
                //Case 8.c
                if (bundle.getInt("case") == 15) {
                    expl.setText("");
                }
                //Case 9.c
                if (bundle.getInt("case") == 16) {
                    expl.setText("");
                }
                //Case 10.a
                if (bundle.getInt("case") == 17) {
                    expl.setText("");
                }
                //Case 10.c
                if (bundle.getInt("case") == 18) {
                    expl.setText("");
                }
                about.setMessage("");
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
        if (bundle.getInt("case") == 1) {
            expl.setText("");
        }
        else if(bundle.getInt("case") == 2)
        {
            expl.setText("Case 2 explanation");
        }
        else if(bundle.getInt("case") == 3)
        {
            expl.setText("Case 3 explanation");
        }
        else if(bundle.getInt("case") == 4)
        {
            expl.setText("Case 4 explanation");
        }
        else if(bundle.getInt("case") == 5)
        {
            expl.setText("Case 5 explanation");
        }
        else if(bundle.getInt("case") == 6)
        {
            expl.setText("Case 6 explanation");
        }
        else if(bundle.getInt("case") == 7)
        {
            expl.setText("Case 7 explanation");
        }
        else if(bundle.getInt("case") == 8)
        {
            expl.setText("Case 8 explanation");
        }
        else if(bundle.getInt("case") == 9)
        {
            expl.setText("Case 9 explanation");
        }
        else if(bundle.getInt("case") == 10)
        {
            expl.setText("Case 10 explanation");
        }
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