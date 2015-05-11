/*
 * Copyright 2014 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.camera2basic;

import android.app.Activity;
import android.os.Bundle;
import android.content.Intent;
import android.widget.Button;
import android.view.View.OnClickListener;
import android.view.View;
import com.example.android.camera2basic.GyroActivity;

public class CameraActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);
        if (null == savedInstanceState) {
            getFragmentManager().beginTransaction()
                    .replace(R.id.container, Camera2BasicFragment.newInstance())
                    .commit();
        }
        GooglePlacesExample gpe = new GooglePlacesExample();
        gpe.getNearerPlaces();

//        Intent intent = new Intent(this, GyroActivity.class);
//        startActivity(intent);
//        finish();

//        Button btListe = (Button)findViewById(R.id.picture);
//        btListe.setOnClickListener(new OnClickListener()
//        {    public void onClick(View v)
//            {
//                Intent intent = new Intent(getApplicationContext(), GyroActivity.class);
//                getApplicationContext().startActivity(intent);
//                finish();
//            }
//        });
    }

}
