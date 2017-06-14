package com.example.administrator.parttimejob;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.administrator.applications.UserSession;

import parttimejob.implement.Jobfinish;

/**
 * Created by Administrator on 2017/6/13.
 */

public class Invit2Activity extends AppCompatActivity implements View.OnClickListener {
    private TextView jName, jPhone, jAddress, jTime, jContent;
    private Button jChoose, jEnd;

    private Intent intent;
    private Bundle bundle;

    private String jobId;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invit2);
        jName = (TextView)findViewById(R.id.textView_jName2);
        jPhone = (TextView)findViewById(R.id.textView_jPhone2);
        jAddress = (TextView)findViewById(R.id.textView_jAddress2);
        jTime = (TextView)findViewById(R.id.textView_jTime2);
        jContent = (TextView)findViewById(R.id.textView_jContent2);
        jChoose = (Button)findViewById(R.id.button_jChoose);
        jEnd = (Button)findViewById(R.id.button_jEnd);
        jChoose.setOnClickListener(this);
        jEnd.setOnClickListener(this);

        intent = getIntent();
        bundle = intent.getExtras();
        jName.setText(bundle.getString("jobName"));
        jPhone.setText(bundle.getString("phone"));
        jAddress.setText(bundle.getString("address"));
        jTime.setText(bundle.getString("excuteDate"));
        jContent.setText(bundle.getString("jobContent"));
        jobId = bundle.getString("jobId");
    }

    @Override
    public void onClick(View v) {
        Intent mIntent = new Intent();
        Bundle mBundle = new Bundle();
        switch (v.getId()) {
            case R.id.button_jChoose:
                mIntent.setClass(Invit2Activity.this, ChooseActivity.class);
                mBundle.putString("jobId", jobId);
                mIntent.putExtras(mBundle);
                break;
            case R.id.button_jEnd:
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Jobfinish jf = new Jobfinish();
                        jf.finishJob(jobId);
                    }
                }).start();
                final UserSession userSession = (UserSession)getApplication();
                bundle.putString("bossId", userSession.getId());
                mIntent.setClass(Invit2Activity.this, Invit3Activity.class);
                mIntent.putExtras(bundle);
                break;
        }
        startActivity(mIntent);
    }
}
