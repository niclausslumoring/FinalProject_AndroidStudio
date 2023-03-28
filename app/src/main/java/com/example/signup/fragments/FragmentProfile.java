package com.example.signup.fragments;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.signup.Login;
import com.example.signup.R;
import com.example.signup.User;
import com.example.signup.userHelper;

public class FragmentProfile extends Fragment {

    private TextView usernameTextView;
    private TextView emailTextView;
    private TextView regionTextView;
    private TextView phoneNumberTextView;

    private Button logoutButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        SharedPreferences sharedPreferences = getContext().getSharedPreferences("MySharedPref", Context.MODE_PRIVATE);
        String username = sharedPreferences.getString("username", "");

        userHelper dbHelper = new userHelper(getContext());
        dbHelper.open();
        User user = dbHelper.getUserByUsername(username);
        dbHelper.close();

        usernameTextView = view.findViewById(R.id.usernameTextView);
        emailTextView = view.findViewById(R.id.emailTextView);
        regionTextView = view.findViewById(R.id.regionTextView);
        phoneNumberTextView = view.findViewById(R.id.phoneNumberTextView);

        usernameTextView.setText(user.getUsername());
        emailTextView.setText(user.getEmail());
        regionTextView.setText(user.getRegion());
        phoneNumberTextView.setText(user.getPhone_number());

        logoutButton = view.findViewById(R.id.logoutButton);
        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setMessage("Are you sure you want to log out?")
                        .setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                SharedPreferences.Editor editor = sharedPreferences.edit();
                                editor.putString("username", "");
                                editor.apply();
                                Intent intent = new Intent(getContext(), Login.class);
                                startActivity(intent);
                                getActivity().finish();
                                Toast.makeText(getContext(), "Logged out successfully", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
                AlertDialog alert = builder.create();
                alert.show();
            }
        });
        return view;
    }

}
