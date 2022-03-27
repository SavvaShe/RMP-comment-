package com.example.prakt9.Model

import java.util.*

class ListBicycles {
    private var BicyclesList:MutableList<Bicycles> = mutableListOf()
    fun  getBicyclesList(): MutableList<Bicycles> {return BicyclesList}

    fun Add(Bicycles:Bicycles,BicyclesList:MutableList<Bicycles>){
        BicyclesList.add(Bicycles)
    }
    fun Delete(id: UUID?, BicyclesList:MutableList<Bicycles>){
        var getobjdelete = BicyclesList.find { it.Id == id }
        BicyclesList.remove(getobjdelete)
    }
    fun Edit(EditbleBicycles: Pair<Bicycles?, UUID?>,BicyclesList:MutableList<Bicycles>):Bicycles?{
        if (EditbleBicycles.first != null && EditbleBicycles.second != null) {
            var getchekobg = BicyclesList.find { it.Id == EditbleBicycles.second }
            getchekobg?.BicyclesType = (EditbleBicycles.first)!!.BicyclesType
        }
        return null
    }
}