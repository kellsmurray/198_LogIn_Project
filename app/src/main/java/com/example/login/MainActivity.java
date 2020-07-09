package com.example.login;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText logName;
    EditText logPassword;
    Button logTopSignIn;
    Button logSignIn;
    Button logSignUp;
    TextView textSignIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final DatabaseHelper db = new DatabaseHelper(this);

        logName = (EditText) findViewById(R.id.reg_name);
        logPassword = (EditText) findViewById(R.id.reg_password);
        logTopSignIn = (Button) findViewById(R.id.reg_in);
        logSignIn = (Button) findViewById(R.id.btn_sign_in);
        logSignUp = (Button) findViewById(R.id.reg_up);
        textSignIn = (TextView) findViewById(R.id.text_sign_in);

        textSignIn.setPaintFlags(textSignIn.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        textSignIn.setText("Sign In");

        logSignIn.equals(logTopSignIn);

        logSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View mviews) {
                Intent sign = new Intent(MainActivity.this, Registration.class);
                startActivity(sign);
            }
        });

        logSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = logName.getText().toString().trim();
                String password = logPassword.getText().toString().trim();

                Boolean res = db.checkUser(username, password);
                if(logName.getText().toString().trim().isEmpty() || logPassword.getText().toString().trim().isEmpty()){
                    Toast.makeText(MainActivity.this,"Please Insert a username and password",Toast.LENGTH_SHORT).show();
                }
                else if(res == true)
                {
                    Intent intent = new Intent(MainActivity.this, Successful.class);
                    startActivity(intent);
                }
                else
                {
                    Toast.makeText(MainActivity.this,"Login Error",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}

