package lock.diary;

public class Enteries 
{
	public long _id;
	public String _text;
	public String _filepath;
	public String _date;
	
	public long getID(){
		return this._id;
	}
	
	// setting id
	public void setID(long id){
		this._id = id;
	}
	
	public String getText(){
		return this._text;
	}
	
	// setting id
	public void setText(String _text){
		this._text = _text;
	}
	
	public String getFilePath(){
		return this._filepath;
	}
	
	// setting id
	public void setFilePath(String filePath){
		this._filepath = filePath;
	}
	
	public String getDate(){
		return this._date;
	}
	
	// setting id
	public void setDate(String date){
		this._date = date;
	}
	
	
	
}
