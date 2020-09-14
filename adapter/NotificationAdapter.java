package com.example.bloodbank.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bloodbank.R;
import com.example.bloodbank.data.model.generalSource.GeneralSourceData;
import com.example.bloodbank.data.model.notifications.NotificationData;
import com.example.bloodbank.view.activity.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class

NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.ViewHolder> {

    @BindView(R.id.card_view_notifications_unread_notificat_iv)
    ImageView cardViewNotificationsUnreadNotificatIv;
    @BindView(R.id.card_view_notifications_read_notificat_iv)
    ImageView cardViewNotificationsReadNotificatIv;
    private final Context context;
    private final List<GeneralSourceData> notificaitonsList;


    public NotificationAdapter( Context context, List<GeneralSourceData> notificaitonsList) {
        this.context = context;
        this.notificaitonsList = notificaitonsList;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context)
                .inflate(R.layout.item_notification, parent, false);
        ButterKnife.bind(this, itemView);
        return new ViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        setData(position);
    }
//
//    private void setData(ViewHolder holder, int position) {
//        if (notificationDataList.get(position).getPivot().getIsRead().equals("0")) {
//               holder.itemNotificationIvIcon.setImageResource(R.drawable.notificaionicon);
//        }else {
//            holder.itemNotificationIvIcon.setImageResource(R.drawable.notificationiconwhite);
//        }
//        holder.iteNotificationTvTitle.setText(notificationDataList.get(position).getTitle());
//
//    }
    private void setData(int position) {
        GeneralSourceData notificationItem = notificaitonsList.get(position);
        if (notificationItem.getPivot().getIsRead().equals("1")) {
            cardViewNotificationsReadNotificatIv.setVisibility(View.VISIBLE);
            cardViewNotificationsUnreadNotificatIv.setVisibility(View.GONE);
        } else {
            cardViewNotificationsReadNotificatIv.setVisibility(View.GONE);
            cardViewNotificationsUnreadNotificatIv.setVisibility(View.VISIBLE);
        }
        onViewClicked(notificationItem);
    }
    private void onViewClicked(GeneralSourceData notificationItem) {
        cardViewNotificationsReadNotificatIv.setOnClickListener(v -> Toast.makeText(context, "isRead: " + notificationItem.getPivot().getIsRead()
                , Toast.LENGTH_SHORT).show());

        cardViewNotificationsUnreadNotificatIv.setOnClickListener(v -> Toast.makeText(context, "isRead: " + notificationItem.getPivot().getIsRead()
                , Toast.LENGTH_SHORT).show());
    }

    @Override
    public int getItemCount() {
        return notificaitonsList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {


        public ViewHolder(View itemView) {
            super(itemView);
        }

    }
}
