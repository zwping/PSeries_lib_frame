package win.zwping.frame.mvvm;

import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;

import java.lang.reflect.ParameterizedType;

/**
 * 基础的ViewModel <br />
 * 如需多个界面共享一个数据，可将派生类变换为单例类
 *
 * @param <Bean>
 */
public abstract class BaseVModel<Bean> extends ViewModel {

    /**
     * 基类中针对简单情况预设了一个liveData，免去重复代码 <br />
     * 如需更多的共享数据，可在派生类中自定义
     */
    public MutableLiveData<Bean> liveData;

    public BaseVModel() {
        this.liveData = new MutableLiveData<>();
        try {
            ParameterizedType type = (ParameterizedType) this.getClass().getGenericSuperclass();
            Class clazz = (Class<Bean>) type.getActualTypeArguments()[0];
            setBean((Bean) clazz.newInstance());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public BaseVModel<Bean> setBean(Bean bean) {
        liveData.setValue(bean);
        return this;
    }

    public BaseVModel<Bean> setPostBean(Bean bean) {
        liveData.postValue(bean);
        return this;
    }

    public Bean getBean() {
        return liveData.getValue();
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
}
