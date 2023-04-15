package com.ramces.catspragma.view.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ramces.catspragma.R
import com.ramces.catspragma.entities.Breed

class BreedAdapter(private var breeds: List<Breed>) : RecyclerView.Adapter<BreedAdapter.BreedViewHolder>() {

    inner class BreedViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val nameTextView: TextView = itemView.findViewById(R.id.nameTextView)
        private val originTextView: TextView = itemView.findViewById(R.id.originTextView)
        private val intelligenceTextView: TextView = itemView.findViewById(R.id.intelligenceTextView)
        private val imageView: ImageView = itemView.findViewById(R.id.breedImageView)

        fun bind(breed: Breed, context: Context) {
            nameTextView.text = breed.name
            originTextView.text = context.getString(R.string.ori, breed.origin)
            intelligenceTextView.text = context.getString(R.string.intelligence_text, breed.intelligence)

            Glide.with(context)
                .load(breed.imageUrl)
                .centerCrop()
                .into(imageView)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BreedViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.breed_item, parent, false)
        return BreedViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: BreedViewHolder, position: Int) {
        holder.bind(breeds[position], holder.itemView.context)
    }

    override fun getItemCount(): Int {
        return breeds.size
    }

    fun setBreeds(breeds: List<Breed>) {
        this.breeds = breeds
        notifyDataSetChanged()
    }
}
