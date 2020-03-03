package com.app.fitv1.Features.Register;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.app.fitv1.Base.BaseActivity;
import com.app.fitv1.Features.Login.LoginActivity;
import com.app.fitv1.ProjectUtils.BaseCallBack;
import com.app.fitv1.R;
import com.app.fitv1.RegisterViewModel;
import com.app.fitv1.WebServices.BasicApiModel;
import com.app.fitv1.databinding.ActivityRegisterBinding;

public class Register extends BaseActivity<ActivityRegisterBinding, RegisterPresenter> implements RegisterView {
    RegisterViewModel registerViewModel;

    public static void start(Context context) {
        Intent starter = new Intent(context, Register.class);
        context.startActivity(starter);
    }

    @Override
    protected int setLayoutId() {
        return R.layout.activity_register;
    }

    @Override
    protected void onCreateActivityG() {
        injectPresenter(new RegisterPresenter());
        getPresenter().attachView(this);
        registerViewModel = new RegisterViewModel(new BaseCallBack<View>() {
            @Override
            public void onCallBack(View view) {
                switch (view.getId()) {
                    case R.id.btnRegister:
                        getPresenter().performRegister();
                        break;
                    case R.id.tvLogin:
                        LoginActivity.start(getActivityG());
                        finish();
                        break;
                }
            }
        });
        binding.setHandler(registerViewModel);
        binding.setData(registerViewModel);
    }

    @Override
    public void initViews() {

    }

    @Override
    public Context getActivityG() {
        return Register.this;
    }

    @Override
    public String email() {
        return registerViewModel.getEmail();
    }

    @Override
    public String password() {
        return registerViewModel.getPassword();
    }

    @Override
    public String name() {
        return registerViewModel.getName();
    }

    @Override
    public Boolean isChecked() {
        return registerViewModel.isChecked();
    }

    @Override
    public void registerResponse(BasicApiModel output) {
        if (output.getStatus()) {
            LoginActivity.start(getActivityG());
            finish();
        }
        displayError(output.getMessage());
    }
}
