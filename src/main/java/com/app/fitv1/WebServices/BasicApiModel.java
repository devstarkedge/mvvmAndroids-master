/*
 * Copyright (c) 2017. Code by PRM . Happy coding
 */

package com.app.fitv1.WebServices;

import com.google.gson.annotations.SerializedName;

public class BasicApiModel {
    @SerializedName("status")
    private boolean status;

    @SerializedName("message")
    private String message;

    public boolean getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }
}
