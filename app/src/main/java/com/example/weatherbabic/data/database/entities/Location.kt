package com.example.weatherbabic.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
data class Location(
    @PrimaryKey @ColumnInfo(name = COLUMN_NAME) val name: String
) : Serializable {
    companion object {
        const val COLUMN_NAME = "name"
    }
}