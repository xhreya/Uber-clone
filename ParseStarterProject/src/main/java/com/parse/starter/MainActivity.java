/*
 * Copyright (c) 2015-present, Parse, LLC.
 * All rights reserved.
 *
 * This source code is licensed under the BSD-style license found in the
 * LICENSE file in the root directory of this source tree. An additional grant
 * of patent rights can be found in the PATENTS file in the same directory.
 */
package com.parse.starter;
import android.content.Intent;
import android.location.LocationManager;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.Switch;

import androidx.appcompat.app.AppCompatActivity;

import com.parse.LogInCallback;
import com.parse.Parse;
import com.parse.ParseAnalytics;
import com.parse.ParseAnonymousUtils;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SaveCallback;


public class MainActivity extends AppCompatActivity {

  public void redirectActivity(){
    if(ParseUser.getCurrentUser().get("ririderorDriver")=="rider")
    {
      Intent intent=new Intent(getApplicationContext(),rider.class);

    }
  }

  public void  getstarted(View view)
  {
    Switch usertypeswitch=(Switch)findViewById(R.id.usertypeswitch);
    Log.i("sWITCH VALUE",String.valueOf(usertypeswitch.isChecked()));
    String userType="driver";
    if(usertypeswitch.isChecked())
    {
      userType="rider";
    }

    ParseUser.getCurrentUser().put("riderorDriver",userType);

   Log.i("Info","Redirecting "+userType);
   redirectActivity();

  }
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    getSupportActionBar().hide();
    if(ParseUser.getCurrentUser()==null)
    {
      ParseAnonymousUtils.logIn(new LogInCallback() {
        @Override
        public void done(ParseUser user, ParseException e) {
          if(e==null)
          {
            Log.i("Info","Anonymous login succesfull");

          }
          else {
            Log.i("Info","Anonymous login failed");

          }
        }
      });
    }else
    {
      if(ParseUser.getCurrentUser().get("riderorDriver")!=null)
      {
        Log.i("Info","Redirecting as "+ParseUser.getCurrentUser().get("riderorDriver"));
         redirectActivity();
      }
    }
    ParseAnalytics.trackAppOpenedInBackground(getIntent());
  }

}