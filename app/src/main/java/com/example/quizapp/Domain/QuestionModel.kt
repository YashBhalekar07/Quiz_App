package com.example.quizapp.Domain

import android.os.Parcel
import android.os.Parcelable

data class QuestionModel(val id: Int,
    val question:String?,
    val answer_1:String?,
    val answer_2:String?,
    val answer_3:String?,
    val answer_4:String?,
    val correctAnswer:String?,
    val score:Int,
    val picPath:String?,
    var clickedAnswer:String?
    ):Parcelable{
        constructor(parcel: Parcel):this(
            parcel.readInt(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readInt(),
            parcel.readString(),
            parcel.readString()
        ){

        }


    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(question)
        parcel.writeString(answer_1)
        parcel.writeString(answer_2)
        parcel.writeString(answer_3)
        parcel.writeString(answer_4)
        parcel.writeString(correctAnswer)
        parcel.writeInt(score)
        parcel.writeString(picPath)
        parcel.writeString(clickedAnswer)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR:Parcelable.Creator<QuestionModel>{
        override fun createFromParcel(parcel: Parcel): QuestionModel {
            return QuestionModel(parcel)
        }

        override fun newArray(size: Int): Array<QuestionModel?> {
            return arrayOfNulls(size)
        }
    }


}
