package com.app.fitv1.Features.Register;

import com.app.fitv1.Base.Contract.Viewable;
import com.app.fitv1.WebServices.BasicApiModel;

interface RegisterView extends Viewable<RegisterPresenter> {
    String email();

    String password();

    String name();

    Boolean isChecked();

    void registerResponse(BasicApiModel output);
}
