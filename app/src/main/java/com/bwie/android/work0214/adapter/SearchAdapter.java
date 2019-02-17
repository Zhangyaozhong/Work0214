package com.bwie.android.work0214.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bwie.android.work0214.R;
import com.bwie.android.work0214.bean.ListBean;
import com.bwie.android.work0214.bean.SearchBean;

import java.util.List;

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
        holder.price.setText("￥" + listBean.price);
        holder.title.setText(listBean.commodityName);
        holder.sold.setText("已售" + listBean.saleNum + "件");
        Uri uri = Uri.parse(listBean.masterPic);
        holder.icon.setImageURI(uri);

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
        private ImageView icon;
        private TextView title, price, sold;

        public SearchViewHolder(@NonNull View itemView) {
            super(itemView);
            icon = itemView.findViewById(R.id.iv_icon);
            title = itemView.findViewById(R.id.tv_title);
            price = itemView.findViewById(R.id.tv_price);
            sold = itemView.findViewById(R.id.tv_sold);
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
