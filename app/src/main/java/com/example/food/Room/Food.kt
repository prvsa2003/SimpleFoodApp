package com.example.food.Room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "table_food")
data class Food (
    @PrimaryKey(autoGenerate = true)
    val id : Int?=null,


    val txtsubject : String ,
    val price : String ,
    val distance : String ,
    val txtcity : String ,

    @ColumnInfo(name = "url")
    val urlimage : String ,


    val numofrate : Int ,
    val rating : Float

        )