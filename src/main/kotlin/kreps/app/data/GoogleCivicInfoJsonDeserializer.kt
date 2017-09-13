package kreps.app.data

import com.github.kittinunf.fuel.core.ResponseDeserializable
import com.google.gson.*
import com.sun.org.apache.xpath.internal.operations.Div
import java.io.Reader
import java.lang.reflect.Type

class GoogleCivicInfoJsonDeserializer : JsonDeserializer<CivicInfo> {
    override fun deserialize(json: JsonElement?, typeOfT: Type?, context: JsonDeserializationContext?): CivicInfo {
        if(json == null)
            throw Exception()

        val json : JsonObject = json.asJsonObject
        val divs = json.get("divisions").asJsonObject
        val divisions = arrayListOf<Division>()
        for (d in divs.entrySet().iterator()) {
            val p = divs[d.key].asJsonObject
            val name = p.get("name")
            val indices = p.getAsJsonArray("officeIndices")

            divisions.add(Division(d.key, name.asString, indices?.map{ it.asInt } ?: emptyList()))
        }



        return CivicInfo(divisions)
    }
}

class GoogleCivicInfoResponseDeserializer : ResponseDeserializable<CivicInfo>{
    override fun deserialize(reader: Reader): CivicInfo? {
        val builder: GsonBuilder = GsonBuilder()
        builder.registerTypeAdapter(CivicInfo::class.java, GoogleCivicInfoJsonDeserializer())

        val gson = builder.create()

        return gson.fromJson(reader, CivicInfo::class.java)
    }
}

data class Division(val DivisionId : String, val Name : String, val OfficeIndices: List<Int>)

data class CivicInfo(val divisions: List<Division>)

