package com.example.doctorpatientapp

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.firestore.FirebaseFirestore

class MainActivity : AppCompatActivity() {

    private lateinit var etName: TextInputEditText
    private lateinit var etAge: TextInputEditText
    private lateinit var etGender: TextInputEditText
    private lateinit var etWeight: TextInputEditText
    private lateinit var etCondition: TextInputEditText
    private lateinit var etScore: TextInputEditText
    private lateinit var btnSave: Button

    private lateinit var db: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        // Initialize views
        etName = findViewById(R.id.etName)
        etAge = findViewById(R.id.etAge)
        etGender = findViewById(R.id.etGender)
        etWeight = findViewById(R.id.etWeight)
        etCondition = findViewById(R.id.etCondition)
        etScore = findViewById(R.id.etScore)
        btnSave = findViewById(R.id.btnSave)

        // Firebase
        db = FirebaseFirestore.getInstance()

        btnSave.setOnClickListener {

            val name = etName.text.toString().trim()
            val age = etAge.text.toString().trim()
            val gender = etGender.text.toString().trim()
            val weight = etWeight.text.toString().trim()
            val condition = etCondition.text.toString().trim()
            val score = etScore.text.toString().trim()

            // Validation
            if (name.isEmpty() || age.isEmpty() || score.isEmpty()) {
                Toast.makeText(this, "Please fill required fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Data map
            val patientData = hashMapOf(
                "name" to name,
                "age" to age,
                "gender" to gender,
                "weight" to weight,
                "condition" to condition,
                "score" to score
            )

            // Save to Firebase
            db.collection("patients")
                .add(patientData)
                .addOnSuccessListener {
                    Toast.makeText(this, "Patient Saved", Toast.LENGTH_SHORT).show()

                    // Clear fields
                    etName.setText("")
                    etAge.setText("")
                    etGender.setText("")
                    etWeight.setText("")
                    etCondition.setText("")
                    etScore.setText("")
                }
                .addOnFailureListener {
                    Toast.makeText(this, "Error saving data", Toast.LENGTH_SHORT).show()
                }
        }
    }
}