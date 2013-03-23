package com.alt32.psforum.clases;

import android.util.Log;

import com.alt32.psforum.datatypes.UserInfo;

public class UserManager {
		
	public static UserInfo login(UserInfo _user) {
		
		String url = "access/login_cookie.php?usr="+_user.username+"&ssoid="+_user.ssoid;
		Log.d("PS", url);
		return (UserInfo)DataParser.parse(url, UserInfo.class);
				
	}
	
	public static UserInfo login(String usuario, String password){
		
		String url = "access/login.php?usr="+usuario+"&pwd="+password;
		return (UserInfo)DataParser.parse(url, UserInfo.class);
		
	}
	
	public static boolean logout(){
		
		return false;
		
	}
	
}
