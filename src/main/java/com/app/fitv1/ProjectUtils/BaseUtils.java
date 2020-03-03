package com.app.fitv1.ProjectUtils;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityOptions;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.location.LocationManager;
import android.net.Uri;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Environment;
import android.text.Html;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.Pair;
import android.util.TypedValue;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;

import com.app.fitv1.R;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import static android.content.ContentValues.TAG;

public class BaseUtils {
    private static Toast toastG;
    private static ProgressDialog progressDialog;

    /**
     * @param msg    -message to be displayed
     * @param center - true ,if toast is to be displayed in center,otherwise false.
     */
    public static void showToast(String msg, Context context, boolean center) {
        if (toastG != null) {
            toastG.cancel();
        }
        toastG = Toast.makeText(context, msg, Toast.LENGTH_SHORT);
        if (center) {
            toastG.setGravity(Gravity.CENTER, 0, 0);
        }
        toastG.show();
    }

    public static void toggleKeyboard(Context con, View view) {
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) con.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
        }
    }

    public static String get_time_ago(String stringDate) {
        SimpleDateFormat format = new SimpleDateFormat(DateHelper.DateFormat);
        TimeZone timeZone = TimeZone.getTimeZone("UTC");
        format.setTimeZone(timeZone);
        Date date = null;
        try {
            date = format.parse(stringDate);
            System.out.println(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        long diff = System.currentTimeMillis() - date.getTime();
        return millisToLongDHMS(
                diff > 0 ? diff : -(diff)).replaceAll(",", "") + " ago.";
    }

    public final static long ONE_SECOND = 1000;
    public final static long SECONDS = 60;

    public final static long ONE_MINUTE = ONE_SECOND * 60;
    public final static long MINUTES = 60;

    public final static long ONE_HOUR = ONE_MINUTE * 60;
    public final static long HOURS = 24;

    public final static long ONE_DAY = ONE_HOUR * 24;
    public final static long MONTHS = ONE_DAY * 30;

    public static String millisToLongDHMS(long duration) {
        StringBuffer res = new StringBuffer();
        long temp = 0;
        if (duration >= ONE_SECOND) {
            temp = duration / MONTHS;
            if (temp > 0) {
                duration -= temp * MONTHS;
                res.append(temp).append(" month").append(temp > 1 ? "s" : "");
                return res.toString();
            }


            temp = duration / ONE_DAY;
            if (temp > 0) {
                duration -= temp * ONE_DAY;
                res.append(temp).append(" day").append(temp > 1 ? "s" : "");

                return res.toString();
            }

            temp = duration / ONE_HOUR;
            if (temp > 0) {
                duration -= temp * ONE_HOUR;
                res.append(temp).append(" hour").append(temp > 1 ? "s" : "");
                return res.toString();
            }

            temp = duration / ONE_MINUTE;
            if (temp > 0) {
                duration -= temp * ONE_MINUTE;
                res.append(temp).append(" minute").append(temp > 1 ? "s" : "");
                return res.toString();
            }

            if (!res.toString().equals("") && duration >= ONE_SECOND) {
                res.append(" and ");
            }

            temp = duration / ONE_SECOND;
            if (temp > 0) {
                //                res.append(temp).append(" second").append(temp > 1 ? "s" : "");
                res.append("few seconds");
            }
            return res.toString();
        } else {
            return "0 second";
        }
    }


    public static void shakeThisView(View target_view) {
        final AnimatorSet mAnimatorSet = new AnimatorSet();
        mAnimatorSet.playTogether(ObjectAnimator.ofFloat(target_view, "scaleX", 1, 0.7f, 0.7f, 0.7f, 1, 1), ObjectAnimator.ofFloat(target_view, "scaleY", 1, 0.7f, 0.7f, 0.7f, 1, 1), ObjectAnimator.ofFloat(target_view, "rotation", 0, -3, -3, 3, -3, 3, -3, 3, -3, 3, -3, 0));
        mAnimatorSet.setDuration(1000);
        mAnimatorSet.start();
    }

    // this animation will zoon the view from its -60% to 100 zooming position . means from -60% to its origial size
    public synchronized static void zoomUpaBit(View target_view) {
        final AnimatorSet mAnimatorSet = new AnimatorSet();
        mAnimatorSet.playTogether(ObjectAnimator.ofFloat(target_view, "scaleX", 0.6f, 1f), ObjectAnimator.ofFloat(target_view, "scaleY", 0.6f, 1f));
        mAnimatorSet.setDuration(500);
//        mAnimatorSet.setInterpolator(new BounceInterpolator());
        mAnimatorSet.start();
    }

    public static String getNotNullString(String target, String defaultString) {
        return target == null || target.trim().isEmpty() ? defaultString
                : target.equals("null") ? defaultString
                : target.equals("") ? defaultString : target;
    }

    /**
     * finish all the activities from stack.(works only in higher versions).
     */
    public static void finishAll(Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            ((Activity) context).finishAffinity();
        } else {

            ((Activity) context).finish();
        }
    }

    /**
     * @param i    -intent to be fired.
     * @param logo --shareable view. (used shared object for transitions ).
     */
    public static void startTransition(Activity activity, Intent i, View logo) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(activity,
                    Pair.create(logo, Constants.Extras.TRANSITION_NAME_1));
            activity.startActivity(i, options.toBundle());
        } else {
            activity.startActivity(i);
        }
    }

    /**
     * @param i     -intent to be fired.
     * @param view1 --1st shareable view. (used shared object for transitions ).
     * @param view2 --2nd shareable view. (used shared object for transitions ).
     */
    public static void startTransition(Activity activity, Intent i, View view1, View view2) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Pair<View, String> p1 = Pair.create(view1, Constants.Extras.TRANSITION_NAME_1);
            Pair<View, String> p2 = Pair.create(view2, Constants.Extras.TRANSITION_NAME_2);

            ActivityOptions options = ActivityOptions.
                    makeSceneTransitionAnimation(activity, p1, p2);
            activity.startActivity(i, options.toBundle());
        } else {
            activity.startActivity(i);
        }
    }

    /**
     * @return true, if app is running in foreground.
     */
    public static boolean isAppOnForeground(Context context) {
        ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> appProcesses = activityManager.getRunningAppProcesses();
        if (appProcesses == null) {
            return false;
        }
        final String packageName = "gilm.lecture.verb";
        for (ActivityManager.RunningAppProcessInfo appProcess : appProcesses) {
            if (appProcess.importance == ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND && appProcess.processName.equals(packageName)) {
                return true;
            }
        }
        return false;
    }

    /**
     * @param view --current focused view
     */
    public static void hideKeyboard(Context context, View view) {
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }


    public static void shareIntent(Context con, String text) {
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TITLE, con.getString(R.string.app_name));
        sendIntent.putExtra(Intent.EXTRA_TEXT, Html.fromHtml(con.getString(R.string.app_name)));
        sendIntent.setType("text/plain");
        con.startActivity(sendIntent);
    }

    public static void InviteByOtherApp(Context activityG) {
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_SUBJECT, activityG.getString(R.string.app_name));
        sendIntent.putExtra(Intent.EXTRA_TEXT, activityG.getString(R.string.app_name));
        sendIntent.setType("text/plain");
        ((Activity) activityG).startActivity(sendIntent);

    }

    /**
     * @param con
     * @param textView  pass any view having text compatibility. Example : Edittext also extends TextView
     * @param percntage give it followed by "f"
     */
    public static void setTextSizeByPercentage(Context con, TextView textView, float percntage) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((Activity) con).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int width = displayMetrics.widthPixels;

        float textSize = width * percntage;

        textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, (textSize / 100));
    }

    public static String getWifiStrength(Context mContext) {
        final WifiManager wifi = (WifiManager) mContext.getSystemService(Context.WIFI_SERVICE);
        int state = wifi.getWifiState();
        int signalStrangth = 0;
        if (state == WifiManager.WIFI_STATE_ENABLED) {
            List<ScanResult> results = wifi.getScanResults();

            for (ScanResult result : results) {
                if (result.BSSID.equals(wifi.getConnectionInfo().getBSSID())) {
                    int level = WifiManager.calculateSignalLevel(wifi.getConnectionInfo().getRssi(),
                            result.level);
                    int difference = level * 100 / result.level;
                    if (difference >= 100) {
                        signalStrangth = 4;
                    } else if (difference >= 75) {
                        signalStrangth = 3;
                    } else if (difference >= 50) {
                        signalStrangth = 2;
                    } else if (difference >= 25) {
                        signalStrangth = 1;
                    } else {
                        signalStrangth = 0;
                    }
                    Log.e("Values are", "\nDifference :" + difference + " signal state:" + signalStrangth);

                }

            }
        }
        return "" + signalStrangth;
    }

    public static String getMacAddress(Context con) {
        WifiManager manager = (WifiManager) con.getSystemService(Context.WIFI_SERVICE);
        WifiInfo info = manager.getConnectionInfo();
        return info.getMacAddress();
    }



    public static String getScreenShotOf(View view, Context context) throws ClassCastException {
        try {
            /**
             * the screenshot functionality does not works properly in marshmallow in case of full webview screenshot . So we have separated the code for vartious versions.
             */
            if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) {
                view.setDrawingCacheEnabled(true);
                return storeImage(context, view.getDrawingCache());
            } else {
                DisplayMetrics displayMetrics = new DisplayMetrics();
                ((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
                view.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                view.measure(displayMetrics.widthPixels, displayMetrics.heightPixels);
                view.layout(0, 0, displayMetrics.widthPixels, displayMetrics.heightPixels);
                view.buildDrawingCache();
                Bitmap bitmap = Bitmap.createBitmap(view.getMeasuredWidth(), view.getMeasuredHeight(), Bitmap.Config.ARGB_8888);

                Canvas canvas = new Canvas(bitmap);
                view.draw(canvas);
                return storeImage(context, bitmap);
            }
        } catch (Exception e) {
            BaseUtils.showToast("Error while taking screenshot.", context, false);
        }

        return null;
    }

    private static String storeImage(Context context, Bitmap image) {
        File pictureFile = getOutputMediaFile(context);
        if (pictureFile == null) {
            Log.d(TAG,
                    "Error creating media file, check storage permissions: ");// e.getMessage());
            return "";
        }
        try {
            FileOutputStream fos = new FileOutputStream(pictureFile);
            image.compress(Bitmap.CompressFormat.PNG, 90, fos);
            fos.close();

            return pictureFile.getAbsolutePath();
        } catch (FileNotFoundException e) {
            Log.d(TAG, "File not found: " + e.getMessage());
        } catch (IOException e) {
            Log.d(TAG, "Error accessing file: " + e.getMessage());
        }
        return "";
    }

    private static File getOutputMediaFile(Context context) {
        // To be safe, you should check that the SDCard is mounted
        // using Environment.getExternalStorageState() before doing this.
        File mediaStorageDir = new File(Environment.getExternalStorageDirectory()
                + "/Android/data/"
                + context.getApplicationContext().getPackageName()
                + "/Files");

        // This location works best if you want the created images to be shared
        // between applications and persist after your app has been uninstalled.

        // Create the storage directory if it does not exist
        if (!mediaStorageDir.exists()) {
            if (!mediaStorageDir.mkdirs()) {
                return null;
            }
        }
        // Create a media file name
        String timeStamp = new SimpleDateFormat("ddMMyyyy_HHmm").format(new Date());
        File mediaFile;
        String mImageName = "MI_" + timeStamp + ".jpg";
        mediaFile = new File(mediaStorageDir.getPath() + File.separator + mImageName);
        return mediaFile;
    }

    public static void setHeightWidthWithRatio_LinearLayout(Activity activity, View imgv, float height, float width) {
        Display display = activity.getWindowManager().getDefaultDisplay();
        float screenWidth = Math.round(display.getWidth());
        float imageHeight = height;
        float imageWidth = width;
        float target1 = imageHeight / imageWidth;
        float targetHeight = target1 * screenWidth;

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, Math.round(targetHeight));
        imgv.setLayoutParams(params);
        imgv.invalidate();

        Log.e("TARGET HEIGHT", targetHeight + "");
    }

    public static void setHeightWidthWithRatio_FrameLayout(Activity activity, View imgv, float height, float width) {
        Display display = activity.getWindowManager().getDefaultDisplay();
        float screenWidth = Math.round(display.getWidth());
        float imageHeight = height;
        float imageWidth = width;
        float target1 = imageHeight / imageWidth;
        float targetHeight = target1 * screenWidth;

        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, Math.round(targetHeight));
        imgv.setLayoutParams(params);
        imgv.invalidate();

        Log.e("TARGET HEIGHT", targetHeight + "");
    }

    public static void setHeightWidtWRAP_LinearLayout(Activity activity, View imgv, Context con) {
        Display display = activity.getWindowManager().getDefaultDisplay();

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        imgv.setLayoutParams(params);
        imgv.invalidate();
    }

    public static Uri saveBitmapToLocal(Bitmap inImage) {
//        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
//        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
//        String path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), inImage, "Title", null);
//        return Uri.parse(path);

        try {
            ByteArrayOutputStream bytes = new ByteArrayOutputStream();
            inImage.compress(Bitmap.CompressFormat.JPEG, 40, bytes);

            File f = new File(Environment.getExternalStorageDirectory()
                    + File.separator + "thumbnail.jpg");
            f.createNewFile();
            FileOutputStream fo = new FileOutputStream(f);
            fo.write(bytes.toByteArray());

            fo.close();

            return Uri.fromFile(f);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void showLoading(String progressMessage, Context context) {
        if (progressDialog == null) {
            progressDialog = new ProgressDialog(context);
            progressDialog.setTitle(context.getResources().getString(R.string.app_name));
            progressDialog.setMessage(progressMessage);
            progressDialog.setCancelable(false);
        }
        progressDialog.show();
    }

    public static void hideLoading() {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
            progressDialog = null;
        }
    }

    public static String secondsToMinutes(int totalSeconds) {

        final int MINUTES_IN_AN_HOUR = 60;
        final int SECONDS_IN_A_MINUTE = 60;

        int seconds = totalSeconds % SECONDS_IN_A_MINUTE;
        int totalMinutes = totalSeconds / SECONDS_IN_A_MINUTE;
        int minutes = totalMinutes % MINUTES_IN_AN_HOUR;
        int hours = totalMinutes / MINUTES_IN_AN_HOUR;

        if (hours == 0) {
            return (minutes < 10 ? "0" + minutes : minutes)
                    + ":"
                    + (seconds < 10 ? "0" + seconds : seconds);
        } else {
            return (hours < 10 ? "0" + hours : hours)
                    + ":"
                    + (minutes < 10 ? "0" + minutes : minutes)
                    + ":"
                    + (seconds < 10 ? "0" + seconds : seconds);
        }
    }

    public static String encodeEmoji(String message) {
        try {
            return URLEncoder.encode(message,
                    "UTF-8");
        } catch (UnsupportedEncodingException e) {
            return message;
        }
    }


    public static String decodeEmoji(String message) {
        String myString = null;
        try {
            return URLDecoder.decode(
                    message, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            return message;
        }
    }

    public static String QuickLectureType(String quickLectureType, String defaultString) {
        if (quickLectureType.equals("text")) {
            return "Text Post ";
        } else if (quickLectureType.equals("audio")) {
            return "Audio Post ";
        } else if (quickLectureType.equals("video")) {
            return "Presentation Post ";
        } else if (quickLectureType.equals("discovery_audio")) {
            return "Discovery Audio Post ";
        } else if (quickLectureType.equals("discovery_video")) {
            return "Discovery Video Post ";
        } else if (quickLectureType.equals("discovery_text")) {
            return "Discovery Text Post ";
        } else {
            return defaultString;
        }
    }


    public static String getExternalSdCardPath() {
        String path = null;

        File sdCardFile = null;
        List<String> sdCardPossiblePath = Arrays.asList("external_sd", "ext_sd", "external", "extSdCard");

        for (String sdPath : sdCardPossiblePath) {
            File file = new File("/mnt/", sdPath);

            if (file.isDirectory() && file.canWrite()) {
                path = file.getAbsolutePath();

                String timeStamp = new SimpleDateFormat("ddMMyyyy_HHmmss").format(new Date());
                File testWritable = new File(path, "test_" + timeStamp);

                if (testWritable.mkdirs()) {
                    testWritable.delete();
                } else {
                    path = null;
                }
            }
        }

        if (path != null) {
            sdCardFile = new File(path);
        } else {
            sdCardFile = new File(Environment.getExternalStorageDirectory().getAbsolutePath());
        }

        return sdCardFile.getAbsolutePath();
    }

    public static boolean isGpsEnabled(Context con) {
        boolean gps_enabled = false;
        boolean network_enabled = false;
        LocationManager lm = (LocationManager) con.getSystemService(Context.LOCATION_SERVICE);

        try {
            gps_enabled = lm.isProviderEnabled(LocationManager.GPS_PROVIDER);
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            network_enabled = lm.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        if (!gps_enabled)// && !network_enabled)
        {
            return false;
        } else {
            return true;
        }
    }
}
