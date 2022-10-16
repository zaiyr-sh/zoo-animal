package kg.project.example.zooanimal.utils

import android.content.Context
import android.view.View
import android.widget.Toast

fun View.setVisible(isVisible: Boolean) {
    visibility = when (isVisible) {
        true -> View.VISIBLE
        false -> View.GONE
    }
}

fun Context.showToast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}