package com.hnf.api_github

import com.hnf.api_github.data.response.GithubResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET ("search/users")
//    @Headers ("Authorization: token <>")
    fun getUsers(
<<<<<<< HEAD
        @Query("q") query : String
=======
        @Query("q") query: Query
>>>>>>> 8218084cd423b06d6e73bbde72a3d62374fd2705
    ) : Call<GithubResponse>

//    @GET ("users/{username}")
//    fun getDetailUser (
//        @Path("username") username : String
//    ) : Call<DetailUserResponse>
}