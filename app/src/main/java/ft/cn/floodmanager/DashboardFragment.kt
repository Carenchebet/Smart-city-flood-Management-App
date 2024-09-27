package ft.cn.floodmanager

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ft.cn.floodmanager.R

class DashboardFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView

        override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View? {
            // Inflate the layout for this fragment
            val view = inflater.inflate(R.layout.fragment_dashboard, container, false)

            // Setup RecyclerView
            recyclerView = view.findViewById(R.id.recyclerView)
            recyclerView.layoutManager = LinearLayoutManager(context)


            return view
        }

        // Function to set up dummy data for RecyclerView

    }

