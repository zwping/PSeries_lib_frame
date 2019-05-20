package win.zwping.frame.comm;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import androidx.annotation.ColorInt;
import androidx.annotation.Nullable;
import razerdp.basepopup.BasePopupWindow;
import win.zwping.code.review.PEditText;
import win.zwping.code.review.PImageView;
import win.zwping.code.review.PTextView;
import win.zwping.code.utils.ScreenUtil;
import win.zwping.frame.R;

import static android.view.View.GONE;

/**
 * <p>describe：
 * <p>    note：
 * <p> @author：zwp on 2019-05-06 16:50:17 mail：1101558280@qq.com web: http://www.zwping.win </p>
 */
public class CommPop extends BasePopupWindow {

    protected PImageView exitPiv;
    protected PEditText contentPet;
    protected PTextView contentPtv, cancelPtv, confirmPtv;
    protected View line, hLine;

    private boolean autoCancel = true;

    public CommPop(Context context) {
        super(context, (int) (ScreenUtil.getScreenWidth() * 0.75), ViewGroup.LayoutParams.WRAP_CONTENT);
        setPopupGravity(Gravity.CENTER);
        exitPiv = findViewById(R.id.exit_piv);
        contentPet = findViewById(R.id.content_pet);
        contentPtv = findViewById(R.id.content_ptv);
        cancelPtv = findViewById(R.id.cancel_ptv);
        confirmPtv = findViewById(R.id.confirm_ptv);
        line = findViewById(R.id.line_v);
        hLine = findViewById(R.id.h_line_v);
        exitPiv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        cancelPtv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (autoCancel) dismiss();
            }
        });

        setAllowDismissWhenTouchOutside(false);
    }


    @Override
    public View onCreateContentView() {
        return View.inflate(getContext(), R.layout.comm_pop, null);
    }

    public CommPop setIsInput(Boolean input) {
        contentPet.setVisibility(input ? View.VISIBLE : GONE);
        contentPet.setVisibility(input ? GONE : View.VISIBLE);
        return this;
    }

    public CommPop setCancelTxt(CharSequence s) {
        cancelPtv.setText(s);
        return this;
    }

    public CommPop setConfirmTxt(CharSequence s) {
        confirmPtv.setText(s);
        return this;
    }

    public CommPop setContent(CharSequence s) {
        contentPet.setText(s);
        contentPtv.setText(s);
        return this;
    }

    public CommPop setContentHint(CharSequence s) {
        contentPet.setHint(s);
        return this;
    }

    public CommPop setContentView(View view) {
        RelativeLayout rl = findViewById(R.id.content_rl);
        for (int i = 0; i < rl.getChildCount(); i++)
            rl.getChildAt(i).setVisibility(GONE);
        rl.addView(view);
        return this;
    }

    public CommPop setCancelHide() {
        cancelPtv.setVisibility(GONE);
        return this;
    }

    public CommPop setShowExit() {
        exitPiv.setVisibility(View.VISIBLE);
        return this;
    }

    public CommPop setOutsideDismiss() {  // 逆默认值
        setOutsideDismiss(true);
        return this;
    }

    public CommPop setOutsideDismiss(Boolean dismiss) {
        setAllowDismissWhenTouchOutside(dismiss);
        return this;
    }

    public CommPop setBackPressDismiss() { // 逆默认值
        setBackPressDismiss(true);
        return this;
    }

    public CommPop setBackPressDismiss(Boolean dismiss) {
        setBackPressEnable(dismiss);
        return this;
    }

    public CommPop setCancelTxtColor(@ColorInt int c) {
        cancelPtv.setTextColor(c);
        return this;
    }

    public CommPop setConfirmTxtColor(@ColorInt int c) {
        confirmPtv.setTextColor(c);
        return this;
    }

    public CommPop setCancelLis(final OnCancelClickListener lis) {
        autoCancel = false;
        cancelPtv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lis.onCancel();
            }
        });
        return this;
    }

    public CommPop setConfirmLis(final OnConfirmClickListener lis) {
        confirmPtv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lis.onConfirm(CommPop.this, contentPet.getContent());
            }
        });
        return this;
    }

    public CommPop setHandleOj(OnHandleOjListener lis) {
        lis.onHandle(this);
        return this;
    }

    public CommPop setHandleView(OnHandleViewListener lis) {
        lis.onHandle(getContentView());
        return this;
    }


    public void show() {
        showPopupWindow();
    }

    ///////////////////////////

    public interface OnConfirmClickListener {
        void onConfirm(CommPop pop, @Nullable String s);
    }

    public interface OnCancelClickListener {
        void onCancel();
    }

    public interface OnHandleOjListener {
        void onHandle(CommPop pop);
    }

    public interface OnHandleViewListener {
        void onHandle(View view);
    }
}
