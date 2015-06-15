package com.myapp.hubert.modernartui;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;


public class MainActivity extends Activity {

    TextView mRectangle1;
    TextView mRectangle2;
    TextView mRectangle3;
    SeekBar mSeekBar;
    Spinner mSpinner1;
    Spinner mSpinner2;
    Spinner mSpinner3;

    LinearLayout first_controls;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRectangle1 = (TextView) findViewById(R.id.rectangle_1);
        mRectangle2 = (TextView) findViewById(R.id.rectangle_2);
        mRectangle3 = (TextView) findViewById(R.id.rectangle_3);

        mSeekBar = (SeekBar) findViewById(R.id.seek_bar);

        mSpinner1 = (Spinner) findViewById(R.id.r_edit);
        mSpinner2 = (Spinner) findViewById(R.id.g_edit);
        mSpinner3 = (Spinner) findViewById(R.id.b_edit);
        mSpinner1.setAdapter(new myAdapter());
        mSpinner2.setAdapter(new myAdapter());
        mSpinner3.setAdapter(new myAdapter());

        first_controls = (LinearLayout) findViewById(R.id.first_controls);

        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int r = (int) (Math.random() * 256);
                int g = (int) (Math.random() * 256);
                int b = (int) (Math.random() * 256);
                mRectangle3.setBackgroundColor(Color.rgb(r, g, b));
            }
        });

        mSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                int rgbValue = (int) ((double) (100 - progress) / 100 * 255);
                mRectangle2.setBackgroundColor(Color.rgb(rgbValue, rgbValue, rgbValue));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

        mSpinner1.setOnItemSelectedListener(new spinnerListener());
        mSpinner2.setOnItemSelectedListener(new spinnerListener());
        mSpinner3.setOnItemSelectedListener(new spinnerListener());

        reset();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch(id) {
            case R.id.action_reset:
                reset();
                break;
            case R.id.action_more_info:
                new MOMADialogFragment().show(getFragmentManager(), "dialog");
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    private void reset() {
        mSpinner1.setSelection(255);
        mSpinner2.setSelection(0);
        mSpinner3.setSelection(0);

        mSeekBar.setProgress(50);

        mRectangle3.setBackgroundColor(Color.rgb(0,0,255));
    }

    class myAdapter extends BaseAdapter implements SpinnerAdapter {

        final int MAX_NUMBER = 255;

        @Override
        public int getCount() {
            return MAX_NUMBER + 1;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = getLayoutInflater();
            TextView temp = (TextView) inflater.inflate(android.R.layout.simple_spinner_item, first_controls, false);
            temp.setText(String.valueOf(position));
            temp.setTextColor(Color.rgb(0, 0, 0));
            temp.setGravity(Gravity.END);
            return temp;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public Object getItem(int position) {
            return position;
        }
    }

    class spinnerListener implements AdapterView.OnItemSelectedListener {
        @Override
        public void onNothingSelected(AdapterView<?> parent) {}

        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            int r = (int) mSpinner1.getSelectedItem();
            int g = (int) mSpinner2.getSelectedItem();
            int b = (int) mSpinner3.getSelectedItem();
            mRectangle1.setBackgroundColor(Color.rgb(r, g, b));
        }
    }
}
