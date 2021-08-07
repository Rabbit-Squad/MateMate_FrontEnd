package org.matemate;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class SettingActivity extends AppCompatActivity {

    TextView logoutBtn;
    TextView leaveBtn;
    ImageView settingImg;
    TextView settingId;
    TextView settingNickname;
    TextView settingJoinDate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        logoutBtn = findViewById(R.id.setting_logout);
        leaveBtn = findViewById(R.id.setting_leave);
        settingImg = findViewById(R.id.setting_profileImg);
        settingId = findViewById(R.id.setting_id);
        settingNickname = findViewById(R.id.setting_nickname);
        settingJoinDate = findViewById(R.id.setting_joinDate);
    }
}