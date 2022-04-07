package com.ubaya.a160419129_uts_library.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.ubaya.a160419129_uts_library.R
import com.ubaya.a160419129_uts_library.util.loadImage
import com.ubaya.a160419129_uts_library.viewmodel.DetailViewModel
import kotlinx.android.synthetic.main.fragment_library_detail.*

class LibraryDetailFragment : Fragment() {
    private lateinit var viewModel: DetailViewModel
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_library_detail,container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        var id = ""
        arguments?.let {
            id = LibraryDetailFragmentArgs.fromBundle(requireArguments()).idBook
        }
        viewModel = ViewModelProvider(this).get(DetailViewModel::class.java)
        viewModel.fetch(id)

        observeViewModel()


    }

    private fun observeViewModel() {
        viewModel.libraryDetailLD.observe(viewLifecycleOwner){
            textJudulBuku.setText(it.name)
            textInfo.setText(it.detail)
            imageView2.loadImage(it.photoUrl, progressBar)
        }
    }
}