package com.example.greenfresh.activity.fixme

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.greenfresh.R
import com.example.greenfresh.activity.MainActivity
import com.example.greenfresh.adapter.RecommendMenuAdapter
import com.example.greenfresh.model.RecommendMenu

class RecommendMenuActivity : AppCompatActivity() {
    lateinit var recyclerViewBreaskfast: RecyclerView
    lateinit var adapterBreaskfast: RecommendMenuAdapter
    lateinit var listBreaskfast : ArrayList<RecommendMenu>

    lateinit var recyclerView_lunch : RecyclerView
    lateinit var adapterLunch: RecommendMenuAdapter
    lateinit var listLunch: ArrayList<RecommendMenu>

    lateinit var recyclerView_dinner : RecyclerView
    lateinit var adapterDinner: RecommendMenuAdapter
    lateinit var listDinner: ArrayList<RecommendMenu>
    lateinit var toolbar : Toolbar
    lateinit var tv_title_menu : TextView
    lateinit var btn_return_home: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recommed_menu)
        initView()
        tv_title_menu.text = intent.getStringExtra("fitme")
        actionToolbar()
        getListBreaskfast()
        getListLunch()
        getListDinner()

        btn_return_home.setOnClickListener {
            startActivity(Intent(this,MainActivity::class.java))
        }
    }

    private fun getListBreaskfast() {

        listBreaskfast.add(RecommendMenu(1,"Bread","https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSvfexJ7gth8jclgIElcSM7MCVCcYAYlVuxiw&usqp=CAU","100g",300))
        listBreaskfast.add(RecommendMenu(2,"Chicken Eggs","https://a-z-animals.com/media/2022/02/shutterstock_1187378770-1024x1024.jpg","1",70))
        listBreaskfast.add(RecommendMenu(3,"Milk","https://images.immediate.co.uk/production/volatile/sites/30/2020/02/Glass-and-bottle-of-milk-fe0997a.jpg?resize=960,872","1 glass",150 ))
        listBreaskfast.add(RecommendMenu(4,"Banana","https://upload.wikimedia.org/wikipedia/commons/thumb/8/8a/Banana-Single.jpg/800px-Banana-Single.jpg","1 fruit",100))
    }

    private fun getListLunch() {

        listLunch.add(RecommendMenu(1,"Rice","https://www.marthastewart.com/thmb/ts80O6Fy1XyD66-QKjM9AweAh4o=/1500x0/filters:no_upscale():max_bytes(150000):strip_icc()/perfect-white-rice-7-ef73aef3b89c42008d409441071502fb.jpg","150g",200 ))
        listLunch.add(RecommendMenu(2,"BBQ Chicken","https://i-giadinh.vnecdn.net/2022/02/11/Buoc-8-8-4440-1644565411.jpg","100g",165))
        listLunch.add(RecommendMenu(3,"Salad","https://cdn.tgdd.vn/Files/2022/01/24/1412588/salad-la-gi-15-mon-salad-dinh-duong-cho-bua-com-gia-dinh-202201241950443123.jpg","",50 ))
        listLunch.add(RecommendMenu(4,"Orange","https://thumbs.dreamstime.com/b/orange-13642243.jpg","1 fruit",60 ))
    }

    private fun getListDinner() {

        listDinner.add(RecommendMenu(1,"Rice","https://www.marthastewart.com/thmb/ts80O6Fy1XyD66-QKjM9AweAh4o=/1500x0/filters:no_upscale():max_bytes(150000):strip_icc()/perfect-white-rice-7-ef73aef3b89c42008d409441071502fb.jpg","150g",200))
        listDinner.add(RecommendMenu(2,"Tomato","https://upload.wikimedia.org/wikipedia/commons/8/89/Tomato_je.jpg","100g",100 ))
        listDinner.add(RecommendMenu(3,"Grilled salmon","https://www.acouplecooks.com/wp-content/uploads/2020/05/Grilled-Salmon-015-1.jpg","1 glass",150 ))
        listDinner.add(RecommendMenu(4,"Grape","https://img.freepik.com/premium-vector/isolated-dark-grape-with-green-leaf_317810-1956.jpg?w=2000","1 fruit",70 ))
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
        recyclerViewBreaskfast = findViewById(R.id.recycleView_breafast)
        listBreaskfast = ArrayList()
        adapterBreaskfast = RecommendMenuAdapter(this,listBreaskfast)
        recyclerViewBreaskfast.layoutManager = LinearLayoutManager(applicationContext, LinearLayoutManager.HORIZONTAL, false)
        recyclerViewBreaskfast.adapter = adapterBreaskfast

        recyclerView_lunch = findViewById(R.id.recyclerView_lunch)
        listLunch = ArrayList()
        adapterLunch = RecommendMenuAdapter(this,listLunch)
        recyclerView_lunch.layoutManager = LinearLayoutManager(applicationContext, LinearLayoutManager.HORIZONTAL, false)
        recyclerView_lunch.adapter = adapterLunch

        recyclerView_dinner = findViewById(R.id.recycleView_dinner)
        listDinner = ArrayList()
        adapterDinner = RecommendMenuAdapter(this,listDinner)
        recyclerView_dinner.layoutManager = LinearLayoutManager(applicationContext, LinearLayoutManager.HORIZONTAL, false)
        recyclerView_dinner.adapter = adapterDinner

        tv_title_menu = findViewById(R.id.tv_title_menu)
        toolbar = findViewById(R.id.toolbar2)
        btn_return_home = findViewById(R.id.btn_return_home)
    }
}