package kreps.app.data

import com.github.kittinunf.fuel.core.ResponseDeserializable
import com.google.gson.*
import kreps.app.core.Address
import kreps.app.core.Party
import kreps.app.core.Representative
import java.io.Reader
import java.lang.reflect.Type

class GoogleCivicInfoJsonDeserializer : JsonDeserializer<CivicInfo> {
    override fun deserialize(json: JsonElement?, typeOfT: Type?, context: JsonDeserializationContext?): CivicInfo {
        if (json == null)
            throw Exception()

        val jsonObject: JsonObject = json.asJsonObject

        val divisions = getListOfDivisions(jsonObject.get("divisions").asJsonObject)
        val offices = getListOfOffices(jsonObject.get("offices").asJsonArray)
        val representatives = getListOfRepresentatives(jsonObject.get("officials").asJsonArray)

        return CivicInfo(divisions, offices, representatives)
    }

    private fun getListOfDivisions(divs: JsonObject): List<Division> {
        return divs.entrySet().map { parseDivision(it) }
    }

    private fun getListOfOffices(jsonOffices: JsonArray): List<Office> {
        return jsonOffices.map { parseOffice(it.asJsonObject) }
    }

    private fun getListOfRepresentatives(jsonReps: JsonArray): List<Representative> {
        return jsonReps.map { parseRepresentative(it.asJsonObject) }
    }

    private fun parseOffice(o: JsonObject): Office {
        return Office(
                Name = o.get("name").asString,
                DivisionId = o.get("divisionId").asString,
                Levels = o.getAsJsonArray("levels")?.map { it.asString } ?: emptyList(),
                Roles = o.getAsJsonArray("roles")?.map { it.asString } ?: emptyList(),
                Officials = o.getAsJsonArray("officialIndices")?.map { it.asInt } ?: emptyList()
        )
    }

    private fun parseDivision(d: MutableMap.MutableEntry<String, JsonElement>): Division {
        val p = d.value.asJsonObject
        val name = p.get("name")
        val indices = p.getAsJsonArray("officeIndices")
        return Division(d.key, name.asString, indices?.map { it.asInt } ?: emptyList())
    }

    fun parseRepresentative(rep: JsonObject): Representative {
        val name: String = rep.get("name").asString
        val party = Party.valueOf(rep.get("party")?.asString ?: "Unknown")
        val addressJson = rep.get("address")?.asJsonArray
        var address : Address? = null
        if(addressJson != null){
            address = parseAddress(addressJson[0].asJsonObject)
        }

        return Representative(name, party, "url", address ?: Address())
    }

    fun parseAddress(address: JsonObject): Address {
        address.get("line1").asString
        return Address(
                address.get("line1")?.asString ?: "",
                address.get("line2")?.asString ?: "",
                address.get("city")?.asString ?: "",
                address.get("state")?.asString ?: "",
                address.get("zip")?.asInt ?: 0
        )
    }
}

class GoogleCivicInfoResponseDeserializer : ResponseDeserializable<CivicInfo> {
    override fun deserialize(reader: Reader): CivicInfo? {
        val builder = GsonBuilder()
        builder.registerTypeAdapter(CivicInfo::class.java, GoogleCivicInfoJsonDeserializer())

        val gson = builder.create()

        return gson.fromJson(reader, CivicInfo::class.java)
    }
}

data class Division(val DivisionId: String, val Name: String, val OfficeIndices: List<Int>)
data class Office(
        val Name: String,
        val DivisionId: String,
        val Levels: List<String>,
        val Roles: List<String>,
        val Officials: List<Int>
)

data class CivicInfo(val divisions: List<Division>, val offices: List<Office>, val representatives: List<Representative>)

