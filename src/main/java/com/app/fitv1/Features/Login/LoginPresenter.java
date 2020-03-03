package com.app.fitv1.Features.Login;

import android.util.Patterns;

import com.app.fitv1.Base.BasePresenter;
import com.app.fitv1.ProjectUtils.BaseCallBack;
import com.app.fitv1.WebServices.ApisHelper;
import com.app.fitv1.WebServices.BaseApplication;
import com.google.gson.JsonObject;

class LoginPresenter extends BasePresenter<LoginView> {
    void performLogin() {
        if (getView().email() == null || getView().email().isEmpty()) {
            getView().displayError("Please enter email");
        } else if (!Patterns.EMAIL_ADDRESS.matcher(getView().email()).matches()) {
            getView().displayError("Please enter valid email");
        } else if (getView().password() == null || getView().password().isEmpty()) {
            getView().displayError("Please enter password");
        } else if (getView().password().length() < 8) {
            getView().displayError("Password should contains at'least 8 characters");
        } else {
            getView().showLoading("Please wait");
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("email", getView().email());
            jsonObject.addProperty("password", getView().password());
            createApiRequest(BaseApplication.getRetrofit().create(ApisHelper.class).login(jsonObject),
                    new BaseCallBack<LoginResponse>() {
                        @Override
                        public void onCallBack(LoginResponse output) {
                            getView().hideLoading();
                            getView().loginResponse(output);
                        }
                    });
        }
    }
}
