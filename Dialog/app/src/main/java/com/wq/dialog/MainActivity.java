package com.wq.dialog;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import InheritDialog.MyDialog;
import FragmentDialog.MyFrageDialog;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void showMydialog(View view){
        MyDialog myDialog = new MyDialog(this);
        myDialog.show();
    }

    public void showFragDialog(View view){
        MyFrageDialog mfd = new MyFrageDialog();
        mfd.show(getFragmentManager(), "myFragDialog");
    }

    public void showAlertDialog(View view) {
        Intent intent = new Intent(this, AletActivity.class);
        startActivity(intent);
    }

    public void showActivityDialog(View view)
    {
        Intent intent = new Intent(this, DialogActivity.class);
        startActivity(intent);
    }

    public void showPickers(View view){
        Intent intent = new Intent(this, PickersActivity.class);
        startActivity(intent);
    }
}
