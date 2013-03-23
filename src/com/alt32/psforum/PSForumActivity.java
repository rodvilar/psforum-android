package com.alt32.psforum;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.alt32.psforum.clases.FileManager;
import com.alt32.psforum.clases.UserManager;
import com.alt32.psforum.datatypes.UserInfo;


public class PSForumActivity extends Activity {
	/** Called when the activity is first created. */
	
	public static UserInfo userinfo = new UserInfo();
	public static EditText txtUserName;
	public static EditText txtPassword;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);
        
        final LinearLayout layLogin = (LinearLayout) findViewById(R.id.layLogin);
        final LinearLayout layLoading = (LinearLayout) findViewById(R.id.layLoading);
        final Button btnLogin = (Button) findViewById(R.id.btnLogin);
        
        PSForumActivity.txtUserName = (EditText) findViewById(R.id.txtUsuario);
        PSForumActivity.txtPassword = (EditText) findViewById(R.id.txtPassword);
                    
        
        new Thread() {
			public void run() {
				try {
					 //si ya tengo ssoid
			        if (checkUser()) {
			        	
			        	//conecto con el server para obtener usuario y status
			        	PSForumActivity.userinfo = UserManager.login(PSForumActivity.userinfo);
			        	
			        	goToForo(PSForumActivity.userinfo);
			        	
			        }
			        else {
			        	//pido login
			        	layLogin.setVisibility(View.VISIBLE);
			        	layLoading.setVisibility(View.GONE);
			        	
			        }


				} catch (Exception e) {
					hRefresh.sendEmptyMessage(1);
				}
			}
		}.start();
        
		
        
        btnLogin.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
		
				layLogin.setVisibility(View.GONE);
	        	layLoading.setVisibility(View.VISIBLE);
	        	
				PSForumActivity.userinfo = UserManager.login(PSForumActivity.txtUserName.getText().toString(), PSForumActivity.txtPassword.getText().toString());
								
				if (PSForumActivity.userinfo.ssoid != "~"){
		        	FileManager.setSSOID(PSForumActivity.this, userinfo.ssoid);
		        	FileManager.setUserName(PSForumActivity.this, userinfo.username);
		        	goToForo(PSForumActivity.userinfo);
				}
				else {
				}

			}
		});
        
        //#D6012E
        
	}
	
	Handler hRefresh = new Handler() {

		public void handleMessage(Message msg) {
			switch (msg.what) {
			case 1: {
				Toast.makeText(PSForumActivity.this,
						"No hay conexión a internet", Toast.LENGTH_LONG).show();
			}
				break;
			default:
				break;
			}
		}

	};
	
	//se ejecuta la primera vez para obtener un ssoid
	public boolean checkUser() {
						
		try 
		{			
		
			String _ssoid = FileManager.getSSOID(this);
			
			if (_ssoid != ""){
				PSForumActivity.userinfo.ssoid = _ssoid;
				PSForumActivity.userinfo.username = FileManager.getUserName(this);
				return true;
			}
			else {
				return false;
			}
		}
		catch (Exception ex) {
			return false;
		}
	}
	
	public void goToForo(UserInfo user) {
		Intent intent = new Intent(PSForumActivity.this, ForoActivity.class);
		intent.putExtra("username", user.username);
		intent.putExtra("status", user.status);
		intent.putExtra("ssoid", user.ssoid);
		startActivity(intent);
		this.finish();
	}
	

}