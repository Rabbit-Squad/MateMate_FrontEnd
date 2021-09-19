package org.matemate;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class MyPostAdapter extends RecyclerView.Adapter<MyPostAdapter.ViewHolder> implements OnPostItemClickListener {
    List<Post> posts;
    Context context;

    TextView writer_name;
    TextView title;
    TextView location;
    TextView time;
    TextView member;
    TextView content;

    OnPostItemClickListener listener;

    public MyPostAdapter(Context context, List<Post>posts) {
        this.context = context;
        this.posts = posts;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public ViewHolder(@NonNull View itemView, final OnPostItemClickListener listener) {
            super(itemView);
            writer_name = itemView.findViewById(R.id.writer_name);
            title = itemView.findViewById(R.id.post_title);
            location = itemView.findViewById(R.id.post_location);
            time = itemView.findViewById(R.id.meeting_time);
            member = itemView.findViewById(R.id.member_num);
            content = itemView.findViewById(R.id.post_content);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getBindingAdapterPosition();

                    if (listener != null) {
                        listener.onItemClick(ViewHolder.this, v, position);
                    }
                }
            });
        }

        public void setPost(Post post) {
            posts.add(post);
            writer_name.setText(post.getNickname());
            title.setText(post.getTitle());
            location.setText(post.getLocation());
            time.setText(post.getDeadline().toString());
            member.setText(post.getCur_num() + " / "+post.getMin_num());
            content.setText(post.getContent());
        }

        public Post getPost(int position) {
            return posts.get(position);
        }
    }

    @NonNull
    @Override
    public MyPostAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View postView = inflater.inflate(R.layout.mypost_item, viewGroup, false);
        return new ViewHolder(postView, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Post post = posts.get(position);
        holder.setPost(post);
    }


    public void setOnItemClickListener(OnPostItemClickListener listener) {
        this.listener = listener;
    }


    @Override
    public void onItemClick(PostAdapter.ViewHolder holder, View view, int position) {

    }

    @Override
    public void onItemClick(MyPostAdapter.ViewHolder holder, View view, int position) {
        if (listener != null) {
            listener.onItemClick(holder, view, position);
        }
    }


    @Override
    public int getItemViewType(int position) {return position;}
    public int getItemCount() {
        return posts.size();
    }
    public Post getPost(int position) {
        return posts.get(position);
    }
    public void addPost(Post post) {
        posts.add(post);
    }
    public void setPosts (List<Post> posts) { this.posts = posts; }
}
