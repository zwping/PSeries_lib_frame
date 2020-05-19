package win.zwping.frame;

import androidx.annotation.Nullable;
import androidx.lifecycle.LifecycleOwner;

import com.blankj.rxbus.RxBus;
import win.zwping.frame.lis.RxBusCallback;

/**
 * <p>describe：RxBus
 * <p>    note：
 * <p> @author：zwp on 2019-05-06 16:02:12 mail：1101558280@qq.com web: https://www.zwping.com </p>
 */
public final class RxBusUtil {


    public static void unregister(Object o) {
        RxBus.getDefault().unregister(o);
    }

    public static void removeSticky(Object o) {
        RxBus.getDefault().removeSticky(o);
    }

    public static void removeSticky(Object o, String tag) {
        RxBus.getDefault().removeSticky(o, tag);
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

    //////////////////////////////

    public static void subscribeS(Object o, String tag, final RxBusCallback<String> callback) {
        RxBus.getDefault().subscribe(o, tag, new RxBus.Callback<String>() {
            @Override
            public void onEvent(String s) {
                callback.onEvent(s);
            }
        });
    }

    public static void subscribeI(Object o, String tag, final RxBusCallback<Integer> callback) {
        RxBus.getDefault().subscribe(o, tag, new RxBus.Callback<Integer>() {
            @Override
            public void onEvent(Integer s) {
                callback.onEvent(s);
            }
        });
    }

    public static void subscribeB(Object o, String tag, final RxBusCallback<Boolean> callback) {
        RxBus.getDefault().subscribe(o, tag, new RxBus.Callback<Boolean>() {
            @Override
            public void onEvent(Boolean s) {
                callback.onEvent(s);
            }
        });
    }

    public static void subscribeS(Object o, final RxBusCallback<String> callback) {
        RxBus.getDefault().subscribe(o, new RxBus.Callback<String>() {
            @Override
            public void onEvent(String s) {
                callback.onEvent(s);
            }
        });
    }

    public static void subscribeI(Object o, final RxBusCallback<Integer> callback) {
        RxBus.getDefault().subscribe(o, new RxBus.Callback<Integer>() {
            @Override
            public void onEvent(Integer s) {
                callback.onEvent(s);
            }
        });
    }

    public static void subscribeB(Object o, final RxBusCallback<Boolean> callback) {
        RxBus.getDefault().subscribe(o, new RxBus.Callback<Boolean>() {
            @Override
            public void onEvent(Boolean s) {
                callback.onEvent(s);
            }
        });
    }

    public static void subscribeStickyS(Object o, String tag, final RxBusCallback<String> callback) {
        RxBus.getDefault().subscribeSticky(o, tag, new RxBus.Callback<String>() {
            @Override
            public void onEvent(String s) {
                callback.onEvent(s);
            }
        });
    }

    public static void subscribeStickyI(Object o, String tag, final RxBusCallback<Integer> callback) {
        RxBus.getDefault().subscribeSticky(o, tag, new RxBus.Callback<Integer>() {
            @Override
            public void onEvent(Integer s) {
                callback.onEvent(s);
            }
        });
    }

    public static void subscribeStickyB(Object o, String tag, final RxBusCallback<Boolean> callback) {
        RxBus.getDefault().subscribeSticky(o, tag, new RxBus.Callback<Boolean>() {
            @Override
            public void onEvent(Boolean s) {
                callback.onEvent(s);
            }
        });
    }

    public static void subscribeStickyS(Object o, final RxBusCallback<String> callback) {
        RxBus.getDefault().subscribeSticky(o, new RxBus.Callback<String>() {
            @Override
            public void onEvent(String s) {
                callback.onEvent(s);
            }
        });
    }

    public static void subscribeStickyI(Object o, final RxBusCallback<Integer> callback) {
        RxBus.getDefault().subscribeSticky(o, new RxBus.Callback<Integer>() {
            @Override
            public void onEvent(Integer s) {
                callback.onEvent(s);
            }
        });
    }

    public static void subscribeStickyB(Object o, final RxBusCallback<Boolean> callback) {
        RxBus.getDefault().subscribeSticky(o, new RxBus.Callback<Boolean>() {
            @Override
            public void onEvent(Boolean s) {
                callback.onEvent(s);
            }
        });
    }

    public static <T> void subscribe(Object o, String tag, final RxBus.Callback<T> callback) {
        RxBus.getDefault().subscribe(o, tag, callback);
    }

    public static <T> void subscribe(Object o, final RxBus.Callback<T> callback) {
        RxBus.getDefault().subscribe(o, callback);
    }
    public static <T> void subscribeSticky(Object o, String tag, final RxBus.Callback<T> callback) {
        RxBus.getDefault().subscribeSticky(o, tag, callback);
    }

    public static <T> void subscribeSticky(Object o, final RxBus.Callback<T> callback) {
        RxBus.getDefault().subscribeSticky(o, callback);
    }
    //////////////////////////////
}
