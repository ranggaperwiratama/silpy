package com.example.silpy;

import com.example.silpy.R.style;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;

public class SplashScreen extends Activity{
	//Set waktu lama splashscreen
		 private static int splashInterval = 3500;
		  
		 @Override
		 protected void onCreate(Bundle savedInstanceState) {
		  super.onCreate(savedInstanceState);
		  setTheme(style.AppBaseTheme);
		  requestWindowFeature(Window.FEATURE_NO_TITLE);
		  getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, 
			                                WindowManager.LayoutParams.FLAG_FULLSCREEN);
		  setContentView(R.layout.splash);
		   
		  new Handler().postDelayed(new Runnable() {
		    
		    
		   @Override
		   public void run() {
		    // TODO Auto-generated method stub
		    Intent i = new Intent(SplashScreen.this, MainMenuActivity.class);
		    startActivity(i);
		     
		                                //Menyelesaikan Splashscreen
		    finish();
		       }
		    }, splashInterval);
		    
		  };
}
