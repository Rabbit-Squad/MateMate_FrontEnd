package org.matemate;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.Layout;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.post_list);
        LinearLayoutManager manager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(manager);
        PostAdapter adapter = new PostAdapter();

        ImageView menu = findViewById(R.id.menu_button);
        final DrawerLayout drawer = findViewById(R.id.main_layout);
        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawer.openDrawer(Gravity.LEFT);
            }
        });


        adapter.addPost(new Post("피바", "밥 먹읍시다. ", "작은도쿄", 1,2, "15:30",false));
        adapter.addPost(new Post("쭈니", "어차피", "윈도", 1, 8, "21:00", true));
        recyclerView.setAdapter(adapter);
    }
}
