package com.example.sqlite

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class StudentListAdapter internal  constructor(
    context: Context
): RecyclerView.Adapter<StudentListAdapter.StudentViewHolder>(){
    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var students= emptyList<Student>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
        val itemView=inflater.inflate(R.layout.student_item,parent,false)
        return StudentViewHolder(itemView)
    }

    override fun getItemCount()=students.size

    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
        val current=students[position]
        holder.edNombre.text=current.nombre
        holder.edControl.text=current.noControl
        holder.edCarrera.text=current.carrera
    }

    inner class StudentViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val edControl: TextView =itemView.findViewById(R.id.item_control)
        val edNombre: TextView =itemView.findViewById(R.id.item_nombres)
        val edCarrera: TextView =itemView.findViewById(R.id.item_carrera)
    }

    fun setStudentList(studentList: List<Student>){
        this.students=studentList
        notifyDataSetChanged()
    }
}