package com.app.fitv1.ProjectUtils;

import android.content.Context;
import android.content.SharedPreferences;

import com.app.fitv1.Features.Login.LoginResponse;

public class SharedPrefHelper
{

    private static final String PREF_NAME = "FitV1";
    private final Context context;

    private SharedPreferences.Editor edit;
    private SharedPreferences sharedPreferences;
    private String USERNAME = "UserName";
    private String USER_ID = "UserId";
    private String EMAIL = "Email";
    private String PROFILE_PIC = "profile_pic";
    private String PROFILEPICCover = "coverProfilePic";
    private String PHONE = "phone";
    private String GENDER = "gender";

    public SharedPrefHelper(Context context) {
        this.context = context;
        sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        edit = sharedPreferences.edit();
    }

    public void setUserData(LoginResponse userData) {
        setUserName(userData.data.getUser_name());
        setUserId(userData.data.getUser_id());
        setEmail(userData.data.getEmail());
        setProfilePic(userData.data.getProfile_pic());
        setCoverProfilePic(userData.data.getCover_pic());
        setDeviceToken(context, userData.data.getDevice_id());
        setPhone(userData.data.getPhone());
        setGender(userData.data.getGender());
    }


    public void setPhone(String phone) {
        edit.putString(PHONE, phone);
        edit.apply();
    }

    public String getPhone() {
        return sharedPreferences.getString(PHONE, "");
    }


    public void setGender(String gender) {
        edit.putString(GENDER, gender);
        edit.apply();
    }

    public String getGender() {
        return sharedPreferences.getString(GENDER, "");
    }


    public void setEmail(String Email) {
        edit.putString(EMAIL, Email);
        edit.apply();
    }

    public String getEmail() {
        return sharedPreferences.getString(EMAIL, "");
    }

    public void setUserName(String UserName) {
        edit.putString(USERNAME, UserName);
        edit.apply();
    }

    public String getUserName() {
        return sharedPreferences.getString(USERNAME, "");
    }

    public void setProfilePic(String ProfilePic) {
        edit.putString(PROFILE_PIC, ProfilePic);
        edit.apply();
    }

    public String getProfilePic() {
        return sharedPreferences.getString(PROFILE_PIC, "");
    }

    public void setCoverProfilePic(String ProfilePic) {
        edit.putString(PROFILEPICCover, ProfilePic);
        edit.apply();
    }

    public String getCoverProfilePic() {
        String string = sharedPreferences.getString(PROFILEPICCover, "");
        return string.isEmpty() ? getProfilePic() : string;
    }

    public void setUserId(String UserId) {
        edit.putString(USER_ID, UserId);
        edit.apply();
    }

    public String getUserId() {
        return sharedPreferences.getString(USER_ID, "");
    }

    //    device token stuff start
    public void setDeviceToken(Context context, String DeviceToken) {
        SharedPreferences.Editor editDeviceToken;
        editDeviceToken = context.getSharedPreferences(PREF_NAME + "DeviceToken", Context.MODE_PRIVATE).edit();
        editDeviceToken.putString("DeviceToken", DeviceToken);
        editDeviceToken.apply();
    }

    public String getDeviceToken(Context context) {
        return context.getSharedPreferences(PREF_NAME + "DeviceToken", Context.MODE_PRIVATE).getString("DeviceToken", "");
    }
//    device token stuff end

    public void logOut() {
        edit.clear();
        edit.apply();
    }

    public void setMyLatitude(String latitude) {
        edit.putString("MyLatitude", latitude);
        edit.apply();
    }

    public String getMyLatitude() {
        return sharedPreferences.getString("MyLatitude", "0.000");
    }

    public void setMyLongitude(String myLongitude) {
        edit.putString("MyLongitude", myLongitude);
        edit.apply();
    }

    public String getMyLongitude() {
        return sharedPreferences.getString("MyLongitude", "0.000");
    }

    public void setMaxKmRange(int maxRangeNow) {
        edit.putInt("maxKmRange", maxRangeNow);
        edit.apply();
    }

    public int getMaxKmRange() {
        return sharedPreferences.getInt("maxKmRange", 5);
    }
}
