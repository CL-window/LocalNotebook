package com.cl.slack.studentnotbook.activity;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.PopupWindow;

import com.cl.slack.studentnotbook.R;
import com.cl.slack.studentnotbook.bean.Memorandum;

/**
 * Created by slack
 * on 17/12/23 下午5:55
 */

public class NoteEditDialog extends PopupWindow implements View.OnClickListener{

    Memorandum mMemorandum;
    Activity mActivity;
    EditText mContent;
    public NoteEditDialog(@NonNull Activity context, Memorandum memorandum) {
        super(context);
        mActivity = context;
        mMemorandum = memorandum;
        initView(context);
    }

    private void initView(Context context) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.layout_note_edit, null);

        this.setContentView(view);

        mContent = view.findViewById(R.id.memorandum_edit_content);
        view.findViewById(R.id.memorandum_delete_btn).setOnClickListener(this);
        view.findViewById(R.id.memorandum_update_btn).setOnClickListener(this);

        mContent.setText(mMemorandum.content);
        mContent.setSelection(mContent.getText().length() );

        //设置可以获取焦点，否则弹出菜单中的EditText是无法获取输入的
        this.setFocusable(true);
        //这句是为了防止弹出菜单获取焦点之后，点击activity的其他组件没有响应
        this.setBackgroundDrawable(new BitmapDrawable());
        //防止虚拟软键盘被弹出菜单遮住
        this.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.memorandum_delete_btn:
                deleteMemorandum();
                break;
            case R.id.memorandum_update_btn:
                updateMemorandum();
                break;
        }
    }

    private void updateMemorandum() {
        String content = mContent.getText().toString().trim();
        if(mCallback != null) {
            mCallback.onUpdate(mMemorandum, content);
        }
        dismiss();
    }

    private void deleteMemorandum() {
        if(mCallback != null) {
            mCallback.onDelete(mMemorandum);
        }
        dismiss();
    }

    Callback mCallback;
    public NoteEditDialog setCallback(Callback callback) {
        mCallback = callback;
        return this;
    }
    public interface Callback {
        void onDelete(Memorandum memorandum);
        void onUpdate(Memorandum memorandum, String msg);
    }
}
