package com.app.fitv1.ProjectUtils;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

// HomeHelp
@Retention(RetentionPolicy.SOURCE)
public @interface Constants
{

    @interface Extras
    {
        String TRANSITION_NAME_1 = "name";
        String TRANSITION_NAME_2 = "name2";
    }

    @interface RequestCode
    {
        int CAMERA = 11;
        int GALLERY = 22;
        int PLACE_PICKER = 101;
        int COLOR_PICKER = 202;
        int IMAGE_SEARCH = 303;
        int INVITE = 404;
        int IMAGESEARCH = 505;
        int GP_SIGN_IN = 606;
        int SCREEN_RECORDER = 707;
        int DISCOVERY = 808;
        int RECORD_PPT_LECTURE = 909;
        int PPT_SELECTOR = 111;
        int MULTIPLE_IMAGE_PICKER = 222;
        int WEBVIEW_RECORDING = 333;
        int AUDIO_PLAYER = 444;
        int INTEREST_SELECTION = 555;
        int PAYPAL_PAYMENT = 666;
        int EMAIL_VERIFICATION = 777;
        int JOIN_GROUP = 888;
        int MINIMIZED_PLAYER_PERMISSION = 999;
        int FIREBASE_INVITE = 1010;
        int GPS_LOCATION = 1111;
        int CoHostRequestCode = 2222;
        int LecturerSelectionRequestCode = 3333;
    }

    @interface NotificationFlags
    {
        String like_unlike_lecture = "3";
        String add_to_favourite_list = "4";
        String lecture_write_comment = "5";
        String write_comment_reply = "5";
        String block_unblock_user = "6";
        String event_attend_and_interest = "7";
        String send_event_invitation = "8";
        String share = "9";
        String chat = "10";
        String remove_from_favorite = "11";
        String following = "12";
        String unfollowing = "13";
        String reposted = "14";
        String unreposted = "15";
        String new_quick_lecture = "16";
        String new_lecture_event = "17";
        String update_lecture_event = "18";
        String notification_start_stop_recording_command = "19";
        String notification_stop_recording = "20";
    }

}
