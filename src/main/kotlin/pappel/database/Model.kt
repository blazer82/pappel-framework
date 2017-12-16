package pappel.database

abstract class Model {

    class FieldDefinition<T>(val options: Options<T>) {

        var value: T? = null

        // TODO: Add references
        // TODO: Add validation
        // TODO: Add custom getter and setter
        // TODO: Implement full sequelizer API

        class Options<T>(
                val name: String,
                val type: Type,
                val fieldName: String? = null,
                val allowNull: Boolean = true,
                val defaultValue: T? = null,
                val isPrimaryKey: Boolean = false,
                val isUnique: Boolean = false,
                val autoIncrement: Boolean = false,
                val comment: String? = null
        )

        enum class Type {
            INT, STRING
        }

    }

}