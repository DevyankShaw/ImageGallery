package com.example.devyankshaw.imagegalleryusingmvvmretrofitglidekotlin.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.devyankshaw.imagegalleryusingmvvmretrofitglidekotlin.data.api.FlickerInterface
import com.example.devyankshaw.imagegalleryusingmvvmretrofitglidekotlin.data.model.ImageUrls
import com.google.gson.Gson
import com.google.gson.JsonObject
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class HomeViewModel() : ViewModel() {

    var mImageUrlList: ArrayList<ImageUrls>
    var imageLiveData: MutableLiveData<ArrayList<ImageUrls>>

    init {
        mImageUrlList = ArrayList()
        imageLiveData = MutableLiveData()

        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.flickr.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val flickerInterface: FlickerInterface = retrofit.create(FlickerInterface::class.java)

        val call: Call<JsonObject?>? = flickerInterface.getImageDetails()

        call!!.enqueue(object : Callback<JsonObject?> {

            override fun onResponse(call: Call<JsonObject?>, response: Response<JsonObject?>) {
                if (response.isSuccessful) {
                    try {
                        val jsonObject = JSONObject(Gson().toJson(response.body()))
                        val jsonArray: JSONArray = jsonObject.getJSONObject("photos").getJSONArray("photo")
                        for (k in 0 until jsonArray.length()) {
                            val jsonObject: JSONObject? = jsonArray.getJSONObject(k)
                            mImageUrlList.add(ImageUrls(jsonObject?.get("url_s").toString()))
                        }
                        imageLiveData.postValue(mImageUrlList)
                    } catch (e: JSONException) {
                        e.printStackTrace()
                    }
                } else {
                    /*Toast.makeText(activity, "Some error occurred...", Toast.LENGTH_LONG)
                        .show()*/
                }
            }

            override fun onFailure(call: Call<JsonObject?>, t: Throwable) {
                Log.d("ImageResponse", t.toString())
            }
        })
    }

    fun getImagesList(): LiveData<ArrayList<ImageUrls>>? {
        return imageLiveData
    }

}
