package com.example.bloodbank.view.fragment.homeCycle.donation;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Spinner;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bloodbank.R;
import com.example.bloodbank.adapter.DonationAdapter;
import com.example.bloodbank.adapter.GeneralAdapter;
import com.example.bloodbank.data.model.generalSource.GeneralSourceData;
import com.example.bloodbank.data.repository.DonationsRepository;
import com.example.bloodbank.data.repository.GeneralRepository;
import com.example.bloodbank.utility.local.SharedPreferenceManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnItemSelected;
import butterknife.Unbinder;


public class DonationReqsFragment extends Fragment
        implements DonationsRepository.OnGetDonationReqsListener
        , GeneralRepository.OnGetBloodTypeSpinListListener
        , GeneralRepository.OnGetCitySpinListListener {

    @BindView(R.id.blood_type_spin_frag_donation_reqs)
    Spinner bloodTypeSpinFragDonationReqs;
    @BindView(R.id.city_spin_frag_donation_reqs)
    Spinner citySpinFragDonationReqs;
    @BindView(R.id.rv_frag_donation_reqs)
    RecyclerView rvFragDonationReqs;
    private Unbinder unbinder;
    private int cityId;
    private int bloodTypeId;
    private String apiToken;

    public DonationReqsFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        apiToken = SharedPreferenceManager.loadUserApiToken(getActivity());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_donation_requests, container, false);
        unbinder = ButterKnife.bind(this, view);
        createBloodTypeSpin();
        createCitySpin();
        getDonationReqs();
        return view;
    }

    private void getDonationReqs() {
        DonationsRepository.getInstance().onGetDonationReqsListener = this;
        DonationsRepository.getInstance().getAllDonationReqs(
                apiToken);
    }

    private void createCitySpin() {
        GeneralRepository.getInstance().onGetCitySpinListListener = this;
        GeneralRepository.getInstance().getCitiesList(getActivity(), "0");
    }


    private void createBloodTypeSpin() {
        GeneralRepository.getInstance().onGetBloodTypeSpinListListener = this;
        GeneralRepository.getInstance().getBloodTypesList(getActivity());
    }

    @Override
    public void showCityList(List<GeneralSourceData> CitiesList) {
        ArrayList<GeneralSourceData> citiesArrayList = new ArrayList<>(CitiesList);
        GeneralSourceData dataObject = new GeneralSourceData();
        dataObject.setName(requireActivity().getString(R.string.city3));
        citiesArrayList.add(0, dataObject);
        GeneralAdapter citiesAdapter = new GeneralAdapter(getActivity(), citiesArrayList);
        citySpinFragDonationReqs.setAdapter(citiesAdapter);
    }

    @Override
    public void showBloodTypeList(List<GeneralSourceData> bloodTypesList) {
        ArrayList<GeneralSourceData> bTArrayList = new ArrayList<>(bloodTypesList);
        GeneralSourceData dataObject = new GeneralSourceData();
        dataObject.setName(requireActivity().getString(R.string.blood_type3));
        bTArrayList.add(0, dataObject);
        GeneralAdapter bTAdapter = new GeneralAdapter(getActivity(), bTArrayList);
        bloodTypeSpinFragDonationReqs.setAdapter(bTAdapter);
    }

    @Override
    public void showDonationReqs(List<GeneralSourceData> donationReqList) {
        ArrayList<GeneralSourceData> donationReqsArrayList = new ArrayList<>(donationReqList);
        DonationAdapter donationAdapter = new DonationAdapter(getActivity(), donationReqsArrayList);
        rvFragDonationReqs.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvFragDonationReqs.setAdapter(donationAdapter);
    }

    @Override
    public void showFilteredDonationReq(List<GeneralSourceData> filteredDonationReqList) {
        ArrayList<GeneralSourceData> filteredDonationReqsArrayList = new ArrayList<>(filteredDonationReqList);
        DonationAdapter donationAdapter = new DonationAdapter(getActivity(), filteredDonationReqsArrayList);
        donationAdapter.notifyDataSetChanged();
        rvFragDonationReqs.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvFragDonationReqs.setAdapter(donationAdapter);
    }

    @OnItemSelected({R.id.blood_type_spin_frag_donation_reqs, R.id.city_spin_frag_donation_reqs})
    public void onSpinnerItemSelected(Spinner spinner, int position) {
        switch (spinner.getId()) {
            case R.id.blood_type_spin_frag_donation_reqs: {
                bloodTypeId = position;
                DonationsRepository.getInstance().FilterAllDonationReqs(
                        apiToken, String.valueOf(bloodTypeId), String.valueOf(cityId));
                break;
            }
            case R.id.city_spin_frag_donation_reqs: {
                cityId = position;
                DonationsRepository.getInstance().FilterAllDonationReqs(
                        apiToken, String.valueOf(bloodTypeId), String.valueOf(cityId));
                break;
            }
        }
    }

    @OnClick({R.id.floating_btn_frag_donation_reqs})
    public void onViewClicked(View view) {
        if (view.getId() == R.id.floating_btn_frag_donation_reqs) {
            Fragment fragment = requireActivity().getSupportFragmentManager()
                    .findFragmentById(R.id.container_activity_home);
            if (fragment != null) {
                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.container_activity_home, new DonationReqFormFragment())
                        .addToBackStack(null)
                        .commit();
            }
        }
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


}
