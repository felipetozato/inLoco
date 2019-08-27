package com.felipe.test.inloco.features.citylist


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.felipe.test.inloco.R
import com.felipe.test.inloco.model.WeatherCityInfo
import com.felipe.test.inloco.utils.UnitConvertion
import kotlinx.android.synthetic.main.fragment_city_weather_detail.*

class CityWeatherDetailFragment : Fragment() {


    private lateinit var cityInfo: WeatherCityInfo

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            cityInfo = CityWeatherDetailFragmentArgs.fromBundle(it)
                .weatherInfo
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_city_weather_detail, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        textViewMaxTemp.text = cityInfo.tempInfo?.tempMax?.let {
            "${UnitConvertion.fromKtoC(it)} C"
        } ?: run { getString(R.string.temp_not_informed) }

        textViewMinTemp.text = cityInfo.tempInfo?.tempMin?.let {
            "${UnitConvertion.fromKtoC(it)} C"
        } ?: run { getString(R.string.temp_not_informed) }

        textViewDescription.text = cityInfo.weatherCityInfo?.first()?.description

        toolbar.title = cityInfo.name
    }

}
