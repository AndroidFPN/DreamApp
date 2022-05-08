package com.androidfpn.dreamapp.data.locale.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "sound",
    foreignKeys = [
        ForeignKey(
            entity = SoundCategories::class,
            parentColumns = ["id"],
            childColumns = ["categoryFK"]
        )]
)

class Sound(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "categoryFK") val categoryFK: Int,
    @ColumnInfo(name = "image") val image: String,
    @ColumnInfo(name = "source") val source: String,
    @ColumnInfo(name = "fileName") val fileName: String,
    @ColumnInfo(name = "LastMinutePlayed") val LastMinutePlayed: Long,
    @ColumnInfo(name = "isBookMarked") val isBookMarked: Boolean,
)