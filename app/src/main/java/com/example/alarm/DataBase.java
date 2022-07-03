package com.example.alarm;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class DataBase extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "Alarm";

    // Table name: Note.
    private static final String TABLE_ALARM = "Alarm";

    private static final String COLUMN_ALARM_ID ="ID";
    private static final String COLUMN_AlARM_IMAGE ="Alarm_Image";
    private static final String COLUMN_ALARM_TITLE = "Alarm_tittle";
    private static final String COLUMN_ALARM_MSWITCH = "Alarm_switch";
    private static final String COLUMN_ALARM_HOURSTARTTIME = "Alarm_HourStartTime";
    private static final String COLUMN_ALARM_HOURENDTIME = "Alarm_HourEndTime";
    private static final String COLUMN_ALARM_MINUTESTARTTIME = "Alarm_MinuteStartTime";
    private static final String COLUMN_ALARM_MINUTEENDTIME = "Alarm_MinuteEndTime";
    private static final String COLUMN_ALARM_DATE = "Alarm_Date";

  //String sql2 = "CREATE TABLE Alarm(ID INTEGER PRIMARY KEY,Alarm_Image INTEGER,Alarm_tittle TEXT,Alarm_switch INTEGER,Alarm_HourStartTimeINTEGER,Alarm_HourEndTimeINTEGER,Alarm_MinuteStartTimeINTEGER,Alarm_MinuteEndTimeINTEGER,Alarm_DateTEXT)"

    //private String SQLiteQuery = "CREATE TABLE IF NOT EXISTS Alarm(ID INTEGER PRIVATE KEY AUTOINCREMENT,Image INTEGER,Mtitle VARCHAR(50),Mswitch BIT,HourStartTime INTEGER,HourEndTime INTEGER,MinuteStartTime INTEGER,MinuteEndTime INTEGER";

    public DataBase(Context context)  {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }



    @Override
    public void onCreate(SQLiteDatabase db) {
        String alarmSQL = "CREATE TABLE " + TABLE_ALARM + "("
                + COLUMN_ALARM_ID + " INTEGER PRIMARY KEY," + COLUMN_AlARM_IMAGE + " INTEGER,"
                + COLUMN_ALARM_TITLE + " TEXT,"  + COLUMN_ALARM_MSWITCH + " INTEGER,"
                + COLUMN_ALARM_HOURSTARTTIME + " INTEGER," +COLUMN_ALARM_HOURENDTIME +" INTEGER,"
                + COLUMN_ALARM_MINUTESTARTTIME+" INTEGER,"+COLUMN_ALARM_MINUTEENDTIME+" INTEGER,"
                + COLUMN_ALARM_DATE+" TEXT" +")";

        Log.d("AAA", alarmSQL );
        db.execSQL(alarmSQL);
    }

    public void addNote(Alarm alarm) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_AlARM_IMAGE, R.drawable.ic_baseline_watch_later_24);
        values.put(COLUMN_ALARM_TITLE,alarm.getmTitle());
        values.put(COLUMN_ALARM_MSWITCH,alarm.getmSwitch()?1:0);
        values.put(COLUMN_ALARM_HOURSTARTTIME,alarm.getHourStartTime());
        values.put(COLUMN_ALARM_HOURENDTIME,alarm.getHourEndTime());
        values.put(COLUMN_ALARM_MINUTESTARTTIME,alarm.getMinuteStartTime());
        values.put(COLUMN_ALARM_MINUTEENDTIME,alarm.getMinuteEndTime());
        values.put(COLUMN_ALARM_DATE,alarm.getDate());
        db.insert(TABLE_ALARM,null,values);
        db.close();
    }
    public List<Alarm> getAllNotes() {

        List<Alarm> alarmList = new ArrayList<Alarm>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_ALARM;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Alarm alarm = new Alarm();
//                note.setNoteId(Integer.parseInt(cursor.getString(0)));
//                note.setNoteTitle(cursor.getString(1));
//                note.setNoteContent(cursor.getString(2));

                alarm.setId(cursor.getInt(0));
                alarm.setImageView(cursor.getInt(1));
                alarm.setmTitle(cursor.getString(2));
                alarm.setmSwitch(cursor.getInt(3) ==1 ? true : false);

                alarm.setHourStartTime(cursor.getInt(4));
                alarm.setHourEndTime(cursor.getInt(5));
                alarm.setMinuteStartTime(cursor.getInt(6));
                alarm.setMinuteEndTime(cursor.getInt(7));
                alarm.setDate(cursor.getString(8));
                // Adding note to list
                alarmList.add(alarm);
            } while (cursor.moveToNext());
        }

        // return note list
        return alarmList;
    }

    public void updateAlarm(Alarm alarm) {
        //Log.i(TAG, "MyDatabaseHelper.updateNote ... "  + note.getNoteTitle());

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_AlARM_IMAGE,alarm.getImageView());
        values.put(COLUMN_ALARM_TITLE,alarm.getmTitle());
        values.put(COLUMN_ALARM_MSWITCH,alarm.getmSwitch() ? 1 : 0);
        values.put(COLUMN_ALARM_HOURSTARTTIME,alarm.getHourStartTime());
        values.put(COLUMN_ALARM_HOURENDTIME,alarm.getHourEndTime());
        values.put(COLUMN_ALARM_MINUTESTARTTIME,alarm.getMinuteStartTime());
        values.put(COLUMN_ALARM_MINUTEENDTIME,alarm.getMinuteEndTime());
        values.put(COLUMN_ALARM_DATE,alarm.getDate());

        // updating row
        db.update(TABLE_ALARM, values, COLUMN_ALARM_ID + " = ?",
                new String[]{String.valueOf(alarm.getId())});
    }


    public void Delete(Alarm alarm){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        sqLiteDatabase.delete(TABLE_ALARM,COLUMN_ALARM_ID + "= ?" ,new String[]{String.valueOf(alarm.getId())});
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sqldrop = "DROP TABLE " + TABLE_ALARM;
        String alarmSQL = "CREATE TABLE " + TABLE_ALARM + "("
                + COLUMN_ALARM_ID + " INTEGER PRIMARY KEY," + COLUMN_AlARM_IMAGE + " INTEGER,"
                + COLUMN_ALARM_TITLE + " TEXT,"  + COLUMN_ALARM_MSWITCH + " INTEGER,"
                + COLUMN_ALARM_HOURSTARTTIME + " INTEGER," +COLUMN_ALARM_HOURENDTIME +" INTEGER,"
                + COLUMN_ALARM_MINUTESTARTTIME+" INTEGER,"+COLUMN_ALARM_MINUTEENDTIME+" INTEGER,"
                + COLUMN_ALARM_DATE+" TEXT" +")";
                Log.d("BBB", alarmSQL );
        db.execSQL(sqldrop);
        db.execSQL(alarmSQL);
    }
}
