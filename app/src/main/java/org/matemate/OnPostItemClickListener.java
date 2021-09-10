package org.matemate;

import android.view.View;

public interface OnPostItemClickListener {
    public void onItemClick(PostAdapter.ViewHolder holder, View view, int position);
}
