package com.mo.view;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.Window;
import android.webkit.WebSettings;
import android.webkit.WebSettings.LayoutAlgorithm;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.mo.mo.R;
import com.mo.util.NetUtil;

public class LoadActivity extends Activity {
	AnimationDrawable ad;
	Animation a;
	Boolean isExit;
	ImageView iv_toLogin, iv_loading_anim;
	WebView webview_load;
	LinearLayout ll_loadingView;
	String loadUrl = "http://android.myapp.com/myapp/category.htm?orgame=1";
	/**
	 * handle 用来处理加载动画的进一步显示判断
	 */
	Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case 1:
				if (ll_loadingView.getVisibility() == View.VISIBLE) {
					ll_loadingView.setVisibility(View.GONE);
				}
				break;
			default:
				break;
			}
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.load_view);
		initView();
		refreshNet();
	}

	/**
	 * 刷新网络，判断是否联网并进行加载
	 */
	private void refreshNet() {
		new Thread() {

			@Override
			public void run() {

				if (NetUtil.isNetOk(LoadActivity.this)) {
					try {
						if (ll_loadingView.getVisibility() == View.GONE) {
							ll_loadingView.setVisibility(View.VISIBLE);
						}

						Message msg = handler.obtainMessage();
						msg.what = 1;
						handler.sendMessage(msg);

					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} else {
					ll_loadingView.setVisibility(View.VISIBLE);
				}
			}

		}.start();
	}

	/**
	 * 初始化
	 */
	private void initView() {
		// TODO Auto-generated method stub
		webview_load = (WebView) findViewById(R.id.webview_load);
		iv_toLogin = (ImageView) findViewById(R.id.iv_toLogin);
		ll_loadingView = (LinearLayout) findViewById(R.id.loading);
		iv_loading_anim = (ImageView) findViewById(R.id.iv_loading_anim);

		iv_loading_anim.setImageResource(R.anim.frame);
		ad = (AnimationDrawable) iv_loading_anim.getDrawable();
		ad.start();
		WebSettings webSettings = webview_load.getSettings();

		// 设置WebView属性，能够执行Javascript脚本
		webSettings.setJavaScriptEnabled(true);
		// 设置可以支持缩放
		webview_load.getSettings().setSupportZoom(true);
		// 设置默认缩放方式尺寸是far
		webview_load.getSettings().setDefaultZoom(WebSettings.ZoomDensity.FAR);
		// 设置出现缩放工具
		webview_load.getSettings().setBuiltInZoomControls(true);

		// 设置可以访问文件
		webSettings.setAllowFileAccess(true);
		// 设置支持缩放
		webSettings.setBuiltInZoomControls(true);
		// webSettings.setUseWideViewPort(true);
		webSettings.setLoadWithOverviewMode(true);

		webview_load.getSettings().setLayoutAlgorithm(
				LayoutAlgorithm.SINGLE_COLUMN);

		// 加载需要显示的网页
		webview_load.loadUrl(loadUrl);

		// 设置Web视图
		webview_load.setWebViewClient(new webViewClient());
		iv_toLogin.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				startActivity(new Intent(LoadActivity.this, LoginActivity.class));
			}
		});

		ll_loadingView.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				webview_load.loadUrl(loadUrl);
				refreshNet();
			}
		});
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// keyCode == KeyEvent.KEYCODE_BACK) && webview_load.canGoBack()
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			// goBack()表示返回WebView的上一页面
			webview_load.goBack();
			return true;
		} else if (keyCode == KeyEvent.KEYCODE_MENU) {
			return true;
		} else if (keyCode == 27) {
			// 屏蔽ESC按键监听
			return true;
		} else if (keyCode == KeyEvent.KEYCODE_HOME) {
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}

	private class webViewClient extends WebViewClient {
		public boolean shouldOverrideUrlLoading(WebView view, String url) {
			view.loadUrl(url);
			return true;
		}

		@Override
		public void onPageFinished(WebView view, String url) {
			super.onPageFinished(view, url);
		}
	}
}
