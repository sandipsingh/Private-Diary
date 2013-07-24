package lock.diary;

import android.os.Bundle;
import android.preference.PreferenceManager;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;
//import com.apperhand.device.android.AndroidSDKProvider;

public class FirstLoginActivity extends Activity
{
	public static EditText edt_Ans; 
	EditText edt_Pwd1, edt_Pwd2;
	Spinner spin_Que;
	ImageView btn_Go;
	public static int b=0;
	SharedPreferences prefs;        
	
	//login_view components
	TextView qtext;
	public static EditText edt_Pwd;
	EditText anstext;
	ImageView btn_Ansok,btn_login_Go,btn_fgt_Pwd;

	
/*    @Override
	 public boolean onKeyDown(int keyCode, KeyEvent event)   
	 {
	      if(keyCode == KeyEvent.KEYCODE_HOME)            
	      {
	           finish();
	           return true;
	      }

	      return super.onKeyDown(keyCode, event);
	 }*/
	
/*	public void onConfigurationChanged(Configuration newConfig) 
	{
	    super.onConfigurationChanged(newConfig);

	    // Checks the orientation of the screen
	    if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) 
	    {
	       // Toast.makeText(this, "landscape", Toast.LENGTH_SHORT).show();
	    	//finish();
	    	Intent i=new Intent();
	    	i.setClass(getApplicationContext(), FirstLoginActivity.class);
	    	startActivity(i);
	    //	setContentView(R.layout.login_view);
	    } 
	  //  else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT)
	  //  {
	 //       Toast.makeText(this, "portrait", Toast.LENGTH_SHORT).show();
	  //  }
	  }*/
	
	public boolean onKeyDown(int keyCode, KeyEvent event) 
	{
        if (event.getAction() == KeyEvent.ACTION_DOWN) 
        {
            switch (keyCode) 
            {
            case KeyEvent.KEYCODE_HOME:
                System.out.println("Home clicked....");
            return true;

            }
        }
        return super.onKeyDown(keyCode, event);
    }
	
	public void onStop()
	{
		super.onStop();
		finish();
	}
	
