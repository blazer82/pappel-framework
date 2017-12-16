package pappel.database

import pappel.JSONUtils

class Connection {

    private val sequelize: dynamic
    private val models: MutableMap<String, dynamic> = mutableMapOf()

    constructor(host: String, database: String, username: String, password: String) {
        val Sequelize: dynamic = pappel.require("sequelize")

        sequelize = js("new Sequelize(database, username, password, {\"host\": host, \"dialect\": \"mysql\"})")
    }

    /**
     * Convenience method for [connect] without callback
     */
    fun connect() {
        connect {}
    }

    /**
     * Connect to database and invoke [callback]
     */
    fun connect(callback: (error: Error?) -> Unit) {
        sequelize.authenticate().then {
            callback.invoke(null)
        }.catch {
            err -> callback.invoke(Error(err.message as String))
        }
    }

    fun registerModel(name: String, fields: Set<Model.FieldDefinition<Any>>) {
        val Sequelize: dynamic = pappel.require("sequelize")
        val options: MutableMap<String, Any> = mutableMapOf()

        for (field in fields) {
            val fieldOptions: MutableMap<String, Any> = mutableMapOf()
            fieldOptions.put("type", when (field.options.type) {
                Model.FieldDefinition.Type.INT -> Sequelize.INTEGER
                Model.FieldDefinition.Type.STRING -> Sequelize.STRING
            })
            fieldOptions.put("primaryKey", field.options.isPrimaryKey)

            options.put(field.options.name, fieldOptions)
        }

        models.put("name", sequelize.define(name, JSONUtils.toJSON(options)))
    }

}