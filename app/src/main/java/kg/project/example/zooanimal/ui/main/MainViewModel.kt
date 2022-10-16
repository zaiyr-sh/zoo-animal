package kg.project.example.zooanimal.ui.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kg.project.example.zooanimal.MainRepository
import kg.project.example.zooanimal.data.Animal
import kg.project.example.zooanimal.utils.CoreEvent
import kg.project.example.zooanimal.utils.Event
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: MainRepository) : ViewModel() {

    val animalsLiveData: LiveData<List<Animal>>
        get() = _animalsLiveData
    private val _animalsLiveData = MutableLiveData<List<Animal>>()

    var event: MutableLiveData<Event> = MutableLiveData()

    fun getAnimals(number: Int = 10) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                event.postValue(CoreEvent.Loading)
                _animalsLiveData.postValue(repository.getAnimals(number))
                event.postValue(CoreEvent.Success())
            } catch (throwable: Throwable) {
                // Error handler
                Log.d("MainViewModel", throwable.localizedMessage.toString())
                event.postValue(CoreEvent.Error("Something went wrong"))
            }
        }
    }

    fun addAnimalToDb(animal: Animal) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                event.postValue(CoreEvent.Loading)
                repository.addAnimalToDb(animal)
                event.postValue(CoreEvent.Success("Animal was added in favourite"))
            } catch (throwable: Throwable) {
                // Error handler
                Log.d("MainViewModel", throwable.localizedMessage.toString())
                event.postValue(CoreEvent.Error("Something went wrong"))
            }
        }
    }

}