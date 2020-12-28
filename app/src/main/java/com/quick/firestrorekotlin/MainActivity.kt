package com.quick.firestrorekotlin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import com.google.firebase.auth.FirebaseAuth
import com.quick.firestrorekotlin.Activity.AddDataActivity
import com.quick.firestrorekotlin.Activity.DashboardActivity
import com.quick.firestrorekotlin.Activity.SignInActivity
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.concurrent.schedule

class MainActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()
//        auth = FirebaseAuth.getInstance()
////        startActivity(Intent(this@MainActivity, AddDataActivity::class.java))
//        GlobalScope.launch {
//            delay(2000)
//            if (this@MainActivity.auth.currentUser !== null) {
//                startActivity(Intent(this@MainActivity, DashboardActivity::class.java))
//                finish()
//            } else {
//                startActivity(Intent(this@MainActivity, SignInActivity::class.java))
//                finish()
//            }
//        }
    }
}