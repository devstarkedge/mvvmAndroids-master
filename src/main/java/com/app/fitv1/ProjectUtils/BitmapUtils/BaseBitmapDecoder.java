//==============================================================================================================================
package com.app.fitv1.ProjectUtils.BitmapUtils;


//==============================================================================================================================

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;

import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;

import com.app.fitv1.BuildConfig;
import com.app.fitv1.ProjectUtils.Constants;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;


//------------------------------------------------------------------------------------------------------------------------------

//==============================================================================================================================
public class BaseBitmapDecoder
{
    //--------------------------------------------------------------------------------------------------------------------------

    /**
     * generate an temp file
     *
     * @return Uri of the temp file.
     */
    public static Uri getTemporaryUri(Context con) {
        return getUriFromFile(con,createTemporaryFile());
    }

    //--------------------------------------------------------------------------------------------------------------------------

    /**
     * Handle on activity result,after image selection from galley/camera.
     *
     * @param context
     * @param requestCode
     * @param resultCode
     * @param data
     * @return Uri of the selected image.
     */
    public static Uri onActivityResult(Context context, int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case Constants.RequestCode.CAMERA:
                if (resultCode == Activity.RESULT_OK) {
                    try {
                        return getUriFromFile(context,generatedname);
//                        if (tempFile.exists())
//                            tempFile.delete();
                    }
                    catch (Exception error) {
                        error.printStackTrace();
                    }
                }

                break;

            case Constants.RequestCode.GALLERY:
                try {
                    try {
                        String filePathG = Environment.getExternalStorageDirectory() + "/LV_IMAGE_" + System.currentTimeMillis() + ".png";
                        InputStream inputStream;
                        inputStream = context.getContentResolver().openInputStream(data.getData());
                        FileOutputStream fileOutputStream = new FileOutputStream(filePathG);
                        copyStream(inputStream, fileOutputStream);
                        fileOutputStream.close();
                        inputStream.close();

                        return getUriFromFile(context,new File(filePathG));
                    }
                    catch (Exception ex) {
                        Log.e("Exception is", ex.toString());
                    }

                    return data.getData();
                }
                catch (Exception error) {
                    error.printStackTrace();
                }

                break;
        }

        return null;
    }

    private static void copyStream(InputStream input, OutputStream output) throws IOException {
        byte[] buffer = new byte[1024];
        int bytesRead;
        while ((bytesRead = input.read(buffer)) != -1) {
            output.write(buffer, 0, bytesRead);
        }
    }

    /**
     * generate an random name for the temp file.
     *
     * @return
     */
    public static String randomName() {
        return System.currentTimeMillis() + "";
    }

    //--------------------------------------------------------------------------------------------------------------------------

    /**
     * Create an temp file using an random name.
     *
     * @return
     */
    public static File createTemporaryFile() {
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            try {
                File file = new File(Environment.getExternalStorageDirectory(), "LV_IMAGE_" + randomName());

                file.createNewFile();

                generatedname = file;

                return file;
            }
            catch (IOException error) {
            }
        }

        return null;
    }

    /**
     * intent to open device camera.
     *
     * @param context
     * @param frag
     */
    public static void openCamera(final Context context, final Fragment frag) {
        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, getTemporaryUri(context));
        if (frag != null) {
            frag.startActivityForResult(cameraIntent, Constants.RequestCode.CAMERA);
        }
        else {
            ((Activity) context).startActivityForResult(cameraIntent, Constants.RequestCode.CAMERA);
        }
    }


    public static void selectImage(final Context context, final Fragment frag) {
        generatedname = null;
        final CharSequence[] items = {"Take Photo", "Choose from Library",
                "Cancel"};
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Add Photo!");
        builder.setItems(items, new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                if (items[item].equals("Take Photo")) {
                    openCamera(context, frag);
                }
                else if (items[item].equals("Choose from Library")) {
                    openGallery(context, frag, "image/*");
                }
                else if (items[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }



    /**
     * intent to open gallery for image selection.
     *
     * @param context
     * @param frag
     */
    public static void openGallery(final Context context, final Fragment frag, String fileType) {
        Intent pickPhoto = new Intent(Intent.ACTION_PICK);

        pickPhoto.setType(fileType);


        if (frag != null) {
            frag.startActivityForResult(pickPhoto, Constants.RequestCode.GALLERY);
        }
        else {
            ((Activity) context).startActivityForResult(pickPhoto, Constants.RequestCode.GALLERY);
        }

    }


    private static File generatedname;


    public static Uri getUriFromFile(Context context, File filePath)
    {
        Uri uri;
        if(Build.VERSION.SDK_INT > 24) {
            uri = FileProvider.getUriForFile(context, BuildConfig.APPLICATION_ID + ".provider",filePath);
        }else{
            uri = Uri.fromFile(filePath);
        }
        return uri;
    }

}
