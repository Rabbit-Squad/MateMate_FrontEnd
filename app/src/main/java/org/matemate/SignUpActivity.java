package org.matemate;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import org.w3c.dom.Text;

import java.util.regex.Pattern;

public class SignUpActivity extends AppCompatActivity {
    EditText id;
    EditText pw;
    EditText nickname;
    EditText email;
    String num_eng_pattern = "^[a-zA-Z0-9]{8~15}$"; // 영문+숫자+8~15자
    String email_pattern = "^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$";
    Button signUpBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        id = findViewById(R.id.id_input);
        pw = findViewById(R.id.pw_input);
        nickname = findViewById(R.id.nickname_input);
        email = findViewById(R.id.email_input);
        signUpBtn = findViewById(R.id.sign_up_btn);

        id.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus) {
                    if(!num_eng_pattern.matches((id.getText()).toString())) {
                        Toast.makeText(id.getContext(), "아이디는 영문과 숫자조합의 8~15자여야 합니다. ", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

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
                if (id.length() != 0 && pw.length() != 0 && email.length() != 0 && nickname.length() != 0)
                {
                    Toast.makeText(getApplicationContext(), "회원가입이 완료되었습니다.", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        });
    }
}
