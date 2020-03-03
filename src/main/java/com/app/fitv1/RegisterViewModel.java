package com.app.fitv1;

import android.view.View;

import com.app.fitv1.ProjectUtils.BaseCallBack;

public class RegisterViewModel {
    private final BaseCallBack<View> callBack;
    private String email = "";
    private String password = "";
    private String name = "";

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

    private String phone = "";
    private boolean isChecked = false;

    public RegisterViewModel(BaseCallBack<View> callBack) {
        this.callBack = callBack;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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


    public void onClick(View view) {
        callBack.onCallBack(view);
    }
}
