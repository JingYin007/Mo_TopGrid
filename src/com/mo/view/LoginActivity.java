package com.mo.view;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.mo.mo.R;

public class LoginActivity extends Activity implements OnClickListener {

	EditText et_name, et_password;
	Button btn_register, btn_login;
	ImageView iv_back;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.login_view);
		initView();

	}

	private void initView() {
		et_name = (EditText) findViewById(R.id.et_name);
		et_password = (EditText) findViewById(R.id.et_password);
		btn_login = (Button) findViewById(R.id.btn_login);
		btn_register = (Button) findViewById(R.id.btn_register);
		iv_back = (ImageView) findViewById(R.id.iv_back);

		btn_register.setOnClickListener(this);
		btn_login.setOnClickListener(this);
		iv_back.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_register:
			Toast.makeText(this, "Welcome!", Toast.LENGTH_SHORT).show();
			break;
		case R.id.btn_login:
			Toast.makeText(this,
					et_name.getText() + "   " + et_password.getText(),
					Toast.LENGTH_SHORT).show();
			break;
		case R.id.iv_back:
			finish();
			break;
		default:
			break;
		}
	}

}
