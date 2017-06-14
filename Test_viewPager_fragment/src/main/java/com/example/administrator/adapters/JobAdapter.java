package com.example.administrator.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.parttimejob.R;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/6/13.
 */

public class JobAdapter extends BaseAdapter {

    private List<Map<String, Object>> data;
    private LayoutInflater layoutInflater;
    private Context context;
    private MyClickListener mListener;

    public JobAdapter(Context context,List<Map<String, Object>> data,MyClickListener listener){
        this.context=context;
        this.data=data;
        this.layoutInflater=LayoutInflater.from(context);
        this.mListener = listener;
    }

    /**
     * 组件集合，对应list.xml中的控件
     * @author Administrator
     */
    public final class ViewHolder{
        public ImageView image;
        public TextView title;
        public Button view;
        public TextView info;
    }
    @Override
    public int getCount() {
        return data.size();
    }
    /**
     * 获得某一位置的数据
     */
    @Override
    public Object getItem(int position) {
        return data.get(position);
    }
    /**
     * 获得唯一标识
     */
    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if(convertView == null){
            holder = new ViewHolder();
            //获得组件，实例化组件

            convertView = layoutInflater.inflate(R.layout.adapter_job, null);
            holder.image = (ImageView)convertView.findViewById(R.id.image);
            holder.title = (TextView)convertView.findViewById(R.id.title);
            holder.view = (Button)convertView.findViewById(R.id.view);
            holder.info = (TextView)convertView.findViewById(R.id.info);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }
        //绑定数据
        holder.image.setBackgroundResource((Integer)data.get(position).get("image"));
        holder.title.setText((String)data.get(position).get("title"));
        holder.info.setText((String)data.get(position).get("info"));
        holder.view.setOnClickListener(mListener);
        holder.view.setTag(position);
        return convertView;
    }

    /**
     * 用于回调的抽象类
     */
    public static abstract class MyClickListener implements View.OnClickListener {
        /**
         * 基类的onClick方法
         */
        @Override
        public void onClick(View v) {
            myOnClick((Integer) v.getTag(), v);
        }
        public abstract void myOnClick(int position, View v);
    }

}
