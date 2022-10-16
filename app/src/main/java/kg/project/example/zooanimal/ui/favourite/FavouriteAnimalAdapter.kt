package kg.project.example.zooanimal.ui.favourite

import android.graphics.Bitmap
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import kg.project.example.zooanimal.R
import kg.project.example.zooanimal.data.Animal
import kg.project.example.zooanimal.databinding.ListItemAnimalBinding
import kg.project.example.zooanimal.utils.setVisible

class FavouriteAnimalAdapter(private val listener: FavouriteAnimalListener) : ListAdapter<Animal, FavouriteAnimalViewHolder>(DIFF_CALLBACK)  {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavouriteAnimalViewHolder {
        return FavouriteAnimalViewHolder.from(parent, listener)
    }

    override fun onBindViewHolder(holder: FavouriteAnimalViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    companion object {
        val DIFF_CALLBACK: DiffUtil.ItemCallback<Animal> =
            object : DiffUtil.ItemCallback<Animal>() {
                override fun areItemsTheSame(oldItem: Animal, newItem: Animal) =
                    oldItem == newItem
                override fun areContentsTheSame(oldItem: Animal, newItem: Animal) =
                    oldItem.id == newItem.id

            }
    }
}

class FavouriteAnimalViewHolder(private val vb: ListItemAnimalBinding) : RecyclerView.ViewHolder(vb.root) {

    private lateinit var animal: Animal

    fun bind(animal: Animal) {
        this.animal = animal
        vb.run {
            ivFavourite.setImageDrawable(itemView.context.getDrawable(R.drawable.ic_delete))
            tvName.text = animal.name
            tvDescription.text = "Diet: ${animal.diet}"
            tvGeo.text = "Geo range: ${animal.geoRange}"
            tvLength.text = "Length from ${animal.lengthMin} to ${animal.lengthMax}"
            tvWeight.text = "Weight from ${animal.weightMin} to ${animal.weightMax}"
            tvLifespan.text = "Lifespan: ${animal.lifespan} years"
            Glide.with(itemView.context)
                .asBitmap()
                .load(animal.imageLink)
                .error(R.drawable.ic_home)
                .override(100)
                .addListener(
                object : RequestListener<Bitmap> {
                    override fun onResourceReady(
                        resource: Bitmap?,
                        model: Any?,
                        target: Target<Bitmap>?,
                        dataSource: DataSource?,
                        isFirstResource: Boolean
                    ): Boolean {
                        vb.progressBar.setVisible(false)
                        return false
                    }

                    override fun onLoadFailed(
                        e: GlideException?,
                        model: Any?,
                        target: Target<Bitmap>?,
                        isFirstResource: Boolean
                    ): Boolean {
                        vb.progressBar.setVisible(false)
                        return false
                    }

                }
            ) .into(ivIcon)
        }
    }

    companion object {
        fun from(parent: ViewGroup, listener: FavouriteAnimalListener): FavouriteAnimalViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val vb = ListItemAnimalBinding.inflate(layoutInflater, parent, false)
            return FavouriteAnimalViewHolder(vb).apply {
                vb.ivFavourite.setOnClickListener {
                    listener.onDeleteFavouriteClick(animal.id)
                }
            }
        }
    }

}

interface FavouriteAnimalListener {
    fun onDeleteFavouriteClick(id: Int?)
}