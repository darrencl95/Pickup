package com.pickup.pickup.controller;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.pickup.pickup.R;

/**
 * Created by zachschlesinger on 4/1/17.
 */

public class JoinActivity extends AppCompatActivity {

    private TextView display;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joinevent);

        display = (TextView) findViewById(R.id.display);
        display.setText(getTime().toString());
    }

    private String getTime() {
        String[] times = {"12,2", "1,3", "2,4"};

        Boolean[] person1 = {true, true, true};
        Boolean[] person2 = {false, true, true};
        Boolean[] person3 = {true, false, false};
        Boolean[] person4 = {false, true, true};
        Boolean[] person5 = {false, true, false};

        Boolean[][] people = new Boolean[5][];
        people[0] = person1;
        people[1] = person2;
        people[2] = person3;
        people[3] = person4;
        people[4] = person5;

        int[] finalPeople = new int[4];




        return "meh";
    }
}
