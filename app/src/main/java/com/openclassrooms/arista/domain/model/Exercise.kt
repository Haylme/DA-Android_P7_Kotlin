package com.openclassrooms.arista.domain.model

import com.openclassrooms.arista.data.entity.ExerciseDto
import java.time.LocalDateTime
import java.time.ZoneOffset

data class Exercise(
    val id: Long? = null,
    var startTime: LocalDateTime,
    var duration: Int,
    var category: ExerciseCategory,
    var intensity: Int
) {

    companion object {


        fun fromDto(dto: ExerciseDto): Exercise {

            val category = ExerciseCategory.values().firstOrNull {
                it.name.equals(dto.category, ignoreCase = true)
            } ?: throw IllegalArgumentException("Invalid category: ${dto.category}")

            return Exercise(
                id = dto.id,
                startTime = LocalDateTime.ofEpochSecond(dto.startTime / 1000, 0, ZoneOffset.UTC),
                duration = dto.duration,
                category = category,
                intensity = dto.intensity
            )

        }

    }

    fun toDto(): ExerciseDto {

        return ExerciseDto(
            id = this.id ?: 0,
            startTime = this.startTime.toEpochSecond(ZoneOffset.UTC) * 1000,
            duration = this.duration,
            category = this.category.name.uppercase(),
            intensity = this.intensity
        )

    }


}