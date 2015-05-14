package com.example.ddmeng.hellodialogfragment;

import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class MyBasicDialogFragment extends DialogFragment {
    int mNum;

    /**
     * Create a new instance of MyDialogFragment, providing "num"
     * as an argument.
     */
    static MyBasicDialogFragment newInstance(int num) {
        MyBasicDialogFragment f = new MyBasicDialogFragment();

        // Supply num input as an argument.
        Bundle args = new Bundle();
        args.putInt("num", num);
        f.setArguments(args);

        return f;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mNum = getArguments().getInt("num");

        // Pick a style based on the num.
        int style = DialogFragment.STYLE_NORMAL, theme = 0;
        style = getStyle(style);
        theme = getTheme(theme);
        setStyle(style, theme);
    }

    private int getStyle(int style) {
        switch ((mNum - 1) % 6) {
            case 1:
                style = DialogFragment.STYLE_NO_TITLE;
                break;
            case 2:
                style = DialogFragment.STYLE_NO_FRAME;
                break;
            case 3:
//                style = DialogFragment.STYLE_NO_INPUT;
                break;
            case 4:
                style = DialogFragment.STYLE_NORMAL;
                break;
            case 5:
                style = DialogFragment.STYLE_NORMAL;
                break;
            case 6:
                style = DialogFragment.STYLE_NO_TITLE;
                break;
            case 7:
                style = DialogFragment.STYLE_NO_FRAME;
                break;
            case 8:
                style = DialogFragment.STYLE_NORMAL;
                break;
        }
        return style;
    }

    private int getTheme(int theme) {
        switch ((mNum - 1) % 6) {
            case 4:
                theme = android.R.style.Theme_Holo;
                break;
            case 5:
                theme = android.R.style.Theme_Holo_Light_Dialog;
                break;
            case 6:
                theme = android.R.style.Theme_Holo_Light;
                break;
            case 7:
                theme = android.R.style.Theme_Holo_Light_Panel;
                break;
            case 8:
                theme = android.R.style.Theme_Holo_Light;
                break;
        }
        return theme;
    }


    public String getNameForNum(int num) {
        String name = "dialog";
        switch ((num - 1) % 6) {
            case 1:
                name += ", DialogFragment.STYLE_NO_TITLE";
                break;
            case 2:
                name += ", DialogFragment.STYLE_NO_FRAME";
                break;
            case 3:
                name += ", DialogFragment.STYLE_NO_INPUT";
                break;
            case 4:
                name += ", DialogFragment.STYLE_NORMAL";
                break;
            case 5:
                name += ", DialogFragment.STYLE_NORMAL";
                break;
            case 6:
                name += ", DialogFragment.STYLE_NO_TITLE";
                break;
            case 7:
                name += ", DialogFragment.STYLE_NO_FRAME";
                break;
            case 8:
                name += ", DialogFragment.STYLE_NORMAL";
                break;

        }
        return name;

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.basic_dialog_fragment, container, false);
        View tv = v.findViewById(R.id.text);
        ((TextView) tv).setText("Dialog #" + mNum + ": using style "
                + getNameForNum(mNum));

        // Watch for button clicks.
        Button button = (Button) v.findViewById(R.id.show);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // When button is clicked, call up to owning activity.
                ((DialogFragmentCallback) getActivity()).showDialog(MyBasicDialogFragment.class.getName());
            }
        });

        return v;
    }

}
