package com.app.fitv1.Features.Login;

public class LoginResponse {
    public Data data;

    private String message = "";
    private boolean status = false;

    public boolean isStatus() {
        return status;
    }



    public String getMessage() {
        return message;
    }

    public class Data {
        private String user_name = "";
        private String email = "";
        private String gender = "";
        private String user_id = "";
        private String profile_pic = "";

        public String getProfile_pic() {
            return profile_pic;
        }

        public String getCover_pic() {
            return cover_pic;
        }

        private String cover_pic = "";

        public String getUser_id() {
            return user_id;
        }

        public String getUser_name() {
            return user_name;
        }

        public String getEmail() {
            return email;
        }

        public String getGender() {
            return gender;
        }

        public String getDevice_id() {
            return device_id;
        }

        public String getPhone() {
            return phone;
        }

        public String getAddress() {
            return address;
        }

        public String getUser_type() {
            return user_type;
        }

        public String getExpert_features() {
            return expert_features;
        }

        private String device_id = "";
        private String phone = "";
        private String address = "";
        private String user_type = "";
        private String expert_features = "";
    }
}
