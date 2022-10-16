package kg.project.example.zooanimal.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class Animal(
    @PrimaryKey var id: Int? = null,
    @SerializedName("name") var name: String? = null,
    @SerializedName("latin_name") var latinName: String? = null,
    @SerializedName("animal_type") var animalType: String? = null,
    @SerializedName("active_time") var activeTime: String? = null,
    @SerializedName("length_min") var lengthMin: String? = null,
    @SerializedName("length_max") var lengthMax: String? = null,
    @SerializedName("weight_min") var weightMin: String? = null,
    @SerializedName("weight_max") var weightMax: String? = null,
    @SerializedName("lifespan") var lifespan: String? = null,
    @SerializedName("habitat") var habitat: String? = null,
    @SerializedName("diet") var diet: String? = null,
    @SerializedName("geo_range") var geoRange: String? = null,
    @SerializedName("image_link") var imageLink: String? = null
)