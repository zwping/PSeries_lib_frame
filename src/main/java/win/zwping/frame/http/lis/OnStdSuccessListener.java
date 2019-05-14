package win.zwping.frame.http.lis;

import androidx.annotation.Nullable;
import win.zwping.frame.http.HttpBean;

/**
 * <p>describe：
 * <p>    note：
 * <p> @author：zwp on 2019-05-14 10:41:02 mail：1101558280@qq.com web: http://www.zwping.win </p>
 */
public interface OnStdSuccessListener<B extends HttpBean> {
    void onSuccess(int tag, Boolean refresh,@Nullable B it);
}

