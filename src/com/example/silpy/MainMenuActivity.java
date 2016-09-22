package com.example.silpy;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import com.example.silpy.R.menu;



public class MainMenuActivity extends Activity {

	Button btnLokasi,btnRute,btnJarak,btnJurusan,btnAbout,btnHelp;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_menu);
		
		btnLokasi= (Button) findViewById(R.id.btnLokasiku);
	    btnLokasi.setOnClickListener(new OnClickListener() {
	        public void onClick(View v) {
	        	Intent i= new Intent(getApplicationContext(), Lokasiku.class);
	            startActivity(i);

	    } });
	    
	    btnRute= (Button) findViewById(R.id.btnRute);
	    btnRute.setOnClickListener(new OnClickListener() {
	        public void onClick(View v) {
	             //Toast.makeText(getApplicationContext(), "Anda Menekan Tombol Rute", Toast.LENGTH_SHORT).show();
	        	Intent ipt= new Intent(getApplicationContext(), MainActivityPt.class);
	            startActivity(ipt);
	    } });
	    
	    btnJarak= (Button) findViewById(R.id.btnJarak);
	    btnJarak.setOnClickListener(new OnClickListener() {
	        public void onClick(View v) {
	             //Toast.makeText(getApplicationContext(), "Anda Menekan Tombol Jarak", Toast.LENGTH_SHORT).show();
	        	//Intent ig= new Intent(getApplicationContext(), Ugm.class);
	            //startActivity(ig);
	    } });
	    
	    btnJurusan= (Button) findViewById(R.id.btnJurusan);
	    btnJurusan.setOnClickListener(new OnClickListener() {
	        public void onClick(View v) {
	        	Intent in= new Intent(getApplicationContext(), MainJurusan.class);
	            startActivity(in);
	    } });
	    
	    btnAbout= (Button) findViewById(R.id.btnAbout);
	    btnAbout.setOnClickListener(new OnClickListener() {
	        public void onClick(View v) {
	             Toast.makeText(getApplicationContext(), "Anda Menekan Tombol About", Toast.LENGTH_SHORT).show();

	    } });
	    
	    btnHelp= (Button) findViewById(R.id.btnBantuan);
	    btnHelp.setOnClickListener(new OnClickListener() {
	        public void onClick(View v) {
	             Toast.makeText(getApplicationContext(), "Anda Menekan Tombol Bantuan", Toast.LENGTH_SHORT).show();

	    } });
	}
	
	



	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main_menu, menu);
		return true;
	}
	
	@Override
    public boolean onOptionsItemSelected(MenuItem item) {
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
		                  MainMenuActivity.this.finish();
		            }
		      }).setNegativeButton("Tidak",new DialogInterface.OnClickListener() {
		      public void onClick(DialogInterface dialog,int id) {
		                  dialog.cancel();
		            }
		      }).show();
		}



}
