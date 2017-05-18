package com.example.test_viewpager_fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.model.UserSession;

import java.util.HashMap;
import java.util.Map;

import userManage.implement.ModifyUserInfo;
import userManage.interfac.IModifyUserInfo;

/**
 * Created by Administrator on 2017/5/5.
 */
public class EditAcitivity extends Activity implements View.OnClickListener {

    private static final int MSG_SUCCESS = 0;//修改成功的标识
    private static final int MSG_FAILURE = 1;//修改失败的标识

    private EditText nickName, school, tel;
    private RadioGroup sex;
    private Button commit;

    private Thread mThread;

    private Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            switch(msg.what) {
                case MSG_SUCCESS:
                    Toast.makeText(getApplication(), "修改成功！", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent();
                    intent.setClass(EditAcitivity.this, MainActivity.class);
                    EditAcitivity.this.startActivity(intent);
                    break;
                case MSG_FAILURE:
                    Toast.makeText(getApplication(), "修改失败！", Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit);

        nickName = (EditText)findViewById(R.id.editText_aNickName);
        school = (EditText)findViewById(R.id.editText_aSchool);
        tel = (EditText)findViewById(R.id.editText_aTel);
        sex = (RadioGroup)findViewById(R.id.radioGroup_aSex);
        commit = (Button)findViewById(R.id.button_alterCommit);

        commit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(mThread == null) {
            mThread = new Thread(runnable);
            mThread.start();
        }
    }

    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            Map<String, String> modifyInfo = new HashMap<String, String>();
            modifyInfo.put("nikename", nickName.getText().toString());
            modifyInfo.put("school", school.getText().toString());
            modifyInfo.put("phonenumber", tel.getText().toString());
            if(sex.getCheckedRadioButtonId() == R.id.radioButton_aMale) {
                modifyInfo.put("sex", "男");
            }
            else {
                modifyInfo.put("sex", "女");
            }

            final UserSession userSession = (UserSession)getApplication();

            IModifyUserInfo modifyUserInfo = new ModifyUserInfo();
            if(modifyUserInfo.changeNikename(modifyInfo, userSession.getId()).equals("success")) {
                mHandler.obtainMessage(MSG_SUCCESS).sendToTarget();
            }
            else {
                mHandler.obtainMessage(MSG_FAILURE).sendToTarget();
            }
        }
    };
}
