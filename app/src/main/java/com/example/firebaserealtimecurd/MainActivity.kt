package com.example.firebaserealtimecurd

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.firebaserealtimecurd.databinding.ActivityMainBinding
import com.google.firebase.Firebase
import com.google.firebase.database.database

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    var firebaseDatabase = Firebase.database
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.fab.setOnClickListener {
            firebaseDatabase.reference.push().setValue("testing updated")
            firebaseDatabase.reference.child("testing1").setValue("updated value")
        }
    }
}