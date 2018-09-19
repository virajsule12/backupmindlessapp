package virajsule.com.game;

import android.media.Image;
import android.os.CountDownTimer;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.GestureDetector;

import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements GestureDetector.OnGestureListener {

    private int lives = 3;
    private int score =0;
    private boolean leftOn,rightOn,upOn,downOn,incorrectSwipe;

    GestureDetector gestureDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        gestureDetector = new GestureDetector(MainActivity.this, MainActivity.this);

        handleStart();
    }

    public void handleStart(){
        Button start = (Button) findViewById(R.id.startbtn);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new CountDownTimer(1000, 100) {

                    public void onTick(long millisUntilFinished) {

                    }

                    public void onFinish() {
                        doArrows();

                    }
                }.start();

            }
        });
    }

    public void doArrows(){
        final int currentscore=score;
        final int currentlives=lives;
        final ImageView up = (ImageView) findViewById(R.id.upimg);
        final ImageView left = (ImageView) findViewById(R.id.leftimg);
        final ImageView right = (ImageView) findViewById(R.id.rightimg);
        final ImageView down = (ImageView) findViewById(R.id.downimg);

            int rand = (int)Math.floor(Math.random()*4);
            if (rand==0){
                leftOn=true;
                left.setVisibility(View.VISIBLE);

            }
            else if(rand==1){
                rightOn=true;
                right.setVisibility(View.VISIBLE);
            }
            else if(rand==2){
                upOn=true;
                up.setVisibility(View.VISIBLE);
            }
            else {
                downOn=true;
                down.setVisibility(View.VISIBLE);
            }
            new CountDownTimer(700, 100) {

                public void onTick(long millisUntilFinished) {

                }

                public void onFinish() {
                    leftOn=false;
                    rightOn=false;
                    downOn=false;
                    upOn=false;
                    left.setVisibility(View.INVISIBLE);
                    right.setVisibility(View.INVISIBLE);
                    down.setVisibility(View.INVISIBLE);
                    up.setVisibility(View.INVISIBLE);
//                    if (currentlives==lives && currentscore==score && !incorrectSwipe){
//                        lives--;
//                    }
                    updateText(lives,score);
                    int time = (int) Math.floor(Math.random()*3000)+1000;
                    new CountDownTimer(time, 100) {

                        public void onTick(long millisUntilFinished) {

                        }

                        public void onFinish() {

                            if (lives>0){
                                doArrows();
                            }

                        }
                    }.start();


                }
            }.start();



    }


    @Override
    public boolean onFling(MotionEvent motionEvent1, MotionEvent motionEvent2, float X, float Y) {

        if (motionEvent1.getX() - motionEvent2.getX() > 50) {
//            Toast.makeText(MainActivity.this, "You Swiped Left!", Toast.LENGTH_LONG).show();
            if (leftOn){
                score++;
            }
            else{
                lives--;
                incorrectSwipe=true;
            }
            updateText(lives,score);
            return true;
        }

        if (motionEvent2.getX() - motionEvent1.getX() > 50) {
//            Toast.makeText(MainActivity.this, "You Swiped Right!", Toast.LENGTH_LONG).show();
            if (rightOn){
                score++;
            }
            else{
                lives--;
                incorrectSwipe=true;
            }
            updateText(lives,score);
            return true;
        }
        if (motionEvent2.getY() - motionEvent1.getY() > 50) {
//            Toast.makeText(MainActivity.this, "You Swiped Down!", Toast.LENGTH_LONG).show();
            if (downOn){
                score++;
            }
            else{
                lives--;
                incorrectSwipe=true;
            }
            updateText(lives,score);
            return true;
        }
        if (motionEvent2.getY() - motionEvent1.getY() < 50) {
//            Toast.makeText(MainActivity.this, "You Swiped Up!", Toast.LENGTH_LONG).show();
            if (upOn){
                score++;
            }
            else{
                lives--;
                incorrectSwipe=true;
            }
            updateText(lives,score);
            return true;
        }
        return true;
    }

    public void updateText(int l, int s){
        TextView score = (TextView) findViewById(R.id.scoretxt);
        TextView lives = (TextView) findViewById(R.id.livestxt);
        score.setText("Score: " + s);
        if (l==0){
            lives.setText("GAME OVER!");
        }
        else{
            lives.setText("Lives: " + l);
        }


    }

    @Override
    public void onLongPress(MotionEvent arg0) {

    }

    @Override
    public boolean onScroll(MotionEvent arg0, MotionEvent arg1, float arg2, float arg3) {

        return false;
    }

    @Override
    public void onShowPress(MotionEvent arg0) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent arg0) {

        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent motionEvent) {

        return gestureDetector.onTouchEvent(motionEvent);
    }

    @Override
    public boolean onDown(MotionEvent arg0) {
        //Toast.makeText(GameActivity.this, "You Swiped TEST!", Toast.LENGTH_LONG).show();
        //
//        final ImageView lever = (ImageView) findViewById(R.id.imageView);
//
//        final TextView text = (TextView)findViewById(R.id.text);
//
//
//        Animation rotate = new RotateAnimation(0.0f, 90.0f,
//                Animation.RELATIVE_TO_SELF, .0f, Animation.RELATIVE_TO_PARENT,
//                .5f);
//        rotate.setRepeatCount(0);
//        rotate.setDuration(500);
//        rotate.getFillBefore();
//        rotate.setFillAfter(true);
//        rotate.setInterpolator(new AccelerateDecelerateInterpolator());
//        lever.startAnimation(rotate);
//
//        new CountDownTimer(500, 1000) {
//
//            public void onTick(long millisUntilFinished) {
//                text.setText("seconds remaining: " + millisUntilFinished / 1000);
//            }
//
//            public void onFinish() {
//                text.setText("done!");
//
//                Animation rotate = new RotateAnimation(90.0f, 0.0f,
//                        Animation.RELATIVE_TO_SELF, .0f, Animation.RELATIVE_TO_PARENT,
//                        .5f);
//                rotate.setRepeatCount(0);
//                rotate.setDuration(500);
//
//                rotate.setFillAfter(true);
//                rotate.setInterpolator(new AccelerateDecelerateInterpolator());
//                lever.startAnimation(rotate);
//
//            }
//        }.start();
        //
        return false;
    }


}
