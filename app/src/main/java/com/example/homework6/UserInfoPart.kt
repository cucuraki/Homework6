package com.example.homework6
import android.content.Context
import android.util.Log.d
import android.widget.Toast

enum class UserInfoPart{
    FIRSTNAME{
        override fun onEmpty(context: Context) {
            Toast.makeText(context, "First name shouldn't be empty", Toast.LENGTH_LONG).show()
        }
    },
    LASTNAME{
        override fun onEmpty(context: Context) {
            Toast.makeText(context, "Last name shouldn't be empty", Toast.LENGTH_LONG).show()
        }
    },
    AGE{
        override fun onEmpty(context: Context) {
            Toast.makeText(context, "Age shouldn't be empty", Toast.LENGTH_LONG).show()
        }
    },
    EMAIL{
        override fun onEmpty(context: Context) {
            Toast.makeText(context, "Email shouldn't be empty", Toast.LENGTH_LONG).show()
        }
    };
    abstract fun onEmpty(context: Context)
}