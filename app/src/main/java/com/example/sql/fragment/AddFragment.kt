package com.example.sql.fragment

import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.sql.R
import com.example.sql.data.User
import com.example.sql.data.UserViewModel

class AddFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_add, container, false)

        val buttonAdd = view.findViewById<Button>(R.id.buttonAdd)

        val userViewModel = UserViewModel(context)

        val editNome = view.findViewById<EditText>(R.id.editNome)
        val editSobrenome = view.findViewById<EditText>(R.id.editSobrenome)
        val editIdade = view.findViewById<EditText>(R.id.editIdade)

        fun inputCheck(nome: String, sobreNome: String, idade: Editable): Boolean{
            return !(TextUtils.isEmpty(nome) &&
                    TextUtils.isEmpty(sobreNome) &&
                    idade.isEmpty()
                    )
        }

        fun inserirNoBanco(){
            val nome = editNome.text.toString()
            val sobreNome = editSobrenome.text.toString()
            val idade = editIdade.text

            if(inputCheck(nome, sobreNome, idade)){
                val user = User(0, nome, sobreNome, idade.toString().toInt())
                userViewModel.addUser(user)
                Toast.makeText(
                    context, "Usuário adicionado",
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

        buttonAdd.setOnClickListener {
            inserirNoBanco()
        }

        return view
    }

}