package com.king.base;

import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;


/**
 * @author Jenly <a href="mailto:jenly1314@gmail.com">Jenly</a>
 * @date 2016/10/11
 */
public abstract class GestureActivity extends BaseActivity implements GestureDetector.OnGestureListener {

    private GestureDetector mGestureDetector;

    private int verticalMinDistance = 300;

    private int minVelocity = 0;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mGestureDetector = new GestureDetector(context,this);

    }


    @Override
    public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent1, float velocityX, float velocityY) {

        if(Math.abs(velocityX)>minVelocity){

            if(motionEvent.getX() - motionEvent1.getX()>verticalMinDistance){//left
                onLeftFling();
            }else if(motionEvent1.getX() - motionEvent.getX() > verticalMinDistance){//right
                if(!onRightFling()){
                    finish();
                }
            }

        }

        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return mGestureDetector.onTouchEvent(event);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        mGestureDetector.onTouchEvent(ev);
        return super.dispatchTouchEvent(ev);
    }


    @Override
    public boolean onDown(MotionEvent motionEvent) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent motionEvent) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent motionEvent) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent motionEvent) {

    }



    public abstract void onLeftFling();


    public abstract boolean onRightFling();


}
