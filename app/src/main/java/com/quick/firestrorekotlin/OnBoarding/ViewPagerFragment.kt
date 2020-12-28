package com.quick.firestrorekotlin.OnBoarding

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.quick.firestrorekotlin.OnBoarding.Screen.FirstScreen
import com.quick.firestrorekotlin.OnBoarding.Screen.SecondScreen
import com.quick.firestrorekotlin.OnBoarding.Screen.ThirdScreen
import com.quick.firestrorekotlin.R
import kotlinx.android.synthetic.main.fragment_viewpager.view.*

class ViewPagerFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_viewpager, container, false)

        val fragmentList = arrayListOf<Fragment>(
            FirstScreen(),
            SecondScreen(),
            ThirdScreen()
        )

        val adapter = ViewPagerAdapter(
            fragmentList, requireActivity().supportFragmentManager, lifecycle
        )

        view.viewPager.adapter = adapter

        return view
    }
}