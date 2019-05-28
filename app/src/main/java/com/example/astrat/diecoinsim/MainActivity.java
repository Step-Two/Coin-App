package com.example.astrat.diecoinsim;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import static android.provider.AlarmClock.EXTRA_MESSAGE;

public class MainActivity extends AppCompatActivity {
    public static final String EXTRA_MESSAGE = "com.example.astrat.diecoinsim.MESSAGE";
    private int mButtonPresses = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        TextView tv = findViewById(R.id.dieSelection);
        tv.setText(Integer.toString(mButtonPresses) + "d6");

        final Button button = findViewById(R.id.screenButton);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mButtonPresses++;
                if(mButtonPresses >= 7)
                    mButtonPresses = 1;
                // Code here executes on main thread after user presses button
                TextView tv = findViewById(R.id.dieSelection);
                tv.setText(Integer.toString(mButtonPresses) + "d6");



            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /** Called when the user taps the Send button */
    public void sendMessage(View view) {
        // Do something in response to button
        Intent intent = new Intent(this, DisplayFlipActivity.class);
        //EditText editText = (EditText) findViewById(R.id.flipResult);
        //String message = editText.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, mButtonPresses);
        startActivity(intent);

    }

}
