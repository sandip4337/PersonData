package com.sandip.persondata

import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.sandip.persondata.ViewModel.MainViewModel
import com.sandip.persondata.data.Person
import kotlinx.android.synthetic.main.fragment_add.*
import kotlinx.android.synthetic.main.fragment_add.view.*

class AddFragment : Fragment() {

    lateinit var mainViewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val  view =  inflater.inflate(R.layout.fragment_add, container, false)

        mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        view.addbutton.setOnClickListener {
            addUserDataToDatabase()
        }

        return view
    }

    private fun addUserDataToDatabase() {

        val firstName = addFirstName.text.toString()
        val lastName = addLastName.text.toString()
        val age = addAge.text

        if(inputCheck(firstName,lastName,age)){

            val user = Person(0,firstName,lastName,Integer.parseInt(age.toString()))
            mainViewModel.addUser(user)

            Toast.makeText(requireContext(),"Successfully Added",Toast.LENGTH_SHORT).show()

            findNavController().navigate(R.id.action_addFragment_to_listFragment)
        }

    }

    private fun inputCheck(firstName: String, lastName: String, age: Editable):Boolean{
        return !(TextUtils.isEmpty(firstName) && TextUtils.isEmpty(lastName) && age.isEmpty())
    }
}