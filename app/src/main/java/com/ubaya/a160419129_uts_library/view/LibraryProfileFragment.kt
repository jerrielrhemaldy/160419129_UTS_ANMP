package com.ubaya.a160419129_uts_library.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.ubaya.a160419129_uts_library.R
import kotlinx.android.synthetic.main.fragment_library_profile.*

/**
 * A simple [Fragment] subclass.
 * Use the [LibraryProfileFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class LibraryProfileFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_library_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        buttonAbout.setOnClickListener {
            val action = LibraryProfileFragmentDirections.actionAboutLibrary()
            Navigation.findNavController(it).navigate(action)
        }
    }
}