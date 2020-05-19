package win.zwping.frame.http.lis;

import androidx.annotation.Nullable;
import win.zwping.frame.http.HttpBean;

/**
 * <p>describe：
 * <p>    note：
 * <p> @author：zwp on 2019-05-14 10:41:10 mail：1101558280@qq.com web: https://www.zwping.com </p>
 */
public interface OnSuccessListener<B extends HttpBean> {
    void onSuccess(@Nullable B it);
}
