package com.example.silpy;

import java.util.ArrayList;

import com.example.silpy.control.NavDrawerItem;
import com.example.silpy.control.NavDrawerListAdapter;
import com.example.silpy.fragment.PtFragment;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;



public class MainJurusan extends Activity{
	
	
	private DrawerLayout mDrawerLayout;
	private ListView mDrawerList;
	private ActionBarDrawerToggle mDrawerToggle;
	private CharSequence mDrawerTitle;
	private CharSequence mTitle;
	private String[] navMenuTitles;
	private TypedArray navMenuIcons;
	private ArrayList<NavDrawerItem> navDrawerItems;
	private NavDrawerListAdapter adapter;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_jurusan);
		mTitle = mDrawerTitle = getTitle();
		navMenuTitles = getResources().getStringArray(R.array.nav_drawer_items2);
		navMenuIcons = getResources()
				.obtainTypedArray(R.array.nav_drawer_icons);
		mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
		mDrawerList = (ListView) findViewById(R.id.list_slidermenu);
		navDrawerItems = new ArrayList<NavDrawerItem>();
		navDrawerItems.add(new NavDrawerItem(navMenuTitles[0], navMenuIcons.getResourceId(0, -1)));
		navDrawerItems.add(new NavDrawerItem(navMenuTitles[1], navMenuIcons.getResourceId(1, -1)));
		navDrawerItems.add(new NavDrawerItem(navMenuTitles[2], navMenuIcons.getResourceId(2, -1)));		
		navMenuIcons.recycle();
		mDrawerList.setOnItemClickListener(new SlideMenuClickListener());
		adapter = new NavDrawerListAdapter(getApplicationContext(),
				navDrawerItems);
		mDrawerList.setAdapter(adapter);
		getActionBar().setDisplayHomeAsUpEnabled(true);
		getActionBar().setHomeButtonEnabled(true);
		mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
				R.drawable.ic_drawer,
				R.string.app_name, 
				R.string.app_name 
		) {
			public void onDrawerClosed(View view) {
				getActionBar().setTitle(mTitle);
				invalidateOptionsMenu();
			}
			public void onDrawerOpened(View drawerView) {
				getActionBar().setTitle(mDrawerTitle);
				invalidateOptionsMenu();
			}
		};
		mDrawerLayout.setDrawerListener(mDrawerToggle);
		//Bundle b = getIntent().getExtras();
		//String a=b.getString("username");
		//String c=b.getString("password");
		//Toast.makeText(this, a+c, Toast.LENGTH_LONG).show();
		//fragment homeHRIS tampil pertama kali
		PtFragment PtFragment = new PtFragment();
		FragmentManager fragmentManager = getFragmentManager();
		FragmentTransaction ft = fragmentManager.beginTransaction();
		ft.replace(R.id.frame_container, PtFragment);
		ft.commit();
		mDrawerList.setItemChecked(0, true);
		mDrawerList.setSelection(0);
		setTitle(navMenuTitles[0]);
		mDrawerLayout.closeDrawer(mDrawerList);
	}
	public void onResume()
	{
		super.onResume();
		//if(Credential.checkSession(this)==false)finish();
		
	}
	private class SlideMenuClickListener implements
			ListView.OnItemClickListener {
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			//Bundle b = getIntent().getExtras();
			//String a=b.getString("username");
			//String c=b.getString("password");
			if(position==0)
			{
				PtFragment PtFragment = new PtFragment();
				FragmentManager fragmentManager = getFragmentManager();
				FragmentTransaction ft = fragmentManager.beginTransaction();
				ft.replace(R.id.frame_container, PtFragment);
				ft.commit();
				mDrawerList.setItemChecked(position, true);
				mDrawerList.setSelection(position);
				setTitle(navMenuTitles[position]);
				mDrawerLayout.closeDrawer(mDrawerList);
				
				
			}
			if(position==1)
			{
				/*Toast.makeText(getApplicationContext(), "Please choose approval", Toast.LENGTH_LONG).show();
				homeHRIS homeFragment = new homeHRIS(a,c);
				FragmentManager fragmentManager = getFragmentManager();
				FragmentTransaction ft = fragmentManager.beginTransaction();
				ft.replace(R.id.frame_container, homeFragment);
				ft.commit();*/
				//homeHRIS homeFragment = new homeHRIS(a,c);
				//FragmentManager fragmentManager = getFragmentManager();
				//FragmentTransaction ft = fragmentManager.beginTransaction();
				//ft.replace(R.id.frame_container, homeFragment);
				//ft.commit();
				mDrawerList.setItemChecked(position, true);
				mDrawerList.setSelection(position);
				setTitle(navMenuTitles[position]);
				mDrawerLayout.closeDrawer(mDrawerList);
			}
			if(position==2)
			{
				finish();
				
			}
		}
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main_menu, menu);
		return true;
	}
	@Override
    public boolean onOptionsItemSelected(MenuItem item) {
		if (mDrawerToggle.onOptionsItemSelected(item)) {
			return true;
		}
		switch(item.getItemId()) {
	     
	                       
	      case R.id.keluar:
	      keluar(); 
	      return true;
	                       
	      default:

        return super.onOptionsItemSelected(item);
		}
    }
		
	public void keluar(){
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setMessage("Apakah Anda Benar-Benar ingin keluar?").setCancelable(false).setPositiveButton("Ya",new DialogInterface.OnClickListener() {
		      public void onClick(DialogInterface dialog,int id) {
		                  MainJurusan.this.finish();
		            }
		      }).setNegativeButton("Tidak",new DialogInterface.OnClickListener() {
		      public void onClick(DialogInterface dialog,int id) {
		                  dialog.cancel();
		            }
		      }).show();
		}
	@Override
	public boolean onPrepareOptionsMenu(Menu menu) {
		boolean drawerOpen = mDrawerLayout.isDrawerOpen(mDrawerList);
		//menu.findItem(R.id.action_settings).setVisible(!drawerOpen);
		return super.onPrepareOptionsMenu(menu);
	}
	@Override
	public void setTitle(CharSequence title) {
		mTitle = title;
		getActionBar().setTitle(mTitle);
	}
	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		mDrawerToggle.syncState();
	}
	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		mDrawerToggle.onConfigurationChanged(newConfig);
	}

}
