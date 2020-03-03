/*
 * Copyright (c) 2017. Code by PRM . Happy coding
 */

package com.app.fitv1.WebServices;


import com.app.fitv1.Features.Login.LoginResponse;
import com.google.gson.JsonObject;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApisHelper {
    @POST(Web.Apis.REGISTER)
    Observable<BasicApiModel> register(@Body JsonObject object);


    @POST(Web.Apis.FORGOT_PASSWORD)
    Observable<BasicApiModel> forgotPassword(@Body JsonObject object);

    @POST(Web.Apis.LOGIN)
    Observable<LoginResponse> login(@Body JsonObject object);


}
