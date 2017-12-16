package pappel.database

import pappel.JSONUtils

abstract class Model<T>(private val dataClass: T) {

    abstract val sequelizeModel: dynamic

    fun sync(force: Boolean = false, callback: ((error: Error?) -> Unit)? = null) {
        sequelizeModel.sync(JSONUtils.toJSON(mapOf("force" to force))).then {
            callback?.invoke(null)
        }.catch {
            err -> callback?.invoke(Error(err.message as String))
        }
    }

    fun new(): T {
        return ::dataClass.invoke()
    }

    fun save(data: T) {
        val sequelizeData = sequelizeModel.build(data)
        sequelizeData.save()
    }

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