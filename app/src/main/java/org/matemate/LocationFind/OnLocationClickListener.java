package org.matemate.LocationFind;

import android.view.View;

import org.matemate.LocationFind.LocationAdapter;

public interface OnLocationClickListener {
    void onItemClick(LocationAdapter.ViewHolder holder, View view, int position);
}