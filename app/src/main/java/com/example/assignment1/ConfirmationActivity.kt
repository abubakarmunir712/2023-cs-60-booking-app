package com.example.assignment1

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.assignment1.databinding.ActivityConfirmationBinding

class ConfirmationActivity : AppCompatActivity() {

    private lateinit var binding: ActivityConfirmationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityConfirmationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val name = intent.getStringExtra("EXTRA_NAME")
        val phone = intent.getStringExtra("EXTRA_PHONE")
        val email = intent.getStringExtra("EXTRA_EMAIL")
        val type = intent.getStringExtra("EXTRA_TYPE")
        val date = intent.getStringExtra("EXTRA_DATE")
        val time = intent.getStringExtra("EXTRA_TIME")
        val gender = intent.getStringExtra("EXTRA_GENDER")

        binding.tvConfirmName.text = name
        binding.tvConfirmPhone.text = phone
        binding.tvConfirmEmail.text = email
        binding.tvConfirmType.text = type
        binding.tvConfirmDate.text = date
        binding.tvConfirmTime.text = time
        binding.tvConfirmGender.text = gender
    }
}