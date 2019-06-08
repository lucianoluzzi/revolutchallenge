package br.com.lucianoluzzi.currencyrateconverter.repository

import br.com.lucianoluzzi.currencyrateconverter.repository.dto.RatesDTO
import br.com.lucianoluzzi.currencyrateconverter.repository.service.CurrencyService
import retrofit2.Retrofit

class CurrenciesRepositoryImpl : CurrenciesRepository {
    companion object {
        const val BASE_URL = "https://revolut.duckdns.org/"
    }

    private val retrofit by lazy {
        retrofit()
    }

    private fun retrofit() = with(Retrofit.Builder()) {
        baseUrl(BASE_URL)
        addConverterFactory(retrofit2.converter.gson.GsonConverterFactory.create())
        build()
    }

    override fun getCurrenciesRate(baseCurrency: String): RatesDTO {
        val service = retrofit.create(CurrencyService::class.java)
        val call = service.getRates(baseCurrency)
        val body = call.execute().body()

        return body?.rates ?: run {
            RatesDTO()
        }
    }
}