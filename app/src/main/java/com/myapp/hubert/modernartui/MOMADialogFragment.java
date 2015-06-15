package com.myapp.hubert.modernartui;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.TextView;

/**
 * Created by Hubert on 02/06/2015.
 */
public class MOMADialogFragment extends DialogFragment {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        TextView tmp = new TextView(getActivity());
        tmp.setText(R.string.dialog_message);
        tmp.setGravity(Gravity.CENTER);
        tmp.setPadding(16, 16, 16, 16);
        tmp.setTextSize(16);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(tmp)
               .setPositiveButton(R.string.dialog_yes, new DialogInterface.OnClickListener() {
                   @Override
                   public void onClick(DialogInterface dialog, int which) {
                       Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.moma.org"));
                       startActivity(intent);
                   }
               })
               .setNegativeButton(R.string.dialog_no, new DialogInterface.OnClickListener() {
                   @Override
                   public void onClick(DialogInterface dialog, int which) { }
               });
        return builder.create();
    }
}
