package win.zwping.frame.http.lis;

import androidx.annotation.Nullable;
import win.zwping.frame.http.HttpBean;

/**
 * <p>describe：
 * <p>    note：
 * <p> @author：zwp on 2019-05-14 10:40:42 mail：1101558280@qq.com web: http://www.zwping.win </p>
 */
public interface OnErrorListener<B extends HttpBean> {
    void onError(@Nullable B it);
}
