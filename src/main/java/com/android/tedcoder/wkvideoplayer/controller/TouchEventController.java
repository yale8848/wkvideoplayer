package com.android.tedcoder.wkvideoplayer.controller;

import android.app.Activity;
import android.content.Context;
import android.graphics.Point;
import android.media.AudioManager;
import android.util.DisplayMetrics;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by Yale on 2016/12/22.
 */

public class TouchEventController {


    private Context mContext;
    private int mScreenWidth = 0;
    private int mScreenHeight = 0;
    private Point mPointDown = null;

    private int mStartGap = 0;

    private int mLastDistanceY = 0;
   // private GestureDetectorCompat mGestureDetectorCompat;
    private AudioManager mAudioManager;
    private TouchCallBack mTouchCallBack;

    private int mMaxVoice = 0;
    private int mVoiceGap = 0;
    private int mViewHeight = 0;

    private int mSumY = 0;
    private int mLastY = 0;

    public TouchEventController(Context context, final View wrapper){
        mContext= context;
        initScreenInfo();
        wrapper.post(new Runnable() {
            @Override
            public void run() {
                mViewHeight = wrapper.getHeight();
                mVoiceGap = mViewHeight/100;
            }
        });
      //  mGestureDetectorCompat = new GestureDetectorCompat(context,mOnGestureListener );

        mAudioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
        mMaxVoice = mAudioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);


    }
    private void initScreenInfo(){
        DisplayMetrics dm = new DisplayMetrics();
        ((Activity)mContext).getWindowManager().getDefaultDisplay().getMetrics(dm);
        mScreenHeight = dm.heightPixels;
        mScreenWidth = dm.widthPixels;
    }
    public void setTouchCallBack(TouchCallBack touchCallBack){
        mTouchCallBack = touchCallBack;
    }
    private GestureDetector.OnGestureListener mOnGestureListener = new GestureDetector.OnGestureListener(){
        @Override
        public boolean onDown(MotionEvent e) {
         //   Log.d("gesture1","onDown"+e.getAction());
            mPointDown = new Point((int)e.getX(),(int)e.getY());
            mSumY = 0;
            return true;
        }

        @Override
        public void onShowPress(MotionEvent e) {

        }

        @Override
        public boolean onSingleTapUp(MotionEvent e) {
           // Log.d("gesture1","onSingleTapUp"+e.getAction());
            return false;
        }

        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {

            if (distanceY>0&&mLastY<0||distanceY<0&&mLastY>0){
                mSumY = 0;
            }

            if (mPointDown.x<mScreenWidth/2){

            }else{
                voiceHolder(distanceY);
            }
            mLastY = (int) distanceY;

           // Log.d("gesture1","oscroll e1 "+e1.getAction()+" e2 "+e2.getAction()+" distanceX " +distanceX+" distanceY "+distanceY);
            return false;
        }

        @Override
        public void onLongPress(MotionEvent e) {

        }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
           // Log.d("gesture1","onFling e1 "+e1.getAction()+" e2 "+e2.getAction()+" velocityX " +velocityX+" velocityY "+velocityY);
            return false;
        }
    };
/*    public boolean onTouchEvent(MotionEvent event){

        return  mGestureDetectorCompat.onTouchEvent(event);
    }*/

    private void voiceHolder( float distanceY){

        mSumY += distanceY;
        float b = ((float)mSumY/mViewHeight)*100;

        if (Math.abs(mSumY)>1){
            int current = mAudioManager.getStreamVolume(AudioManager.STREAM_MUSIC);

        }

    }
    private void lightnessHolder(int x, int y) {
    }


    public interface  TouchCallBack{
        void centerText(String text);
        void tap();
    }

}
