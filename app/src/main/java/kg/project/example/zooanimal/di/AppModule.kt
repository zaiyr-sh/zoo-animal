package kg.project.example.zooanimal.di

import android.app.Application
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kg.project.example.zooanimal.ApiAnimal
import kg.project.example.zooanimal.MainRepository
import kg.project.example.zooanimal.db.AnimalDao
import kg.project.example.zooanimal.db.AppDatabase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    private const val DATABASE_NAME = "zoo-animal-database"

    @Provides
    @Singleton
    fun provideDatabase(app: Application): AppDatabase =
        Room.databaseBuilder(
            app,
            AppDatabase::class.java,
            DATABASE_NAME
        ).build()

    @Provides
    fun provideMainRepository(apiAnimal: ApiAnimal, animalDao: AnimalDao) = MainRepository(apiAnimal, animalDao)

    @Provides
    fun provideAnimalDao(db: AppDatabase) = db.animalDao()

}