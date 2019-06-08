package br.com.lucianoluzzi.currencyrateconverter.repository.dto

import com.google.gson.annotations.SerializedName
import java.math.BigDecimal

class RatesDTO(
    @SerializedName("AUD") val aud: BigDecimal,
    @SerializedName("BGN") var bgn: BigDecimal,
    @SerializedName("BRL") var brl: BigDecimal,
    @SerializedName("CAD") var cad: BigDecimal,
    @SerializedName("CHF") var chf: BigDecimal,
    @SerializedName("CNY") var cny: BigDecimal,
    @SerializedName("CZK") var czk: BigDecimal,
    @SerializedName("DKK") var dkk: BigDecimal,
    @SerializedName("EUR") var eur: BigDecimal,
    @SerializedName("GBP") var gbp: BigDecimal,
    @SerializedName("HKD") var hkd: BigDecimal,
    @SerializedName("HRK") var hrk: BigDecimal,
    @SerializedName("HUF") var huf: BigDecimal,
    @SerializedName("IDR") var idr: BigDecimal,
    @SerializedName("ILS") var ils: BigDecimal,
    @SerializedName("INR") var inr: BigDecimal,
    @SerializedName("ISK") var isk: BigDecimal,
    @SerializedName("JPY") var jpy: BigDecimal,
    @SerializedName("KRW") var krw: BigDecimal,
    @SerializedName("MXN") var mxn: BigDecimal,
    @SerializedName("MYR") var myr: BigDecimal,
    @SerializedName("NOK") var nok: BigDecimal,
    @SerializedName("NZD") var nzd: BigDecimal,
    @SerializedName("PHP") var php: BigDecimal,
    @SerializedName("PLN") var pln: BigDecimal,
    @SerializedName("RON") var ron: BigDecimal,
    @SerializedName("RUB") var rub: BigDecimal,
    @SerializedName("SEK") var sek: BigDecimal,
    @SerializedName("SGD") var sgd: BigDecimal,
    @SerializedName("THB") var thb: BigDecimal,
    @SerializedName("TRY") var `try`: BigDecimal,
    @SerializedName("USD") var usd: BigDecimal,
    @SerializedName("ZAR") var zar: BigDecimal
)