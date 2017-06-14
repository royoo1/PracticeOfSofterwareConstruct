package com.example.administrator.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.administrator.parttimejob.R;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/6/13.
 */

public class CommentAdapter extends BaseAdapter {

    private List<Map<String, Object>> data;
    private LayoutInflater layoutInflater;
    private Context context;

    public static final Map<Integer, String> map = new HashMap<Integer, String>();

    public CommentAdapter(Context context, List<Map<String, Object>> data){
        this.context=context;
        this.data=data;
        this.layoutInflater=LayoutInflater.from(context);
    }

    /**
     * 组件集合，对应list.xml中的控件
     * @author Administrator
     */
    public class ViewHolder{
        public TextView title;
        public RadioGroup rg;
        public RadioButton good;
        public RadioButton general;
        public RadioButton bad;
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if(convertView == null){
            holder = new ViewHolder();
            //获得组件，实例化组件

            convertView = layoutInflater.inflate(R.layout.adapter_comment, null);
            holder.rg = (RadioGroup)convertView.findViewById(R.id.optiongroup);
            holder.title = (TextView)convertView.findViewById(R.id.title_com);
            holder.good = (RadioButton)convertView.findViewById(R.id.good);
            holder.general = (RadioButton)convertView.findViewById(R.id.general);
            holder.bad = (RadioButton)convertView.findViewById(R.id.bad);
            holder.info = (TextView)convertView.findViewById(R.id.info_com);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }
        //绑定数据
        holder.title.setText((String)data.get(position).get("title"));
        holder.info.setText((String)data.get(position).get("info"));

        final RadioGroup rgBtn = holder.rg;
        rgBtn.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton rbtn = (RadioButton)group.findViewById(checkedId);
                map.put(position, rbtn.getText().toString());
            }
        });

        return convertView;
    }

}
