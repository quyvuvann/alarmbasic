package com.example.alarm;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ListView;
import android.widget.SearchView;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class AlarmActivity extends AppCompatActivity {
    ListView listView;
    List<Alarm> clockList;
    AlarmAdapter alarmAdapter;
    FloatingActionButton floatingActionButton;
    DataBase dataBase;
    SearchView searchView;

    //EditTimeActivity editTimeActivity = new EditTimeActivity() ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm);

        searchView = (SearchView) findViewById(R.id.searchView);
        listView = (ListView) findViewById(R.id.idlistview);
        floatingActionButton = (FloatingActionButton) findViewById(R.id.floatingbutton);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AlarmActivity.this, EditTimeActivity.class);
                intent.putExtra("type", "ADD");
                someActivityResultLauncher.launch(intent);
            }
        });
        clockList = new ArrayList<>();
        dataBase = new DataBase(this);
        List<Alarm> list = dataBase.getAllNotes();
        this.clockList.addAll(list);

//        clockList.add(new Clock(R.drawable.ic_android_black_24dp, alarm.getmTitle().toString(), alarm.getHourStartTime() + "", alarm.getHourEndTime() + ""));

//        clockList.add(new Alarm(0, R.drawable.ic_android_black_24dp, "image1", false, 7, 9, 0, 0, "SUN"));
//        clockList.add(new Alarm(1, R.drawable.ic_android_black_24dp, "image2", false, 5, 7, 0, 0, "MON"));
//        clockList.add(new Alarm(2, R.drawable.ic_android_black_24dp, "image3", true, 7, 12, 0, 0, "TUE"));
//        clockList.add(new Alarm(3, R.drawable.ic_android_black_24dp, "image4", false, 7, 3, 0, 0, "SAT"));

//        clockList.add(new Clock(R.drawable.ic_baseline_pedal_bike_24, "hinh2", "07:00", "12:00"));
//        clockList.add(new Clock(R.drawable.ic_baseline_ev_station_24, "hinh3", "05:00", "07:00"));
//        clockList.add(new Clock(R.drawable.ic_baseline_redeem_24, "hinh4", "00:00", "03:00"));
        alarmAdapter = new AlarmAdapter(this, R.layout.adapter_view, clockList);
        listView.setAdapter(alarmAdapter);
//        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String query) {
//                if(clockList.contains(query)){
//                   // alarmAdapter.ge
//                }
//            }
//
//            @Override
//            public boolean onQueryTextChange(String newText) {
//                return false;
//            }
//        });
//        dataBase = new DataBase(this, "Alarm.sqlite", null, 1);
//        dataBase.QueryData("CREATE TABLE IF NOT EXISTS Alarm(ID INTEGER PRIVATE KEY AUTOINCREMENT,Image INTEGER,Mtitle VARCHAR(50),Mswitch NUMERIC,HourStartTime INTEGER,HourEndTime INTEGER,MinuteStartTime INTEGER,MinuteEndTime INTEGER,Date VARCHAR)");
//        Cursor cursor =dataBase.Getdata("SSELECT * FROM Alarm");
//        while(cursor.moveToNext()){
//           // clockList.add(new Alarm(cursor.getInt(0),cursor.getInt(1),cursor.getString(2),cursor.getInt(3),cursor.getInt(4),cursor.getInt(5),cursor.getInt(6),cursor.getString(7)));
//        }
   }

//    public void GetData() {
//        Cursor dataAlarm = dataBase.Getdata("SELECT * FROM Alarm");
//        clockList.clear();
//        while (dataAlarm.moveToNext()) {
//            int image = dataAlarm.getInt(1);
//            String mtitle = dataAlarm.getString(2);
//            Boolean mswitch = dataAlarm.
//            int id = dataAlarm.getInt(0);
//        }
//    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_adapter, menu);
        return super.onCreateOptionsMenu(menu);
    }

    ActivityResultLauncher<Intent> someActivityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        // There are no request codes
                        Intent data = result.getData();
                        Alarm alarm = (Alarm) data.getSerializableExtra("data");

                        String type = data.getStringExtra("type");
                        if (type.equals("ADD")) {


                            //clockList.add(new Alarm(clockList.size(), R.drawable.ic_android_black_24dp, alarm.getmTitle(), alarm.mSwitch, alarm.getHourStartTime(), alarm.getHourEndTime(), alarm.getMinuteStartTime(), alarm.getMinuteEndTime(), alarm.getDate()));
                            dataBase = new DataBase(AlarmActivity.this);
                            dataBase.addNote(alarm);
                            clockList.add(alarm);
                            alarmAdapter.notifyDataSetChanged();

//                            InputStream inputStream = getContentResolver().openInputStream(uri);
//                            Bitmap bitmap = BitmapFactory.decodeStream(inputStream);

                        }
                        else{
                            int id = alarm.getId();
                            for (int i = 0; i < clockList.size(); i++) {
                                if (clockList.get(i).getId() == id) {

//                                Toast.makeText(AlarmActivity.this, "test" + alarm.getId(), Toast.LENGTH_SHORT).show();

                                    dataBase = new DataBase(AlarmActivity.this);
                                    dataBase.updateAlarm(alarm);
                                    clockList.set(i, alarm);
                                    alarmAdapter.notifyDataSetChanged();
                                }
                            }
                        }
//                        Toast.makeText(AlarmActivity.this, alarm.getDate()+"test", Toast.LENGTH_SHORT).show();
                        //  Log.d("vinh", alarm.toString());

                    }
                }
            });

}