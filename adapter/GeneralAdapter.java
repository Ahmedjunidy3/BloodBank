package com.example.bloodbank.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


import com.example.bloodbank.R;
import com.example.bloodbank.data.model.generalSource.GeneralSourceData;

import java.util.ArrayList;

public class GeneralAdapter extends BaseAdapter {
    private final Context context;
    private final ArrayList<GeneralSourceData> generalList;

    public GeneralAdapter(Context context, ArrayList<GeneralSourceData> generalList) {
        this.context = context;
        this.generalList = generalList;
    }

    @Override
    public int getCount() {
        return generalList.size();
    }

    @Override
    public Object getItem(int position) {
        return generalList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        @SuppressLint("ViewHolder") View itemView = LayoutInflater.from(context)
                .inflate(R.layout.custom_spinner_items, parent, false);
        TextView spinTV = itemView.findViewById(R.id.item_spinner_tv_text);
         String name = generalList.get(position).getName();
        if (name != null) {
            spinTV.setText(name);
            spinTV.setTextColor(Color.RED);
        } else {
            spinTV.setText(R.string.not_available);
        }
        return itemView;
    }

}
