package com.example.administrator.parttimejob;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;

import com.example.administrator.adapters.JobAdapter;
import com.example.administrator.applications.UserSession;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.MissionInfo;
import parttimejob.implement.GetMissionListByPersonId;

/**
 * Created by Administrator on 2017/6/13.
 */

public class MissionListActivity extends AppCompatActivity {

    private static final int MSG_SUCCESS = 0;//登陆成功的标识
    private static final int MSG_FAILURE = 1;//登陆失败的标识

    private ListView taskList;

    private List<MissionInfo> missionList;
    private List<Map<String, Object>> list=new ArrayList<Map<String,Object>>();


    private Thread mThread;

    private Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MSG_SUCCESS:
                    for(int i = 0; i < missionList.size(); i++) {
                        Map<String, Object> map=new HashMap<String, Object>();
                        map.put("image", R.mipmap.ic_launcher);
                        map.put("title", missionList.get(i).getJobname());
                        map.put("info", "￥" + missionList.get(i).getCash());
                        list.add(map);
                    }
                    taskList.setAdapter(new JobAdapter(MissionListActivity.this, list, mListener));
                    break;
                case MSG_FAILURE:
                    break;
            }
        }
    };

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mission);

        missionList = new ArrayList<MissionInfo>();

        taskList = (ListView)findViewById(R.id.listView_missionList);
        if(mThread == null) {
            mThread = new Thread(runnable);
            mThread.start();
        }
    }

    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            GetMissionListByPersonId get = new GetMissionListByPersonId();

            final UserSession userSession = (UserSession)getApplication();
            missionList = get.getMissionListByPersonId(userSession.getId());
            if(missionList.size() != 0) {
                mHandler.obtainMessage(MSG_SUCCESS).sendToTarget();
            }
            else {
                mHandler.obtainMessage(MSG_FAILURE).sendToTarget();
            }
        }
    };

    private JobAdapter.MyClickListener mListener = new JobAdapter.MyClickListener() {
        @Override
        public void myOnClick(int position, View v) {

            Intent intent = new Intent();
            Bundle bundle = new Bundle();

            final UserSession userSession = (UserSession)getApplication();

            bundle.putString("bossId", missionList.get(position).getUserid());
            bundle.putString("jobId", missionList.get(position).getJobid());
            bundle.putString("jobName", missionList.get(position).getJobname());
            bundle.putString("phone", missionList.get(position).getPhone());
            bundle.putString("excuteDate", missionList.get(position).getExcutedate());
            bundle.putString("address", missionList.get(position).getAdress());
            bundle.putString("jobContent", missionList.get(position).getJobcontent());
            bundle.putString("ifGet", missionList.get(position).getIfget());

            intent.putExtras(bundle);
            intent.setClass(MissionListActivity.this, Invit1Activity.class);
            startActivity(intent);
        }
    };

}
