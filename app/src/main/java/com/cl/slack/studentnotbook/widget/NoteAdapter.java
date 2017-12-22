package com.cl.slack.studentnotbook.widget;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cl.slack.studentnotbook.NoteData;
import com.cl.slack.studentnotbook.R;
import com.cl.slack.studentnotbook.bean.Memorandum;

/**
 * Created by slack
 * on 17/12/22 下午7:48
 */

public class NoteAdapter extends BaseAdapter<NoteAdapter.VH> {

    private NoteData mNoteData;
    public NoteAdapter(@NonNull RecyclerView recyclerView, @NonNull NoteData data) {
        super(recyclerView);
        mNoteData = data;
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

        TextView name;
        TextView content;
        VH(View itemView) {
            super(itemView);
            name = (TextView) findView(R.id.note_name);
            content = (TextView) findView(R.id.note_content);
        }

        void bindView(Memorandum memorandum) {
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
