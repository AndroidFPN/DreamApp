package com.androidfpn.dreamapp.data.locale.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "artist")
class Artist (@PrimaryKey(autoGenerate = true) val id: Int,
              @ColumnInfo(name = "name") val name: String,
              @ColumnInfo(name = "image") val image: String,
              @ColumnInfo(name = "about") val about: String
)