package com.example.food.Room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface FoodDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertOrUpdate(food: Food)
//    @Insert
//    fun insertfood (food: Food)
    @Insert
    fun insertAllfood(data:List<Food>)

//    @Update
//    fun update(food: Food)

    //چرا کوئری زدیم ؟ چون اینجا نمیتونست بفهمه چجوری بگیره
    @Query("select * from table_food")
    fun getallfood() : List<Food>

    @Delete
    fun deletefood(food: Food)

    @Query("delete from table_food")
    fun deletealldata()

    @Query("select * from table_food where txtsubject like '%'||:Searching ||'%'")
    fun searchfood(Searching:String):List<Food>



}