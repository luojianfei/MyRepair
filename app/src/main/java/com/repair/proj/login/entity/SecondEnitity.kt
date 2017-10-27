package com.repair.proj.login.entity

import android.databinding.BaseObservable

/**
 * 说明：
 * Created by code_nil on 2017/10/27.
 */

class SecondEnitity : BaseObservable {

    constructor()

    constructor(address: String, name: String, idCard: String, urgencyName: String, urgencyPhoneNumber: String) : this() {
        this.address = address
        this.name = name
        this.idCard = idCard
        this.urgencyName = urgencyName
        this.urgencyPhoneNumber = urgencyPhoneNumber
    }


    var address: String = ""
        set(value) {
            field = value
            notifyChange()
        }
    var name: String = ""
        set(value) {
            field = value
            notifyChange()
        }
    var idCard: String = ""
        set(value) {
            field = value
            notifyChange()
        }
    var urgencyName: String = ""
        set(value) {
            field = value
            notifyChange()
        }
    var urgencyPhoneNumber: String = ""
        set(value) {
            field = value
            notifyChange()
        }


}
