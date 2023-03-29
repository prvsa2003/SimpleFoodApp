package com.example.food

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.food.databinding.ActivityMainBinding
import com.example.food.databinding.DaylogdeleteBinding
import com.example.food.databinding.DialogAddNewItemBinding
import com.example.food.databinding.UpdateBinding
import java.util.Random

class MainActivity : AppCompatActivity() , Food_Adapter.FoodEvent {
    private lateinit var binding: ActivityMainBinding
     lateinit var MyAdapter : Food_Adapter
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val foodlist = arrayListOf(

            Food("Pizza Regina" , "15" ,"3"  , "Italy" , "https://www.einfachkochen.de/sites/einfachkochen.de/files/styles/full_width_tablet_4_3/public/2022-06/pizza_regina_2.jpg" ,  20 , 5f),
            Food("Piccata Milanese" , "20.99" ,"67"  , "Milan" , "https://www.einfachkochen.de/sites/einfachkochen.de/files/styles/700_530/public/2021-07/schnitzel_milanese_1_0.jpg" ,  45 , 4.5f),
            Food("Hamburger Labskaus" , "12"  , "87" , "Berlin" , "https://www.einfachkochen.de/sites/einfachkochen.de/files/styles/700_530/public/2022-07/2022_hamburger-labskaus_aufmacher.jpg" , 100 , 3f),
            Food("Badisches Schäufele" , "10" , "56" , "Paris" , "https://www.einfachkochen.de/sites/einfachkochen.de/files/styles/700_530/public/2021-09/badisches_schaeufele_1_4.jpg" , 78 , 2.5f ),
            Food("Hackbraten mit Ei" , "9.99" , "85" , "Frankfurt" , "https://www.einfachkochen.de/sites/einfachkochen.de/files/styles/700_530/public/2022-11/2022_hackbraten-mit-ei_aufmacher.jpg" , 90  , 1f ) ,
            Food("Glutenfreier Nudelsalat" , "20" , "`76" , "Cologne" , "https://www.einfachkochen.de/sites/einfachkochen.de/files/styles/700_530/public/2022-06/glutenfreier_nudelsalat_15540.jpg" , 345 , 3.5f) ,
            Food("Pulpoarme" , "89,99" ,"786" , "Pekan" , "https://dsee-os-cdn.azureedge.net/public/thumbnail/ed/e7/3b/1611045421/pulpo_1280x1280.jpg" , 879 , 1.5f ) ,
            Food("Caviar-Tasting " , "19,99" , "654" , "Hobart" , "https://dsee-os-cdn.azureedge.net/public/thumbnail/84/4b/bb/1633870444/Thunfischtatar-mit-Kaviartopping-Rezepte-Vorspeise-Aufmacher.jpg_400x400.jpg" , 239 , 5f) ,
            Food("Frutti di Mare Meeresfrüchte-Mix" , "15.99" ,"636"  , "perth" , "https://dsee-os-cdn.azureedge.net/public/thumbnail/cd/e9/25/1611046921/83495-frutti-di-mare-rezept1_400x400.jpg" ,  5678 , 3f),
            Food("Donald Russell" , "19,99" ,"500"  , "Antalia" , "https://dsee-os-cdn.azureedge.net/public/thumbnail/fa/a5/72/1611050998/24567-donald-russell-lamm-carree-kaufen-rezept-2_400x400.jpg" ,  3636 , 5f),
            Food("Rinder Roastbeef" , "169,99"  , "4687" , "Berlin" , "https://dsee-os-cdn.azureedge.net/public/thumbnail/e0/0c/44/1617867248/Surf-and-Turf-Rezepte-Tipps-von-Profis.jpg_400x400.jpg" , 1780 , 1.5f),
            Food("Räucherlachssalat" , "15,99" , "5856" , "Monikh" , "https://dsee-os-cdn.azureedge.net/public/thumbnail/cb/78/b2/1674196768/Raeucherlachsalat-Senf-Dill-Sauce-kaufen-25402-1_1280x1280.jpg" , 7866 , 2.5f ),
        )
         MyAdapter = Food_Adapter(foodlist.clone() as ArrayList<Food>, this)
        binding.recycleMain.adapter =MyAdapter

        binding.recycleMain.layoutManager = LinearLayoutManager(this , RecyclerView.VERTICAL , false)


