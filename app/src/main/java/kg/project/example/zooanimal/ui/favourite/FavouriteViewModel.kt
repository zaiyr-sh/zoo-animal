package kg.project.example.zooanimal.ui.favourite

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
class FavouriteViewModel @Inject constructor(private val repository: MainRepository) : ViewModel() {

    val animalsLiveData: LiveData<List<Animal>>
        get() = _animalsLiveData
    private val _animalsLiveData = MutableLiveData<List<Animal>>()

    var event: MutableLiveData<Event> = MutableLiveData()

    fun getAnimals() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                event.postValue(CoreEvent.Loading)
                _animalsLiveData.postValue(repository.getAnimalsFromDb())
                event.postValue(CoreEvent.Success())
            } catch (throwable: Throwable) {
                // Error handler
                Log.d("FavouriteViewModel", throwable.localizedMessage.toString())
                event.postValue(CoreEvent.Error("Something went wrong"))
            }
        }
    }

    fun deleteAnimalFromDb(id: Int?) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                event.postValue(CoreEvent.Loading)
                repository.deleteAnimalFromDb(id)
                getAnimals()
                event.postValue(CoreEvent.Success("Animal was deleted from favourite"))
            } catch (throwable: Throwable) {
                // Error handler
                Log.d("FavouriteViewModel", throwable.localizedMessage.toString())
                event.postValue(CoreEvent.Error("Something went wrong"))
            }
        }
    }

}