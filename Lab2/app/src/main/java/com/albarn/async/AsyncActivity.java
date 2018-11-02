package com.albarn.async;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.os.AsyncTask;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import com.albarn.equation.R;

public class AsyncActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new GraphicsView(this));
    }

    //view for android logo display
    private class GraphicsView extends View{

        //last touch position
        private float touchX=0,touchY=0;
        //current position of logo
        private float x=0,y=0;
        //logo image
        private Bitmap myBitmap;
        GraphicsView(AsyncActivity context) {
            super(context);
            Log.d(getString(R.string.log_tag_calculation),"view created");

            //load image
            myBitmap= BitmapFactory.decodeResource(getApplicationContext().getResources(),R.drawable.ic_launcher);
            if(myBitmap==null){
                Log.e(getString(R.string.log_tag_calculation),"bitmap is null");
            }
            else {
                Log.d(getString(R.string.log_tag_calculation),"bitmap loaded");
            }
        }

        @Override
        protected void onDraw(Canvas canvas) {
            canvas.drawBitmap(myBitmap,x,y,null);
        }

        //task for logo moving
        private MoveAsyncTask move=null;

        //if user taps on the screen
        //we start new async task which moves logo to
        //this position in 1 second
        @Override
        public boolean onTouchEvent(MotionEvent event) {

            //get touch position
            touchX=event.getX();
            touchY=event.getY();

            //stop current task if its running
            if(move!=null&&!move.isCancelled()){
                move.cancel(true);
            }

            //create & execute
            move=new MoveAsyncTask();
            move.execute();
            Log.d(getString(R.string.log_tag_calculation),"on touch:"+touchX+":"+touchY);
            return true;
        }

        private class MoveAsyncTask extends AsyncTask<Void, Void, Float> {

            //parameter for line function
            float t=0;
            float X,Y;
            @Override
            protected void onCancelled() {
                Log.d(getString(R.string.log_tag_lifecycle),"MoveAsyncTask.onCancelled()");
                super.onCancelled();
            }

            @Override
            protected void onPreExecute() {
                Log.d(getString(R.string.log_tag_lifecycle),"MoveAsyncTask.onPreExecute()");
                super.onPreExecute();
            }

            @Override
            protected void onProgressUpdate(Void... values) {
                x=X;
                y=Y;
                invalidate();
                super.onProgressUpdate(values);
            }

            @Override
            protected void onPostExecute(Float aFloat) {
                super.onPostExecute(aFloat);
            }

            @Override
            protected Float doInBackground(Void... ignored) {
                Log.d(getString(R.string.log_tag_lifecycle),"MoveAsyncTask.doInBackground()");
                //get line functions constants
                float x1=x,y1=y;
                float ax=touchX-x, ay=touchY-y;
                X=x;Y=y;
                //params for animation
                int steps=100;
                int time=1000;
                for(;t<=1.0;t+=1.0/steps){
                    //check if we still alive
                    if(isCancelled()){
                        return t;
                    }

                    //set new point according to parameter value
                    X=x1+ax*t;
                    Y=y1+ay*t;
                    publishProgress();

                    //wait for a bit
                    SystemClock.sleep(time/steps);
                }
                return t;
            }
        }
    }
}
