package com.example.login;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Registration extends AppCompatActivity {

    EditText regName;
    EditText regPassword;
    EditText regEmail;
    TextView regSignUptxt;
    Button regTopSignUp;
    Button regSignUp;
    CheckBox regCheckbox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        final DatabaseHelper db = new DatabaseHelper(this);

        regName = (EditText) findViewById(R.id.reg_name);
        regPassword = (EditText) findViewById(R.id.reg_password);
        regEmail = (EditText) findViewById(R.id.edit_email);
        regTopSignUp = (Button) findViewById(R.id.reg_up);
        regSignUp = (Button) findViewById(R.id.btn_sign_in);
        regCheckbox = (CheckBox) findViewById(R.id.checkBox);
        regSignUptxt = (TextView) findViewById(R.id.text_sign_up);

        regSignUptxt.setPaintFlags(regSignUptxt.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        regSignUptxt.setText("Sign Up");

        regTopSignUp.equals(regSignUp);

        regSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String user = regName.getText().toString().trim();
                String pwd = regPassword.getText().toString().trim();
                String mail = regEmail.getText().toString().trim();

                Long val = db.addUser(user, pwd, mail);
                if(regName.getText().toString().trim().isEmpty() || regPassword.getText().toString().trim().isEmpty() || regEmail.getText().toString().trim().isEmpty()){
                    Toast.makeText(Registration.this, "Please input a username, password and email address ", Toast.LENGTH_SHORT).show();
                }
                else if(regCheckbox.isChecked()) {
                    if (val > 0) {
                        Intent intent = new Intent(Registration.this, MainActivity.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(Registration.this, "Registeration Error", Toast.LENGTH_SHORT).show();
                    }
                    ;
                }
                else{
                    Toast.makeText(Registration.this, "Please Accept the Terms of Service", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
