package com.example.dayanandakishore.trial1;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;
import java.util.Calendar;

public class MainActivity_settings extends AppCompatActivity {


    /*Pending intent allows other applications to use the permission of your application*/

    int a = 19, b = 38;
    /*Change these variables in order to change the time for which alarm and notifications are applied*/
    TextView t;
    Spinner spinner;
    ArrayAdapter<CharSequence> adapter;
    Spinner spinner2;
    ArrayAdapter<CharSequence> adapter2;
    Spinner spinner3;
    ArrayAdapter<CharSequence> adapter3;
    Spinner spinner4;
    ArrayAdapter<CharSequence> adapter4;
    ToggleButton alarm_b;
    ToggleButton notifications;

    String p7 = Integer.toString(0);

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_settings);

        onClickListener();
        onNotificationListener();
        SharedPreferences s = getSharedPreferences("MyData", Context.MODE_PRIVATE);

        ToggleButton b1 = (ToggleButton)findViewById(R.id.Alarm_button);
        ToggleButton n1 = (ToggleButton)findViewById(R.id.notifications_button);
        String n = s.getString("boolean","false");

        if(n.equals("true")) {
            b1.setChecked(true);
        }
        else {
            b1.setChecked(false);
        }


        String p = s.getString("boolean2","false");
        if(p.equals("true")) {
            n1.setChecked(true);
        }
        else {
            n1.setChecked(false);
        }





    }

    public void onClickListener() {
        ToggleButton alarm_b = (ToggleButton) findViewById(R.id.Alarm_button);
        ToggleButton notifications = (ToggleButton) findViewById(R.id.notifications_button);
        alarm_b.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                long time;
                if (isChecked) {
                    SharedPreferences s = getSharedPreferences("MyData", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = s.edit();
                    editor.putString("boolean","true");
                    editor.apply();
                    TextView t = (TextView) findViewById(R.id.alarm_text);
                    t.setVisibility(View.VISIBLE);
                    String z1 = s.getString("spinner", p7);
                    int a1 = Integer.parseInt(z1);
                    spinner = (Spinner) findViewById(R.id.spinner);
                    spinner.setVisibility(View.VISIBLE);
                    adapter = ArrayAdapter.createFromResource(MainActivity_settings.this, R.array.Timings, android.R.layout.simple_spinner_item);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinner.setAdapter(adapter);
                    spinner.setSelection(a1);

                    // Spinner 1 is just about the timings before which the alarm should be triggered//
                    spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            //  b = b + position * 5 + 5;
                           // Toast.makeText(getBaseContext(), "The alarm is going to trigger at " + a + ":" + b, Toast.LENGTH_LONG).show();
                            SharedPreferences sp = getSharedPreferences("MyData", Context.MODE_PRIVATE);
                            SharedPreferences.Editor editor = sp.edit();
                            String pos1 = Integer.toString(position);
                            editor.putString("spinner",pos1);
                            editor.apply();


                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });
                    // Spinner 1 and adapter 1 is completed

                    //Spinner 2 -> Do you need notifications for every event//
                    TextView t1 = (TextView) findViewById(R.id.alarm_text2);
                    t1.setVisibility(View.VISIBLE);
                    spinner2 = (Spinner) findViewById(R.id.spinner2);
                    spinner2.setVisibility(View.VISIBLE);
                    String z2 = s.getString("spinner2",p7);
                    int a2 = Integer.parseInt(z2);
                    adapter2 = ArrayAdapter.createFromResource(MainActivity_settings.this, R.array.Timings2, android.R.layout.simple_spinner_item);
                    adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinner2.setAdapter(adapter2);
                    spinner2.setSelection(a2);
                    spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                           // Toast.makeText(getBaseContext(), parent.getItemAtPosition(position) + "   selected", Toast.LENGTH_LONG).show();
                            SharedPreferences s = getSharedPreferences("MyData", Context.MODE_PRIVATE);
                            SharedPreferences.Editor editor = s.edit();
                            String pos2 = Integer.toString(position);
                            editor.putString("spinner2",pos2);
                            editor.apply();

                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });
                    } else {
                    Toast.makeText(MainActivity_settings.this, "ALARM OFF", Toast.LENGTH_SHORT).show();
                    TextView t = (TextView) findViewById(R.id.alarm_text);
                    t.setVisibility(View.GONE);
                    spinner = (Spinner) findViewById(R.id.spinner);
                    spinner.setVisibility(View.GONE);
                    TextView t1 = (TextView) findViewById(R.id.alarm_text2);
                    t1.setVisibility(View.GONE);
                    spinner2 = (Spinner) findViewById(R.id.spinner2);
                    spinner2.setVisibility(View.GONE);

                    SharedPreferences s = getSharedPreferences("MyData", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = s.edit();
                    editor.putString("boolean", "false");
                    editor.apply();

                }

            }
        });
    }

    public void onNotificationListener() {
        ToggleButton n = (ToggleButton) findViewById(R.id.notifications_button);
        n.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                long time;
                if (isChecked) {
                    Toast.makeText(MainActivity_settings.this, "Notifications On", Toast.LENGTH_LONG).show();
                    Calendar calendar = Calendar.getInstance();
                    calendar.set(Calendar.HOUR_OF_DAY, a);
                    calendar.set(Calendar.MINUTE, b);
                    calendar.set(Calendar.SECOND, 0);
                    SharedPreferences s = getSharedPreferences("MyData", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = s.edit();
                    editor.putString("boolean2", "true");
                    editor.apply();

                    TextView t2 = (TextView) findViewById(R.id.notify_text1);
                    t2.setVisibility(View.VISIBLE);
                    spinner3 = (Spinner) findViewById(R.id.spinner3);
                    spinner3.setVisibility(View.VISIBLE);
                    String z3 = s.getString("spinner3",p7);
                    int a3 = Integer.parseInt(z3);
                    adapter3 = ArrayAdapter.createFromResource(MainActivity_settings.this, R.array.Timings3, android.R.layout.simple_spinner_item);
                    adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinner3.setAdapter(adapter3);
                    spinner3.setSelection(a3);
                    spinner3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            Toast.makeText(getBaseContext(), parent.getItemAtPosition(position) + "  selected", Toast.LENGTH_LONG).show();
                            SharedPreferences s = getSharedPreferences("MyData", Context.MODE_PRIVATE);
                            SharedPreferences.Editor editor = s.edit();
                            String pos3 = Integer.toString(position);
                            editor.putString("spinner3", pos3);
                            editor.apply();

                        }


                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });
                    TextView t3 = (TextView) findViewById(R.id.notify_text2);
                    t3.setVisibility(View.VISIBLE);
                    spinner4 = (Spinner) findViewById(R.id.spinner4);
                    spinner4.setVisibility(View.VISIBLE);

                    adapter4 = ArrayAdapter.createFromResource(MainActivity_settings.this, R.array.Timings4, android.R.layout.simple_spinner_item);
                    adapter4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                    String z4 = s.getString("spinner4",p7);
                    int a4 = Integer.parseInt(z4);
                    spinner4.setAdapter(adapter4);
                    spinner4.setSelection(a4);
                    spinner4.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            // Toast.makeText(getBaseContext(),parent.getItemAtPosition(position)+"   selected",Toast.LENGTH_LONG).show();
                            SharedPreferences s = getSharedPreferences("MyData", Context.MODE_PRIVATE);
                            SharedPreferences.Editor editor = s.edit();
                            String pos4 = Integer.toString(position);
                            editor.putString("spinner4", pos4);
                            editor.apply();

                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });
                }
                      /*Here request code should be unique for different receivers so that they will not override each other*/
                   else {
                    Toast.makeText(MainActivity_settings.this, "Notifications OFF", Toast.LENGTH_SHORT).show();
                    TextView t2 = (TextView) findViewById(R.id.notify_text1);
                    t2.setVisibility(View.GONE);
                    spinner3 = (Spinner) findViewById(R.id.spinner3);
                    spinner3.setVisibility(View.GONE);
                    TextView t3 = (TextView) findViewById(R.id.notify_text2);
                    t3.setVisibility(View.GONE);
                    spinner4 = (Spinner) findViewById(R.id.spinner4);
                    spinner4.setVisibility(View.GONE);
                    SharedPreferences s = getSharedPreferences("MyData", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = s.edit();
                    editor.putString("boolean2", "false");
                    editor.apply();

                }


            }

        });
    }

}


