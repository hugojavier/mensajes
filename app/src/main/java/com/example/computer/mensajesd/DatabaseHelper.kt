package com.example.computer.mensajesd

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.computer.mensajesd.Const.DATABASE_NAME
import com.example.computer.mensajesd.Const.DATABASE_VERSION


class DatabaseHelper(context: Context?, name: String?, factory: SQLiteDatabase.CursorFactory?, version: Int) : SQLiteOpenHelper(context, name, factory, version) {

    val CONTACTS_TABLE : String = "contacts_table"
    val ID : String = "id"
    val NOMBRE : String = "nombre"
    val NUMERO : String = "número"

    companion object{
        private var instance : DatabaseHelper? = null

        fun getInstance(ctx:Context):DatabaseHelper{
            if(instance == null){
                instance = DatabaseHelper(ctx, DATABASE_NAME, null, DATABASE_VERSION)

            }
            return instance!!
        }
    }

    val createSql : String = "create table $CONTACTS_TABLE($ID INTEGER PRIMARY KEY AUTOINCREMENT, $NOMBRE TEXT, $NUMERO TEXT)"

    override fun onCreate(p0: SQLiteDatabase?) {
        p0?.execSQL(createSql)
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {

    }

    fun añadirContacto (nombre :String, número :String) : Long{
        var values :ContentValues = ContentValues()
        values.put(NOMBRE, nombre)
        values.put(NUMERO, número)
        return this.writableDatabase.insert(CONTACTS_TABLE, null, values)
    }

    fun updateContact (id :Int?, nombre :String?, número:String?) : Int{
        var values :ContentValues = ContentValues()
        values.put(NOMBRE, nombre)
        values.put(NUMERO, número)
        return this.writableDatabase.update(CONTACTS_TABLE,values, "$ID=?", arrayOf("$id"))
    }

    fun borrarContacto (id:Int?){
        this.writableDatabase.delete(CONTACTS_TABLE, "$ID=?", arrayOf("$id"))
    }

    fun getAllContactos() : ArrayList<Contact>{
        var list :ArrayList<Contact> = ArrayList()
        var cursor :Cursor = this.writableDatabase.query(CONTACTS_TABLE, arrayOf(ID, NOMBRE, NUMERO), null, null, null , null, null)

        if(cursor.moveToFirst()){
            do{
                var con : Contact = Contact()
                con.id= cursor.getInt(0)
                con.nombre = cursor.getString(1)
                con.número = cursor.getString(2)
                list.add(con)
            }while (cursor.moveToNext())
        }
        return list 
    }


}
