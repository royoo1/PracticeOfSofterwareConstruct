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

import com.example.administrator.adapters.CommentAdapter;
import com.example.administrator.applications.UserSession;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import parttimejob.implement.EvaluateGrade;
import parttimejob.implement.GetApplyPersonId;
import parttimejob.interfac.IGetApplyPersonId;

/**
 * Created by Administrator on 2017/6/13.
 */

public class CommentActivity extends AppCompatActivity {

    private static final int MSG_SUCCESS = 0;//成功的标识
    private static final int MSG_FAILURE = 1;//失败的标识

    private ListView listView;
    private Button sent;

    private Intent intent;
    private Bundle bundle;

    private Map<Integer,String> commentMap;
    List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
    private Thread thread, mThread;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MSG_SUCCESS:
                    listView.setAdapter(new CommentAdapter(CommentActivity.this, list));
                    break;
                case MSG_FAILURE:
                    break;
            }
        }
    };

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MSG_SUCCESS:
                    Toast.makeText(CommentActivity.this, "评分成功！", Toast.LENGTH_SHORT).show();
                    Intent in = new Intent();
                    in.setClass(CommentActivity.this, MainActivity.class);
                    startActivity(in);
                    break;
                case MSG_FAILURE:
                    Toast.makeText(CommentActivity.this, "评分失败！", Toast.LENGTH_SHORT).show();
                    break;
            }
        }

    };

    protected void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);

        intent = getIntent();
        bundle = intent.getExtras();

        listView = (ListView)findViewById(R.id.MyListView_com);
        sent = (Button)findViewById(R.id.button_sent);
        sent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mThread == null) {
                    mThread = new Thread(mRunnable);
                    mThread.start();
                }
            }
        });
        //list=getData();

        commentMap = CommentAdapter.map;

        if(thread == null) {
            thread = new Thread(runnable);
            thread.start();
        }

    }

    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            List<String> commentList;
            final UserSession userSession = (UserSession)getApplication();

            try {
                if (userSession.getId().equals(bundle.getString("bossId"))) {
                    IGetApplyPersonId get = new GetApplyPersonId();
                    commentList = get.getconfirmedPersonId(bundle.getString("jobId"));
                    for (int i = 0; i < commentList.size(); i++) {
                        Map<String, Object> map = new HashMap<String, Object>();
                        map.put("title", commentList.get(i));
                        map.put("info", "评价");
                        list.add(map);
                    }
                } else {
                    Map<String, Object> map = new HashMap<String, Object>();
                    map.put("title", bundle.getString("bossId"));
                    map.put("info", "评价");
                    list.add(map);
                }
                handler.obtainMessage(MSG_SUCCESS).sendToTarget();
            } catch(Exception e) {
                handler.obtainMessage(MSG_FAILURE).sendToTarget();
            }
        }
    };

    Runnable mRunnable = new Runnable() {
        @Override
        public void run() {
            EvaluateGrade eg = new EvaluateGrade();
            Map<String, String> evaluateMap = new HashMap<String, String>();
            Iterator<Map.Entry<Integer, String>> it = commentMap.entrySet().iterator();
            while(it.hasNext()) {
                Map.Entry<Integer, String> entry = it.next();
                if(entry.getValue().equals("好评")) {
                    evaluateMap.put(list.get(entry.getKey()).get("title").toString(), "3");
                } else if(entry.getValue().equals("中评")) {
                    evaluateMap.put(list.get(entry.getKey()).get("title").toString(), "2");
                } else {
                    evaluateMap.put(list.get(entry.getKey()).get("title").toString(), "1");
                }
            }

            try {
                eg.changeScore(evaluateMap);
                mHandler.obtainMessage(MSG_SUCCESS).sendToTarget();
            } catch (Exception e) {
                mHandler.obtainMessage(MSG_FAILURE).sendToTarget();
            }

        }
    };

}
