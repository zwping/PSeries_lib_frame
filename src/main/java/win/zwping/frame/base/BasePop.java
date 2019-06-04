package win.zwping.frame.base;

import android.content.Context;
import razerdp.basepopup.BasePopupWindow;
import win.zwping.code.utils.ConversionUtil;
import win.zwping.code.utils.ScreenUtil;

public abstract class BasePop extends BasePopupWindow {

    /////////////////////////////////////////

    public int getScreentHeight(Float scale) {
        return Math.round(ScreenUtil.getScreenHeight() * scale);
    }

    public int getScreentWidth(Float scale) {
        return Math.round(ScreenUtil.getScreenWidth() * scale);
    }

    /////////////////////////////////////////

    public BasePop(Context context) {
        super(context);
    }

    public BasePop(Context context, boolean delayInit) {
        super(context, delayInit);
    }

    public BasePop(Context context, int width, int height) {
        super(context, width, height);
    }

    public BasePop(Context context, int width, int height, boolean delayInit) {
        super(context, width, height, delayInit);
    }

}