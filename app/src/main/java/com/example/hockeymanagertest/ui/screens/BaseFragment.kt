package com.example.hockeymanagertest.ui.screens

import android.annotation.SuppressLint
import android.content.res.ColorStateList
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.hockeymanagertest.R
import com.example.hockeymanagertest.databinding.FragmentBaseBinding
import com.example.hockeymanagertest.ui.adapters.BottomNavPagerAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class BaseFragment : Fragment() {
    private var _binding: FragmentBaseBinding? = null
    private val binding get() = _binding!!

    private lateinit var navController: NavController
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBaseBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //setup navController
        val navHostFragment =
            requireActivity().supportFragmentManager.findFragmentById(R.id.fragment_container_view) as NavHostFragment
        navController = navHostFragment.navController

        //set stroke color from drawable resources to card



        setupViewPager()

    }

    @SuppressLint("ResourceAsColor")
    private fun setupViewPager() {
        val fragmentsList = listOf(ClubFragment(), HomeFragment(), StoreFragment())
        val tabItemsText = arrayOf("CLUB", "HOME", "STORE")
        val tabItemsIcons = arrayOf(
            AppCompatResources.getDrawable(
                requireContext(),
                R.drawable.baseline_monitor_heart_24
            ),
            AppCompatResources.getDrawable(
                requireContext(),
                R.drawable.round_home_24
            ),
            AppCompatResources.getDrawable(
                requireContext(),
                R.drawable.round_storefront_24
            )
        )
        val tabLayout = binding.tabLayoutContainer
        val viewPager = binding.viewPager
        val navAdapter = BottomNavPagerAdapter(
            list = fragmentsList,
            fm = childFragmentManager,
            lifecycle = lifecycle
        )

        viewPager.apply {
            adapter = navAdapter
            isUserInputEnabled = false
            currentItem = 1
        }

        TabLayoutMediator(tabLayout, viewPager) { tab, pos ->
            tab.apply {
                text = tabItemsText[pos]
                icon = tabItemsIcons[pos]
            }
        }.attach()

        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {

            override fun onTabSelected(tab: TabLayout.Tab?) {
                tab?.icon?.setTint(R.color.main_color_base)
                tabLayout.setSelectedTabIndicatorColor(R.color.main_color_base)
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }
        })

        navController.addOnDestinationChangedListener { _, destination, _ ->
            val position = navAdapter.getFragmentIDs(destination.id)
            if (position != -1) viewPager.currentItem = position
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}