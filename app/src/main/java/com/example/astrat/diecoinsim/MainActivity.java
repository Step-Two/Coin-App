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
    private int mDieSides = 2;
    private int mDieQuantity = 1;
    private boolean mIsFlipped = false;
    private boolean mSwitchClick = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        TextView tv = findViewById(R.id.dieSelection);
        tv.setText(Integer.toString(mButtonPresses) + "d" + Integer.toString(mDieSides));

        final Button button = findViewById(R.id.screenButton);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(!mSwitchClick){
                    if(!mIsFlipped)
                        mDieQuantity++;
                    mIsFlipped = false;
                    if(mDieQuantity >= 7)
                        mDieQuantity = 1;
                    // Code here executes on main thread after user presses button
                    TextView tv = findViewById(R.id.dieSelection);
                    tv.setText(Integer.toString(mDieQuantity) + "d" + Integer.toString(mDieSides));
                }
                if(mSwitchClick) {
                    if(!mIsFlipped)
                        mDieSides = mDieSides+2;
                    mIsFlipped=false;
                    if(mDieSides > 20)
                        mDieSides = 2;
                    // Code here executes on main thread after user presses button
                    TextView tv = findViewById(R.id.dieSelection);
                    tv.setText(Integer.toString(mDieQuantity) + "d" + Integer.toString(mDieSides));
                }
            }
        });
        button.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                mSwitchClick = !mSwitchClick;
                return true;
            }
        });


        final Button flipbutton = findViewById(R.id.flipButton);
        flipbutton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Code here executes on main thread after user presses button
                TextView tv = findViewById(R.id.dieSelection);

                String disp = Integer.toString(mDieQuantity) + "d" + Integer.toString(mDieSides) + "\n" ;
                int tot = 0;
                for(int ind = 0; ind <  mDieQuantity; ind++)
                {
                    int val = (int)(1+Math.random()*mDieSides);
                    tot += val;
                    disp += Integer.toString(val);
                    if(ind < mDieQuantity - 1)
                        disp += ", ";
                }
                disp += "\nTotal: ";
                disp += Integer.toString(tot);


                tv.setText(disp);
                mIsFlipped=true;
                mSwitchClick=false;
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


}
