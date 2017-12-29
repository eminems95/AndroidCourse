package com.anotherdeveloper.androidcourse.utilities;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.support.annotation.ColorInt;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.widget.Toast;

import com.anotherdeveloper.androidcourse.R;

/**
 * Created by Marcin on 2017-12-26.
 * :)
 */

public class ConnectivityManagement {
    private Context context;
    private boolean isConnected=false;
    public ConnectivityManagement(Context context) {
        this.context = context;
    }

    public boolean getConnectionStatus(Toolbar toolbar) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        TypedValue typedValue = new TypedValue();
        Resources.Theme theme = context.getTheme();
        @ColorInt int color;
        if (cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isAvailable() && cm.getActiveNetworkInfo().isConnected()) {
            isConnected=true;
            theme.resolveAttribute(R.attr.colorPrimary, typedValue, true);
            color = typedValue.data;
            toolbar.setBackgroundColor(color);
            Toast.makeText(context, "Online mode", Toast.LENGTH_SHORT).show();
        } else {
            toolbar.setBackgroundColor(Color.GRAY);
            Toast.makeText(context, "Offline mode", Toast.LENGTH_SHORT).show();
            isConnected = false;
        }
        return isConnected;
    }

    public void checkConnection(IConnectionCheck iConnectionCheck){

    }

    public interface IConnectionCheck {
        void onOnline(boolean isConnected);
        void onOffline(boolean isConnected);
    }
}
