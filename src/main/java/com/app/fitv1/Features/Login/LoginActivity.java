package com.app.fitv1.Features.Login;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.app.fitv1.Base.BaseActivity;
import com.app.fitv1.Features.ForgotPassword.ForgotPassword;
import com.app.fitv1.Features.Navigations.Navigation;
import com.app.fitv1.Features.Register.Register;
import com.app.fitv1.LoginViewModel;
import com.app.fitv1.ProjectUtils.BaseCallBack;
import com.app.fitv1.R;
import com.app.fitv1.databinding.ActivityLoginBinding;

public class LoginActivity extends BaseActivity<ActivityLoginBinding, LoginPresenter> implements LoginView {
    LoginViewModel loginViewModel;

    public static void start(Context context) {
        Intent starter = new Intent(context, LoginActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected int setLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void onCreateActivityG() {
        injectPresenter(new LoginPresenter());
        getPresenter().attachView(this);
        loginViewModel = new LoginViewModel(new BaseCallBack<View>() {
            @Override
            public void onCallBack(View view) {
                switch (view.getId()) {
                    case R.id.tvRegister:
                        Register.start(view.getContext());
                        finish();
                        break;
                    case R.id.tvForgotPassword:
                        ForgotPassword.start(view.getContext());
                        break;
                    case R.id.btnLogin:
                        getPresenter().performLogin();
                        break;
                }
            }
        });
        binding.setHandler(loginViewModel);
        binding.setData(loginViewModel);

    }

    @Override
    public void initViews() {
    }

    @Override
    public Context getActivityG() {
        return LoginActivity.this;
    }

    @Override
    public String email() {
        return loginViewModel.getEmail();
    }

    @Override
    public String password() {
        return loginViewModel.getPassword();
    }

    @Override
    public void loginResponse(LoginResponse output) {
        if (output.isStatus()) {
            getLocalData().setUserData(output);
            Navigation.start(getActivityG());
            finish();
        }
        displayError(output.getMessage());
    }
}
