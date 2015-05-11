package com.example.android.camera2basic;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

public class GyroActivity extends Activity implements SensorEventListener
{
    //a TextView
//    private Button btnShowMore;

    private Camera2BasicFragment ci;
    //the Sensor Manager
    private SensorManager sManager;


    private class Place {
        String name;
        String distance;
        String category;
        String timings;
        String rating;
        String phone;
        String url;


        public String getShortDesc() {
            StringBuffer sb = new StringBuffer();
            sb.append("Name : " + this.name);
            sb.append("\n\nCategory : " + this.category);
            return sb.toString();
        }
    }

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);
        ci = Camera2BasicFragment.newInstance();
        if (null == savedInstanceState) {
            getFragmentManager().beginTransaction()
                    .replace(R.id.container, ci)
                    .commit();
        }

        //get the TextView from the layout file
        //tv = ci.tv;// (TextView) findViewById(R.id.picture_gy);

        //get a hook to the sensor service
//        btnShowMore = ci.btnShow;

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
    Place place = new Place();
    Place getDirection(float x, float y, float z)
    {
        if(     isEqual(x, y, z, 0, -90, 0) ||
                isEqual(x, y, z, -90, 90, 180) ||
                isEqual(x, y, z, 0, 90, 180) ||
                isEqual(x, y, z, 90, 0, 270)
                )
//            previous = "North is displayed";

        // North
        {
            place.category = "North";
            place.distance = "210mts";
            place.name = "Subway";
            place.phone = "";
            place.timings = "";
            place.url = "";
            place.rating = "";
        }

        else if(     isEqual(x, y, z, 0, -90, 270) ||
                isEqual(x, y, z, 90, 90, 90) ||
                isEqual(x, y, z, 0, 90, 90) ||
                isEqual(x, y, z, -90, 90, 90)
                )
//            previous = "West is displayed";

        {
            place.category = "West";
            place.distance = "92mts";
            place.name = "Cafeteria";
            place.phone = "";
            place.timings = "";
            place.url = "";
            place.rating = "";
        }

        else if(     isEqual(x, y, z, 0, -90, 90) ||
                isEqual(x, y, z, -90, 0, 180) ||
                isEqual(x, y, z, 0, 90, 270) ||
                isEqual(x, y, z, 90, 0, 0)
                )
//            previous = "East is displayed";

        {
            place.category = "East";
            place.distance = "480mts";
            place.name = "Saravana Bhavan";
            place.phone = "";
            place.timings = "";
            place.url = "";
            place.rating = "";
        }

        else if(     isEqual(x, y, z, 0, -90, 180) ||
                isEqual(x, y, z, -90, 0, 270) ||
                isEqual(x, y, z, 0, 90, 0) ||
                isEqual(x, y, z, 90, 0 , 90)
                )
//            previous = "South is displayed";
        {
            place.category = "South";
            place.distance = "560mts";
            place.name = "Apollo Hospitals";
            place.phone = "";
            place.timings = "";
            place.url = "";
            place.rating = "";
        }
//
//        if(     isEqual(x, y, z, 0, 180, z)
//                )
//            previous = "Up";
//
//        if(     isEqual(x, y, z, 0, 0, z)
//                )
//            previous = "Down";

        return place;
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
        Place place1 = getDirection(event.values[2], event.values[1], event.values[0]);
        ci.tv.setText(
//                "Orientation X (Roll) :"+ Float.toString(event.values[2]) +"\n"+
//                "Orientation Y (Pitch) :"+ Float.toString(event.values[1]) +"\n"+
//                "Orientation Z (Yaw) :"+ Float.toString(event.values[0]) + "\n"+
                place1.getShortDesc());
        ci.tv.setTextSize(20);
//        ci.tv.setBackgroundColor();
//        ci.btnShow.setOnClickListener(new View.OnClickListener()
//        {    public void onClick(View v)
//                    {
//                Intent intent = new Intent(getApplicationContext(), ShowPlaceActivity.class);
//                intent.putExtra("name", place.name);
//                intent.putExtra("distance", place.distance);
//                intent.putExtra("category", place.category);
//                intent.putExtra("timings", place.timings);
//                intent.putExtra("rating", place.rating);
//                intent.putExtra("phone", place.phone);
//                intent.putExtra("url", place.url);
//
//                        getApplicationContext().startActivity(intent);
//                finish();
//}
//        });
    }
}