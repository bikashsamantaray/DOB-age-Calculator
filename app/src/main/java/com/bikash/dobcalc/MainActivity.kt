package com.bikash.dobcalc

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.DatePicker
import android.widget.TextView
import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    val myCalendar = Calendar.getInstance()
    val year = myCalendar.get(Calendar.YEAR)
    val month = myCalendar.get(Calendar.MONTH)
    val day = myCalendar.get(Calendar.DAY_OF_MONTH)
    var selectedDate:String?=""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnCalculateAgeMinutes: Button = findViewById(R.id.btnCalculateAgeMinutes)

        val selectDate=findViewById<Button>(R.id.tvSelectedDate)
        selectDate.setOnClickListener{
            clickDatePicker()
        }
        btnCalculateAgeMinutes.setOnClickListener{
            calculateMinutes()
        }

    }

    private fun clickDatePicker() {


        val tvSelectedDate = findViewById<TextView>(R.id.tvSelectedDate)


        val dpd = DatePickerDialog(this,DatePickerDialog.OnDateSetListener{ _ , selectedYear , selectedMonth, selectedDayOfMonth ->
            Toast.makeText(this,"the day is $selectedDayOfMonth and the month is ${selectedMonth+1} and the year is $selectedYear",
                Toast.LENGTH_LONG).show()
            selectedDate = "$selectedDayOfMonth/${selectedMonth+1}/$selectedYear"
            tvSelectedDate.text = selectedDate

        },
            year, month, day)

        dpd.datePicker.maxDate = System.currentTimeMillis() - 86400000
        dpd.show()





    }

    @SuppressLint("SetTextI18n")
    private fun calculateMinutes(){
        val tvLived = findViewById<TextView>(R.id.tv_lived)
        val tvAgeInMinutes = findViewById<TextView>(R.id.tvAgeInMinutes)
        val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        val theDate = sdf.parse(selectedDate.toString())
        val selectedDateInMinutes = theDate.time / 60000
        val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))

        val currentDateInMinutes = currentDate.time / 60000
        val differenceInMinutes = currentDateInMinutes - selectedDateInMinutes
        tvLived.visibility = View.VISIBLE
        tvAgeInMinutes.text = differenceInMinutes.toString() + " minutes"




    }
}