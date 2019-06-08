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
            Currency(1, rates::AUD.name, rates.AUD),
            Currency(2, rates::BGN.name, rates.BGN),
            Currency(3, rates::BRL.name, rates.BRL),
            Currency(4, rates::CAD.name, rates.CAD),
            Currency(5, rates::CHF.name, rates.CHF),
            Currency(6, rates::CNY.name, rates.CNY),
            Currency(7, rates::CZK.name, rates.CZK),
            Currency(8, rates::DKK.name, rates.DKK),
            Currency(9, rates::GBP.name, rates.GBP),
            Currency(10, rates::HKD.name, rates.HKD),
            Currency(11, rates::HRK.name, rates.HRK),
            Currency(12, rates::HUF.name, rates.HUF),
            Currency(13, rates::IDR.name, rates.IDR),
            Currency(14, rates::ILS.name, rates.ILS),
            Currency(15, rates::INR.name, rates.INR),
            Currency(16, rates::ISK.name, rates.ISK),
            Currency(17, rates::JPY.name, rates.JPY),
            Currency(18, rates::KRW.name, rates.KRW),
            Currency(19, rates::MXN.name, rates.MXN),
            Currency(20, rates::MYR.name, rates.MYR),
            Currency(21, rates::NOK.name, rates.NOK),
            Currency(22, rates::NZD.name, rates.NZD),
            Currency(23, rates::PHP.name, rates.PHP),
            Currency(24, rates::PLN.name, rates.PLN),
            Currency(25, rates::RON.name, rates.RON),
            Currency(26, rates::RUB.name, rates.RUB),
            Currency(27, rates::SEK.name, rates.SEK),
            Currency(28, rates::SGD.name, rates.SGD),
            Currency(29, rates::THB.name, rates.THB),
            Currency(30, rates::TRY.name, rates.TRY),
            Currency(31, rates::USD.name, rates.USD),
            Currency(32, rates::ZAR.name, rates.ZAR)
        )
    }

    fun stopFetching() {
        onCleared()
    }
}