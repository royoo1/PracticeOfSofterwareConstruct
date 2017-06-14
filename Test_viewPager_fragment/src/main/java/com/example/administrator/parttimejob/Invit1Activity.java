package com.example.administrator.parttimejob;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

/**
 * Created by Administrator on 2017/6/13.
 */

public class Invit1Activity extends AppCompatActivity {

    private TextView jName, jPhone, jAddress, jTime, jContent, jState;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invit1);
        jName = (TextView)findViewById(R.id.textView_jName1);
        jPhone = (TextView)findViewById(R.id.textView_jPhone1);
        jAddress = (TextView)findViewById(R.id.textView_jAddress1);
        jTime = (TextView)findViewById(R.id.textView_jTime1);
        jContent = (TextView)findViewById(R.id.textView_jContent1);
        jState = (TextView)findViewById(R.id.textView_jState1);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();


        jName.setText(bundle.getString("jobName"));
        jPhone.setText(bundle.getString("phone"));
        jAddress.setText(bundle.getString("address"));
        jTime.setText(bundle.getString("excuteDate"));
        jContent.setText(bundle.getString("jobContent"));

        if(bundle.getString("ifGet").equals("0")) {
            jState.setText("审核中");
        } else if(bundle.getString("ifGet").equals("1")) {
            jState.setText("已录用");
        } else {
            jState.setText("未录用");
        }
    }

}
