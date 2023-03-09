package com.example.dbcalc

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    
     private var tvSelectedDate : TextView? = null
     private  var tvAgeinMinutes : TextView? = null
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvSelectedDate = findViewById(R.id.tvSelectedDate)
        tvAgeinMinutes = findViewById(R.id.tvAgeInMinutes)
        val datePickerbtn : Button =  findViewById(R.id.BtnDatePicker)

        datePickerbtn.setOnClickListener {
            clickDaterPicker()
        }
    }

    private fun clickDaterPicker() {

        val myCalendar = Calendar.getInstance()
        val year = myCalendar.get(Calendar.YEAR)
        val month = myCalendar.get(Calendar.MONTH)
        val day = myCalendar.get(Calendar.DAY_OF_MONTH)
       val dpd =  DatePickerDialog(this,
           DatePickerDialog.OnDateSetListener { _, selectedYear, selectedMonth, selectedDayOfMonth ->

               Toast.makeText(this,"Year was $selectedYear, month was ${selectedMonth+1}," +
                       "day was $selectedDayOfMonth",
                   Toast.LENGTH_LONG).show()


               val selectedDate = "$selectedDayOfMonth/${selectedMonth+1}/$selectedYear"
               tvSelectedDate?.text = selectedDate


               val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
               val theDate = dateFormat.parse(selectedDate)

               theDate?.let {
                   val selectedDateInMinutes = theDate.time / 6000
                   val currentDate =dateFormat.parse(dateFormat.format(System.currentTimeMillis()))
                   currentDate?.let {
                       val currentDateInMinutes = currentDate.time / 6000
                       val differenceInMinutes = currentDateInMinutes - selectedDateInMinutes
                       tvAgeinMinutes?.text  = differenceInMinutes.toString()
                   }

                   }


           },
           year,
           month,
           day
       )
        dpd.datePicker.maxDate =System.currentTimeMillis() - 86400000
            dpd.show()

//
    }
}