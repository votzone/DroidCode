package FragmentDialog;

import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import com.wq.dialog.R;

/**
 * Created by Administrator on 2018/4/17 0017.
 */

public class MyFrageDialog extends DialogFragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.inherit_dialog_layout,container, false);
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // 不重写 oncreateview 时, 应直接返回一个dialog对象
//        return new MyDialog(getActivity());

        // 重写 oncreateview 时, super.onCreateDialog() 返回的是一个根据oncreateview得到的dialog
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        return dialog;
    }
}
