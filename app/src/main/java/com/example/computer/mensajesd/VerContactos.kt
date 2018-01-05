package com.example.computer.mensajesd

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcelable
import android.os.PersistableBundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.computer.mensajesd.Contact
import com.example.computer.mensajesd.Const.UPDATE
import com.example.computer.mensajesd.Const.ADD
import com.example.computer.mensajesd.Const.CONTACT_KEY
import com.example.computer.mensajesd.Const.WHAT
import kotlinx.android.synthetic.main.activity_ver_contactos.*

class VerContactos : AppCompatActivity() {

    var adapter :ContactAdapter?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ver_contactos)

        var bundle = intent.extras
        SaludoUsuario.text = bundle.getString("cuenta")

        adapter = ContactAdapter(this@VerContactos)
        activityVerContactosList.adapter = adapter

        fab1.setOnClickListener{
            var intent : Intent = Intent(this@VerContactos, AddOrEditContacts::class.java)
            intent.putExtra(WHAT, ADD)
            startActivity(intent)
        }
    }

    class ContactAdapter() :BaseAdapter(){
        var list : ArrayList<Contact>? = null
        var context : Context? = null

        constructor(context : Context?): this(){
            this.list = DatabaseHelper.getInstance(context!!).getAllContactos()
            this.context = context

            Log.i("qwerty", "gh")

        }

        fun updateList(){
            this.list = DatabaseHelper.getInstance(context!!).getAllContactos()
            notifyDataSetChanged()
            Log.i("asdfgh", "asd")
        }



        override fun getView(position: Int, view: View?, p2: ViewGroup?): View {
            var convertView : View? = view

            Log.i("zxcbb","hugo")
            if(convertView == null){
                convertView = View.inflate(context,R.layout.item_contacts,null)


            }

            var txtNombre : TextView = convertView?.findViewById<TextView>(R.id.itemContactsTxtName) as TextView
            var txtNumero : TextView = convertView.findViewById<TextView>(R.id.itemContactsTxtNumero) as TextView
            var imgEdit : ImageView = convertView.findViewById<ImageView>(R.id.itemContactsImgEdit) as ImageView
            var imgDelete : ImageView = convertView.findViewById<ImageView>(R.id.itemContactsImgDelete) as ImageView
            txtNombre.text = list?.get(position)?.nombre
            txtNumero.text = list?.get(position)?.numero

            imgEdit.setOnClickListener {
                var intent = Intent(context, AddOrEditContacts::class.java)
                intent.putExtra(WHAT, UPDATE)
                intent.putExtra(CONTACT_KEY, (list?.get(position)) as Parcelable)


                context?.startActivity(intent)
            }

            imgDelete.setOnClickListener {
                DatabaseHelper.getInstance(context!!).borrarContacto(list?.get(position)?.id)
                list?.removeAt(position)
                notifyDataSetChanged()
            }

            return convertView
        }

        override fun getItem(p0: Int): Contact? {
            return list?.get(p0)
        }

        override fun getItemId(p0: Int): Long {
            return p0.toLong()
        }

        override fun getCount(): Int {
            return list!!.size
        }

    }
}
