package com.example.pet_dairy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Register_Snack extends AppCompatActivity implements View.OnClickListener {

    long mNow;
    Date mDate;
    SimpleDateFormat mFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

    ImageButton btnregister;
    Spinner spnGive, spnType;
    TextView txtmsg, date;
    Button btndate;
    EditText txttime, txtmany;
    private DrawerLayout drawerLayout;
    private View drawerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register__snack);

        txtmany = findViewById(R.id.txtMany);
        txtmsg = findViewById(R.id.textView6);
        btnregister = findViewById(R.id.btnregister);
        btnregister.setOnClickListener(this);
        spnGive = findViewById(R.id.spnGive);
        spnType = findViewById(R.id.spnType);
        date = findViewById(R.id.txtdate);
        btndate = findViewById(R.id.btndate);
        btndate.setOnClickListener(this);
        drawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);
        drawerView = (View)findViewById(R.id.drawer);

        ArrayAdapter GiveAdapter = ArrayAdapter.createFromResource(this,
                R.array.name, android.R.layout.simple_spinner_item);
        GiveAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnGive.setAdapter(GiveAdapter);

        ArrayAdapter TypeAdapter = ArrayAdapter.createFromResource(this,
                R.array.종류, android.R.layout.simple_spinner_item);
        TypeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnType.setAdapter(TypeAdapter);
        Button Btn1 = (Button)findViewById(R.id.Btn1);
        Btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 =new Intent(Register_Snack.this,Register_Food.class);
                startActivity(intent1);
            }
        });
        Button Btn2 = (Button)findViewById(R.id.Btn2);
        Btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2 =new Intent(Register_Snack.this,Register_Snack.class);
                startActivity(intent2);
            }
        });
        Button Btn3 = (Button)findViewById(R.id.Btn3);
        Btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent3 =new Intent(Register_Snack.this,Register_Health.class);
                startActivity(intent3);
            }
        });
        Button Btn4 = (Button)findViewById(R.id.Btn4);
        Btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent4 =new Intent(Register_Snack.this,Register_Run.class);
                startActivity(intent4);
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
            case R.id.btnregister:
                txtmsg.setText("오늘의 간식정보 등록 완료!");

                FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
                DatabaseReference rootRef = firebaseDatabase.getReference("Family Pet");

                String Give = spnGive.getSelectedItem().toString();
                String Type = spnType.getSelectedItem().toString();
                String Many = txtmany.getText().toString();
                String Date = date.getText().toString();

                Snack snack = new Snack(Give, Type, Many, Date);

                DatabaseReference SnackRef = rootRef.child("snack");
                SnackRef.push().setValue(snack);
                break;

            case R.id.btndate:
                date.setText(getTime());
                break;

            default:
                break;
        }
    }

    private String getTime () {
        mNow = System.currentTimeMillis();
        mDate = new Date(mNow);
        return mFormat.format(mDate);
    }

}