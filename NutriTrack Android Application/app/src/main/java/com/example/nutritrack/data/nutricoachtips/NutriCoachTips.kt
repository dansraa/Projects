package com.example.nutritrack.data.nutricoachtips

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.example.nutritrack.data.patient.Patient

@Entity (
    tableName = "nutricoachtips",
    foreignKeys = [
        ForeignKey(
            entity = Patient::class,
            parentColumns = ["userId"],
            childColumns = ["tipUserId"]
        )
    ]
)
data class NutriCoachTips (
    @PrimaryKey (autoGenerate = true)
    val tipId: Int = 0,
    @ColumnInfo(index = true)
    val tipUserId: Int, // Foreign key from patient table.
    val promptText: String
)