package win.zwping.frame;

import org.greenrobot.eventbus.EventBus;

/**
 * <p>describe：对事件总线eventBus的总结
 * <p>    note：
 * <p>  author：zwp on 2017/8/9 0009 mail：1101558280@qq.com web: http://www.zwping.win </p>
 */

public final class EventBusUtil {

    /**
     * 注册订阅者
     *
     * @param subscriber
     */
    public static void register(Object subscriber) {
        if (!EventBus.getDefault().isRegistered(subscriber))
            EventBus.getDefault().register(subscriber);
    }

    /**
     * 注销订阅者
     *
     * @param subscriber
     */
    public static void unregister(Object subscriber) {
        EventBus.getDefault().unregister(subscriber);
    }

    /***
     * 注销粘性事件的订阅者
     * ***/
    public static void unregisterSticky(Object subscriber) {
        EventBus.getDefault().removeAllStickyEvents();
        unregister(subscriber);
    }

    /**
     * 发送事件
     *
     * @param bean 索引bean
     */
    public static void post(Object bean) {
        EventBus.getDefault().post(bean);
    }

    /**
     * 发送粘性事件
     *
     * @param bean 索引bean
     */
    public static void postSticky(Object bean) {
        EventBus.getDefault().postSticky(bean);
    }

    /**
     * 接收事件
     * {@link org.greenrobot.eventbus.ThreadMode#POSTING} 在发布事件的线程执行
     * {@link org.greenrobot.eventbus.ThreadMode#MAIN} 在主线程执行
     * {@link org.greenrobot.eventbus.ThreadMode#BACKGROUND} 如果发布事件的线程为主线程则新建一个线程执行，否则在发布事件的线程执行
     * {@link org.greenrobot.eventbus.ThreadMode#ASYNC} 在新的线程执行
     */
    //@Subscribe(threadMode = ThreadMode.MAIN_ORDERED,sticky = true)
    //public void onMoonEvent(MessageEvent event){
    //     EventBusUtil.unregisterSticky(this);
    //}

    //    @Override
    //    protected void onDestroy() {
    //        super.onDestroy();
    //        EventBusUtil.unregisterSticky(this);
    //    }
}
