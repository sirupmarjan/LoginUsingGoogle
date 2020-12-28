package com.quick.firestrorekotlin.Model

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class PersonDatabase(var context: Context?) : SQLiteOpenHelper(context, "db_person", null, 1) {
    private lateinit var mQuery: String
    override fun onCreate(db: SQLiteDatabase?) {
        mQuery = "CREATE TABLE IF NOT EXISTS tb_person(" +
                "_id INTEGER PRIMARY KEY," +
                "documentID TEXT," +
                "firstName TEXT," +
                "lastName TEXT," +
                "age INTEGER" +
                ")"
        db?.execSQL(mQuery)
    }

    fun insertData(values: ContentValues) {
        val db = this.writableDatabase
        db.insert("tb_person", null, values)
    }

    fun select(): Cursor? {
        val db = this.writableDatabase
        mQuery = "select * from tb_person"
        return db.rawQuery(mQuery, null)
    }
    fun update(documentID : String,firstName : String, lastName : String, age : String) {
        val db = this.writableDatabase
        mQuery = "UPDATE tb_person SET firstName = '"+firstName+"', lastName = '"+lastName+"', age = "+age+" WHERE documentID = '"+documentID+"'"
        db.execSQL(mQuery)
    }
    fun delete() {
        val db = this.writableDatabase
        mQuery = "DELETE FROM tb_person"
        db.execSQL(mQuery)
    }
    fun deleteItem(docID : String) {
        val db = this.writableDatabase
        mQuery = "DELETE FROM tb_person where documentID = '"+docID+"'"
        db.execSQL(mQuery)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }
}