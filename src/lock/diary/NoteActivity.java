package lock.diary;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.format.DateUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class NoteActivity extends Activity {
	ArrayList<Integer> list = new ArrayList<Integer>();

	int left, right, top, bottom;
	LinedEditText editText; // edit text view
	TextView dateText, dayText; // date text

	String filePath;
	// Entry entry = null; // the actual entry object ref
	Entry entry;
ImageView homeButton;
	ImageButton  clearButton, settingsButton, shareButton,
	fileButton; // bottom row buttons
	public static long time = System.currentTimeMillis();
	Bitmap attachment;
	private static final int SELECT_PHOTO = 100;
	String updatedTime = "";
	ImageView datePicker;
	private int year;
	private int month;
	private String Month;
	private int day;
	static final int DATE_DIALOG_ID = 999;
ImageView btnSave,btnFont;

	final Context context=this;
	TextWatcher watcher = new TextWatcher() { // TextWatcher for autosave
	// feature

	public void onTextChanged(CharSequence s, int start, int before,
	int count) {


}

	public void beforeTextChanged(CharSequence s, int start, int count,
	int after) {

	}


public void afterTextChanged(Editable s) {
	if (System.currentTimeMillis() - time > 5000) {
	time = System.currentTimeMillis();
	Toast toast = Toast.makeText(getApplicationContext(),
	"Draft Saved", Toast.LENGTH_SHORT);
	toast.setGravity(Gravity.TOP | Gravity.CENTER, 0, 0);
	toast.show();
	}
	data.open();

entry.text = s.toString();
	data.updateEntry(entry.id, entry.text, null, updatedTime);
	data.close();

}
	};

	EntryData data = new EntryData(this);

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
	/*
	 * public void onStop() { super.onStop(); finish(); }
	 */

	public void onDestroy()
	{
	super.onDestroy();
	}
	
	public void onResume()
	{
		super.onResume();
		String font=FontPicker.font1;
		Typeface font_1 = Typeface.createFromAsset(getAssets(), font);  
		  editText.setTypeface(font_1);
	}

	public void onCreate(Bundle savedInstanceState) {
	// App app = (App) getApplication();
	long time = 0;
	super.onCreate(savedInstanceState);
	setContentView(R.layout.entry_view);

	setTheme(android.R.style.Theme_Light_NoTitleBar);

	Bundle extras = getIntent().getExtras();
btnSave=(ImageView)findViewById(R.id.btnSave);
	btnSave.setOnClickListener(new OnClickListener(){

		public void onClick(View arg0) {
			// TODO Auto-generated method stub
		
			
			String s1=editText.getText().toString();
			if(s1.length()>0)
			{
			data.open();
			entry.text = s1.toString();
				data.updateEntry(entry.id, entry.text, null, updatedTime);
				data.close();
				Toast.makeText(getApplicationContext(), "Entry Saved", Toast.LENGTH_LONG).show();}

		}});
	ImageView btnFont=(ImageView)findViewById(R.id.btnFont);
	btnFont.setOnClickListener(new OnClickListener(){

		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			System.out.println("Font Clicked");
			Intent i=new Intent(getApplicationContext(),FontPicker.class);
			startActivity(i);
		}});
	
	
	// If activity is started for editing
	if (extras != null && !extras.containsKey("plus")) {

long id = extras.getLong("ID");
	data.open();
	entry = data.getEntry(id);
	String latestTime = extras.getString("time");
	//convertToMills(latestTime);
	time = System.currentTimeMillis();

	updatedTime = latestTime;
	App.currentId = id;
	data.close();
	System.out.println("time is " + time);
	}

	// This is a new note
	else if (extras != null && extras.containsKey("plus")
	&& savedInstanceState == null) {
	updatedTime=getDateFromCurrenMills();
	
	data.open();
	entry = new Entry("", updatedTime);
	time = System.currentTimeMillis();
	// todoAS
	data.insert(entry);
	
	App.currentId = entry.id;

data.close();
	System.out.println("time is1   " + time);
	System.out.println(time);

}

	else {
	data.open();
	entry = data.getEntry(App.currentId);
	time = entry.id;
	App.currentId = time;
	data.close();
	System.out.println("time is2   " + time);
	}

	// FOR DATE PICKER
	addListenerOnDatePicker();

setCurrentDateOnView();
	
	 // Reading all contacts
        Log.d("Reading: ", "Reading all contacts..");
        data.open();
        List<Enteries> enteries = data.getAllEnteries();       
 
        for (Enteries cn : enteries) {
            String log = "Id: "+cn.getID()+" ,Text: " + cn.getText() + " ,File: " + cn.getFilePath()+" ,Date: "+cn.getDate();
                // Writing Contacts to log
        Log.d("Name: ", log);
        
        }        
        data.close();
	settingsButton = (ImageButton) findViewById(R.id.settingsButton);
	// ////////////////////////////////////////////////////////

// Text logic
	// ////////////////////////////////////////////////////////

	settingsButton.setOnClickListener(new OnClickListener() {

	public void onClick(View v) {
	Intent i = new Intent(getBaseContext(), PrefsActivity.class);
	startActivity(i);

	}
	});

	editText = (LinedEditText) findViewById(R.id.editable_text);

	editText.addTextChangedListener(watcher);
	editText.setText(data.getEntryText(entry.id));

	editText.setOnKeyListener(new View.OnKeyListener() 
	{
		public boolean onKey(View v, int keyCode, KeyEvent event) 
		{
		    switch(keyCode) 
		    {
		        case KeyEvent.KEYCODE_0:
		            //handle code for pressing 0
		            break;
		        case KeyEvent.KEYCODE_ENTER :
		            //handles code for pressing 1
		        	System.out.println("Enter Pressed");
		        //	Toast.makeText(getApplicationContext(), "enter", Toast.LENGTH_LONG).show();
		        default:
		            break;
		    }
			return false;
		}
		});
	// ////////////////////////////////////////////////////////
	// Date text logic
	// ////////////////////////////////////////////////////////

	// SimpleDateFormat curFormater = new SimpleDateFormat("dd/MM/yyyy");
	// String days = curFormater.format(day);

	String date = DateUtils.formatDateTime(this, time,
	DateUtils.FORMAT_NO_MONTH_DAY | DateUtils.FORMAT_SHOW_YEAR);
	String day = (DateUtils.formatDateTime(this, time,
	DateUtils.FORMAT_SHOW_DATE));

	// for(int i = 0; i < day.length(); i++){
	// if(day.charAt(i) == '/'){
	// list.add(i);
	// }
	// }

	System.out.println(day);
	char[] c_day = day.toCharArray();
	char[] realDay = null;

	for (int i = 0; i < c_day.length; i++) {
	if (c_day[i] == '/' && i == 1) {


realDay = new char[i + 1];

	int k = i + 1;
	for (int j = 0; j <= i; j++) {

	realDay[j] = c_day[k++];

	}
	realDay[i] = ' ';
	c_day = null;
	day = null;
	break;

	}
	if (c_day[i] == '/' && i == 2) {

	realDay = new char[i + 1];
	int k = i + 1;
	for (int j = 0; j < i; j++) {
	realDay[j] = c_day[k++];

	}
	realDay[i] = ' ';
	c_day = null;
	day = null;
	break;

	}
	}// for Ends

	Calendar c = Calendar.getInstance();
	try {

	if (realDay != null) {
	dateText.setText(String.format("%tB", c) + " "
	+ String.format("%tY", c));
	dayText.setText(new String(realDay));
	} else {
	if (!updatedTime.equalsIgnoreCase("")) {
	dateText.setText(updatedTime.substring(2,
	updatedTime.length()));
	dayText.setText(updatedTime.substring(0, 2));
	}else{
	dateText.setText(date);
	dayText.setText(day.substring(day.length()-2));
	}
	}
	} catch (NullPointerException e) {
	e.printStackTrace();
	}
	// ////////////////////////////////////////////////////////
	// Home button logic
	// ////////////////////////////////////////////////////////
	homeButton = (ImageView) findViewById(R.id.homeButton);
	homeButton.setOnClickListener(new OnClickListener() {

	public void onClick(View v) {
	String month=updatedTime.substring(3, 6); 
	String monthin=String.valueOf(setmonth(month));
	Intent i = new Intent(getBaseContext(), MainActivity.class);
	i.putExtra("MONTH", monthin);
	data.open();
	System.out.println("updatedTime"+updatedTime+"month"+month+"monthin"+monthin);
	// ensure we don't save empty entries
	/*	String aa= (String) dateText.getText();
	String bb=aa.substring(0, 3); //month name
	Log.d("MONTH NAME: ", bb);
	
	i.putExtra("MONTH_NAME", bb);
	*/
	if (entry.text.length() < 1) {
	data.deleteEntry(entry.id);
	}
	startActivity(i);
	data.close();
	
	}
	});

	// ////////////////////////////////////////////////////////
	// Clear button logic
	// ////////////////////////////////////////////////////////
	clearButton = (ImageButton) findViewById(R.id.plusButton);

	clearButton.setImageResource(R.drawable.cross);
	/*	clearButton.setOnClickListener(new OnClickListener() {

	public void onClick(View v) {
	Intent i = new Intent(getBaseContext(), MainActivity.class);
	data.open();
	data.deleteEntry(entry.id);
	startActivity(i);
	data.close();
	}
	});*/
	clearButton.setOnClickListener(new OnClickListener()
	{

	public void onClick(View v) 
	{
	// TODO Auto-generated method stub
	AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
	 	alertDialogBuilder.setTitle("Alert!!");
	 	alertDialogBuilder
	.setMessage("Do you want to delete this entry?")
	.setCancelable(false)
	.setPositiveButton("Yes",new DialogInterface.OnClickListener() {
	public void onClick(DialogInterface dialog,int id) {
	// if this button is clicked, close
	// current activity
	Intent i = new Intent(getBaseContext(), MainActivity.class);
	data.open();
	data.deleteEntry(entry.id);
	startActivity(i);
	data.close();
	//NoteActivity.this.finish();
	}
	  })
	.setNegativeButton("No",new DialogInterface.OnClickListener() {
	public void onClick(DialogInterface dialog,int id) {
	// if this button is clicked, just close
	// the dialog box and do nothing
	dialog.cancel();
	}
	});
	 
	// create alert dialog
	AlertDialog alertDialog = alertDialogBuilder.create();
	 
	// show it
	alertDialog.show();
	}
	
	});

	// ////////////////////////////////////////////////////////
	// Share button logic
	// ////////////////////////////////////////////////////////
	shareButton = (ImageButton) findViewById(R.id.shareButton);
	shareButton.setOnClickListener(new OnClickListener() {

		public void onClick(View v) {

			if (entry.text.length() > 1) {
				System.out.println("file path is "+"file://"+filePath);
				
				Intent i = new Intent(android.content.Intent.ACTION_SEND);
				// i.setType("text/plain");
				i.setType("image/*");
				i.putExtra(android.content.Intent.EXTRA_SUBJECT,"Private Diary");
				i.putExtra(android.content.Intent.EXTRA_TEXT, entry.text);
				i.putExtra(Intent.EXTRA_STREAM, Uri.parse("file://" + filePath));
				startActivity(Intent.createChooser(i, "Share via"));
				
			}

			else {
				Toast t = Toast.makeText(v.getContext()
						.getApplicationContext(), "Nothing to share",
						Toast.LENGTH_SHORT);
				t.show();
			}

		}
	});
	// ////////////////////////////////////////////////////////
	// Attachment button logic
	// ////////////////////////////////////////////////////////
	fileButton = (ImageButton) findViewById(R.id.fileButton);
	if (entry.filePath != null && entry.filePath.length() > 1) {
		attachment=setImageToImageView(entry.filePath);
		//attachment = BitmapFactory.decodeFile(filePath);
		Bitmap scaledAttachment = Bitmap.createScaledBitmap(attachment,40, 40, true);
		fileButton.setImageBitmap(scaledAttachment);
	}

	fileButton.setOnClickListener(new OnClickListener() {

		public void onClick(View v) {

			if (attachment != null) {
				AlertDialog.Builder alert = new AlertDialog.Builder(v
						.getContext());

				// Set an EditText view to get user input
				final ImageView input = new ImageView(v.getContext());
				alert.setView(input);
				input.setImageBitmap(attachment);
				LayoutParams params = new LayoutParams(
						200,
						200);
				input.setLayoutParams(params);
				alert.setPositiveButton("Ok",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,
									int whichButton) {
								//
							}
						});
				alert.setCancelable(false);
				alert.show();
			}

			else {
				Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);

				photoPickerIntent.setType("image/*");
				startActivityForResult(photoPickerIntent, SELECT_PHOTO);
			}

		}
	});

	}// OnCreate Ends

	public void setCurrentDateOnView() {
	// TODO Auto-generated method stub

	final Calendar c = Calendar.getInstance();
	year = c.get(Calendar.YEAR);
	month = c.get(Calendar.MONTH);
	day = c.get(Calendar.DAY_OF_MONTH);
	try {
	// set current date into textview
	dayText.setText(new StringBuilder().append(day));
	// set selected date into textview

	dateText.setText(new StringBuilder().append(month + 1).append(" ")
	.append(year));
	} catch (NullPointerException e) {
	e.printStackTrace();
	}
	// set current date into datepicker
	// dpResult.init(year, month, day, null);
	}// 400

	public void addListenerOnDatePicker() {
	datePicker = (ImageView) findViewById(R.id.datePicker);
	datePicker.setOnClickListener(new OnClickListener() {
	@SuppressWarnings("deprecation")
	public void onClick(View v) {
	// TODO Auto-generated method stub
	showDialog(DATE_DIALOG_ID);
	}
	});

	dateText = (TextView) findViewById(R.id.date_text);
	dateText.setOnClickListener(new OnClickListener() {
	@SuppressWarnings("deprecation")
	public void onClick(View v) {
	// TODO Auto-generated method stub
	showDialog(DATE_DIALOG_ID);
	}
	});

	dayText = (TextView) findViewById(R.id.day_text);
	dayText.setOnClickListener(new OnClickListener() {
	@SuppressWarnings("deprecation")
	public void onClick(View v) {
	// TODO Auto-generated method stub
	showDialog(DATE_DIALOG_ID);
	}
	});

	}

	protected Dialog onCreateDialog(int id) {
	switch (id) {
	case DATE_DIALOG_ID:
	// set date picker as current date
	return new DatePickerDialog(this, datePickerListener, year, month,
	day);
	}
	return null;
	}

	private DatePickerDialog.OnDateSetListener datePickerListener = new DatePickerDialog.OnDateSetListener() {
	// when dialog box is closed, below method will be called.
	public void onDateSet(DatePicker view, int year, int selectedMonth,
	int selectedDay) {
	// TODO Auto-generated method stub
	month = selectedMonth;
	day = selectedDay;
	dayText.setText(new StringBuilder().append(day));

	if (month == 0) {
	Month = "Jan";
	} else if (month == 1) {
	Month = "Feb";
	} else if (month == 2) {
	Month = "Mar";
	} else if (month == 3) {

Month = "Apr";
	} else if (month == 4) {
	Month = "May";
	} else if (month == 5) {
	Month = "Jun";
	} else if (month == 6) {
	Month = "Jul";
	} else if (month == 7) {
	Month = "Aug";
	} else if (month == 8) {
	Month = "Sept";
	} else if (month == 9) {
	Month = "Oct";
	} else if (month == 10) {
	Month = "Nov";
	} else if (month == 11) {
	Month = "Dec";
	}
	// set selected date into textview
	dateText.setText(new StringBuilder().append(Month).append(" ")
	.append(year));
	updatedTime = day + " " + Month + " " + year;
	// set selected date into datepicker also
	// dpResult.init(year, month, day, null);
	}
	};

	protected void onActivityResult(int requestCode, int resultCode,
	Intent imageReturnedIntent) {
	super.onActivityResult(requestCode, resultCode, imageReturnedIntent);

	switch (requestCode) {
	case SELECT_PHOTO:
	if (resultCode == RESULT_OK) {
	Uri selectedImage = imageReturnedIntent.getData();
	String[] filePathColumn = { MediaStore.Images.Media.DATA };

	Cursor cursor = getContentResolver().query(selectedImage,filePathColumn, null, null, null);
	cursor.moveToFirst();

	int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
    filePath = cursor.getString(columnIndex);
	cursor.close();

	data.updateEntry(entry.id, null, filePath, "");
	Log.d("CCC", filePath);
	attachment=setImageToImageView(filePath);
	//attachment = BitmapFactory.decodeFile(filePath);
	Bitmap scaledAttachment = Bitmap.createScaledBitmap(attachment,40, 40, true);
	fileButton.setImageBitmap(scaledAttachment);

	}
	}
	}

	
	public Bitmap setImageToImageView(String filePath) 
	{ 
	// Decode image size 
	BitmapFactory.Options o = new BitmapFactory.Options(); 
	o.inJustDecodeBounds = true; 
	BitmapFactory.decodeFile(filePath, o); 

	// The new size we want to scale to
	//   1024*4=  4096
	final int REQUIRED_SIZE = 1024; 

	// Find the correct scale value. It should be the power of 2. 
	int width_tmp = o.outWidth, height_tmp = o.outHeight; 
	int scale = 1; 
	while (true) 
	{ 
	if (width_tmp < REQUIRED_SIZE && height_tmp < REQUIRED_SIZE) 
	break; 
	width_tmp /= 4; 
	height_tmp /= 4; 
	scale *= 4; 
	} 

	// Decode with inSampleSize 
	BitmapFactory.Options o2 = new BitmapFactory.Options(); 
	o2.inSampleSize = scale; 
	Bitmap bitmap = BitmapFactory.decodeFile(filePath, o2); 
	return bitmap;

	}

	
	public long convertToMills(String date) {
	int year = Integer.valueOf(date.substring(date.length() - 4,date.length()));
	String date1 = date.substring(0, 2);
	int setdate = Integer.valueOf(date1.replace(" ", ""));
	int setmonth = setmonth(date);
	Calendar cal = Calendar.getInstance();
	cal.set(year, setmonth, setdate);

	Date resultdate = cal.getTime();
	long setthisdate = resultdate.getTime();
	// SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy");
	// String date = sdf.format(resultdate);

	return setthisdate;

	}

	int setmonth(String date) {
	String updateMonth = date.substring(date.indexOf(" ") + 1,

date.indexOf(" ") + 4);
	int newmonth = 0;
	if (updateMonth.equalsIgnoreCase("Jan")) {
	newmonth = 0;
	} else if (updateMonth.equalsIgnoreCase("Feb")) {
	newmonth = 1;
	} else if (updateMonth.equalsIgnoreCase("Mar")) {
	newmonth = 2;
	} else if (updateMonth.equalsIgnoreCase("Apr")) {

newmonth = 3;
	} else if (updateMonth.equalsIgnoreCase("May")) {
	newmonth = 4;
	} else if (updateMonth.equalsIgnoreCase("Jun")) {
	newmonth = 5;
	} else if (updateMonth.equalsIgnoreCase("Jul")) {

newmonth = 6;
	} else if (updateMonth.equalsIgnoreCase("Aug")) {
	newmonth = 7;
	} else if (updateMonth.equalsIgnoreCase("Sep")) {
	newmonth = 8;
	} else if (updateMonth.equalsIgnoreCase("Oct")) {

newmonth = 9;
	} else if (updateMonth.equalsIgnoreCase("Nov")) {
	newmonth = 10;
	} else if (updateMonth.equalsIgnoreCase("Dec")) {
	newmonth = 11;
	}
	return newmonth;

	}

	@Override
	protected void onPause() {
	data.open();
	if (entry.text.length() < 1) {
	data.deleteEntry(entry.id);
	}
	data.close();
	super.onPause();
	}

	/*public boolean onKeyDown(int keyCode, KeyEvent event) {
	// TODO Auto-generated method stub
	startActivity(new Intent(NoteActivity.this, CalendarView.class));
	finish();
	return super.onKeyDown(keyCode, event);
	}*/
	


	
	public boolean onKeyDown(int keyCode, KeyEvent event) 
	{
	if (keyCode == KeyEvent.KEYCODE_HOME) 
	{
	finish();
	return true;
	}
	return super.onKeyDown(keyCode, event);
	}


	// TOD// @Override
	// protected void onStop() {
	// // TODO Auto-generated method stub
	// finish();
	// super.onStop();
	// }

	

