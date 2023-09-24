package com.hnf.api_github.ui

import android.os.Bundle
import android.text.Editable
import android.util.Log
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import com.google.android.material.search.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.search.SearchBar
import com.hnf.api_github.ApiConfig
import com.hnf.api_github.R
import com.hnf.api_github.data.response.GithubResponse
import com.hnf.api_github.data.response.ItemsItem
import com.hnf.api_github.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var userAdapter: UserAdapter
    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val user = findViewById<RecyclerView>(R.id.rvUser)
        val progressBar = findViewById<ProgressBar>(R.id.progressBar)
        val searchView = findViewById<SearchView>(R.id.searchView)
        val searchBar = findViewById<SearchBar>(R.id.searchBar)

        with(binding){
            searchView.setupWithSearchBar(searchBar)
            searchView
                .editText
                .setOnEditorActionListener { textView, actionId, event ->
                    searchBar.text = searchView.text
                    searchView.hide()
                    Toast.makeText(this@MainActivity, searchView.text, Toast.LENGTH_SHORT).show()
                    false
                }
        }

        userAdapter = UserAdapter(emptyList())
        user.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            setHasFixedSize(true)
            adapter = userAdapter
        }

        

        progressBar.visibility = View.VISIBLE
        ApiConfig.apiService.getUsers("x").enqueue(object : Callback<GithubResponse> {
            override fun onResponse(
                call: Call<GithubResponse>,
                response: Response<GithubResponse>
            ) {
                progressBar.visibility = View.GONE
                if (response.isSuccessful) {
                    val responseUser = response.body()
                    val dataUser = responseUser?.items
                    val userAdapter = UserAdapter(dataUser as List<ItemsItem>)
                    user.apply {
                        layoutManager = LinearLayoutManager(this@MainActivity)
                        setHasFixedSize(true)
                        userAdapter.notifyDataSetChanged()
                        adapter = userAdapter
                    }
                }
            }

            override fun onFailure(call: Call<GithubResponse>, t: Throwable) {
                progressBar.visibility = View.GONE
                Toast.makeText(applicationContext, t.localizedMessage, Toast.LENGTH_SHORT).show()
            }
        })

    }}