package com.example.ddmeng.hellodialogfragment;

public interface DialogFragmentCallback {
    void showDialog(String dialogName);

    void doPositiveClick(String dialogName);

    void doNegativeClick(String dialogName);
}
