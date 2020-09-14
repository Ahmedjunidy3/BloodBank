package com.example.bloodbank.view.fragment.homeCycle.notification;

import com.example.bloodbank.view.fragment.BaseFragment;

public class NotificationSettingFragment extends BaseFragment {

//    @BindView(R.id.notification_setting_fragment_tv_title)
//    TextView notificationSettingFragmentTvTitle;
//    @BindView(R.id.notification_setting_fragment_rv_blood_type)
//    RecyclerView notificationSettingFragmentRvBloodType;
//    @BindView(R.id.notification_setting_fragment_rl_blood_gone)
//    RelativeLayout notificationSettingFragmentRlBloodGone;
//    @BindView(R.id.notification_setting_fragment_iv)
//    ImageView notificationSettingFragmentIv;
//    @BindView(R.id.notification_setting_fragment_rl_blood_types)
//    RelativeLayout notificationSettingFragmentRlBloodTypes;
//    @BindView(R.id.notification_setting_fragment_rl_bloods)
//    RelativeLayout notificationSettingFragmentRlBloods;
//    @BindView(R.id.notification_setting_fragment_rv_governorate)
//    RecyclerView notificationSettingFragmentRvGovernorate;
//    @BindView(R.id.notification_setting_fragment_rl_governorate_gone)
//    RelativeLayout notificationSettingFragmentRlGovernorateGone;
//    @BindView(R.id.notification_setting_fragment_iv2)
//    ImageView notificationSettingFragmentIv2;
//    @BindView(R.id.notification_setting_fragment_rl_governorate)
//    RelativeLayout notificationSettingFragmentRlGovernorate;
//    @BindView(R.id.notification_setting_fragment_rl)
//    RelativeLayout notificationSettingFragmentRl;
//    @BindView(R.id.notification_setting_fragment_btn_save)
//    Button notificationSettingFragmentBtnSave;
//    @BindView(R.id.notification_setting_fragment_rl_sub_view)
//    RelativeLayout notificationSettingFragmentRlSubView;
//    private List<String> bloodTypes = new ArrayList<>(), governorates = new ArrayList<>();
//
//    private NotificationSettingAdapter bloodAdpter, governoratesAdpter;
//
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        // Inflate the layout for this fragment
//        initFragment();
//        View view = inflater.inflate(R.layout.fragment_notification_setting, container, false);
//        ButterKnife.bind(this, view);
//        init();
//        getNotifcation();
//        return view;
//
//    }
//
//    private void init() {
//        notificationSettingFragmentRvBloodType.setLayoutManager(new GridLayoutManager(getActivity(), 3));
//        notificationSettingFragmentRvGovernorate.setLayoutManager(new GridLayoutManager(getActivity(), 3));
//
//    }
//
//    private void getNotifcation() {
//
//        getClient().getNotificationsSettings("W4mx3VMIWetLcvEcyF554CfxjZHwdtQldbdlCl2XAaBTDIpNjKO1f7CHuwKl").enqueue(new Callback<NotificationsSettings>() {
//            @Override
//            public void onResponse(Call<NotificationsSettings> call, Response<NotificationsSettings> response) {
//                try {
//                    if (response.body().getStatus() == 1) {
//                        bloodTypes = response.body().getData().getBloodTypes();
//                        governorates = response.body().getData().getGovernorates();
//                        getBloodTypes();
//                        getGovernorates();
//                    }
//
//                } catch (Exception e) {
//
//                }
//            }
//
//            @Override
//            public void onFailure(Call<NotificationsSettings> call, Throwable t) {
//
//            }
//        });
//    }
//
//    private void getGovernorates() {
//        getClient().getgovernorate().enqueue(new Callback<GeneralResponse>() {
//            @Override
//            public void onResponse(Call<GeneralResponse> call, Response<GeneralResponse> response) {
//
//                try {
//                    governoratesAdpter = new NotificationSettingAdapter(getActivity(), (BaseActivity) getActivity(), response.body().getData(), governorates);
//                    notificationSettingFragmentRvGovernorate.setAdapter(governoratesAdpter);
//                } catch (Exception e) {
//
//                }
//
//            }
//
//            @Override
//            public void onFailure(Call<GeneralResponse> call, Throwable t) {
//
//            }
//        });
//    }
//
//    private void getBloodTypes() {
//        getClient().getBloodTypes().enqueue(new Callback<GeneralResponse>() {
//            @Override
//            public void onResponse(Call<GeneralResponse> call, Response<GeneralResponse> response) {
//                try {
//                    bloodAdpter = new NotificationSettingAdapter(getActivity(), (BaseActivity) getActivity(), response.body().getData(), bloodTypes);
//                    notificationSettingFragmentRvBloodType.setAdapter(bloodAdpter);
//                } catch (Exception e) {
//
//                }
//
//            }
//
//            @Override
//            public void onFailure(Call<GeneralResponse> call, Throwable t) {
//
//            }
//        });
//    }
//
//    private void onCall(final boolean state) {
//        getClient().setNotificationsSettings("W4mx3VMIWetLcvEcyF554CfxjZHwdtQldbdlCl2XAaBTDIpNjKO1f7CHuwKl", governoratesAdpter.newIds, bloodAdpter.newIds).enqueue(new Callback<NotificationsSettings>() {
//            @Override
//            public void onResponse(Call<NotificationsSettings> call, Response<NotificationsSettings> response) {
//                try {
//                    if (response.body().getStatus() == 1) {
//                        Toast.makeText(baseActivity, response.body().getMsg(), Toast.LENGTH_SHORT).show();
//                    }
//
//                } catch (Exception e) {
//
//                }
//            }
//
//            @Override
//            public void onFailure(Call<NotificationsSettings> call, Throwable t) {
//
//            }
//        });
//    }
//
//    @Override
//    public void onBack() {
//        super.onBack();
//    }
//
//    @OnClick({R.id.notification_setting_fragment_iv, R.id.notification_setting_fragment_iv2, R.id.notification_setting_fragment_btn_save, R.id.notification_setting_fragment_rl_sub_view})
//    public void onViewClicked(View view) {
//        HelperMethod.disappearKeypad(getActivity(), getView());
//        switch (view.getId()) {
//            case R.id.notification_setting_fragment_iv:
//                visible(notificationSettingFragmentRlBloodGone , notificationSettingFragmentIv);
//                break;
//            case R.id.notification_setting_fragment_iv2:
//                visible(notificationSettingFragmentRlGovernorateGone , notificationSettingFragmentIv2);
//                break;
//            case R.id.notification_setting_fragment_btn_save:
//                onCall(false);
//                break;
//            case R.id.notification_setting_fragment_rl_sub_view:
//                break;
//        }
//    }
//
//    private void visible(View view, ImageView imageView) {
//        if (view.getVisibility() == View.GONE){
//            view.setVisibility(View.VISIBLE);
//            imageView.setImageResource(R.drawable.addiconred);
//        }else{imageView.setImageResource(R.drawable.addiconred);
//            view.setVisibility(View.VISIBLE);
//        }
//    }
}
