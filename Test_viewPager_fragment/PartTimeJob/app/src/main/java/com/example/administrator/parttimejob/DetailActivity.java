package com.example.administrator.parttimejob;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.applications.UserSession;

/**
 * Created by Administrator on 2017/6/13.
 */

public class DetailActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btn;
    private TextView jName, jCash, jNumber, jType, jAddress, jDate, jContent, jPhone, jobCash;
    private String jobId;

    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        btn = (Button) findViewById(R.id.baoming);
        jName = (TextView) findViewById(R.id.textView_jName);
        jCash = (TextView) findViewById(R.id.textView_jCash);
        jNumber = (TextView) findViewById(R.id.textView_jNumber);
        jType = (TextView) findViewById(R.id.textView_jType);
        jAddress = (TextView) findViewById(R.id.textView_jAddress);
        jDate = (TextView) findViewById(R.id.textView_jDate);
        jContent = (TextView) findViewById(R.id.textView_jContent);
        jPhone = (TextView) findViewById(R.id.textView_jPhone);
        jobCash = (TextView) findViewById(R.id.textView_jobCash);

        Intent intent = getIntent();

        Bundle bundle = intent.getExtras();
        jName.setText(bundle.getString("jobName"));
        jCash.setText(bundle.getString("cash"));
        jNumber.setText(bundle.getString("personNumber"));
        jType.setText(bundle.getString("jobType"));
        jAddress.setText(bundle.getString("address"));
        jDate.setText(bundle.getString("excuteDate"));
        jContent.setText(bundle.getString("jobContent"));
        jPhone.setText(bundle.getString("phone"));
        jobCash.setText(bundle.getString("cash"));

        jobId = bundle.getString("jobId");

        btn.setOnClickListener(this);

        TextView share = (TextView) findViewById(R.id.fenxiang);

        share.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Toast.makeText(getApplicationContext(), "123", Toast.LENGTH_SHORT).show();
                // Activity不能直接跳转到Fragment，需要先跳转到Fragment所附着的Activity中，然后再更改Activity当前显示哪个Fragment。
            }
        });
    }

    public void onClick(View v) {
        final UserSession userSession = (UserSession)getApplication();
        Intent mIntent = new Intent();
        mIntent.setClass(this, GetActivity.class);
        Bundle mBundle = new Bundle();
        mBundle.putString("userId", userSession.getId());
        mBundle.putString("jobId", jobId);
        mIntent.putExtras(mBundle);
        startActivity(mIntent);
    }

}
