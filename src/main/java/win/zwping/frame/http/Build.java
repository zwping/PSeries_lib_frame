package win.zwping.frame.http;

import androidx.annotation.Nullable;

import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.callback.Callback;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.exception.StorageException;
import com.lzy.okgo.model.Progress;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.PostRequest;
import com.lzy.okgo.request.base.Request;

import java.io.File;
import java.lang.reflect.Type;
import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import okhttp3.ResponseBody;
import win.zwping.code.utils.LogUtil;
import win.zwping.code.utils.ToastUtil;
import win.zwping.frame.http.lis.OnErrorListener;
import win.zwping.frame.http.lis.OnStdErrorListener;
import win.zwping.frame.http.lis.OnStdSuccessListener;
import win.zwping.frame.http.lis.OnSuccessListener;

import static win.zwping.code.utils.EmptyUtil.isNotEmpty;


/**
 * <p>describe：
 * <p>    note：
 * <p> @author：zwp on 2019-05-14 10:41:55 mail：1101558280@qq.com web: http://www.zwping.win </p>
 */
public class Build<B extends HttpBean> {

    private static HttpConfig httpConfig;

    static void setHttpConfig(HttpConfig config) {
        httpConfig = config;
    }

    private Request request;
    @Nullable
    public B bean;
    private int requestNum = 0; // 同个界面多个请求的序列
    private Boolean hasRefresh = false; // 刷新解耦
    private Boolean autoShowLoading = false;
    private CharSequence autoShowLoadingTxt = null;
    private Boolean autoShowErrorMsg = true;

    private Boolean cacheSucDataToSuc = false; // 将读取缓存成功的数据通过OnSuccessListener输出

    private OnStdSuccessListener<B> onStdSuccessListener;
    private OnSuccessListener<B> onSuccessListener;
    private OnStdSuccessListener<B> onStdCacheSuccessListener;
    private OnSuccessListener<B> onCacheSuccessListener;
    private OnStdErrorListener<B> onStdErrorListener;
    private OnErrorListener<B> onErrorListener;

    private Boolean executeFilter = true; // 是否执行

    Build(Request request, @Nullable B bean) {
        this.request = request;
        this.bean = bean;
    }

    /////////////////////////////////////////
    public Build<B> setAutoLoading() {
        this.autoShowLoading = true;
        return this;
    }

    public Build<B> setAutoLoading(Boolean autoShowLoading) {
        this.autoShowLoading = autoShowLoading;
        return this;
    }

    public Build<B> setAutoLoading(CharSequence loadingTxt) {
        this.autoShowLoading = true;
        this.autoShowLoadingTxt = loadingTxt;
        return this;
    }

    public Build<B> setAutoLoading(Boolean autoShowLoading, CharSequence loadingTxt) {
        this.autoShowLoading = autoShowLoading;
        this.autoShowLoadingTxt = loadingTxt;
        return this;
    }

    public Build<B> setRefresh() {
        this.hasRefresh = true;
        return this;
    }

    public Build<B> setRefresh(Boolean hasRefresh) {
        this.hasRefresh = hasRefresh;
        return this;
    }

    public Build<B> setRequestNum(int requestNum) {
        this.requestNum = requestNum;
        return this;
    }

    public Build<B> setAutoShowErrorMsg(Boolean autoShow) {
        this.autoShowErrorMsg = autoShow;
        return this;
    }

    public Build<B> cacheMode() {
        cacheMode(CacheMode.FIRST_CACHE_THEN_REQUEST);
        return this;
    }

    public Build<B> cacheMode(CacheMode cacheMode) {
        request.cacheMode(cacheMode);
        return this;
    }

    public Build<B> cacheSucToSuc() {
        cacheSucDataToSuc = true;
        return this;
    }

    public Build<B> executeFilter(HttpExecuteFilter filter) {
        executeFilter = filter.filter();
        return this;
    }

    public interface HttpExecuteFilter {
        boolean filter();
    }

    ////////////////////////////
    public Build<B> onStdSuccess(OnStdSuccessListener<B> listener) {
        this.onStdSuccessListener = listener;
        return this;
    }

    public Build<B> onSuccess(OnSuccessListener<B> listener) {
        this.onSuccessListener = listener;
        return this;
    }

    public Build<B> onStdCacheSuccess(OnStdSuccessListener<B> listener) {
        this.onStdCacheSuccessListener = listener;
        return this;
    }

    public Build<B> onCacheSuccess(OnSuccessListener<B> listener) {
        this.onCacheSuccessListener = listener;
        return this;
    }

    public Build<B> onStdError(OnStdErrorListener<B> listener) {
        this.onStdErrorListener = listener;
        return this;
    }

    public Build<B> onError(OnErrorListener<B> listener) {
        this.onErrorListener = listener;
        return this;
    }

    //////////////////////////////

