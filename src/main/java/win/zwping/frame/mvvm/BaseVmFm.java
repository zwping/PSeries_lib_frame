package win.zwping.frame.mvvm;

import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

import win.zwping.frame.base.BaseFm;

public abstract class BaseVmFm<Bind extends ViewDataBinding> extends BaseFm {

    protected Bind bind;

    @Override
    public View setContentView(@NonNull LayoutInflater inflater) {
        bind = DataBindingUtil.inflate(mInflater, bindLayout(), mContainer, false);
        return bind.getRoot();
    }
}
