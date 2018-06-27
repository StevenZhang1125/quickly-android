package com.zzh.myframework.base;

import android.support.annotation.DrawableRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.StringRes;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.zzh.myframework.R;


/**
 * <pre>
 *     author : ZhangZhanghua
 *     time   : 2018/04/16
 *     desc   : 带标题的activity基类
 * </pre>
 */
public abstract class BaseToolBarActivity extends BaseActivity implements IBaseView {

    protected View mContentView;
    protected TextView tvToolbarText;
    protected TextView tvToolbarSubText;
    protected ImageView ivToolbarRight;
    protected Toolbar toolbar;

    @Override
    protected void setBaseView(@LayoutRes int layoutId) {
        if (layoutId <= 0) return;
        View view = LayoutInflater.from(this).inflate(R.layout.activity_base_toolbar, (ViewGroup) getWindow().getDecorView().getRootView(), false);
        FrameLayout mFlContent = view.findViewById(R.id.flContent);
        mContentView = LayoutInflater.from(this).inflate(layoutId, mFlContent, false);
        mFlContent.addView(mContentView);
        setContentView(view);
        toolbar = findViewById(R.id.toolbar);
        tvToolbarText = findViewById(R.id.tvToolbarText);
        tvToolbarSubText = findViewById(R.id.tvToolbarSubText);
        ivToolbarRight = findViewById(R.id.ivToolbarRight);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        setTitle(getTitle());
    }

    @Override
    public void setTitle(CharSequence title) {
        tvToolbarText.setText(title);
    }

    @Override
    public void setTitle(int titleId) {
        tvToolbarText.setText(titleId);
    }

    protected void setBackEnable(boolean isEnable) {
        getSupportActionBar().setDisplayHomeAsUpEnabled(isEnable);
        getSupportActionBar().setDisplayShowHomeEnabled(isEnable);
    }

    protected void setSubText(String text, View.OnClickListener onClickListener) {
        tvToolbarSubText.setText(text);
        tvToolbarSubText.setOnClickListener(onClickListener);
        tvToolbarSubText.setVisibility(View.VISIBLE);
        ivToolbarRight.setVisibility(View.GONE);
    }

    protected void setSubText(@StringRes int rid, View.OnClickListener onClickListener) {
        tvToolbarSubText.setText(rid);
        tvToolbarSubText.setOnClickListener(onClickListener);
        tvToolbarSubText.setVisibility(View.VISIBLE);
        ivToolbarRight.setVisibility(View.GONE);
    }

    protected void setRightImage(@DrawableRes int rid, View.OnClickListener onClickListener) {
        ivToolbarRight.setImageResource(rid);
        ivToolbarRight.setOnClickListener(onClickListener);
        ivToolbarRight.setVisibility(View.VISIBLE);
        ivToolbarRight.setVisibility(View.GONE);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }
}