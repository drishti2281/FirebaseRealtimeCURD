package com.example.firebaserealtimecurd

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import com.example.firebaserealtimecurd.databinding.ActivityMainBinding
import com.google.firebase.Firebase
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.database


class MainActivity : AppCompatActivity() {
    //lateinit var binding: ActivityMainBinding
    var firebaseDatabase = Firebase.database
    var list = arrayListOf<student>()
    lateinit var arrayAdapter: ArrayAdapter<student>
    lateinit var mainActivity: MainActivity
    val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
         //binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        arrayAdapter = ArrayAdapter(this,android.R.layout.simple_dropdown_item_1line,list)
        binding.lvlistview.adapter = arrayAdapter

        firebaseDatabase.reference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                for (snapshots in dataSnapshot.children) {
                    Log.d("TAG", "Value is: ${dataSnapshot.value}")
                    var name = dataSnapshot.child("name").getValue(String::class.java)
                    val value = snapshots.getValue(student::class.java)
                    value?.id = snapshots.key
                    Log.d("TAG", "Value is: ${snapshots.value}  value $value")
                    list.add(value ?: student())
                    arrayAdapter.notifyDataSetChanged()
                }
            }
            override fun onCancelled(error: DatabaseError) {
                Log.w("TAG", "Failed to read value.", error.toException())
            }
        })

        binding.fab.setOnClickListener {
            firebaseDatabase.reference.push().setValue(student(name = "Drishti", rollNo = 2))
        }
    }
}






