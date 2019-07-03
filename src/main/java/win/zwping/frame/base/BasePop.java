package win.zwping.frame.base;

import android.content.Context;
import android.view.View;
import androidx.annotation.FloatRange;
import androidx.annotation.IntDef;
import androidx.annotation.IntRange;
import com.chad.library.adapter.base.BaseViewHolder;
import razerdp.basepopup.BasePopupWindow;
import win.zwping.code.utils.ScreenUtil;
import win.zwping.code.utils.ToastUtil;

public abstract class BasePop extends BasePopupWindow {

    //////////////////////////////////////////////

    /*** 很重要的一个方法 ***/
    public BaseViewHolder getViewHolder() {
        return new BaseViewHolder(getContentView());
    }

    //////////////////////////////////////////////

    public void setOnClickLis(int id, final View.OnClickListener lis) {
        findViewById(id).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lis.onClick(v);
            }
        });
    }

    public void setOnDismiss(int... ids) {
        for (int id : ids) {
            findViewById(id).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dismiss();
                }
            });
        }
    }

    public void setGones(int... ids) {
        for (int id : ids) {
            findViewById(id).setVisibility(View.GONE);
        }
    }

    public void setVisibles(int... ids) {
        for (int id : ids) {
            findViewById(id).setVisibility(View.VISIBLE);
        }
    }

    public static int getScreenWidth(float percentage) {
        return Math.round(ScreenUtil.getScreenWidth() * percentage);
    }

    public static int getScreenHeight(float percentage) {
        return Math.round(ScreenUtil.getScreenHeight() * percentage);
    }

    //////////////////////////////////////////////

    public void showToast(Object o) {
        ToastUtil.showShort(o + "");
    }

    //////////////////////////////////////////////

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
