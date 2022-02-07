package org.matemate.Setting;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.matemate.Participate.DeleteResponse;
import org.matemate.Interface.OnBackPressedListener;
import org.matemate.Login.LoginActivity;
import org.matemate.R;
import org.matemate.Server.RetrofitClient;
import org.matemate.Server.ServiceApi;

public class SettingFragment extends Fragment implements OnBackPressedListener {

    TextView logoutBtn;
    TextView leaveBtn;
    ImageView settingImg;
    TextView settingId;
    TextView settingNickname;
    FragmentManager fragmentManager;

    ServiceApi serviceApi;

    int userIdx;

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
        serviceApi = RetrofitClient.getClient().create(ServiceApi.class);

        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("login", Context.MODE_PRIVATE);
        userIdx = sharedPreferences.getInt("userIdx", -1);

        startGetUserInfo(userIdx);

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
                builder.setPositiveButton("예", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        startDeleteUser(userIdx);
                    }
                });
                builder.setNegativeButton("아니오", null);
                builder.create().show();
            }
        });

    }

    public void startGetUserInfo(int idx) {
        serviceApi.getUserInfo(idx).enqueue(new Callback<ProfileResponse>() {
            ProfileResponse result;

            @Override
            public void onResponse(Call<ProfileResponse> call, Response<ProfileResponse> response) {
                result = response.body();

                settingId.setText(result.getEmail());
                settingNickname.setText(result.getNickname());

                // Toast.makeText(getContext(), result.getMessage(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<ProfileResponse> call, Throwable t) {
                Toast.makeText(getContext(), "유저 정보 로딩 실패", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void startDeleteUser(int idx) {
        serviceApi.deleteUser(idx).enqueue(new Callback<DeleteResponse>() {
            DeleteResponse result;

            @Override
            public void onResponse(Call<DeleteResponse> call, Response<DeleteResponse> response) {
                result = response.body();
                System.out.println(result);

                Toast.makeText(getContext(), result.getMessage(), Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(getContext(), LoginActivity.class);
                startActivity(intent);
                getActivity().finish();
            }

            @Override
            public void onFailure(Call<DeleteResponse> call, Throwable t) {
                t.printStackTrace();
                Toast.makeText(getContext(), "유저 정보 로딩 실패", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onBackPressed() {
        fragmentManager.beginTransaction().remove(SettingFragment.this).commit();
        fragmentManager.popBackStack();
    }
}