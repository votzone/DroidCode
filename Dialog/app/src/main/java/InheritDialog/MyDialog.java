package InheritDialog;

import android.app.Dialog;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wq.dialog.R;

import java.io.IOException;
import java.io.InputStream;

import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;
import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;

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
//        setContentView(R.layout.inherit_dialog_layout);
//
//        btn1 = findViewById(R.id.ll_btn_1);
//        btn2 = findViewById(R.id.ll_btn_2);
//        btn3 = findViewById(R.id.ll_btn_3);

//        LayoutInflater layoutInflater = LayoutInflater.from(getContext());
//        View v = layoutInflater.inflate(R.layout.inherit_dialog_layout,null, false);
//        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(MATCH_PARENT, MATCH_PARENT);
//        addContentView(v, layoutParams);
//        setContentView(v);

        setHandWriteContentView();
    }

    private void setHandWriteContentView(){
        // view root
        LinearLayout llroot = new LinearLayout(getContext());
        llroot.setBackgroundColor(Color.parseColor("#66000000"));
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT);
        llroot.setLayoutParams(layoutParams);

        // view gift1
        LinearLayout llGift = getLinearLayout();
        btn1 = llGift;

        // view gift1 -> img
        ImageView imageView = getImageView("ic_launcher_round.png");
        llGift.addView(imageView);

        // view gift1 -> text
        TextView textView = getTextView("选项1");
        llGift.addView(textView);


        // view gift2
        llGift =  getLinearLayout();
        btn2 = llGift;

        // view gift2 -> img
        imageView = getImageView("ic_launcher_round.png");
        llGift.addView(imageView);

        // view gift2 -> text
        textView = getTextView("选项2");
        llGift.addView(textView);

        // view gift3
        llGift =  getLinearLayout();
        btn3 = llGift;

        // view gift3 -> img
        imageView = getImageView("ic_launcher_round.png");
        llGift.addView(imageView);

        // view gift3 -> text
        textView = getTextView("选项3");
        llGift.addView(textView);

        // add to root
        llroot.addView(btn1);
        llroot.addView(btn2);
        llroot.addView(btn3);
        setContentView(llroot);
    }

    public LinearLayout getLinearLayout(){
        LinearLayout llGift = new LinearLayout(getContext());
        // android: gravity属性
        llGift.setGravity(Gravity.CENTER);
        LinearLayout.LayoutParams lpg = new LinearLayout.LayoutParams(convertDip2px(150), convertDip2px(280), 1);
        llGift.setLayoutParams(lpg);
        llGift.setOrientation(LinearLayout.VERTICAL);
        return llGift;
    }

    public ImageView getImageView(String drawableFileName){
        ImageView imageView = new ImageView(getContext());
        imageView.setImageDrawable(loadImageFromAsserts(getContext(), drawableFileName));
        LinearLayout.LayoutParams lpgImg = new LinearLayout.LayoutParams(convertDip2px(80), convertDip2px(80));
        // android: layout_gravity属性
//        lpgImg.gravity = Gravity.CENTER;
        imageView.setLayoutParams(lpgImg);
        return imageView;
    }

    public TextView getTextView(String text){
        TextView textView = new TextView(getContext());
        textView.setText(text);
        textView.setPadding(convertDip2px(3),convertDip2px(3),convertDip2px(3),convertDip2px(3));
        textView.setTextSize(18);
        LinearLayout.LayoutParams lpgText = new LinearLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT);
        lpgText.topMargin = convertDip2px(5);
//        lpgText.gravity = Gravity.CENTER;
        textView.setLayoutParams(lpgText);
        textView.setBackgroundColor(Color.parseColor("#dcb240"));
        return textView;
    }

    public static Drawable loadImageFromAsserts(final Context ctx, String fileName) {
        try {
            InputStream is = ctx.getResources().getAssets().open(fileName);
            return Drawable.createFromStream(is, null);
        } catch (IOException e) {
            if (e != null) {
                e.printStackTrace();
            }
        } catch (OutOfMemoryError e) {
            if (e != null) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            if (e != null) {
                e.printStackTrace();
            }
        }
        return null;
    }

    private int convertDip2px(float dip){
        float scale = Resources.getSystem().getDisplayMetrics().density;
        return (int)(dip * scale + 0.5f);
    }

}
