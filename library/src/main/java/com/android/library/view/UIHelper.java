package com.android.library.view;

import android.app.ProgressDialog;
import android.content.Context;


public class UIHelper {
	/**
	 * 主题加载对话框
	 * 
	 * @ReqParam context
	 * @return
	 */
	public static ProgressDialog newProgressDialog(Context context) {
		ProgressDialog dialog = new ProgressDialog(context); // ,
																// R.style.AppTheme_Dialog
		return dialog;
	}

	/**
	 * 主题加载对话框2
	 * 
	 * @ReqParam target
	 * @ReqParam activity
	 * @ReqParam msg
	 * @return
	 */
	public static CustomProgressDialog newNormalProgressDialog(
			Context activity, String msg) {
		CustomProgressDialog normalProgressDialog = new CustomProgressDialog(
				activity);
		normalProgressDialog.create();
		normalProgressDialog.setMessage(msg);
		return normalProgressDialog;
	}
}
