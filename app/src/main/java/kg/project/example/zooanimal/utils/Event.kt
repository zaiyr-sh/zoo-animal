package kg.project.example.zooanimal.utils

open class Event
sealed class CoreEvent : Event() {
    class Success(val message: String? = null) : CoreEvent()
    object Loading : CoreEvent()
    class Error(val message: String) : CoreEvent()
}