package com.example.food

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.food.Room.Food
import com.example.food.Room.FoodDao
import com.example.food.Room.Mydatabase
import com.example.food.databinding.ActivityMainBinding
import com.example.food.databinding.DaylogdeleteBinding
import com.example.food.databinding.DialogAddNewItemBinding
import com.example.food.databinding.UpdateBinding
import java.util.Random

//import room :
//1.Entity
//2.Dao
//3.datatbase
class MainActivity : AppCompatActivity(), Food_Adapter.FoodEvent {
    private lateinit var binding: ActivityMainBinding
    lateinit var MyAdapter: Food_Adapter
    lateinit var foodDao: FoodDao
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        foodDao = Mydatabase.getdatabase(this).foodDao

        val sharedperfences = getSharedPreferences("food", Context.MODE_PRIVATE)
        if (sharedperfences.getBoolean("avalinwrood", true)) {
            firstrun()
            sharedperfences.edit().putBoolean("avalinwrood", false).apply()
        }

        showalldata()


//
//        binding.btnaddnewfood.setOnClickListener{
//
//            val dialog = AlertDialog.Builder(this).create()
//            val dialog_bindinig = DialogAddNewItemBinding.inflate(layoutInflater)
//            dialog.setView(dialog_bindinig.root)
//            dialog.setCancelable(true)
//            dialog.show()
//
//            dialog_bindinig.dialogBtnDone.setOnClickListener{
//                if(
//                    dialog_bindinig.dialogEdtFoodCity.length()>0 &&
//                    dialog_bindinig.dialogEdtFoodDistance.length()>0 &&
//                    dialog_bindinig.dialogEdtFoodPrice.length()>0 &&
//                    dialog_bindinig.dialogEdtNameFood.length()>0
//                ) {
//                    val txtName = dialog_bindinig.dialogEdtNameFood.text.toString()
//                    val txtCity = dialog_bindinig.dialogEdtFoodCity.text.toString()
//                    val txtDistance = dialog_bindinig.dialogEdtFoodDistance.text.toString()
//                    val txtPrice = dialog_bindinig.dialogEdtFoodPrice.text.toString()
//                    val txtRateingNumber :Int = (1..150).random()
//                    val min = 0f
//                    val max = 5f
//                    val random :Float = min + Random().nextFloat()*(max-min)
//                    val ratingBarStar :Float = random
//                    val randomforurl = (0 until 12).random()
//                    val urlPic = foodlist[randomforurl].urlimage
//                    val newfood = Food(id,txtName , txtPrice , txtDistance , txtCity  , urlPic , txtRateingNumber , ratingBarStar)
//                    MyAdapter.AddFood(newfood)
//
//                    binding.recycleMain.scrollToPosition(0)
//                    dialog.dismiss()
//                }else{
//                    Toast.makeText(this, "Please type something...", Toast.LENGTH_SHORT).show()
//                }
//
//
//
//            }
//
//
//        }
//
//        binding.Search.addTextChangedListener {
//            if(it!!.isNotEmpty()){
//                //filter data :
//              val cloneList = foodlist.clone() as ArrayList<Food>
//                val filteredList = cloneList.filter {
//                     food ->
//                    food.txtsubject.contains(it)
//                }
//                MyAdapter.setData( ArrayList(filteredList))
//
//
//            }else{
//                //show all data :
//                MyAdapter.setData(foodlist.clone() as ArrayList<Food>)
//            }
//        }


    }

    private fun showalldata() {
        val foodData = foodDao.getallfood()
        MyAdapter = Food_Adapter(ArrayList(foodData), this)
        binding.recycleMain.adapter = MyAdapter

        binding.recycleMain.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)

    }

    private fun firstrun() {
        val foodlist = arrayListOf(

            Food(
                txtsubject = "Pizza Regina",
                price = "15",
                distance = "3",
                txtcity = "Italy",
                urlimage = "https://www.einfachkochen.de/sites/einfachkochen.de/files/styles/full_width_tablet_4_3/public/2022-06/pizza_regina_2.jpg",
                numofrate = 20,
                rating = 5f
            ),
            Food(
                txtsubject = "Piccata Milanese",
                price = "20.99",
                distance = "67",
                txtcity = "Milan",
                urlimage = "https://www.einfachkochen.de/sites/einfachkochen.de/files/styles/700_530/public/2021-07/schnitzel_milanese_1_0.jpg",
                numofrate = 45,
                rating = 4.5f
            ),
            Food(
                txtsubject = "Hamburger Labskaus",
                price = "12",
                distance = "87",
                txtcity = "Berlin",
                urlimage = "https://www.einfachkochen.de/sites/einfachkochen.de/files/styles/700_530/public/2022-07/2022_hamburger-labskaus_aufmacher.jpg",
                numofrate = 100,
                rating = 3f
            ),
            Food(
                txtsubject = "Badisches Schäufele",
                price = "10",
                distance = "56",
                txtcity = "Paris",
                urlimage = "https://www.einfachkochen.de/sites/einfachkochen.de/files/styles/700_530/public/2021-09/badisches_schaeufele_1_4.jpg",
                numofrate = 78,
                rating = 2.5f
            ),
            Food(
                txtsubject = "Hackbraten mit Ei",
                price = "9.99",
                distance = "85",
                txtcity = "Frankfurt",
                urlimage = "https://www.einfachkochen.de/sites/einfachkochen.de/files/styles/700_530/public/2022-11/2022_hackbraten-mit-ei_aufmacher.jpg",
                numofrate = 90,
                rating = 1f
            ),
            Food(
                txtsubject = "Glutenfreier Nudelsalat",
                price = "20",
                distance = "`76",
                txtcity = "Cologne",
                urlimage = "https://www.einfachkochen.de/sites/einfachkochen.de/files/styles/700_530/public/2022-06/glutenfreier_nudelsalat_15540.jpg",
                numofrate = 345,
                rating = 3.5f
            ),
            Food(
                txtsubject = "Pulpoarme",
                price = "89,99",
                distance = "786",
                txtcity = "Pekan",
                urlimage = "https://dsee-os-cdn.azureedge.net/public/thumbnail/ed/e7/3b/1611045421/pulpo_1280x1280.jpg",
                numofrate = 879,
                rating = 1.5f
            ),
            Food(
                txtsubject = "Caviar-Tasting ",
                price = "19,99",
                distance = "654",
                txtcity = "Hobart",
                urlimage = "https://dsee-os-cdn.azureedge.net/public/thumbnail/84/4b/bb/1633870444/Thunfischtatar-mit-Kaviartopping-Rezepte-Vorspeise-Aufmacher.jpg_400x400.jpg",
                numofrate = 239,
                rating = 5f
            ),
            Food(
                txtsubject = "Frutti di Mare Meeresfrüchte-Mix",
                price = "15.99",
                distance = "636",
                txtcity = "perth",
                urlimage = "https://dsee-os-cdn.azureedge.net/public/thumbnail/cd/e9/25/1611046921/83495-frutti-di-mare-rezept1_400x400.jpg",
                numofrate = 5678,
                rating = 3f
            ),
            Food(
                txtsubject = "Donald Russell",
                price = "19,99",
                distance = "500",
                txtcity = "Antalia",
                urlimage = "https://dsee-os-cdn.azureedge.net/public/thumbnail/fa/a5/72/1611050998/24567-donald-russell-lamm-carree-kaufen-rezept-2_400x400.jpg",
                numofrate = 3636,
                rating = 5f
            ),
            Food(
                txtsubject = "Rinder Roastbeef",
                price = "169,99",
                distance = "4687",
                txtcity = "Berlin",
                urlimage = "https://dsee-os-cdn.azureedge.net/public/thumbnail/e0/0c/44/1617867248/Surf-and-Turf-Rezepte-Tipps-von-Profis.jpg_400x400.jpg",
                numofrate = 1780,
                rating = 1.5f
            ),
            Food(
                txtsubject = "Räucherlachssalat",
                price = "15,99",
                distance = "5856",
                txtcity = "Monikh",
                urlimage = "https://dsee-os-cdn.azureedge.net/public/thumbnail/cb/78/b2/1674196768/Raeucherlachsalat-Senf-Dill-Sauce-kaufen-25402-1_1280x1280.jpg",
                numofrate = 7866,
                rating = 2.5f
            ),
        )
        foodDao.insertAllfood(foodlist)
    }

    override fun onFoodClicked(food: Food, position: Int) {
//        val dialog = AlertDialog.Builder(this).create()
//
//        val updateDialogBinding = UpdateBinding.inflate(layoutInflater)
//        updateDialogBinding.dialogEdtNameFood.setText(food.txtsubject)
//        updateDialogBinding.dialogEdtFoodCity.setText(food.txtcity)
//        updateDialogBinding.dialogEdtFoodPrice.setText(food.price)
//        updateDialogBinding.dialogEdtFoodDistance.setText(food.distance)
//
//        updateDialogBinding.dialogUpdateBtnCancel.setOnClickListener {
//            dialog.dismiss()
//        }
//
//        updateDialogBinding.dialogUpdateBtnDone.setOnClickListener(){
//            if(
//                updateDialogBinding.dialogEdtFoodCity.length()>0 &&
//                updateDialogBinding.dialogEdtFoodDistance.length()>0 &&
//                updateDialogBinding.dialogEdtFoodPrice.length()>0 &&
//                updateDialogBinding.dialogEdtNameFood.length()>0
//            ) {
//                val txtName = updateDialogBinding.dialogEdtNameFood.text.toString()
//                val txtCity = updateDialogBinding.dialogEdtFoodCity.text.toString()
//                val txtDistance = updateDialogBinding.dialogEdtFoodDistance.text.toString()
//                val txtPrice = updateDialogBinding.dialogEdtFoodPrice.text.toString()
//                    //Create New Fool To Add Recycler View
//                val newFood = Food(id , txtName , txtPrice , txtDistance , txtCity , food.urlimage , food.numofrate , food.rating)
//
//                // update data :
//                MyAdapter.updataFood(newFood , position)
//                dialog.dismiss()
//            }else{
//                Toast.makeText(this, "Please enter all values", Toast.LENGTH_SHORT).show()
//            }
//        }
//        dialog.setView(updateDialogBinding.root)
//        dialog.setCancelable(true)
//        dialog.show()
    }

    override fun onFoodLongClicked(food: Food, position: Int) {
        //نمایش دیالوگ
        val dialog = AlertDialog.Builder(this) .create()
        val dialogdeletbinding = DaylogdeleteBinding.inflate(layoutInflater)
        dialog.setView(dialogdeletbinding.root)
        dialog.setCancelable(true)
        dialog.show()

        dialogdeletbinding.dialogBtnDeleteCancel.setOnClickListener {
            dialog.dismiss()
        }
        dialogdeletbinding.dialogBtnDeleteSure.setOnClickListener {
            MyAdapter.deletefood(food , position)
            foodDao.deletefood(food)
            dialog.dismiss()
        }
    }


}