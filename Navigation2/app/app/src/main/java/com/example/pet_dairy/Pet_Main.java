package com.example.pet_dairy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;


public class Pet_Main extends AppCompatActivity implements View.OnClickListener {
    ImageButton btnFood, btnSnack, btnWalk, btnHealth;
    private DrawerLayout drawerLayout;
    private View drawerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pet__main);

        btnFood = findViewById(R.id.imgBtn1);
        btnFood.setOnClickListener(this);
        btnSnack = findViewById(R.id.imgBtn2);
        btnSnack.setOnClickListener(this);
        btnWalk = findViewById(R.id.imgBtn3);
        btnWalk.setOnClickListener(this);
        btnHealth = findViewById(R.id.imgBtn4);
        btnHealth.setOnClickListener(this);
        drawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);
        drawerView = (View)findViewById(R.id.drawer);

        Button Btn1 = (Button)findViewById(R.id.Btn1);
        Btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent_f =new Intent(Pet_Main.this,Register_Food.class);
                startActivity(intent_f);
            }
        });
        Button Btn2 = (Button)findViewById(R.id.Btn2);
        Btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent_s =new Intent(Pet_Main.this,Register_Snack.class);
                startActivity(intent_s);
            }
        });
        Button Btn3 = (Button)findViewById(R.id.Btn3);
        Btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent_h =new Intent(Pet_Main.this,Register_Health.class);
                startActivity(intent_h);
            }
        });
        Button Btn4 = (Button)findViewById(R.id.Btn4);
        Btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent_r =new Intent(Pet_Main.this,Register_Run.class);
                startActivity(intent_r);
            }
        });

        Button btn_close = (Button)findViewById(R.id.btn_close);
        btn_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.closeDrawers();
            }
        });

        drawerLayout.setDrawerListener(listener);
        drawerView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return true;
            }
        });
    }
    DrawerLayout.DrawerListener listener = new DrawerLayout.DrawerListener() {
        @Override
        public void onDrawerSlide(@NonNull View drawerView, float slideOffset) {
        }

        @Override
        public void onDrawerOpened(@NonNull View drawerView) {
        }
        @Override
        public void onDrawerClosed(@NonNull View drawerView) {
        }
        @Override
        public void onDrawerStateChanged(int newState) {
        }
    };

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.imgBtn1:
                Intent intent1 = new Intent(Pet_Main.this, Register_Food.class);
                startActivity(intent1);
                break;
            case R.id.imgBtn2:
                Intent intent2 = new Intent(Pet_Main.this, Register_Snack.class);
                startActivity(intent2);
                break;
            case R.id.imgBtn3:
                Intent intent3 = new Intent(Pet_Main.this, Register_Run.class);
                startActivity(intent3);
                break;
            case R.id.imgBtn4:
                Intent intent4 = new Intent(Pet_Main.this, Register_Health.class);
                startActivity(intent4);
                break;
            default: break;
        }

    }
}