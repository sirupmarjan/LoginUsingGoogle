package com.quick.firestrorekotlin.Model

import android.os.Parcel
import android.os.Parcelable
import com.google.firebase.firestore.PropertyName

data class ListPerson(
    var personlist : ArrayList<Person> = arrayListOf()
)

data class Person (
    @get: PropertyName("firstName")
    @set: PropertyName("firstName")
    var firstName : String? = "",

    @get: PropertyName("lastName")
    @set: PropertyName("lastName")
    var lastName : String? = "",

    @get: PropertyName("age")
    @set: PropertyName("age")
    var age : Int = -1
) {

}

