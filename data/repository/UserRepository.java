package com.example.bloodbank.data.repository;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;


import com.example.bloodbank.R;
import com.example.bloodbank.data.model.login.Login;
import com.example.bloodbank.utility.local.SharedPreferenceManager;
import com.example.bloodbank.view.activity.HomeActivity;
import com.example.bloodbank.view.activity.UserActivity;
import com.example.bloodbank.view.fragment.userCycle.SecRestPasswordFragment;
import com.google.firebase.iid.FirebaseInstanceId;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.bloodbank.data.api.RetrofitClient.getClient;

public abstract class UserRepository {

    public static void checkMyLoginDetails(Activity activity, String loginPhoneNo, String loginPass) {
        getClient().checkLoginDetails(loginPhoneNo, loginPass).enqueue(new Callback<Login>() {
            @Override
            public void onResponse(@NonNull Call<Login> call, @NonNull Response<Login> response) {
                try {
                    Login loginResponse = response.body();
                    if (loginResponse != null && loginResponse.getStatus() == 1) {
                        Intent intent = new Intent(activity, HomeActivity.class);
                        activity.startActivity(intent);
                        SharedPreferenceManager.saveUserApiToken(activity
                                , loginResponse.getLoginData().getApiToken());
                        if (SharedPreferenceManager.loadUserDeviceToken(activity) == null) {
                            registerMyDeviceToken(activity);
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(@NonNull Call<Login> call, @NonNull Throwable t) {
                Toast.makeText(activity, "Response Failure: " + t.getMessage()
                        , Toast.LENGTH_LONG).show();

            }
        });
    }

    public static void getMyResetPassPinCode(Activity activity, String phoneNo) {
        getClient().resetUserPass(phoneNo).enqueue(new Callback<Login>() {
            @Override
            public void onResponse(@NonNull Call<Login> call, @NonNull Response<Login> response) {
                try {
                    Login pinCodeResponse = response.body();
                    if (pinCodeResponse != null) {
                        if (pinCodeResponse.getStatus() == 1) {
                            Toast.makeText(activity, pinCodeResponse.getMsg()
                                    , Toast.LENGTH_SHORT).show();
                            Fragment fragment = ((AppCompatActivity) activity).getSupportFragmentManager()
                                    .findFragmentById(R.id.container_activity_login);
                            if (fragment != null) {
                                fragment = new SecRestPasswordFragment();
                                Bundle bundle = new Bundle();
                                bundle.putString("USER_REG_PHONE", phoneNo);
                                fragment.setArguments(bundle);
                                ((AppCompatActivity) activity).getSupportFragmentManager()
                                        .beginTransaction()
                                        .replace(R.id.container_activity_login, fragment)
                                        .commit();
                            }
                        }
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(@NonNull Call<Login> call, @NonNull Throwable t) {
                Toast.makeText(activity, "Failure Msg: " + t.getMessage()
                        , Toast.LENGTH_SHORT).show();
            }
        });
    }

    public static void setMyNewPass(Activity activity, String pass, String passConfirm
            , String pinCode, String phoneNo) {
        getClient().setUserNewPass(pass, passConfirm, pinCode, phoneNo).enqueue(new Callback<Login>() {
            @Override
            public void onResponse(@NonNull Call<Login> call, @NonNull Response<Login> response) {
                try {
                    Login newPassResponse = response.body();
                    if (newPassResponse != null) {
                        if (newPassResponse.getStatus() == 1) {
                            Toast.makeText(activity, newPassResponse.getMsg()
                                    , Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(activity, newPassResponse.getMsg()
                                    , Toast.LENGTH_SHORT).show();
                        }
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(@NonNull Call<Login> call, @NonNull Throwable t) {
                Toast.makeText(activity, "Failure Msg: " + t.getMessage()
                        , Toast.LENGTH_SHORT).show();
            }
        });
    }

    public static void setMySignUpDetails(Activity activity, String name, String email
            , String birthDate, String cityId, String phoneNo, String lastDonateDate
            , String pass, String passConfirm, String bloodType) {
        getClient().setUserRegisteredDetails(name, email, birthDate, cityId, phoneNo, lastDonateDate
                , pass, passConfirm, bloodType)
                .enqueue(new Callback<Login>() {
                    @Override
                    public void onResponse(@NonNull Call<Login> call, @NonNull Response<Login> response) {
                        try {
                            Login signUpResponse = response.body();
                            if (signUpResponse != null) {
                                if (signUpResponse.getStatus() == 1) {
                                    Toast.makeText(activity, signUpResponse.getMsg()
                                            , Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(activity, UserActivity.class);
                                    activity.startActivity(intent);
                                    registerMyDeviceToken(activity);
                                } else {
                                    Toast.makeText(activity, signUpResponse.getMsg()
                                            , Toast.LENGTH_SHORT).show();
                                }
                            }

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<Login> call, @NonNull Throwable t) {
                        Toast.makeText(activity, "Failure Msg: " + t.getMessage()
                                , Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private static void registerMyDeviceToken(Activity activity) {
        FirebaseInstanceId.getInstance().getInstanceId()
                .addOnCompleteListener(task -> {
                    if (!task.isSuccessful()) {
                        return;
                    }
                    String token = Objects.requireNonNull(task.getResult()).getToken();
                    getClient().registerDeviceToken(token
                            , SharedPreferenceManager.loadUserApiToken(activity)
                            , "android").enqueue(new Callback<Login>() {
                        @Override
                        public void onResponse(@NonNull Call<Login> call, @NonNull Response<Login> response) {
                            try {
                                Login tokenRegResponse = response.body();
                                if (tokenRegResponse != null) {
                                    if (tokenRegResponse.getStatus() == 1) {
                                        SharedPreferenceManager.saveUserDeviceToken(activity, token);
                                    }
                                }

                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }

                        @Override
                        public void onFailure(@NonNull Call<Login> call, @NonNull Throwable t) {
                            Toast.makeText(activity, "Failure Msg: " + t.getMessage()
                                    , Toast.LENGTH_SHORT).show();
                        }
                    });

                });
    }


}
