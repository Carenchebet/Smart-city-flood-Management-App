package ft.cn.floodmanager

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth
import android.util.Patterns
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import ft.cn.floodmanager.cityofficial.CityOfficialMainActivity
import ft.cn.floodmanager.dataanalyst.DataAnalystMainActivity

class MainActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // UI Elements
        val username: TextInputEditText = findViewById(R.id.username)
        val password: TextInputEditText = findViewById(R.id.password)
        val loginButton: Button = findViewById(R.id.loginButton)

        // Firebase Auth instance
        auth = FirebaseAuth.getInstance()

        loginButton.setOnClickListener {
            val email = username.text.toString().trim()
            val pass = password.text.toString().trim()

            if (validateInputs(email, pass)) {
                loginUser(email, pass)
            }
        }
    }

    // Validate email and password inputs
    private fun validateInputs(email: String, pass: String): Boolean {
        return when {
            email.isEmpty() || pass.isEmpty() -> {
                Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_LONG).show()
                false
            }
            !Patterns.EMAIL_ADDRESS.matcher(email).matches() -> {
                Toast.makeText(this, "Enter a valid email address", Toast.LENGTH_LONG).show()
                false
            }
            pass.length < 6 -> {
                Toast.makeText(this, "Password must be at least 6 characters", Toast.LENGTH_LONG).show()
                false
            }
            else -> true
        }
    }

    // Firebase login function
    private fun loginUser(email: String, pass: String) {
        auth.signInWithEmailAndPassword(email, pass).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                Toast.makeText(this, "Login Successful", Toast.LENGTH_LONG).show()
                val user = auth.currentUser
                updateUI(user)
            } else {
                Log.e("LoginActivity", "Login failed", task.exception)
                Toast.makeText(this, "Login failed. Please check your credentials.", Toast.LENGTH_LONG).show()
            }
        }
    }

    // Redirect user after successful login
    private fun updateUI(user: FirebaseUser?) {
        user?.let {
            // Example of moving to the next activity after login
            val id=user.uid.toString()
            FirebaseDatabase.getInstance().reference.child("users").child(id).child("userRole").addValueEventListener(object:
                ValueEventListener{
                override fun onDataChange(snapshot: DataSnapshot) {
                    val role=snapshot.value
                    if(role=="admin")
                    {
                        startActivity(Intent(this@MainActivity, DashboardActivity::class.java))
                        finish() // Close the current activity
                    }
                    else if(role=="cityofficial")
                    {
                        startActivity(Intent(this@MainActivity, CityOfficialMainActivity::class.java))
                        finish() // Close the current activity
                    }
                    else if(role=="dataanalyst")
                    {
                        startActivity(Intent(this@MainActivity, DataAnalystMainActivity::class.java))
                        finish() // Close the current activity
                    }
                }

                override fun onCancelled(error: DatabaseError) {

                }

            })
        }
    }
}
