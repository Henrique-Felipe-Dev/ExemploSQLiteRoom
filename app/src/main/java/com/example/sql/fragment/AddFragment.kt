package com.example.sql.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.sql.R
import com.example.sql.data.User
import com.example.sql.data.MainViewModel
import com.example.sql.databinding.FragmentAddBinding

class AddFragment : Fragment() {

    private lateinit var binding: FragmentAddBinding
    private val mainViewModel: MainViewModel by activityViewModels()
    private var itemSelecionado: User? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentAddBinding.inflate(layoutInflater, container, false)

        carregarDados()

        binding.buttonAdd.setOnClickListener {
            inserirNoBanco()
        }

        return binding.root
    }

    private fun inputCheck(nome: String, raca: String, sexo: String, idade: String, dano: String, hp: String): Boolean{
        return !(nome == "" || raca == "" || sexo == "" || idade == "" || dano == "" || hp == "")
    }

    private fun inserirNoBanco(){
        val nome = binding.editNome.text.toString()
        val raca = binding.editRaca.text.toString()
        val sexo = binding.editSexo.text.toString()
        val idade = binding.editIdade.text.toString()
        val dano = binding.editDano.text.toString()
        val hp = binding.editHp.text.toString()

        if(inputCheck(nome, raca, sexo, idade, dano, hp)){
            val salvar: String
            if(itemSelecionado != null){
                salvar = "Personagem Atualizado"
                val user = User(itemSelecionado?.id!!, nome, raca, sexo, idade.toInt(), dano.toDouble(), hp.toDouble())
                mainViewModel.updateUser(user)
            }else{
                salvar = "Personagem Criado"
                val user = User(0, nome, raca, sexo, idade.toInt(), dano.toDouble(), hp.toDouble())
                mainViewModel.addUser(user)
            }
            Toast.makeText(
                context, salvar,
                Toast.LENGTH_LONG
            ).show()
            findNavController().navigate(R.id.action_addFragment_to_listaFragment)
        }else{
            Toast.makeText(
                context, "Preencha todos os campos!",
                Toast.LENGTH_LONG
            ).show()
        }
    }

    private fun carregarDados(){
        itemSelecionado = mainViewModel.itemSelecionado
        if(itemSelecionado != null){
            binding.editNome.setText(itemSelecionado?.nome)
            binding.editRaca.setText(itemSelecionado?.raca)
            binding.editSexo.setText(itemSelecionado?.sexo)
            binding.editIdade.setText(itemSelecionado?.idade.toString())
            binding.editDano.setText(itemSelecionado?.dano.toString())
            binding.editHp.setText(itemSelecionado?.hp.toString())
        }
    }

}