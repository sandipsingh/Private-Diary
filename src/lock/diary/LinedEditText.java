package lock.diary;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.widget.EditText;

public class LinedEditText extends EditText 
{
	private Rect mRect;
	private Paint mPaint;

	// we need this constructor for LayoutInflater
	public LinedEditText(Context context, AttributeSet attrs)
	{
		super(context, attrs);
		mRect = new Rect();
		mPaint = new Paint();
		mPaint.setStyle(Paint.Style.STROKE);
		mPaint.setColor(0xFFa5a5a5);                    

	}

	@Override
	protected void onDraw(Canvas canvas) 
	{               
		super.onDraw(canvas);

		int count = getLineCount();
		Rect r = mRect;
		Paint paint = mPaint;

		int baseline = getLineBounds(0, r);

		for (int j = 0; j < count+10; j++) 
		{

			canvas.drawLine(r.left, baseline + (getLineHeight()) * j, r.right,
					baseline + (getLineHeight()) * j, paint);

		}                              

	}       

}