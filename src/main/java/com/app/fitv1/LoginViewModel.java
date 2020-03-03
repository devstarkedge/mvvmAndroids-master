package com.app.fitv1;

import android.view.View;

import com.app.fitv1.ProjectUtils.BaseCallBack;

public class LoginViewModel {
    private final BaseCallBack<View> callBack;
    private String email = "";
    private String password = "";

    public LoginViewModel(BaseCallBack<View> callBack) {
        this.callBack = callBack;
    }


    public void onClick(View view) {
        callBack.onCallBack(view);
    }


    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}