package com.quick.firestrorekotlin.OnBoarding.Screen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.quick.firestrorekotlin.R
import kotlinx.android.synthetic.main.fragment_second_screen.view.*


class SecondScreen : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_second_screen, container, false)

        val viewpager =activity?.findViewById<ViewPager2>(R.id.viewPager)

        view.tv_next2.setOnClickListener {
            viewpager?.currentItem = 2
        }

        return view
    }
}