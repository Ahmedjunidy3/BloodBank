package com.example.bloodbank.view.fragment.userCycle;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;

import androidx.fragment.app.Fragment;

import com.example.bloodbank.R;
import com.example.bloodbank.data.repository.UserRepository;
import com.example.bloodbank.utility.local.SharedPreferenceManager;
import com.example.bloodbank.view.activity.HomeActivity;
import com.example.bloodbank.view.fragment.BaseFragment;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends Fragment {


    @BindView(R.id.phone_no_et_frag_login)
    EditText phoneNoEtFragLogin;
    @BindView(R.id.pass_et_frag_login)
    EditText passEtFragLogin;
    @BindView(R.id.remember_cb_frag_login)
    CheckBox rememberCbFragLogin;
    private Unbinder unbinder;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        unbinder = ButterKnife.bind(this, view);
        checkLoginDetails();
        return view;


    }

    private void checkLoginDetails() {
        String userRegPhone = SharedPreferenceManager.loadUserPhoneNo(getActivity());
        String userRegPass = SharedPreferenceManager.loadUserPassword(getActivity());
        if (userRegPhone != null && userRegPass != null) {
            phoneNoEtFragLogin.setText(userRegPhone);
            passEtFragLogin.setText(userRegPass);
            rememberCbFragLogin.setChecked(true);
            Intent intent = new Intent(getActivity(), HomeActivity.class);
            startActivity(intent);
        }
    }

        @OnClick({R.id.remember_cb_frag_login, R.id.reset_pass_tv_frag_login
                , R.id.login_btn_frag_login, R.id.register_tv_frag_login})
        public void onViewClicked (View view){
            String userPhoneNo = getUserPhoneNo();
            String userPass = getUserPass();
            switch (view.getId()) {
                case R.id.remember_cb_frag_login: {
                    if (rememberCbFragLogin.isChecked()) {
                        if (userPhoneNo.length() > 0 && userPass.length() > 0) {
                            SharedPreferenceManager.saveUserPhoneNo(getActivity(), userPhoneNo);
                            SharedPreferenceManager.saveUserPassword(getActivity(), userPass);
                        }
                    } else {
                        SharedPreferenceManager.removeUserLoginDetails(getActivity());
                    }
                    break;
                }
                case R.id.reset_pass_tv_frag_login: {
                    Fragment fragment = requireActivity().getSupportFragmentManager()
                            .findFragmentById(R.id.container_activity_login);
                    if (fragment != null) {
                        getActivity().getSupportFragmentManager()
                                .beginTransaction()
                                .replace(R.id.container_activity_login, new RestPasswordFragment())
                                .addToBackStack(null)
                                .commit();
                    }
                    break;
                }
                case R.id.login_btn_frag_login: {
                    UserRepository.checkMyLoginDetails(getActivity(), userPhoneNo, userPass);
                    break;
                }
                case R.id.register_tv_frag_login: {
                    Fragment fragment = requireActivity().getSupportFragmentManager()
                            .findFragmentById(R.id.container_activity_login);
                    if (fragment != null) {
                        getActivity().getSupportFragmentManager()
                                .beginTransaction()
                                .replace(R.id.container_activity_login, new RegisterFragment())
                                .commit();
                    }
                    break;
                }
            }
        }

        private String getUserPass () {
            return passEtFragLogin.getText().toString().trim();
        }

        private String getUserPhoneNo () {
            return phoneNoEtFragLogin.getText().toString().trim();
        }

        @Override
        public void onDestroyView () {
            super.onDestroyView();
            unbinder.unbind();
        }


//            case R.id.login_fragment_btn_login:
//                if (isConnected(getActivity())) {
//                    getClient().getLogin(loginFragmentEtNumber.getText().toString(), Integer.parseInt(loginFragmentEtPassword.getText().toString())).enqueue(new Callback<Login>() {
//                        @Override
//                        public void onResponse(Call<Login> call, Response<Login> response) {
//                            try {
//                                if (response.body().getStatus() == 1) {
//                                    Intent in = new Intent(getActivity(), HomeActivity.class);
//                                    startActivity(in);
//                                }
//                            } catch (Exception e) {
//
//                            }
//
//                        }
//
//                        @Override
//                        public void onFailure(Call<Login> call, Throwable t) {
//                            Log.d(TAG, "onFailure" + t.toString());
//
//                        }
//                    });
//                }
//                break;
//            case R.id.login_fragment_tv_register:
//                replaceFragment(getActivity().getSupportFragmentManager(), R.id.container_activity_login, new RegisterFragment());
//
//                break;
//        }
//    }


}
