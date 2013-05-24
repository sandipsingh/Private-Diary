package lock.diary;

import android.os.Bundle;
import android.preference.EditTextPreference;
import android.preference.PreferenceActivity;
import android.view.KeyEvent;

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

	}
}


