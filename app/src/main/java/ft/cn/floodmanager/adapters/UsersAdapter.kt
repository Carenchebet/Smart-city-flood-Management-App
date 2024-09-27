package ft.cn.floodmanager.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ft.cn.floodmanager.R
import ft.cn.floodmanager.model.UserClass

class UsersAdapter(private val userList: List<UserClass>) :
    RecyclerView.Adapter<UsersAdapter.UserViewHolder>() {

    class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textUserRole: TextView = itemView.findViewById(R.id.textUserRole)
        val textUsername: TextView = itemView.findViewById(R.id.textUsername)
        val textFullname: TextView = itemView.findViewById(R.id.textFullname)
        val textCity: TextView = itemView.findViewById(R.id.textCity)
        val textPhoneNumber: TextView = itemView.findViewById(R.id.textPhoneNumber)
        val textEmail: TextView = itemView.findViewById(R.id.textEmail)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_user, parent, false)
        return UserViewHolder(view)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user = userList[position]
        holder.textUserRole.text = "User Role: ${user.userRole}"
        holder.textUsername.text = "Username: ${user.username}"
        holder.textFullname.text = "Fullname: ${user.fullname}"
        holder.textCity.text = "City: ${user.city}"
        holder.textPhoneNumber.text = "Phone Number: ${user.phoneNumber}"
        holder.textEmail.text = "Email: ${user.email}"
    }

    override fun getItemCount(): Int = userList.size
}
