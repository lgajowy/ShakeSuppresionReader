package com.shakeSuppression.app.view.utils;

import android.content.Context;
import android.graphics.Point;
import android.view.Display;
import android.view.WindowManager;

public class PanelSize extends Point {

    public PanelSize(Context context) {
        setPanelSize(context);
    }

    private void setPanelSize(Context context) {
        Display display = ((WindowManager) context.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
        display.getSize(this);
    }
}
