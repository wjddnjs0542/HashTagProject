package com.android.lee.hashtagproject;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.android.lee.hashtagproject.databinding.ActivityResultBinding;

/**
 * <PRE>
 *      TextActivities
 * </PRE>
 * Created by Lee on 2017. 09. 14..
 */

public class ResultActivity extends AppCompatActivity {

    private ActivityResultBinding mBinding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(ResultActivity.this, R.layout.activity_result);
        String tag = getIntent().getStringExtra(IntentExtras.EXTRA_HASH_TEXT);
        mBinding.tvResult.setText(tag);
    }
}
