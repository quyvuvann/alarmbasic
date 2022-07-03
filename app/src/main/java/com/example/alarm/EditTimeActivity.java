package com.example.alarm;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class EditTimeActivity extends AppCompatActivity {
    ImageView imgRightIcon, imgLeftIcon;
    TimePicker timePickerStart, timePickerEnd;
    EditText editTextTitle;
    @SuppressLint("UseSwitchCompatOrMaterialCode")
    Switch switchEditTime;
    Button btnMon, btnTue, btnWed, btnThu, btnFri, btnSat, btnSun;
    String dates = "";
    Boolean mSwitch;
    Alarm alarm= new Alarm();
    String type = "";
    DataBase dataBase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edittime);

        connect();
        setUp();

        Intent intent = getIntent();
        if (intent.hasExtra("type")) {
            type = intent.getStringExtra("type");
            if (type.equals("ADD")) {

            } else if (type.equals("EDIT")) {
                alarm = (Alarm) intent.getSerializableExtra("dataMain");
                String mTitle = alarm.getmTitle();
                Boolean m = alarm.getmSwitch();
                editTextTitle.setText(mTitle);
                switchEditTime.setChecked(m);
                setdrawble(alarm.getDate());
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    timePickerStart.setHour(alarm.getHourStartTime());
                    timePickerEnd.setHour(alarm.getHourEndTime());
                    timePickerStart.setMinute(alarm.getMinuteStartTime());
                    timePickerEnd.setMinute(alarm.getMinuteEndTime());
                } else {
                    timePickerStart.setCurrentHour(alarm.getHourStartTime());
                    timePickerEnd.setCurrentHour(alarm.getHourEndTime());
                    timePickerEnd.setCurrentMinute(alarm.getMinuteStartTime());
                    timePickerEnd.setCurrentMinute(alarm.getMinuteEndTime());
                    editTextTitle.setText(mTitle);
                    switchEditTime.setChecked(m);
                }
            } else {
                Toast.makeText(this, "Data type error!", Toast.LENGTH_SHORT).show();
            }
        }

    }

    // You can do the assignment inside onAttach or onCreate, i.e, before the activity is displayed


    private void getAlarm() {
        alarm.hourStartTime = timePickerStart.getCurrentHour();
        alarm.hourEndTime = timePickerEnd.getCurrentHour();
        alarm.minuteStartTime = timePickerStart.getCurrentMinute();
        alarm.minuteEndTime = timePickerEnd.getCurrentMinute();
        alarm.mTitle = editTextTitle.getText().toString();
        alarm.date = dates.toString();
        alarm.mSwitch = switchEditTime.isChecked();
//        dataBase = new DataBase(this);
//        dataBase.updateAlarm(alarm);

        nextActivity();
    }

    private void nextActivity() {
        Intent intent = new Intent(this, AlarmActivity.class);
        intent.putExtra("data", alarm);
        intent.putExtra("type", type);
        setResult(RESULT_OK, intent);

        finish();
    }

    private void setdrawble(String dates) {
        btnSun.setBackgroundResource(dates.equals("SUN") ? R.drawable.drawble_rank : R.drawable.custom_button_white);
        btnMon.setBackgroundResource(dates.equals("MON") ? R.drawable.drawble_rank : R.drawable.custom_button_white);
        btnTue.setBackgroundResource(dates.equals("TUE") ? R.drawable.drawble_rank : R.drawable.custom_button_white);
        btnWed.setBackgroundResource(dates.equals("WED") ? R.drawable.drawble_rank : R.drawable.custom_button_white);
        btnThu.setBackgroundResource(dates.equals("THU") ? R.drawable.drawble_rank : R.drawable.custom_button_white);
        btnFri.setBackgroundResource(dates.equals("FRI") ? R.drawable.drawble_rank : R.drawable.custom_button_white);
        btnSat.setBackgroundResource(dates.equals("SAT") ? R.drawable.drawble_rank : R.drawable.custom_button_white);
    }

    private void connect() {
        btnSun = (Button) findViewById(R.id.buttonSunday);
        btnMon = (Button) findViewById(R.id.buttonMonday);
        btnTue = (Button) findViewById(R.id.buttonTuesday);
        btnWed = (Button) findViewById(R.id.buttonWednesday);
        btnThu = (Button) findViewById(R.id.buttonThursday);
        btnFri = (Button) findViewById(R.id.buttonFriday);
        btnSat = (Button) findViewById(R.id.buttonSaturday);
        editTextTitle = (EditText) findViewById(R.id.editTextTime);
        switchEditTime = (Switch) findViewById(R.id.switchEditTime);
        timePickerEnd = (TimePicker) findViewById(R.id.timePickerEnd);
        timePickerStart = (TimePicker) findViewById(R.id.timePickerStart);
        imgLeftIcon = (ImageView) findViewById(R.id.left_icon);
        imgRightIcon = (ImageView) findViewById(R.id.right_icon);

    }

    public void setUp() {
        // set up all conection to view
        btnSun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dates = "SUN";
                setdrawble(dates);
                // btnSun.setBackgroundResource(R.drawable.custom_button);
                //btnSun.setEnabled(true);
            }
        });
        btnMon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dates = "MON";
                setdrawble(dates);
                Log.d("AAA", dates);
            }
        });
        btnTue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dates = "TUE";
                setdrawble(dates);
            }
        });
        btnWed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dates = "WED";
                setdrawble(dates);
            }
        });
        btnThu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dates = "THU";
                setdrawble(dates);
            }
        });
        btnFri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dates = "FRI";
                setdrawble(dates);
            }
        });
        btnSat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dates = "SAT";
                setdrawble(dates);
            }
        });
//        String mangbtn[] = {"btnMon","btnTues","btnWednes","btnThurs","btnFri","btnSatur","btnSun"};
//        for(int i= 0;i<=6;i++){
//            if()
//        }
        switchEditTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alarm.mSwitch = switchEditTime.isChecked();
            }
        });

        imgRightIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                getAlarm();

                //dataBase.getAllNotes();

            }
        });
        switchEditTime.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (switchEditTime.isChecked()) {
                    //mSwitch = true;
                    Toast.makeText(EditTimeActivity.this, "true", Toast.LENGTH_SHORT).show();
                } else {
                    //mSwitch = false;
                    Toast.makeText(EditTimeActivity.this, "false", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}