package ft.cn.floodmanager

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import ft.cn.floodmanager.model.Report
import ft.cn.floodmanager.model.SensorData

class ReportsFragment : Fragment() {

    private lateinit var report: Report

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_reports, container, false)

        // Example report; replace this with actual report data
        report = Report(
            description = "Severe flooding expected due to heavy rainfall.",
            city = "Nairobi",
            floodPrediction = "High risk of flooding in the next 24 hours.",
            sensorData = listOf(
                SensorData("25°C", "60%", "1.5m", "2024-09-25"),
                SensorData("24°C", "65%", "1.2m", "2024-09-26")
            )
        )

        view.findViewById<TextView>(R.id.textDescription).text = "Description: ${report.description}"
        view.findViewById<TextView>(R.id.textCity).text = "City: ${report.city}"
        view.findViewById<TextView>(R.id.textPrediction).text = "Flood Prediction: ${report.floodPrediction}"

        // Display sensor data
        val sensorDataString = report.sensorData.joinToString("\n") {
            "Temperature: ${it.temperature}, Humidity: ${it.humidity}, Water Level: ${it.waterLevel}, Date: ${it.date}"
        }
        view.findViewById<TextView>(R.id.textSensorData).text = "Sensor Data:\n$sensorDataString"

        return view
    }
}
