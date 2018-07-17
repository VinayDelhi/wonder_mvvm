package com.example.wonder.wonder_mvvm.base.view;

import android.app.Activity;
import android.app.Dialog;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.example.wonder.wonder_mvvm.R;
import com.example.wonder.wonder_mvvm.util.Log;


/**
 * Class that holds ready to use dialogs
 */
public class ProgressDialog {
    private static final String LOG_TAG = ProgressDialog.class.getSimpleName();
    private static Dialog progressDialog;
    private static TextView tvProgress;
    private static TextView innerProgress;

    /**
     * Shows the progress dialog
     *
     * @param activity
     * @return the {@link TextView} on which progress has to be set
     */
    public static void showOn(Activity activity) {

        showOn(activity, "Loading...");
    }

    /**
     * Method to show the progress dialog with a message
     *
     * @param activity
     * @param message
     * @return
     */
    public static void showOn(final Activity activity, final String message) {

        try {
            /* Check if the last instance is alive */
            if (progressDialog != null)
                if (progressDialog.isShowing()) {
                    tvProgress.setText(message);
                    return;
                }

            /*  Ends Here   */

            progressDialog = new Dialog(activity,
                    android.R.style.Theme_Translucent_NoTitleBar);

            progressDialog.setContentView(R.layout.dialog_progress);

            tvProgress = (TextView) progressDialog
                    .findViewById(R.id.tvProgress);
            innerProgress = (TextView) progressDialog
                    .findViewById(R.id.progress);
//            tvProgress.setTypeface(Font.getRegular(activity));
            tvProgress.setText(message);
            innerProgress.setText("");

            ((ProgressWheel) progressDialog.findViewById(R.id.progress_wheel))
                    .spin();

            Window dialogWindow = progressDialog.getWindow();
            WindowManager.LayoutParams layoutParams = dialogWindow
                    .getAttributes();
            layoutParams.dimAmount = 0.8f;
            dialogWindow.addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
            progressDialog.setCancelable(false);
            progressDialog.setCanceledOnTouchOutside(false);

            progressDialog.show();

        } catch (Exception e) {
            e.printStackTrace();
            Log.e(LOG_TAG, "EXCEPTION: " + e.getMessage());
        }
    }

    public static void showOn(final Activity activity, final String message, boolean cancellable) {

        try {
            /* Check if the last instance is alive */
            if (progressDialog != null)
                if (progressDialog.isShowing()) {
                    tvProgress.setText(message);
                    return;
                }

            /*  Ends Here   */

            progressDialog = new Dialog(activity,
                    android.R.style.Theme_Translucent_NoTitleBar);

            progressDialog.setContentView(R.layout.dialog_progress);

            tvProgress = (TextView) progressDialog
                    .findViewById(R.id.tvProgress);
            innerProgress = (TextView) progressDialog
                    .findViewById(R.id.progress);
//            tvProgress.setTypeface(Font.getRegular(activity));
            tvProgress.setText(message);
            innerProgress.setText("");

            ((ProgressWheel) progressDialog.findViewById(R.id.progress_wheel))
                    .spin();

            Window dialogWindow = progressDialog.getWindow();
            WindowManager.LayoutParams layoutParams = dialogWindow
                    .getAttributes();
            layoutParams.dimAmount = 0.8f;
            dialogWindow.addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
            if (cancellable) {
                progressDialog.setCancelable(true);
                progressDialog.setCanceledOnTouchOutside(true);
            }

            progressDialog.show();

        } catch (Exception e) {
            e.printStackTrace();
            Log.e(LOG_TAG, "EXCEPTION: " + e.getMessage());
        }
    }

    public static void updateProgress(int percentage) {
        innerProgress.setText(Integer.toString(percentage) + "%");
    }

    /**
     * Dismisses the Progress Dialog
     */
    public static boolean dismissProgressDialog() {

        Log.e(LOG_TAG, "DISMISSED");

        if (progressDialog != null)
            if (progressDialog.isShowing()) {

                try {
                    progressDialog.dismiss();
                } catch (Exception ex) {
                    Log.e(LOG_TAG, "Dismiss Loading Dialog " + ex.toString());
                }
                progressDialog = null;
                tvProgress = null;
                innerProgress = null;
                return true;
            }

        return false;
    }

    public static boolean isProgressShowing() {
        if(progressDialog != null) {
            return progressDialog.isShowing();
        } else return false;
    }
}
