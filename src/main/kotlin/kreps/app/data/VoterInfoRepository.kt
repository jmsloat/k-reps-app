package kreps.app.data

import com.github.kittinunf.fuel.core.FuelManager
import com.github.kittinunf.fuel.core.ResponseDeserializable
import com.github.kittinunf.fuel.httpGet
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import kreps.app.core.Address
import kreps.app.core.Representative
import java.io.Reader
import java.lang.reflect.Type

class VoterInfoRepository {

    init {
        FuelManager.instance.basePath = "https://www.googleapis.com/civicinfo/v2"
        FuelManager.instance.baseParams = listOf("key" to CivicInfoKeyProvider.GetGoogleCivicInfoApiKey())
    }

    fun getRepresentatives(address: Address): Unit {
        val response = "/representatives".httpGet(listOf("address" to address.toString())).response { request, response, result ->
            println(response.toString())
        }
    }

    fun printRepresentatives(reps: List<Representative>): Unit {
        for (x in reps) {
            println(x);
        }
    }

    class RepDeserializer : ResponseDeserializable<Representative> {
        override fun deserialize(reader: Reader): Representative? {
            return super.deserialize(reader)
        }
    }


    data class GoogleCivicInfoResponse (
            val divisions: List<Int>,
            val offices: List<Office>
    ) {

        data class Office(val officeName: String, val divisionId: String, val levels: List<String>);

        class GoogleJsonDeseralizer : JsonDeserializer<GoogleCivicInfoResponse> {
            override fun deserialize(json: JsonElement?, typeOfT: Type?, context: JsonDeserializationContext?): GoogleCivicInfoResponse {
                if (json == null) throw NullPointerException("invalid json")
                val o = json.asJsonObject
                val offices = o.get("office");

                return GoogleCivicInfoResponse(listOf(), listOf());
            }

        }
    }

}

fun main(args: Array<String>) {
    val repo = VoterInfoRepository()
    val testAddress = Address("3815 Winding Way", "", "Cincinnati", "OH", 45229)

    val representatives = repo.getRepresentatives(testAddress)
    println(representatives)

}
