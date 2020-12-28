package com.quick.firestrorekotlin.Activity

import android.content.ContentValues
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.quick.firestrorekotlin.Model.Person
import com.quick.firestrorekotlin.Model.PersonDatabase
import com.quick.firestrorekotlin.R
import kotlinx.android.synthetic.main.activity_add_data.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext


class AddDataActivity : AppCompatActivity() {
    private val TAG = "AddDataActivity"
    private var person = Person()
    var db = FirebaseFirestore.getInstance()
    var helperPerson = PersonDatabase(this)
    private val personCollectionRef = Firebase.firestore.collection("persons")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_data)

        btn_addData.setOnClickListener {
            var firstname = et_fistName.text.toString()
            var lastname = et_lastName.text.toString()
            var age = et_age.text.toString().toInt()
            var person = Person(firstname, lastname, age)
            savePerson(person)
        }
//        getData()
        btn_getData.setOnClickListener {
            startActivity(Intent(this, ScrollActivity::class.java))
        }
    }
    private fun savePerson(person: Person) = CoroutineScope(Dispatchers.IO).launch {
        try {
            personCollectionRef.add(person).await()
            withContext(Dispatchers.Main){
                Toast.makeText(this@AddDataActivity, "successfully add data", Toast.LENGTH_SHORT).show()
            }
        }catch (e: Exception){
            withContext(Dispatchers.Main){
                Toast.makeText(this@AddDataActivity, e.message, Toast.LENGTH_SHORT).show()
            }
        }
    }
    private fun getData(){
        db.collection("persons")
            .get()
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    for (document in task.result!!) {
                        val value = ContentValues()
                        value.put("documentID", document.id)
                        value.put("firstName",document.data["firstName"].toString())
                        value.put("lastName",document.data["lastName"].toString())
                        value.put("age",document.data["age"].toString())
                        helperPerson.insertData(value)
                    }
                } else {
                    Log.w(TAG, "Error getting documents.", task.exception)
                }
            }
    }
}