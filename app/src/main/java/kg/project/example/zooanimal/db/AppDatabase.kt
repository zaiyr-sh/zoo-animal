package kg.project.example.zooanimal.db

import androidx.room.Database
import androidx.room.RoomDatabase
import kg.project.example.zooanimal.data.Animal

@Database(entities = [Animal::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun animalDao(): AnimalDao
}