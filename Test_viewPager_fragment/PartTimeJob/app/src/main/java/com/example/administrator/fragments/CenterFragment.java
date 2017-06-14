package com.example.administrator.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.administrator.applications.UserSession;
import com.example.administrator.parttimejob.EditActivity;
import com.example.administrator.parttimejob.LoginActivity;
import com.example.administrator.parttimejob.MainActivity;
import com.example.administrator.parttimejob.MissionListActivity;
import com.example.administrator.parttimejob.R;
import com.example.administrator.parttimejob.TaskListActivity;
import com.hyphenate.EMCallBack;
import com.hyphenate.chat.EMClient;

/**
 * Created by Administrator on 2017/6/13.
 */

public class CenterFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view  = inflater.inflate(R.layout.activity_center, container, false);
        Button log = (Button)view.findViewById(R.id.button_log);
        ImageButton edit = (ImageButton)view.findViewById(R.id.imageButton_edit);
        ImageButton task = (ImageButton)view.findViewById(R.id.imageButton_task);
        ImageButton mission = (ImageButton)view.findViewById(R.id.imageButton_mission);
        final UserSession userSession = (UserSession)getActivity().getApplication();
        if(userSession.getId() == null) {
            log.setText("请先登录");
            log.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // TODO Auto-generated method stub
                    EMClient.getInstance().logout(false);
                    Intent intent = new Intent();
                    intent.setClass(getActivity(), LoginActivity.class);
                    getActivity().startActivity(intent);
                }
            });
        }
        else {

            log.setText("退出" + userSession.getId());
            log.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    EMClient.getInstance().logout(false, new EMCallBack() {
                        @Override
                        public void onSuccess() {
                            Log.i("lc", "退出成功");
                            userSession.setId(null);
                            startActivity(new Intent(getActivity(), MainActivity.class));
                        }

                        @Override
                        public void onError(int i, String s) {
                            Log.e("lc", "退出失败! " + i + ", " + s);
                        }

                        @Override
                        public void onProgress(int i, String s) {

                        }
                    });

                }
            });
        }

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(getActivity(), EditActivity.class);
                getActivity().startActivity(intent);
            }
        });

        task.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(getActivity(), TaskListActivity.class);
                getActivity().startActivity(intent);
            }
        });

        mission.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(getActivity(), MissionListActivity.class);
                getActivity().startActivity(intent);
            }
        });

        return view;
    }

}
