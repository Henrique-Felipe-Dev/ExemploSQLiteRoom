package com.example.sql.adapter

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.sql.data.OnItemSelectedListener
import com.example.sql.data.User
import com.example.sql.data.MainViewModel
import com.example.sql.databinding.CustomRowBinding

class ListAdapter (
    private val onItemSelectedListener: OnItemSelectedListener,
    private val mainViewModel: MainViewModel,
    private val context: Context
        ): RecyclerView.Adapter<ListAdapter.MyViewHolder>() {

    private var userList = emptyList<User>()

    class MyViewHolder(val binding: CustomRowBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            CustomRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val user = userList[position]

        holder.binding.textNome.text = user.nome
        holder.binding.textRaca.text = user.raca
        holder.binding.textSexo.text = user.sexo
        holder.binding.textIdade.text = user.idade.toString()
        holder.binding.textDano.text = user.dano.toString()
        holder.binding.textHp.text = user.hp.toString()

        holder.binding.buttonEdit.setOnClickListener {
            onItemSelectedListener.onItemSelectedListener(user)
        }

        holder.binding.buttonDelete.setOnClickListener {
            showAlertDialog(user)
        }

    }

    override fun getItemCount(): Int {
        return userList.size
    }

    fun setData(user: List<User>){
        this.userList = user
        notifyDataSetChanged()
    }

    private fun showAlertDialog(user: User){
        AlertDialog.Builder(context)
            .setTitle("Deletar Personagem")
            .setMessage("Você realmente deseja deletar este personagem?")
            .setPositiveButton("Sim"){
                _,_ -> mainViewModel.deleteUser(user)
            }
            .setNegativeButton("Não"){
                _,_ ->
            }.show()
    }

}