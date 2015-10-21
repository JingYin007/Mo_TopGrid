package com.mo.view;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.mo.mo.R;

public class WelcomeActivity extends Activity {
	AnimationDrawable ad,ad2,ad3;
	ImageView iv_login,iv_login2,iv_login3;
	Animation a,a2,a3;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		final Window window = getWindow();// ��ȡ��ǰ�Ĵ������
		window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);// ������״̬��
		requestWindowFeature(Window.FEATURE_NO_TITLE);// �����˱�����
		setContentView(R.layout.welcome_view);
		iv_login = (ImageView) findViewById(R.id.iv_toMain);// ���ҵ���Ӧ��ImageView
		iv_login2 = (ImageView) findViewById(R.id.iv_toMain2);
		iv_login3 = (ImageView) findViewById(R.id.iv_toMain3);
		
		iv_login.setImageResource(R.drawable.iv_load);// ע��drawable�е�ͼƬ��name������Сд��ĸ
		iv_login2.setImageResource(R.drawable.iv_load);
		iv_login3.setImageResource(R.drawable.iv_load);
		 a = AnimationUtils.loadAnimation(this, R.anim.ha);
		 a2 = AnimationUtils.loadAnimation(this, R.anim.ha2);
		 a3 = AnimationUtils.loadAnimation(this, R.anim.ha3);
		
		iv_login.startAnimation(a);
		iv_login2.startAnimation(a3);
		iv_login3.startAnimation(a2);
		a.setFillAfter(true);
		welcomeUI();
	}

	private void welcomeUI() {
		new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					Thread.sleep(3100);
					Message message = new Message();
					welHandler.sendMessage(message);// ������Ϣ�а���ʲô����������Ҫ����Ϊ���յĺ�������Ҫ�ò���
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}).start();
	}

	Handler welHandler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			welcomeFunction();
		}

	};

	public void welcomeFunction() {
		Intent intent = new Intent();
		intent.setClass(WelcomeActivity.this, LoadActivity.class);
		startActivity(intent);
		WelcomeActivity.this.finish();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
}
