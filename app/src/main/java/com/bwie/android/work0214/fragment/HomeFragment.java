package com.bwie.android.work0214.fragment;

import android.content.Intent;
import android.os.Build;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bwie.android.work0214.ParticularsActivity;
import com.bwie.android.work0214.R;
import com.bwie.android.work0214.adapter.SearchAdapter;
import com.bwie.android.work0214.base.BaseFragment;
import com.bwie.android.work0214.bean.ListBean;
import com.bwie.android.work0214.bean.SearchBean;
import com.bwie.android.work0214.contract.SearchContract;
import com.bwie.android.work0214.database.greenDao.db.DaoSession;
import com.bwie.android.work0214.database.greenDao.db.ListBeanDao;
import com.bwie.android.work0214.presenter.SearchPresenter;
import com.bwie.android.work0214.utils.GreenDaoUtil;
import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;


public class HomeFragment extends BaseFragment implements SearchContract.ISearchView, SearchAdapter.ItemCallback {

    @BindView(R.id.mXRecycleView)
    XRecyclerView mXRecycleView;
    @BindView(R.id.iv_more)
    ImageView iv_more;
    @BindView(R.id.et_search)
    EditText et_search;
    @BindView(R.id.tv_search)
    TextView tv_search;
    private SearchPresenter searchPresenter;
    private SearchAdapter searchAdapter;
    private List<ListBean> dataList = new ArrayList<>();
    private List<ListBean> listBeans = new ArrayList<>();
    private int page = 1;
    private String content;
    private String count = "10";
    private int FLAG = 1;

    @Override
    protected void setUpData() {
        searchPresenter = new SearchPresenter(this);

        //搜索的点击事件
        tv_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                content = et_search.getText().toString().trim();
                requestData();
            }
        });

    }

    private void requestData() {

//        if (listBeans != null && listBeans.size() > 0) {
//            setSearchAdapter();
//        } else {
        HashMap<String, String> params = new HashMap<>();
        params.put("keyword", content);
        params.put("page", "" + page);
        params.put("count", count);
        if (!TextUtils.isEmpty(content)) {
            searchPresenter.obtinData(params);
        } else {
            Toast.makeText(getActivity(), "不能为空", Toast.LENGTH_SHORT).show();
        }
//        }

    }

    /**
     * 一些View的相关操作
     */
    @Override
    protected void setUpView() {
        // 可以设置是否开启加载更多/下拉刷新
        mXRecycleView.setLoadingMoreEnabled(true);
        // 可以设置加载更多的样式，很多种
        mXRecycleView.setLoadingMoreProgressStyle(ProgressStyle.Pacman);
        // 如果设置上这个，下拉刷新的时候会显示上次刷新的时间
        mXRecycleView.getDefaultRefreshHeaderView() // get default refresh header view
                .setRefreshTimeVisible(true);  // make refresh time visible,false means hiding
        mXRecycleView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                FLAG = 1;
                requestData();
                mXRecycleView.refreshComplete();


            }

            @Override
            public void onLoadMore() {
                FLAG = 2;
                page++;
                requestData();

                mXRecycleView.loadMoreComplete();


            }
        });
        mXRecycleView.setLayoutManager(new

                GridLayoutManager(getActivity(), 2, LinearLayoutManager.VERTICAL, false));

    }

    @Override
    protected int setLayoutResourceID() {
        //透明状态栏
        getActivity().getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        //透明导航栏
//            getActivity().getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        return R.layout.fragment_home;
    }

    /**
     * 请求接口成功的回调方法
     *
     * @param list
     */
    @Override
    public void success(List<SearchBean.ResultBean> list) {
        DaoSession daoSession = GreenDaoUtil.getInstance(getActivity()).getDaoSession();
        ListBeanDao listBeanDao = daoSession.getListBeanDao();
//        请求成功后  添加到数据库
        for (SearchBean.ResultBean bean : list) {
            ListBean listBean = new ListBean(Long.parseLong(bean.commodityId), bean.commodityName, bean.masterPic, bean.price, bean.saleNum);
            this.listBeans.add(listBean);
            //添加
            listBeanDao.insertOrReplace(listBean);
        }
        //FLAG 标识  1代表刷新  2代表加载
        if (FLAG == 1) {
            dataList.clear();
        }
        dataList.addAll(this.listBeans);
        setSearchAdapter(dataList);
          /*  for (SearchBean.ResultBean resultBean : list) {
                Toast.makeText(getActivity(), resultBean.commodityName, Toast.LENGTH_SHORT).show();
            }
        }*/

    }

    //设置适配器的方法
    private void setSearchAdapter(List<ListBean> dataList) {
        if (dataList != null && dataList.size() > 0) {
            //设置适配器
            if (searchAdapter == null) {
                searchAdapter = new SearchAdapter(getActivity(), dataList);
                mXRecycleView.setAdapter(searchAdapter);
            } else {
                searchAdapter.notifyDataSetChanged();
            }
            searchAdapter.setItemCallback(this);

        }

    }

    @Override
    public void failure(String msg) {
        Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
//                上来首先判断数据库有没有数据  如果有则直接设置适配器 没有请求网络获取
        DaoSession daoSession = GreenDaoUtil.getInstance(getActivity()).getDaoSession();
        ListBeanDao listBeanDao = daoSession.getListBeanDao();
//        查询所有
        List<ListBean> listBeans = listBeanDao.loadAll();
        setSearchAdapter(listBeans);
//        if (listBeans != null && listBeans.size() > 0) {
//            SearchAdapter searchAdapter = new SearchAdapter(getActivity(), listBeans);
//            mXRecycleView.setAdapter(searchAdapter);
//        }
//        new SearchAdapter()
    }

    @Override
    public void itemClick(int position) {
        ListBean listBean = dataList.get(position);
        String commodityId = String.valueOf(listBean.commodityId);
        EventBus.getDefault().postSticky(commodityId);
        startActivity(new Intent(getActivity(), ParticularsActivity.class));
    }


}
