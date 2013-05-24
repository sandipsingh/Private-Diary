package lock.diary;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class EntryData {

	public static final String DB_NAME = "private_diary";
	public static final int DB_VERSION = 4;
	public static final String TABLE_ENTRY = "entries";
	public static final String C_ID = "_id";
	public static final String C_FILE = "_file";
	public static final String C_TEXT = "_text";
	public static final String C_NEWDATE = "_NEWDATE";
	private static final String GET_ALL_ORDER_BY = C_ID + " DESC";

	Context context;
	DBHelper dbHelper;
	SQLiteDatabase db;

	public EntryData(Context context) {
		this.context = context;
		dbHelper = new DBHelper();

	}

	// ---opens the database---
		public EntryData open() throws SQLException {
			db = dbHelper.getWritableDatabase();
			return this;
		}

		// ---closes the database---
		public void close() {
			dbHelper.close();
		}
		
	public void insert(Entry entry) {

		db = dbHelper.getWritableDatabase();

		ContentValues values = new ContentValues();

		values.put(C_ID, entry.id);
		values.put(C_FILE, entry.filePath);
		values.put(C_TEXT, entry.text);
		values.put(C_NEWDATE, entry.newDate);

		db.insert(TABLE_ENTRY, null, values);

	}

	// Getting All Contacts
	public List<Enteries> getAllEnteries() {
		List<Enteries> contactList = new ArrayList<Enteries>();
		// Select All Query
		String selectQuery = "SELECT  * FROM " + TABLE_ENTRY;

	//	SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);

		// looping through all rows and adding to list
		if (cursor.moveToFirst()) {
			do {
				Enteries contact = new Enteries();
				contact.setID(Long.parseLong(cursor.getString(0)));
				contact.setText(cursor.getString(1));
				contact.setFilePath(cursor.getString(2));
				contact.setDate(cursor.getString(3));
				// Adding contact to list
				contactList.add(contact);
			} while (cursor.moveToNext());
		}

		// return contact list
		return contactList;
	}

	public Cursor getCursor() 
	{
		db = dbHelper.getReadableDatabase();
		return db.query(TABLE_ENTRY, null, null, null, null, null, GET_ALL_ORDER_BY);
	}

	public Cursor getCursor(int month, int year) 
	{
		String Currentmonth=getMonthName(month);
		Calendar c = Calendar.getInstance();
		// year, month, day, hourOfDay, minute
		c.set(year, month, 1, 1, 1);
		long start = c.getTimeInMillis();

		Log.d("DB", "start :" + start);

		if (month < 11)
			c.set(year, month + 1, 1, 1, 1);

		else
			c.set(year + 1, 1, 1, 1, 1);

		long end = c.getTimeInMillis();

		Log.d("DB", "end : " + end);

		db = dbHelper.getReadableDatabase();

		String args = "_id > " + start + " and " + "_id < " + end;
		String newargs="_NEWDATE like %"+Currentmonth+"%";
		return db.rawQuery("Select * from entries where _NEWDATE like '%"+Currentmonth+"%'", null);
		//return db.query(TABLE, null, newargs, null, null, null, GET_ALL_ORDER_BY);

	}

	public Cursor getCursor(int day, int month, int year) 
	{
		Calendar c = Calendar.getInstance();
		// year, month, day, hourOfDay, minute
		c.set(year, month, day, 0, 0);
		long start = c.getTimeInMillis();

		// if (month < 11)
		c.set(year, month, day + 1, 1, 1);

		// else c.set(year + 1, 1, 1, 1, 1);

		long end = c.getTimeInMillis();

		db = dbHelper.getReadableDatabase();

		String args = "_id > " + start + " and " + "_id < " + end;
		

		return db.query(TABLE_ENTRY, null, args, null, null, null, GET_ALL_ORDER_BY);

	}
	
	public boolean isHasNotes(int day, int month, int year) 
	{
		boolean ishasnotes=false;
		String date=String.valueOf(day+" "+getMonthName(month)+" "+year);
		String[] arg=new String[1];
		arg[0]=date;
		Cursor cursor=db.rawQuery("Select * from entries where _NEWDATE=?", arg);
		if(cursor!=null && cursor.getCount()>0){
			ishasnotes=true;
		}
		return ishasnotes;
	}

	public int deleteEntry(long id) 
	{
		db = dbHelper.getWritableDatabase();
		String strFilter = "_id=" + id;
		return db.delete(TABLE_ENTRY, strFilter, null);
	}

	public int updateEntry(long id, String newText, String file, String newDate) 
	{
		db = dbHelper.getWritableDatabase();
		ContentValues args = new ContentValues();
		if (newText != null)
			args.put(C_TEXT, newText);
		if (file != null)
			args.put(C_FILE, file);
		if (newDate != null)
			args.put(C_NEWDATE, newDate);
		String strFilter = "_id=" + id;
		int rez = db.update(TABLE_ENTRY, args, strFilter, null);
		return rez;
	}

	public Entry getEntry(long id) 
	{
		db = dbHelper.getReadableDatabase();
		Entry e = null;
		Cursor c = null;
		c = db.rawQuery("select * from " + TABLE_ENTRY + " where " + C_ID + " = "
				+ id, null);

		// if cursor is null return a null entry. must be checked when called.
		if (c == null) {
			db.close();

			return e;
		}

		if (c.moveToFirst()) {
			String text = c.getString(c.getColumnIndex(C_TEXT));
			// todoAS
			e = new Entry(text, "");
			e.filePath = c.getString(c.getColumnIndex(C_FILE));
			e.id = id;
		}

		else {
			return null;
		}

		db.close();
		return e;
	}

	public String getEntryText(long id) {
		db = dbHelper.getReadableDatabase();
		Entry q = null;
		Cursor c = null;
		c = db.rawQuery("select * from " + TABLE_ENTRY + " where " + C_ID + " = "
				+ id, null);

		// if cursor is null return a null entry. must be checked when called.
		if (c == null) {
			db.close();
			return null;
		}

		if (c.moveToFirst()) {
			return c.getString(c.getColumnIndex(C_TEXT));
		}

		db.close();
		return null;

	}

	class DBHelper extends SQLiteOpenHelper {

		public DBHelper() {
			super(context, DB_NAME, null, DB_VERSION);
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			String sql = String.format("create table %s"
					+ "(%s int primary key, %s text, %s text,%s text)", TABLE_ENTRY,
					C_ID, C_TEXT, C_FILE, C_NEWDATE);

			Log.d("onCreate", sql);

			db.execSQL(sql);

		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			
		//	db.execSQL("DROP TABLE IF EXISTS " + TABLE_ENTRY);

			// Create tables again
		//	onCreate(db);
		}

	}

	public String getMonthName(int month) {
		String Month = "";
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
		return Month;

	}
}
