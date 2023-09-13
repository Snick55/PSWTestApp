package com.github.snick55.pswtestapp.presentation.filters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.github.snick55.pswtestapp.databinding.FilterItemBinding

class FiltersAdapter: RecyclerView.Adapter<FiltersAdapter.FiltersViewHolder> (){

    private var filters: List<String> = emptyList()

    fun setUpList(list: List<String>){
        filters = list
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FiltersViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = FilterItemBinding.inflate(inflater, parent, false)
        return FiltersViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FiltersViewHolder, position: Int) {
       holder.bind(filters[position])
    }

    override fun getItemCount(): Int = filters.size

    inner class FiltersViewHolder(private val binding: FilterItemBinding): RecyclerView.ViewHolder(binding.root){
       fun bind(filterName: String){
           binding.filterName.text = filterName
       }
    }
}

