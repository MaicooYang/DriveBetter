package com.loganisawesome.drivebetter.utilities.helpers;

import android.content.Context;
import android.widget.Toast;

public class ToastHelper
{
	public static void showShortMessage(Context context, String message)
	{
		Toast toast = Toast.makeText(context, message, Toast.LENGTH_SHORT);
		toast.show();
	}
	
	public static void showLongMessage(Context context, String message)
	{
		Toast toast = Toast.makeText(context, message, Toast.LENGTH_LONG);
		toast.show();
	}
}
