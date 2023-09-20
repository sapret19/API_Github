package com.hnf.api_github

import com.hnf.api_github.data.response.GithubResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface ApiService {
    @GET ("search/users")
    @Headers ("Authorization: token <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>")
    fun getUsers(
        @Query("q") query: String
    ) : Call<ArrayList<GithubResponse>>

//    @GET ("users/{username}")
//    fun getDetailUser (
//        @Path("username") username : String
//    ) : Call<DetailUserResponse>
}