package com.app.fitv1.Features.Register;

import android.util.Patterns;

import com.app.fitv1.Base.BasePresenter;
import com.app.fitv1.ProjectUtils.BaseCallBack;
import com.app.fitv1.WebServices.ApisHelper;
import com.app.fitv1.WebServices.BaseApplication;
import com.app.fitv1.WebServices.BasicApiModel;
import com.google.gson.JsonObject;

class RegisterPresenter extends BasePresenter<RegisterView> {
    void performRegister() {
        if (getView().name() == null || getView().name().isEmpty()) {
            getView().displayError("Please enter Full name");
        } else if (getView().email() == null || getView().email().isEmpty()) {
            getView().displayError("Please enter email");
        } else if (! Patterns.EMAIL_ADDRESS.matcher(getView().email()).matches()) {
            getView().displayError("Please enter valid email");
        } else if (getView().password() == null || getView().password().isEmpty()) {
            getView().displayError("Please enter password");
        } else if (getView().password().length() < 8) {
            getView().displayError("Password should contains at'least 8 characters");
        } else if (!getView().isChecked()) {
            getView().displayError("Please accept terms of services and privacy policies");
        } else {
            getView().showLoading("Please wait");
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("email", getView().email());
            jsonObject.addProperty("password", getView().password());
            jsonObject.addProperty("user_name", getView().name());
            createApiRequest(BaseApplication.getRetrofit().create(ApisHelper.class).register(jsonObject),
                    new BaseCallBack<BasicApiModel>() {
                        @Override
                        public void onCallBack(BasicApiModel output) {
                            getView().hideLoading();
                            getView().registerResponse(output);
                        }
                    });
        }
    }
}

