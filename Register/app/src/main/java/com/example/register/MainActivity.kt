package com.example.yourappname

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import android.R


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val firstName = findViewById<EditText>(R.id.first_name)
        val lastName = findViewById<EditText>(R.id.second_name)
        val email = findViewById<EditText>(R.id.email)
        val phone = findViewById<EditText>(R.id.number)
        val password = findViewById<EditText>(R.id.password)
        val confirmPassword = findViewById<EditText>(R.id.con_password)
        val radioGroup = findViewById<RadioGroup>(R.id.radioGroup)
        val submitButton = findViewById<Button>(R.id.submit)

        submitButton.setOnClickListener {
            val fname = firstName.text.toString()
            val lname = lastName.text.toString()
            val emailText = email.text.toString()
            val phoneText = phone.text.toString()
            val pass = password.text.toString()
            val conPass = confirmPassword.text.toString()

            val selectedGenderId = radioGroup.checkedRadioButtonId
            val gender = if (selectedGenderId != -1) {
                findViewById<RadioButton>(selectedGenderId).text.toString()
            } else {
                "Not selected"
            }

            // Basic validation
            if (fname.isEmpty() || lname.isEmpty() || emailText.isEmpty() ||
                phoneText.isEmpty() || pass.isEmpty() || conPass.isEmpty()) {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (pass != conPass) {
                Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // You can do actual registration logic here
            val info = """
                Name: $fname $lname
                Email: $emailText
                Phone: $phoneText
                Gender: $gender
            """.trimIndent()

            Toast.makeText(this, "Submitted:\n$info", Toast.LENGTH_LONG).show()
        }
    }
}
