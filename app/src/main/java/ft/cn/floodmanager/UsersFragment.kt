package ft.cn.floodmanager

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.database.*
import ft.cn.floodmanager.adapters.UsersAdapter
import ft.cn.floodmanager.model.UserClass
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.firebase.auth.FirebaseAuth

class UsersFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: UsersAdapter
    private val userList: MutableList<UserClass> = mutableListOf()
    private lateinit var databaseReference: DatabaseReference
    private lateinit var fabAddUser: FloatingActionButton
    private lateinit var usersListener: ValueEventListener

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_users, container, false)

        recyclerView = view.findViewById(R.id.recyclerView)
        fabAddUser = view.findViewById(R.id.add_users)

        recyclerView.layoutManager = LinearLayoutManager(context)
        adapter = UsersAdapter(userList)
        recyclerView.adapter = adapter

        // Initialize Firebase Realtime Database reference
        databaseReference = FirebaseDatabase.getInstance().getReference("users")

        // Load existing users from Firebase Realtime Database
        loadUsersFromDatabase()

        // Handle FAB click to add new user
        fabAddUser.setOnClickListener {
            showAddUserDialog()
        }

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        // Remove the listener to prevent memory leaks
        databaseReference.removeEventListener(usersListener)
    }

    private fun loadUsersFromDatabase() {
        usersListener = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                userList.clear()
                for (userSnapshot in snapshot.children) {
                    val user =UserClass(userRole=userSnapshot.child("userRole").value.toString(),
                        username=userSnapshot.child("username").value.toString(),
                        fullname=userSnapshot.child("fullname").value.toString(),
                       city=userSnapshot.child("city").value.toString(),
                        phoneNumber=userSnapshot.child("phoneNumber").value.toString(),
                      email=userSnapshot.child("email").value.toString()
                    )
                    user?.let {
                        userList.add(it)
                    }
                }
                adapter.notifyDataSetChanged()
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(context, "Failed to load users: ${error.message}", Toast.LENGTH_SHORT).show()
            }
        }

        databaseReference.addValueEventListener(usersListener)
    }

    private fun showAddUserDialog() {
        val dialogView = LayoutInflater.from(context).inflate(R.layout.dialog_add_user, null)

        val editTextRole = dialogView.findViewById<EditText>(R.id.editTextRole)
        val editTextUsername = dialogView.findViewById<EditText>(R.id.editTextUsername)
        val editTextFullName = dialogView.findViewById<EditText>(R.id.editTextFullName)
        val editTextLocation = dialogView.findViewById<EditText>(R.id.editTextLocation)
        val editTextPhone = dialogView.findViewById<EditText>(R.id.editTextPhone)
        val editTextEmail = dialogView.findViewById<EditText>(R.id.editTextEmail)

        MaterialAlertDialogBuilder(requireContext())
            .setTitle("Add New User")
            .setView(dialogView)
            .setPositiveButton("Add") { dialog, _ ->
                val role = editTextRole.text.toString().trim()
                val username = editTextUsername.text.toString().trim()
                val fullName = editTextFullName.text.toString().trim()
                val location = editTextLocation.text.toString().trim()
                val phone = editTextPhone.text.toString().trim()
                val email = editTextEmail.text.toString().trim()

                if (validateInput(role, username, fullName, location, phone, email)) {
                    val newUser = UserClass(role, username, fullName,location,phone,email)
                    addUserToDatabase(newUser)
                } else {
                    Toast.makeText(context, "Please fill in all fields correctly.", Toast.LENGTH_SHORT).show()
                }

                dialog.dismiss()
            }
            .setNegativeButton("Cancel") { dialog, _ ->
                dialog.cancel()
            }
            .show()
    }

    private fun validateInput(
        role: String,
        username: String,
        fullName: String,
        location: String,
        phone: String,
        email: String
    ): Boolean {
        // Basic validation; you can enhance this as needed
        return role.isNotEmpty() &&
                username.isNotEmpty() &&
                fullName.isNotEmpty() &&
                location.isNotEmpty() &&
                phone.isNotEmpty() &&
                android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    private fun addUserToDatabase(user: UserClass) {
        // Generate a unique key for the new user
        val key = databaseReference.push().key

        if (key == null) {
            Toast.makeText(context, "Error generating unique ID for user.", Toast.LENGTH_SHORT).show()
            return
        }

        // Add the user to the database

                FirebaseAuth.getInstance().createUserWithEmailAndPassword(user.email,"user@2024").addOnCompleteListener {
                    if(it.isSuccessful)
                    {
                        databaseReference.child(it.result.user!!.uid).setValue(user)
                            .addOnSuccessListener {
                        Toast.makeText(context, "User added successfully.", Toast.LENGTH_SHORT).show()
                            }
                            .addOnFailureListener { e ->
                                Toast.makeText(context, "Error adding user: ${e.message}", Toast.LENGTH_SHORT).show()
                            }
                    }
                }

                // The userList will automatically update via the ValueEventListener

    }
}
