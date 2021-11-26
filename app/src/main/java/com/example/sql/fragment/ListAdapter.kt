package com.example.sql.fragment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.sql.R
import com.example.sql.data.User

class ListAdapter: RecyclerView.Adapter<ListAdapter.MyViewHolder>() {

    private var userList = emptyList<User>()

    class MyViewHolder(view: View): RecyclerView.ViewHolder(view){
        val textId = view.findViewById<TextView>(R.id.textId)
        val textNome = view.findViewById<TextView>(R.id.textNome)
        val textSobrenome = view.findViewById<TextView>(R.id.textSobrenome)
        val textIdade = view.findViewById<TextView>(R.id.textIdade)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context)
            .inflate(R.layout.custom_row, parent, false)
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = userList[position]

        holder.textId.text = currentItem.id.toString()
        holder.textNome.text = currentItem.nome
        holder.textSobrenome.text = currentItem.sobrenome
        holder.textIdade.text = currentItem.idade.toString()
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    fun setData(user: List<User>){
        this.userList = user
        notifyDataSetChanged()
    }

}