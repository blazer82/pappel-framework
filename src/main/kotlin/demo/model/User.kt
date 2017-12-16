package demo.model

import pappel.database.Model

class User : Model() {

    var id: Int? = null
    var username: String? = null

    companion object {
        val fields = setOf(
                FieldDefinition<Int>(FieldDefinition.Options(
                        name = "id",
                        type = FieldDefinition.Type.INT,
                        isPrimaryKey = true
                )),
                FieldDefinition<String>(FieldDefinition.Options(
                        name = "username",
                        type = FieldDefinition.Type.STRING
                ))
        ) as Set<Model.FieldDefinition<Any>>
    }

}