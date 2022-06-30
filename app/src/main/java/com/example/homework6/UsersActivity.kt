package com.example.homework6

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.homework6.databinding.ActivityMainBinding
import com.example.homework6.databinding.ActivityUsersBinding

class UsersActivity: AppCompatActivity() {
    private lateinit var binding: ActivityUsersBinding
    private var count = 0
    private lateinit var usersArray: ArrayList<User>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUsersBinding.inflate(layoutInflater)
        setContentView(binding.root)
        count = 0
        userArrayInitialization()
        firstUser()
        binding.goBack.setOnClickListener {
            onBackPressed()
        }
        binding.next.setOnClickListener {
            onNextClicked()
        }
    }
    private fun onNextClicked(){
        if(count == usersArray.size - 1)
            count = 0
        else count++
        setContext(usersArray[count])
    }
    private fun userArrayInitialization(){
        usersArray = arrayListOf()
        activeUsers.forEach{
            usersArray.add(it)
        }
    }
    private fun firstUser(){
        if(usersArray.size == 0){
            Toast.makeText(applicationContext, "There are no users", Toast.LENGTH_LONG).show()
            return
        }
        setContext(usersArray[0])
    }

    private fun setContext(user: User){
        binding.firstName.text = user.name
        binding.lastName.text = user.lastName
        binding.age.text = user.age.toString()
        binding.email.text = user.email
    }
}