package com.example.devyankshaw.imagegalleryusingmvvmretrofitglidekotlin.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.devyankshaw.imagegalleryusingmvvmretrofitglidekotlin.R
import com.example.devyankshaw.imagegalleryusingmvvmretrofitglidekotlin.data.model.ImageUrls
import kotlinx.android.synthetic.main.home_fragment.*

class HomeFragment : Fragment() {

    lateinit var mRecyclerView: RecyclerView
    lateinit var mImageAdapter: ImageAdapter
    lateinit var viewModel: HomeViewModel

    companion object {
        fun newInstance() = HomeFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.home_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(HomeViewModel::class.java)
        viewModel.getImagesList()
            ?.observe(viewLifecycleOwner,
                Observer<ArrayList<ImageUrls>> { list ->
                    recycler_view.adapter = ImageAdapter(activity, list)
                    recycler_view.layoutManager = LinearLayoutManager(activity)
                    recycler_view.setHasFixedSize(true)
                })

    }

}
