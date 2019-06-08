package br.com.lucianoluzzi.currencyrateconverter.model

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import br.com.lucianoluzzi.currencyrateconverter.repository.CurrenciesRepository
import br.com.lucianoluzzi.currencyrateconverter.repository.dto.RatesDTO
import io.reactivex.Observable
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.concurrent.TimeUnit

class CurrenciesRateViewModel(private val repository: CurrenciesRepository) : BaseViewModel() {
    private val _currenciesRates = MutableLiveData<List<Currency>>().apply {
        mutableListOf<List<Currency>>()
    }
    val currenciesRates: LiveData<List<Currency>> = _currenciesRates

    suspend fun startFetchingRates(baseCurrency: String) = withContext(Dispatchers.IO) {
        val interval = Observable.interval(0, 1, TimeUnit.SECONDS)
        with(interval) {
            val disposable = subscribe {
                val currenciesRate = repository.getCurrenciesRate(baseCurrency)
                Log.d("VIEWMODEL", "request")
                _currenciesRates.postValue(getCurrencyListFromResponse(currenciesRate))
            }
            addDisposable(disposable)
        }
    }

    private fun getCurrencyListFromResponse(rates: RatesDTO): List<Currency> {
        return listOf(
            Currency(rates::AUD.name, rates.AUD),
            Currency(rates::BGN.name, rates.BGN),
            Currency(rates::BRL.name, rates.BRL),
            Currency(rates::CAD.name, rates.CAD),
            Currency(rates::CHF.name, rates.CHF),
            Currency(rates::CNY.name, rates.CNY),
            Currency(rates::CZK.name, rates.CZK),
            Currency(rates::DKK.name, rates.DKK),
            Currency(rates::GBP.name, rates.GBP),
            Currency(rates::HKD.name, rates.HKD),
            Currency(rates::HRK.name, rates.HRK),
            Currency(rates::HUF.name, rates.HUF),
            Currency(rates::IDR.name, rates.IDR),
            Currency(rates::ILS.name, rates.ILS),
            Currency(rates::INR.name, rates.INR),
            Currency(rates::ISK.name, rates.ISK),
            Currency(rates::JPY.name, rates.JPY),
            Currency(rates::KRW.name, rates.KRW),
            Currency(rates::MXN.name, rates.MXN),
            Currency(rates::MYR.name, rates.MYR),
            Currency(rates::NOK.name, rates.NOK),
            Currency(rates::NZD.name, rates.NZD),
            Currency(rates::PHP.name, rates.PHP),
            Currency(rates::PLN.name, rates.PLN),
            Currency(rates::RON.name, rates.RON),
            Currency(rates::RUB.name, rates.RUB),
            Currency(rates::SEK.name, rates.SEK),
            Currency(rates::SGD.name, rates.SGD),
            Currency(rates::THB.name, rates.THB),
            Currency(rates::TRY.name, rates.TRY),
            Currency(rates::USD.name, rates.USD),
            Currency(rates::ZAR.name, rates.ZAR)
        )
    }

    fun stopFetching() {
        onCleared()
    }
}