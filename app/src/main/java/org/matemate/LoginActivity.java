package org.matemate;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    EditText idInput;
    EditText pwInput;
    Button loginBtn;
    CheckBox remember_check;
    TextView createAccount;

    public static final int REQUEST_CODE_CREATE_ACCOUNT = 101;
    public static final int REQUEST_CODE_MAIN = 201;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        idInput = findViewById(R.id.login_id);
        pwInput = findViewById(R.id.login_pw);
        loginBtn = findViewById(R.id.login_btn);
        remember_check = findViewById(R.id.login_remember_account);
        createAccount = findViewById(R.id.login_sign_up);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (idInput.length() == 0 || pwInput.length() == 0)
                {
                    //로그인 정보를 모두 입력하지 않았을 시
                    Toast.makeText(getApplicationContext(), "아이디와 비밀번호를 모두 입력해주세요.", Toast.LENGTH_SHORT).show();
                }
                else if (idInput.length() != 0 && pwInput.length() != 0)
                {
                    //로그인 성공~~
                    Toast.makeText(LoginActivity.this, "로그인하였습니다.", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivityForResult(intent, REQUEST_CODE_MAIN);
                }
            }
        });

        //회원가입 버튼 - 회원가입 띄움
        createAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SignUpActivity.class);
                startActivityForResult(intent, REQUEST_CODE_CREATE_ACCOUNT);
            }
        });
    }

}