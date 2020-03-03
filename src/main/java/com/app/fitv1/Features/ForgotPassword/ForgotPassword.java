package com.app.fitv1.Features.ForgotPassword;

import android.content.Context;
import android.content.Intent;
import android.provider.Settings;
import android.view.View;

import com.app.fitv1.Base.BaseActivity;
import com.app.fitv1.ForgetPasswordViewModel;
import com.app.fitv1.ProjectUtils.BaseCallBack;
import com.app.fitv1.ProjectUtils.BaseUtils;
import com.app.fitv1.R;
import com.app.fitv1.WebServices.BasicApiModel;
import com.app.fitv1.databinding.ActivityForgotPasswordBinding;

public class ForgotPassword extends BaseActivity<ActivityForgotPasswordBinding, ForgotPasswordPresenter> implements ForgotPasswordView {
    ForgetPasswordViewModel forgetPasswordViewModel;

    public static void start(Context context) {
        Intent starter = new Intent(context, ForgotPassword.class);
        context.startActivity(starter);
    }

    @Override
    protected int setLayoutId() {
        return R.layout.activity_forgot_password;
    }

    @Override
    protected void onCreateActivityG() {
        injectPresenter(new ForgotPasswordPresenter());
        getPresenter().attachView(this);
        forgetPasswordViewModel = new ForgetPasswordViewModel(new BaseCallBack<View>() {
            @Override
            public void onCallBack(View view) {
                if (view.getId() == R.id.btnSubmit) {
                    getPresenter().performForgotPassword();
                }
            }
        });
        binding.setHandler(forgetPasswordViewModel);
        binding.setData(forgetPasswordViewModel);
    }

    @Override
    public void initViews() {

    }

    @Override
    public Context getActivityG() {
        return ForgotPassword.this;
    }

    @Override
    public String email() {
        return forgetPasswordViewModel.getEmail();
    }

    @Override
    public void forgotPasswordResponse(BasicApiModel output) {
        if (output.getStatus()) {
            BaseUtils.hideKeyboard(getActivityG(), binding.btnSubmit);
            finish();
        }
        displayError(output.getMessage());
    }
}
