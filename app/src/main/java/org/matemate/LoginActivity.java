package org.matemate;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    EditText idInput;
    EditText pwInput;
    Button loginBtn;
    CheckBox remember_check;
    TextView createAccount;
    private ServiceApi serviceApi = RetrofitClient.getClient().create(ServiceApi.class);
    ;

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
                {   /*
                    //로그인 성공~~
                    Toast.makeText(LoginActivity.this, "로그인하였습니다.", Toast.LENGTH_SHORT).show();*/
                    String id = idInput.getText().toString();
                    String pw = pwInput.getText().toString();
                    startLogin(new LoginData(id, pw));
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

    private void startLogin(LoginData loginData) {
        serviceApi.userLogin(loginData).enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                LoginResponse result = response.body();
                System.out.println(response.toString());
                System.out.println(result.getStatus());
                try {
                    if (result.getStatus() == 200) {
                        SharedPreferences sharedPreferences = getSharedPreferences("login", MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putInt("userIdx", result.getUserIdx());
                        editor.putString("token", result.getToken());
                        editor.commit();

                        System.out.println("Try to open main Activity...");

                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivityForResult(intent, REQUEST_CODE_MAIN);
                        finish();
                    }
                    else if (result.getStatus() == 204) {
                        Toast.makeText(getApplicationContext(), result.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }catch (Exception e){
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(), "Login fail", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "로그인 에러 발생", Toast.LENGTH_SHORT).show();
                Log.e("로그인 에러", t.getMessage());
                t.printStackTrace();
            }
        });
    }
}