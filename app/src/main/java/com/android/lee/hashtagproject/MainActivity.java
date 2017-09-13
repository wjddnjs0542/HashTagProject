package com.android.lee.hashtagproject;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.android.lee.hashtagproject.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, HashTagClickListener{

    private ActivityMainBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(MainActivity.this, R.layout.activity_main);
        mBinding.btnHashTagAdd.setOnClickListener(this);
        mBinding.tvHashTag.setHashClickListener(this);
    }

    @Override
    public void onClick(View view) {
        mBinding.tvHashTag.setText(mBinding.etHashTag.getText().toString());
    }

    @Override
    public void OnHashClick(String hashTag) {
        Intent intent = new Intent(MainActivity.this, ResultActivity.class);
        intent.putExtra(IntentExtras.EXTRA_HASH_TEXT, hashTag);
        startActivity(intent);
    }
}
