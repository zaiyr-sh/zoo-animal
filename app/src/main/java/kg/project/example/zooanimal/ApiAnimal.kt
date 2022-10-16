package kg.project.example.zooanimal

import kg.project.example.zooanimal.data.Animal
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiAnimal {

    @GET("animals/rand")
    suspend fun getAnimal(): Animal

    @GET("animals/rand/{number}")
    suspend fun getAnimals(@Path("number") number: Int): List<Animal>

}