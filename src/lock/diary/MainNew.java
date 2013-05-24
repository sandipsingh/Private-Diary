package lock.diary;

import java.util.Calendar;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.format.DateUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.SimpleCursorAdapter.ViewBinder;

public class MainNew extends Activity implements OnClickListener {
	public Calendar month;
	public int newMonth=-1;
	public Calendar myCalendar = Calendar.getInstance();
	int myIndex;

	boolean logged = false;

	static final String[] months = { "January", "February", "March", "April",
	"May", "June", "July", "August", "September", "October",
	"November", "December" };

	ImageButton plus, fwd, bck, settings;
	TextView calendarB;
	SharedPreferences firstStartPref, prefs;
	TextView monthText, empty;
	EntryData data;
	Cursor cursor;
	ListView list;
	App app = (App) getApplication();
	SimpleCursorAdapter adapter;

	void checkForPassword() {
	//
	}

	@Override
	protected void onResume() {
	super.onResume();
	try {
	if (getIntent().getStringExtra("MONTH") != null) {
	String monthgname = getIntent().getStringExtra("MONTH");
	newMonth = Integer.valueOf(monthgname);
	cursor = data.getCursor(newMonth, 2013);
	cursor.getCount();
	monthText = (TextView) findViewById(R.id.textView1);
	monthText.setText(getMonthName(newMonth));
	myIndex=newMonth;
	} else {
	cursor = data.getCursor(myCalendar.get(Calendar.MONTH), 2013);
	}
	adapter.changeCursor(cursor);
	} catch (Exception e) {
	e.printStackTrace();
	}
	}

	public String getMonthName(int month) {
	String Month = "";
	if (month == 0) {
	Month = "January";
	} else if (month == 1) {
	Month = "Febuary";
	} else if (month == 2) {
	Month = "March";
	} else if (month == 3) {
	Month = "April";
	} else if (month == 4) {
	Month = "May";
	} else if (month == 5) {
	Month = "June";
	} else if (month == 6) {
	Month = "July";
	} else if (month == 7) {
	Month = "August";
	} else if (month == 8) {
	Month = "September";
	} else if (month == 9) {
	Month = "Oct";
	} else if (month == 10) {
	Month = "November";
	} else if (month == 11) {
	Month = "December";
	}
	return Month;

	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
	if (keyCode == KeyEvent.KEYCODE_HOME) {
	finish();
	return true;
	}
	return super.onKeyDown(keyCode, event);
	}

	/*
	 * @Override public boolean onCreateOptionsMenu(Menu menu) {
	 * getMenuInflater().inflate(R.menu.activity_main, menu); return true; }
	 * 
	 * @Override public boolean onOptionsItemSelected(MenuItem item) { return
	 * super.onOptionsItemSelected(item); }
	 */


@Override
	protected void onPause() {
	// TODO Auto-generated method stub
	MainNew.this.finish();
	super.onPause();
	}

	@Override
	protected void onStop() {
	// TODO Auto-generated method stub
	int i = FirstLoginActivity.b;
	if (i == 1) {
	FirstLoginActivity.edt_Pwd.setText("");
	// DefaultUserLogin.edt_Pwd.setText("");
	finish();
	}
	super.onStop();
	}

	final ViewBinder VIEW_BINDER = new ViewBinder() {
	@TargetApi(3)
	public boolean setViewValue(View view, Cursor cursor, int columnIndex) {
	/*
	 * Format the date
	 */
	if (view.getId() == R.id.text_date) {
	String time = cursor.getString(cursor
	.getColumnIndex(EntryData.C_NEWDATE));
	// int format = DateUtils.FORMAT_SHOW_DATE|
	// DateUtils.FORMAT_ABBREV_ALL;
	// String date = DateUtils.formatDateTime(getBaseContext(),
	// time,format);
	// System.out.println(" date is   "+date);
	if (time != null && !time.equalsIgnoreCase("")) {
	time = time.substring(0, time.length() - 4);
	((TextView) view).setText(time);
	}
	return true;
	}

	if (view.getId() == R.id.media_button) {
	String path = cursor.getString(cursor
	.getColumnIndex(EntryData.C_FILE));
	if (path != null) {
	Bitmap b = BitmapFactory.decodeFile(path);
	b = Bitmap.createScaledBitmap(b, 22, 22, true);
	((ImageView) view).setImageBitmap(b);
	return true;
	}

}
	return false;
	}
	};// View Binder

	/*
	 * public void onConfigurationChanged(Configuration newConfig) {

* super.onConfigurationChanged(newConfig);
	 * 
	 * // Checks the orientation of the screen if (newConfig.orientation ==
	 * Configuration.ORIENTATION_LANDSCAPE) { // Toast.makeText(this,
	 * "landscape", Toast.LENGTH_SHORT).show(); //finish(); Intent i=new
	 * Intent(); i.setClass(getApplicationContext(), FirstLoginActivity.class);
	 * startActivity(i);
	 * 
	 * 
	 * //setContentView(R.layout.login_view); } // else if
	 * (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) // { //
	 * Toast.makeText(this, "portrait", Toast.LENGTH_SHORT).show(); // } }
	 */
	public void onDestroy() {
	super.onDestroy();
	}

