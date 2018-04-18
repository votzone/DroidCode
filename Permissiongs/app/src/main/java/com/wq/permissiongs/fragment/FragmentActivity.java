package com.wq.permissiongs.fragment;


import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.wq.permissiongs.R;

public class FragmentActivity extends AppCompatActivity {


    PermissionFragment permissionFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);
        permissionFragment = new PermissionFragment();

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.v_container, permissionFragment);
        fragmentTransaction.commit();
    }
}
