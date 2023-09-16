package com.hnf.api_github

import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET ("users")
    fun getUsers(
        @Path("id") id : String
    )
}