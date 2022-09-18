package com.bikash.dobcalc

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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




    private var tvAgeInMiutes: TextView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val btnCalculateAgeMinutes: Button = findViewById(R.id.btnCalculateAgeMinutes)

        tvAgeInMiutes = findViewById(R.id.tvAgeInMinutes)

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

    fun calculateMinutes(){
        val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        val theDate = sdf.parse(selectedDate)
        theDate?.let {
            val selectedDateInMinutes = theDate.time / 60000
            val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))
            currentDate?.let {
                val currentDateInMinutes = currentDate.time / 60000
                val differenceInMinutes = currentDateInMinutes - selectedDateInMinutes
                tvAgeInMiutes?.text = differenceInMinutes.toString()
            }

        }

    }
}