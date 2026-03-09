package com.example.assignment1

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.RadioButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.assignment1.databinding.ActivityBookAppointmentBinding
import java.text.SimpleDateFormat
import java.util.*

class BookAppointmentActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBookAppointmentBinding
    private val calendar = Calendar.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBookAppointmentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.tvDate.setOnClickListener {
            showDatePicker()
        }

        binding.tvTime.setOnClickListener {
            showTimePicker()
        }

        binding.btnConfirmBooking.setOnClickListener {
            validateAndConfirm()
        }
    }

    private fun showDatePicker() {
        val dateSetListener = DatePickerDialog.OnDateSetListener { _, year, month, dayOfMonth ->
            calendar.set(Calendar.YEAR, year)
            calendar.set(Calendar.MONTH, month)
            calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
            val format = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
            binding.tvDate.text = format.format(calendar.time)
        }

        DatePickerDialog(
            this, dateSetListener,
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        ).show()
    }

    private fun showTimePicker() {
        val timeSetListener = TimePickerDialog.OnTimeSetListener { _, hourOfDay, minute ->
            calendar.set(Calendar.HOUR_OF_DAY, hourOfDay)
            calendar.set(Calendar.MINUTE, minute)
            val format = SimpleDateFormat("hh:mm a", Locale.getDefault())
            binding.tvTime.text = format.format(calendar.time)
        }

        TimePickerDialog(
            this, timeSetListener,
            calendar.get(Calendar.HOUR_OF_DAY),
            calendar.get(Calendar.MINUTE),
            false
        ).show()
    }

    private fun validateAndConfirm() {
        val fullName = binding.etFullName.text.toString().trim()
        val phone = binding.etPhoneNumber.text.toString().trim()
        val email = binding.etEmail.text.toString().trim()
        val appointmentType = binding.spinnerAppointmentType.selectedItem.toString()
        val date = binding.tvDate.text.toString()
        val time = binding.tvTime.text.toString()
        val genderId = binding.radioGroupGender.checkedRadioButtonId
        val termsAccepted = binding.cbTerms.isChecked

        if (fullName.isEmpty()) {
            binding.etFullName.error = getString(R.string.error_empty)
            return
        }
        if (phone.isEmpty()) {
            binding.etPhoneNumber.error = getString(R.string.error_empty)
            return
        }
        if (email.isEmpty()) {
            binding.etEmail.error = getString(R.string.error_empty)
            return
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            binding.etEmail.error = getString(R.string.error_email)
            return
        }
        if (date == getString(R.string.hint_appointment_date)) {
            Toast.makeText(this, getString(R.string.error_date), Toast.LENGTH_SHORT).show()
            return
        }
        if (time == getString(R.string.hint_appointment_time)) {
            Toast.makeText(this, getString(R.string.error_time), Toast.LENGTH_SHORT).show()
            return
        }
        if (genderId == -1) {
            Toast.makeText(this, getString(R.string.error_gender), Toast.LENGTH_SHORT).show()
            return
        }
        if (!termsAccepted) {
            Toast.makeText(this, getString(R.string.error_terms), Toast.LENGTH_SHORT).show()
            return
        }

        val gender = findViewById<RadioButton>(genderId).text.toString()

        val intent = Intent(this, ConfirmationActivity::class.java).apply {
            putExtra("EXTRA_NAME", fullName)
            putExtra("EXTRA_PHONE", phone)
            putExtra("EXTRA_EMAIL", email)
            putExtra("EXTRA_TYPE", appointmentType)
            putExtra("EXTRA_DATE", date)
            putExtra("EXTRA_TIME", time)
            putExtra("EXTRA_GENDER", gender)
        }
        startActivity(intent)
    }
}