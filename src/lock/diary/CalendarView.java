package lock.diary;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Random;


import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class CalendarView extends Activity implements OnClickListener
{
	TextView calendarB;
	public Calendar month;
	public CalendarAdapter adapter;
	public Handler handler;
	public ArrayList<String> items;
	
	@Override
	protected void onPause() 
	{                                 
	    //finish();      
		super.onPause();           
	}
	

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
	
	@Override
	protected void onStop() 
	{
		// TODO Auto-generated method stub
		if(FirstLoginActivity.b==1)
		{
			FirstLoginActivity.edt_Pwd.setText("");
		}
		finish();
		super.onStop();
	}
	

	
	public void onDestroy()
	{
		super.onDestroy();
	}
	
	public void onConfigurationChanged(Configuration newConfig) 
	{
	    super.onConfigurationChanged(newConfig);

	    // Checks the orientation of the screen
	    if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) 
	    {
	       // Toast.makeText(this, "landscape", Toast.LENGTH_SHORT).show();
	    	//finish();
	    	Intent i=new Intent();
	    	i.setClass(getApplicationContext(), CalendarView.class);
	    	startActivity(i);
	    	

	    	//setContentView(R.layout.login_view);
	    } 
	    else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT)
	    {
	    	Intent i=new Intent();
	    	i.setClass(getApplicationContext(), CalendarView.class);
	    	startActivity(i);
	    	

	       // Toast.makeText(this, "portrait", Toast.LENGTH_SHORT).show();
	   }
	  }
	
	ImageButton plus, settings;
	GridView gridview;
	TextView title, previous, next;
	
	EntryData entry=new EntryData(this);
	
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setTheme(android.R.style.Theme_Light_NoTitleBar_Fullscreen);

		setContentView(R.layout.calendar);
		month = Calendar.getInstance();
		setTheme(android.R.style.Theme_Light_NoTitleBar_Fullscreen);
	
		
		plus = (ImageButton) findViewById(R.id.plusButton);
		plus.setOnClickListener(this);

		settings = (ImageButton) findViewById(R.id.settingsButton);
		settings.setOnClickListener(this);

		items = new ArrayList<String>();
		adapter = new CalendarAdapter(this, month);

		gridview = (GridView) findViewById(R.id.gridview);
		gridview.setAdapter(adapter);

		handler = new Handler();
		refreshCalendar();
	//	handler.post(calendarUpdater);

		title = (TextView) findViewById(R.id.title);
		title.setText(android.text.format.DateFormat.format("MMMM yyyy", month));

		previous = (TextView) findViewById(R.id.previous);
		previous.setOnClickListener(this);

		next = (TextView) findViewById(R.id.next);
		next.setOnClickListener(this);
		
		calendarB = (TextView) findViewById(R.id.img_ListView);
		calendarB.setOnClickListener(this);

		gridview.setOnItemClickListener(new OnItemClickListener() 
		{
			public void onItemClick(AdapterView<?> parent, View v,
					int position, long id) 
			{
				TextView date = (TextView) v.findViewById(R.id.date);
				if (date instanceof TextView && !date.getText().equals("")) 
				{
					Intent intent = new Intent(CalendarView.this,MainActivity.class);
					intent.putExtra("day", Integer.parseInt(date.getText().toString()));
					intent.putExtra("year", month.get(Calendar.YEAR));
					intent.putExtra("MONTH", String.valueOf(month.get(Calendar.MONTH)));
					startActivity(intent);
				}
			}
		});
	}

	public void refreshCalendar() 
	{
		TextView title = (TextView) findViewById(R.id.title);

		adapter.refreshDays();
		adapter.notifyDataSetChanged();
		handler.post(calendarUpdater); // generate some random calendar items

		title.setText(android.text.format.DateFormat.format("MMMM yyyy", month));
	}
	
	public Runnable calendarUpdater = new Runnable() 
	{

		public void run() 
		{
			items.clear();
			for (int i = 0; i < 31; i++) 
			{
				Random r = new Random();

				if (r.nextInt(10) > 6) 
				{
					items.add(Integer.toString(i));
				}
			}

			// adapter.setItems();
			adapter.notifyDataSetChanged();       
		}
	};

	public void onClick(View v) 
	{
		// TODO Auto-generated method stub
		if(v.getId()==R.id.plusButton)
		{
			Intent i = new Intent(CalendarView.this, NoteActivity.class);
			i.putExtra("plus", true);
			startActivity(i);
		}
		
		if(v.getId()==R.id.settingsButton)
		{
			Intent i = new Intent(getBaseContext(), PrefsActivity.class);
			startActivity(i);
		}
		
		if(v.getId()==R.id.previous)
		{
			if (month.get(Calendar.MONTH) == month.getActualMinimum(Calendar.MONTH)) 
			{
				month.set((month.get(Calendar.YEAR) - 1),month.getActualMaximum(Calendar.MONTH), 1);
			} 
			else 
			{
				month.set(Calendar.MONTH, month.get(Calendar.MONTH) - 1);
			}
			refreshCalendar();
		}
		
		if(v.getId()==R.id.next)
		{

			if (month.get(Calendar.MONTH) == month
					.getActualMaximum(Calendar.MONTH)) 
			{
				month.set((month.get(Calendar.YEAR) + 1),
						month.getActualMinimum(Calendar.MONTH), 1);
			} 
			else 
			{
				month.set(Calendar.MONTH, month.get(Calendar.MONTH) + 1);
			}
			refreshCalendar();
		}
		
		if(v.getId()==R.id.img_ListView)
		{
			Intent i = new Intent(CalendarView.this, MainActivity.class);
			startActivity(i);
		}
		
	}
}
