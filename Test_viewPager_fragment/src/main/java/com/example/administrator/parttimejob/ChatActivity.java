package com.example.administrator.parttimejob;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.hyphenate.easeui.ui.EaseChatFragment;

/**
 * Created by Administrator on 2017/6/14.
 */

public class ChatActivity extends AppCompatActivity {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        EaseChatFragment chatFragment = new EaseChatFragment();

        chatFragment.setArguments(getIntent().getExtras());

        getSupportFragmentManager().beginTransaction().add(R.id.ec_layout_container, chatFragment).commit();

    }

}
