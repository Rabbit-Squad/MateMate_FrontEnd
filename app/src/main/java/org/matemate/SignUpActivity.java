package org.matemate;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import org.w3c.dom.Text;

import java.util.regex.Pattern;

public class SignUpActivity extends AppCompatActivity {
    EditText pw;
    EditText nickname;
    EditText email;
    String num_eng_pattern = "^[a-zA-Z0-9]{8~15}$"; // 영문+숫자+8~15자
    String email_pattern = "^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$";
    Button signUpBtn;

    ServiceApi service = RetrofitClient.getClient().create(ServiceApi.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        pw = findViewById(R.id.pw_input);
        nickname = findViewById(R.id.nickname_input);
        email = findViewById(R.id.email_input);
        signUpBtn = findViewById(R.id.sign_up_btn);


        pw.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus) {
                    if(!num_eng_pattern.matches((pw.getText()).toString())) {
                        Toast.makeText(pw.getContext(), "비밀번호는 영문과 숫자조합의 8~15자여야 합니다. ", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        email.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus) {
                    if(!email_pattern.matches((email.getText()).toString())) {
                        Toast.makeText(email.getContext(), "유효한 이메일주소를 입력하세요. ", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //모든 항목 작성 시
                if (pw.length() != 0 && email.length() != 0 && nickname.length() != 0)
                {
                    JoinData data = new JoinData(nickname.getText().toString(), email.getText().toString(), pw.getText().toString());
                    startJoin(data);
                }
            }
        });
    }

    //회원가입 통신
    private void startJoin(JoinData data) {
        service.userJoin(data).enqueue(new Callback<JoinResponse>() {
            @Override
            public void onResponse(Call<JoinResponse> call, Response<JoinResponse> response) {
                JoinResponse result = response.body();
                Toast.makeText(getApplicationContext(), result.getMessage(), Toast.LENGTH_SHORT).show();

                if (result.getStatus() == 200) {
                    finish();
                }
            }

            @Override
            public void onFailure(Call<JoinResponse> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "회원가입 오류", Toast.LENGTH_SHORT).show();
                Log.e("회원가입 에러 발생", t.getMessage());
                t.printStackTrace();
            }
        });
    }
}
