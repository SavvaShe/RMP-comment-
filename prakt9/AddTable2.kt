package com.example.prakt9

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.prakt9.Model.Bicycles
import com.example.prakt9.Model.Participant
import com.example.prakt9.Model.Valid
import java.util.*
/*class BicyclesAdapterID(private val bicycles:List<Bicycles>): RecyclerView.Adapter<BicyclesAdapterID.BicyclesViewHolder>(){
    class BicyclesViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        var id = itemView.findViewById<EditText>(R.id.id)
        var bicycleType = itemView.findViewById<TextView>(R.id.bicycleType)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BicyclesViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.bicycles_id,parent,false)
        return BicyclesViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: BicyclesViewHolder, position: Int) {
        holder.id.text=bicycles[position].Id.toString()
        holder.bicycleType.text=bicycles[position].BicyclesType
    }

    override fun getItemCount() = bicycles.size
}*/
class AddTable2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_table2)
        val Add = findViewById<Button>(R.id.buttonAdd)
        val editTextSurname = findViewById<EditText>(R.id.editTextSurname)
        val editTextName = findViewById<EditText>(R.id.editTextName)
        val editTextMiddleName = findViewById<EditText>(R.id.editTextMiddleName)
        val editTextIdBicycleType = findViewById<EditText>(R.id.editTextIdBicycleType)
        val editTextExperience = findViewById<EditText>(R.id.editTextExperience)
        val bicyclesRecyclerView = findViewById<RecyclerView>(R.id.ListRecyclerView)
        bicyclesRecyclerView.adapter =
            BicyclesAdapter(MainActivity.DB.OListB.getBicyclesList())
        bicyclesRecyclerView.layoutManager = LinearLayoutManager(this)
        Add.setOnClickListener {
            if (editTextSurname.text!=null){
                if (Valid.checkFIO(editTextSurname.text.toString())){
                    if (editTextName.text!=null){
                        if (Valid.checkFIO(editTextName.text.toString())){
                            if (editTextMiddleName.text!=null){
                                if(Valid.checkFIO(editTextMiddleName.text.toString())){
                                    if (editTextIdBicycleType.text!=null){
                                        if (MainActivity.DB.OListB.getBicyclesList().find { it.Id == (UUID.fromString(editTextIdBicycleType.text.toString())) } != null){
                                            if (editTextExperience!=null){
                                                if (Valid.checkExperience(editTextExperience.text.toString())){
                                                    val experienceStr = editTextExperience.text.toString()
                                                    val experienceInt = experienceStr.toIntOrNull()
                                                    if(experienceInt!=null) {
                                                        val Participant_ = Participant(
                                                            editTextSurname.text.toString(),
                                                            editTextName.text.toString(),
                                                            editTextMiddleName.text.toString(),
                                                            editTextIdBicycleType.text.toString(),
                                                            experienceInt)
                                                        MainActivity.DB.OListP.Add(Participant_)
                                                        editTextSurname.text = null
                                                        editTextName.text=null
                                                        editTextMiddleName.text=null
                                                        editTextIdBicycleType.text = null
                                                        editTextExperience.text = null
                                                        Toast.makeText(this,getString(R.string.entry_added_successfully),
                                                            Toast.LENGTH_SHORT).show()
                                                    }
                                                }
                                            }else Toast.makeText(this,getString(R.string.you_havent_entered_anything),Toast.LENGTH_SHORT).show()

                                        }else Toast.makeText(this,getString(R.string.this_type_is_not_in_1_table),Toast.LENGTH_SHORT).show()
                                    }else Toast.makeText(this,getString(R.string.you_havent_entered_anything),Toast.LENGTH_SHORT).show()
                                }
                            }else Toast.makeText(this,getString(R.string.you_havent_entered_anything),Toast.LENGTH_SHORT).show()
                        }
                    }else Toast.makeText(this,getString(R.string.you_havent_entered_anything),Toast.LENGTH_SHORT).show()
                }
            }else Toast.makeText(this,getString(R.string.you_havent_entered_anything),Toast.LENGTH_SHORT).show()
        }
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId==android.R.id.home){
            if (MainActivity.Table1IsTrue){
                var intent = Intent(this,MenuTable1::class.java)
                startActivity(intent)
                finish()
            }else {
                var intent = Intent(this,MenuTable2::class.java)
                startActivity(intent)
                finish()
            }
        }
        return true
    }
}