package com.app.fitv1.Features.ForgotPassword;

import com.app.fitv1.Base.Contract.Viewable;
import com.app.fitv1.WebServices.BasicApiModel;

interface ForgotPasswordView extends Viewable<ForgotPasswordPresenter> {
    String email();

    void forgotPasswordResponse(BasicApiModel output);
}
