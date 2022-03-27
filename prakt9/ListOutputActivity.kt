package com.example.prakt9

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.prakt9.Model.Bicycles
import com.example.prakt9.Model.Participant
class BicyclesAdapter(private val bicycles:List<Bicycles>):RecyclerView.Adapter<BicyclesAdapter.BicyclesViewHolder>(){//адаптер Велосипедов для работы с RecyclerView в коде
    class BicyclesViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {// Он служит своеобразным контейнером для всех компонентов, которые входят в элемент списка. При этом RecyclerView создаёт ровно столько контейнеров, сколько нужно для отображения на экране. Новый класс добавим в состав нашего созданного ранее класса. В скобках указываем название для элемента списка на основе View и этот же параметр указываем и для RecycleView.ViewHolder.
        var id = itemView.findViewById<TextView>(R.id.id)//создание айди из ресурсов
        var Photo = itemView.findViewById<ImageView>(R.id.imageViewPhoto)//создание фото  из ресурсов
        var bicycleType = itemView.findViewById<TextView>(R.id.bicycleType)//создание типа  из ресурсов
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BicyclesViewHolder {//переопределение функции
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.bicycles,parent,false)//LayoutInflater - это класс, используемый для создания представлений из файла ресурс макета
        return BicyclesViewHolder(itemView)//возврат значения
    }

    override fun onBindViewHolder(holder: BicyclesViewHolder, position: Int) {
        holder.id.text=bicycles[position].Id.toString()
        holder.bicycleType.text=bicycles[position].BicyclesType
        if (bicycles[position].Photo!=null){
            holder.Photo.setImageBitmap(bicycles[position].Photo)
        }
    }

    override fun getItemCount() = bicycles.size
}
class ParticipantAdapter(private val partipicant:List<Participant>):RecyclerView.Adapter<ParticipantAdapter.ParticipantViewHolder>(){
    class ParticipantViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {
        var surname = itemView.findViewById<TextView>(R.id.surname)
        var name = itemView.findViewById<TextView>(R.id.name)
        var middleName = itemView.findViewById<TextView>(R.id.middleName)
        var bicycleType = itemView.findViewById<TextView>(R.id.bicycleType)
        var experience = itemView.findViewById<TextView>(R.id.experience)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ParticipantViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.participant,parent,false)
        return ParticipantViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ParticipantViewHolder, position: Int) {
        holder.surname.text=partipicant[position].F
        holder.name.text=partipicant[position].I
        holder.middleName.text=partipicant[position].O
        holder.bicycleType.text=partipicant[position].IdBicycleType
        holder.experience.text=partipicant[position].Experience.toString()
    }
    override fun getItemCount() = partipicant.size
}
class ListOutputActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_output)
        if (!MainActivity.Table1IsTrue) {
            val participantRecyclerView = findViewById<RecyclerView>(R.id.ListRecyclerView)
            participantRecyclerView.adapter =
                ParticipantAdapter(MainActivity.DB.OListP.getParticipantList())
            participantRecyclerView.layoutManager = LinearLayoutManager(this)
        }else {
            val bicyclesRecyclerView = findViewById<RecyclerView>(R.id.ListRecyclerView)
            bicyclesRecyclerView.adapter =
                BicyclesAdapter(MainActivity.DB.OListB.getBicyclesList())
            bicyclesRecyclerView.layoutManager = LinearLayoutManager(this)
        }

    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId==android.R.id.home){
            if (MainActivity.Table1IsTrue){
                var intent = Intent(this,MenuTable1::class.java)
                startActivity(intent)//старт активити в менюшке 1 таблицы
                finish()//конец
            }else {
                var intent = Intent(this,MenuTable2::class.java)
                startActivity(intent)//старт активити в менюшке 2 таблицы
                finish()//конец
            }
        }
        return true
    }
}