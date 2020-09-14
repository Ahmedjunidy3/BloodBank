package com.example.bloodbank.view.fragment.genral;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.example.bloodbank.R;
import com.example.bloodbank.data.repository.GeneralRepository;
import com.example.bloodbank.utility.local.SharedPreferenceManager;
import com.example.bloodbank.view.fragment.homeCycle.home.MoreFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class ContactUsFragment extends Fragment {

    @BindView(R.id.toolbar_frag_contact_us)
    Toolbar toolbarFragContactUs;
    @BindView(R.id.title_et_frag_contact_us)
    EditText titleEtFragContactUs;
    @BindView(R.id.msg_et_frag_contact_us)
    EditText msgEtFragContactUs;
    private Unbinder unbinder;
    private final GeneralRepository generalRepo = new GeneralRepository();

    public ContactUsFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_contact_us
                , container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.arrow_back_iv_frag_contact_us, R.id.send_et_frag_contact_us})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.arrow_back_iv_frag_contact_us: {
                Fragment fragment = requireActivity().getSupportFragmentManager()
                        .findFragmentById(R.id.container_activity_home);
                if (fragment != null) {
                    getActivity().getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.container_activity_home, new MoreFragment())
                            .commit();
                }
                break;
            }
            case R.id.send_et_frag_contact_us: {
                generalRepo.sendMyMessage(getActivity()
                        , SharedPreferenceManager.loadUserApiToken(getActivity())
                        , titleEtFragContactUs.getText().toString().trim()
                        , msgEtFragContactUs.getText().toString().trim());
                break;
            }
        }
    }


}