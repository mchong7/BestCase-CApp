package bestcase.capp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import java.lang.String;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;

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
                EditText text_dist = (EditText) findViewById(R.id.dist);

                // initialize variables to what was input in text boxes
                if(isEmpty(text_initial_vel)) {
                    double initial_vel = Double.parseDouble(text_initial_vel.getText().toString());
                }
                else
                {
                    double initial_vel = Double.NaN;
                }
                if(isEmpty(text_final_vel))
                {
                    double final_vel = Double.parseDouble(text_final_vel.getText().toString());
                }
                else
                {
                    double final_vel = Double.NaN;
                }
                if(isEmpty(text_accel))
                {
                    double accel = Double.parseDouble(text_accel.getText().toString());
                }
                else
                {
                    double accel = Double.NaN;
                }
                if(isEmpty(text_accel))
                {
                    double time = Double.parseDouble(text_time.getText().toString());
                }
                else
                {
                    double time = Double.NaN;
                }
                if(isEmpty(text_dist))
                {
                    double dist = Double.parseDouble(text_dist.getText().toString());
                }
                else
                {
                    double dist = Double.NaN;
                }
            }
        }
        );
    }




}
