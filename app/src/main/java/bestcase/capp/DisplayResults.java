package bestcase.capp;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class DisplayResults extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_results);
        Button b = (Button) findViewById(R.id.back);
        Intent intent = getIntent();
        Bundle bundle = getIntent().getExtras();
        String velocity = bundle.getString("velocity");
        TextView textView = new TextView(this);
        textView.setTextSize(40);
        textView.setText(velocity);
        TextView initial_vel = (TextView) findViewById(R.id.velocity_0_num);
        TextView vel = (TextView) findViewById(R.id.velocity_num);
        TextView accel = (TextView) findViewById(R.id.accel_num);
        TextView time = (TextView) findViewById(R.id.time_num);
        TextView disp = (TextView) findViewById(R.id.displace_num);
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
