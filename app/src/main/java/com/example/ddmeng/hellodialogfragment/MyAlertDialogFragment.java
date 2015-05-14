package com.example.ddmeng.hellodialogfragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;

/**
 * This MyAlertDialogFragment is from "Alert Dialog" part of http://developer.android.com/reference/android/app/DialogFragment.html
 */
public class MyAlertDialogFragment extends DialogFragment {
    public static MyAlertDialogFragment newInstance(int title) {
        MyAlertDialogFragment frag = new MyAlertDialogFragment();
        Bundle args = new Bundle();
        args.putInt("title", title);
        frag.setArguments(args);
        return frag;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        int title = getArguments().getInt("title");

        return new AlertDialog.Builder(getActivity())
                .setTitle(title)
                .setPositiveButton(R.string.alert_dialog_ok,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                ((DialogFragmentCallback) getActivity()).doPositiveClick(MyAlertDialogFragment.class.getName());
                            }
                        }
                )
                .setNegativeButton(R.string.alert_dialog_cancel,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                ((DialogFragmentCallback) getActivity()).doNegativeClick(MyAlertDialogFragment.class.getName());
                            }
                        }
                )
                .create();
    }
}
