package org.matemate;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

public class SettingFragment extends Fragment implements OnBackPressedListener {

    TextView logoutBtn;
    TextView leaveBtn;
    ImageView settingImg;
    TextView settingId;
    TextView settingNickname;
    TextView settingJoinDate;
    FragmentManager fragmentManager;

    DrawerLayout drawer;
    NavigationView detailMenu;

    ServiceApi serviceApi;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.activity_setting, container, false);

        initUI(rootView);

        return rootView;
    }

    public void initUI(ViewGroup rootView) {
        fragmentManager = getActivity().getSupportFragmentManager();
        logoutBtn = rootView.findViewById(R.id.setting_logout);
        leaveBtn = rootView.findViewById(R.id.setting_leave);
        settingImg = rootView.findViewById(R.id.setting_profileImg);
        settingId = rootView.findViewById(R.id.setting_id);
        settingNickname = rootView.findViewById(R.id.setting_nickname);
        settingJoinDate = rootView.findViewById(R.id.setting_joinDate);

        detailMenu = rootView.findViewById(R.id.detail_menu);
        drawer = rootView.findViewById(R.id.my_posts_layout);

        //Add Listener
        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Process for logout...
                Toast.makeText(getContext(), "Log out", Toast.LENGTH_SHORT).show();
            }
        });

        leaveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle("회원 탈퇴");
                builder.setMessage("정말로 탈퇴하시겠습니까?");
                builder.setPositiveButton("예", null);
                builder.setNegativeButton("아니오", null);
                builder.create().show();
            }
        });
    }

    public void getUserInfo() {
        return ;
    }

    @Override
    public void onBackPressed() {
        fragmentManager.beginTransaction().remove(SettingFragment.this).commit();
        fragmentManager.popBackStack();
    }
}