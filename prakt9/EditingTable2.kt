package com.example.prakt9

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.prakt9.Model.Bicycles
import com.example.prakt9.Model.Participant
import com.example.prakt9.Model.Valid
import java.util.*

class EditingTable2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editing_table2)
        val buttonEditing = findViewById<Button>(R.id.buttonEditing)
        val buttonInput = findViewById<Button>(R.id.buttonInput)
        val editTextSurname = findViewById<EditText>(R.id.editTextSurname)
        val editTextName = findViewById<EditText>(R.id.editTextName)
        val editTextMiddleName = findViewById<EditText>(R.id.editTextMiddleName)
        val editTextIdBicycleType = findViewById<EditText>(R.id.editTextIdBicycleType)
        val editTextExperience = findViewById<EditText>(R.id.editTextExperience)
        val bicyclesRecyclerView = findViewById<RecyclerView>(R.id.ListRecyclerView)
        val editTextIndex = findViewById<EditText>(R.id.editTextNumber)
        buttonEditing.isEnabled = false
        buttonInput.setOnClickListener {
            if (editTextIndex.text != null) {
                val indexStr = editTextIndex.text.toString()
                val indexInt = indexStr.toIntOrNull()
                var result: UUID? = null
                if (indexInt != null){
                    MainActivity.DB.OListB.getBicyclesList().forEachIndexed { index, id ->
                        if (index == (indexInt - 1)) {
                            result = id.Id
                        }
                    }
                }
                if (result != null) {
                    buttonEditing.isEnabled = true
                    buttonEditing.setOnClickListener {
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
                                                                    //MainActivity.DB.OListP.Add(Participant_)
                                                                    MainActivity.DB.OListP.Edit(
                                                                        Pair(Participant_, result))
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
                } else Toast.makeText(this, "", Toast.LENGTH_SHORT).show()

            } else Toast.makeText(
                this,
                getString(R.string.you_havent_entered_anything),
                Toast.LENGTH_SHORT
            ).show()
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