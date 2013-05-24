package lock.diary;

import java.util.Random;

import android.graphics.Bitmap;

public class Entry {
	// Date is represented as the milliseconds passed since Jan 1 1970.
	public long dateAdded;
	public String newDate;
	public String text;

	public String filePath;
	public Bitmap file;

	public long id;
	Random random = new Random();

	public Entry(String pText,String newDate) {

		// Sets the id to current time. Simplifies database storage and and
		// access
		id = System.currentTimeMillis();

		text = pText;
		dateAdded = System.currentTimeMillis();
		this.newDate=newDate;
	}
}