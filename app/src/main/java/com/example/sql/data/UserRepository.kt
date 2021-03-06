package com.example.sql.data

import androidx.lifecycle.LiveData

class UserRepository(private val userDao: UserDao) {

    val lerTodosOsDados: LiveData<List<User>> = userDao.lerTodosOsDados()

    fun addUser(user: User){
        userDao.addUser(user)
    }

    fun updateUser(user: User){
        userDao.updateUser(user)
    }

    fun deleteUser(user: User){
        userDao.deleteUser(user)
    }
}