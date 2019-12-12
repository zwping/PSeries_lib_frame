package win.zwping.frame.http;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.lzy.okgo.model.Response;

/**
 * <p>describe：
 * <p>    note：
 * <p> @author：zwp on 2019-05-14 12:53:08 mail：1101558280@qq.com web: http://www.zwping.win </p>
 */
public interface HttpConfig<B extends HttpBean> {

    void showProgress(Object tag,@Nullable CharSequence loadingTxt);

    void hideProgress(Object tag);

    /**
     *
     * @param build
     * @param rawResponse
     */
    void onSuccess(@NonNull Build<B> build,@Nullable Response<String> rawResponse);

    void onCacheSuccess(@NonNull Build<B> build);

    /**
     *
     * @param build
     * @param rawResponse
     */
    void onError(@NonNull Build<B> build,@Nullable Response<String> rawResponse);


}
