package com.bwie.android.work0214.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bwie.android.work0214.R;
import com.bwie.android.work0214.bean.ListBean;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.SearchViewHolder> {
    private Context context;
    private List<ListBean> list;

    public SearchAdapter(Context context, List<ListBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public SearchViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.search_item_layout, viewGroup, false);
        SearchViewHolder holder = new SearchViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull SearchViewHolder holder, final int i) {
        ListBean listBean = list.get(i);
        holder.tvPrice.setText("￥" + listBean.price);
        holder.tvTitle.setText(listBean.commodityName);
        holder.tvSold.setText("已售" + listBean.saleNum + "件");
        Uri uri = Uri.parse(listBean.masterPic);
        holder.ivIcon.setImageURI(uri);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (itemCallback != null) {
                    itemCallback.itemClick(i);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    public class SearchViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_icon)
        SimpleDraweeView ivIcon;
        @BindView(R.id.tv_title)
        TextView tvTitle;
        @BindView(R.id.tv_price)
        TextView tvPrice;
        @BindView(R.id.tv_sold)
        TextView tvSold;

        public SearchViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public interface ItemCallback {
        void itemClick(int position);
    }

    private ItemCallback itemCallback;

    public void setItemCallback(ItemCallback itemCallback) {
        this.itemCallback = itemCallback;
    }

}
