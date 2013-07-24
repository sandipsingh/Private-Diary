package lock.diary;


	import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.Gallery.LayoutParams;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.ViewSwitcher;
	 
	public class Instructions extends Activity implements
	        AdapterView.OnItemSelectedListener, ViewSwitcher.ViewFactory {
	 
		View lastSelectedView=null;
		
		ImageView i1,i2,i3,i4;
	    @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        requestWindowFeature(Window.FEATURE_NO_TITLE);
	 
	        setContentView(R.layout.instructions);
	 
	        
	         i1=(ImageView)findViewById(R.id.imageView1);
	         i2=(ImageView)findViewById(R.id.imageView2);
	         i3=(ImageView)findViewById(R.id.imageView3);
	         i4=(ImageView)findViewById(R.id.imageView4);
	        
	 	   
	     //   mSwitcher = (ImageSwitcher) findViewById(R.id.switcher);
	   //     mSwitcher.setFactory(this);
	   //     mSwitcher.setInAnimation(AnimationUtils.loadAnimation(this,
	    //            android.R.anim.fade_in));
	    //    mSwitcher.setOutAnimation(AnimationUtils.loadAnimation(this,
	      //          android.R.anim.fade_out));
	 
	        Gallery g = (Gallery) findViewById(R.id.gallery);
	        g.setAdapter(new ImageAdapter(this));
	        g.setOnItemSelectedListener(this);
	        //To select the xSelected one -> 0 is the first.
	      
		}
	   
		
	 
	    public void onItemSelected(AdapterView<?> parent, View v, int position, long id) 
	    {
	    	
	    		  
	     //   mSwitcher.setImageResource(mImageIds[position]);
	        if(position==0)
	        {
	        	i1.setImageResource(R.drawable.small);
	        	i2.setImageResource(R.drawable.big);
	        	i3.setImageResource(R.drawable.small);
	        	i4.setImageResource(R.drawable.small);
	        	
	        }
	         if(position==1)
	        {
	        	 	i1.setImageResource(R.drawable.big);
	     	       i2.setImageResource(R.drawable.small);
	          	i3.setImageResource(R.drawable.small);
	        	i4.setImageResource(R.drawable.small);
	      
	        }
	         if(position==2)
	        {	
	        	 i1.setImageResource(R.drawable.small);
	        	 i2.setImageResource(R.drawable.small);
	             i3.setImageResource(R.drawable.big);       	
	             i4.setImageResource(R.drawable.small);
	        }
	          if(position==3)
	        {
	        		 i1.setImageResource(R.drawable.small);
		        	 i2.setImageResource(R.drawable.small);
		        	   	i3.setImageResource(R.drawable.small);
		             i4.setImageResource(R.drawable.big);
	        }
	          }
	    	
	    	
	    
	 
	    public void onNothingSelected(AdapterView<?> parent) {
	    }
	 
	    public View makeView() {
	        ImageView i = new ImageView(this);
	        i.setBackgroundColor(0xFF000000);
	        i.setScaleType(ImageView.ScaleType.FIT_XY);
	        i.setAdjustViewBounds(true);
	        i.setLayoutParams(new ImageSwitcher.LayoutParams(LayoutParams.MATCH_PARENT,
	                LayoutParams.MATCH_PARENT));
	        return i;
	    }
	 
	    private ImageSwitcher mSwitcher;
	 
	    public class ImageAdapter extends BaseAdapter {
	        public ImageAdapter(Context c) {
	            mContext = c;
	        }
	 
	        public int getCount() {
	            return mImageIds.length;
	        }
	 
	        public Object getItem(int position) { 
		        return position; 
		    } 
	 
	        public long getItemId(int position) { 
		        return position; 
		    } 
	        
	       
		    
		    
	 
	        public View getView(int position, View convertView, ViewGroup parent) {
	            ImageView i = new ImageView(mContext);
	          
	            i.setImageResource(mImageIds[position]);
	            i.setAdjustViewBounds(true);
//	            i.setLayoutParams(new Gallery.LayoutParams(
//	                    LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
//	            
	            return i;
	        }
	 
	        private Context mContext;
	 
	    }
	 
//	    private Integer[] mThumbIds = {
//	            R.drawable.sc1, R.drawable.sc2,R.drawable.sc3,R.drawable.sc4
//	            };
	 
	    private Integer[] mImageIds = {
	    		 R.drawable.sc1, R.drawable.sc2,R.drawable.sc3,R.drawable.sc4};
	
	}
