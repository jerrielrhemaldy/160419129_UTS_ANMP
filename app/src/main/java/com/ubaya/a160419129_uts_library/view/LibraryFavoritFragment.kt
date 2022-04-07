package com.ubaya.a160419129_uts_library.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.ubaya.a160419129_uts_library.R
import com.ubaya.a160419129_uts_library.model.Library
import com.ubaya.a160419129_uts_library.viewmodel.ListViewModel
import kotlinx.android.synthetic.main.fragment_library_favorit.*
import kotlinx.android.synthetic.main.fragment_library_list.*

/**
 * A simple [Fragment] subclass.
 * Use the [LibraryFavoritFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class LibraryFavoritFragment : Fragment() {

    private lateinit var viewModel: ListViewModel
    private val libraryListAdapter = LibraryListAdapter(arrayListOf())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_library_favorit, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel = ViewModelProvider(this).get(ListViewModel::class.java)
        viewModel.refresh()

        recyclerViewFavorite.layoutManager = LinearLayoutManager(context)
        recyclerViewFavorite.adapter = libraryListAdapter

        observeViewModel()

        refreshLayout.setOnRefreshListener {
            recyclerViewFavorite.visibility = View.GONE
            textErorFavorite.visibility = View.GONE
            progressBarFavorite.visibility = View.VISIBLE
            viewModel.refresh()
            refreshLayoutFavorite.isRefreshing = false
        }

    }

    private fun observeViewModel() {
        viewModel.libraryLD.observe(viewLifecycleOwner){
            var array: ArrayList<Library> = arrayListOf()
            array.add(it[1])
            libraryListAdapter.updateLibraryList(array)
        }
        viewModel.libraryLoadErrorLD.observe(viewLifecycleOwner){
            textErorFavorite.visibility = if (it) View.VISIBLE else View.GONE
        }
        viewModel.loadingLD.observe(viewLifecycleOwner, Observer {
            if(it){
                recyclerViewFavorite.visibility = View.GONE
                progressBarFavorite.visibility = View.VISIBLE
            }
            else{
                recyclerViewFavorite.visibility = View.VISIBLE
                progressBarFavorite.visibility = View.GONE
            }
        })
    }
}