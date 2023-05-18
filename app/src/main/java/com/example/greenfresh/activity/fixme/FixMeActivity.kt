package com.example.greenfresh.activity.fixme

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.EditText
import android.widget.RadioButton
import android.widget.TextView
import android.widget.Toast
import com.example.greenfresh.R

class FixMeActivity : AppCompatActivity() {
    lateinit var edt_fit_age: EditText
    lateinit var edt_fit_height: EditText
    lateinit var edt_fit_weight: EditText

    lateinit var radioMale: RadioButton
    lateinit var radioFemale: RadioButton
    lateinit var btn_calculate_fit: TextView

    lateinit var auto_complete: AutoCompleteTextView

    lateinit var list: ArrayList<String>
    lateinit var adapter: ArrayAdapter<String>
    var age = 0
    var height = 0
    var weight = 0
    var gender = false
    var frequency = 0.0

    var BMR: Double = 0.0
    var TDEE: Double = 0.0
    var BMI : Double = 0.0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fix_me)

        initView()

        getDataItemFrequency()
        adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, list)
        auto_complete.setAdapter(adapter)

        auto_complete.setOnItemClickListener { parent, view, position, id ->
            btn_calculate_fit.setOnClickListener {
                if (edt_fit_age.text.toString() != ""
                    && edt_fit_height.text.toString() != ""
                    && edt_fit_weight.text.toString() != ""
                    && !auto_complete.isActivated
                ) {
                    age = edt_fit_age.text.toString().toInt()
                    height = edt_fit_height.text.toString().toInt()
                    weight = edt_fit_weight.text.toString().toInt()

                    if (radioMale.isChecked) {
                        BMR = 88.36 + (13.4 * weight) + (4.8 * height) - (5.7 * age)

                    } else if (radioFemale.isChecked) {
                        BMR = 447.6 + (9.2 * weight) + (3.1 * height) - (4.3 * age)

                    }
                    when (position) {
                        0 -> frequency = 1.2
                        1 -> frequency = 1.375
                        2 -> frequency = 1.55
                        3 -> frequency = 1.725
                        4 -> frequency = 1.9
                    }
                    TDEE = frequency * BMR
                    var heightInMeters : Double = height/100.0
                    BMI = weight/ (heightInMeters*heightInMeters)


                    val i = Intent(this, ResultFitmeActivity::class.java)
                    i.putExtra("tdee", TDEE)

                    i.putExtra("bmi", BMI)
                    startActivity(i)

                } else {
                    Toast.makeText(this, "Please enter all fields!", Toast.LENGTH_SHORT).show()
                }
            }
        }


    }

    private fun getDataItemFrequency() {

        list.add("Less activity")
        list.add("Light activity (1-3 times per week)")
        list.add("Moderate activity (3-5 times per week)")
        list.add("High activity (6-7 times per week)")
        list.add("Very high activity (daily or intense workouts)")

    }

    private fun initView() {
        edt_fit_age = findViewById(R.id.edt_fit_age)
        edt_fit_height = findViewById(R.id.edt_fit_height)
        edt_fit_weight = findViewById(R.id.edt_fit_weight)

        radioMale = findViewById(R.id.radio_male)
        radioFemale = findViewById(R.id.radio_female)
        btn_calculate_fit = findViewById(R.id.btn_calculate_fit)
        auto_complete = findViewById(R.id.auto_complete)

        list = ArrayList()

    }
}