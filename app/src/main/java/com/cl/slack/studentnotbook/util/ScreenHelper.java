package com.cl.slack.studentnotbook.util;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.WindowManager;

/**
 * Created by slack
 * on 17/12/23 下午1:30
 */

public class ScreenHelper {
    public static ScreenHelper helper = new ScreenHelper();
    private ScreenHelper() {
    }

    private int width;
    private int height;
    private float density;
    private final DisplayMetrics info = new DisplayMetrics();

    public void init(Context context) {
        try {
            WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
            if (wm != null) {
                wm.getDefaultDisplay().getRealMetrics(info);
                width = info.widthPixels;
                height = info.heightPixels;
                density = info.density;
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int dpToPx(float dp) {
        return (int) (dp * density+ 0.5f);
    }

}