	@SuppressWarnings("deprecation")
	public void onCreate(Bundle savedInstanceState) {
	try {
	super.onCreate(savedInstanceState);

	setContentView(R.layout.activity_main);
	app = (App) getApplication();
	setTheme(android.R.style.Theme_Light_NoTitleBar_Fullscreen);
	data = new EntryData(this);
	month = Calendar.getInstance();
	prefs = PreferenceManager.getDefaultSharedPreferences(this);

	Log.d("PASSWORD", prefs.getString("password", "PULAMEA"));

	final String[] FROM = { EntryData.C_ID, EntryData.C_TEXT,
	EntryData.C_FILE };
	final int[] TO = { R.id.text_date, R.id.text_entry,
	R.id.media_button };
	boolean calledFromCallendar = false;
	Bundle extras = getIntent().getExtras();
	if (extras != null && extras.containsKey("month")) {
	int day = extras.getInt("day");
	int month = extras.getInt("month");
	int year = extras.getInt("year");
	cursor = data.getCursor(day, month, year);
	calledFromCallendar = true;
	}

	else {
	String date = DateUtils.formatDateTime(this, NoteActivity.time,
	DateUtils.FORMAT_NO_MONTH_DAY
	| DateUtils.FORMAT_SHOW_YEAR);
	try {
	int yr = Integer.parseInt(date);
	cursor = data.getCursor(myCalendar.get(Calendar.MONTH) + 1,
	yr);
	} catch (Exception e) {
	// TODO: handle exception
	}
	}

	checkForPassword();

	list = (ListView) findViewById(R.id.list);
	list.setOnItemClickListener(new OnItemClickListener() {

	public void onItemClick(AdapterView<?> parent, View view,
	int position, long id) {
	Cursor a = (Cursor) list.getAdapter().getItem(position);
	long _id = a.getLong(a.getColumnIndex(EntryData.C_ID));
	String time = a.getString(a
	.getColumnIndex(EntryData.C_NEWDATE));
	Intent i = new Intent(getBaseContext(), NoteActivity.class);
	i.putExtra("ID", _id);
	i.putExtra("time", time);

startActivity(i);
	}

	});

	plus = (ImageButton) findViewById(R.id.plusButton);
	plus.setOnClickListener(this);

	settings = (ImageButton) findViewById(R.id.settingsButton);
	settings.setOnClickListener(this);

	adapter = new SimpleCursorAdapter(this, R.layout.row, cursor, FROM,
	TO);
	adapter.setViewBinder(VIEW_BINDER);
	list.setAdapter(adapter);
	adapter.notifyDataSetChanged();

	fwd = (ImageButton) findViewById(R.id.fwd);
	fwd.setOnClickListener(this);
	bck = (ImageButton) findViewById(R.id.back);

bck.setOnClickListener(this);
	monthText = (TextView) findViewById(R.id.textView1);
	empty = (TextView) findViewById(R.id.empty);
	monthText.setText(android.text.format.DateFormat.format("MMMM",
	month));
	// if (cursor.getCount() < 1)
	// empty.setText("No entries for"+ months[m.index]);
	//
	// else
	// empty.setText("");
	myIndex = myCalendar.get(Calendar.MONTH);

	calendarB = (TextView) findViewById(R.id.img_CalView);
	calendarB.setOnClickListener(this);

	} catch (Exception e) {
	e.printStackTrace();
	}
	}

	public void onClick(View v) {
	// TODO Auto-generated method stub
	if (v.getId() == R.id.plusButton) {
	try {
	Intent i = new Intent(getBaseContext(), NoteActivity.class);
	i.putExtra("plus", true);
	startActivity(i);
	} catch (NullPointerException e) {
	e.printStackTrace();
	}
	}
	if (v.getId() == R.id.settingsButton) {
	Intent i = new Intent(getBaseContext(), PrefsActivity.class);
	startActivity(i);
	}
	if (v.getId() == R.id.fwd) {
	if (myIndex < 11)
	myIndex++;
	
	cursor = data.getCursor(myIndex, 2012);
	
	if (cursor.getCount() < 1) {
	empty.setText("No entries for " + months[myIndex]);
	} else {
	empty.setText("");
	}
	adapter.changeCursor(cursor);
	monthText.setText(months[myIndex]);
	}
	if (v.getId() == R.id.back) {

if (myIndex > 0)
	myIndex--;
	
	cursor = data.getCursor(myIndex, 2012);
	
	if (cursor.getCount() < 1) {
	empty.setText("No entries for " + months[myIndex]);
	} else {
	empty.setText("");
	}
	adapter.changeCursor(cursor);
	monthText.setText(months[myIndex]);
	}
	if (v.getId() == R.id.img_CalView) {
	Intent i = new Intent(MainNew.this, CalendarView.class);
	startActivity(i);
	}

	}
}
