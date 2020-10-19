package com.quick.firestrorekotlin.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.auth.FirebaseAuth
import com.quick.firestrorekotlin.R
import kotlinx.android.synthetic.main.activity_dashboard.*

class DashboardActivity : AppCompatActivity() {
    private lateinit var auth : FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)
        auth = FirebaseAuth.getInstance()
        var currentUser = auth?.currentUser

        tv_userId.text = currentUser?.uid
        tv_userName.text = currentUser?.displayName
        tv_userEmail.text = currentUser?.email

        btn_logout.setOnClickListener {
            auth.signOut()
            startActivity(Intent(this@DashboardActivity, SignInActivity::class.java))
            finish()
        }
    }
}