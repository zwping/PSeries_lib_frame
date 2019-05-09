package win.zwping.frame;

import android.content.Context;

import com.blankj.rxbus.RxBus;
import com.orhanobut.hawk.Hawk;
import win.zwping.code.Util;

/**
 * <p>describe：替换SharedPreferences的框架
 * <p>    note：
 * <p> @author：zwp on 2019-04-12 10:19:04 mail：1101558280@qq.com web: http://www.zwping.win </p>
 */
public class HawkUtil {

    public static void init(Context context) {
        Hawk.init(context).build();
    }

    public HawkUtil() {
        throw new UnsupportedOperationException("u can't instantiate me..."); //不支持操作的异常
    }

    public static <T> boolean put(String key, T value) {
        return Hawk.put(key, value);
    }

    public static String getString(String key) {
        return Hawk.get(key, null);
    }

    public static int getInt(String key) {
        return Hawk.get(key, -1);
    }

    public static long getLong(String key) {
        return Hawk.get(key, 0L);
    }

    public static Boolean getBoolean(String key) {
        return Hawk.get(key, false);
    }

    public static <T> T get(String key) {
        return Hawk.get(key);
    }

    public static void delete(String key) {
        Hawk.delete(key);
    }

    public static void deleteAll() {
        Hawk.deleteAll();
    }
}
