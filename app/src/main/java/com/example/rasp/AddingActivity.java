package com.example.rasp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NavUtils;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;

public class AddingActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    TimePicker tpAdd;
    TextView tvTime;
    Spinner spinnerWeek;
    Button btnAdd;

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setMessage("Are you sure you want to exit?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        AddingActivity.this.finish();
                    }
                })
                .setNegativeButton("No", null)
                .show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adding);

//      It is onBackPressed on action bar(getSupportActionBar().setDisplayHomeAsUpEnabled(true))
        exitAddActy();


        //It's AutoCompleteTextView for adding subjects
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, SUBJECTS);
        AutoCompleteTextView textView = (AutoCompleteTextView)
                findViewById(R.id.AuTvSub);
        textView.setAdapter(adapter);


        // Spinner
        spinnerWeek = (Spinner) findViewById(R.id.spinnerWeek);
        ArrayAdapter<CharSequence> adapterSpinner = ArrayAdapter.createFromResource(this,
                R.array.week, android.R.layout.simple_spinner_item);
        adapterSpinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerWeek.setAdapter(adapterSpinner);
        spinnerWeek.setOnItemSelectedListener(this);


        btnAdd = (Button) findViewById(R.id.btnAdd);
        tvTime = (TextView) findViewById(R.id.tvTime);
        tpAdd = (TimePicker) findViewById(R.id.tpAdd);
        tpAdd.setIs24HourView(false); //if true, it means there is 24 hours. But false means 12 hours with am and pm
        tpAdd.setEnabled(true); //if true, it means we can touch it, false means the opposite
        Calendar now = Calendar.getInstance();
        tpAdd.setCurrentHour(now.get(Calendar.HOUR_OF_DAY));
        tpAdd.setCurrentMinute(now.get(Calendar.MINUTE));




        // the chose time is showed on textview
        tpAdd.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                tvTime.setText("Hour: " + hourOfDay + "\n" + "Minutes: "
                        + minute + "\n");
            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }

    //It adds automatically these words
    private static final String[] SUBJECTS = new String[] {
            "Math", "English language", "Physics", "Chemistry", "History"
    };

    //Clicking on week items
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }
    //nothing clicking in week item
    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    // options for item backPressed on action bar
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:

                return super.onOptionsItemSelected(item);
        }
    }

    // onBackPressed on action bar
    private void exitAddActy () {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

}
