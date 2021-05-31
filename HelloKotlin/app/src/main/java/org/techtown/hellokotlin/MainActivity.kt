package org.techtown.hellokotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //var btnHello : Button
        val btnHello = findViewById<Button>(R.id.btnHello)
        //btnHello = findViewById(R.id.btnHello)
        btnHello.setOnClickListener {
            Toast.makeText(this@MainActivity, "Hello Kotlin", Toast.LENGTH_LONG).show()
        }
    }
}