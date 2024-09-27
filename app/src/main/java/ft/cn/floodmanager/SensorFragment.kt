package ft.cn.floodmanager

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ft.cn.floodmanager.adapters.SensorDataAdapter
import ft.cn.floodmanager.model.SensorData

class SensorFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: SensorDataAdapter
    private val sensorDataList: List<SensorData> = listOf(
        // Example data; replace with your actual sensor data
        SensorData("25°C", "60%", "1.5m", "2024-09-25"),
        SensorData("24°C", "65%", "1.2m", "2024-09-26"),
        SensorData("23°C", "70%", "1.8m", "2024-09-27")
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_sensor, container, false)
        recyclerView = view.findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(context)
        adapter = SensorDataAdapter(sensorDataList)
        recyclerView.adapter = adapter
        return view
    }
}
