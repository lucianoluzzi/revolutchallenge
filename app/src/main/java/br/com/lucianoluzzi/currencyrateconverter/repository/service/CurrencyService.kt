package br.com.lucianoluzzi.currencyrateconverter.repository.service

import br.com.lucianoluzzi.currencyrateconverter.repository.dto.ResponseDTO
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface CurrencyService {
    @GET("latest")
    fun getRates(@Query("base") base: String): Call<ResponseDTO>
}