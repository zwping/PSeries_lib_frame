package win.zwping.frame.mvvm;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.Arrays;

import win.zwping.code.comm.CommCallback;

import static win.zwping.code.utils.EmptyUtil.isNotEmpty;

/**
 * 基础的ViewModel <br />
 * 如需多个界面共享一个数据，可将派生类变换为单例类
 *
 * @param <Bean>
 */
public abstract class BaseVModel<Bean> extends ViewModel implements IVModel {

    /**
     * 基类中针对简单情况预设了一个liveData，免去重复代码 <br />
     * 如需更多的共享数据，可在派生类中自定义
     */
    public MutableLiveData<Bean> liveData;
    private Bean __bean;

    public BaseVModel() {
        this.liveData = new MutableLiveData<>();
        initBean();
    }

    @Override
    public void initBean() {
        try {
            ParameterizedType type = (ParameterizedType) this.getClass().getGenericSuperclass();
            Class clazz = (Class<Bean>) type.getActualTypeArguments()[0];
            // loopInit(clazz);
            setBean((Bean) clazz.newInstance());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @param clazz
     * @deprecated 不能有效的初始化内部class类的值
     */
    @Deprecated
    private void loopInit(Class clazz) {
        Class[] cs = clazz.getDeclaredClasses();
        if (isNotEmpty(cs)) {
            for (Class c : cs) {
                try {
//                    Field[] fs = c.getDeclaredFields();
//                    for (Field f : fs) {
//                        System.out.println(clazz.getName() +"-" + f.getGenericType().toString() + "----" + c.getName());
//                        if (f.getGenericType().toString().contains(c.getName())) {
//                            f.set(f.getName(), c.newInstance());
//                            System.out.println("----" + f.getName() + c.newInstance());
//                        }
//                    }
                    loopInit(c);
                } catch (Exception e) {
                    System.out.println("loop init exception");
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 设置实体，更新数据
     *
     * @param bean
     * @return
     */
    public BaseVModel<Bean> setBean(@Nullable Bean bean) {
        if (bean != null) liveData.setValue(__bean = bean);
        return this;
    }

    /**
     * 设置实体，更新数据视图
     *
     * @param bean
     * @return
     */
    public BaseVModel<Bean> setPostBean(@Nullable Bean bean) {
        if (bean != null) liveData.postValue(__bean = bean);
        return this;
    }

    public Bean getBean() {
        return liveData.getValue();
    }

    public void clearBean() {
        initBean();
    }

    /**
     * 创建观察者 <br />
     * 多用于将Bean赋值給dataBinding，这样每次setBean数值均会自动更改
     *
     * @param owner
     * @param observer
     * @return
     */
    public BaseVModel<Bean> observe(@NonNull LifecycleOwner owner, @NonNull Observer<Bean> observer) {
        liveData.observe(owner, observer);
        return this;
    }

    public BaseVModel<Bean> notifyChange(CommCallback<Bean> callback) {
        callback.callback(__bean);
        setBean(__bean);
        return this;
    }
}
