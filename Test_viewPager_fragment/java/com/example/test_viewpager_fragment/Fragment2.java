package com.example.test_viewpager_fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parttimejob.implement.GetJobInfo;
import parttimejob.interfac.IGetJobInfo;
import model.JobInfo;


public class Fragment2 extends Fragment implements AdapterView.OnItemClickListener {


	private static final int MSG_SUCCESS = 0;//查看成功的标识
	private static final int MSG_FAILURE = 1;//查看失败的标识

	private List<JobInfo> jobList;

	private ListView listView;
	List<Map<String, Object>> list=new ArrayList<Map<String,Object>>();

	private Thread mThread;

	private Handler mHandler = new Handler() {
		public void handleMessage(Message msg) {
			switch(msg.what) {
				case MSG_SUCCESS:
					List<JobInfo> jobList = (List)msg.obj;
					for(int i = 0; i < jobList.size(); i++) {
						Map<String, Object> map=new HashMap<String, Object>();
						map.put("image", R.drawable.ic_launcher);
						map.put("title", jobList.get(i).getJobname());
						map.put("info", "￥" + jobList.get(i).getCash());
						list.add(map);
					}
					listView.setAdapter(new ListViewAdapter(getActivity(), list,mListener));
					break;
				case MSG_FAILURE:
					break;
			}
		}
	};

	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view=inflater.inflate(R.layout.layout2, container, false);

		/*listView=(ListView)view.findViewById(R.id.MyListView);
		listView.setAdapter(new ListViewAdapter(titles,texts,resIds));*/
		listView = (ListView)view.findViewById(R.id.MyListView);
		if(mThread == null) {
			mThread = new Thread(runnable);
			mThread.start();
		}
		//List<Map<String, Object>> list=getData();
		//  listView.setAdapter(new ListViewAdapter(getActivity(), list,mListener));
		//listView.setOnItemClickListener(getActivity());


		/*listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
									long arg3) {
				// TODO Auto-generated method stub

					startActivity(new Intent(getActivity(), detail.class));

			}

		});*/

		return view;
	}

	/*
	public List<Map<String, Object>> getData(){

		for (int i = 0; i < 10; i++) {
			Map<String, Object> map=new HashMap<String, Object>();
			map.put("image", R.drawable.ic_launcher);
			map.put("title", "这是一个标题"+i);
			map.put("info", "这是一个详细信息" + i);
			list.add(map);
		}
		return list;
	}

*/
	//响应item点击事件
	@Override
	public void onItemClick(AdapterView<?> arg0, View v, int position, long id) {
		Toast.makeText(getActivity(), "listview的item被点击了！，点击的位置是-->" + position,
				Toast.LENGTH_SHORT).show();
	}

	/**
	 * 实现类，响应按钮点击事件
	 */
	private ListViewAdapter.MyClickListener mListener = new ListViewAdapter.MyClickListener() {
		@Override
		public void myOnClick(int position, View v) {
			Toast.makeText(
					getActivity(),
					"listview的内部的按钮被点击了！，位置是-->" + position + ",内容是-->"
							+ list.get(position), Toast.LENGTH_SHORT)
					.show();

			Intent intent = new Intent();
			intent.setClass(getActivity(), detail.class);
			Bundle bundle = new Bundle();

			bundle.putString("jobId", jobList.get(position).getJobid());
			bundle.putString("jobName", jobList.get(position).getJobname());
			bundle.putString("cash", jobList.get(position).getCash());
			bundle.putString("personNumber", jobList.get(position).getPersonnumber());
			bundle.putString("jobType", jobList.get(position).getJobtype());
			bundle.putString("address", jobList.get(position).getAdress());
			bundle.putString("excuteDate", jobList.get(position).getExcutedate());
			bundle.putString("jobContent", jobList.get(position).getJobcontent());
			bundle.putString("phone", jobList.get(position).getPhone());

			intent.putExtras(bundle);

			startActivity(intent);
		}
	};


	Runnable runnable = new Runnable() {
		@Override
		public void run() {
			IGetJobInfo getJobInfo = new GetJobInfo();
			try {
				jobList = getJobInfo.getBytype("pushtime", 1);
				mHandler.obtainMessage(MSG_SUCCESS, jobList).sendToTarget();
			} catch(Exception e) {
				mHandler.obtainMessage(MSG_FAILURE, jobList).sendToTarget();
			}
		}
	};


}
