package org.matemate.Home;

import android.view.View;

import org.matemate.MyPostList.MyPostAdapter;
import org.matemate.WritePost.PostAdapter;

public interface OnPostItemClickListener {
    public void onItemClick(PostAdapter.ViewHolder holder, View view, int position);
    public void onItemClick(MyPostAdapter.ViewHolder holder, View view, int position);
}
