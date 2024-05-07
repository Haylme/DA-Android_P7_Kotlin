package com.openclassrooms.arista.domain.model

import com.openclassrooms.arista.data.entity.UserDto

data class User(val id: Long? = null,var name: String, var email: String , val password: String){

    companion object {


        fun fromDto(dto: UserDto): User {

            return User(

                id = dto.id,
                name =dto.nom,
                email = dto.email,
                password = dto.password
            )

        }

    }

    fun toDto(): UserDto {

        return UserDto(

            id = this.id ?:0,
            nom = this.name,
            email = this.email,
            password = this.password


        )


    }

}