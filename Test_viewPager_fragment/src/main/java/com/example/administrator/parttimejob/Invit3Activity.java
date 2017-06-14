package com.example.administrator.parttimejob;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


/**
 * Created by Administrator on 2017/6/13.
 */

public class Invit3Activity extends AppCompatActivity implements View.OnClickListener {

    private TextView jName, jPhone, jAddress, jTime, jContent;
    private Button jEvaluate;

    private Intent intent;
    private Bundle bundle;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invit3);
        jName = (TextView)findViewById(R.id.textView_jName3);
        jPhone = (TextView)findViewById(R.id.textView_jPhone3);
        jAddress = (TextView)findViewById(R.id.textView_jAddress3);
        jTime = (TextView)findViewById(R.id.textView_jTime3);
        jContent = (TextView)findViewById(R.id.textView_jContent3);
        jEvaluate = (Button)findViewById(R.id.button_jEvaluate);
        jEvaluate.setOnClickListener(this);

        intent = getIntent();
        bundle = intent.getExtras();
        jName.setText(bundle.getString("jobName"));
        jPhone.setText(bundle.getString("phone"));
        jAddress.setText(bundle.getString("address"));
        jTime.setText(bundle.getString("excuteDate"));
        jContent.setText(bundle.getString("jobContent"));
    }

    @Override
    public void onClick(View v) {
        Intent mIntent = new Intent();
        mIntent.putExtras(bundle);
        mIntent.setClass(Invit3Activity.this, CommentActivity.class);
        startActivity(mIntent);

    }


}
