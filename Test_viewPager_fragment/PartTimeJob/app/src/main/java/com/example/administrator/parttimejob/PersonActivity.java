package com.example.administrator.parttimejob;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.hyphenate.chat.EMMessage;
import com.hyphenate.easeui.EaseConstant;

import model.UserInfo;
import userManage.implement.GetUserInfo;

/**
 * Created by Administrator on 2017/6/13.
 */

public class PersonActivity extends AppCompatActivity {

    private static final int MSG_SUCCESS = 0;//成功的标识
    private static final int MSG_FAILURE = 1;//失败的标识


    private TextView nickName,sex, school, grade;
    private Button talk, collect;
    private ImageButton back;

    private String personId;
    private UserInfo person;

    private Thread mThread;

    private Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MSG_SUCCESS:
                    nickName.setText(person.getNikename());
                    sex.setText(person.getSex());
                    school.setText(person.getSchool());
                    grade.setText(person.getCommentstar());
                    break;
                case MSG_FAILURE:
                    break;
            }
        }
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person);

        nickName = (TextView)findViewById(R.id.UserName);
        sex = (TextView)findViewById(R.id.UserSex);
        school = (TextView)findViewById(R.id.UserSchool);
        grade = (TextView)findViewById(R.id.UserGrade);
        talk = (Button) findViewById(R.id.button_talk);

        Intent intent = getIntent();
        personId = intent.getExtras().getString("personId");

        if(mThread == null) {
            mThread = new Thread(runnable);
            mThread.start();
        }

        talk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mIntent = new Intent(PersonActivity.this, ChatActivity.class);
                mIntent.putExtra(EaseConstant.EXTRA_USER_ID, personId);
                mIntent.putExtra(EaseConstant.EXTRA_CHAT_TYPE, EMMessage.ChatType.Chat);
                startActivity(mIntent);
            }
        });

    }


    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            GetUserInfo get = new GetUserInfo();
            person = get.getUserInfoById(personId);
            if(person != null) {
                mHandler.obtainMessage(MSG_SUCCESS).sendToTarget();
            }
            else {
                mHandler.obtainMessage(MSG_FAILURE).sendToTarget();
            }
        }
    };

}
