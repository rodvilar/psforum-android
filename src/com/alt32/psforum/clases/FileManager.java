package com.alt32.psforum.clases;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import android.content.Context;

public class FileManager {

	public static String getSSOID(Context context) {

		String _userinfofile = "_file_ssoid";	
		return getValue(context, _userinfofile);

	}

	public static boolean setSSOID(Context context, String ssoid) {

		String _userinfofile = "_file_ssoid";	
		return setValue(context, _userinfofile, ssoid);

	}
	
	public static String getUserName(Context context) {

		try {
		String _userinfofile = "_file_username";	
		return getValue(context, _userinfofile);
		}
		catch (Exception ex) {
			return "";
		}

	}

	public static boolean setUserName(Context context, String username) {

		String _userinfofile = "_file_username";	
		return setValue(context, _userinfofile, username);

	}
	
	
	private static boolean setValue(Context context, String key, String value) {
		
		try {
			FileOutputStream fos = context.openFileOutput(key,
					Context.MODE_PRIVATE);
			ObjectOutputStream os = new ObjectOutputStream(fos);
			os.writeObject(value);
			os.close();
			return true;
		} catch (Exception e) {
			return false;
		}
		
	}
	
	private static String getValue(Context context, String key) {
		
		String _tmpValue = "";

		try {
			FileInputStream fis = context.openFileInput(key);
			ObjectInputStream is = new ObjectInputStream(fis);
			_tmpValue = (String) is.readObject();
			is.close();
			return _tmpValue;
		} catch (Exception e) {
			return "";
		}

		
	}

}
