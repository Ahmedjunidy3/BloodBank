package com.example.bloodbank.view.fragment.homeCycle.donation;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.example.bloodbank.R;
import com.example.bloodbank.data.api.ApiService;
import com.example.bloodbank.data.api.RetrofitClient;
import com.example.bloodbank.data.model.generalSource.GeneralSourceData;
import com.example.bloodbank.utility.local.SharedPreferenceManager;
import com.example.bloodbank.view.fragment.homeCycle.home.HomeFragment;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DonationReqDetailsFragment extends Fragment {

    @BindView(R.id.fragment_donation_req_details_name)
    TextView fragmentDonationReqDetailsName;
    @BindView(R.id.fragment_donation_req_details_age)
    TextView fragmentDonationReqDetailsAge;
    @BindView(R.id.fragment_donation_req_details_blood_type)
    TextView fragmentDonationReqDetailsBloodType;
    @BindView(R.id.fragment_donation_req_details_no_blood_bags)
    TextView fragmentDonationReqDetailsNoBloodBags;
    @BindView(R.id.fragment_donation_req_details_hospital_name)
    TextView fragmentDonationReqDetailsHospitalName;
    @BindView(R.id.fragment_donation_req_details_address)
    TextView fragmentDonationReqDetailsAddress;
    @BindView(R.id.fragment_donation_req_details_telephone_no)
    TextView fragmentDonationReqDetailsTelephoneNo;
    @BindView(R.id.fragment_donation_req_details_btn_call)
    Button fragmentDonationReqDetailsBtnCall;
    @BindView(R.id.fragment_donation_req_details_header_title)
    TextView fragmentDonationReqDetailsHeaderTitle;
    @BindView(R.id.fragment_donation_req_details_toolbar)
    Toolbar fragmentDonationReqDetailsToolbar;
    @BindView(R.id.fragment_donation_req_details_arrow_back_iv)
    ImageView fragmentDonationReqDetailsArrowBackIv;
    private ApiService apiService;
    private int reqId;
    private String phoneNo;
    private Unbinder unbinder;

    public DonationReqDetailsFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        apiService = RetrofitClient.getClient();
        if (getArguments() != null) {
            reqId = Integer.parseInt(requireArguments()
                    .getString("DONATION_REQ_ID"));
            phoneNo = getArguments().getString("DONATION_REQ_PHONE_NO");
            Toast.makeText(getActivity(), "Req Id: " + reqId, Toast.LENGTH_SHORT).show();
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_donation_req_details, container, false);
        unbinder = ButterKnife.bind(this, view);
        ((AppCompatActivity) requireActivity())
                .setSupportActionBar(fragmentDonationReqDetailsToolbar);
        Objects.requireNonNull(((AppCompatActivity) getActivity())
                .getSupportActionBar()).setTitle("");
        getDonationReqDetails();
        return view;
    }

    private void getDonationReqDetails() {
        Call<GeneralSourceData> donationReqDetailsCall = apiService
                .getDonationDetails(SharedPreferenceManager.loadData(getActivity(), "API_TOKEN")
                        , reqId);
        donationReqDetailsCall.enqueue(new Callback<GeneralSourceData>() {
            @Override
            public void onResponse(@NonNull Call<GeneralSourceData> call, @NonNull Response<GeneralSourceData> response) {
                try {
                    assert response.body() != null;
                    GeneralSourceData dataObject = response.body().getDataObject();
                    fragmentDonationReqDetailsHeaderTitle.setText(requireActivity()
                            .getString(R.string.header_title_donation_req_details_fragment,
                                    dataObject.getPatientName()));
                    fragmentDonationReqDetailsName.setText(getActivity()
                            .getString(R.string.patient_name_donation_fragment, dataObject.getPatientName()));
                    fragmentDonationReqDetailsAge.setText(getActivity()
                            .getString(R.string.patient_age_donation_req_details_fragment, dataObject.getPatientAge()));
                    fragmentDonationReqDetailsBloodType.setText(getActivity()
                            .getString(R.string.patient_blood_type_donation_req_details_fragment
                                    , dataObject.getBloodType().getName()));
                    fragmentDonationReqDetailsNoBloodBags.setText(getActivity()
                            .getString(R.string.patient_no_blood_bags_donation_req_details_fragment
                                    , dataObject.getBagsNum()));
                    fragmentDonationReqDetailsHospitalName.setText(getActivity()
                            .getString(R.string.hospital_name_donation_req_details_fragment
                                    , dataObject.getHospitalName()));
                    fragmentDonationReqDetailsAddress.setText(getActivity()
                            .getString(R.string.hospital_address_donation_req_details_fragment
                                    , dataObject.getHospitalAddress()));
                    fragmentDonationReqDetailsTelephoneNo.setText(getActivity()
                            .getString(R.string.patient_phone_no_donation_req_details_fragment
                                    , dataObject.getPhone()));
                } catch (Exception e) {
                    Log.d("TAG", "Caught Error: " + e.getMessage());
                }
            }

            @Override
            public void onFailure(@NonNull Call<GeneralSourceData> call, @NonNull Throwable t) {
                Toast.makeText(getActivity(), "Failure Msg:" + t.getMessage()
                        , Toast.LENGTH_LONG).show();
            }
        });
    }

    @OnClick({R.id.fragment_donation_req_details_arrow_back_iv, R.id.fragment_donation_req_details_btn_call})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.fragment_donation_req_details_arrow_back_iv: {
                Fragment fragment = requireActivity().getSupportFragmentManager()
                        .findFragmentById(R.id.container_activity_home);
                if (fragment != null) {
                    fragment = new HomeFragment();
                    getActivity().getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.container_activity_home, fragment)
                            .commit();
                }
                break;
            }
            case R.id.fragment_donation_req_details_btn_call: {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:" + phoneNo));
                startActivity(intent);
                break;
            }
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


}
