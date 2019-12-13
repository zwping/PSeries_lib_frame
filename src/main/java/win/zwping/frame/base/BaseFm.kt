package win.zwping.frame.base;

import android.os.Bundle
import win.zwping.code.basic.BasicFm
import win.zwping.frame.RxBusUtil
import win.zwping.frame.http.Http

/**
 * <p>describe：
 * <p>    note：
 * <p> @author：zwp on 2019-05-08 11:43:10 mail：1101558280@qq.com web: http://www.zwping.win </p>
 */
abstract class BaseFm : BasicFm() {

    protected var ac: BaseAc? = null

    override fun onCreateViewLazy(savedInstanceState: Bundle?) {
        super.onCreateViewLazy(savedInstanceState)
        val ac1 = mActivity
        this.ac = if (ac1 is BaseAc) ac1 else null
    }

    override fun onDestroy() {
        Http.destroy(this)
        super.onDestroy()
        RxBusUtil.unregister(this)
    }

    fun showProgress(txt: CharSequence? = null) {
        ac?.showProgress(txt)
    }

    fun hideProgress() {
        ac?.hideProgress()
    }

}