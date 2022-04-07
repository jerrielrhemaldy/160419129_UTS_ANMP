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
import com.ubaya.a160419129_uts_library.viewmodel.ListViewModel
import kotlinx.android.synthetic.main.fragment_library_list.*

class LibraryListFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_library_list,container, false)
    }
    private lateinit var viewModel: ListViewModel
    private val libraryListAdapter = LibraryListAdapter(arrayListOf())

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(ListViewModel::class.java)
        viewModel.refresh()

        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = libraryListAdapter

        observeViewModel()

        refreshLayout.setOnRefreshListener {
            recyclerView.visibility = View.GONE
            textEror.visibility = View.GONE
            progressBar.visibility = View.VISIBLE
            viewModel.refresh()
            refreshLayout.isRefreshing = false
        }

    }

    fun observeViewModel() {
        viewModel.libraryLD.observe(viewLifecycleOwner, Observer {
            libraryListAdapter.updateLibraryList(it)
        })
        viewModel.libraryLoadErrorLD.observe(viewLifecycleOwner, Observer {
            if(it == true){
                textEror.visibility = View.VISIBLE
            }
            else{
                textEror.visibility = View.GONE
            }
        })
        viewModel.loadingLD.observe(viewLifecycleOwner, Observer {
            if(it == true){
                recyclerView.visibility = View.GONE
                progressBar.visibility = View.VISIBLE
            }
            else{
                recyclerView.visibility = View.VISIBLE
                progressBar.visibility = View.GONE
            }
        })
    }

}