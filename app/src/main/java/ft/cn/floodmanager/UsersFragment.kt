package ft.cn.floodmanager

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ft.cn.floodmanager.adapters.UsersAdapter
import ft.cn.floodmanager.model.UserClass

class UsersFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: UsersAdapter
    private val userList: List<UserClass> = listOf(
        // Example user data; replace this with actual user data
        UserClass("Admin", "johndoe", "John Doe", "Nairobi", "1234567890", "johndoe@example.com"),
        UserClass("User", "janedoe", "Jane Doe", "Mombasa", "0987654321", "janedoe@example.com"),
        UserClass("User", "alice", "Alice Smith", "Nakuru", "1122334455", "alice@example.com")
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_users, container, false)
        recyclerView = view.findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(context)
        adapter = UsersAdapter(userList)
        recyclerView.adapter = adapter
        return view
    }
}
