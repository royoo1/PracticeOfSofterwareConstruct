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
import android.widget.Toast;

import com.example.administrator.applications.UserSession;
import com.hyphenate.EMCallBack;
import com.hyphenate.chat.EMClient;

import userManage.implement.ConfirmLogin;
import userManage.interfac.IConfirmLogin;

/**
 * Created by Administrator on 2017/6/13.
 */

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private static final int MSG_SUCCESS = 0;//登陆成功的标识
    private static final int MSG_FAILURE = 1;//登陆失败的标识

    private Button login, register;
    private EditText lId, lPwd;

    private String read;

    private Thread mThread;

    private Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MSG_SUCCESS:
                    Toast.makeText(getApplication(), "登陆成功", Toast.LENGTH_SHORT).show();
                    final UserSession userSession = (UserSession)getApplication();
                    userSession.setId(lId.getText().toString());
                    Intent i = new Intent();
                    i.setClass(LoginActivity.this, MainActivity.class);
                    LoginActivity.this.startActivity(i);
                    break;
                case MSG_FAILURE:
                    Toast.makeText(getApplication(), (String)msg.obj, Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        login = (Button)findViewById(R.id.button_login);
        register = (Button)findViewById(R.id.button_register);
        lId = (EditText)findViewById(R.id.editText_lId);
        lPwd = (EditText)findViewById(R.id.editText_lPwd);
        login.setOnClickListener(this);
        register.setOnClickListener(this);
    }

    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            IConfirmLogin confirm = new ConfirmLogin();
            read = confirm.confirmLogin(lId.getText().toString(), lPwd.getText().toString());
            if(read.equals("success")) {
                mHandler.obtainMessage(MSG_SUCCESS, read).sendToTarget();           //登陆成功
            }
            else {
                mHandler.obtainMessage(MSG_FAILURE, read).sendToTarget();           //登陆失败
            }
        }
    };

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.button_login:
                EMClient.getInstance().login(lId.getText().toString().trim(), lPwd.getText().toString().trim(), new EMCallBack() {
                    @Override
                    public void onSuccess() {
                        Log.i("lc", "登陆成功! ");
                        if(mThread == null) {
                            mThread = new Thread(runnable);
                            mThread.start();
                        }
                    }

                    @Override
                    public void onError(int i, String s) {
                        Log.e("lc", "登陆失败! " + i + ", " + s);
                    }

                    @Override
                    public void onProgress(int i, String s) {

                    }
                });

                break;

            case R.id.button_register:
                Intent intent = new Intent();
                intent.setClass(LoginActivity.this, RegisterActivity.class);
                LoginActivity.this.startActivity(intent);
                break;
        }
    }

}
