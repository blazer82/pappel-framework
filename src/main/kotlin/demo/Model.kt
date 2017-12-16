package demo

import pappel.database.Connection
import pappel.database.Model

class Model(db: Connection) {

    val user: Model<User>

    init {
        user = db.defineModel(
                "User",
                setOf(
                        Model.FieldDefinition<Int>(Model.FieldDefinition.Options(
                                name = "id",
                                type = Model.FieldDefinition.Type.INT,
                                isPrimaryKey = true,
                                autoIncrement = true
                        )),
                        Model.FieldDefinition<String>(Model.FieldDefinition.Options(
                                name = "username",
                                type = Model.FieldDefinition.Type.STRING
                        ))
                ) as Set<Model.FieldDefinition<Any>>,
                User())
    }

    data class User(
            var id: Int? = null,
            var username: String? = null
    )

}