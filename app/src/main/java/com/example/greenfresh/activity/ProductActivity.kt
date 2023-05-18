package com.example.greenfresh.activity

import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.PorterDuff
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.ViewGroup
import android.view.Window
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.AuthFailureError
import com.android.volley.RequestQueue
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.greenfresh.R
import com.example.greenfresh.activity.fixme.StartFixMeActivity
import com.example.greenfresh.activity.profile.ProfileActivity
import com.example.greenfresh.adapter.CategoryProductAdapter
import com.example.greenfresh.adapter.ProductAdapter
import com.example.greenfresh.model.Category
import com.example.greenfresh.model.Product
import com.example.greenfresh.utils.Server
import org.json.JSONArray
import org.json.JSONObject

class ProductActivity : AppCompatActivity() {


    lateinit var recyclerView: RecyclerView
    lateinit var recyclerViewCate: RecyclerView
    lateinit var adapterProduct: ProductAdapter
    lateinit var arrPro: ArrayList<Product>
    lateinit var arrCate: ArrayList<Category>
    lateinit var adapterCate: CategoryProductAdapter
    lateinit var btnSearch: ImageView
    lateinit var editSearch: EditText
    lateinit var pic_filter: ImageView


    companion object {
        var sort = ""
        var search = ""
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product)

        initView()

        getProduct()

        bottomNavagation()
        getCategoryProduct()

        btnSearch.setOnClickListener {
            search = editSearch.text.toString().trim()
            getProductSearch(search, "1")
        }
        pic_filter.setOnClickListener {
            showDialogFilter()
        }
    }

    private fun showDialogFilter() {
        val dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.dialog_filter_product)

        // handle in here
        val btn_canel: TextView = dialog.findViewById(R.id.btn_cancel_dialog)

        val tv_sort_name_acs: TextView = dialog.findViewById(R.id.tv_sort_name_acs)
        val tv_sort_name_des: TextView = dialog.findViewById(R.id.tv_sort_name_des)
        val tv_sort_sale: TextView = dialog.findViewById(R.id.tv_sort_sale)
        val tv_price_high: TextView = dialog.findViewById(R.id.tv_price_high)
        val tv_price_low: TextView = dialog.findViewById(R.id.tv_price_low)

        tv_sort_name_acs.setOnClickListener {
            sort = "1"
            getProduct()
            Toast.makeText(applicationContext, "Sort by name from A-Z", Toast.LENGTH_LONG).show()
            dialog.dismiss()
        }
        tv_sort_name_des.setOnClickListener {
            sort = "2"
            getProduct()
            Toast.makeText(applicationContext, "Sort by name from Z-A", Toast.LENGTH_LONG).show()
            dialog.dismiss()
        }
        tv_sort_sale.setOnClickListener {
            sort = "3"
            getProduct()
            Toast.makeText(applicationContext, "Sort by Best Seller", Toast.LENGTH_LONG).show()
            dialog.dismiss()
        }
        tv_price_high.setOnClickListener {
            sort = "4"
            getProduct()
            Toast.makeText(applicationContext, "Sort by price highest", Toast.LENGTH_LONG).show()
            dialog.dismiss()
        }
        tv_price_low.setOnClickListener {
            sort = "5"
            getProduct()
            Toast.makeText(applicationContext, "Sort by price lowest", Toast.LENGTH_LONG).show()
            dialog.dismiss()
        }

        btn_canel.setOnClickListener {
            dialog.dismiss()
        }


        dialog.show()
        dialog.window?.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.window?.attributes?.windowAnimations = R.style.DialogAnimation
        dialog.window?.setGravity(Gravity.BOTTOM)
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
                var star = 0.0
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
                        star = jsonObject!!.getDouble("star")
                        arrPro.add(
                            Product(
                                id,
                                name,
                                thumb,
                                description,
                                price,
                                calories,
                                sale,
                                unit,
                                star
                            )
                        )

                        adapterProduct.notifyDataSetChanged()

                    }
                } else if (response.length == 2) {
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
                    if (sort != "") {
                        params["sort"] = sort
                    }
                    return params
                }
            }
        requestQueue.add(stringRequest)
    }

    private fun getProductSearch(search: String, idCate: String) {
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
                var star = 0.0
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
                        star = jsonObject!!.getDouble("star")
                        arrPro.add(
                            Product(
                                id,
                                name,
                                thumb,
                                description,
                                price,
                                calories,
                                sale,
                                unit,
                                star
                            )
                        )

                        adapterProduct.notifyDataSetChanged()

                    }
                } else if (response.length == 2) {
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
                    if (sort != "") {
                        params["sort"] = sort

                    }
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
        adapterCate = CategoryProductAdapter(arrCate) {
            getProductSearch(editSearch.text.toString().trim(), it.id.toString())
        }
        recyclerViewCate.layoutManager =
            LinearLayoutManager(applicationContext, LinearLayoutManager.HORIZONTAL, false)
        recyclerViewCate.adapter = adapterCate


        btnSearch = findViewById(R.id.btn_search)
        editSearch = findViewById(R.id.edtSearch)
        pic_filter = findViewById(R.id.pic_filter)
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
        var btnCart: LinearLayout = findViewById(R.id.btn_cart_bottom)
        var btnProfile: LinearLayout = findViewById(R.id.btn_profile_bottom)
        var btnFixMe : LinearLayout = findViewById(R.id.supportBtn)
        btnFixMe.setOnClickListener {
            startActivity(Intent(applicationContext, StartFixMeActivity::class.java))
        }
        btnHome.setOnClickListener {
            startActivity(Intent(applicationContext, MainActivity::class.java))
        }
//        btnDiscover.setOnClickListener {
//            startActivity(Intent(applicationContext, ProductActivity::class.java))
//
//        }
        btnCart.setOnClickListener {
            startActivity(Intent(applicationContext, CartActivity::class.java))
        }
        btnProfile.setOnClickListener {
            startActivity(Intent(applicationContext, ProfileActivity::class.java))
        }
    }


}