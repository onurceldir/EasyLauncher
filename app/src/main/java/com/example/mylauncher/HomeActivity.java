package com.example.mylauncher;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class HomeActivity extends AppCompatActivity {


    private TextView dateTimeDisplay;
    private Calendar calendar;
    private SimpleDateFormat dateFormat;
    private String date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);



        /**Time format*/
        TextView dateTimeDisplay = (TextView) findViewById(R.id.text_date_display);

        calendar = Calendar.getInstance();

        dateFormat = new SimpleDateFormat("dd MMM YYYY    HH:MM");
        date = dateFormat.format(calendar.getTime());
        dateTimeDisplay.setText(date);

    }

    /**if i add hideStatusBar() method to oncreate method, it will hide just first time.
    * i overrided onResume method because of this.*/
    @Override
    protected void onResume() {
        super.onResume();
        hideStatusBar();
    }

    private void hideStatusBar() {
        View decorView = getWindow().getDecorView();
        // Hide the status bar.
        /**Firstly i used this code below. but i wanted to auto hide. because if
        * i don't go to onResume() method anyway, it will shown everytime.*/
        //int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN        //Constant Value: 4 (0x00000004)
                      | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY; //Constant Value: 4096 (0x00001000)
        /* or "4096 + 4 = 4100" for all of this //
        int uiOptions = 4100;*/
        decorView.setSystemUiVisibility(uiOptions);

    }


    //Call Management
    public void onDialerButtonClick(View v) {
        Intent launchIntent = getPackageManager().getLaunchIntentForPackage("com.android.contacts");
        startActivity(launchIntent);
    }
    public void onWhatsappButtonClick(View v) {
        Intent launchIntent = getPackageManager().getLaunchIntentForPackage("com.whatsapp");
        startActivity(launchIntent);
    }
    public void onMailButtonClick(View v) {
        Intent launchIntent = getPackageManager().getLaunchIntentForPackage("com.google.android.gm");
        startActivity(launchIntent);
    }
    public void onBrowserButtonClick(View v) {
        Intent launchIntent = getPackageManager().getLaunchIntentForPackage("com.android.chrome");
        startActivity(launchIntent);
    }
    public void onClockButtonClick(View v) {
        Intent launchIntent = getPackageManager().getLaunchIntentForPackage("com.android.deskclock");
        startActivity(launchIntent);
    }
    public void onClassroomButtonClick(View v) {
        Intent launchIntent = getPackageManager().getLaunchIntentForPackage("com.google.android.apps.classroom");
        startActivity(launchIntent);
    }
    public void onSettingButtonClick(View view) {
        Intent launchIntent = getPackageManager().getLaunchIntentForPackage("com.android.settings");
        startActivity(launchIntent);
    }

    public void onSearchButtonClick(View view) {
        Intent intent = new Intent(this, SearchActivity.class);
        startActivity(intent);
    }

    /** There is two options for disable back button. Another code(api level up to 1.6) for do same thing is this:*/
    /* @Override
        public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
        //preventing default implementation previous to android.os.Build.VERSION_CODES.ECLAIR
        return true;
        }
        return super.onKeyDown(keyCode, event);
    }
    --This function disables back button(higher api level 2.0) in home activity. I switched this code because it is simple and newer.
    */
    @Override
    public void onBackPressed() {}


}
