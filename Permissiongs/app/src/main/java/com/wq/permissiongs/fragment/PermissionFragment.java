package com.wq.permissiongs.fragment;

import android.Manifest;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.wq.permissiongs.R;

/**
 * fragment request permission
 */
public class PermissionFragment extends Fragment {

    private static final int key_frag_request_calendar = 0x74;


    public PermissionFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_permission, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        View btn = view.findViewById(R.id.btn_read_calendar);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                readCalendar(v);
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case key_frag_request_calendar:
                if (grantResults != null && grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    doReadCalendar();
                } else {
                    doNotReadCalendar();
                }
                break;
        }
    }


    private void shortToast(String msg){
        Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
    }


    public void readCalendar(View view){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){

            if (requestSinglePermission(Manifest.permission.READ_CALENDAR, key_frag_request_calendar)){
                doReadCalendar();
            }else {
                shortToast("requesting permission ");
            }
        }else {
            doReadCalendar();
        }
    }


    private void doReadCalendar(){
        shortToast("doReadCalendar");
    }

    private void doNotReadCalendar(){
        shortToast("doNotReadCalendar");
    }



    @RequiresApi(api = Build.VERSION_CODES.M)
    private boolean requestSinglePermission(final String permission, final int requestCode){

        if(getActivity().checkSelfPermission(permission)!= PackageManager.PERMISSION_GRANTED){
            if (shouldShowRequestPermissionRationale(permission)) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setMessage("TEST: need request Permission "+permission).
                        setOnDismissListener(new DialogInterface.OnDismissListener() {
                            @Override
                            public void onDismiss(DialogInterface dialog) {
                                requestPermissions(new String[]{permission}, requestCode);
                            }
                        }).show();

            }else {
                // need fragment.requestPersmission
                // Can only use lower 16 bits for requestCode
                requestPermissions(new String[]{permission}, requestCode);
            }
        } else {
            // permission already granted
            return true;
        }
        // permission not granted
        return false;

    }


}