public String getDateFromCurrenMills(){

	// long yourmilliseconds = 1119193190;
	// SimpleDateFormat sdf = new SimpleDateFormat("MMM dd,yyyy HH:mm");
	SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
	Date resultdate = new Date(Long.valueOf(System.currentTimeMillis()));
	String date = sdf.format(resultdate);
	//cal
	String currentDate=date.substring(3, 5);
	String currentMonth=date.substring(0, 2);
	String currentYear=date.substring(6, 10);
	currentDate=currentDate.replace("0", "");
	String saveDate=currentDate+" "+getCurrentMonth(currentMonth)+" "+currentYear;
	
	return saveDate; 

	}
	public String getCurrentMonth(String currentmonth){
	
	String charMonth = null;
	if (currentmonth.equalsIgnoreCase("01")) {
	charMonth = "Jan";
	} else if (currentmonth.equalsIgnoreCase("02")) {
	charMonth = "Feb";
	} else if (currentmonth.equalsIgnoreCase("03")) {
	charMonth = "Mar";
	} else if (currentmonth.equalsIgnoreCase("04")) {
	charMonth = "Apr";
	} else if (currentmonth.equalsIgnoreCase("05")) {
	charMonth = "May";
	} else if (currentmonth.equalsIgnoreCase("06")) {
	charMonth = "Jun";
	} else if (currentmonth.equalsIgnoreCase("07")) {
	charMonth = "Jul";
	} else if (currentmonth.equalsIgnoreCase("08")) {
	charMonth = "Aug";
	} else if (currentmonth.equalsIgnoreCase("09")) {
	charMonth = "Sept";
	} else if (currentmonth.equalsIgnoreCase("10")) {
	charMonth = "Oct";
	} else if (currentmonth.equalsIgnoreCase("11")) {
	charMonth = "Nov";
	} else if (currentmonth.equalsIgnoreCase("12")) {
	charMonth = "Dec";
	}
	return charMonth;
	
	}
}


