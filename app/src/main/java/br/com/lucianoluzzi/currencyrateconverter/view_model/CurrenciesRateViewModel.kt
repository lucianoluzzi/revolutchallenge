package br.com.lucianoluzzi.currencyrateconverter.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import br.com.lucianoluzzi.currencyrateconverter.model.Currency
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
    private var baseCurrency: String = RatesDTO::eur.name.toUpperCase()

    suspend fun startFetchingRates(baseCurrency: String) = withContext(Dispatchers.IO) {
        val interval = Observable.interval(0, 1, TimeUnit.SECONDS)
        val disposable = interval
            .subscribe {
                val currenciesRate = repository.getCurrenciesRate(baseCurrency.toUpperCase())
                _currenciesRates.postValue(
                    Currency.getCurrencyListFromRatesDTO(baseCurrency, currenciesRate)
                )
            }
        addDisposable(disposable)
    }

    fun stopFetching() {
        onCleared()
    }
}