package com.example.apiintegration

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide


class DisplayFragment : Fragment() {
    //Deklarerar mina views
    private lateinit var cityText: TextView
    private lateinit var tempText: TextView
    private lateinit var descriptionText: TextView
    private lateinit var iconImage: ImageView



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstance: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_display, container, false)

         cityText = view.findViewById(R.id.city)
         tempText = view.findViewById(R.id.temp)
         descriptionText = view.findViewById(R.id.description)
         iconImage = view.findViewById(R.id.icon)
        //hämtar datan som jag skickade med i bundeln
        val args = arguments
        if (args != null){
            //Hämtar varje data genom att ange nyckel tex "city" för att få variablen
            val city = args.getString("city")
            val temp = args.getString("temp")
            val description = args.getString("description")
            val icon = args.getString("icon")

            cityText.text = city
            tempText.text = temp
            descriptionText.text = description
            //Använder glide bibloteket för att visa bilden
            Glide.with(this).load(icon).into(iconImage)


        }

        return view
    }




}