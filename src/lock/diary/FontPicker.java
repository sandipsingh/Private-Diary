package lock.diary;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class FontPicker extends Activity implements OnClickListener {
	
 public static String font1="weblysleekuisl.ttf";
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
 		 setContentView(R.layout.font);
 		 
 		

 		
 		 ImageView close=(ImageView)findViewById(R.id.imageView1);
 		 close.setOnClickListener(this);
 		
 		 TextView t2=(TextView)findViewById(R.id.textView2);
 		 Typeface font = Typeface.createFromAsset(getAssets(), "Chantelli_Antiqua.ttf");  
 		 t2.setTypeface(font); 
 		 t2.setOnClickListener(this);
 		
 		 TextView t3=(TextView)findViewById(R.id.textView3);
 		 Typeface font1 = Typeface.createFromAsset(getAssets(), "Aerovias_Brasil_NF.ttf");  
		 t3.setTypeface(font1); 
 		 t3.setOnClickListener(this);
 		 
 		 TextView t4=(TextView)findViewById(R.id.textView4);
 		 Typeface font2 = Typeface.createFromAsset(getAssets(), "18836_HELR45W.ttf");  
		 t4.setTypeface(font2); 
 		 t4.setOnClickListener(this);
 		 
 		 TextView t5=(TextView)findViewById(R.id.textView5);
 		 Typeface font3 = Typeface.createFromAsset(getAssets(), "arrivalsanddepartures.ttf");  
		 t5.setTypeface(font3); 
 		 t5.setOnClickListener(this);
 		 
 		 TextView t6=(TextView)findViewById(R.id.textView6);
 		 Typeface font4 = Typeface.createFromAsset(getAssets(), "Bahij Jalal-Regular.ttf");  
		 t6.setTypeface(font4); 
 		 t6.setOnClickListener(this);
 		 
 		 TextView t7=(TextView)findViewById(R.id.textView7);
		 Typeface font5 = Typeface.createFromAsset(getAssets(), "Drakalligro Original.ttf");  
		 t7.setTypeface(font5); 
		 t7.setOnClickListener(this);
		 
		 TextView t8=(TextView)findViewById(R.id.textView8);
		 Typeface font6 = Typeface.createFromAsset(getAssets(), "ines.ttf");  
		 t8.setTypeface(font6); 
		 t8.setOnClickListener(this);
		 
		 TextView t9=(TextView)findViewById(R.id.textView9);
		 Typeface font7 = Typeface.createFromAsset(getAssets(), "Leo Arrow.ttf");  
		 t9.setTypeface(font7); 
		 t9.setOnClickListener(this);
		 
		 TextView t10=(TextView)findViewById(R.id.textView10);
		 Typeface font8 = Typeface.createFromAsset(getAssets(), "weblysleekuisl.ttf");  
		 t10.setTypeface(font8); 
		 t10.setOnClickListener(this);
		 
		 TextView t11=(TextView)findViewById(R.id.textView11);
		 Typeface font9 = Typeface.createFromAsset(getAssets(), "weblysleekuisli.ttf");  
		 t11.setTypeface(font9); 
		 t11.setOnClickListener(this);
	}

	public void onClick(View v) {
		// TODO Auto-generated method stub
		if(v.getId()==R.id.imageView1)
		{
			finish();
		}
		if(v.getId()==R.id.textView2)
		{
			font1="Chantelli_Antiqua.ttf";
			finish();
		}
		if(v.getId()==R.id.textView3)
		{
			font1="Aerovias_Brasil_NF.ttf";
			finish();
		}
		if(v.getId()==R.id.textView4)
		{
			font1="18836_HELR45W.ttf";
			finish();
		}
		if(v.getId()==R.id.textView5)
		{
			font1="arrivalsanddepartures.ttf";
			finish();
		}	
		if(v.getId()==R.id.textView6)
		{
			font1="Bahij Jalal-Regular.ttf";
			finish();
		}
		if(v.getId()==R.id.textView7)
		{
			font1="Drakalligro Original.ttf";
			finish();
		}
		if(v.getId()==R.id.textView8)
		{
			font1="ines.ttf";
			finish();
		}
		if(v.getId()==R.id.textView9)
		{
			font1="Leo Arrow.ttf";
			finish();
		}	
		if(v.getId()==R.id.textView10)
		{
			font1="weblysleekuisl.ttf";
			finish();
		}
		if(v.getId()==R.id.textView11)
		{
			font1="weblysleekuisli.ttf";
			finish();
		}
	}
 
}
