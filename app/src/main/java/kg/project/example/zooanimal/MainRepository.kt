package kg.project.example.zooanimal

import kg.project.example.zooanimal.data.Animal
import kg.project.example.zooanimal.db.AnimalDao

class MainRepository(
    private val apiAnimal: ApiAnimal,
    private val animalDao: AnimalDao
) {

    suspend fun getAnimal() = apiAnimal.getAnimal()

    suspend fun getAnimals(number: Int) = apiAnimal.getAnimals(number)

    suspend fun getAnimalsFromDb() = animalDao.getAnimalsFromDb()

    suspend fun addAnimalToDb(animal: Animal) = animalDao.insert(animal)

    suspend fun deleteAnimalFromDb(id: Int?) = animalDao.delete(id)

}