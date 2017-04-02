package com.pickup.pickup.controller;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.pickup.pickup.R;

/**
 * Created by zachschlesinger on 4/1/17.
 */

public class FriendsActivity extends AppCompatActivity {

    // declare UI components
    private Button buttonFriends;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friends);

        // initialize UI components
        buttonFriends = (Button) findViewById(R.id.buttonFriends);

        buttonFriends.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getBaseContext(), "But you're already here", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
