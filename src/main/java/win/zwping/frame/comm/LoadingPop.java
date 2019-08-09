package win.zwping.frame.comm;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import razerdp.basepopup.BasePopupWindow;
import win.zwping.code.review.PTextView;
import win.zwping.frame.R;

import static win.zwping.code.utils.EmptyUtil.isNotEmpty;

/**
 * <p>describe：
 * <p>    note：
 * <p> @author：zwp on 2019-05-14 16:27:44 mail：1101558280@qq.com web: http://www.zwping.win </p>
 */
public class LoadingPop extends BasePopupWindow {

    private PTextView loadingPtv;

    public LoadingPop(Context context) {
        super(context);
        setBackgroundColor(Color.TRANSPARENT);
        setPopupGravity(Gravity.CENTER);
        setAllowDismissWhenTouchOutside(false);
        loadingPtv = findViewById(R.id.loading_ptv);
    }

    public LoadingPop setTxt(@Nullable CharSequence txt) {
        loadingPtv.setGone(isNotEmpty(txt));
        loadingPtv.setText(txt);
        return this;
    }

    @Override
    public View onCreateContentView() {
        return createPopupById(R.layout.comm_pop_loading);
    }
}
