package com.example.signup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText username, email, phone_number, region, password;
    Button signup_button, signin_button;

    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        username = findViewById(R.id.username);
        email = findViewById(R.id.email);
        phone_number = findViewById(R.id.phone_number);
        region = findViewById(R.id.region);
        password = findViewById(R.id.password);
        signup_button = findViewById(R.id.signup_button);
        signin_button = findViewById(R.id.signin_button);
        DB = new DBHelper(this);

        signup_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username_new = username.getText().toString();
                String email_new = email.getText().toString();
                String phone_number_new = phone_number.getText().toString();
                String region_new = region.getText().toString();
                String password_new = password.getText().toString();

                boolean check = validateInfo(username_new, email_new, phone_number_new, region_new, password_new);

                if (check == true) {
                    Toast.makeText(getApplicationContext(), "Data is valid", Toast.LENGTH_SHORT).show();
                    Boolean insert = DB.insertData(username_new, email_new, region_new, phone_number_new, password_new);
                    if (insert == true) {
                        System.out.println("New user registered with username: " + username_new);
                        startActivity(new Intent(getApplicationContext(), Login.class));
                    } else {
                        System.out.println("Registration Failed");
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Sorry, check information again !", Toast.LENGTH_SHORT).show();
                }
            }
        });

        signin_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Login.class);
                startActivity(intent);
            }
        });
    }

    private Boolean validateInfo(String username_new, String email_new, String phone_number_new, String region_new, String password_new) {
        if (username_new.length() == 0) {
            username.requestFocus();
            username.setError("Field can not be empty !");
            return false;
        } else if (!username_new.matches("[a-zA-Z0-9]+")) {
            username.requestFocus();
            username.setError("Username must be alphanumeric !");
            return false;
        } else if (DB.checkUsername(username_new)) {
            Toast.makeText(MainActivity.this, "Username already exists", Toast.LENGTH_SHORT).show();
            return false;
        } else if (email_new.length() == 0) {
            email.requestFocus();
            email.setError("Field can not be empty !");
            return false;
        } else if (!email_new.matches("[a-zA-Z0-9._]+@[a-z]+\\.com")) {
            email.requestFocus();
            email.setError("Enter valid email !");
            return false;
        } else if (phone_number_new.length() == 0) {
            phone_number.requestFocus();
            phone_number.setError("Field can not be empty !");
            return false;
        } else if (region_new.length() == 0) {
            region.requestFocus();
            region.setError("Field can not be empty !");
            return false;
        } else if (password_new.length() <= 5) {
            password.requestFocus();
            password.setError("Password’s length must be at least five characters");
            return false;
        } else if (!password_new.matches("[a-zA-Z0-9]+")) {
            password.requestFocus();
            password.setError("Password must be alphanumeric !");
            return false;
        } else {
            return true;
        }
    }
}
