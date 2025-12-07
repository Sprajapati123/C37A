package com.example.c37a.repository

import com.example.c37a.model.UserModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class UserRepoImpl : UserRepo {

    val auth: FirebaseAuth = FirebaseAuth.getInstance()
    val database: FirebaseDatabase = FirebaseDatabase.getInstance()

    var ref: DatabaseReference = database.getReference("users")

    override fun login(
        email: String,
        password: String,
        callback: (Boolean, String) -> Unit
    ) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    callback(true, "Login success")
                } else {
                    callback(false, "${it.exception?.message}")

                }
            }
    }

    override fun register(
        email: String,
        password: String,
        callback: (Boolean, String, String) -> Unit
    ) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    callback(true, "Registration success", "${auth.currentUser?.uid}")
                } else {
                    callback(false, "${it.exception?.message}", "")
                }
            }
    }

    override fun addUserToDatabase(
        userId: String,
        model: UserModel,
        callback: (Boolean, String) -> Unit
    ) {
        ref.child(userId).setValue(model).addOnCompleteListener {
            if (it.isSuccessful) {
                callback(true, "Registration success")
            } else {
                callback(false, "${it.exception?.message}")
            }
        }
    }

    override fun updateProfile(
        userId: String,
        model: UserModel,
        callback: (Boolean, String) -> Unit
    ) {
        ref.child(userId).updateChildren(model.toMap())
            .addOnCompleteListener {
            if (it.isSuccessful) {
                callback(true, "Profile updated")
            } else {
                callback(false, "${it.exception?.message}")
            }
        }
    }

    override fun deleteAccount(
        userId: String,
        callback: (Boolean, String) -> Unit
    ) {
        ref.child(userId).removeValue().addOnCompleteListener {
            if(it.isSuccessful){
                callback(true,"Account deleted")
            }else{
                callback(false,"${it.exception?.message}")
            }
        }
    }

    override fun getUserById(
        userId: String,
        callback: (Boolean, String, UserModel?) -> Unit
    ) {
        TODO("Not yet implemented")
    }

    override fun getAllUser(callback: (Boolean, String, List<UserModel>?) -> Unit) {
        TODO("Not yet implemented")
    }

    override fun getCurrentUser(): FirebaseUser {
        TODO("Not yet implemented")
    }

    override fun logOut(callback: (Boolean, String) -> Unit) {
        TODO("Not yet implemented")
    }
}