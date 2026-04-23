package com.example.doctorpatientapp

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.FirebaseFirestore

class PatientActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_patient)

        val listView = findViewById<ListView>(R.id.listPatients)
        val db = FirebaseFirestore.getInstance()

        val patientList = ArrayList<String>()

        db.collection("patients")
            .get()
            .addOnSuccessListener { result ->

                var bestScore = 0

                for (document in result) {

                    val name = document.getString("name")
                    val score = document.getString("score")?.toIntOrNull() ?: 0

                    if (score > bestScore) bestScore = score

                    patientList.add("$name - Score: $score")
                }

                patientList.add("\n🏆 Best Score: $bestScore")

                val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, patientList)
                listView.adapter = adapter
            }
    }
}