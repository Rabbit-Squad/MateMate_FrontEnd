package org.matemate;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyPostFragment extends Fragment implements OnBackPressedListener{

    ServiceApi serviceApi= RetrofitClient.getClient().create(ServiceApi.class);
    RecyclerView recyclerView;
    PostAdapter adapter;
    int userIdx;
    List<ListData> Lists = new ArrayList();
    List<Post> Posts = new ArrayList();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_myposts, container, false);

        recyclerView = rootView.findViewById(R.id.my_post_list);
        LinearLayoutManager manager = new LinearLayoutManager(this.getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(manager);
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("login", Context.MODE_PRIVATE);
        userIdx = sharedPreferences.getInt("userIdx", -1);
        initUI(rootView);
        return rootView;
    }

    private void initUI(ViewGroup rootView) {
        getUserList();
    }

    private void getUserList() {
        serviceApi.getUserList(userIdx).enqueue(new Callback<ListResponse>() {
            @Override
            public void onResponse(Call<ListResponse> call, Response<ListResponse> response) {
                System.out.println(response.toString());
                ListResponse listResponse = response.body();
                try {
                    Lists = listResponse.getData();
                    for (int i = 0; i < Lists.size(); i++) {
                        Posts.add(new Post(Lists.get(i).getNickname(), Lists.get(i).getDeadline().toString(), Lists.get(i).getLocation(), Lists.get(i).getMin_num(), Lists.get(i).getCur_num(), Lists.get(i).getTitle(), Lists.get(i).getContent(), Lists.get(i).getClosed()));
                    }
                    adapter = new PostAdapter(getContext(), Posts); //adapter설정 + itemCount도 7인것 확인.
                    recyclerView.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                    System.out.println(adapter.getItemCount());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }


            @Override
            public void onFailure(Call<ListResponse> call, Throwable t) {
                Toast.makeText(getContext(), "서버 에러", Toast.LENGTH_SHORT).show();
                Log.e("서버 에러", t.getMessage());
                t.printStackTrace();
            }
        });


    }

    @Override
    public void onBackPressed() {
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        fragmentManager.beginTransaction().remove(MyPostFragment.this).commit();
        fragmentManager.popBackStack();
        //getFragmentManager().beginTransaction().remove(MyPostFragment.this).commit();
        //getFragmentManager().popBackStack();
    }
}

