package com.dima.blogmobile.ui.list.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dima.blogmobile.R;
import com.dima.blogmobile.common.adapter.BaseRecyclerAdapter;
import com.dima.blogmobile.local.entity.DbPost;
import com.dima.blogmobile.utils.DateConvertUtil;

import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PostsAdapter extends BaseRecyclerAdapter<DbPost, PostsAdapter.PostsViewHolder> {


    private OnPostItemClickListener onPostItemClickListener;

    public void setOnPostItemClickListener(OnPostItemClickListener onPostItemClickListener) {
        this.onPostItemClickListener = onPostItemClickListener;
    }

    public interface OnPostItemClickListener {
        void onPostItemClicked(DbPost dbPost);
    }

    @Override
    public int getItemCount() {
        return getItems().size();
    }

    @NonNull
    @Override
    public PostsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_post, parent, false);
        return new PostsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PostsViewHolder holder, int position) {
        DbPost dbPost = getItem(position);

        holder.postTitleTextView.setText(dbPost.getTitle());
        holder.messageTextView.setText(dbPost.getText());
        holder.publicDateTextView.setText(DateConvertUtil.dateFromLong(dbPost.getDatePublic()));
//        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
//        String date = formatter.format(new Date(dbPost.getDatePublic()));
    }


    public class PostsViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.postTitleTextView)
        TextView postTitleTextView;
        @BindView(R.id.messageTextView)
        TextView messageTextView;
        @BindView(R.id.publicDateTextView)
        TextView publicDateTextView;

        public PostsViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @OnClick(R.id.postItemLayout)
        protected void onItemClicked() {
            int position = getAdapterPosition();
            if (position != RecyclerView.NO_POSITION && onPostItemClickListener != null) {
                onPostItemClickListener.onPostItemClicked(getItem(position));
            }
        }
    }
}
