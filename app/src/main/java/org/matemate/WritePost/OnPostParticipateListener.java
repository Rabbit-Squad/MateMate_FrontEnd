package org.matemate.WritePost;

import android.view.View;

import org.matemate.WritePost.PostAdapter;

public interface OnPostParticipateListener {
    public void onPostParticipateClick(PostAdapter.ViewHolder holder, View view, int position);
}
