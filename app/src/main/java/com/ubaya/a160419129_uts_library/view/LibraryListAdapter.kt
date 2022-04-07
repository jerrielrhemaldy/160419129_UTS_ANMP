package com.ubaya.a160419129_uts_library.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.ubaya.a160419129_uts_library.model.Library
import com.ubaya.a160419129_uts_library.R
import com.ubaya.a160419129_uts_library.util.loadImage
import kotlinx.android.synthetic.main.list_item_library.view.*

class LibraryListAdapter(val libraryList:ArrayList<Library>)
        :RecyclerView.Adapter<LibraryListAdapter.LibraryViewHolder>()
    {
        class LibraryViewHolder(var view: View):
            RecyclerView.ViewHolder(view)
        fun updateLibraryList(newLibraryList: List<Library>){
            libraryList.clear()
            libraryList.addAll(newLibraryList)
            notifyDataSetChanged()
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LibraryViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val view = inflater.inflate(R.layout.list_item_library,parent,false)
            return LibraryViewHolder(view)
        }

        override fun getItemCount(): Int {
            return libraryList.size
        }

        override fun onBindViewHolder(holder: LibraryViewHolder, position: Int) {
            val id = libraryList[position].id
            holder.view.textNama.text = libraryList[position].name

            holder.view.buttonDetail.setOnClickListener{

                val action = LibraryListFragmentDirections.actionLibraryDetail(id.toString())
                Navigation.findNavController(it).navigate(action)
            }
            holder.view.imageView4.loadImage(libraryList[position].photoUrl, holder.view.progressBar1)

        }

}