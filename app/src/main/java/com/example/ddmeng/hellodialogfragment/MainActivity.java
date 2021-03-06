package com.example.ddmeng.hellodialogfragment;

import android.app.DialogFragment;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity implements DialogFragmentCallback {

    private int mStackLevel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button1 = (Button) findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Create the fragment and show it as a dialog.
                DialogFragment newFragment = MyDialogFragment.newInstance();
                newFragment.show(getFragmentManager(), "dialog");
            }
        });

        Button button2 = (Button) findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog(MyBasicDialogFragment.class.getName());
            }
        });


        Button button3 = (Button) findViewById(R.id.button3);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog(MyAlertDialogFragment.class.getName());
            }
        });

        Button button4 = (Button) findViewById(R.id.button4);
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog(MyCustomDialogFragment.class.getName());
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void showDialog(String name) {
        if (MyAlertDialogFragment.class.getName().equals(name)) {
            showMyAlertDialog();
        } else if (MyBasicDialogFragment.class.getName().equals(name)) {
            showMyBasicDialog();
        } else if (MyCustomDialogFragment.class.getName().equals(name)) {
            showMyCustomDialogFragment();
        }
    }

    private void showMyAlertDialog() {
        DialogFragment newFragment = MyAlertDialogFragment.newInstance(
                R.string.alert_dialog_two_buttons_title);
        newFragment.show(getFragmentManager(), "alert dialog");
    }

    private void showMyBasicDialog() {
        mStackLevel++;

        // DialogFragment.show() will take care of adding the fragment
        // in a transaction.  We also want to remove any currently showing
        // dialog, so make our own transaction and take care of that here.
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        Fragment prev = getFragmentManager().findFragmentByTag("basic dialog");
        if (prev != null) {
            ft.remove(prev);
        }
        ft.addToBackStack(null);

        // Create and show the dialog.
        DialogFragment newFragment = MyBasicDialogFragment.newInstance(mStackLevel);
        newFragment.show(ft, "basic dialog");
    }

    private void showMyCustomDialogFragment() {
        new MyCustomDialogFragment.Builder(MainActivity.this)
                .setMessage(R.string.hello_world)
                .setPositiveButton(R.string.alert_dialog_ok)
                .setNegativeButton(R.string.alert_dialog_cancel)
                .show(getFragmentManager());
    }

    @Override
    public void doPositiveClick(String name) {

        if (MyAlertDialogFragment.class.getName().equals(name)) {
            Log.i("MyAlert", "positive");
        } else if (MyCustomDialogFragment.class.getName().equals(name)) {
            Log.i("MyCustom", "positive");
        }
    }

    @Override
    public void doNegativeClick(String name) {
        if (MyAlertDialogFragment.class.getName().equals(name)) {
            Log.i("MyAlert", "negative");
        } else if (MyCustomDialogFragment.class.getName().equals(name)) {
            Log.i("MyCustom", "negative");
        }
    }

    /**
     * This MyDialogFragment is from "Selecting Between Dialog or Embedding"
     * of the Android Reference: http://developer.android.com/reference/android/app/DialogFragment.html
     */
    public static class MyDialogFragment extends DialogFragment {
        static MyDialogFragment newInstance() {
            return new MyDialogFragment();
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View v = inflater.inflate(R.layout.hello_world, container, false);
            View tv = v.findViewById(R.id.text);
            ((TextView) tv).setText("This is an instance of MyDialogFragment");
            return v;
        }
    }
}
