package com.example.greenfresh.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.greenfresh.R
import com.example.greenfresh.adapter.PaymentAdapter
import com.example.greenfresh.model.Payment

class PaymentActivity : AppCompatActivity() {
    lateinit var recyclerView: RecyclerView
    lateinit var adapter: PaymentAdapter
    lateinit var list : ArrayList<Payment>
    lateinit var toolbar: Toolbar
    lateinit var btnConfirm : ConstraintLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_payment)

        initView()
        getPayment()
        actionToolbar()
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        btnConfirm.setOnClickListener {
            startActivity(Intent(this, SuccessOrderActivity::class.java))
        }
    }
    private fun actionToolbar() {
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowTitleEnabled(false)
        toolbar.setNavigationOnClickListener {
            onBackPressed()
        }
    }

    private fun getPayment() {
        list.add(Payment("Paypal","data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAOEAAADhCAMAAAAJbSJIAAAA8FBMVEX///8ALIoAm+EAH2sAKYkAluAUM4wAKIEAEYPJzN4AnOKq0/EAoegAmeAAIW0AK4oAFYMAJ4gAIIYAGYQAHoUAG2gAFWUAAIAAJHcAKoUAI4UAk99wgrYAkdgAFGQAEGPu+P0AC4Fhda7Q1ueos9GBxe1XtekAhMkAJXoAJnEAZKkAPIMAe8GcqMz0+/7Y7fkAM3wAX64AQ5oAecXm9fzm6/SBj7srSpk2U51JYqW0vtiMmsMgPZF7irnd4/AwS5hHXaBZaqcAAGi43/UAbLFDr+YASo94wuwAVpwfpOOz3PQAcL0AVqcAOpMASJ6d0PFN2r4kAAAHeklEQVR4nO2djVraShCGTSQWU9IQYKOGaoKILYpoe0T7Y62totZzrPd/NydAVZCfnYTdmdBn3ivwdXe/nUx2w9ISwzAMwzAMwzAMwzAMwzAMwzAMwzCMQjonpyvpOP30eftk50u9Q60wm4NaxculxAtKlXIxv1b+9O2gHlGbTGGnZijAK5Vr3o+v9Ta1zjjtsqvCsIcbVIpnb1epjV6yU1Ql2CdXyhsHEbXUCNslpYY9yXL5W5ai52NOtWFMpXKSnQVZUbYMh3HLK1+ozf5wri5oRvHWtqndBqyqDZphyt/Pqe167JS1GRqBUafWi3mrPEqH8IoZULwINBoauSL5RG2feToNDe+Memfs5DRF6SOVH8SG9bxeQcOoHdAaKq5KJ5GnnacnFe2Gpbekhttao7SPW6bM0/Z3vVHap0I5iFGgOUp7uAbhSjyv6Rc0jOIOneEX/VEaExDuiQca6+5n3CLd47DWuvuZPF136hQhSmMqX8kM8whRGhNcUAl21lAEDbdEZbiqve7+wxqVoc4Wxgg1qsINKUrjMKXqZnzCidK4qqFqnq7o6HdnybCDUXeTGtaBQeMmJDuG0Lp741UCNnoM/i/0hpAWhuuuL6dhff19z9OlNfwBaGFspPJ78nw1GEqq0huyWaQbwVFJsv2wY0ij1H01r2BfcqNG08c4B1Slcw/hgJ+HJIaAKE0ZM2M0rZuIwPArIErVCIr9gmVf4htC6m5FhrcF07Ea6IbyKHXn2yue8K9s0zStXWxD+Ys1NVG6vBy+6xmaVhdXENLCUBQ0odM3xB5FSAtDVZQ65gDctQjpBqsRFNe2+aiImajf0KLU3ys8Gjq/ENvfeFH6J2gGg/iAZyifpKqitLn5bGhaaIPYqckn6XslgnFFY5oEgwg5haEmSoeWYX8lYhlCWhhv1BgOLcPeIGI9Z0BaGEoERXNE0LRukAw/y1sYaqLUvy2MGDpYtZv81aGiKA1HJ2msGKEIQloYSqJUvBkdQtOs4tQ1kNPPSqJ0NEkR9wu0Foa/ab80xCm/sVoYQ1X30zrECVPARRIlUbr1MmdiQ5ynxAuculvsjwkibRfRvfTVoZLNImxRGXYANZuCzUIcvQxSNEOkFsbYbo+3DnFaGOJ6whAiGeLU3WJsL+wbouyHKHV3eDVpCJFqGowXa5N2ir7hHYJgG9ANnjtKx+u1AVWMR2CMFkbYmjhH4zE8RjCEXCSZs4WxNfZM8Rg0KC1T/XW3P15xPxqibBbao1TsO9MMcR6e5Afa5otScTRVEOfVRVt+gXuuFoY/fQRjMIKmrreFsTVLEKcjDLnAnT5Kw2t7xgjiPOHrjFIRbxMzBHEqGp0tDL/5bso++GiIsQw1vjoMr2dlDNoyjM40bRZbzdbMGWpi7YbngK99pIhSX+zNipgBKGU3pIWRuBss/OXbzdkrEG+SgloYyTYLETb3NmUTFG+Sqj6FIfxwv2VC/NBejwJOP0OjVIhQ7F/Fwwfyw3p32FYTpUL4W1tHty2wnom13S+1i+nrbhGLCT9WC8Pm9V5r00mgh3dKoV6TL8N14Ys3E2g2j/avb/euWpumbSey6w8h0kkTyJ080bxyCtOxk7oNhtDGEYTU3e4RKPsTgrQKQVGam/S+YV7wzgoBWhj38uokOWhHhZbk3WDvgwZDtJNCkG9heP+qn6ROAe1QIuAURvCPekOch4o+gLo7mPLGYQ4wT85CPucpeVBPIYi2CJcgB9pyHxX74V5DaK9Iq9Lcf4qjFPcqyXlJauh9UDtJrS7q92kA3WDFUYp93wkSpSprNqeKfWcNcidP4Wbh4N87lNfd7r0j/8uBftVdlBb3MG3AZvFbUZQ6lo31vDRERz5JFdXdjuU0KL7xBWhhBCrq7nj8GhGBH+gURjDhxGRSvWr3NY0f6ECbN1eUOrGe3aC5f99H/jlP10gp6MRYjt19INSLOVXawnD6WD2qlv1r9+bukPx7+vJuMLDujidj1Sn86u7u3jQaDw93h8cRuVwPZS0Mq3sZUctMRFULg+ADCUAU1d14rc/EAFoYJVNuaL2mFpkK4BdJVgCTtIpeT4MBROlvwCR1qD2mAvisEKTuRrsGmhxACwOyWSDdPksD4NUhpO7GbO8mBNDCgPS7Ub/fkQz5nTxjBdDvxjl9lwr5L5LkPgKWoR1Ri0yj4ympuzMcpXVAlAL63UjXXNMAaWEA6u7slt2gFgag7s5wlEJ+kQSwWTi0bYpZAE4/3y90lEbyb9BB+t0ZfjiEtDAgm0V2oxTSwgDU3RmuSgEH2kCbRXajVFHdjXgyJimAXwIMAL3SKrXHdORtKEi/O8NRurS6JutDgeru7EZpXJcG+dmsAeruDEdpTGdVQlf+Ch/tpK8eAIYZjlIIttzQycQrprQcS/0yHaUADi25YZajVM6d3DDDD/gQGgDDxY7SXcBmsdhRKt8sHDu73WAAUUFuuNhRevzXR+nlXx+lD399lEI2i4UOGoAh6s0QDRxakizF/g0O9dxVrZmQHPpVy/HrGVwuvh/DMAzDMAzDMAzDMAzDMAzDMAzDMIvM/43k/caUpKRiAAAAAElFTkSuQmCC"))
        list.add(Payment("Google Pay","https://upload.wikimedia.org/wikipedia/commons/thumb/5/53/Google_%22G%22_Logo.svg/2008px-Google_%22G%22_Logo.svg.png"))
        list.add(Payment("Apple Pay","data:image/png;base64,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"))
        list.add(Payment("**** **** **** 4567","https://upload.wikimedia.org/wikipedia/commons/thumb/2/2a/Mastercard-logo.svg/1280px-Mastercard-logo.svg.png"))

//        list.add(Payment("Google Pay",""))

    }

    private fun initView() {
        recyclerView = findViewById(R.id.recycle_payment)
        list = ArrayList()
        adapter = PaymentAdapter(this,list)
        toolbar = findViewById(R.id.toolbar_payment)
        btnConfirm =findViewById(R.id.btn_save_pass)
    }
}