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

        holder.bindView(mNoteData.get(position));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return mNoteData.size();
    }

    class VH extends BaseViewHolder {

        LinearLayout root;
        TextView name;
        TextView content;
        VH(View itemView) {
            super(itemView);
            root = (LinearLayout) findView(R.id.note_root);
            name = (TextView) findView(R.id.note_name);
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
            name.setText("");
            name.append(memorandum.student.nameCH);
            name.append("\n");
            name.append(memorandum.student.nameEN);
            name.append("\n");
            int length = memorandum.content.length();
            String msg = memorandum.content;
            if(length > 50) {
                msg = msg.substring(0, 50) + "...";
            }
            content.setText(msg);
        }
    }
}
