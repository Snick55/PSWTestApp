package com.github.snick55.pswtestapp.presentation.carsList

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.github.snick55.pswtestapp.databinding.CarItemBinding

class CarsListAdapter(private val onItemPressed: (Int) -> Unit) :
    RecyclerView.Adapter<CarsListAdapter.ViewHolder>(), View.OnClickListener {

    private var cars: List<CarItemUi> = emptyList()

    fun setList(list: List<CarItemUi>) {
        val diffUtilCallback = Callback(cars, list)
        val diffResult = DiffUtil.calculateDiff(diffUtilCallback)
        cars = list
        diffResult.dispatchUpdatesTo(this)
    }

    inner class ViewHolder(private val binding: CarItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(car: CarItemUi) {
            binding.root.tag = car
            car.show(
                binding.brandTextView,
                binding.manufactureTextView,
                binding.priceTextView,
                binding.descriptionTextView
            )
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = CarItemBinding.inflate(inflater, parent, false)
        binding.root.setOnClickListener(this)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = cars.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(cars[position])
    }

    override fun onClick(view: View) {
        val car = view.tag as CarItemUi
        onItemPressed.invoke(car.id)
    }

    private class Callback(
        private val oldList: List<CarItemUi>,
        private val newList: List<CarItemUi>
    ) : DiffUtil.Callback() {
        override fun getOldListSize(): Int = oldList.size

        override fun getNewListSize(): Int = newList.size

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            val oldCar = oldList[oldItemPosition]
            val newCar = newList[newItemPosition]
            return oldCar.id == newCar.id
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            val oldCar = oldList[oldItemPosition]
            val newCar = newList[newItemPosition]
            return oldCar == newCar
        }
    }
}