	public void onDestroy()
	{
		super.onDestroy();
	}
	
	
    @Override
    public void onCreate(Bundle savedInstanceState) 
    {
    //	AndroidSDKProvider.initSDK(this);
   	//AndroidSDKProvider.initSDK(this);
        super.onCreate(savedInstanceState);
       
        requestWindowFeature(Window.FEATURE_NO_TITLE);
		prefs = PreferenceManager.getDefaultSharedPreferences(this);
		
		if (prefs.getBoolean("firstrun", true)) 
		{
		setTheme(android.R.style.Theme_Light_NoTitleBar);
		setContentView(R.layout.first_login);
		setTheme(android.R.style.Theme_Light_NoTitleBar);

		edt_Pwd1 = (EditText) findViewById(R.id.edt_Pwd1);
		edt_Pwd1.addTextChangedListener(new TextWatcher() 
		{
			   public void afterTextChanged(Editable s) {
			   }
			 
			   public void beforeTextChanged(CharSequence s, int start, 
			     int count, int after) {
			   }
			 
			   public void onTextChanged(CharSequence s, int start, 
			     int before, int count) {
				  
				   if(s.length()==0)
		           {
		                 return;
		           }
		           else
		           {
		                  // your code here
		        	   String _pwd=edt_Pwd1.getText().toString();
						  System.out.println(_pwd);
						  char first_letter=_pwd.charAt(0);
						  if(first_letter=='A' ||first_letter=='B' ||first_letter=='C' ||first_letter=='D' ||first_letter=='E' ||first_letter=='F'
								  ||first_letter=='G' ||first_letter=='H' ||first_letter=='I' ||first_letter=='J' ||first_letter=='K' 
								  ||first_letter=='L' ||first_letter=='M' ||first_letter=='N' ||first_letter=='O' ||first_letter=='P' 
								  ||first_letter=='Q' ||first_letter=='R' ||first_letter=='S' ||first_letter=='T' ||first_letter=='U' 
								  ||first_letter=='V' ||first_letter=='W' ||first_letter=='X' ||first_letter=='Y' ||
								   first_letter=='Z' )
						  {
							 // System.out.println("caps");
								Toast.makeText(getApplicationContext(), "Capital letters entered", Toast.LENGTH_LONG).show();   
						  }
				
		           }
				 }
		});
		
		edt_Pwd2 = (EditText) findViewById(R.id.edt_Pwd2);
		edt_Pwd2.addTextChangedListener(new TextWatcher() 
		{
			   public void afterTextChanged(Editable s) {
			   }
			 
			   public void beforeTextChanged(CharSequence s, int start, 
			     int count, int after) {
			   }
			 
			   public void onTextChanged(CharSequence s, int start, 
			     int before, int count) {
				  
				   if(s.length()==0)
		           {
		                 return;
		           }
		           else
		           {
		                  // your code here
		        	   String _pwd=edt_Pwd2.getText().toString();
						  System.out.println(_pwd);
						  char first_letter=_pwd.charAt(0);
						  if(first_letter=='A' ||first_letter=='B' ||first_letter=='C' ||first_letter=='D' ||first_letter=='E' ||first_letter=='F'
								  ||first_letter=='G' ||first_letter=='H' ||first_letter=='I' ||first_letter=='J' ||first_letter=='K' 
								  ||first_letter=='L' ||first_letter=='M' ||first_letter=='N' ||first_letter=='O' ||first_letter=='P' 
								  ||first_letter=='Q' ||first_letter=='R' ||first_letter=='S' ||first_letter=='T' ||first_letter=='U' 
								  ||first_letter=='V' ||first_letter=='W' ||first_letter=='X' ||first_letter=='Y' ||
								   first_letter=='Z' )
						  {
							 // System.out.println("caps");
								Toast.makeText(getApplicationContext(), "Capital letters entered", Toast.LENGTH_LONG).show();   
						  }
				
		           }
				 }
		});

		spin_Que = (Spinner) findViewById(R.id.spin_Que);
		btn_Go = (ImageView) findViewById(R.id.btn_Go);
		edt_Ans = (EditText) findViewById(R.id.edt_Ans);
		edt_Ans.addTextChangedListener(new TextWatcher() 
		{
			   public void afterTextChanged(Editable s) {
			   }
			 
			   public void beforeTextChanged(CharSequence s, int start, 
			     int count, int after) {
			   }
			 
			   public void onTextChanged(CharSequence s, int start, 
			     int before, int count) {
				  
				   if(s.length()==0)
		           {
		                 return;
		           }
		           else
		           {
		                  // your code here
		        	   String _pwd=edt_Ans.getText().toString();
						  System.out.println(_pwd);
						  char first_letter=_pwd.charAt(0);
						  if(first_letter=='A' ||first_letter=='B' ||first_letter=='C' ||first_letter=='D' ||first_letter=='E' ||first_letter=='F'
								  ||first_letter=='G' ||first_letter=='H' ||first_letter=='I' ||first_letter=='J' ||first_letter=='K' 
								  ||first_letter=='L' ||first_letter=='M' ||first_letter=='N' ||first_letter=='O' ||first_letter=='P' 
								  ||first_letter=='Q' ||first_letter=='R' ||first_letter=='S' ||first_letter=='T' ||first_letter=='U' 
								  ||first_letter=='V' ||first_letter=='W' ||first_letter=='X' ||first_letter=='Y' ||
								   first_letter=='Z' )
						  {
							 // System.out.println("caps");
								Toast.makeText(getApplicationContext(), "Capital letters entered", Toast.LENGTH_LONG).show();   
						  }
				
		           }
				 }
		});

		final SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.question_array,
						android.R.layout.simple_spinner_item);
		
		// Specify the layout to use when the list of choices appears
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spin_Que.setAdapter(adapter);

		spin_Que.setOnItemSelectedListener(new OnItemSelectedListener() 
		{
			public void onItemSelected(AdapterView<?> parent, View view,
					int pos, long id) 
			{
				prefs.edit().putString("q",	parent.getItemAtPosition(pos).toString()).commit();  
			}

			public void onNothingSelected(AdapterView<?> parent) {
				//

			}
		});
		
		edt_Pwd1.setVisibility(View.VISIBLE);
		
		btn_Go.setOnClickListener(new OnClickListener() 
		{
			public void onClick(View v) 
			{
				String check = edt_Pwd1.getText().toString();
				String check2 = edt_Pwd2.getText().toString();
				if (check.length() > 5 && check.equals(check2) && edt_Ans.getText().toString().length() > 0) 
				{
					prefs.edit().putString("password", check).commit();
					Intent i = new Intent(FirstLoginActivity.this,MainActivity.class);
					startActivity(i);
					prefs.edit().putBoolean("firstrun", false).commit();
					prefs.edit().putString("a", edt_Ans.getText().toString()).commit();
				} 
				else if(check.length() < 5) 
				{
					Toast.makeText(getBaseContext(),"Password too short, minimum 6 letters",Toast.LENGTH_SHORT).show();
				}
				
				else if(!check.equals(check2))
				{
					Toast.makeText(getBaseContext(),"Passwords did not match",Toast.LENGTH_SHORT).show();
				}
				
				else 
				{
					Toast.makeText(getBaseContext(),"Please enter a security answer",Toast.LENGTH_SHORT).show();
				}    
				
			}//onclk
		});//listnr	*/
		}//if Ends
		
		
		else 
		{
			b=1;
			setTheme(android.R.style.Theme_Light_NoTitleBar);
		
			setContentView(R.layout.login_view);
			setTheme(android.R.style.Theme_Light_NoTitleBar);

			btn_Ansok =(ImageView) findViewById(R.id.btn_Ansok);
			qtext = (TextView) findViewById(R.id.qtext);
			edt_Pwd = (EditText) findViewById(R.id.edt_Pwd);
			btn_login_Go = (ImageView) findViewById(R.id.btn_login_Go);
			btn_fgt_Pwd = (ImageView) findViewById(R.id.btn_fgt_Pwd);
			anstext = (EditText) findViewById(R.id.anstext);
			
			btn_Ansok.setVisibility(View.INVISIBLE);
			qtext.setVisibility(View.INVISIBLE);
			btn_Ansok.setVisibility(View.INVISIBLE);
			anstext.setVisibility(View.INVISIBLE);  
			
			btn_login_Go.setOnClickListener(new OnClickListener() 
			{
				public void onClick(View v) 
				{
				
					String check = edt_Pwd.getText().toString();
					String pass = prefs.getString("password", "");
					if (check.equals(pass)) 
					{
						Intent i = new Intent(FirstLoginActivity.this,CalendarView.class);
						startActivity(i);
					}
					else if(check.length()<6)
					{
						Toast.makeText(getBaseContext(), "password is less than six digit",Toast.LENGTH_SHORT).show();
					}
					else
					{          

						Toast.makeText(getBaseContext(), "Invalid password",Toast.LENGTH_SHORT).show();
					}
					
				}
			});

			btn_fgt_Pwd.setOnClickListener(new OnClickListener() 
			{
				public void onClick(View v) 
				{
					
					if (prefs.getString("q", "").length() > 1) 
					{
						qtext.setVisibility(View.VISIBLE);
						qtext.setText(prefs.getString("q", ""));
						anstext.setVisibility(View.VISIBLE);
						btn_Ansok.setVisibility(View.VISIBLE);
						btn_Ansok.setOnClickListener(new OnClickListener() 
						{
							public void onClick(View v)
							{
								if (anstext.getText().toString().equals(prefs.getString("a", ""))) 
								{
									qtext.setText("Your password is:  " + prefs.getString("password", ""));
									
									anstext.setVisibility(View.INVISIBLE);
									btn_Ansok.setVisibility(View.INVISIBLE);
								} 
								else 
								{
									Toast.makeText(getBaseContext(),"Invalid answer",Toast.LENGTH_SHORT).show();
								}
							}
						});

					}//if

					else 
					{
						Toast.makeText(getBaseContext(), "No question set",Toast.LENGTH_SHORT).show();
					}
					
				}
			});//lis
			
		}//else

    }
   
}
