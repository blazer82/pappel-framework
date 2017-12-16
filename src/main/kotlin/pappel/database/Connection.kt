package pappel.database

import pappel.JSONUtils

class Connection {

    private val sequelize: dynamic

    constructor(host: String, database: String, username: String, password: String) {
        val Sequelize: dynamic = pappel.require("sequelize")

        sequelize = js("new Sequelize(database, username, password, {\"host\": host, \"dialect\": \"mysql\"})")
    }

    /**
     * Connect to database and invoke [callback]
     */
    fun connect(callback: ((error: Error?) -> Unit)? = null) {
        sequelize.authenticate().then {
            callback?.invoke(null)
        }.catch {
            err -> callback?.invoke(Error(err.message as String))
        }
    }

    fun <T>defineModel(name: String, fields: Set<Model.FieldDefinition<Any>>, dataClass: T): Model<T> {
        val Sequelize: dynamic = pappel.require("sequelize")
        val options: MutableMap<String, Any> = mutableMapOf()

        for (field in fields) {
            val fieldOptions: MutableMap<String, Any> = mutableMapOf()
            fieldOptions.put("type", when (field.options.type) {
                Model.FieldDefinition.Type.INT -> Sequelize.INTEGER
                Model.FieldDefinition.Type.STRING -> Sequelize.STRING
            })
            fieldOptions.put("primaryKey", field.options.isPrimaryKey)
            fieldOptions.put("autoIncrement", field.options.autoIncrement)

            options.put(field.options.name, fieldOptions)
        }

        val sequelizeModel = sequelize.define(name, JSONUtils.toJSON(options))
        val model = object : Model<T>(dataClass) {
            override val sequelizeModel = sequelizeModel
        }

        return model
    }

}