package com.example.administrator.parttimejob;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.hyphenate.chat.EMClient;
import com.hyphenate.exceptions.HyphenateException;

import model.UserInfo;
import userManage.implement.SetUserInfo;
import userManage.interfac.ISetUserInfo;

/**
 * Created by Administrator on 2017/6/13.
 */

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    private static final int MSG_SUCCESS = 0;//注册成功的标识
    private static final int MSG_FAILURE = 1;//注册失败的标识
    private EditText rId, rName, rNickName, rSchool, rTel, rPwd;
    private RadioGroup rSex;
    private Button register;

    private Thread mThread;

    private Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            switch(msg.what) {
                case MSG_SUCCESS:
                    Toast.makeText(getApplication(), "注册成功！", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent();
                    intent.setClass(RegisterActivity.this, LoginActivity.class);
                    RegisterActivity.this.startActivity(intent);
                    break;
                case MSG_FAILURE:
                    Toast.makeText(getApplication(), "注册失败！", Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };

    @Override
    public void onClick(View v) {

        if(mThread == null) {
            mThread = new Thread(runnable);
            mThread.start();
        }

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        rId = (EditText) findViewById(R.id.editText_rId);
        rName = (EditText)findViewById(R.id.editText_rName);
        rNickName = (EditText) findViewById(R.id.editText_rNickName);
        rSchool = (EditText)findViewById(R.id.editText_rSchool);
        rTel = (EditText)findViewById(R.id.editText_rTel);
        rSex = (RadioGroup)findViewById(R.id.radioGroup_rSex);
        rPwd = (EditText)findViewById(R.id.editText_rPwd);
        register = (Button)findViewById(R.id.button_registerCommit);
        register.setOnClickListener(this);
    }

    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            UserInfo user = new UserInfo();
            //用户对象实体化
            user.setUserid(rId.getText().toString());
            user.setUsername(rName.getText().toString());
            user.setNikename(rNickName.getText().toString());
            user.setSchool(rSchool.getText().toString());
            user.setPhonenumber(rTel.getText().toString());
            user.setPassword(rPwd.getText().toString());
            if(rSex.getCheckedRadioButtonId() == R.id.radioButton_male) {
                user.setSex("男");
            }
            else {
                user.setSex("女");
            }

            ISetUserInfo setter = new SetUserInfo();
            try {
                setter.sendInfo(user);

                try {
                    EMClient.getInstance().createAccount(rId.getText().toString().trim(), rPwd.getText().toString().trim());
                    Log.i("lc", "注册成功! ");
                    mHandler.obtainMessage(MSG_SUCCESS).sendToTarget();
                } catch (HyphenateException he) {
                    he.printStackTrace();
                    Log.e("lc", "注册失败! " +  he.getErrorCode() + ", " + he.getMessage());
                    mHandler.obtainMessage(MSG_FAILURE).sendToTarget();
                }

            } catch(Exception e) {
                e.printStackTrace();
                mHandler.obtainMessage(MSG_FAILURE).sendToTarget();
            }
        }
    };

}
