package com.example.computer.mensajesd

import android.os.Parcel
import android.os.Parcelable


class Contact : Parcelable {


    var id: Int = 0
    var nombre: String = ""
    var número: String = ""

    constructor(){

    }

    constructor(id: Int, nombre: String, número: String){

        this.id = id
        this.número = número
        this.nombre = nombre
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeInt(this.id)
        dest.writeString(this.nombre)
        dest.writeString(this.número)
    }

    override fun toString(): String {
        return "Contact(id=$id, nombre=$nombre, número=$número)"
    }

    protected constructor(`in`: Parcel) {
        this.id = `in`.readInt()
        this.número = `in`.readString()
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