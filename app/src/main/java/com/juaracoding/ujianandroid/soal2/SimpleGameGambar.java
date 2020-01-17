package com.juaracoding.ujianandroid.soal2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.juaracoding.ujianandroid.R;

import java.util.Timer;
import java.util.TimerTask;

public class SimpleGameGambar extends AppCompatActivity implements View.OnTouchListener{

    private FrameLayout frame;
    private ImageView imgBlack;
    private Button btnUp, btnDown, btnLeft, btnRight;
    //variable titik kordinat
    private float boxX, boxY;
    //boolean set action on button click by one touchListener
    private boolean moveUp, moveDown, moveRight, moveLeft;
    //variable fungsi set move image view
    private Timer timer = new Timer();
    //variable fungsi
    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_game_gambar);
        //define semua item view layout
        frame = findViewById(R.id.frame);

        imgBlack = findViewById(R.id.imgBlack);

        btnUp = findViewById(R.id.btnUp);
        btnDown = findViewById(R.id.btnDown);
        btnRight = findViewById(R.id.btnRight);
        btnLeft = findViewById(R.id.btnLeft);

        btnUp.setOnTouchListener(this);
        btnDown.setOnTouchListener(this);
        btnRight.setOnTouchListener(this);
        btnLeft.setOnTouchListener(this);

        //deklarasi fungsi timer, set delay and period value
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(new Runnable() {
                    @Override
                    public void run() {

                        changePos();

                    }
                });
            }
        },0, 20);
    }

    //fungsi untuk membuat gambar bergerak di titik kordinat menggunakan timer delay dan handler period
    public void changePos(){

        boxX = imgBlack.getX();
        boxY = imgBlack.getY();

        //bergerak keatas dengan nilai fungsi timer
        if (moveUp) boxY -= 20;
        //bergerak kebawah dengan nilai fungsi timer
        if (moveDown) boxY += 20;
        //bergerak kekanan dengan nilai fungsi timer
        if (moveRight) {boxX +=20;}
        //bergerak kekiri dengan nilai fungsi timer
        if(moveLeft) {boxX -=20;}

        // mengatur batas move di layoutframe
        //koordinat y
        if (boxY < 0) boxY = 0;
        if (boxY > frame.getHeight() - imgBlack.getHeight()) boxY = frame.getHeight() - imgBlack.getHeight();

        //kordinat x
        if(boxX <0) boxX = 0;
        if (boxX > frame.getWidth() - imgBlack.getWidth()) {
            boxX = frame.getWidth() - imgBlack.getWidth();


        }
            imgBlack.setX(boxX);
            imgBlack.setY(boxY);
    }

    //ontouch button
    @Override
    public boolean onTouch(View v, MotionEvent event) {

        if(event.getAction() == MotionEvent.ACTION_DOWN){
            switch(v.getId()){
                case R.id.btnUp:
                    moveUp = true;
                    break;
                case R.id.btnDown:
                    moveDown = true;
                    break;
                case R.id.btnRight:
                    moveRight = true;
                    break;
                case R.id.btnLeft:
                    moveLeft = true;
                    break;
            }
        } else {

            moveUp = false;
            moveDown = false;
            moveRight = false;
            moveLeft = false;
        }
        return true;
    }
}

