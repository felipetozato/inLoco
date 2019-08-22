package com.felipe.test.inloco

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.felipe.test.inloco.model.WeatherCityInfo

/**
 * A fragment representing a list of Items.
 * Activities containing this fragment MUST implement the
 * [CityListFragment.OnListFragmentInteractionListener] interface.
 */
class CityListFragment : Fragment(), MyCityListRecyclerViewAdapter.OnListInteractionListener {

    private lateinit var list: Array<WeatherCityInfo>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            list = it.getParcelableArray(ARG_LIST) as Array<WeatherCityInfo>
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_citylist_list, container, false)

        // Set the adapter
        if (view is RecyclerView) {
            with(view) {
                layoutManager = LinearLayoutManager(context)
                adapter = MyCityListRecyclerViewAdapter(list, this@CityListFragment)
            }
        }
        return view
    }

    override fun onItemClicked(weatherCityInfo: WeatherCityInfo) {

    }

    companion object {

        const val ARG_LIST = "list"

        @JvmStatic
        fun newInstance(list: List<WeatherCityInfo>) =
            CityListFragment().apply {
                arguments = Bundle().apply {
                    putParcelableArray(ARG_LIST, list.toTypedArray())
                }
            }
    }

}
