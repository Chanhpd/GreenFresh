package com.example.greenfresh.activity.fixme

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

import androidx.constraintlayout.widget.ConstraintLayout
import com.example.greenfresh.R
import java.text.DecimalFormat

class ResultFitmeActivity : AppCompatActivity() {
    lateinit var tv_rs_tdee: TextView
    lateinit var tv_bmi : TextView
    lateinit var tv_advices: TextView
    lateinit var tv_name_advices :TextView
    lateinit var tv_fit_change : TextView

    lateinit var edt_kg_fit : EditText
    lateinit var edt_day_fit : EditText
    lateinit var btn_fit_fit : TextView
    lateinit var tv_rs_fit_calo: TextView
    lateinit var constrain_calculate_fit: ConstraintLayout
    lateinit var btn_menu_recom : TextView


    var TDEE = 0.0
    var BMI = 0.0
    var caloEachDay = 0
    var kg = 0
    var day = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result_fitme)

        initView()

        TDEE = intent.getDoubleExtra("tdee", 0.0)
        BMI = intent.getDoubleExtra("bmi",0.0)
        constrain_calculate_fit.visibility = View.GONE

        val decimalFormat = DecimalFormat("#.##")
        decimalFormat.maximumFractionDigits = 2
        val roundedNumber = decimalFormat.format(TDEE)
        tv_rs_tdee.text = roundedNumber.toString()
        var bmi_tv = ""
        if(BMI<18.5){
            bmi_tv = "Skinny person"
            tv_name_advices.text = "To weight gain"
            tv_advices.text = "It's important to focus on gaining weight in a healthy way. Consider increasing your calorie intake through nutritious foods and incorporating strength training exercises to build muscle mass. Consult with a healthcare professional or a registered dietitian for personalized guidance."
        } else if(BMI in 18.5..24.9){
            bmi_tv = "Normal person"
            tv_name_advices.text = "Keep your weight"
            tv_advices.text= "Congratulations on falling within the normal weight range! To maintain your healthy weight, continue practicing good eating habits and regular physical activity. Aim for a balanced diet consisting of fruits, vegetables, whole grains, lean proteins, and healthy fats. Stay active by engaging in exercises you enjoy and maintaining an overall active lifestyle."
        } else if(BMI > 30.0){
            bmi_tv = "Obese person"
            tv_name_advices.text = "To lose gain"
            tv_advices.text = "If you fall into the obese category, it's crucial to take steps towards weight loss to improve your health. Consider seeking guidance from a healthcare professional or a registered dietitian who can create a personalized weight loss plan for you. This may include a combination of a calorie-controlled diet, regular physical activity, and behavior modifications."

        } else if(BMI in 25.0 .. 29.9){
            bmi_tv = "Overweight person"
            tv_name_advices.text = "To lose gain"
            tv_advices.text = "If you have a BMI in this range, it's advisable to focus on losing weight to reduce health risks. Start by making small, sustainable changes to your diet and lifestyle. Incorporate more fruits, vegetables, and whole grains while reducing processed and high-calorie foods. "
        }

        tv_bmi.text = "BMI = " + decimalFormat.format(BMI).toString()+" --> $bmi_tv"

        if(BMI<21.75){
            tv_fit_change.text = "increase"

            btn_fit_fit.setOnClickListener {
                if(edt_kg_fit.text.toString()!="" && edt_day_fit.text.toString()!=""){
                    kg = edt_kg_fit.text.toString().toInt()
                    day = edt_day_fit.text.toString().toInt()
                    caloEachDay = (TDEE + ((7700.0*kg)/day)).toInt()
                    tv_rs_fit_calo.text = caloEachDay.toString()
                    constrain_calculate_fit.visibility = View.VISIBLE

                    btn_menu_recom.setOnClickListener {
                        val i = Intent(this, RecommendMenuActivity::class.java)
                        i.putExtra("caloEachDay",caloEachDay)
                        i.putExtra("fitme","Weight gain")
                        startActivity(i)
                    }

                } else{
                    Toast.makeText(this, "Please enter fields!", Toast.LENGTH_SHORT).show()
                }
            }

        } else if(BMI>21.75){
            tv_fit_change.text = "decrease"

            btn_fit_fit.setOnClickListener {
                if(edt_kg_fit.text.toString()!="" && edt_day_fit.text.toString()!=""){
                    kg = edt_kg_fit.text.toString().toInt()
                    day = edt_day_fit.text.toString().toInt()
                    caloEachDay = (TDEE - ((7700.0*kg)/day)).toInt()
                    tv_rs_fit_calo.text = caloEachDay.toString()
                    constrain_calculate_fit.visibility = View.VISIBLE

                    btn_menu_recom.setOnClickListener {
                        val i = Intent(this, RecommendMenuActivity::class.java)
                        i.putExtra("caloEachDay",caloEachDay)
                        i.putExtra("fitme","Lose gain")
                        startActivity(i)
                    }

                } else{
                    Toast.makeText(this, "Please enter fields!", Toast.LENGTH_SHORT).show()
                }
            }

        }

    }

    private fun initView() {
        tv_rs_tdee = findViewById(R.id.tv_rs_tdee)
        tv_advices = findViewById(R.id.tv_advice)
        tv_name_advices = findViewById(R.id.tv_name_advices)
        tv_bmi= findViewById(R.id.tv_bmi)
        tv_fit_change = findViewById(R.id.tv_fit_change)

        edt_kg_fit = findViewById(R.id.edt_kg_fit)
        edt_day_fit =  findViewById(R.id.edt_day_fit)
        btn_fit_fit = findViewById(R.id.btn_fit_fit)
        tv_rs_fit_calo = findViewById(R.id.tv_rs_fit_calo)

        constrain_calculate_fit = findViewById(R.id.constrain_calculate_fit)
        btn_menu_recom = findViewById(R.id.btn_menu_recom)

    }
}