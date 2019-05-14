package win.zwping.frame.comm;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import razerdp.basepopup.BasePopupWindow;
import win.zwping.frame.R;

/**
 * <p>describe：
 * <p>    note：
 * <p> @author：zwp on 2019-05-14 16:27:44 mail：1101558280@qq.com web: http://www.zwping.win </p>
 */
public class LoadingPop extends BasePopupWindow {

    public LoadingPop(Context context) {
        super(context);
        setBackgroundColor(Color.TRANSPARENT);
        setPopupGravity(Gravity.CENTER);
        setAllowDismissWhenTouchOutside(false);
    }

    @Override
    public View onCreateContentView() {
        return View.inflate(getContext(), R.layout.comm_pop_loading, null);
    }
}
