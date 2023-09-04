package com.github.snick55.pswtestapp.presentation.details

import android.widget.TextView

data class CarDetailUi(
    private val brand: String,
    private val description: String,
    private val manufacturer: String,
    private val price: Int
){
    fun show(
        brandTextView: TextView,
        manufactureTextView: TextView,
        priceTextView: TextView,
        descriptionTextView: TextView
    ) {
        brandTextView.text = brand
        manufactureTextView.text = manufacturer
        priceTextView.text = price.toString()
        descriptionTextView.text = description
    }
}
