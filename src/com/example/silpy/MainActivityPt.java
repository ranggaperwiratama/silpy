package com.example.silpy;

import com.example.silpy.control.GPSTracker;
import com.example.silpy.control.JSONHelper;
import com.example.silpy.entity.PerguruanTinggi;

import java.util.List;

import org.json.JSONObject;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.widget.Toast;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.GoogleMap.OnInfoWindowClickListener;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MainActivityPt extends FragmentActivity implements OnInfoWindowClickListener{

	private GoogleMap			map;
	private JSONHelper			json;
	private ProgressDialog		pDialog;
	private LatLng				myLocation;
	GPSTracker gps;
	private List<PerguruanTinggi>	listPerguruanTinggi;
	//private final String		URL_API			= "http://suryatools.cuccfree.com/silpy/api.php";
	//private static String URL_API = "http://169.254.130.124:1011/silpy/public/pt";
	private final String		URL_API			= "http://wisatapedia.16mb.com/silpy/silpy.php";
	public static final String	KEY_NAMA		= "nama";
	//public static final String	KEY_ALAMAT		= "alamat";
	public static final String	KEY_LAT_TUJUAN	= "lat_tujuan";
	public static final String	KEY_LNG_TUJUAN	= "lng_tujuan";
	public static final String	KEY_LAT_ASAL	= "lat_asal";
	public static final String	KEY_LNG_ASAL	= "lng_asal";

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_pt);
		json = new JSONHelper();

		if (isKonekInternet())
		{
			new AsynTaskMain().execute();
			
			setupMapIfNeeded();
			addingMarkerMy();
		} else
		{
			ShowAlert(this, "Warning", "Anda tidak tersambung dengan internet");
		}
	}

	private void setupMapIfNeeded()
	{
		gps = new GPSTracker(this);
		if (map == null)
		{
			FragmentManager fragmentManager = getSupportFragmentManager();
			SupportMapFragment supportMapFragment = (SupportMapFragment) fragmentManager.findFragmentById(R.id.maps);
			map = supportMapFragment.getMap();
			
			CameraPosition cameraPosition = new CameraPosition.Builder().target(
	                new LatLng(gps.getLatitude(), gps.getLongitude())).zoom(12).build();
			map.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
			map.setMyLocationEnabled(true);
			map.getUiSettings().setZoomControlsEnabled(true);
			map.getUiSettings().setZoomGesturesEnabled(true);
			map.getUiSettings().setCompassEnabled(true);
			map.getUiSettings().setMyLocationButtonEnabled(true);
			map.getUiSettings().setRotateGesturesEnabled(true);
			map.getUiSettings().setAllGesturesEnabled(true);
			map.getMyLocation();
			// check if map is created successfully or not
			if (map == null) {
				Toast.makeText(getApplicationContext(),
						"Sorry! unable to create maps", Toast.LENGTH_SHORT)
						.show();
			}
			
			if (map != null)
			{
				setupMap();
			}
		}

	}

	/*
	 * Cek internet connection
	 */
	private boolean isKonekInternet()
	{
		ConnectivityManager connectivityManager = (ConnectivityManager) this
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		if (connectivityManager != null)
		{
			NetworkInfo[] info = connectivityManager.getAllNetworkInfo();
			if (info != null)
			{
				for (int i = 0; i < info.length; i++)
				{
					if (info[i].getState() == NetworkInfo.State.CONNECTED)
					{
						return true;
					}
				}
			}

		}
		return false;
	}

	public void ShowAlert(Context context, String title, String message)
	{
		AlertDialog alertDialog = new AlertDialog.Builder(context).create();

		alertDialog.setTitle(title);
		alertDialog.setMessage(message);
		alertDialog.setButton(DialogInterface.BUTTON_POSITIVE, "Ok", new DialogInterface.OnClickListener()
		{

			@Override
			public void onClick(DialogInterface dialog, int which)
			{
				// TODO Auto-generated method stub
				finish();
			}
		});
		alertDialog.show();

	}

	private void setupMap()
	{
		map.setOnInfoWindowClickListener(this);
	}


	@Override
	protected void onResume()
	{
		// TODO Auto-generated method stub
		super.onResume();

		int resCode = GooglePlayServicesUtil.isGooglePlayServicesAvailable(getApplicationContext());
		if (resCode != ConnectionResult.SUCCESS)
		{
			GooglePlayServicesUtil.getErrorDialog(resCode, this, 1);
		}
	}

	private class AsynTaskMain extends AsyncTask<Void, Void, Void>
	{

		@Override
		protected void onPostExecute(Void result)
		{
			// TODO Auto-generated method stub
			pDialog.dismiss();
			runOnUiThread(new Runnable()
			{

				@Override
				public void run()
				{
					
					// TODO Auto-generated method stub
					for (int i = 0; i < listPerguruanTinggi.size(); i++)
					{

						map.addMarker(new MarkerOptions()
								.position(new LatLng(listPerguruanTinggi.get(i).getLat(),
										listPerguruanTinggi.get(i).getLng()))
								.title(listPerguruanTinggi.get(i).getNama())
								//.snippet(listPerguruanTinggi.get(i).getAlamat())
								.icon(BitmapDescriptorFactory.fromResource(R.drawable.mark_kampus)));

					}
				}
			});

			super.onPostExecute(result);
		}

		@Override
		protected void onPreExecute()
		{
			// TODO Auto-generated method stub
			super.onPreExecute();
			pDialog = new ProgressDialog(MainActivityPt.this);
			pDialog.setMessage("Loading....");
			pDialog.setCancelable(true);
			pDialog.show();
		}

		@Override
		protected Void doInBackground(Void... params)
		{
			// TODO Auto-generated method stub

			JSONObject jObject = json.getJSONFromURL(URL_API);
			Log.d("Semua Anggota1: ", jObject.toString());
			listPerguruanTinggi = json.getPerguruanTinggiAll(jObject);
			return null;
		}
	}

	@Override
	public void onInfoWindowClick(Marker marker)
	{
		// marker id -> m0, m1, m2 dst..
		String id = marker.getId();
		id = id.substring(1);

		myLocation = new LatLng(map.getMyLocation().getLatitude(), map.getMyLocation().getLongitude());

		if (myLocation != null)
		{
			Bundle bundle = new Bundle();
			bundle.putString(KEY_NAMA, listPerguruanTinggi.get(Integer.parseInt(id)).getNama());
			//bundle.putString(KEY_ALAMAT, listPerguruanTinggi.get(Integer.parseInt(id)).getAlamat());
			bundle.putDouble(KEY_LAT_TUJUAN, marker.getPosition().latitude);
			bundle.putDouble(KEY_LNG_TUJUAN, marker.getPosition().longitude);
			bundle.putDouble(KEY_LAT_ASAL, myLocation.latitude);
			bundle.putDouble(KEY_LNG_ASAL, myLocation.longitude);

			Intent i = new Intent(MainActivityPt.this, InfoPerguruanTinggiActivity.class);
			i.putExtras(bundle);
			startActivity(i);

		} else
		{
			Toast.makeText(this, "Tidak dapat menemukan lokasi anda ", Toast.LENGTH_LONG).show();
		}
	}
	
	public void addingMarkerMy(){
		map.addMarker(new MarkerOptions().position(new LatLng(gps.getLatitude(), gps.getLongitude()))
				.title("Posisi Saya")
				.snippet("Saya")
				.icon(BitmapDescriptorFactory.fromResource(R.drawable.mark_blue)));
	}

}
