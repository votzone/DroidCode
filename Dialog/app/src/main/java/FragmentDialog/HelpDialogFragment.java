package FragmentDialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.wq.dialog.R;

public class HelpDialogFragment 
extends DialogFragment 
implements View.OnClickListener
{
	public static HelpDialogFragment
	newInstance(int helpResId)
	{
		HelpDialogFragment hdf = new HelpDialogFragment();
		Bundle bundle = new Bundle();
		bundle.putInt("help_resource", helpResId);
		hdf.setArguments(bundle);

		return hdf;
	}

    @Override    
    public void onCreate(Bundle icicle)
    {
    	super.onCreate(icicle);
    	this.setCancelable(true);
        int style = DialogFragment.STYLE_NORMAL, theme = 0;
        setStyle(style,theme);
    }

    public View onCreateView(LayoutInflater inflater,            
    		ViewGroup container, 
    		Bundle icicle)
    {
        return super.onCreateView(inflater, container, icicle);
    }

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		return builder.create();
//		return super.onCreateDialog(savedInstanceState);
	}

	public void onClick(View v)
	{
		dismiss();
	}
}
