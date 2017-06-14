package com.example.administrator.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.parttimejob.R;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/6/13.
 */

public class ChooseAdapter extends BaseAdapter {

    private Context context;                        //运行上下文
    private List<Map<String, Object>> listItems;    //商品信息集合
    private LayoutInflater listContainer;           //视图容器
    private boolean[] hasChecked;                   //记录选中状态
    private MyClickListener mListener;

    public final class ListItemView {                //自定义控件集合
        public ImageView image;
        public TextView title;
        public TextView info;
        public CheckBox check;
        public Button detail;
    }

    public ChooseAdapter(Context context, List<Map<String, Object>> listItems, MyClickListener listener) {
        this.context = context;
        listContainer = LayoutInflater.from(context);   //创建视图容器并设置上下文
        this.listItems = listItems;
        hasChecked = new boolean[getCount()];
        this.mListener = listener;
    }

    public int getCount() {
        // TODO Auto-generated method stub
        return listItems.size();
    }

    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return listItems.get(position);
    }

    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    /**
     * 记录勾选了哪个物品
     * @param checkedID 选中的物品序号
     */
    private void checkedChange(int checkedID) {
        hasChecked[checkedID] = !hasChecked[checkedID];
    }

    /**
     * 判断物品是否选择
     * @param checkedID 物品序号
     * @return 返回是否选中状态
     */
    public boolean hasChecked(int checkedID) {
        return hasChecked[checkedID];
    }

    /**
     * ListView Item设置
     */
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub

        final int selectID = position;
        //自定义视图
        ListItemView  listItemView = null;
        if (convertView == null) {
            listItemView = new ListItemView();
            //获取list_item布局文件的视图
            convertView = listContainer.inflate(R.layout.adapter_choose, null);
            //获取控件对象
            listItemView.image = (ImageView)convertView.findViewById(R.id.imageItem);
            listItemView.title = (TextView)convertView.findViewById(R.id.titleItem);
            listItemView.info = (TextView)convertView.findViewById(R.id.infoItem);
            listItemView.detail= (Button)convertView.findViewById(R.id.detailItem);
            listItemView.check = (CheckBox)convertView.findViewById(R.id.checkItem);
            //设置控件集到convertView
            convertView.setTag(listItemView);
        }else {
            listItemView = (ListItemView)convertView.getTag();
        }

        //设置文字和图片
        listItemView.image.setBackgroundResource((Integer) listItems.get(
                position).get("image"));
        listItemView.title.setText((String) listItems.get(position)
                .get("title"));
        listItemView.info.setText((String) listItems.get(position).get("info"));
        listItemView.detail.setText("详细信息");
        listItemView.detail.setOnClickListener(mListener);
        listItemView.detail.setTag(position);
        // 注册多选框状态事件处理
        listItemView.check
                .setOnCheckedChangeListener(new CheckBox.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView,
                                                 boolean isChecked) {
                        //记录物品选中状态
                        checkedChange(selectID);
                    }
                });



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
