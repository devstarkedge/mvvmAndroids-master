package com.app.fitv1.Features.ForgotPassword;

import android.util.Patterns;

import com.app.fitv1.Base.BasePresenter;
import com.app.fitv1.ProjectUtils.BaseCallBack;
import com.app.fitv1.WebServices.ApisHelper;
import com.app.fitv1.WebServices.BaseApplication;
import com.app.fitv1.WebServices.BasicApiModel;
import com.google.gson.JsonObject;

class ForgotPasswordPresenter extends BasePresenter<ForgotPasswordView> {
    void performForgotPassword() {
        if (getView().email() == null || getView().email().isEmpty()) {
            getView().displayError("Please enter email");
        } else if (!Patterns.EMAIL_ADDRESS.matcher(getView().email()).matches()) {
            getView().displayError("Please enter valid email");
        } else {
            getView().showLoading("Please wait");
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("email", getView().email());
            createApiRequest(BaseApplication.getRetrofit().create(ApisHelper.class).forgotPassword(jsonObject),
                    new BaseCallBack<BasicApiModel>() {
                        @Override
                        public void onCallBack(BasicApiModel output) {
                            getView().hideLoading();
                            getView().forgotPasswordResponse(output);
                        }
                    });
        }
    }
}
