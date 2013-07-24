package lock.diary;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class CalendarAdapter extends BaseAdapter  implements OnClickListener{
	static final int FIRST_DAY_OF_WEEK = 0; // Sunday = 0, Monday = 1
	private Context mContext;
	private java.util.Calendar month;
	private Calendar selectedDate;
	private ArrayList<Entry> items;
	EntryData data;
	LinearLayout lay;
	String date11;
	
	public CalendarAdapter(Context c, Calendar monthCalendar) {
		data = new EntryData(c);
		month = monthCalendar;
		selectedDate = (Calendar) monthCalendar.clone();
		mContext = c;
		month.set(Calendar.DAY_OF_MONTH, 1);
		this.items = new ArrayList<Entry>();
		refreshDays();
	}

	//
	// public void setItems(ArrayList<String> items) {
	// for(int i = 0;i != items.size();i++){
	// if(items.get(i).length()==1) {
	// items.set(i, "0" + items.get(i));
	// }
	// }
	// this.items = items;
	// }
	//
	public void setItems() {
		this.items = items;
	}

	public int getCount() {
		return days.length;
	}

	public Object getItem(int position) {
		return null;
	}

	public long getItemId(int position) {
		return 0;
	}

	public void onClick(View view) {
		String date111=(String) ((TextView)view).getText();
		 System.out.println("DATE PICKED IS this "+date111);
//		selectedDayMonthYearButton.setText("Selected: " + date_month_year);
//		Log.e("Selected date", date_month_year);
//		try {
//			Date parsedDate = dateFormatter.parse(date_month_year);
//			Log.d(tag, "Parsed Date: " + parsedDate.toString());
//
//		} catch (ParseException e) {
//			e.printStackTrace();
//		}
	}
	
	// create a new view for each item referenced by the Adapter
	public View getView(int position, View convertView, ViewGroup parent) {
		View v = convertView;
		TextView dayView;
		if (convertView == null) { // if it's not recycled, initialize some
									// attributes
			LayoutInflater vi = (LayoutInflater) mContext
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			v = vi.inflate(R.layout.calendar_item, null);
		}
		dayView = (TextView) v.findViewById(R.id.date);
		// date11=(String) dayView.getTag();
		//String date11=dayView.getText();
		 System.out.println("DATE PICKED IS "+date11);
		dayView.setOnClickListener(this);
		lay=(LinearLayout) v.findViewById(R.id.lay);
	//	lay.setOnClickListener(this);
		// disable empty days from the beginning
		if (days[position].equals("")) {
			dayView.setClickable(false);
			dayView.setFocusable(false);
		}

		else {
			// mark current day as focused
			if (month.get(Calendar.YEAR) == selectedDate.get(Calendar.YEAR)
					&& month.get(Calendar.MONTH) == selectedDate
							.get(Calendar.MONTH)
					&& days[position].equals(""
							+ selectedDate.get(Calendar.DAY_OF_MONTH))) {

				// v.setBackgroundResource(R.drawable.item_background_focused);
			} else {
				// v.setBackgroundResource(R.drawable.list_item_background);
			}
		}
		dayView.setText(days[position]);

		// create date string for comparison
		String date = days[position];

		if (date.length() == 1) {
			date = "0" + date;
		}
		
		
		String monthStr = "" + (month.get(Calendar.MONTH) + 1);
		if (monthStr.length() == 1) {
			monthStr = "0" + monthStr;
		}

		// show icon if date is not empty and it exists in the items array
		ImageView iw = (ImageView) v.findViewById(R.id.date_icon);
		if (days[position].length() > 0)
			if (data.getCursor(Integer.parseInt(days[position]),
					month.get(Calendar.MONTH), month.get(Calendar.YEAR))
					.getCount() > 0) {
				Log.d("s", "ss: " + "day " + Integer.parseInt(days[position])
						+ "month " + month.get(Calendar.MONTH) + "year "
						+ month.get(Calendar.YEAR));
				//iw.setVisibility(View.VISIBLE);
			} else {
				//iw.setVisibility(View.INVISIBLE);
			}
		if (days[position].length() > 0) {
			if (data.isHasNotes(Integer.parseInt(days[position]),
					month.get(Calendar.MONTH), month.get(Calendar.YEAR))) {
				iw.setVisibility(View.VISIBLE);
				dayView.setClickable(true);
			}else{
				iw.setVisibility(View.INVISIBLE);
				dayView.setClickable(false);
			}
		}
		return v;
	}

	public void refreshDays() {
		// clear items
		items.clear();

		int lastDay = month.getActualMaximum(Calendar.DAY_OF_MONTH);
		int firstDay = (int) month.get(Calendar.DAY_OF_WEEK);

		// figure size of the array
		if (firstDay == 1) {
			days = new String[lastDay + (FIRST_DAY_OF_WEEK * 6)];
		} else {
			days = new String[lastDay + firstDay - (FIRST_DAY_OF_WEEK + 1)];
		}

		int j = FIRST_DAY_OF_WEEK;

		// populate empty days before first real day
		if (firstDay > 1) {
			for (j = 0; j < firstDay - FIRST_DAY_OF_WEEK; j++) {
				days[j] = "";
			}
		} else {
			for (j = 0; j < FIRST_DAY_OF_WEEK * 6; j++) {
				days[j] = "";
			}
			j = FIRST_DAY_OF_WEEK * 6 + 1; // sunday => 1, monday => 7
		}

		// populate days
		int dayNumber = 1;
		for (int i = j - 1; i < days.length; i++) {
			days[i] = "" + dayNumber;
			dayNumber++;
		}
	}

	// references to our items
	public String[] days;

}
