package com.example.greenfresh.activity

import android.content.Intent
import android.graphics.PorterDuff
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.*
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import com.example.greenfresh.R
import com.example.greenfresh.adapter.BestSellerAdapter
import com.example.greenfresh.adapter.CategoryProductAdapter
import com.example.greenfresh.model.Category
import com.example.greenfresh.model.Product
import com.example.greenfresh.utils.Server
import org.json.JSONObject

class ProductActivity : AppCompatActivity() {
    lateinit var recyclerView: RecyclerView
    lateinit var recyclerViewCate: RecyclerView
    lateinit var adapterProduct: BestSellerAdapter
    lateinit var arrPro: ArrayList<Product>
    lateinit var arrCate: ArrayList<Category>
    lateinit var adapterCate: CategoryProductAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product)

        initView()
        getProduct()
        bottomNavagation()
        getCategoryProduct()

    }

    private fun getCategoryProduct() {
        var requestQueue: RequestQueue = Volley.newRequestQueue(applicationContext)
        var jsonArrayRequest = JsonArrayRequest(Server.linkCategory, Response.Listener { response ->
            var id = 0
            var name = ""
            var thumb = ""

            if (response != null) {

                for (i in 0 until response.length()) {
                    var jsonObject: JSONObject? = response.getJSONObject(i)
                    id = jsonObject!!.getInt("id")
                    name = jsonObject!!.getString("name")
                    thumb = jsonObject!!.getString("thumb")

                    arrCate.add(Category(id, name, thumb))
                    adapterCate.notifyDataSetChanged()
                }
            }
        }, Response.ErrorListener { error ->
            Toast.makeText(applicationContext, error.toString(), Toast.LENGTH_SHORT).show()
            Log.d("DDDD", error.toString())
        })
        requestQueue.add(jsonArrayRequest)
    }


    private fun getProduct() {
        var requestQueue: RequestQueue = Volley.newRequestQueue(applicationContext)
        var jsonArrayRequest = JsonArrayRequest(Server.linkSeller, Response.Listener { response ->
            var id = 0
            var name = ""
            var thumb = ""
            var description = ""
            var price = 0.0
            var calories = 0
            var sale = 0
            var unit = ""
            if (response != null) {

                for (i in 0 until response.length()) {
                    var jsonObject: JSONObject? = response.getJSONObject(i)
                    id = jsonObject!!.getInt("id")
                    name = jsonObject!!.getString("name")
                    thumb = jsonObject!!.getString("thumb")
                    description = jsonObject!!.getString("description")
                    price = jsonObject!!.getDouble("price")
                    calories = jsonObject!!.getInt("calories")
                    sale = jsonObject!!.getInt("sale")
                    unit = jsonObject!!.getString("unit")

                    arrPro.add(Product(id, name, thumb, description, price, calories, sale, unit))
                    adapterProduct.notifyDataSetChanged()
                }
            }
        }, Response.ErrorListener { error ->
            Toast.makeText(applicationContext, error.toString(), Toast.LENGTH_SHORT).show()
            Log.d("DDDD", error.toString())
        })
        requestQueue.add(jsonArrayRequest)
    }


    private fun initView() {
        recyclerView = findViewById(R.id.view_product)


        arrPro = ArrayList()
        adapterProduct = BestSellerAdapter(this,arrPro)

        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = GridLayoutManager(applicationContext, 2)
        recyclerView.adapter = adapterProduct

        recyclerViewCate = findViewById(R.id.view_cate_pro)
        arrCate = ArrayList()
        adapterCate = CategoryProductAdapter(arrCate)
        recyclerViewCate.layoutManager =
            LinearLayoutManager(applicationContext, LinearLayoutManager.HORIZONTAL, false)
        recyclerViewCate.adapter = adapterCate
    }

    private fun bottomNavagation() {
        // Set the color filter on the drawable
        val drawable = resources.getDrawable(R.drawable.ic_baseline_wb_sunny_24, null)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            drawable.setColorFilter(resources.getColor(R.color.green, null), PorterDuff.Mode.SRC_IN)
        }

        val img: ImageView = findViewById(R.id.imgViewProfile)
        val text: TextView = findViewById(R.id.tvDiscover)
        img.setImageDrawable(drawable)
        text.setTextColor(ContextCompat.getColor(applicationContext, R.color.green))

        var btnHome: LinearLayout = findViewById(R.id.btn_home_bottom)
        var btnDiscover: LinearLayout = findViewById(R.id.btn_discover_bottom)

        btnHome.setOnClickListener {
            startActivity(Intent(applicationContext, MainActivity::class.java))
        }
        btnDiscover.setOnClickListener {
            startActivity(Intent(applicationContext, ProductActivity::class.java))

        }
    }
}