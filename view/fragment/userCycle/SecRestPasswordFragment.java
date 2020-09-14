package com.example.bloodbank.view.fragment.userCycle;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;


import com.example.bloodbank.R;
import com.example.bloodbank.data.repository.UserRepository;
import com.example.bloodbank.view.fragment.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;


public class SecRestPasswordFragment extends BaseFragment {

    @BindView(R.id.pin_code_et_frag_new_pass)
    EditText pinCodeEtFragNewPass;
    @BindView(R.id.new_pass_et_frag_new_pass)
    EditText newPassEtFragNewPass;
    @BindView(R.id.pass_confirm_et_frag_new_pass)
    EditText passConfirmEtFragNewPass;
    private Unbinder unbinder;
    private String userPhoneNo;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        initFragment();
        View view = inflater.inflate(R.layout.fragment_sec_rest_password, container, false);
        unbinder = ButterKnife.bind(this, view);
        if (getArguments() != null) {
            userPhoneNo = getArguments().getString("USER_REG_PHONE");
        }
        return view;

    }

    @OnClick(R.id.change_btn_frag_new_pass)
    public void onViewClicked() {
        UserRepository.setMyNewPass(getActivity()
                , newPassEtFragNewPass.getText().toString().trim()
                , passConfirmEtFragNewPass.getText().toString().trim()
                , pinCodeEtFragNewPass.getText().toString().trim()
                , userPhoneNo);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
