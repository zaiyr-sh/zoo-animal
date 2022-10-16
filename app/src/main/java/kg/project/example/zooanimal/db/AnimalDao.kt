package kg.project.example.zooanimal.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kg.project.example.zooanimal.data.Animal

@Dao
interface AnimalDao {

    @Query("SELECT * FROM Animal")
    suspend fun getAnimalsFromDb(): List<Animal>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(animal: Animal)

    @Query("DELETE FROM animal WHERE id = :id")
    suspend fun delete(id: Int?)

}