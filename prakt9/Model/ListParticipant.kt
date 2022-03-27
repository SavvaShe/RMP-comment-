package com.example.prakt9.Model

import java.util.*

class ListParticipant {
    private var ParticipantList:MutableList<Participant> = mutableListOf()
    fun getParticipantList(): MutableList<Participant> {return ParticipantList}
    fun Add(Participant: Participant) {
        ParticipantList.add(Participant)
    }
    fun Delete(id: UUID?) {
        var getobjdelete = ParticipantList.find { it.Id == id }
        ParticipantList.remove(getobjdelete)
    }
    fun Edit(Editbleparticipant: Pair<Participant?, UUID?>): Participant? {
        if (Editbleparticipant.first != null && Editbleparticipant.second != null) {
            var getchekobg = ParticipantList.find { it.Id == Editbleparticipant.second }
            getchekobg?.F = (Editbleparticipant.first)!!.F
            getchekobg?.I = (Editbleparticipant.first)!!.I
            getchekobg?.O = (Editbleparticipant.first)!!.O
            getchekobg?.IdBicycleType = (Editbleparticipant.first)!!.IdBicycleType
            getchekobg?.Experience = (Editbleparticipant.first)!!.Experience
        }
        return null
    }
}