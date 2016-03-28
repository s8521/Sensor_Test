package com.example.administrator.sensor_test;

import java.util.List;
import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.TextView;

//public class MainActivity extends AppCompatActivity {
    public class MainActivity extends Activity  implements SensorEventListener{
    private TextView lab_X;
    private TextView lab_Y;
    private TextView lab_Z;

    private SensorManager sensorManager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        FindViews();
        sensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
    }

    @Override
    protected void onResume() {
        super.onResume();
        SetSensor();
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
    }

    @Override
    protected void onPause() {
        super.onPause();
        //解除感應器註冊
        sensorManager.unregisterListener(this);
    }

    private void FindViews()
    {
        lab_X = (TextView) this.findViewById(R.id.lab_X);
        lab_Y = (TextView) this.findViewById(R.id.lab_Y);
        lab_Z = (TextView) this.findViewById(R.id.lab_Z);
    }

    protected void SetSensor()
    {
        List sensors = sensorManager.getSensorList(Sensor.TYPE_ACCELEROMETER);
        //如果有取到該手機的方位感測器，就註冊他。
        if (sensors.size()>0)
        {
            //registerListener必須要implements SensorEventListener，
            //而SensorEventListener必須實作onAccuracyChanged與onSensorChanged
            //感應器註冊
            sensorManager.registerListener(this, sensors.get(0), SensorManager.SENSOR_DELAY_NORMAL);
        }
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        // TODO Auto-generated method stub
        float[] values = event.values;
        lab_X.setText("X：" + String.valueOf(values[0]));
        lab_Y.setText("Y：" + String.valueOf(values[1]));
        lab_Z.setText("Z：" + String.valueOf(values[2]));
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // TODO Auto-generated method stub
    }
}
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//
//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
//    }
//
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_main, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }
}