        binding.btnaddnewfood.setOnClickListener{

            val dialog = AlertDialog.Builder(this).create()
            val dialog_bindinig = DialogAddNewItemBinding.inflate(layoutInflater)
            dialog.setView(dialog_bindinig.root)
            dialog.setCancelable(true)
            dialog.show()

            dialog_bindinig.dialogBtnDone.setOnClickListener{
                if(
                    dialog_bindinig.dialogEdtFoodCity.length()>0 &&
                    dialog_bindinig.dialogEdtFoodDistance.length()>0 &&
                    dialog_bindinig.dialogEdtFoodPrice.length()>0 &&
                    dialog_bindinig.dialogEdtNameFood.length()>0
                ) {
                    val txtName = dialog_bindinig.dialogEdtNameFood.text.toString()
                    val txtCity = dialog_bindinig.dialogEdtFoodCity.text.toString()
                    val txtDistance = dialog_bindinig.dialogEdtFoodDistance.text.toString()
                    val txtPrice = dialog_bindinig.dialogEdtFoodPrice.text.toString()
                    val txtRateingNumber :Int = (1..150).random()
                    val min = 0f
                    val max = 5f
                    val random :Float = min + Random().nextFloat()*(max-min)
                    val ratingBarStar :Float = random
                    val randomforurl = (0 until 12).random()
                    val urlPic = foodlist[randomforurl].urlimage
                    val newfood = Food(txtName , txtPrice , txtDistance , txtCity  , urlPic , txtRateingNumber , ratingBarStar)
                    MyAdapter.AddFood(newfood)

                    binding.recycleMain.scrollToPosition(0)
                    dialog.dismiss()
                }else{
                    Toast.makeText(this, "Please type something...", Toast.LENGTH_SHORT).show()
                }



            }


        }

        binding.Search.addTextChangedListener {
            if(it!!.isNotEmpty()){
                //filter data :
              val cloneList = foodlist.clone() as ArrayList<Food>
                val filteredList = cloneList.filter {
                     food ->
                    food.txtsubject.contains(it)
                }
                MyAdapter.setData( ArrayList(filteredList))


            }else{
                //show all data :
                MyAdapter.setData(foodlist.clone() as ArrayList<Food>)
            }
        }



    }

    override fun onFoodClicked(food : Food , position: Int) {
        val dialog = AlertDialog.Builder(this).create()

        val updateDialogBinding = UpdateBinding.inflate(layoutInflater)
        updateDialogBinding.dialogEdtNameFood.setText(food.txtsubject)
        updateDialogBinding.dialogEdtFoodCity.setText(food.txtcity)
        updateDialogBinding.dialogEdtFoodPrice.setText(food.price)
        updateDialogBinding.dialogEdtFoodDistance.setText(food.distance)

        updateDialogBinding.dialogUpdateBtnCancel.setOnClickListener {
            dialog.dismiss()
        }

        updateDialogBinding.dialogUpdateBtnDone.setOnClickListener(){
            if(
                updateDialogBinding.dialogEdtFoodCity.length()>0 &&
                updateDialogBinding.dialogEdtFoodDistance.length()>0 &&
                updateDialogBinding.dialogEdtFoodPrice.length()>0 &&
                updateDialogBinding.dialogEdtNameFood.length()>0
            ) {
                val txtName = updateDialogBinding.dialogEdtNameFood.text.toString()
                val txtCity = updateDialogBinding.dialogEdtFoodCity.text.toString()
                val txtDistance = updateDialogBinding.dialogEdtFoodDistance.text.toString()
                val txtPrice = updateDialogBinding.dialogEdtFoodPrice.text.toString()
                    //Create New Fool To Add Recycler View
                val newFood = Food(txtName , txtPrice , txtDistance , txtCity , food.urlimage , food.numofrate , food.rating)

                // update data :
                MyAdapter.updataFood(newFood , position)
                dialog.dismiss()
            }else{
                Toast.makeText(this, "Please enter all values", Toast.LENGTH_SHORT).show()
            }
        }
        dialog.setView(updateDialogBinding.root)
        dialog.setCancelable(true)
        dialog.show()
    }

    override fun onFoodLongClicked(food: Food, position: Int) {
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
            dialog.dismiss()
        }
    }




}