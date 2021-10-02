package org.matemate;

import android.view.View;

public interface OnNotificationClickListener {
    void onItemClick(NotificationAdapter.ViewHolder holder, View view, int position);
}
