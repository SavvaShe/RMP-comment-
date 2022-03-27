package com.example.prakt9

import android.content.ActivityNotFoundException
import android.content.Intent
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import com.example.prakt9.Model.Bicycles
import com.example.prakt9.Model.Valid
import java.util.*

class AddTable1 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_table1)
        val Add = findViewById<Button>(R.id.buttonAdd)
        val buttonAddPhoto = findViewById<Button>(R.id.buttonAddPhoto)
        val editTextBicycleType = findViewById<EditText>(R.id.editTextBicycleType)
        buttonAddPhoto.setOnClickListener {
            val intent = Intent()
           // val intent = getIntent()

            //intent.component = null
            //intent.setPackage("com.google.android.GoogleCamera")
           // intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            //startActivityForResult(intent, REQUEST_IMAGE_CAPTURE)
            intent.action = MediaStore.ACTION_IMAGE_CAPTURE
            //if (intent.resolveActivity(packageManager)!=null) {
            try {
                startActivityForResult(intent, REQUEST_IMAGE_CAPTURE)
            }catch(e: ActivityNotFoundException){
                Toast.makeText(this,e.message,Toast.LENGTH_SHORT).show()
            }
            //}
        }
        Add.setOnClickListener {
            val typeStr = editTextBicycleType.text.toString()
            if (editTextBicycleType.text!=null){
                if(Valid.checkType(typeStr)){
                    if (Photo!=null) {
                        val Bicycles_ = Bicycles(typeStr, Photo)
                        MainActivity.DB.OListB.Add(
                            Bicycles_,
                            MainActivity.DB.OListB.getBicyclesList()
                        )
                        editTextBicycleType.text = null
                        Toast.makeText(
                            this,
                            getString(R.string.entry_added_successfully),
                            Toast.LENGTH_SHORT
                        ).show()
                    }else Toast.makeText(this,getString(R.string.you_have_not_added_a_photo),Toast.LENGTH_SHORT).show()
                }else Toast.makeText(this,getString(R.string.you_entered_the_wrong_type),Toast.LENGTH_SHORT).show()
            }else Toast.makeText(this,getString(R.string.you_havent_entered_anything),Toast.LENGTH_SHORT).show()
        }
    }
    companion object{
        const val REQUEST_IMAGE_CAPTURE = 1
        var Photo :Bitmap? = null
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if ((requestCode== REQUEST_IMAGE_CAPTURE)&&(resultCode== RESULT_OK)){
            val bitmap= data?.extras?.get("data") as Bitmap
            Photo = bitmap
            val imageViewPhoto = findViewById<ImageView>(R.id.imageViewPhoto)
            imageViewPhoto.setImageBitmap(bitmap)

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