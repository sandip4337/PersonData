package com.sandip.persondata

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.sandip.persondata.data.Person
import kotlinx.android.synthetic.main.person_data_row.view.*

class ListAdapter :RecyclerView.Adapter<ListAdapter.MyViewHolder>(){

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



//        holder.itemView.deletebtn.setOnClickListener {
//            val action = ListFragmentDirections.actionListFragmentToAddFragment(item)
//            holder.itemView.
//        }
    }

    override fun getItemCount(): Int {
        return personlist.size
    }

}