package win.zwping.frame.base

import com.gyf.immersionbar.ImmersionBar;
import win.zwping.code.basic.BasicAc
import win.zwping.code.comm.CommCallback
import win.zwping.frame.RxBusUtil
import win.zwping.frame.comm.LoadingPop
import win.zwping.frame.http.Http

/**
 * <p>describe：
 * <p>    note：
 * <p> @author：zwp on 2019-05-08 11:43:02 mail：1101558280@qq.com web: https://www.zwping.com </p>
 */
abstract class BaseAc : BasicAc() {

    override fun onDestroy() {
        Http.destroy(this)
        super.onDestroy()
        RxBusUtil.unregister(this)
    }

    private var loading: LoadingPop? = null

    fun showProgress(txt: CharSequence? = null) {
        if (loading == null) loading = LoadingPop(this)
        loading?.also {
            runOnUiThread {
                it.setTxt(txt)
                if (!it.isShowing) it.showPopupWindow()
            }
        }
    }

    fun hideProgress() {
        loading?.also {
            runOnUiThread { it.dismiss() }
        }
    }

}