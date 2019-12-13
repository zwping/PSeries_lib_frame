package win.zwping.frame.mvvm;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

import win.zwping.frame.base.BaseAc;

/**
 * 支持mvvm的ac基类，支持ktx混用
 * @param <Bind>
 */
public abstract class BaseVmAc<Bind extends ViewDataBinding> extends BaseAc {

    protected Bind bind;

    public abstract int bindVmLayout();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        bind = DataBindingUtil.setContentView(this, bindVmLayout());
        super.onCreate(savedInstanceState);
    }

    @Override
    @Deprecated
    public final int bindLayout() {
        return 0;
    }
}
