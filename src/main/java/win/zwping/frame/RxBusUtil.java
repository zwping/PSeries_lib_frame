package win.zwping.frame;

import com.blankj.rxbus.RxBus;

/**
 * <p>describe：RxBus
 * <p>    note：
 * <p> @author：zwp on 2019-05-06 16:02:12 mail：1101558280@qq.com web: http://www.zwping.win </p>
 */
public final class RxBusUtil {

    public static void unregister(Object o) {
        RxBus.getDefault().unregister(o);
    }

    public static <B> void subscribe(Object o, RxBus.Callback<B> callback) {
        RxBus.getDefault().subscribe(o, callback);
    }

    public static <B> void subscribe(final Object o, final String tag, RxBus.Callback<B> callback) {
        RxBus.getDefault().subscribe(o, tag, callback);
    }

    public static void post(Object o) {
        RxBus.getDefault().post(o);
    }

    public static void post(Object o, String tag) {
        RxBus.getDefault().post(o, tag);
    }

    public static void postSticky(Object o) {
        RxBus.getDefault().postSticky(o);
    }

    public static void postSticky(Object o, String tag) {
        RxBus.getDefault().postSticky(o, tag);
    }

    public static void removeSticky(Object o) {
        RxBus.getDefault().removeSticky(o);
    }

    public static void removeSticky(Object o, String tag) {
        RxBus.getDefault().removeSticky(o, tag);
    }

    //////////////////////////////

    public interface Callback<B> {
        void onEvent(B b);
    }

    ///////////////
    /*

        RxBusUtil.subscribe(this, "notifyRegion", object : RxBus.Callback<String>() {
            override fun onEvent(s: String) {
            }
        })



     */

}
