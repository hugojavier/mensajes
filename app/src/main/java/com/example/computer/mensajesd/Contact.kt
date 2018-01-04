package com.example.computer.mensajesd

import android.os.Parcel
import android.os.Parcelable


class Contact : Parcelable {


    var id: Int = 0
    var nombre: String = ""
    var numero: String = ""

    constructor(){

    }

    constructor(id: Int, nombre: String, numero: String){

        this.id = id
        this.numero = numero
        this.nombre = nombre
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeInt(this.id)
        dest.writeString(this.nombre)
        dest.writeString(this.numero)
    }

    override fun toString(): String {
        return "Contact(id=$id, nombre=$nombre, número=$numero)"
    }

    protected constructor(`in`: Parcel) {
        this.id = `in`.readInt()
        this.numero = `in`.readString()
        this.nombre = `in`.readString()
    }

    companion object {
        @JvmField val CREATOR: Parcelable.Creator<Contact> = object : Parcelable.Creator<Contact> {
            override fun createFromParcel(source: Parcel): Contact {
                return Contact(source)
            }

            override fun newArray(size: Int): Array<Contact?> {
                return arrayOfNulls(size)
            }
        }
    }
}