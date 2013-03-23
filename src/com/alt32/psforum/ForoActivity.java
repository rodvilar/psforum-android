package com.alt32.psforum;

import android.app.Activity;
import android.os.Bundle;

import com.alt32.psforum.datatypes.UserInfo;

public class ForoActivity extends Activity {

	public static UserInfo userinfo;
	//private ListView lv;
	
	 public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.foro);
	        
	        //TextView txtUsername = (TextView) findViewById(R.id.txtUserName);
	        //txtUsername.setText(PSForumActivity.userinfo.username);

	        /*lv = (ListView) findViewById(R.id.lstCategorias);
	         // Instanciating an array list (you don't need to do this, you already have yours)
	         ArrayList<String> your_array_list = new ArrayList<String>();
	         your_array_list.add("foo");
	         your_array_list.add("bar");
	         // This is the array adapter, it takes the context of the activity as a first // parameter, the type of list view as a second parameter and your array as a third parameter
	         ArrayAdapter<String> arrayAdapter =      
	         new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, your_array_list);
	         lv.setAdapter(arrayAdapter); */
	        
	 }
		
	
}
