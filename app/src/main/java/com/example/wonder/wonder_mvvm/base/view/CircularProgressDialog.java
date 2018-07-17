package com.example.wonder.wonder_mvvm.base.view;

import android.app.Dialog;
import android.content.Context;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.example.wonder.wonder_mvvm.R;

/**
 * Class that holds ready to use dialogs
 */
public class CircularProgressDialog extends Dialog {
    private TextView tvProgress;
    private TextView innerProgress;
    private ProgressWheel progressWheel;

    public CircularProgressDialog(Context context) {
        super(context, android.R.style.Theme_Translucent_NoTitleBar);
        init();
    }

    public CircularProgressDialog(Context context, int themeResId) {
        super(context, themeResId);
        init();
    }

    protected CircularProgressDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
        init();
    }

    public void updateProgress(int percentage) {
        innerProgress.setText(Integer.toString(percentage) + "%");
    }

    public void init() {
        setContentView(R.layout.dialog_progress);
        tvProgress = (TextView) findViewById(R.id.tvProgress);
        innerProgress = (TextView) findViewById(R.id.progress);
        progressWheel = (ProgressWheel) findViewById(R.id.progress_wheel);

        Window dialogWindow = getWindow();
        WindowManager.LayoutParams layoutParams = dialogWindow.getAttributes();
        layoutParams.dimAmount = 0.8f;
        dialogWindow.addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
    }

    public void display() {
        display("Loading");
    }

    public void display(final String message) {
        display(message, false);
    }

    public void display(final String message, boolean cancellable) {
        try {
            if (isShowing()) {
                tvProgress.setText(message);
                return;
            }
            tvProgress.setText(message);
            innerProgress.setText("");
            progressWheel.spin();
            setCancelable(cancellable);
            setCanceledOnTouchOutside(cancellable);

            show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
