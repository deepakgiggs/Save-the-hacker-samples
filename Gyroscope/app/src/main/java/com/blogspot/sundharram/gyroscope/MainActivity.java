package com.blogspot.sundharram.gyroscope;

        import android.app.Activity;
        import android.hardware.Sensor;
        import android.hardware.SensorEvent;
        import android.hardware.SensorEventListener;
        import android.hardware.SensorManager;
        import android.os.Bundle;
        import android.widget.TextView;

public class MainActivity extends Activity implements SensorEventListener
{
    //a TextView
    private TextView tv;
    //the Sensor Manager
    private SensorManager sManager;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //get the TextView from the layout file
        tv = (TextView) findViewById(R.id.h_w);

        //get a hook to the sensor service
        sManager = (SensorManager) getSystemService(SENSOR_SERVICE);
    }

    //when this Activity starts
    @Override
    protected void onResume()
    {
        super.onResume();
		/*register the sensor listener to listen to the gyroscope sensor, use the
		callbacks defined in this class, and gather the sensor information as quick
		as possible*/
        sManager.registerListener(this, sManager.getDefaultSensor(Sensor.TYPE_ORIENTATION),SensorManager.SENSOR_DELAY_FASTEST);
    }

    //When this Activity isn't visible anymore
    @Override
    protected void onStop()
    {
        //unregister the sensor listener
        sManager.unregisterListener(this);
        super.onStop();
    }

    @Override
    public void onAccuracyChanged(Sensor arg0, int arg1)
    {
        //Do nothing.
    }

    boolean isEqual(float a, float b)
    {
        if(a >= b-30 && a <= b+30)
            return true;
        else
            return false;
    }

    boolean isEqual(float x1, float y1, float z1, float x2, float y2, float z2) {

        return isEqual(x1, x2) && isEqual(y1, y2) && isEqual(z1, z2);
    }

    static String previous = "Unknown";
    String getDirection(float x, float y, float z)
    {

        if(     isEqual(x, y, z, 0, -90, 0) ||
                isEqual(x, y, z, -90, 90, 180) ||
                isEqual(x, y, z, 0, 90, 180) ||
                isEqual(x, y, z, 90, 0, 270)
                )
            previous = "North";

        if(     isEqual(x, y, z, 0, -90, 270) ||
                isEqual(x, y, z, 90, 90, 90) ||
                isEqual(x, y, z, 0, 90, 90) ||
                isEqual(x, y, z, -90, 90, 90)
                )
            previous = "West";

        if(     isEqual(x, y, z, 0, -90, 90) ||
                isEqual(x, y, z, -90, 0, 180) ||
                isEqual(x, y, z, 0, 90, 270) ||
                isEqual(x, y, z, 90, 0, 0)
                )
            previous = "East";

        if(     isEqual(x, y, z, 0, -90, 180) ||
                isEqual(x, y, z, -90, 0, 270) ||
                isEqual(x, y, z, 0, 90, 0) ||
                isEqual(x, y, z, 90, 0 , 90)
                )
            previous = "South";
//
//        if(     isEqual(x, y, z, 0, 180, z)
//                )
//            previous = "Up";
//
//        if(     isEqual(x, y, z, 0, 0, z)
//                )
//            previous = "Down";

        return previous;
    }

    @Override
    public void onSensorChanged(SensorEvent event)
    {
        //if sensor is unreliable, return void
        if (event.accuracy == SensorManager.SENSOR_STATUS_UNRELIABLE)
        {
            return;
        }

        //else it will output the Roll, Pitch and Yawn values
        tv.setText("Orientation X (Roll) :"+ Float.toString(event.values[2]) +"\n"+
                "Orientation Y (Pitch) :"+ Float.toString(event.values[1]) +"\n"+
                "Orientation Z (Yaw) :"+ Float.toString(event.values[0]) + "\n"+
                getDirection(event.values[2], event.values[1], event.values[0]));
    }
}