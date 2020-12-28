package com.quick.firestrorekotlin.Activity

import android.app.AlertDialog
import android.content.ContentValues
import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreSettings
import com.quick.firestrorekotlin.Model.ListPerson
import com.quick.firestrorekotlin.Model.PersonDatabase
import com.quick.firestrorekotlin.R
import com.quick.firestrorekotlin.utils.ListAdapter
import kotlinx.android.synthetic.main.activity_scroll.*

class ScrollActivity : AppCompatActivity() {
    private val db = FirebaseFirestore.getInstance()
    private val TAG = "ScrollActivity"
    var helperPerson = PersonDatabase(this)
    var listPerson = ListPerson()
    lateinit var mAdapter: ListAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scroll)
        db.firestoreSettings = FirebaseFirestoreSettings.Builder().build()

        getData()
    }

    private fun getData() {
        helperPerson.delete()
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
                initView()
            }
    }

    fun displayOption(documentID: String, firstName : String, lastName : String, age : String){
        AlertDialog.Builder(this)
            .setTitle("EDIT and DELETE POPOUP")
            .setMessage("select Action")
            .setPositiveButton("Edit", DialogInterface.OnClickListener { dialog, which ->
                popUpEdit(documentID, firstName, lastName, age)
            })
            .setNeutralButton("Delete", DialogInterface.OnClickListener { dialog, which ->
                deleteData(documentID)
            })
            .setNegativeButton("Cancel", DialogInterface.OnClickListener { dialog, which ->
                dialog.cancel()
            }).show()

    }

    fun popUpEdit(documentID: String, firstName : String, lastName : String, age : String){
        var editView : View = LayoutInflater.from(this).inflate(R.layout.popup_edit, null, false)
        val et_firstName : EditText = editView.findViewById(R.id.et_firstNameEdit)
        val et_lastName : EditText = editView.findViewById(R.id.et_lastNameEdit)
        val et_age : EditText = editView.findViewById(R.id.et_ageEdit)
        et_firstName.setText(firstName)
        et_lastName.setText(lastName)
        et_age.setText(age)
        AlertDialog.Builder(this)
            .setView(editView)
            .setCancelable(false)
            .setPositiveButton("OK", DialogInterface.OnClickListener { dialog, which ->
                updateData(documentID, et_firstName.text.toString(), et_lastName.text.toString(), et_age.text.toString())
            })
            .setNegativeButton("Cancel", DialogInterface.OnClickListener { dialog, which ->
                dialog.dismiss()
            }).show()

    }

    fun updateData(documentID: String, firstName : String, lastName : String, age : String){
//        val fe : String =  firstName
        val docData = hashMapOf(
            "firstName" to firstName,
            "lastName" to lastName,
            "age" to age
        )
        db.collection("persons").document(documentID)
            .set(docData)
            .addOnSuccessListener {
                helperPerson.update(documentID,firstName, lastName, age)
                Toast.makeText(this, "Data Updated", Toast.LENGTH_SHORT).show()
                initView()
            }
    }

    fun deleteData(documentID : String){
        db.collection("persons").document(documentID)
            .delete()
            .addOnSuccessListener {
                Toast.makeText(this, "Item Deleted", Toast.LENGTH_SHORT).show()
                helperPerson.deleteItem(documentID)
                initView()
            }
            .addOnFailureListener { e ->
                Log.d(TAG, "deleteData: "+ e.localizedMessage)
                Toast.makeText(this, "Sorry, something was happen", Toast.LENGTH_SHORT).show()
            }

    }


    private fun initView() {
        mAdapter = ListAdapter(helperPerson.select(), this)
        rv_list.adapter = mAdapter
        rv_list.layoutManager = LinearLayoutManager(this)
//        rv_list.addOnItemTouchListener{this -> }

    }
}