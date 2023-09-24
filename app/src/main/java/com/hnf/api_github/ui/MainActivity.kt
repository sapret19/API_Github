package com.hnf.api_github.ui

import android.os.Bundle
import android.text.Editable
import android.util.Log
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hnf.api_github.ApiConfig
import com.hnf.api_github.R
import com.hnf.api_github.data.response.GithubResponse
import com.hnf.api_github.data.response.ItemsItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var userAdapter: UserAdapter
    private lateinit var searchView: SearchView
    private lateinit var searchBar: EditText


    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val user = findViewById<RecyclerView>(R.id.rvUser)
        val progressBar = findViewById<ProgressBar>(R.id.progressBar)
        val searchView = findViewById<SearchView>(R.id.searchView)
        val searchBar = findViewById<EditText>(R.id.searchBar)

        userAdapter = UserAdapter(emptyList())
        user.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            setHasFixedSize(true)
            adapter = userAdapter
        }

        progressBar.visibility = View.VISIBLE
        ApiConfig.getService().getUsers("hanafi").enqueue(object : Callback<GithubResponse>{
            override fun onResponse(
                call: Call<GithubResponse>,
                response: Response<GithubResponse>
            ) {
                progressBar.visibility = View.GONE
                if (response.isSuccessful){
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
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                searchBar.setText(query)
                searchView.clearFocus()  // Close the search view
                Toast.makeText(this@MainActivity, query, Toast.LENGTH_SHORT).show()
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                // Tindakan yang diambil saat teks diubah
                return false
            }
        })

        searchBar.setOnEditorActionListener { textView, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                searchBar.text = Editable.Factory.getInstance().newEditable(searchView.query)
                searchView.visibility = View.GONE
                Toast.makeText(this@MainActivity, searchView.query, Toast.LENGTH_SHORT).show()
                return@setOnEditorActionListener true
            }
            return@setOnEditorActionListener false
        }

    }

    }