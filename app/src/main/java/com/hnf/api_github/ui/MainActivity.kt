package com.hnf.api_github.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.hnf.api_github.ApiConfig
import com.hnf.api_github.R
import com.hnf.api_github.data.response.GithubResponse
import com.hnf.api_github.data.response.ItemsItem
import com.hnf.api_github.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Response
import retrofit2.Callback
import retrofit2.http.Query

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: UserAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = UserAdapter(this@MainActivity, arrayListOf())

        binding
    }

    fun remoteGetUsers(){
        ApiConfig.apiService.getUsers("q").enqueue(object : Callback<ArrayList<GithubResponse>>{
            override fun onResponse(
                call: Call<ArrayList<GithubResponse>>,
                response: Response<ArrayList<GithubResponse>>
            ) {
                if (response.isSuccessful){
                    val data = response.body()
                    setDataToAdapter(data)
                }
            }

            override fun onFailure(call: Call<ArrayList<GithubResponse>>, t: Throwable) {
                Log.d("Error", "" + t.stackTraceToString())
            }

        })
    }

    fun setDataToAdapter(data: ArrayList<GithubResponse>){
        adapter.setData(data)
    }

    }