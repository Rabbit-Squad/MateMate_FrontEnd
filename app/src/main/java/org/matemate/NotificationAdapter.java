package org.matemate;

import android.app.Notification;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.List;

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.ViewHolder> implements OnNotificationClickListener {
    List<NotificationData> list;
    TextView title;
    TextView nickname;
    TextView time;
    TextView contents;
    OnNotificationClickListener listener;


    public NotificationAdapter(List<NotificationData> list) {
        this.list = list;
    }

    public void setOnItemClickListener(OnNotificationClickListener listener) {
        this.listener = listener;
    }

    public NotificationData getNotification(int position) {
        return list.get(position);
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.post_title);
            nickname = itemView.findViewById(R.id.request_nickname);
            contents = itemView.findViewById(R.id.request_message);
            time = itemView.findViewById(R.id.request_time);

            itemView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    if(listener != null) {
                        int pos = getBindingAdapterPosition();
                        listener.onItemClick(ViewHolder.this, v, pos);
                    }
                }
            });
        }

        public void setNotification(NotificationData data) {
            list.add(data);
            title.setText(data.getTitle());
            nickname.setText(data.getNickname());
            contents.setText(data.getContent());
            time.setText(data.getArrive_time());
        }
    }

    @NonNull
    @Override
    public NotificationAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View notificationView = inflater.inflate(R.layout.request_item, parent, false);
        return new ViewHolder(notificationView);
    }

    @Override
    public void onBindViewHolder(@NonNull NotificationAdapter.ViewHolder holder, int position) {
        NotificationData data = list.get(position);
        holder.setNotification(data);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public void onItemClick(ViewHolder holder, View view, int position) {
        if(listener != null) {
            listener.onItemClick(holder, view, position);
        }
    }
}