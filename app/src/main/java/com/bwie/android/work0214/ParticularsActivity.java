package com.bwie.android.work0214;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.bwie.android.work0214.bean.ParticularsBean;
import com.bwie.android.work0214.contract.ParticularsContract;
import com.bwie.android.work0214.presenter.ParticularsPresenter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ParticularsActivity extends AppCompatActivity implements ParticularsContract.IParticularsView {
    private ParticularsPresenter particularsPresenter;
    @BindView(R.id.mWv)
    WebView mWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_particulars);
        EventBus.getDefault().register(this);
        ButterKnife.bind(this);

//        initData();
    }

    private void initData() {


    }

    //接收传过来的id
    @Subscribe(sticky = true)
    public void recive(String id) {
        particularsPresenter = new ParticularsPresenter(this);
        HashMap<String, String> params = new HashMap<>();
        params.put("commodityId", id);
        particularsPresenter.obtinData(params);
        Toast.makeText(this, id, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {

        EventBus.getDefault().unregister(this);
        if (mWebView != null) {
            mWebView.loadDataWithBaseURL(null, "", "text/html", "utf-8", null);
            mWebView.clearHistory();

            ((ViewGroup) mWebView.getParent()).removeView(mWebView);
            mWebView.destroy();
            mWebView = null;
        }
        super.onDestroy();
    }

    /**
     * 请求成功的回调方法
     *
     * @param resultBean
     */
    @Override
    public void success(ParticularsBean.ResultBean resultBean) {
        if (resultBean != null) {
            String details = resultBean.getDetails();
            mWebView.loadData(details, "text/html", "UTF-8");
            mWebView.setWebViewClient(new WebViewClient());
            WebSettings settings = mWebView.getSettings();
            mWebView.setHorizontalScrollBarEnabled(false);//水平不显示
            mWebView.setVerticalScrollBarEnabled(false); //垂直不显示

            //支持屏幕缩放
            settings.setSupportZoom(true);
            settings.setBuiltInZoomControls(true);
            //设置自适应屏幕，两者合用
            settings.setUseWideViewPort(true); //将图片调整到适合webview的大小
            settings.setLoadWithOverviewMode(true); // 缩放至屏幕的大小
        }


    }

    @Override
    public void failure(String msg) {
        Toast.makeText(ParticularsActivity.this, msg, Toast.LENGTH_SHORT).show();
    }
    @Override
    public void finish() {
        ViewGroup view = (ViewGroup) getWindow().getDecorView();
        view.removeAllViews();
        super.finish();
    }


}
