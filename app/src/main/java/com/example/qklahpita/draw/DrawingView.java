package com.example.qklahpita.draw;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.nfc.Tag;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import static android.content.ContentValues.TAG;

/**
 * Created by admin on 2/1/2018.
 */

public class DrawingView extends View{
    private static final String TAG = "DrawingView";
    private Canvas canvas;
    private Path path;
    private Paint paint;

    private Bitmap bitmap;

    public DrawingView(Context context) {
        super(context);

        canvas = new Canvas();
        path = new Path();

        paint = new Paint();
        paint.setColor(DrawActivity.currentColor);
        paint.setStrokeWidth(DrawActivity.currentSize);
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.STROKE);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        bitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        bitmap.eraseColor(Color.WHITE);
        canvas = new Canvas(bitmap);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawBitmap(bitmap,0,0,paint);
        canvas.drawPath(path,paint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event){

        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:{
                path.moveTo(event.getX(), event.getY());
                paint.setColor(DrawActivity.currentColor);
                paint.setStrokeWidth(DrawActivity.currentSize);
                break;
            }
            case MotionEvent.ACTION_MOVE:{
                path.lineTo(event.getX(), event.getY());
                break;
            }
            case MotionEvent.ACTION_UP: {

                canvas.drawPath(path,paint);
                path.reset();
                break;
            }
        }
        invalidate();

        return true;

    }
}
