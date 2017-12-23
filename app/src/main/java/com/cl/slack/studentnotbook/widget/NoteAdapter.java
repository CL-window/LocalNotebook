package com.cl.slack.studentnotbook.widget;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cl.slack.studentnotbook.R;
import com.cl.slack.studentnotbook.bean.Memorandum;
import com.cl.slack.studentnotbook.data.NoteData;
import com.cl.slack.studentnotbook.util.ScreenHelper;

/**
 * Created by slack
 * on 17/12/22 下午7:48
 */

public class NoteAdapter extends BaseAdapter<NoteAdapter.VH> {

    private NoteData mNoteData = NoteData.data;
    public NoteAdapter(@NonNull RecyclerView recyclerView) {
        super(recyclerView);
    }

    @Override
    public VH onCreateViewHolder(ViewGroup parent, int viewType) {
        return new VH(inflateView(R.layout.layout_note, parent, false));
    }

    @Override
    public void onBindViewHolder(VH holder, int position) {

        final Memorandum memorandum = mNoteData.get(position);
        holder.bindView(memorandum);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mCallback != null) {
                    mCallback.onClick(memorandum);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mNoteData.size();
    }

    class VH extends BaseViewHolder {

        int MAX_TEXT = 80;
        LinearLayout root;
        TextView time;
        TextView content;
        VH(View itemView) {
            super(itemView);
            root = (LinearLayout) findView(R.id.note_root);
            time = (TextView) findView(R.id.note_time);
            content = (TextView) findView(R.id.note_content);
        }

        void bindView(Memorandum memorandum) {
            int w = ScreenHelper.helper.getWidth() / 2;
            ViewGroup.LayoutParams param = root.getLayoutParams();
            if(param == null) {
                param = new ViewGroup.LayoutParams(w, w);
            }
            param.height = w;
            param.width = w;

            if(memorandum == null) {
                return;
            }
            time.setText(memorandum.data);
            int length = memorandum.content.length();
            String msg = memorandum.content;
            if(length > MAX_TEXT) {
                msg = msg.substring(0, MAX_TEXT) + "...";
            }
            content.setText(msg);
        }
    }

    private Callback mCallback;
    public void setCallback(Callback callback) {
        this.mCallback = callback;
    }

    public interface Callback {
        void onClick(Memorandum memorandum);
    }
}
