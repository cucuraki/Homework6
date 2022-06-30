package com.example.homework6

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.homework6.databinding.ActivityMainBinding
import java.lang.Exception

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var removedUsersCount = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.addUser.setOnClickListener {
            onAddUserClick()
        }
        binding.removeUser.setOnClickListener {
            onRemoveUserClick()
        }
        binding.updateUser.setOnClickListener {
            onUpdateUserClick()        }
        binding.allUsers.setOnClickListener{
            val intent = Intent(this, UsersActivity::class.java)
            startActivity(intent)
        }
    }
    @SuppressLint("SetTextI18n")
    private fun onAddUserClick(){
        val user: User
        try{
            user = initialization()
        }
        catch(e: Exception){
            myToast(e.toString())
            return
        }
        if(!allIsOk(user, true))
            return
        activeUsers.add(user)
        binding.activeUsersCount.text = "Active users count: ${activeUsers.size}"
        binding.state.setBackgroundColor(Color.GREEN)
        binding.successState.setTextColor(Color.GREEN)
        binding.successState.text = "Success"
        myToast("User added successfully")
    }


    @SuppressLint("SetTextI18n")
    private fun onRemoveUserClick(){
        val user: User
        try{
            user = initialization()
        }
        catch(e: Exception){
            myToast(e.toString())
            return
        }
        if(!allIsOk(user, false))
            return
        activeUsers.remove(user)
        binding.activeUsersCount.text = "Active users count: ${activeUsers.size}"
        removedUsersCount++
        binding.removedUsersCount.text = "Removed users count: $removedUsersCount"
        binding.state.setBackgroundColor(Color.GREEN)
        binding.successState.setTextColor(Color.GREEN)
        binding.successState.text = "Success"
        myToast("User removed successfully")


    }

    @SuppressLint("SetTextI18n")
    private fun onUpdateUserClick(){
        val user: User
        try{
            user = initialization()
        }
        catch(e: Exception){
            myToast(e.toString())
            return
        }
        if(!allIsOk(user, false))
            return

        binding.state.setBackgroundColor(Color.GREEN)
        binding.successState.setTextColor(Color.GREEN)
        binding.successState.text = "Success"
        val intent = Intent(this, UpdateUserActivity::class.java)
        intent.putExtra("user", user)
        startActivity(intent)
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
    private fun allIsOk(user: User, userPresence: Boolean): Boolean{
        var bool = true
        when{
            somethingIsEmpty(applicationContext)  ->{
                bool =  false
            }
            !emailIsCorrectPattern(applicationContext) -> {
                bool = false
            }
            user !in activeUsers && !userPresence-> {
                myToast("User does not exist")
                bool = false
            }
            user in activeUsers && userPresence -> {
                myToast("User already exists")
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