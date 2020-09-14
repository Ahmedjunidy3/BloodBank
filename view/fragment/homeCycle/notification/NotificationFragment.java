package com.example.bloodbank.view.fragment.homeCycle.notification;

import androidx.fragment.app.Fragment;

import com.example.bloodbank.view.fragment.BaseFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class NotificationFragment extends BaseFragment {

//    @BindView(R.id.notification_fragment_rv_notification_list)
//    RecyclerView notificationFragmentRvNotificationList;
//    Unbinder unbinder;
//    private LinearLayoutManager linearLayoutManager;
//    private List<NotificationData> notificationsDataList = new ArrayList<>();
//    private NotificationAdapter notificationAdapter;
//    private OnEndLess onEndLess;
//    private int maxPage = 0;
//
//    public NotificationFragment() {
//        // Required empty public constructor
//    }
//
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        // Inflate the layout for this fragment
//        initFragment();
//        View view = inflater.inflate(R.layout.fragment_notification, container, false);
//        unbinder = ButterKnife.bind(this , view);
//         init();
//
//        return view;
//    }
//
//    private void init() {
//         linearLayoutManager = new LinearLayoutManager(getActivity());
//         notificationFragmentRvNotificationList.setLayoutManager(linearLayoutManager);
//
//        onEndLess = new OnEndLess(linearLayoutManager, 1) {
//            @Override
//            public void onLoadMore(int current_page) {
//                if (current_page <= maxPage) {
//                    if (maxPage != 0 && current_page != 1) {
//                        onEndLess.previous_page = current_page;
//                            getNotification(current_page);
//                    } else {
//                        onEndLess.current_page = onEndLess.previous_page;
//                    }
//                }
//            }
//        };
//         notificationFragmentRvNotificationList.addOnScrollListener(onEndLess);
//
//        notificationAdapter = new NotificationAdapter(getActivity() , notificationsDataList);
//         notificationFragmentRvNotificationList.setAdapter(notificationAdapter);
//
//         getNotification(1);
//    }
//
//    private void getNotification(int page) {
//      getClient().getgNotification("W4mx3VMIWetLcvEcyF554CfxjZHwdtQldbdlCl2XAaBTDIpNjKO1f7CHuwKl",page).enqueue(new Callback<Notifications>() {
//          @Override
//          public void onResponse(Call<Notifications> call, Response<Notifications> response) {
//            try {
//                if (response.body().getStatus() == 1){
//                    maxPage=response.body().getData().getLastPage();
//                    notificationsDataList.addAll(response.body().getData().getData());
//                    notificationAdapter.notifyDataSetChanged();
//                }
//            }catch (Exception e){
//
//            }
//
//          }
//
//          @Override
//          public void onFailure(Call<Notifications> call, Throwable t) {
//
//          }
//      });
//    }
//
//    @Override
//    public void onDestroyView() {
//        super.onDestroyView();
//        unbinder.unbind();
//    }
}
