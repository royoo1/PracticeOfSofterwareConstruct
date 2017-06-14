package com.example.administrator.fragments;

import android.support.v4.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.administrator.applications.UserSession;
import com.example.administrator.parttimejob.MainActivity;
import com.example.administrator.parttimejob.R;

import java.text.SimpleDateFormat;
import java.util.Date;

import model.JobInfo;
import parttimejob.implement.SendJobInfo;


/**
 * Created by Administrator on 2017/6/13.
 */

public class Fragment1 extends Fragment implements View.OnClickListener {
    private static final int MSG_SUCCESS = 0;//发布成功的标识
    private static final int MSG_FAILURE = 1;//发布失败的标识

    private View view;
    private Button release;
    private EditText wn, tel, content, money, people;
    private Spinner type, wType, location;
    private DatePicker date;
    private TimePicker time;

    private Thread mThread;

    private Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MSG_SUCCESS:
                    Toast.makeText(getActivity().getApplication(), "发布招聘成功！", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent();
                    intent.setClass(getActivity(), MainActivity.class);
                    startActivity(intent);
                    break;
                case MSG_FAILURE:
                    Toast.makeText(getActivity().getApplication(), "发布招聘失败！", Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        view= inflater.inflate(R.layout.activity_layout1, container, false);

        release = (Button)view.findViewById(R.id.button_release);
        wn = (EditText)view.findViewById(R.id.editText_wn);
        tel = (EditText)view.findViewById(R.id.editText_tel);
        type = (Spinner)view.findViewById(R.id.spinner_type);
        content = (EditText)view.findViewById(R.id.add_content);
        money = (EditText)view.findViewById(R.id.editText_money);
        wType = (Spinner)view.findViewById(R.id.spinner_wtype);
        date = (DatePicker)view.findViewById(R.id.datePicker_date);
        time = (TimePicker)view.findViewById(R.id.timePicker_time);
        location = (Spinner)view.findViewById(R.id.spinner_location);
        people = (EditText)view.findViewById(R.id.editText_people);

        release.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        setJobInfo();
    }

    private void setJobInfo() {
        if(mThread == null) {
            mThread = new Thread(runnable);
            mThread.start();
        }
    }

    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            SendJobInfo sender = new SendJobInfo();
            final UserSession userSession = (UserSession)getActivity().getApplication();
            JobInfo jobInfo = new JobInfo();
            jobInfo.setUserid(userSession.getId());
            jobInfo.setJobname(wn.getText().toString());
            jobInfo.setPhone(tel.getText().toString());
            jobInfo.setJobtype(type.getSelectedItem().toString());
            jobInfo.setJobcontent(content.getText().toString());
            jobInfo.setCash(money.getText().toString());
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            jobInfo.setSexrequire("男");
            jobInfo.setDeadline("2017-4-24 12:00:00");
            jobInfo.setPushdate(df.format(new Date()));
            jobInfo.setExcutedate(date.getYear() + "-" + date.getMonth() + "-" + date.getDayOfMonth() + " " + time.getCurrentHour() + ":" + time.getCurrentMinute() + ":00");
            jobInfo.setAdress(location.getSelectedItem().toString());
            jobInfo.setPersonnumber(people.getText().toString());
            try {
                sender.sendJobInfo(jobInfo);
                mHandler.obtainMessage(MSG_SUCCESS).sendToTarget();
            } catch (Exception e) {
                mHandler.obtainMessage(MSG_FAILURE).sendToTarget();
            }
        }
    };

}
