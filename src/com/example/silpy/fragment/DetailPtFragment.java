package com.example.silpy.fragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.silpy.R;
import com.example.silpy.control.JSONParser;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;



@SuppressLint("ValidFragment")
public class DetailPtFragment extends Fragment{
	private HashMap<String, String> hm = new HashMap<String, String>();
	ProgressDialog mProgressDialog;
	String username1,password1;
	JSONParser jParser = new JSONParser();
	private static String url_semua_anggota = "http://wisatapedia.16mb.com/silpy/silpybyid.php";
	//private static String url_semua_anggota = "http://169.254.130.124:1011/silpy/public/pt/id";

	//private static String url_semua_anggota = "http://10.50.40.175:1011/silpy/public/api/pt/";
	
	private static final String TAG_RESPONSE = "response";
	private static final String TAG_Nama_Pt = "nama_pt";
	private static final String TAG_Jurusan = "jurusan";
	private static final String TAG_Akreditasi = "Akreditasi";
	private static final String TAG_jenjang = "jenjang";
	private static final String TAG_tahun = "tahun";
	private static final String TAG_masaberlaku = "masa_berlaku";
	private static final String TAG_status = "status";
	
	
	private String id;
	private String Nama_Pt;
	private String Jurusan;
	private String Akreditasi;
	private String Jenjang;
	private String Tahun;
	private String Masa_Berlaku;
	private String Status;
	
	EditText  Nama_Pt1,Jurusan1,Akreditasi1,Jenjang1,Tahun1,Masa_Berlaku1,Status1;
	JSONArray member = null;
	
	int temp=0;

	public DetailPtFragment(){}
	public DetailPtFragment(String id)
	{
		this.id=id;
	}
	public void onResume()
	{
		super.onResume();
		
		
	}
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
		getActivity().getActionBar().setTitle("Detail Perguruan Tinggi");
        View rootView = inflater.inflate(R.layout.fragment_detail_po, container, false);
        Nama_Pt1=(EditText) rootView.findViewById(R.id.txtPONum);
        Jurusan1=(EditText) rootView.findViewById(R.id.txtBuyer);
        Akreditasi1=(EditText) rootView.findViewById(R.id.txtLimit);
        Tahun1=(EditText) rootView.findViewById(R.id.txtAmount);
        Status1=(EditText) rootView.findViewById(R.id.txtAprAmount);
        Jenjang1=(EditText) rootView.findViewById(R.id.txtjenjang);
        //Masa_Berlaku1=(EditText) rootView.findViewById(R.id.txtjenjang);
    	
    	
    	
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.
	              Builder().permitAll().build();
	 
	           StrictMode.setThreadPolicy(policy);
    	
    	new myAsyncTask().execute();
    	Button save = (Button)rootView.findViewById(R.id.btnSave);
    	
    	temp=0;
        save.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub				
				//onCreateDialog(1);		
				
			}
		});
        return rootView;
    }
	private class myAsyncTask extends AsyncTask<Void, Void, Void>    {
		 
        @Override
        protected void onProgressUpdate(Void... values) {
        	super.onPreExecute();

        }
 
        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            if(temp==0)
            {
	            Nama_Pt1.setText(hm.get("NamaPt").toString());
	            Jurusan1.setText(hm.get("Jurusan").toString());
	            Akreditasi1.setText(hm.get("Akreditasi").toString());
	            Jenjang1.setText(hm.get("Jenjang").toString());
	            Tahun1.setText(hm.get("Tahun").toString());
	            //Masa_Berlaku1.setText(hm.get("MasaBerlaku").toString());
	            Status1.setText(hm.get("Status").toString());
	    
	            mProgressDialog.dismiss();
	            temp=1;
            }
            else
            {
            	mProgressDialog.dismiss();
            }            
        }
 
        @Override
        protected void onPreExecute() {
            super.onPreExecute();  
            if(temp==0)
            {
        	mProgressDialog = new ProgressDialog(getActivity());
        	mProgressDialog.setMessage("Please Wait...");
        	mProgressDialog.setIndeterminate(false);
        	mProgressDialog.setCancelable(false);
        	mProgressDialog.show();
        	
            }
            
        }
 
        @Override
        protected Void doInBackground(Void... arg0) {
        	if(temp==0)
        	{
	        	List<NameValuePair> params = new ArrayList<NameValuePair>();
	        	params.add(new BasicNameValuePair("id",id));
	        	
	        	JSONObject json = jParser.makeHttpRequest(url_semua_anggota, "GET", params);
	        	
	        	Log.d("Semua Anggota: ", json.toString());
	        	try {
	        				member = json.getJSONArray(TAG_RESPONSE);
							JSONObject c = member.getJSONObject(0);							
							 Nama_Pt = c.getString(TAG_Nama_Pt);
							 Jurusan = c.getString(TAG_Jurusan);
							 Jenjang = c.getString(TAG_jenjang);
							 Tahun = c.getString(TAG_tahun);
							 //Masa_Berlaku = c.getString(TAG_masaberlaku);
							 Status = c.getString(TAG_status);
							 Akreditasi = c.getString(TAG_Akreditasi);
							
							 
							 hm.put("NamaPt", Nama_Pt);
							 hm.put("Jurusan", Jurusan);						 
							 hm.put("Jenjang", Jenjang);
							 hm.put("Tahun", Tahun);
							 //hm.put("MasaBerlaku", Masa_Berlaku);
							 hm.put("Status", Status);
							 hm.put("Akreditasi", Akreditasi);						 
	 			
					
	        	}
	        	catch(JSONException e){e.printStackTrace();}
        	}
        	
				
			return null;
        }
	}
	
	
}
