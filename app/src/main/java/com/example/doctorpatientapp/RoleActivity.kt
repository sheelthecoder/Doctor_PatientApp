package com.example.doctorpatientapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class RoleActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_role)

        val btnDoctor = findViewById<Button>(R.id.btnDoctor)
        val btnPatient = findViewById<Button>(R.id.btnPatient)

        btnDoctor.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }

        btnPatient.setOnClickListener {
            startActivity(Intent(this, PatientActivity::class.java))
        }
    }
}