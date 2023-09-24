package com.hnf.api_github.ui.detail

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.hnf.api_github.ui.Follower
import com.hnf.api_github.ui.Following

class SectionsPagerAdapter(activity: AppCompatActivity) : FragmentStateAdapter(activity) {
    override fun getItemCount(): Int {
       return 2
    }

    override fun createFragment(position: Int): Fragment {
        var fragment: Fragment? = null
        when (position) {
            0 -> fragment = Follower()
            1 -> fragment = Following()
        }
        return fragment as Fragment
    }
}