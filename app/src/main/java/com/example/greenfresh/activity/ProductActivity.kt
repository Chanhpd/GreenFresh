package com.example.greenfresh.activity

import android.content.Intent
import android.graphics.PorterDuff
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.*
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import com.android.volley.AuthFailureError
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.greenfresh.R
import com.example.greenfresh.activity.profile.ProfileActivity
import com.example.greenfresh.adapter.BestSellerAdapter
import com.example.greenfresh.adapter.CategoryProductAdapter
import com.example.greenfresh.adapter.PhotoAdapter
import com.example.greenfresh.adapter.ProductAdapter
import com.example.greenfresh.model.Cart
import com.example.greenfresh.model.Category
import com.example.greenfresh.model.Product
import com.example.greenfresh.utils.Server
import me.relex.circleindicator.CircleIndicator3
import org.json.JSONArray
import org.json.JSONObject
import kotlin.math.abs

class ProductActivity : AppCompatActivity() {


    lateinit var recyclerView: RecyclerView
    lateinit var recyclerViewCate: RecyclerView
    lateinit var adapterProduct: ProductAdapter
    lateinit var arrPro: ArrayList<Product>
    lateinit var arrCate: ArrayList<Category>
    lateinit var adapterCate: CategoryProductAdapter
    lateinit var btnSearch: ImageView
    lateinit var editSearch: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product)

        initView()

        getProduct()
        bottomNavagation()
        getCategoryProduct()

        btnSearch.setOnClickListener {
            var search = editSearch.text.toString().trim()
            getProductSearch(search,"1")
        }

    }



    private fun getCategoryProduct() {
        var requestQueue: RequestQueue = Volley.newRequestQueue(applicationContext)
        var jsonArrayRequest = JsonArrayRequest(Server.linkCategory, { response ->
            var id = 0
            var name = ""
            var thumb = ""

            if (response != null) {
                arrCate.add(Category(0, "All", ""))
                for (i in 0 until response.length()) {
                    var jsonObject: JSONObject? = response.getJSONObject(i)
                    id = jsonObject!!.getInt("id")
                    name = jsonObject!!.getString("name")
                    thumb = jsonObject!!.getString("thumb")

                    arrCate.add(Category(id, name, thumb))
                    adapterCate.notifyDataSetChanged()
                }
            }
        }, { error ->
            Toast.makeText(applicationContext, error.toString(), Toast.LENGTH_SHORT).show()
            Log.d("DDDD", error.toString())
        })
        requestQueue.add(jsonArrayRequest)
    }


    private fun getProduct() {
        var requestQueue: RequestQueue = Volley.newRequestQueue(applicationContext)
        var jsonArrayRequest = JsonArrayRequest(Server.linkProduct, { response ->
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
        }, { error ->
            Toast.makeText(applicationContext, error.toString(), Toast.LENGTH_SHORT).show()
            Log.d("DDDD", error.toString())
        })
        requestQueue.add(jsonArrayRequest)
    }

    private fun getProductSearch(search: String, idCate : String) {
        val requestQueue: RequestQueue = Volley.newRequestQueue(this)
        val link: String = Server.linkProduct
        arrPro.clear()
        val stringRequest =
            object : StringRequest(Method.POST, link, { response ->
                var id = 0
                var name = ""
                var thumb = ""
                var description = ""
                var price = 0.0
                var calories = 0
                var sale = 0
                var unit = ""

                if (response != null && response.length != 2) {

                    val jsonArray: JSONArray = JSONArray(response)
                    for (i in 0 until jsonArray.length()) {
                        val jsonObject: JSONObject = jsonArray.getJSONObject(i)
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
                } else if(response.length==2){
                    arrPro.clear()
                    adapterProduct.notifyDataSetChanged()
                    Toast.makeText(this, "No product found !", Toast.LENGTH_SHORT).show()
                }
            }, { error ->
                Log.d("AAA", error.toString())
            }) {
                @Throws(AuthFailureError::class)
                override fun getParams(): Map<String, String> {
                    val params = HashMap<String, String>()
                    params["search"] = search
                    params["id_cate"] = idCate
                    Log.d("TAG", "getParams: $search : $idCate")
                    return params
                }
            }
        requestQueue.add(stringRequest)
    }


    private fun initView() {
        recyclerView = findViewById(R.id.view_product)
        arrPro = ArrayList()
        adapterProduct = ProductAdapter(this, arrPro)

        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = GridLayoutManager(applicationContext, 2)
        recyclerView.adapter = adapterProduct

        recyclerViewCate = findViewById(R.id.view_cate_pro)
        arrCate = ArrayList()
        adapterCate = CategoryProductAdapter(arrCate){
            getProductSearch(editSearch.text.toString().trim(),it.id.toString())
        }
        recyclerViewCate.layoutManager =
            LinearLayoutManager(applicationContext, LinearLayoutManager.HORIZONTAL, false)
        recyclerViewCate.adapter = adapterCate


        btnSearch = findViewById(R.id.btn_search)
        editSearch = findViewById(R.id.edtSearch)
    }

    private fun bottomNavagation() {
        // Set the color filter on the drawable
        val drawable = resources.getDrawable(R.drawable.ic_baseline_store, null)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            drawable.setColorFilter(resources.getColor(R.color.green, null), PorterDuff.Mode.SRC_IN)
        }

        val img: ImageView = findViewById(R.id.imgViewProfile)
        val text: TextView = findViewById(R.id.tvDiscover)
        img.setImageDrawable(drawable)
        text.setTextColor(ContextCompat.getColor(applicationContext, R.color.green))

        var btnHome: LinearLayout = findViewById(R.id.btn_home_bottom)
        var btnDiscover: LinearLayout = findViewById(R.id.btn_discover_bottom)
        var btnCart : LinearLayout = findViewById(R.id.btn_cart_bottom)
        var btnProfile : LinearLayout = findViewById(R.id.btn_profile_bottom)
        btnHome.setOnClickListener {
            startActivity(Intent(applicationContext, MainActivity::class.java))
        }
//        btnDiscover.setOnClickListener {
//            startActivity(Intent(applicationContext, ProductActivity::class.java))
//
//        }
        btnCart.setOnClickListener {
            startActivity(Intent(applicationContext,CartActivity::class.java))
        }
        btnProfile.setOnClickListener {
            startActivity(Intent(applicationContext, ProfileActivity::class.java))
        }
    }


}