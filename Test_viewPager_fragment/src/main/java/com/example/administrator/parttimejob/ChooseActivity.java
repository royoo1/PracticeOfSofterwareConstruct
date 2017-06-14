package com.example.administrator.parttimejob;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.administrator.adapters.ChooseAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.ApplyInfo;
import parttimejob.implement.GetApplyInfo;
import parttimejob.implement.SetApplyPersonId;
import parttimejob.interfac.IGetApplyinfo;

/**
 * Created by Administrator on 2017/6/13.
 */

public class ChooseActivity extends AppCompatActivity implements View.OnClickListener {

    private static final int MSG_SUCCESS = 0;//修改成功的标识
    private static final int MSG_FAILURE = 1;//修改失败的标识

    private ListView listView;
    private Button commit;
    private ChooseAdapter listViewAdapter;
    private List<Map<String, Object>> listItems;

    private String jobId;
    private List<ApplyInfo> employeeList = null;

    private Thread mThread;
    private Thread thread;

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MSG_SUCCESS:
                    listViewAdapter = new ChooseAdapter(ChooseActivity.this, listItems, mListener);
                    listView.setAdapter(listViewAdapter);
                    break;
                case MSG_FAILURE:
                    break;
            }
        }
    };

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MSG_SUCCESS:
                    Toast.makeText(ChooseActivity.this, "雇佣成功！ ", Toast.LENGTH_SHORT).show();
                    Intent mIntent = new Intent();
                    mIntent.setClass(ChooseActivity.this, MainActivity.class);
                    startActivity(mIntent);
                    break;
                case MSG_FAILURE:
                    Toast.makeText(ChooseActivity.this, "雇佣失败！ ", Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose);

        listView = (ListView)findViewById(R.id.list_goods);
        commit = (Button)findViewById(R.id.button_chooseCommit);

        commit.setOnClickListener(this);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        jobId = bundle.getString("jobId");

        if(mThread == null) {
            mThread = new Thread(mRunnable);
            mThread.start();
        }
    }

    Runnable mRunnable = new Runnable() {
        @Override
        public void run() {
            IGetApplyinfo get = new GetApplyInfo();
            int iJobId = Integer.parseInt(jobId);
            employeeList = get.getApplyInfoByJobid(iJobId);

            listItems = new ArrayList<Map<String, Object>>();

            for(int i = 0; i < employeeList.size(); i++) {
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("image", R.drawable.luna);
                map.put("title", employeeList.get(i).getWorkerid());
                map.put("info", employeeList.get(i).getContent());
                listItems.add(map);
            }

            mHandler.obtainMessage(MSG_SUCCESS).sendToTarget();
        }
    };

    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            List chooseList = new ArrayList();
            for(int i = 0; i < employeeList.size(); i++) {
                if(listViewAdapter.hasChecked(i)) {
                    chooseList.add(employeeList.get(i).getWorkerid());
                }
            }
            SetApplyPersonId set = new SetApplyPersonId();
            try {
                set.confirmApplyPersonId(jobId, chooseList);
                handler.obtainMessage(MSG_SUCCESS).sendToTarget();
            } catch (Exception e) {
                handler.obtainMessage(MSG_FAILURE).sendToTarget();
            }

        }
    };


    /**
     * 实现类，响应按钮点击事件
     */
    private ChooseAdapter.MyClickListener mListener = new ChooseAdapter.MyClickListener() {
        @Override
        public void myOnClick(int position, View v) {

            Intent intent = new Intent();
            intent.setClass(ChooseActivity.this, PersonActivity.class);
            Bundle bundle = new Bundle();
            bundle.putString("personId", employeeList.get(position).getWorkerid());
            intent.putExtras(bundle);

            startActivity(intent);
        }
    };

    @Override
    public void onClick(View v) {
        if(thread == null) {
            thread = new Thread(runnable);
            thread.start();
        }
    }

}
