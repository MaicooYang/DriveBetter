package com.loganisawesome.drivebetter.utilities.adapters;

import com.loganisawesome.drivebetter.landingpage.GraphFragment;
import com.loganisawesome.drivebetter.landingpage.TripInformationFragment;
import com.loganisawesome.drivebetter.utilities.enums.DriveBetterMenuItem;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.MenuItem;

public class MenuPagerAdapter extends FragmentPagerAdapter
{
	public MenuPagerAdapter(FragmentManager fm)
	{
		super(fm);
	}

	@Override
	public Fragment getItem(int position)
	{
		if (position == DriveBetterMenuItem.TripInformation.ordinal())
		{
			return TripInformationFragment.newInstance();
		}
		else if (position == DriveBetterMenuItem.Graphs.ordinal())
		{
			// TODO
			return GraphFragment.newInstance();
		}

		return null;
	}

	@Override
	public int getCount()
	{
		return DriveBetterMenuItem.values().length;
	}
	
	@Override
	public CharSequence getPageTitle(int position)
	{
		return DriveBetterMenuItem.values()[position].getDisplayName();
	}
}
