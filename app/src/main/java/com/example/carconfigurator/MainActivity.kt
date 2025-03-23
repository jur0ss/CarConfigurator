package com.example.carconfigurator

import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val myImageView: ImageView = findViewById(R.id.imageview)

        val images = listOf(
            R.drawable.sedan,
            R.drawable.suv,
            R.drawable.hatchback
        )

        val myRadioGroup: RadioGroup = findViewById<RadioGroup>(R.id.car_radiogroup)

        myRadioGroup.setOnCheckedChangeListener { _, checkedId ->
            val myRadioButton: RadioButton = findViewById(checkedId)
            if (checkedId==R.id.sedan_radio)
            {
                myImageView.setImageResource(images[0])
            }
            else if (checkedId==R.id.suv_radio)
            {
                myImageView.setImageResource(images[1])
            }
            else if (checkedId==R.id.hatchback_radio)
            {
                myImageView.setImageResource(images[2])
            }
        }

        val orderButton = findViewById<Button>(R.id.order_button)

        val textView = findViewById<TextView>(R.id.textview)

        orderButton.setOnClickListener {
            val selectedCar = when(myRadioGroup.checkedRadioButtonId)
            {
                R.id.sedan_radio -> "Sedan"
                R.id.suv_radio -> "SUV"
                R.id.hatchback_radio -> "Hatchback"
                else -> "Nie wybrano auta"
            }

            val airconChecked = findViewById<CheckBox>(R.id.aircon).isChecked
            val leatherChecked = findViewById<CheckBox>(R.id.leather).isChecked
            val sunroofChecked = findViewById<CheckBox>(R.id.sunroof).isChecked
            val bluetoothChecked = findViewById<CheckBox>(R.id.bluetooth).isChecked
            val windowsChecked = findViewById<CheckBox>(R.id.windows).isChecked

            val selectedOptions = mutableListOf<String>()

            if (airconChecked) selectedOptions.add("Klimatyzacja")
            if (leatherChecked) selectedOptions.add("Skórzane siedzenia")
            if (sunroofChecked) selectedOptions.add("Szyberdach")
            if (bluetoothChecked) selectedOptions.add("Głośniki na bluetooth")
            if (windowsChecked) selectedOptions.add("Przyciemniane szyby")

            var i=0
            var order = "Zamówienie: \n$selectedCar \n"
            while(i<=selectedOptions.size-1){
                order = order +  selectedOptions[i] + "\n"
                i++
            }
            textView.text = order

        }










    }
}