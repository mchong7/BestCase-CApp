package bestcase.capp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class WelcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        Button go = (Button) findViewById(R.id.getstarted);
        Button instruct_button = (Button) findViewById(R.id.instructions);
        if (go != null) {
            go.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    Intent intent = new Intent(WelcomeActivity.this, DataInput.class);
                    startActivity(intent);
                }
            });
        }
        if (instruct_button != null) {
            instruct_button.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    Intent intent = new Intent(WelcomeActivity.this, Instructions.class);
                    startActivity(intent);
                }
            });
        }
    }
}
