package com.example.homework6

import android.content.Context
import android.widget.Toast

fun somethingIsEmpty(context: Context): Boolean{
    for(i in info.indices){
        if(info[i].isEmpty()){
            UserInfoPart.values()[i].onEmpty(context)
            return true
        }
    }
    return false
}
fun emailIsCorrectPattern(context: Context): Boolean{
    if(!android.util.Patterns.EMAIL_ADDRESS
            .matcher(info[info.lastIndex])
            .matches()){
        Toast.makeText(context,"Please enter valid email", Toast.LENGTH_LONG).show()
        return false
    }
    return true
}



