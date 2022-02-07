package org.matemate.WritePost;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.matemate.Home.OnPostItemClickListener;
import org.matemate.MyPostList.MyPostAdapter;
import org.matemate.R;

import java.util.List;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.ViewHolder> implements OnPostItemClickListener {
        List<Post> posts;
        Context context;

        OnPostItemClickListener listener;
        OnPostParticipateListener participateListener;

        public PostAdapter(Context context, List<Post>posts) {
                this.context = context;
                this.posts = posts;
        }

        @NonNull
        @Override
        public PostAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
                LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
                View postView = inflater.inflate(R.layout.post_item, viewGroup, false);
                return new ViewHolder(postView, listener, participateListener);
        }

        @Override
        public void onBindViewHolder(@NonNull final PostAdapter.ViewHolder holder, final int position) {
                Post post = posts.get(position);
                holder.setPost(post);
                holder.participateBtn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                                if (participateListener != null) {
                                        participateListener.onPostParticipateClick(holder, v, position);
                                }
                        }
                });
        }

        public void setOnPostParticipateListener(OnPostParticipateListener onPostParticipateListener) {
                this.participateListener = onPostParticipateListener;
        }

        public static class ViewHolder extends RecyclerView.ViewHolder {
                TextView writer_name;
                TextView title;
                TextView location;
                TextView time;
                TextView member;
                Button participateBtn;

                public ViewHolder(@NonNull View itemView, final OnPostItemClickListener listener, OnPostParticipateListener participateListener) {
                        super(itemView);
                        writer_name = itemView.findViewById(R.id.writer_name);
                        title = itemView.findViewById(R.id.post_title);
                        location = itemView.findViewById(R.id.post_location);
                        time = itemView.findViewById(R.id.meeting_time);
                        member = itemView.findViewById(R.id.member_num);
                        participateBtn = itemView.findViewById(R.id.participate_btn);

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
                        // posts.add(post);
                        writer_name.setText(post.getNickname());
                        title.setText(post.getTitle());
                        location.setText(post.getLocation());
                        time.setText(post.getDeadline().toString());
                        member.setText(post.getCur_num() + " / "+post.getMin_num());
                }
        }

        @Override
        public void onItemClick(ViewHolder holder, View view, int position) {
                if (listener != null) {
                        listener.onItemClick(holder, view, position);
                }
        }

        @Override
        public void onItemClick(MyPostAdapter.ViewHolder holder, View view, int position) {

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
