package win.zwping.frame.http.lis;

import androidx.annotation.Nullable;
import win.zwping.frame.http.HttpBean;

/**
 * <p>describe：
 * <p>    note：
 * <p> @author：zwp on 2019-05-14 10:40:51 mail：1101558280@qq.com web: http://www.zwping.win </p>
 */
public interface OnStdErrorListener<B extends HttpBean> {
    void onError(int tag, Boolean refresh, @Nullable B it);
}

