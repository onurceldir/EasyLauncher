package com.example.mylauncher;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    /**In this method, if launcher returns to MainActivity, it will start the method that goes to HomeActivity.
    * In android lifecycle, if users clear the memory; launcher starts onCreate method, in the other way,
    * if user starts the launcher manuel way, onResume() method does not works. Because of these, i override onResume()
    * method with the goToHomeActivity().*/
    @Override
    protected void onResume() {
        super.onResume();

        goToHomeActivity();
    }
    /**This function goes to HomeActivity instantly. I should do everything in MainActivity but
    * it will be clearer because of standart coding.*/
    void goToHomeActivity(){
        Intent intent = new Intent(this, HomeActivity.class);

        /**I am not sure about to use this code given below. This function deletes "activity stack".
        * For the end user this is not a problem; for the developer, it can be little problem later.
        * Because stack uses memory(less than 1MB but it stacks)
        * if you want to understand more about activity stacks read this:
        * https://developer.android.com/guide/components/activities/tasks-and-back-stack*/

        //intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        /**test*/
    }
}
