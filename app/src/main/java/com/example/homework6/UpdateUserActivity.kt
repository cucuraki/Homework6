package com.example.homework6

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.homework6.databinding.ActivityUpdateUserBinding
import java.lang.Exception

class UpdateUserActivity: AppCompatActivity() {
    private lateinit var binding: ActivityUpdateUserBinding
    private lateinit var currentUser: User
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpdateUserBinding.inflate(layoutInflater)
        setContentView(binding.root)
        currentUser = intent.getSerializableExtra("user") as User
        val userInfoFields = arrayOf(binding.firstName
                                        ,binding.lastName
                                        ,binding.age
                                        ,binding.email)
        userInfoFields[0].setText(currentUser.name)
        userInfoFields[1].setText(currentUser.lastName)
        userInfoFields[2].setText(currentUser.age.toString())
        userInfoFields[3].setText(currentUser.email)
        binding.goBack.setOnClickListener {
            onBackPressed()
        }
        binding.updateUser.setOnClickListener{
            updateUser()
        }
    }

    @SuppressLint("SetTextI18n")
    private fun updateUser(){
        val user: User
        try{
            user = initialization()
        }
        catch(e: Exception){
            myToast(e.toString())
            return
        }
        if(user == currentUser){
            myToast("User updated successfully")
            binding.state.setBackgroundColor(Color.GREEN)
            binding.successState.setTextColor(Color.GREEN)
            binding.successState.text = "Success"
            return
        }
        if(!allIsOk(user))
            return

        activeUsers.remove(currentUser)
        activeUsers.add(user)
        currentUser = user
        binding.state.setBackgroundColor(Color.GREEN)
        binding.successState.setTextColor(Color.GREEN)
        binding.successState.text = "Success"
        myToast("User updated successfully")
    }
    @SuppressLint("SetTextI18n")
    private fun initialization(): User{
        info = arrayOf(binding.firstName.text.toString()
            ,binding.lastName.text.toString()
            ,binding.age.text.toString()
            ,binding.email.text.toString())
        val user: User
        try {
            val age = if(info[2].isEmpty())0 else info[2].toInt()
            user = User(info[0], info[1], age, info[3])
        }
        catch (e: Exception){
            myToast(e.toString())
            binding.state.setBackgroundColor(Color.RED)
            binding.successState.setTextColor(Color.RED)
            binding.successState.text = "Error"
            throw e
        }
        return user
    }
    @SuppressLint("SetTextI18n")
    private fun allIsOk(user: User): Boolean{
        var bool = true
        when{
            somethingIsEmpty(applicationContext)  ->{
                bool =  false
            }
            !emailIsCorrectPattern(applicationContext) -> {
                bool = false
            }
            user in activeUsers -> {
                myToast("This user already exists")
                bool = false
            }
        }
        if(!bool){
            binding.state.setBackgroundColor(Color.RED)
            binding.successState.setTextColor(Color.RED)
            binding.successState.text = "Error"
        }
        return bool
    }
    private fun myToast(text: String){
        Toast.makeText(applicationContext, text, Toast.LENGTH_LONG).show()
    }
}