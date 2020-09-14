package com.example.bloodbank.view.fragment.homeCycle.donation;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


import com.example.bloodbank.R;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

class HospitalAddressFragment extends Fragment {

    @BindView(R.id.fragment_donation_req_toolbar)
    TextView fragmentDonationReqToolbar;
    @BindView(R.id.fragment_hospital_address_webview)
    WebView fragmentHospitalAddressWebview;
    @BindView(R.id.fragment_hospital_address_btn_choose_location)
    Button fragmentHospitalAddressBtnChooseLocation;
    private GoogleMap googleMap;
    private Marker marker;
    private Unbinder unbinder;


    public HospitalAddressFragment() {
    }

    @SuppressLint("SetJavaScriptEnabled")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_hosptial_address
                , container, false);
        unbinder = ButterKnife.bind(this, view);
        fragmentHospitalAddressWebview.setWebChromeClient(new WebChromeClient());
        fragmentHospitalAddressWebview.getSettings().setJavaScriptEnabled(true);
        fragmentHospitalAddressWebview.loadUrl("https://www.google.com/maps");
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

}
