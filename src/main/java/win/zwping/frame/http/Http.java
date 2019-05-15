package win.zwping.frame.http;

import android.app.Application;
import androidx.annotation.Nullable;
import com.lzy.okgo.OkGo;

/**
 * <p>describe：
 * <p>    note：
 * <p> @author：zwp on 2019-05-14 10:41:35 mail：1101558280@qq.com web: http://www.zwping.win </p>
 */
public class Http {

    /*** 基于okgo的封装 https://github.com/jeasonlzy/okhttp-OkGo/wiki/Init#%E5%85%A8%E5%B1%80%E9%85%8D%E7%BD%AE ***/
    /*** 必须在Application中初始化，也可以根据以上链接自定义 ***/
    public static OkGo init(Application app, @Nullable HttpConfig config) {
        if (null != config) Build.setHttpConfig(config);
        return OkGo.getInstance().init(app);
    }

    /**
     * @deprecated 大概用法看下方注释
     */
    @Deprecated
    public static OkGo init(Application app) {
        return init(app, null);
    }

    public static void destroy(Object tag) {
        OkGo.getInstance().cancelTag(tag);
    }

    public static <B extends HttpBean> Build<B> post(Object tag, String url, B b) {
        return new Build<B>(OkGo.<String>post(url).tag(tag), b);
    }

    public static <B extends HttpBean> Build<B> get(Object tag, String url, B b) {
        return new Build<B>(OkGo.<String>get(url).tag(tag), b);
    }


    /*
        val p = HttpParams()
        p.put("device", "android");p.put("version", getAppVersionName())
        p.put("region", Hawk.get(Config.Region, "0"))
        p.put("profession", Hawk.get(Config.Profession, "0"))
        p.put("token", HawkUtil.getString(Config.Token))
        Http.init(this, object : HttpConfig<BaseBean> {
            override fun onSuccess(build: Build<BaseBean>) {
                when (build.bean!!.code) {
                    0 -> {
                        build.setSuccessListener()
                    }
                    4001 -> {
                        ToastUtil.showShort(build.bean!!.info)
                        Quick.emptyToken()
                    }
                    else -> {
                        ToastUtil.showShort(build.bean!!.info)
                        build.setErrorListener()
                    }
                }
            }

            override fun onCacheSuccess(build: Build<BaseBean>) {
                when (build.bean!!.code) {
                    0 -> {
                        build.setCacheSuccessListener()
                    }
                }

            }

            override fun onError(build: Build<BaseBean>) {
                build.setErrorListener()
            }

            override fun showProgress(tag: Any?) {
                if (tag is BaseAc) tag.showProgress()
            }

            override fun hideProgress(tag: Any?) {
                if (tag is BaseAc) tag.hideProgress()
            }

        }).setOkHttpClient(OkHttpClient.Builder().cookieJar(CookieJarImpl(SPCookieStore(this))).build())
            .addCommonParams(p)
     */
}
