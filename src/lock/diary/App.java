package lock.diary;

import android.app.Application;

public class App extends Application
{
	static boolean logged;
	static long currentId;
	
	@Override
	public void onCreate() 
	{
		super.onCreate();
		logged = false;
	}
		
}

