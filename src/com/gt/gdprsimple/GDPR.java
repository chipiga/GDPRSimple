package com.gt.gdprsimple;

import com.gt.gdprsimple.R;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.app.AlertDialog;
import android.text.Html;
import android.widget.TextView;
import android.text.util.Linkify;
import android.text.SpannableString;
import android.text.method.LinkMovementMethod;

public class GDPR {
    private static final String GDPR_RESULT = "gdpr_result";
    public static SharedPreferences sharedPreferences = null;

    public static void showDialog(Context mContext) {
        sharedPreferences = mContext.getSharedPreferences("appodeal", Context.MODE_PRIVATE);
        if (!sharedPreferences.contains(GDPR_RESULT)) {
            final TextView message = new TextView(mContext);
            final SpannableString s = new SpannableString(mContext.getText(R.string.gdpr_window_text));
            // Linkify.addLinks(s, Linkify.WEB_URLS);
            message.setPadding(14, 2, 10, 12);
            message.setText(s);
            message.setMovementMethod(LinkMovementMethod.getInstance());
            AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
            builder.setTitle(R.string.gdpr_window_title)
                    .setView(message)
                    // .setMessage(s)
                    // .setMessage(R.string.gdpr_window_text)
                    .setCancelable(false)
                    .setPositiveButton(R.string.gdpr_window_yes,
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    sharedPreferences.edit().putBoolean(GDPR_RESULT, true).apply();
                                }
                            })
                    .setNegativeButton(R.string.gdpr_window_no,
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    sharedPreferences.edit().putBoolean(GDPR_RESULT, false).apply();
                                }
                            });
            builder.create().show();
        }
    }

    public static Boolean getResult() {
        return sharedPreferences.getBoolean(GDPR_RESULT, true);
    }
}