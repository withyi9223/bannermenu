package com.zy.mvptest;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.PagerAdapter;

import java.util.List;

public class TabAdapter extends FragmentPagerAdapter {

	private List<Fragment> mFragments;
	@SuppressWarnings("unused")
	private FragmentManager manager;

	public TabAdapter(FragmentManager fm) {
		super(fm);
		manager = fm;
	}

	public TabAdapter(FragmentManager fm, List<Fragment> fragments) {
		super(fm);
		this.mFragments = fragments;
		this.manager = fm;
	}

	@Override
	public Fragment getItem(int arg0) {
		return mFragments.get(arg0);
	}

	@Override
	public int getCount() {
		return mFragments.size();
	}

	@Override
	public int getItemPosition(Object object) {
		return PagerAdapter.POSITION_NONE;
	}


}