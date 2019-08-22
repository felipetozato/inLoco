package com.felipe.test.inloco

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import com.felipe.test.inloco.model.WeatherCityInfo

import kotlinx.android.synthetic.main.fragment_citylist.view.*


class MyCityListRecyclerViewAdapter(
    private val mValues: Array<WeatherCityInfo>,
    private val mListener: OnListInteractionListener?
) : RecyclerView.Adapter<MyCityListRecyclerViewAdapter.ViewHolder>() {

    interface OnListInteractionListener {

        fun onItemClicked(weatherCityInfo: WeatherCityInfo)
    }

    private val mOnClickListener: View.OnClickListener

    init {
        mOnClickListener = View.OnClickListener { v ->
            val item = v.tag as WeatherCityInfo
            mListener?.onItemClicked(item)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_citylist, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = mValues[position]
        holder.mContentView.text = item.name

        with(holder.mView) {
            tag = item
            setOnClickListener(mOnClickListener)
        }
    }

    override fun getItemCount(): Int = mValues.size

    inner class ViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
        val mContentView: TextView = mView.content

        override fun toString(): String {
            return super.toString() + " '" + mContentView.text + "'"
        }
    }
}
