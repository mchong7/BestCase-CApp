package bestcase.capp;

import android.content.Intent;
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
        Intent intent = getIntent();
        Bundle bundle = getIntent().getExtras();
        TextView unit1 = (TextView) findViewById((R.id.unit1));
        TextView unit2 = (TextView) findViewById((R.id.unit3));
        TextView unit3 = (TextView) findViewById((R.id.unit2));
        TextView unit4 = (TextView) findViewById((R.id.unit5));
        TextView unit5 = (TextView) findViewById((R.id.unit4));
        unit3.setText(Html.fromHtml("m/s<sup><small>2</sup></small>"));
        TextView initial_vel = (TextView) findViewById(R.id.velocity_0_num);
        TextView vel = (TextView) findViewById(R.id.velocity_num);
        TextView accel = (TextView) findViewById(R.id.accel_num);
        TextView time = (TextView) findViewById(R.id.time_num);
        TextView disp = (TextView) findViewById(R.id.displace_num);
        TextView expl = (TextView) findViewById(R.id.explanation);
        if (bundle.getInt("case") == 1) {
            expl.setText(Html.fromHtml("Equations used: <br></br>v<sup><small>2</sup></small> = v<sub><small>0</sub></small><sup><small>2</sup></small> + 2ax<br></br>" +
                    "x = 1/2(v + v<sub><small>0</sub></small>)t"));
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
