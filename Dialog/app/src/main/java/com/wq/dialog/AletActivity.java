package com.wq.dialog;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.Toast;

import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;
import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;

/**
 * https://developer.android.google.cn/reference/android/app/AlertDialog.html
 */
public class AletActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alet);
    }

    public void showMessageDialog(View view){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Title").
                setMessage("Message");

        builder.setCancelable(false);

        // 设置title 前面的icon
//        builder.setIcon(R.mipmap.ic_launcher);
//        builder.setIcon(R.mipmap.app_icon);
        builder.setIconAttribute(android.R.attr.alertDialogIcon);

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.setPositiveButton("OK", null);
        builder.setNeutralButton("Nothing", null);

        builder.show();
    }

    public void showListDialog(View view){
        String[] items = {"Red", "Green", "Blue"};
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Pick Color")
                .setItems(items, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // The 'which' argument contains the index position
                        // of the selected item
                    }
                });
        builder.show();
    }

    public void showMutiChoseDialog(View view){
        String [] items = {"Red", "Green", "Blue"};
//        String [] items = {"Red", "Green", false};
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Pick Color")
                .setMultiChoiceItems(items,null, new DialogInterface.OnMultiChoiceClickListener(){

                    @Override
                    public void onClick(DialogInterface dialog, int which, boolean isChecked) {

                    }
                });
        builder.show();
//        android.app.AlertDialog.Builder builder1 = new android.app.AlertDialog.Builder(this) ;
//        builder1.setNegativeButton()
    }

    public void showProgressDialog(View view){
        ProgressDialog mProgressDialog = new ProgressDialog(this);
//        mProgressDialog.setIconAttribute(android.R.attr.alertDialogIcon);
//        mProgressDialog.setTitle("Title");
        mProgressDialog.setMessage("Message");
        // 仅有两个 style 默认为 STYLE_SPINNER
        mProgressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
//        mProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
//        mProgressDialog.setMax(100);

//        mProgressDialog.setButton(DialogInterface.BUTTON_POSITIVE,
//                "Hide", new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog, int whichButton) {
//
//                    /* User clicked Yes so do some stuff */
//                    }
//                });
//        mProgressDialog.setButton(DialogInterface.BUTTON_NEGATIVE,
//                "Cancel", new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog, int whichButton) {
//
//                    /* User clicked No so do some stuff */
//                    }
//                });

        mProgressDialog.show();
    }

    public void showRadioChoseDialog(View view){
        String [] items = {"Red", "Green", "Blue"};
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Pick Color")
                .setSingleChoiceItems(items,-1, new DialogInterface.OnClickListener(){

                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
        builder.show();
    }

    public void setAdapterDialog(View view){
        // 创建数据
        final String[] items = new String[] { "北京", "上海", "广州", "深圳" };
        // 创建对话框构建器
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("地点")
                .setAdapter(
                        new ArrayAdapter<String>(AletActivity.this,
                                R.layout.item, R.id.tv_content, items),
                        new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog,
                                                int which) {
                                Toast.makeText(AletActivity.this, items[which],
                                        Toast.LENGTH_SHORT).show();
                            }
                        });
        builder.create().show();
    }

    public void setContentViewDialog(View view){
        // 创建对话框构建器
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        // 获取布局
        View view2 = View.inflate(AletActivity.this, R.layout.inherit_dialog_layout, null);
        // 创建对话框
        final AlertDialog alertDialog = builder.create();
        alertDialog.setView(view2);
        alertDialog.show();
    }

    public void setCustomView(){
//        FrameLayout fl = findViewById(android.R.id.custom);
//        fl.addView(myView, new FrameLayout.LayoutParams(MATCH_PARENT, WRAP_CONTENT));
    }





}
