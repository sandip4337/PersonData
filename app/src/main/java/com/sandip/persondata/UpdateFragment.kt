package com.sandip.persondata

import android.app.AlertDialog
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.sandip.persondata.ViewModel.MainViewModel
import com.sandip.persondata.data.Person
import kotlinx.android.synthetic.main.fragment_add.*
import kotlinx.android.synthetic.main.fragment_update.*
import kotlinx.android.synthetic.main.fragment_update.view.*

class UpdateFragment : Fragment() {

    lateinit var mainViewModel: MainViewModel

    private val args by navArgs<UpdateFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_update, container, false)

        mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        view.updateFirstName.setText(args.currentPerson.FirstName)
        view.updateLastName.setText(args.currentPerson.LastName)
        view.updateAge.setText(args.currentPerson.Age.toString())

        view.updatebutton.setOnClickListener {
            updatepersondata()
        }

//      add menu
        setHasOptionsMenu(true)

        return view
    }

    private fun updatepersondata() {

        val firstName = updateFirstName.text.toString()
        val lastName = updateLastName.text.toString()
        val age = Integer.parseInt(updateAge.text.toString())

        if(inputCheck(firstName,lastName,updateAge.text)){
            val updateperson = Person(args.currentPerson.Id,firstName,lastName,age)
            mainViewModel.updateUser(updateperson)

            Toast.makeText(requireContext(),"Updated Successfully", Toast.LENGTH_SHORT).show()

            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
        }
    }

    private fun inputCheck(firstName: String, lastName: String, age: Editable):Boolean{
        return !(TextUtils.isEmpty(firstName) && TextUtils.isEmpty(lastName) && age.isEmpty())
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu, menu)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.deleteItem){
            deleteUser()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun deleteUser() {

        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Yes"){ _,_ ->

            mainViewModel.deleteUser(args.currentPerson)

            Toast.makeText(requireContext(),"Successfully Removed: ${args.currentPerson.FirstName}"
                , Toast.LENGTH_SHORT).show()

            findNavController().navigate(R.id.action_updateFragment_to_listFragment)

        }
        builder.setNegativeButton("No"){_,_ -> }
        builder.setTitle("Delete ${args.currentPerson.FirstName}?")
        builder.setMessage("Are You Sure , You Want To Delete ${args.currentPerson.FirstName}?")
        builder.create().show()
    }


}