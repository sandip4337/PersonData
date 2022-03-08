package com.sandip.persondata

import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import android.widget.SearchView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.sandip.persondata.ViewModel.MainViewModel
import kotlinx.android.synthetic.main.fragment_list.view.*

class ListFragment : Fragment(){

    lateinit var mainViewModel: MainViewModel
    lateinit var adapter: ListAdapter

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

        mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        adapter = ListAdapter(requireContext(),mainViewModel)
        val recyclerView = view.Recylerview
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())



        mainViewModel.personInfo.observe(viewLifecycleOwner, Observer {

            adapter.setdata(it)

        })

        return view
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater){
        inflater.inflate(R.menu.main_menu, menu)

//        val search = menu.findItem(R.id.menu_search)
//        val searchView = search?.actionView as? SearchView
//        searchView?.isSubmitButtonEnabled = true
//        searchView?.setOnQueryTextListener(this)

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

//    override fun onQueryTextSubmit(query: String?): Boolean {
//        if (query != null){
//            searchDatabase(query)
//        }
//        return true
//    }
//
//    override fun onQueryTextChange(query: String?): Boolean {
//        if (query != null){
//            searchDatabase(query)
//        }
//        return true
//    }
//
//    private fun searchDatabase(query: String){
//        val searchQuery = "%$query%"
//
//        mainViewModel.searchDatabase(searchQuery).observe(this,{list ->
//            list.let {
//                adapter.setdata(it)
//            }
//        })
//    }

}