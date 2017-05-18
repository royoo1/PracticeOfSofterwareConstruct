package com.example.test_viewpager_fragment;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.model.UserSession;

/**
 * Created by sks on 2017/4/10.
 */
public class detail extends Activity implements View.OnClickListener {

    private Button btn;
    private TextView jName, jCash, jNumber, jType, jAddress, jDate, jContent, jPhone, jobCash;
    private String jobId;

     protected void onCreate (Bundle savedInstanceState){
         super.onCreate(savedInstanceState);
         setContentView(R.layout.detail);
         btn = (Button)findViewById(R.id.baoming);
         jName = (TextView)findViewById(R.id.textView_jName);
         jCash = (TextView)findViewById(R.id.textView_jCash);
         jNumber = (TextView)findViewById(R.id.textView_jNumber);
         jType = (TextView)findViewById(R.id.textView_jType);
         jAddress = (TextView)findViewById(R.id.textView_jAddress);
         jDate = (TextView)findViewById(R.id.textView_jDate);
         jContent = (TextView)findViewById(R.id.textView_jContent);
         jPhone = (TextView)findViewById(R.id.textView_jPhone);
         jobCash = (TextView)findViewById(R.id.textView_jobCash);

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
          /* btn.setOnClickListener(new View.OnClickListener() {

             @Override
             public void onClick(View v) {
                 // TODO Auto-generated method stub
                 //Toast.makeText(getActivity(), "123", Toast.LENGTH_SHORT).show();
                 // Activity不能直接跳转到Fragment，需要先跳转到Fragment所附着的Activity中，然后再更改Activity当前显示哪个Fragment。

                 intent.putExtra("fragid",1);
//首先在Activity跳转之前，在Intent中传入一个flag，用来标记跳转到哪一个Fragment。

// 然后根据flag来判断显示哪个Fragment

                 FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
// import android.support.v4.app.FragmentTransaction;

                 transaction.replace(R.id.frame_content, new CenterFragment());

                 transaction.commit();


             }
         });*/


    }


    public void onClick(View v) {
        final UserSession userSession = (UserSession)getApplication();
        Intent mIntent = new Intent();
        mIntent.setClass(this, get.class);
        Bundle mBundle = new Bundle();
        mBundle.putString("userId", userSession.getId());
        mBundle.putString("jobId", jobId);
        mIntent.putExtras(mBundle);
        startActivity(mIntent);
    }

}
