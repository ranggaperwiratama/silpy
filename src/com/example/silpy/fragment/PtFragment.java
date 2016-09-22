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
import com.example.silpy.R.id;
import com.example.silpy.R.layout;
import com.example.silpy.control.JSONParser;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.AdapterView.OnItemClickListener;

@SuppressLint("ValidFragment")
public class PtFragment extends Fragment {
	ArrayList<HashMap<String, String>> memberList;
	ListView lv;
    ProgressDialog mProgressDialog;
    String Id;
	String Pt;
	String jurusan;
	String jenjang;
	String Akreditasi;
	JSONParser  jParser = new JSONParser();

	private Context context;
	private int temp=0;
	private static String url_semua_anggota = "http://wisatapedia.16mb.com/silpy/silpy.php";
	//private static String url_semua_anggota = "http://169.254.130.124:1011/silpy/public/pt";
	//private static String url_semua_anggota = "http://suryatools.cuccfree.com/silpy/api.php";
	//private static String url_semua_anggota = "http://10.50.40.175:1011/silpy/public/pt";

	private static final String TAG_RESPONSE = "response";
	private static final String TAG_id = "id";
	private static final String TAG_Nama_Pt = "nama_pt";
	private static final String TAG_Jurusan = "jurusan";
	private static final String TAG_Akreditasi = "Akreditasi";
	private static final String TAG_jenjang = "jenjang";

	
	
	private ArrayList<HashMap<String, String>> jsonlist = new ArrayList<HashMap<String,String>>();
	JSONArray response = null;
	public void onResume()
	{
		super.onResume();
		
		
	}
	public PtFragment(){}	
	
	
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
    	try
    	{
    		getActivity().getActionBar().setTitle("Perguruan Tinggi");
        View rootView = inflater.inflate(R.layout.fragment_pt, container, false);
        lv =(ListView)rootView.findViewById(R.id.listViewLR);
        myAsyncTask myRequest = new myAsyncTask();
        myRequest.execute();
        return rootView;
    	}
    	catch(Exception e)
    	{
    		//Toast.makeText(getActivity(), "Check your connection..", )
    		View rootView = inflater.inflate(R.layout.fragment_pt, container, false);
    		return rootView;
    	}
        
    }
    
    private class myAsyncTask extends AsyncTask<Void, Void, Void>    {
		 
        @Override
        protected void onProgressUpdate(Void... values) {
        	super.onPreExecute();
        }
        
        @Override
        protected void onPreExecute() {
            super.onPreExecute();  
            if(temp==0)
	        {
	        	mProgressDialog = new ProgressDialog(getActivity());
	        	mProgressDialog.setMessage("Please Wait..");
	        	mProgressDialog.setIndeterminate(false);
	        	mProgressDialog.setCancelable(false);
	        	mProgressDialog.show(); 
            }
        }
       
        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            
            if(mProgressDialog.isShowing()){
            	mProgressDialog.dismiss();
            }
            ListAdapter adapter = new  SimpleAdapter(getActivity(),
            		jsonlist, R.layout.list_item_lr, new String[]{ TAG_Nama_Pt, "",TAG_Jurusan}, new int[] {R.id.text_konten1,R.id.text_konten3,R.id.text_konten4});
            lv.setAdapter(adapter);
            lv.setOnItemClickListener(new OnItemClickListener() {

    			@Override
    			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
    					long arg3) {
    				// TODO Auto-generated method stub
    				
    				Log.e("LeaveRequest_RefNo", jsonlist.get(arg2).get(TAG_id));
    				
		    		DetailPtFragment findpeoplefragment  = new DetailPtFragment(jsonlist.get(arg2).get(TAG_id));
				
					FragmentManager fragmentManager = getFragmentManager();
					FragmentTransaction ft = fragmentManager.beginTransaction();
					ft.replace(R.id.frame_container, findpeoplefragment).addToBackStack("1"); //saat fragment selanjutnya diback akan kembali ke fragment ini
					ft.commit();
    			}          	
    		});
            
            temp=1;
        }
 
        
 
        @Override
        protected Void doInBackground(Void... arg0) {
        	if(temp==0)
        	{
        	List<NameValuePair> params = new ArrayList<NameValuePair>();
        	
        	JSONObject json = jParser.makeHttpRequest(url_semua_anggota, "GET", params);//mengirimkan ke url yang ditentukan
        	Log.d("Semua Anggota1: ", json.toString());
        	try {
        		
        			response = json.getJSONArray(TAG_RESPONSE);
        			for (int i = 0; i < response.length(); i++) {
						JSONObject c = response.getJSONObject(i);						
						//mengambil data
						Id=c.getString(TAG_id);
						Pt = c.getString(TAG_Nama_Pt);
						jurusan = c.getString(TAG_Jurusan);
						Akreditasi = c.getString(TAG_Akreditasi);
						jenjang=c.getString(TAG_jenjang);
						
						
						HashMap<String, String> map = new HashMap<String, String>();
						map.put(TAG_id, Id);
						map.put(TAG_Nama_Pt, Pt);
						map.put(TAG_Jurusan, jurusan);
						map.put(TAG_Akreditasi, Akreditasi);
						map.put(TAG_jenjang, jenjang);
						jsonlist.add(map);
						
        			}
        	
        	}
        	catch(JSONException e){e.printStackTrace();}
        	}
			return null;
        }
        
	}
}
