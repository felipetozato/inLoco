package com.felipe.test.inloco

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController

import com.felipe.test.inloco.model.WeatherCityInfo
import androidx.recyclerview.widget.DividerItemDecoration
import kotlinx.android.synthetic.main.fragment_citylist_list.*


class CityListFragment : Fragment(), MyCityListRecyclerViewAdapter.OnListInteractionListener {

    private lateinit var list: Array<WeatherCityInfo>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            list = CityListFragmentArgs.fromBundle(it).list
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? =
        inflater.inflate(R.layout.fragment_citylist_list, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Set the adapter
        with(recyclerViewList) {
            val layout = LinearLayoutManager(context)
            layoutManager = layout
            adapter = MyCityListRecyclerViewAdapter(list, this@CityListFragment)
            val dividerItemDecoration = DividerItemDecoration(context, layout.orientation)
            this.addItemDecoration(dividerItemDecoration)
        }
    }

    override fun onItemClicked(weatherCityInfo: WeatherCityInfo) {
        val directions = CityListFragmentDirections.
            actionCityListFragmentToCityWeatherDetailFragment(weatherCityInfo)
        findNavController().navigate(directions)
    }

}
