package com.loganisawesome.drivebetter.utilities.helpers;

import java.util.List;

import com.loganisawesome.drivebetter.utilities.interfaces.ClearFocusInterface;

import android.content.Context;
import android.graphics.Rect;
import android.os.IBinder;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.inputmethod.InputMethodManager;

public class ClearFocusTouchListener implements OnTouchListener
{
	private ClearFocusInterface clearFocusListener;
	private Context context;
	
	public ClearFocusTouchListener(Context context, ClearFocusInterface listener)
	{
		this.clearFocusListener = listener;
		this.context = context;
	}

	@Override
	public boolean onTouch(View v, MotionEvent event)
	{
		if (event.getAction() == MotionEvent.ACTION_DOWN && this.clearFocusListener != null)
		{
			int x = (int) event.getRawX();
			int y = (int) event.getRawY();
			
			IBinder windowToken = v.getWindowToken();
			ClearFocusTouchListener.hideKeyboard(this.context, windowToken);
			
			List<View> views = this.clearFocusListener.getViewsToClearFocus();
			Rect outRect = new Rect();
			for (View view : views)
			{
				if (view.hasFocus())
				{
					view.getGlobalVisibleRect(outRect);
					if (!outRect.contains(x, y))
					{
						view.clearFocus();
					}
				}
			}
		}
		
		return false;
	}
	
	public static void hideKeyboard(Context context, IBinder windowToken)
	{
		InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
		imm.hideSoftInputFromWindow(windowToken, InputMethodManager.HIDE_NOT_ALWAYS);
	}
}
