package ft.cn.floodmanager.model
data class Report(
    val description: String,
    val city: String,
    val floodPrediction: String,
    val sensorData: List<SensorData> // Assuming SensorData is the data class you created earlier
)
