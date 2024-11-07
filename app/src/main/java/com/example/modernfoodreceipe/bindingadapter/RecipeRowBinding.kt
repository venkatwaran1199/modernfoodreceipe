package com.example.modernfoodreceipe.bindingadapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import coil3.load
import coil3.request.crossfade
import com.example.modernfoodreceipe.R

class RecipeRowBinding {


    companion object{

        @BindingAdapter("loadimagefromurl")
        @JvmStatic
        fun loadimagefromurl(imageView: ImageView,imageurl:String){
            imageView.load(imageurl) {
                crossfade(600)
            }
        }

        @BindingAdapter("convertInttoString")
        @JvmStatic
        fun convertInttoString(textView: TextView,number:Int){
            textView.text = number.toString()
        }

        @BindingAdapter("applyvegancolor")
        @JvmStatic
        fun applyvegancolor(view:View , veganstatus:Boolean){
            if(veganstatus){
                when(view) {
                    is TextView ->{
                        view.setTextColor(
                            ContextCompat.getColor(
                            view.context,
                            R.color.green
                            )
                        )
                    }

                    is ImageView ->{
                        view.setColorFilter(
                            ContextCompat.getColor(
                                view.context,
                                R.color.green
                            )
                        )
                    }
                }
            }
        }
    }
}