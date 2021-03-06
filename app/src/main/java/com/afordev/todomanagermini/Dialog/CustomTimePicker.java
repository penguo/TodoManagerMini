package com.afordev.todomanagermini.Dialog;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Build;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.afordev.todomanagermini.MainActivity;
import com.afordev.todomanagermini.R;
import com.afordev.todomanagermini.SubItem.DataTodo;
import com.afordev.todomanagermini.SubItem.DateForm;

import java.util.Calendar;

/**
 * Created by penguo on 2018-03-07.
 */

public class CustomTimePicker {

    private Context mContext;

    private String dialogTitle, neutralTitle;
    private DialogInterface.OnClickListener positiveListener, negativeListener, neutralListener;
    private TimePicker timePicker;

    public CustomTimePicker(Context mContext) {
        this.mContext = mContext;
        initSet();
    }

    private void initSet() {
        positiveListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        };
        negativeListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        };
    }

    public void setTitle(String dialogTitle) {
        this.dialogTitle = dialogTitle;
    }

    public void setPositiveListener(DialogInterface.OnClickListener positiveListener) {
        this.positiveListener = positiveListener;
    }

    public void setNegativeListener(DialogInterface.OnClickListener negativeListener) {
        this.negativeListener = negativeListener;
    }

    public void setNeutralListener(String st, DialogInterface.OnClickListener neutralListener) {
        neutralTitle = st;
        this.neutralListener = neutralListener;
    }

    public TimePicker getTimePicker() {
        return timePicker;
    }

    public void show(DateForm date) {
        LayoutInflater li = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        LinearLayout layout = (LinearLayout) li.inflate(R.layout.dialog_timepicker, null);
        timePicker = layout.findViewById(R.id.dialog_timepicker);
        TextView tvTitle = layout.findViewById(R.id.dialog_tp_tv_title);
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        final AlertDialog dialog;

        int hour, minute;
        hour = date.getHour();
        minute = date.getMinute();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            timePicker.setHour(hour);
            timePicker.setMinute(minute);
        } else {
            timePicker.setCurrentHour(hour);
            timePicker.setCurrentMinute(minute);
        }
        if(!dialogTitle.equals("")){
            tvTitle.setText(dialogTitle);
        }else{
            tvTitle.setVisibility(View.GONE);
        }

        builder.setView(layout);

        builder.setPositiveButton("저장", positiveListener);
        builder.setNegativeButton("취소", negativeListener);
        if (neutralListener != null) {
            builder.setNeutralButton(neutralTitle, neutralListener);
        }

        dialog = builder.create(); //builder.show()를 create하여 dialog에 저장하는 방식.
        dialog.show();
    }
}
