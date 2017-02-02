package bestcase.capp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

public class DisplayResults extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_results);
        Intent intent = getIntent();
        Bundle bundle = getIntent().getExtras();
        String velocity = bundle.getString("velocity");
        TextView textView = new TextView(this);
        textView.setTextSize(40);
        textView.setText(velocity);


    }
    public void goBack(View view) {
        Intent intent = new Intent(this, DataInput.class);
        startActivity(intent);
        }

}
