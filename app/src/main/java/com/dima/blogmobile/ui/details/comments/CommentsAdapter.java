package com.dima.blogmobile.ui.details.comments;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dima.blogmobile.R;
import com.dima.blogmobile.common.adapter.BaseRecyclerAdapter;
import com.dima.blogmobile.local.entity.DbComment;
import com.dima.blogmobile.utils.DateConvertUtil;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CommentsAdapter extends BaseRecyclerAdapter<DbComment, CommentsAdapter.CommentViewHolder> {


    @Override
    public int getItemCount() {
        return getItems().size();
    }

    @Override
    public CommentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_comment, parent, false);
        return new CommentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CommentViewHolder holder, int position) {
        DbComment dbComment = getItem(position);

        holder.authorTextView.setText(dbComment.getAuthor());
        holder.commentTextView.setText(dbComment.getText());
        holder.dateTextView.setText(DateConvertUtil.dateFromLong(dbComment.getDatePublic()));
    }

    public class CommentViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.authorTextView)
        TextView authorTextView;
        @BindView(R.id.dateTextView)
        TextView dateTextView;
        @BindView(R.id.commentTextView)
        TextView commentTextView;

        public CommentViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
