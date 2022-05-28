package com.example.sql.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sql.R
import com.example.sql.adapter.ListAdapter
import com.example.sql.data.OnItemSelectedListener
import com.example.sql.data.User
import com.example.sql.data.MainViewModel
import com.example.sql.databinding.FragmentListaBinding


class ListaFragment : Fragment(), OnItemSelectedListener {

    private val mainViewModel: MainViewModel by activityViewModels()
    private lateinit var binding: FragmentListaBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentListaBinding.inflate(layoutInflater, container, false)

        val adapter = ListAdapter(this, mainViewModel, requireContext())
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(context)

        mainViewModel.lerTodosOsDados.observe(viewLifecycleOwner) {
            response -> adapter.setData(response)
        }

        binding.floatingAdd.setOnClickListener {
            mainViewModel.itemSelecionado = null
            findNavController().navigate(R.id.action_listaFragment_to_addFragment)
        }
        return binding.root
    }

    override fun onItemSelectedListener(user: User) {
        mainViewModel.itemSelecionado = user
        findNavController().navigate(R.id.action_listaFragment_to_addFragment)
    }
}