package com.sandip.persondata

import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.sandip.persondata.ViewModel.MainViewModel
import kotlinx.android.synthetic.main.fragment_list.view.*

class ListFragment : Fragment() {

    lateinit var mainViewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_list, container, false)

        setHasOptionsMenu(true)

        view.floatingActionButton.setOnClickListener{
            findNavController().navigate(R.id.action_listFragment_to_addFragment)
        }

        val adapter = ListAdapter()
        val recyclerView = view.Recylerview
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        mainViewModel.personInfo.observe(viewLifecycleOwner, Observer {

            adapter.setdata(it)

        })

        return view
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu, menu)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.deleteItem){
            deleteAllUser()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun deleteAllUser() {

        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Yes"){ _,_ ->

            mainViewModel.deleteAllUser()

            Toast.makeText(requireContext(),"Successfully Removed everything"
                , Toast.LENGTH_SHORT).show()

        }
        builder.setNegativeButton("No"){_,_ -> }
        builder.setTitle("Delete Everything?")
        builder.setMessage("Are You Sure , You Want To Delete Everything?")
        builder.create().show()
    }

}