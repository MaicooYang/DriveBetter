package com.loganisawesome.drivebetter.landingpage;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class GraphFragment extends Fragment
{
	public static GraphFragment newInstance()
	{
		GraphFragment fragment = new GraphFragment();
		// Set args
		return fragment;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		// do stuff
		return super.onCreateView(inflater, container, savedInstanceState);
	}

	
}
