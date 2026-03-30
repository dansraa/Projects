package com.example.nutritrack.data.foodIntake

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.example.nutritrack.data.patient.Patient

// Entity table, with foreign key included from Patient table.
@Entity (
    tableName = "foodIntake",
    foreignKeys = [
        ForeignKey(
            entity = Patient::class,
            parentColumns = ["userId"],
            childColumns = ["quizUserId"]
        )
    ]
)
data class FoodIntake(

    @PrimaryKey(autoGenerate = true)
    val quizId: Int = 0,
    @ColumnInfo(index = true)
    val quizUserId: Int, // Foreign key from patient table.
    val eatFruit: Boolean,
    val eatMeat: Boolean,
    val eatFish: Boolean,
    val eatVege: Boolean,
    val eatSeafood: Boolean,
    val eatEggs: Boolean,
    val eatGrains: Boolean,
    val eatPoultry: Boolean,
    val eatNutSeed: Boolean,
    val selectedPersona: String,
    val timeOne: String,
    val timeTwo: String,
    val timeThree: String

)
