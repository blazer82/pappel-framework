package pappel

import kotlin.js.Json
import kotlin.js.json

class JSONUtils {

    companion object {
        fun toJSON(data: Map<String, Any>): Json {
            val arrayOfPairs: Array<Pair<String, Any>> = data.map {
                entry ->
                if (entry.value is Map<*,*>) {
                    Pair(entry.key, toJSON(entry.value as Map<String, Any>))
                }
                else {
                    entry.toPair()
                }
            }.toTypedArray()

            return json(*arrayOfPairs)
        }

        fun toJSON(data: Array<Any>): Array<Any> {
            val array: Array<Any> = data.map {
                entry ->
                if (entry is Map<*,*>) {
                    toJSON(entry as Map<String, Any>)
                }
                else {
                    entry
                }
            }.toTypedArray()

            return array
        }
    }

}