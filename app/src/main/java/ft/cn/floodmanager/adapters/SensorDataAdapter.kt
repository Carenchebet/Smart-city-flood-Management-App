package ft.cn.floodmanager.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ft.cn.floodmanager.R
import ft.cn.floodmanager.model.SensorData

class SensorDataAdapter(private val sensorDataList: List<SensorData>) :
    RecyclerView.Adapter<SensorDataAdapter.SensorDataViewHolder>() {

    class SensorDataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textTemperature: TextView = itemView.findViewById(R.id.textTemperature)
        val textHumidity: TextView = itemView.findViewById(R.id.textHumidity)
        val textWaterLevel: TextView = itemView.findViewById(R.id.textWaterLevel)
        val textDate: TextView = itemView.findViewById(R.id.textDate)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SensorDataViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_sensor_data, parent, false)
        return SensorDataViewHolder(view)
    }

    override fun onBindViewHolder(holder: SensorDataViewHolder, position: Int) {
        val sensorData = sensorDataList[position]
        holder.textTemperature.text = "Temperature: ${sensorData.temperature}"
        holder.textHumidity.text = "Humidity: ${sensorData.humidity}"
        holder.textWaterLevel.text = "Water Level: ${sensorData.waterLevel}"
        holder.textDate.text = "Date: ${sensorData.date}"
    }

    override fun getItemCount(): Int = sensorDataList.size
}
