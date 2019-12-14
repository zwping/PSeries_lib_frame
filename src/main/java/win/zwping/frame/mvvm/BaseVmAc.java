package win.zwping.frame.mvvm;

import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

import win.zwping.frame.base.BaseAc;

/**
 * 支持mvvm的ac基类，支持ktx混用
 *
 * @param <Bind>
 */
public abstract class BaseVmAc<Bind extends ViewDataBinding> extends BaseAc {

    protected Bind bind;

    @Override
    public void setContentView() {
        //super.setContentView();
        bind = DataBindingUtil.setContentView(this, bindLayout());
    }

}
