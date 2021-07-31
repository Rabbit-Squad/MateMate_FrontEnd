package org.matemate;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.ViewHolder> {
        ArrayList<Post> posts = new ArrayList<Post>();

        public class ViewHolder extends RecyclerView.ViewHolder {
                public ViewHolder(@NonNull View itemView) {
                        super(itemView);
                }

                public void setPost(Post post) {
                        posts.add(post);
                }

                public Post getPost(int position) {
                        return posts.get(position);
                }
        }

        @NonNull
        @Override
        public PostAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
                LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
                View postView = inflater.inflate(R.layout.activity_main, viewGroup, false);
                return new ViewHolder(postView);
        }

        @Override
        public void onBindViewHolder(@NonNull PostAdapter.ViewHolder holder, int position) {
                Post post = posts.get(position);
                holder.setPost(post);
        }

        @Override
        public int getItemCount() {
                return posts.size();
        }

        public void addPost(Post post) {
                posts.add(post);
        }
}
