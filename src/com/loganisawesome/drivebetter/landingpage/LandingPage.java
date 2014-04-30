package com.loganisawesome.drivebetter.landingpage;

import java.util.ArrayList;
import java.util.List;

import com.loganisawesome.drivebetter.R;
import com.loganisawesome.drivebetter.utilities.adapters.MenuPagerAdapter;
import com.loganisawesome.drivebetter.utilities.helpers.ClearFocusTouchListener;
import com.loganisawesome.drivebetter.utilities.interfaces.ClearFocusInterface;

import android.support.v7.app.ActionBar.Tab;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class LandingPage extends ActionBarActivity implements ActionBar.TabListener, ClearFocusInterface
{
	ViewPager menuPager;
	List<ClearFocusInterface> clearFocusInterfaces = new ArrayList<ClearFocusInterface>();

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_landing_page);

		// CAREFUL: was moving all member init into onCreate, but super.onCreate() calls onAttachFragment before it gets
		// here resulting in NRP
		initView();
	}

	@Override
	public void onAttachFragment(Fragment fragment)
	{
		super.onAttachFragment(fragment);

		if (fragment instanceof ClearFocusInterface)
		{
			this.clearFocusInterfaces.add((ClearFocusInterface) fragment);
		}
	}

	/**********************************************************************************************
	 * Menu Items
	 **********************************************************************************************/
	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.landing_page, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		int id = item.getItemId();
		if (id == R.id.action_settings)
		{
			return true;
		}

		return super.onOptionsItemSelected(item);
	}

	/**********************************************************************************************
	 * Tab changed
	 **********************************************************************************************/
	@Override
	public void onTabSelected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction)
	{
		this.menuPager.setCurrentItem(tab.getPosition());
		ClearFocusTouchListener.hideKeyboard(this, this.menuPager.getWindowToken());
	}

	@Override
	public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction)
	{
	}

	@Override
	public void onTabReselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction)
	{
	}

	/**********************************************************************************************
	 * Init Methods
	 **********************************************************************************************/
	private void initView()
	{
		// Set up the action bar.
		final ActionBar actionBar = getSupportActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

		// Set the menu adapter
		MenuPagerAdapter menuAdapter = new MenuPagerAdapter(getSupportFragmentManager());
		this.menuPager = (ViewPager) findViewById(R.id.menuPager);
		menuPager.setAdapter(menuAdapter);

		// Set up clear focus listeners
		menuPager.setOnTouchListener(new ClearFocusTouchListener(this, this));

		menuPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener()
		{
			@Override
			public void onPageSelected(int position)
			{
				actionBar.setSelectedNavigationItem(position);
			}
		});

		// For each of the menu items, add a tab to the action bar.
		for (int i = 0; i < menuAdapter.getCount(); i++)
		{
			Tab tab = actionBar.newTab();
			tab.setText(menuAdapter.getPageTitle(i));
			tab.setTabListener(this);
			actionBar.addTab(tab);
		}
	}

	@Override
	public List<View> getViewsToClearFocus()
	{
		List<View> views = new ArrayList<>();
		for (ClearFocusInterface clearFocusInterface : this.clearFocusInterfaces)
		{
			views.addAll(clearFocusInterface.getViewsToClearFocus());
		}

		return views;
	}
}
