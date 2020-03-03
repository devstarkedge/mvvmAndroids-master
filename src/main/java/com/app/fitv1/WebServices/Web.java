/*
 * Copyright (c) 2017. Code by PRM . Happy coding
 */

package com.app.fitv1.WebServices;

public @interface Web {

    @interface Path {
        String BASE_URL = "https://templatesgroup.com/dev/fitbloomapi/";
        String BASE_IMAGE_URL = "";
    }

    @interface Apis {
        String REGISTER = "register.php";
        String LOGIN = "login.php";
        String FORGOT_PASSWORD = "forgot_password.php";
    }
}
