package words.com.flower.utils;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.graphics.Point;
import android.view.WindowManager;

/**
 * Created by utkan on 13/08/15.
 */
public class DeviceUtils {

    private static int ACTION_HEIGHT;
    private final Context context;

    public DeviceUtils(Context context) {
        this.context = context;
    }

    public int getDeviceWidth() {

        Point size = new Point();
        (((WindowManager) context.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay())
                .getSize(size);
        return size.x;
    }

    public int getDeviceHeight() {

        Point size = new Point();
        (((WindowManager) context.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay())
                .getSize(size);
        return size.y;
    }

    public int getOrientation() {
        if (context.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            return Configuration.ORIENTATION_PORTRAIT;
        } else {
            return Configuration.ORIENTATION_LANDSCAPE;
        }
    }

    public int getStatusBarHeight() {
        int result = 0;
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = context.getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

    public int getActionBarSize() {

        if (ACTION_HEIGHT == 0) {

            final TypedArray styledAttributes = context.getTheme().obtainStyledAttributes(
                    new int[]{android.R.attr.actionBarSize});
            ACTION_HEIGHT = (int) styledAttributes.getDimension(0, 0);
            styledAttributes.recycle();

        }
        return ACTION_HEIGHT;
    }
}
