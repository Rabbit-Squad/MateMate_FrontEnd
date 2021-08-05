package org.matemate;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {

    EditText idInput;
    EditText pwInput;
    Button loginBtn;
    CheckBox remember_check;
    TextView createAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        idInput = findViewById(R.id.login_id);
        pwInput = findViewById(R.id.login_pw);
        loginBtn = findViewById(R.id.login_btn);
        remember_check = findViewById(R.id.login_remember_account);
        createAccount = findViewById(R.id.login_sign_up);

    }

}