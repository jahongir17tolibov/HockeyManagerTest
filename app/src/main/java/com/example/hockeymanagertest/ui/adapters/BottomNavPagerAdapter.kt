package com.example.hockeymanagertest.ui.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.hockeymanagertest.R

class BottomNavPagerAdapter(
    private val list: List<Fragment>,
    fm: FragmentManager,
    lifecycle: Lifecycle
) : FragmentStateAdapter(fm, lifecycle) {

    private val fragmentIDs = listOf(R.id.clubFragment, R.id.homeFragment, R.id.storeFragment)

    override fun getItemCount(): Int = list.size

    override fun createFragment(position: Int): Fragment = list[position]

    fun getFragmentIDs(position: Int): Int = fragmentIDs.indexOf(position)

}