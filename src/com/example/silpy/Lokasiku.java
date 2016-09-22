package com.example.silpy;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.silpy.control.GPSTracker;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class Lokasiku extends Activity{
	
	private GoogleMap lokasiku;

    // GPS Location
    GPSTracker gps;
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.lokasiku);

		try {
			// Loading map
			initilizeMap();
			addingMarker();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * function to load map. If map is not created it will create it for you
	 * */
	private void initilizeMap() {
		gps = new GPSTracker(this);
		if (lokasiku == null) {
			lokasiku = ((MapFragment) getFragmentManager().findFragmentById(
					R.id.maplokasiku)).getMap();
			lokasiku.setMapType(GoogleMap.MAP_TYPE_NORMAL);
			CameraPosition cameraPosition = new CameraPosition.Builder().target(
	                new LatLng(gps.getLatitude(), gps.getLongitude())).zoom(15).build();
	 
			lokasiku.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
			lokasiku.setMyLocationEnabled(true);
			lokasiku.getUiSettings().setZoomControlsEnabled(true);
			lokasiku.getUiSettings().setZoomGesturesEnabled(true);
			lokasiku.getUiSettings().setCompassEnabled(true);
			lokasiku.getUiSettings().setMyLocationButtonEnabled(true);
			lokasiku.getUiSettings().setRotateGesturesEnabled(true);
			lokasiku.getUiSettings().setAllGesturesEnabled(true);
			lokasiku.getMyLocation();
			// check if map is created successfully or not
			if (lokasiku == null) {
				Toast.makeText(getApplicationContext(),
						"Sorry! unable to create maps", Toast.LENGTH_SHORT)
						.show();
			}
		}
	}

	public void addingMarker(){
		lokasiku.addMarker(new MarkerOptions().position(new LatLng(gps.getLatitude(), gps.getLongitude()))
				.title("Posisi Saya")
				.snippet("Saya")
				.icon(BitmapDescriptorFactory.fromResource(R.drawable.mark_blue)));
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		initilizeMap();
	}
	
	
	//###################################################
//	GoogleMap lokasiku;
//	
//	@Override
//	protected void onCreate(Bundle savedInstanceState) {
//		super.onCreate(savedInstanceState);
//		setContentView(R.layout.lokasiku);
//		initializeMAp();
//		addingMarker();
//	}
//	
//	public void initializeMAp(){
//		lokasiku=((MapFragment)getFragmentManager().findFragmentById(R.id.maplokasiku)).getMap();
//		lokasiku.clear();
//		if(lokasiku!=null)
//		{
//			lokasiku.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(-7.7526753, 110.4109754),15));
//			lokasiku.animateCamera(CameraUpdateFactory.zoomTo(10),2000,null);
//			lokasiku.setMyLocationEnabled(true);
//		}
//	}
//	
//	public void addingMarker(){
//		lokasiku.addMarker(new MarkerOptions().position(new LatLng(-7.7526753, 110.4109754))
//				.title("Lokosiku..")
//				.snippet("Saya")
//				.icon(BitmapDescriptorFactory.fromResource(R.drawable.marker)));
//	}
//
//	##########################################################
//	MapView maplokasiku = null;
//	MapController controlMap = null;
//	MyLocationOverlay akuDimana = null;
//	
//	protected boolean isLocationDisplayed() {
//        return akuDimana.isMyLocationEnabled();
//    }
//
//
//	protected boolean isRouteDisplayed() {
//		// TODO Auto-generated method stub
//		return false;
//	}
//
//	@Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.lokasiku);
//
//        maplokasiku = (MapView)findViewById(R.id.maplokasiku);
//        //maplokasiku.setBuiltInZoomControls(true);
//        
//        //controlMap = maplokasiku.getController();
//        controlMap.setZoom(15);
//
//        //akuDimana = new LokasikuOverlay(this, maplokasiku);
//        //maplokasiku.getOverlays().add(akuDimana);
//        maplokasiku.postInvalidate();
//    }
//
//	@Override
//    public void onResume()
//    {
//    	super.onResume();
//        akuDimana.enableMyLocation();
//		akuDimana.runOnFirstFix(new Runnable() {
//            public void run() {
//            	controlMap.setCenter(akuDimana.getMyLocation());
//            }
//        });
//		akuDimana.enableCompass();
//    }
//	
//	@Override
//    public void onPause()
//    {
//    	super.onPause();
//        akuDimana.disableMyLocation();
//        akuDimana.disableCompass();
//    }
//	
//	public boolean onCreateOptionsMenu(Menu menu){
//		super.onCreateOptionsMenu(menu);
//		MenuInflater inflater= getMenuInflater();
//		inflater.inflate(R.menu.menudimana, menu);
//		
//		return true;
//	}
//
//	public boolean onOptionsItemSelected(MenuItem item){
//		super.onOptionsItemSelected(item);
//		switch (item.getItemId()) {
//		case R.id.koordinat:
//			startActivity(new Intent(this, LokasikuKoordinat.class));
//			return true;
//		}
//		return false;
//	}
}
