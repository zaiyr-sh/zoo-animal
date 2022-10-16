package kg.project.example.zooanimal.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import kg.project.example.zooanimal.data.Animal
import kg.project.example.zooanimal.databinding.FragmentMainBinding
import kg.project.example.zooanimal.utils.CoreEvent
import kg.project.example.zooanimal.utils.setVisible
import kg.project.example.zooanimal.utils.showToast

@AndroidEntryPoint
class MainFragment : Fragment(), AnimalListener {

    private val vm: MainViewModel by viewModels()
    private lateinit var vb: FragmentMainBinding
    private var adapter = AnimalAdapter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vm.getAnimals()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        vb = FragmentMainBinding.inflate(layoutInflater, container, false)
        return vb.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        vb.rvAnimals.adapter = adapter
        vm.animalsLiveData.observe(viewLifecycleOwner) { animals ->
            adapter.submitList(animals)
        }
        vm.event.observe(viewLifecycleOwner) { event ->
            when (event) {
                is CoreEvent.Loading -> vb.progressBar.setVisible(true)
                is CoreEvent.Success -> {
                    vb.progressBar.setVisible(false)
                    event.message?.let { requireContext().showToast(it) }
                }
                is CoreEvent.Error -> {
                    vb.progressBar.setVisible(false)
                    requireContext().showToast(event.message)
                }
            }
        }
    }

    override fun onAddFavouriteClick(animal: Animal) {
        vm.addAnimalToDb(animal)
    }
}