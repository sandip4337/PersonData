package com.sandip.persondata

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.sandip.persondata.ViewModel.MainViewModel
import com.sandip.persondata.data.Person
import kotlinx.android.synthetic.main.person_data_row.view.*

class ListAdapter(private val context: Context, private var mainViewModel: MainViewModel):RecyclerView.Adapter<ListAdapter.MyViewHolder>(){

    private var personlist = emptyList<Person>()



    fun setdata(items:List<Person>){
        this.personlist = items
        notifyDataSetChanged()
    }

    class MyViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.person_data_row,parent,false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = personlist[position]
        holder.itemView.Id.text = item.Id.toString()
        holder.itemView.Fname.text = item.FirstName
        holder.itemView.Lname.text = item.LastName
        holder.itemView.Age.text = item.Age.toString()

        holder.itemView.update.setOnClickListener {
            val action = ListFragmentDirections.actionListFragmentToUpdateFragment(item)
            holder.itemView.findNavController().navigate(action)
        }

        holder.itemView.deletebtn.setOnClickListener {
            val builder = AlertDialog.Builder(context)
            builder.setPositiveButton("Yes"){ _,_ ->

                mainViewModel.deleteUser(item)

                Toast.makeText(context,"Successfully Removed: ${item.FirstName}"
                        , Toast.LENGTH_SHORT).show()
            }
            builder.setNegativeButton("No"){_,_ -> }
            builder.setTitle("Delete ${item.FirstName}?")
            builder.setMessage("Are You Sure , You Want To Delete ${item.FirstName}?")
            builder.create().show()
        }
    }

    override fun getItemCount(): Int {
        return personlist.size
    }

}