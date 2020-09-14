package com.example.bloodbank.view.fragment.userCycle;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.bloodbank.R;
import com.example.bloodbank.data.model.forgetpasswrd.Forgetpassword;
import com.example.bloodbank.data.repository.UserRepository;
import com.example.bloodbank.view.fragment.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static androidx.constraintlayout.widget.Constraints.TAG;
import static com.example.bloodbank.utility.HelperMethod.replaceFragment;
import static com.example.bloodbank.data.api.RetrofitClient.getClient;
import static com.example.bloodbank.utility.network.InternetState.isConnected;

/**
 * A simple {@link Fragment} subclass.
 */
public class RestPasswordFragment extends BaseFragment {

    @BindView(R.id.phone_no_et_frag_reset_pass)
    EditText phoneNoEtFragResetPass;
    private Unbinder unbinder;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        initFragment();
        View view = inflater.inflate(R.layout.fragment_rest_password, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @OnClick(R.id.send_btn_frag_reset_pass)
    public void onViewClicked() {
        UserRepository.getMyResetPassPinCode(getActivity()
                , phoneNoEtFragResetPass.getText().toString().trim());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

}
