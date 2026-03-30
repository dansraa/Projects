package com.example.nutritrack.data.network
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface APIService {

    @GET("api/fruit/{name}")
    suspend fun searchFruitName(@Path("name") name: String): FruitResponse

    companion object {
        var BASE_URL = "https://fruityvice.com/"

        /**
         * @return
         */
        fun create(): APIService {
            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()
            return retrofit.create(APIService::class.java)
        }
    }

}