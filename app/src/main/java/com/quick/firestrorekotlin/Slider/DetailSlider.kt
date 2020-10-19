package com.quick.firestrorekotlin.Slider

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.auth.FirebaseAuth
import com.quick.firestrorekotlin.R
import kotlinx.android.synthetic.main.activity_dashboard.*
import kotlinx.android.synthetic.main.activity_detail_slider.*

class DetailSlider : AppCompatActivity() {
    private lateinit var auth : FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_slider)
        auth = FirebaseAuth.getInstance()
        var currentUser = auth?.currentUser

        tv_uId.text = currentUser?.uid
        tv_uName.text = currentUser?.displayName
        tv_uEmail.text = currentUser?.email
    }
}