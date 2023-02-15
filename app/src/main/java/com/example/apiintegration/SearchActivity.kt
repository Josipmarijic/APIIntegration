package com.example.apiintegration

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.apiintegration.databinding.ActivitySearchBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class SearchActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySearchBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val apiKey = "d0a05f628d858bc96745ca318c31d4d4"

        //använder Retrofot bibloteket för att göra det enklare att göra api anropet
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.openweathermap.org/")
                //Använder Gson biblioteket för att parsa json svaret från apiet
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        //Skapar ett gränssnitt genom klassen weatherapi så när väl api anropet görs så vet den vilken data jag vill ha från apiet
        val service = retrofit.create(WeatherApi::class.java)





        binding.serachBtn.setOnClickListener{
            val city = binding.city.text.toString()
            //skapar urlen
            val call = service.getCurrentWeatherData(city,apiKey,"metric")
            //loggar urlen så jag vet att den funkar ifall något inte skulle visas
            Log.d("api call", call.request().url().toString())
            //Retrofit har den här metoden för att gör api anropet. call.enqueue gör aå att medans man väntar på svar så fungerar appen fortfarande
            //ifall man skulle ha långsamt internet
            call.enqueue(object  : Callback<WeatherResponse>{
                override fun onResponse(
                    call: Call<WeatherResponse>,
                    response: Response<WeatherResponse>
                ) {
                    if (response.isSuccessful){
                        //här får jag svaret och hämtar ut datan jag vill ha med hjälp av Gson biblioteket
                        val  weatherResponse = response.body()
                        val cityName = weatherResponse?.name
                        val temprature = "${weatherResponse?.main?.temperature}°C"
                        val description = weatherResponse?.weather?.get(0)?.description
                        val icon = "https://openweathermap.org/img/w/${weatherResponse?.weather?.get(0)?.icon}.png"

                        val fragment = DisplayFragment()
                        //Skapar en bundle för att skicka med datan som jag hämtade från apiet till min fragment
                        val bundle = Bundle().apply {
                            putString("city",cityName)
                            putString("temp",temprature)
                            putString("description",description)
                            putString("icon",icon)
                        }

                        fragment.arguments = bundle

                        supportFragmentManager.beginTransaction()
                            .replace(R.id.container, fragment )
                            .commit()
                    }
                }

                override fun onFailure(call: Call<WeatherResponse>, t: Throwable) {
                    Toast.makeText(this@SearchActivity,"Something went wrong",Toast.LENGTH_SHORT).show()
                }
            })

        }
    }
}