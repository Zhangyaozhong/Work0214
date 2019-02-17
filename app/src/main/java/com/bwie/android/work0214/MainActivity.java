package com.bwie.android.work0214;

import android.os.Build;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;

import com.bwie.android.work0214.fragment.CartFragment;
import com.bwie.android.work0214.fragment.CircleFragmennt;
import com.bwie.android.work0214.fragment.HomeFragment;
import com.bwie.android.work0214.fragment.ListFragment;
import com.bwie.android.work0214.fragment.MyFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.rg_tab)
    RadioGroup mRadioGroup;
    @BindView(R.id.send_rl)
    RelativeLayout mSendrl;
    private Fragment[] mFragments;
    private HomeFragment homeFragment;
    private CircleFragmennt circleFragmennt;
    private CartFragment cartFragment;
    private ListFragment listFragment;
    private MyFragment myFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //透明状态栏
//        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        //透明导航栏
//            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initView();
        initData();
    }

    private void initView() {
//        默认选中首页
        mRadioGroup.check(mRadioGroup.getChildAt(0).getId());

        mRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                switch (checkedId) {
                    case R.id.home_rb:
                        transaction.replace(R.id.frameLayout, mFragments[0]);
                        break;
                    case R.id.circle_rb:
                        transaction.replace(R.id.frameLayout, mFragments[1]);
                        break;
                    case R.id.list_rb:
                        transaction.replace(R.id.frameLayout, mFragments[3]);
                        break;
                    case R.id.person_rb:
                        transaction.replace(R.id.frameLayout, mFragments[4]);
                        break;
                }
                transaction.commit();
            }

        });
        mSendrl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mRadioGroup.clearCheck();
                FragmentManager manager = getSupportFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                transaction.replace(R.id.frameLayout, mFragments[2]);
                transaction.commit();
            }
        });
    }

    private void initData() {
        homeFragment = new HomeFragment();
        circleFragmennt = new CircleFragmennt();
        cartFragment = new CartFragment();
        listFragment = new ListFragment();
        myFragment = new MyFragment();
        mFragments = new Fragment[5];
        mFragments[0] = homeFragment;
        mFragments[1] = circleFragmennt;
        mFragments[2] = cartFragment;
        mFragments[3] = listFragment;
        mFragments[4] = myFragment;
        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, mFragments[0]).commit();

    }
}
