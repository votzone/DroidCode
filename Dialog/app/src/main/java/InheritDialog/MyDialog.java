package InheritDialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.Window;

import com.wq.dialog.R;

/**
 * Created by Administrator on 2018/4/16 0016.
 *
 * https://developer.android.google.cn/guide/topics/ui/dialogs.html
 */

public class MyDialog extends Dialog {

    View btn1,btn2, btn3;

    public MyDialog(@NonNull Context context) {
        // 直接使用android.R 下的style
//        super(context, android.R.style.Theme_Holo_Light_Dialog_NoActionBar);

        //使用support v7 资源包中style
//        super(context, R.style.Theme_AppCompat_Light_Dialog);

        // 自定义style
//        super(context, R.style.MyDialog);

        //默认 style
        super(context);


        requestWindowFeature(Window.FEATURE_NO_TITLE);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inherit_dialog_layout);

        btn1 = findViewById(R.id.ll_btn_1);
        btn2 = findViewById(R.id.ll_btn_2);
        btn3 = findViewById(R.id.ll_btn_3);
    }
}
