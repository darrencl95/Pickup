package com.pickup.pickup.controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.pickup.pickup.R;

public class NavigationActivity extends Fragment {

    private Button buttonProfile;
    private Button buttonFriends;
    private Button buttonCurrent;
    private Button buttonPast;
    private Button buttonSettings;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.navigation_view, container, false);
        buttonProfile = (Button) rootView.findViewById(R.id.buttonProfile);
        buttonFriends = (Button) rootView.findViewById(R.id.buttonFriends);
        buttonCurrent = (Button) rootView.findViewById(R.id.buttonCurrent);
        buttonPast = (Button) rootView.findViewById(R.id.buttonPast);
        buttonSettings = (Button) rootView.findViewById(R.id.buttonSettings);

//        buttonProfile.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(getActivity(), "Profile button pressed", Toast.LENGTH_SHORT).show();
//                Log.d("fragment", "button profile onclick works");
//            }
//        });
        buttonProfile.setOnClickListener(clickListener);
        buttonFriends.setOnClickListener(clickListener);
        buttonCurrent.setOnClickListener(clickListener);
        buttonPast.setOnClickListener(clickListener);
        buttonSettings.setOnClickListener(clickListener);
        return rootView;
    }

    private View.OnClickListener clickListener = new View.OnClickListener() {
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.buttonProfile:
                    startActivity(new Intent(getActivity(), ProfileActivity.class));
                    killPreviousActivity();
                    Toast.makeText(getActivity(), "Profile button pressed", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.buttonFriends:
                    startActivity(new Intent(getActivity(), FriendsActivity.class));
                    killPreviousActivity();
                    Toast.makeText(getActivity(), "Friends button pressed", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.buttonCurrent:
                    startActivity(new Intent(getActivity(), CurrentActivity.class));
                    killPreviousActivity();
                    Toast.makeText(getActivity(), "Current button pressed", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.buttonPast:
                    startActivity(new Intent(getActivity(), HistoryActivity.class));
                    killPreviousActivity();
                    Toast.makeText(getActivity(), "Past button pressed", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.buttonSettings:
                    startActivity(new Intent(getActivity(), SettingsActivity.class));
                    killPreviousActivity();
                    Toast.makeText(getActivity(), "Settings button pressed", Toast.LENGTH_SHORT).show();
                    break;
                default:
                    break;
            }
        }
    };

    private void killPreviousActivity() {
        if (!getActivity().getClass().getSimpleName().equals("MainActivity")) {
            getActivity().finish();
        }
    }


}