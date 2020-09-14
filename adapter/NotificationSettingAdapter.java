package com.example.bloodbank.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bloodbank.R;
import com.example.bloodbank.data.model.generalResponse.GeneralResponseData;
import com.example.bloodbank.data.model.generalSource.GeneralSourceData;
import com.example.bloodbank.view.activity.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class

NotificationSettingAdapter extends RecyclerView.Adapter<NotificationSettingAdapter.ViewHolder> {

    private final Context context;
    private final List<GeneralSourceData> generalSourceList;
    private final List<String> oldIds;
    private final List<String> newIds = new ArrayList<>();

    public NotificationSettingAdapter(Context context, List<GeneralSourceData> generalSourceList,
                                      List<String> oldIds) {
        this.context = context;
        this.generalSourceList = generalSourceList;
        this.oldIds = oldIds;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_notificat_settings
                , parent, false);
        return new ViewHolder(view, generalSourceList, newIds);
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        setData(holder, position);
    }

    private void setData(ViewHolder holder, int position) {
        GeneralSourceData dataItem = generalSourceList.get(position);
        holder.cardViewNotificatSettingsChkbox.setText(dataItem.getName());

        if (oldIds.contains(String.valueOf(generalSourceList.get(position).getId()))) {
            holder.cardViewNotificatSettingsChkbox.setChecked(true);
            newIds.add(String.valueOf(generalSourceList.get(position).getId()));
        } else {
            holder.cardViewNotificatSettingsChkbox.setChecked(false);
        }


    }

    private void setAction(ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return generalSourceList.size();
    }



    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.card_view_notificat_settings_chkbox)
        CheckBox cardViewNotificatSettingsChkbox;
        private final List<GeneralSourceData> generalSourceList;
        private final List<String> newIds;

        public ViewHolder(View itemView, List<GeneralSourceData> generalSourceList, List<String> newIds) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            this.generalSourceList = generalSourceList;
            this.newIds = newIds;

        }
        @OnClick(R.id.card_view_notificat_settings_chkbox)
        public void onViewClicked() {
            if (cardViewNotificatSettingsChkbox.isChecked()) {
                newIds.add(String.valueOf(generalSourceList.get(getAdapterPosition())
                        .getId()));
            } else {
                newIds.remove(getAdapterPosition());
            }




        }

    }

    public List<String> getNewIds() {
        return newIds;
    }

    }

