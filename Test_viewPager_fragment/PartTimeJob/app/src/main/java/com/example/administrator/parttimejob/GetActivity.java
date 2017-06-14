package com.example.administrator.parttimejob;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import model.ApplyInfo;
import parttimejob.implement.ApplyJob;

/**
 * Created by Administrator on 2017/6/13.
 */

public class GetActivity extends AppCompatActivity  implements View.OnClickListener {

    private static final int MSG_SUCCESS = 0;//成功的标识
    private static final int MSG_FAILURE = 1;//失败的标识

    private Button get;
    private EditText info;

    private Thread mThread;

    private Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MSG_SUCCESS:
                    Toast.makeText(getApplication(), "申请成功！", Toast.LENGTH_SHORT).show();
                    Intent mIntent = new Intent();
                    mIntent.setClass(GetActivity.this, MainActivity.class);
                    startActivity(mIntent);
                    break;
                case MSG_FAILURE:
                    Toast.makeText(getApplication(), "申请失败！", Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };

    public void onClick(View view) {
        if(mThread == null) {
            mThread = new Thread(runnable);
            mThread.start();
        }
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get);

        Button btn = (Button) findViewById(R.id.backtodetail);
        get = (Button) findViewById(R.id.ButtonGet);
        info = (EditText) findViewById(R.id.add_info);
        get.setOnClickListener(this);
    }

    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            Intent intent = getIntent();
            Bundle bundle = intent.getExtras();
            String userId = bundle.getString("userId");
            String jobId = bundle.getString("jobId");

            ApplyInfo apply = new ApplyInfo();
            apply.setJobid(jobId);
            apply.setWorkerid(userId);
            apply.setContent(info.getText().toString());

            ApplyJob applyJob = new ApplyJob();
            try {
                applyJob.sendApply(apply);
                mHandler.obtainMessage(MSG_SUCCESS).sendToTarget();
            } catch (Exception e) {
                mHandler.obtainMessage(MSG_FAILURE).sendToTarget();
            }
        }
    };

}