    public Build<B> p(String k, Object v) {
        if (isNotEmpty(v))
            if (v instanceof Integer) {
                request.params(k, (int) v);
            } else if (v instanceof Float) {
                request.params(k, (Float) v);
            } else if (v instanceof Double) {
                request.params(k, (Double) v);
            } else if (v instanceof Long) {
                request.params(k, (Long) v);
            } else if (v instanceof Boolean) {
                request.params(k, (Boolean) v);
            } else if (v instanceof File && request instanceof PostRequest) {
                ((PostRequest) request).params(k, (File) v);
            } else {
                request.params(k, v.toString());
            }
        return this;
    }

    public Build<B> handleRequestLis(OnRequestLis lis) {
        lis.onRequest(request);
        return this;
    }

    public interface OnRequestLis {
        void onRequest(Request r);
    }

    /////////////////////////////////

//    public void init(int delayMillis) {
//        HandlerUtil.runOnUiThreadDelay(() -> {
//            execute();
//        }, delayMillis);
//    }

    /**
     * @deprecated {@link #execute()}
     */
    @Deprecated
    public void init() {
        execute();
    }

    /////////////////////////////////
    public void execute() {
        if (!executeFilter) return;
        if (null != httpConfig && autoShowLoading)
            httpConfig.showProgress(request.getTag(), autoShowLoadingTxt);
        request.execute(new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                if (null != httpConfig && autoShowLoading)
                    httpConfig.hideProgress(request.getTag());
                try {
//                    bean = new Gson().fromJson(new JsonReader(((ResponseBody) response.body()).charStream()), bean.getClass());
//                    bean = new Gson().fromJson(response.body(), (Type) bean.getClass());
                    bean = JSON.parseObject(response.body(), (Type) bean.getClass());
                    /* fastJson解析kotlinBean，其中int变量名包含_就无法解析 */
                    if (null != httpConfig) httpConfig.onSuccess(Build.this, response);
                    else setSuccessListener();
//                        // 真正需要自定义的应该是这里，每个团队对于状态码的把控的都不一样
//                        // Eg：Token过期... 可以直接在这处理逻辑
//                        if (data.getCode().equals("200")) { // 200才算成功
//                            setSuccessListener(data);
//                        } else {
//                            ToastUtil.showShort(data.getInfo());  // 其余全部Toast
//                            if (data.getCode().equals("405")) { // Token失效
//                                Quick.INSTANCE.emptyUserInfo();
//                                AcUtil.startActivity(LoginActivity.class);
//                            } else setErrorListener(data);
//                        }
                } catch (Exception e) {
                    e.printStackTrace();
                    LogUtil.i("数据解析错误：" + e.getMessage());
                    if (autoShowErrorMsg) ToastUtil.showShort("数据错误，请重试！");
                    if (null != httpConfig) httpConfig.onError(Build.this, response);
                    else setErrorListener();
                }
            }

            @Override
            public void onCacheSuccess(Response<String> response) {
                super.onCacheSuccess(response);
                if (null != httpConfig && autoShowLoading)
                    httpConfig.hideProgress(request.getTag());
                try {
                    bean = JSON.parseObject(response.body(), (Type) bean.getClass());
                    if (null != httpConfig) httpConfig.onCacheSuccess(Build.this);
                    else setCacheSuccessListener();
//                        if (data.getCode().equals("200")) { // 200 才算成功
//                            setCacheSuccessListener(data);
//                        }
                } catch (Exception e) {
                    e.printStackTrace();
                    LogUtil.i("缓存数据解析错误：" + e.getMessage());
                }
            }

            @Override
            public void onError(Response<String> response) {
                super.onError(response);
                LogUtil.i("网络请求出错：" + response.message());
                if (null != httpConfig && autoShowLoading)
                    httpConfig.hideProgress(request.getTag());

                if (autoShowErrorMsg) {
                    Throwable ex = response.getException();
                    if (ex instanceof UnknownHostException || ex instanceof ConnectException) {
                        ToastUtil.showShort("网络连接失败，请连接网络！");
                    } else if (ex instanceof SocketTimeoutException) {
                        ToastUtil.showShort("网络请求超时！");
                    } else if (ex instanceof StorageException) {
                        ToastUtil.showShort("SD卡不存在或者没有权限！");
                    } else {
                        ToastUtil.showShort("服务器错误！");
                    }
                }
                if (null != httpConfig) httpConfig.onError(Build.this, response);
                else setErrorListener();
            }
        });
    }

    /////////// 方法封装 /////////////////

    public void setSuccessListener() {
        if (null != onStdSuccessListener)
            onStdSuccessListener.onSuccess(requestNum, hasRefresh, bean);
        if (null != onSuccessListener) onSuccessListener.onSuccess(bean);
    }

    public void setCacheSuccessListener() {
        if (null != onStdCacheSuccessListener)
            onStdCacheSuccessListener.onSuccess(requestNum, hasRefresh, bean);
        if (null != onCacheSuccessListener) onCacheSuccessListener.onSuccess(bean);
        if (cacheSucDataToSuc) setSuccessListener();
    }

    public void setErrorListener() {
        if (null != onStdErrorListener) onStdErrorListener.onError(requestNum, hasRefresh, bean);
        if (null != onErrorListener) onErrorListener.onError(bean);
    }
}
