package com.hnf.api_github

import com.hnf.api_github.data.response.GithubResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

interface ApiService {
    @GET ("search/users")
    @Headers ("Authorization: token <>")
    fun getUsers(
        @Path("username") username : String
    ) : Call<GithubResponse>
}