package com.github.snick55.pswtestapp.core

import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController

fun Fragment.navigateTo(directions: NavDirections){
    this.findNavController().navigate(directions)
}

fun Fragment.goBack(){
    this.findNavController().popBackStack()
}