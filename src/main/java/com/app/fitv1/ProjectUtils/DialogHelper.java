package com.app.fitv1.ProjectUtils;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;

import com.app.fitv1.R;

import java.io.IOException;

public class DialogHelper {
    private static final DialogHelper ourInstance = new DialogHelper();

    public static DialogHelper getInstance() {
        return ourInstance;
    }

    private DialogHelper() {
    }

    public void reportAbuse(Context context, String title, final BaseCallBack<String> baseCallBack) {
        final CharSequence[] reportMessage = {"Spam", "Fake Account", "Inappropriate content", "Other"};

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(title);
        builder.setItems(reportMessage, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, final int item) {
                baseCallBack.onCallBack(reportMessage[item].toString());
            }
        });
        builder.show();
    }

    public void showInformation(Context context, String message, final BaseCallBack<String> baseCallBack) {
        showInformation(context, context.getResources().getString(R.string.app_name), message, baseCallBack);
    }

    public void showInformation(Context context, String title, String message, final BaseCallBack<String> baseCallBack) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setCancelable(false);
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (baseCallBack != null) {
                    baseCallBack.onCallBack("");
                }
            }
        });

        AlertDialog dialog = builder.create();
//        dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        dialog.show();
    }

    public void showWithAction(Context context, String message, final BaseCallBack<String> baseCallBack) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(context.getResources().getString(R.string.app_name));
        builder.setMessage(message);
        builder.setCancelable(false);
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                baseCallBack.onCallBack("");
            }
        });
        builder.setNegativeButton("Cancel", null);
        builder.show();
    }

    public void showWith2Action(Context context, String positiveAction, String negativeActionText, String title, String message, final BaseCallBack<String> baseCallBack) {
        try {
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setTitle(title);
            builder.setMessage(message);
            builder.setCancelable(false);
            builder.setPositiveButton(positiveAction, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    baseCallBack.onCallBack("");
                }
            });
            builder.setNegativeButton(negativeActionText, null);
            builder.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }







    public static void inviteConnectOptions(final Context context) {
        /*final CharSequence[] items = {"Email or Contacts", "Other apps",
                "Cancel"};
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Invite Options");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                switch (item) {
                    case 0:
                        BaseUtils.sendFireBaseInvite(context);
                        break;
                    case 1:
                        BaseUtils.InviteByOtherApp(context);
                        break;
                    case 2:
                        dialog.dismiss();
                        break;

                }
            }
        });
        builder.show();*/
    }


    public void logOutDialog(Context context, BaseCallBack baseCallBack) {
        DialogHelper.getInstance().showWith2Action(context, "Log out", "Cancel", "Are you sure ?", "You want to logout from Lecture Verb.", baseCallBack);
    }

    public void exitDialog(Context context, BaseCallBack baseCallBack) {
        DialogHelper.getInstance().showWith2Action(context, "Exit", "Cancel", "Are you sure ?", "You want to exit from Lecture Verb.", baseCallBack);
    }

    public void showReportDialog(final Context con) {
        final Dialog dialog = new Dialog(con);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_report_help);

        final EditText edMessage = dialog.findViewById(R.id.edMessage);
        final TextView txtvSubmit = dialog.findViewById(R.id.txtvSubmit);

        txtvSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                if (edMessage.getText().toString().isEmpty() || edMessage.getText().toString().length() < 10) {
                    BaseUtils.showToast("Please enter a detailed message.", con, true);
                } else {
                    BaseUtils.showLoading("Please wait...", con);
                    // Execute api here
                }
            }
        });

        dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                BaseUtils.hideKeyboard(con, txtvSubmit);
            }
        });


        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(dialog.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        dialog.show();
        dialog.getWindow().setAttributes(lp);
    }

    public void playAudio(Activity con, String filePath) {
        // initializing custom dialog
        final Dialog dialog = new Dialog(con);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_play_audio);
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(dialog.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        dialog.show();
        dialog.getWindow().setAttributes(lp);
        dialog.show();
        final SeekBar seekBAr = (SeekBar) dialog.findViewById(R.id.seekBAr);

        final MediaPlayer mPlayer = new MediaPlayer();
        try {
            // Playing audio using filePath
            Uri myUri1 = Uri.parse(filePath);
            mPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
            mPlayer.setDataSource(con, myUri1);
            mPlayer.prepare();
            mPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    dialog.dismiss();
                }
            });
            mPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    mPlayer.start();
                    seekBAr.setMax(mp.getDuration()/200);
                    seekBAr.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                        @Override
                        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                        }

                        @Override
                        public void onStartTrackingTouch(SeekBar seekBar) {

                        }

                        @Override
                        public void onStopTrackingTouch(SeekBar seekBar) {
                            mPlayer.seekTo(seekBar.getProgress()*200);
                        }
                    });
                }
            });

        } catch (IOException e) {
            e.printStackTrace();
        }

        // ondismiss listener is being used to stop audio
        dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                if (mPlayer != null && mPlayer.isPlaying()) {
                    mPlayer.stop();
                }
            }
        });

        // Handler is being used to show progress of audio in seekbar
        final Handler mHandler = new Handler();
        //necessary to update Seekbar on UI thread only
        con.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (mPlayer != null) {
                    int mCurrentPosition = mPlayer.getCurrentPosition() / 200;
                    seekBAr.setProgress(mCurrentPosition);
                }
                mHandler.postDelayed(this, 200);
            }
        });

        seekBAr.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (mPlayer != null && fromUser) {
                    mPlayer.seekTo(progress * 1000);
                }
            }
        });

    }

    public interface CallBackSingleInterface {
        public void onCompletion();
    }
}
