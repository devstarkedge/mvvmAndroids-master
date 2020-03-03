package com.app.fitv1.Features.Login;

import com.app.fitv1.Base.Contract.Viewable;

interface LoginView extends Viewable<LoginPresenter> {
    String email();

    String password();

    void loginResponse(LoginResponse output);
}
