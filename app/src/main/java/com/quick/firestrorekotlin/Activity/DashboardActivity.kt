package com.quick.firestrorekotlin.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.firebase.auth.FirebaseAuth
import com.quick.firestrorekotlin.R
import kotlinx.android.synthetic.main.activity_dashboard.*
import kotlinx.android.synthetic.main.activity_detail_slider.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DashboardActivity : AppCompatActivity() {
    private lateinit var auth : FirebaseAuth
    private lateinit var bottomSheetBehavior : BottomSheetBehavior<ConstraintLayout>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)
        auth = FirebaseAuth.getInstance()
        var currentUser = auth?.currentUser

        bottomSheetBehavior = BottomSheetBehavior.from(btmSheet)

        tv_uId.text = currentUser?.uid
        tv_uName.text = currentUser?.displayName
        tv_uEmail.text = currentUser?.email
        tv_welcome.text = "Selamat Datang "+currentUser?.displayName+" Selamat menjelajahi Dunia baru bersama kami"

        btn_logout.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                try {
                    auth.signOut().also {
                        startActivity(Intent(this@DashboardActivity, SignInActivity::class.java))
                        finish()
                    }
                }catch (e:Exception){
                    withContext(Dispatchers.Main){
                        Toast.makeText(this@DashboardActivity, e.message, Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }

        ln_toogle.setOnClickListener {
            val state =
                if (bottomSheetBehavior.state == BottomSheetBehavior.STATE_EXPANDED)
                    BottomSheetBehavior.STATE_COLLAPSED
                else
                    BottomSheetBehavior.STATE_EXPANDED
                bottomSheetBehavior.state = state

        }
    }
}