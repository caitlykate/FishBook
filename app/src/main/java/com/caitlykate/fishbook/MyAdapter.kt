package com.caitlykate.fishbook

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class MyAdapter(var listArray: ArrayList<ListItem>, var context: Context): RecyclerView.Adapter<MyAdapter.ViewHolder>() {      //в <> передаем ViewHolder
    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {   //здесь находим все элементы в шаблоне ч/з findViewById, заполняем наш item_layout
        val tvTitle = view.findViewById<TextView>(R.id.tvTitle)
        val tvContent = view.findViewById<TextView>(R.id.tvContent)
        val imgFishId = view.findViewById<ImageView>(R.id.imgFish)

        fun bind(listItem: ListItem, context: Context){     //заполняем шаблон, context передаем, чтобы вывести Toast
            tvTitle.text = listItem.title
            tvContent.text = listItem.content.substring(0..50) + "..."
            imgFishId.setImageResource(listItem.imageId)
            itemView.setOnClickListener() {                        //по клику на элемент ResyclerView открываем подробности
                //Toast.makeText(context, "Вы нажали на: ${tvTitle.text}", Toast.LENGTH_SHORT).show()
                val i = Intent(context, ContentActivity::class.java).apply {
                    putExtra("title", listItem.title)
                    putExtra("content", listItem.content)
                    putExtra("image", listItem.imageId)
                }
                context.startActivity(i)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        //надуваем и отрисовываем наш item_layout
        //берем inflater с MainAct потому что там будет все рисоваться
        val inflater = LayoutInflater.from(context) //спец класс, который рисует
        return ViewHolder(inflater.inflate(R.layout.item_rw,parent,false))      //запускаем ViewHolder и передаем в него отрисованный шаблон для item
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {      //здесь запускаем ф-ю bind
        var listItem = listArray.get(position)              //получаем 1 элемент массива
        holder.bind(listItem,context)
    }

    override fun getItemCount(): Int {
        return listArray.size
    }


    fun updateAdapter(listArrayNew: ArrayList<ListItem>){
        listArray.clear()
        listArray.addAll(listArrayNew)
        notifyDataSetChanged()                  //сообщаем адаптеру, что данные изменились
    }
}