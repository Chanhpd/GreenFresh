package com.example.greenfresh.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.widget.Toolbar
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.AuthFailureError
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.greenfresh.R
import com.example.greenfresh.adapter.CheckoutAdapter
import com.example.greenfresh.api.LoginApi
import com.example.greenfresh.model.Cart
import com.example.greenfresh.utils.Server
import org.json.JSONArray
import org.json.JSONObject

class CheckoutActivity : AppCompatActivity() {
    lateinit var recyclerViewCheckout: RecyclerView
    lateinit var adapterCheckout: CheckoutAdapter
    lateinit var listPro : ArrayList<Cart>
    lateinit var toolbar: Toolbar
    lateinit var btn_payment: ConstraintLayout
    var uid : Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_checkout)

        initView()
        actionToolbar()
        uid = LoginApi().getIdUser(this)
        getData()

        btn_payment.setOnClickListener {
            startActivity(Intent(this, PaymentActivity::class.java))
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

    private fun initView() {
        recyclerViewCheckout = findViewById(R.id.recycleView_checkout)
        listPro = ArrayList()
        toolbar = findViewById(R.id.toolbar_edit_pro)
        btn_payment = findViewById(R.id.btn_save_pass)

    }
    private fun getData() {
        val requestQueue: RequestQueue = Volley.newRequestQueue(this)
        val link: String = Server.linkGetCart
        val stringRequest =
            object : StringRequest(Method.POST, link, { response ->
                var id = 0
                var name = ""
                var thumb = ""
                var price = 0.0
                var num = 0
                if (response != null && response.length != 2) {
                    Log.d("getCart", "getCart: $response")
                    val jsonArray = JSONArray(response)
                    for (i in 0 until jsonArray.length()) {
                        val jsonObject: JSONObject = jsonArray.getJSONObject(i)
                        id = jsonObject.getInt("id")
                        name = jsonObject.getString("name")
                        thumb = jsonObject.getString("thumb")
                        price = jsonObject.getDouble("price")
                        num = jsonObject.getInt("num")

                        listPro.add(Cart(id, name, thumb, price, num))
                        Log.d("FFF", "getCart: $name")
                    }
                    adapterPush(listPro)

                }
            }, { error ->
                Log.d("AAA", error.toString())
            }) {
                @Throws(AuthFailureError::class)
                override fun getParams(): Map<String, String> {
                    val params = HashMap<String, String>()
                    params["uid"] = uid.toString()
                    return params
                }
            }
        requestQueue.add(stringRequest)
    }
    private fun adapterPush(list: ArrayList<Cart>) {
        adapterCheckout = CheckoutAdapter(this, list)
        recyclerViewCheckout.layoutManager = LinearLayoutManager(this)
        recyclerViewCheckout.adapter = adapterCheckout
    }
}