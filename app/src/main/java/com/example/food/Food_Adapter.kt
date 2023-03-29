package com.example.food

import android.content.Context
import android.os.Build
import android.util.Log
import android.view.LayoutInflater
import android.view.RoundedCorner
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.example.food.databinding.ItemFoodBinding
import jp.wasabeef.glide.transformations.RoundedCornersTransformation


class Food_Adapter (private  val data :ArrayList<Food>  , private  val foodevent : FoodEvent) :
    RecyclerView.Adapter<Food_Adapter.foodviewholder>(){


    inner  class  foodviewholder(private val binding: ItemFoodBinding) : RecyclerView.ViewHolder(binding.root){


        fun binddata(position: Int){

            binding.text1.text= data[position].txtsubject
            binding.text2.text = data[position].txtcity
            binding.text3.text = data[position].price
            binding.text4.text = data[position].distance
           binding.star.rating = data[position].rating

            Glide
                .with(binding.root.context)
                .load(data[position].urlimage)
                .transform(RoundedCornersTransformation(16 , 4))
                .into(binding.imagemain)

            itemView.setOnClickListener{
                foodevent.onFoodClicked(data[adapterPosition] , adapterPosition)

            }
            itemView.setOnLongClickListener{
                foodevent.onFoodLongClicked(data[adapterPosition],adapterPosition)
                true
            }

        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): foodviewholder {
       var binding = ItemFoodBinding.inflate(LayoutInflater.from(parent.context) , parent , false)

        return foodviewholder( binding  , )
    }

    override fun onBindViewHolder(holder: foodviewholder, position: Int) {
        holder.binddata(position)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    fun updataFood(newFood: Food , position : Int){
        //update item from list
        data[position] = newFood
        notifyItemChanged(position)
    }

     fun AddFood(newFood : Food){
        //add food to list
        data.add(0,newFood)
        notifyItemInserted(0)
    }
    fun deletefood ( oldfood : Food , oldPosition : Int){
        // remove item from list
        data.remove(oldfood)
        notifyItemRemoved(oldPosition)
    }
    fun setData(newList :ArrayList<Food>){
        //set new data to list :
        data.clear()
        data.addAll(
            newList
        )
        notifyDataSetChanged()

    }

    interface FoodEvent {

        //1.create interface in adapter
        //2.get an objucet if interface in args of adapter
        //3. fill(call) object of interface with your data
        //4. implementation on main activity

        fun onFoodClicked(food : Food , position: Int)
        fun onFoodLongClicked(food :Food , position : Int)
    }


}