package win.zwping.frame.http;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * <p>describe：
 * <p>    note：
 * <p> @author：zwp on 2019-05-14 12:53:08 mail：1101558280@qq.com web: http://www.zwping.win </p>
 */
public interface HttpConfig<B extends HttpBean> {

    void showProgress(Object tag,@Nullable CharSequence loadingTxt);

    void hideProgress(Object tag);

    void onSuccess(@NonNull Build<B> build);

    void onCacheSuccess(@NonNull Build<B> build);

    void onError(@NonNull Build<B> build);


}
