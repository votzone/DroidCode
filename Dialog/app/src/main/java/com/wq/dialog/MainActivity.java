package com.wq.dialog;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import InheritDialog.MyDialog;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.root).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showMydialog();
            }
        });
        showMydialog();
    }

    private void showMydialog(){
        MyDialog myDialog = new MyDialog(this);
        myDialog.show();
    }
}
