package lock.diary;

import android.content.Intent;
import android.os.Bundle;
import android.preference.DialogPreference;
import android.preference.EditTextPreference;
import android.preference.Preference;
import android.preference.Preference.OnPreferenceClickListener;
import android.preference.PreferenceActivity;
import android.preference.PreferenceScreen;
import android.view.KeyEvent;
import android.widget.Toast;

public class PrefsActivity extends PreferenceActivity 
{
	@Override
	 public boolean onKeyDown(int keyCode, KeyEvent event)    
	 {
	      if(keyCode == KeyEvent.KEYCODE_HOME)
	      {
	           finish();
	           return true;
	      }

	      return super.onKeyDown(keyCode, event);
	 }

	@SuppressWarnings("deprecation")
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
	   super.onCreate(savedInstanceState);
	   addPreferencesFromResource(R.xml.prefs);
		setTheme(android.R.style.Theme_Light_NoTitleBar_Fullscreen);

	   EditTextPreference pswd = (EditTextPreference) findPreference("password");  
		setTheme(android.R.style.Theme_Light_NoTitleBar_Fullscreen);
//		EditTextPreference inst = (EditTextPreference)findPreference("inst");
//		inst.setIntent(new Intent(this, Instructions.class));

		Preference inst = (Preference) findPreference("inst");  
		 inst.setOnPreferenceClickListener(new OnPreferenceClickListener(){

			public boolean onPreferenceClick(Preference preference) {
				// TODO Auto-generated method stub
				Intent i=new Intent(getApplicationContext(),Instructions.class);
				startActivity(i);
				return false;
				
				
			}
			 
		 });
//		PreferenceScreen screen = getPreferenceScreen(); // gets the main preference screen 
//		screen.onItemClick(null, null, 3 , 0);
	}
}


