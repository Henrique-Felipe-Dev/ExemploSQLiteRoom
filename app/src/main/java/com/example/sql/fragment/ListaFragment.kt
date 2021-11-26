package com.example.sql.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.sql.R
import com.example.sql.data.UserViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton


class ListaFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_lista, container, false)

        val floatingAdd = view.findViewById<FloatingActionButton>(R.id.floatingAdd)

        val adapter = ListAdapter()
        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(context)

        val userViewModel = UserViewModel(context)
        userViewModel.lerTodosOsDados.observe(viewLifecycleOwner, {
            response -> adapter.setData(response)
        })

        floatingAdd.setOnClickListener {
            findNavController().navigate(R.id.action_listaFragment_to_addFragment)
        }
        return view
    }
}