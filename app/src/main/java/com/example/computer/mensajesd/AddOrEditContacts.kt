package com.example.computer.mensajesd

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.util.Log
import android.view.MenuItem
import android.widget.Toast
import android.os.Parcelable
import com.example.computer.mensajesd.Const.CONTACT_KEY
import kotlinx.android.synthetic.main.activity_add_or_edit_contacts.*
import kotlinx.android.synthetic.main.activity_ver_contactos.*

class AddOrEditContacts : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_or_edit_contacts)

        if(intent.getStringExtra(Const.WHAT).equals(Const.ADD)) {
            activityAddEditButton.text = "guardar"
        }else{
            activityAddEditButton.text = "actualizar"
            var con: Contact = intent.getParcelableExtra(CONTACT_KEY)
            activityAddEditContactEdtNumber.setText(con.número)
            activityAddEditContactEdtName.setText(con.nombre)

        }

        activityAddEditButton.setOnClickListener {
            if(intent.getStringExtra(Const.WHAT).equals(Const.ADD)){
                añadirContacto()
                finish()
            }else{
                updateContact(intent.getParcelableExtra(CONTACT_KEY))
                finish()
            }
        }
    }

    fun logMsg(msg: String) {
        Log.d("TAG", msg);
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item != null){
            when (item.itemId){
                android.R.id.home -> finish()
            }

        }
        return super.onOptionsItemSelected(item)
    }

    fun validateInput() : Boolean{
        if(activityAddEditContactEdtName?.text.toString().trim().equals("") || activityAddEditContactEdtNumber?.text.toString().trim().equals("")){
            Toast.makeText(this,"Por favor completar todos los campos", Toast.LENGTH_SHORT).show()
            return false
        }else
            return true
    }

    fun añadirContacto(){
        if(validateInput()){
            DatabaseHelper.getInstance(this).añadirContacto(activityAddEditContactEdtName?.text.toString(), activityAddEditContactEdtNumber?.text.toString())
        }
    }

    fun updateContact(contact : Contact?){
        if(validateInput()){
            DatabaseHelper.getInstance(this).updateContact(contact?.id, activityAddEditContactEdtName.text.toString(), activityAddEditContactEdtNumber.text.toString())
        }

    }

}
