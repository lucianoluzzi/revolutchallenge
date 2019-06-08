package br.com.lucianoluzzi.currencyrateconverter.model

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
        val disposable = interval.subscribe {
            val currenciesRate = repository.getCurrenciesRate(baseCurrency)
            _currenciesRates.postValue(getCurrencyListFromResponse(currenciesRate))
        }
        addDisposable(disposable)
    }

    private fun getCurrencyListFromResponse(rates: RatesDTO): List<Currency> {
        return listOf(
            Currency(1, rates::aud.name, rates.aud),
            Currency(2, rates::bgn.name, rates.bgn),
            Currency(3, rates::brl.name, rates.brl),
            Currency(4, rates::cad.name, rates.cad),
            Currency(5, rates::chf.name, rates.chf),
            Currency(6, rates::cny.name, rates.cny),
            Currency(7, rates::czk.name, rates.czk),
            Currency(8, rates::dkk.name, rates.dkk),
            Currency(9, rates::gbp.name, rates.gbp),
            Currency(10, rates::hkd.name, rates.hkd),
            Currency(11, rates::hrk.name, rates.hrk),
            Currency(12, rates::huf.name, rates.huf),
            Currency(13, rates::idr.name, rates.idr),
            Currency(14, rates::ils.name, rates.ils),
            Currency(15, rates::inr.name, rates.inr),
            Currency(16, rates::isk.name, rates.isk),
            Currency(17, rates::jpy.name, rates.jpy),
            Currency(18, rates::krw.name, rates.krw),
            Currency(19, rates::mxn.name, rates.mxn),
            Currency(20, rates::myr.name, rates.myr),
            Currency(21, rates::nok.name, rates.nok),
            Currency(22, rates::nzd.name, rates.nzd),
            Currency(23, rates::php.name, rates.php),
            Currency(24, rates::pln.name, rates.pln),
            Currency(25, rates::ron.name, rates.ron),
            Currency(26, rates::rub.name, rates.rub),
            Currency(27, rates::sek.name, rates.sek),
            Currency(28, rates::sgd.name, rates.sgd),
            Currency(29, rates::thb.name, rates.thb),
            Currency(30, rates::`try`.name, rates.`try`),
            Currency(31, rates::usd.name, rates.usd),
            Currency(32, rates::zar.name, rates.zar)
        )
    }

    fun stopFetching() {
        onCleared()
    }
}