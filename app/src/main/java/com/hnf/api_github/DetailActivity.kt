package com.hnf.api_github

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.support.annotation.StringRes
import android.widget.TableLayout
import android.widget.TextView
import android.widget.Toast
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.hnf.api_github.ui.detail.SectionsPagerAdapter
import com.squareup.picasso.Picasso
import de.hdodenhof.circleimageview.CircleImageView

class DetailActivity : AppCompatActivity() {

    companion object{
        @StringRes
        private val TAB_TITLES = intArrayOf(
            R.string.tab_1,
            R.string.tab_2
        )
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val foto = findViewById<CircleImageView>(R.id.avatar_user)
        val nama = findViewById<TextView>(R.id.nama_user)
        val name = intent.getStringExtra("NAMA_KEY")
        val avatar = intent.getStringExtra("AVATAR_KEY")

        nama.text = name
        Picasso.get().load(avatar).into(foto)


        val sectionsPagerAdapter = SectionsPagerAdapter(this)
        val viewPager: ViewPager2 = findViewById(R.id.view_pager)
        viewPager.adapter = sectionsPagerAdapter
        val tabs: TabLayout = findViewById(R.id.tabs)
        TabLayoutMediator(tabs, viewPager) { tab, position ->
            tab.text = resources.getString(TAB_TITLES[position])
        }.attach()

        supportActionBar?.elevation = 0f
    }
}