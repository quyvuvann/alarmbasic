package com.example.alarm;

import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import java.util.Calendar;
import java.util.List;

public class AlarmAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private List<Alarm> clockList;

    public AlarmAdapter(Context context, int layout, List<Alarm> clockList) {
        this.context = context;
        this.layout = layout;
        this.clockList = clockList;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(AlarmActivity context) {
        this.context = context;
    }

    public int getLayout() {
        return layout;
    }

    public void setLayout(int layout) {
        this.layout = layout;
    }

    public List<Alarm> getOclockList() {
        return clockList;
    }

    public void setOclockList(List<Alarm> clockList) {
        this.clockList = clockList;
    }

    @Override
    public int getCount() {
        return clockList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    private class Viewholder {
        ImageView view, imgEdit, imgDel;
        TextView txttittle;
        TextView txtHourStartTime, txtMinuteStartTime, txtHourEndTime, txtMinuteEndTime;
        TextView txtMon, txtTue, txtWed, txtThu, txtFri, txtSat, txtSun;
        Switch aSwitch;
        PendingIntent pendingIntent;
        Calendar calendar;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Viewholder viewholder;
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(layout, null);
            viewholder = new Viewholder();

            viewholder.imgEdit = (ImageView) convertView.findViewById(R.id.imageViewEdit);
            viewholder.view = (ImageView) convertView.findViewById(R.id.imageview);
            viewholder.txttittle = (TextView) convertView.findViewById(R.id.textview);
            viewholder.txtHourStartTime = (TextView) convertView.findViewById(R.id.hourStartTime);
            viewholder.txtHourEndTime = (TextView) convertView.findViewById(R.id.hourEndTime);
            viewholder.txtMinuteStartTime = (TextView) convertView.findViewById(R.id.minuteStartTime);
            viewholder.txtMinuteEndTime = (TextView) convertView.findViewById(R.id.minuteEndTime);
            viewholder.txtMon = (TextView) convertView.findViewById(R.id.day2);
            viewholder.txtTue = (TextView) convertView.findViewById(R.id.day3);
            viewholder.txtWed = (TextView) convertView.findViewById(R.id.day4);
            viewholder.txtThu = (TextView) convertView.findViewById(R.id.day5);
            viewholder.txtFri = (TextView) convertView.findViewById(R.id.day6);
            viewholder.txtSat = (TextView) convertView.findViewById(R.id.day7);
            viewholder.txtSun = (TextView) convertView.findViewById(R.id.day1);
            viewholder.aSwitch = (Switch) convertView.findViewById(R.id.aswitch);
            viewholder.imgDel = (ImageView) convertView.findViewById(R.id.imageViewDeltete);
            viewholder.calendar = Calendar.getInstance();
            convertView.setTag(viewholder);
        } else {
            viewholder = (Viewholder) convertView.getTag();
        }

        Alarm clock = clockList.get(position);
        DataBase dataBase = new DataBase(context);

        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(context, AlarmReceiver.class);

//         viewholder.pendingIntent = PendingIntent.getBroadcast(context, 0,intent, 0);
//
//        alarmManager.set(AlarmManager.RTC_WAKEUP,clock.getMinuteEndTime(), viewholder.pendingIntent);

        viewholder.imgEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, EditTimeActivity.class);
                intent.putExtra("dataMain", clock);
                intent.putExtra("type", "EDIT");
                ((AlarmActivity) context).someActivityResultLauncher.launch(intent);
            }
        });

        viewholder.imgDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                        dataBase.Delete(clock);
//                        clockList.remove(clock);
//                        notifyDataSetChanged();
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setMessage("Bạn muốn xóa đồng hồ " + viewholder.txttittle.getText().toString() + " không ?");
                builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dataBase.Delete(clock);
                        clockList.remove(clock);
                        notifyDataSetChanged();
                    }
                });
                builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                builder.show();
            }
        });

//        viewholder.view.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//                ((AlarmActivity) context).someActivityResultLauncher.launch(intent);
//            }
//        });


        viewholder.view.setImageResource(clock.getImageView());
        viewholder.txttittle.setText(clock.getmTitle());
        viewholder.txtHourStartTime.setText(clock.getHourStartTime() + "");
        viewholder.txtHourEndTime.setText(clock.getHourEndTime() + "");
        viewholder.txtMinuteStartTime.setText(clock.getMinuteStartTime() + "");
        viewholder.txtMinuteEndTime.setText(clock.getMinuteEndTime() + "");
        //viewholder.aSwitch.setChecked(clock.getmSwitch());
        viewholder.aSwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (clock.mSwitch = viewholder.aSwitch.isChecked()){

                    AlarmManager manager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
//                int interval = clockList.get(position).getMinuteEndTime();
//
//                manager.setInexactRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), interval, viewholder.pendingIntent);
//                Toast.makeText(context, "Alarm Set", Toast.LENGTH_SHORT).show();
                    viewholder.calendar.set(Calendar.HOUR_OF_DAY, clock.getHourEndTime());
                    viewholder.calendar.set(Calendar.MINUTE, clock.getMinuteEndTime());
//                    int gio = clockList.get(position).getHourEndTime();
//                    int phut = clockList.get(position).getMinuteEndTime();
//
//                    String string_gio = String.valueOf(gio);
//                    String string_phut = String.valueOf(phut);
                    PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
                     intent.putExtra("extra","on");
                    alarmManager.set(AlarmManager.RTC_WAKEUP, viewholder.calendar.getTimeInMillis(), pendingIntent);
                }
                else{
                    PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
                   alarmManager.cancel(pendingIntent);
                   intent.putExtra("extra","off");
                }
            }
        });
        viewholder.aSwitch.setChecked(clock.getmSwitch());
        setTextStyle(viewholder.txtSun, clock.date.equalsIgnoreCase("sun"));
        setTextStyle(viewholder.txtMon, clock.date.equalsIgnoreCase("mon"));
        setTextStyle(viewholder.txtTue, clock.date.equalsIgnoreCase("tue"));
        setTextStyle(viewholder.txtWed, clock.date.equalsIgnoreCase("wed"));
        setTextStyle(viewholder.txtThu, clock.date.equalsIgnoreCase("thu"));
        setTextStyle(viewholder.txtFri, clock.date.equalsIgnoreCase("fri"));
        setTextStyle(viewholder.txtSat, clock.date.equalsIgnoreCase("sat"));

        return convertView;

    }

    void setTextStyle(TextView textView, boolean isSelected) {
        if (isSelected) {
            textView.setTextColor(context.getResources().getColor(R.color.purple_700));
            textView.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
        } else {
            textView.setTextColor(context.getResources().getColor(R.color.black));
            textView.setTypeface(Typeface.DEFAULT);
        }
    }


}
