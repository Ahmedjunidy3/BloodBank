package com.example.bloodbank.adapter;

import android.app.Activity;
import android.content.Context;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bloodbank.R;
import com.example.bloodbank.data.model.DonationRequests.DonationData;
import com.example.bloodbank.data.model.generalSource.GeneralSourceData;
import com.example.bloodbank.view.activity.BaseActivity;
import com.example.bloodbank.view.fragment.homeCycle.donation.DonationReqDetailsFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class

DonationAdapter extends RecyclerView.Adapter<DonationAdapter.DonationViewHolder> {

@BindView(R.id.card_view_patient_name)
TextView cardViewPatientName;
@BindView(R.id.card_view_patient_hospital)
    TextView cardViewPatientHospital;
@BindView(R.id.card_view_patient_city)
    TextView cardViewPatientCity;
@BindView(R.id.card_view_patient_blood_type)
    TextView cardViewPatientBloodType;
@BindView(R.id.card_view_donation_call_iv)
ImageView cardViewDonationCallIv;
@BindView(R.id.card_view_donation_info_iv)
    ImageView cardViewDonationInfoIv;
private final Context context;
private final List<GeneralSourceData> donationReqsList;

public DonationAdapter(Context context, List<GeneralSourceData> donationReqsList) {
        this.context = context;
        this.donationReqsList = donationReqsList;
        }

@NonNull
@Override
public DonationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context)
        .inflate(R.layout.item_donation, parent, false);
        ButterKnife.bind(this, view);
        return new DonationViewHolder(view);
        }

@Override
public void onBindViewHolder(@NonNull DonationViewHolder holder, int position) {
        GeneralSourceData donationData = donationReqsList.get(position);
        cardViewPatientName.setText(context.getString(R.string.patient_name_donation_fragment
        , donationData.getPatientName()));
        cardViewPatientHospital.setText(context.getString(R.string.hosptial_name_donation_fragment
        , donationData.getHospitalName()));
        cardViewPatientCity.setText(context.getString(R.string.city_name_donation_fragment,
        donationData.getCity().getName()));
        cardViewPatientBloodType.setText(donationData.getBloodType().getName());
        cardViewDonationInfoIv.setOnClickListener(v -> {
        Fragment fragment = ((AppCompatActivity) context).getSupportFragmentManager()
        .findFragmentById(R.id.container_activity_home);
        if (fragment != null) {
        fragment = new DonationReqDetailsFragment();
        Bundle bundle = new Bundle();
        bundle.putString("DONATION_REQ_ID", String.valueOf(donationData.getId()));
        bundle.putString("DONATION_REQ_PHONE_NO", donationData.getPhone());
        fragment.setArguments(bundle);
        ((AppCompatActivity) context).getSupportFragmentManager()
        .beginTransaction()
        .replace(R.id.container_activity_home, fragment)
        .commit();
        }

        });
        cardViewDonationCallIv.setOnClickListener(v -> {
        String phoneNo = donationData.getPhone();
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:" + phoneNo));
        context.startActivity(intent);

        });
        }

@Override
public int getItemCount() {
        return donationReqsList.size();
        }

static class DonationViewHolder extends RecyclerView.ViewHolder {

    DonationViewHolder(@NonNull View itemView) {
        super(itemView);

    }
}



}
