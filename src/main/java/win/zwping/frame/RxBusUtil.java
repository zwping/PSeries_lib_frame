package win.zwping.frame;

import com.blankj.rxbus.RxBus;

/**
 * <p>describe：
 * <p>    note：
 * <p> @author：zwp on 2019-05-06 16:02:12 mail：1101558280@qq.com web: http://www.zwping.win </p>
 */
public final class RxBusUtil {

    public static void unregister(Object o) {
        RxBus.getDefault().unregister(o);
    }

    public static <B> void subscribe(Object context, B b, final Callback<B> callback) {
        RxBus.getDefault().subscribe(context, new RxBus.Callback<B>() {
            @Override
            public void onEvent(B b) {
                callback.onEvent(b);
            }
        });
    }

    public static <B> void subscribe(Object context, String tag, B b, final Callback<B> callback) {
        RxBus.getDefault().subscribe(context, tag, new RxBus.Callback<B>() {
            @Override
            public void onEvent(B b) {
                callback.onEvent(b);
            }
        });
    }

    public static void post(Object o) {
        RxBus.getDefault().post(o);
    }

    public static void post(String tag, Object o) {
        RxBus.getDefault().post(o, tag);
    }

    public static void postSticky(Object o) {
        RxBus.getDefault().postSticky(o);
    }

    public static void postSticky(String tag, Object o) {
        RxBus.getDefault().postSticky(o, tag);
    }

    public static void removeSticky(Object o) {
        RxBus.getDefault().removeSticky(o);
    }

    public static void removeSticky(String tag, Object o) {
        RxBus.getDefault().removeSticky(o, tag);
    }

    //////////////////////////////

    public interface Callback<B> {
        void onEvent(B b);
    }

}
