package com.quick.firestrorekotlin.utils

import android.database.Cursor
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.quick.firestrorekotlin.Activity.ScrollActivity
import com.quick.firestrorekotlin.Model.ListPerson
import com.quick.firestrorekotlin.Model.Person
import com.quick.firestrorekotlin.R
import kotlinx.android.synthetic.main.list_layout.view.*
import org.w3c.dom.Text

class ListAdapter(
    c: Cursor?,
    scrollActivity : ScrollActivity
    ) :
    RecyclerView.Adapter<ListAdapter.PersonHolder>() {
    private val cursor: Cursor
    private val mParent = scrollActivity
    inner class PersonHolder(v: View) : RecyclerView.ViewHolder(v) {
        val root : CardView = v.findViewById(R.id.root)
         val tv_name : TextView = v.findViewById(R.id.tv_nama)
         val tv_age : TextView = v.findViewById(R.id.tv_umur)
        init {

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonHolder {
        return PersonHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.list_layout, parent, false)
        )
    }

    override fun onBindViewHolder(holder: PersonHolder, position: Int) {
        cursor.moveToPosition(position)
        val docID: String =
            cursor.getString(cursor.getColumnIndex("documentID"))
        val firstName: String =
            cursor.getString(cursor.getColumnIndex("firstName"))
        val lastName: String =
            cursor.getString(cursor.getColumnIndex("lastName"))
        val textAge: String =
            cursor.getString(cursor.getColumnIndex("age"))
        holder.tv_name.text = "$firstName $lastName"
        holder.tv_age.text = textAge
        holder.root.setOnClickListener {
            mParent.displayOption(docID, firstName, lastName, textAge)
        }
    }

    override fun getItemCount(): Int {
        return cursor.getCount()



    }

    init {
        cursor = c!!
    }
}