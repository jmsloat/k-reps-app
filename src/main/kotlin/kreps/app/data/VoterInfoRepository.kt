package kreps.app.data

import com.beust.klaxon.JsonObject
import com.beust.klaxon.Parser
import com.github.kittinunf.fuel.core.FuelManager
import com.github.kittinunf.fuel.httpGet
import kreps.app.core.Address

class VoterInfoRepository {

    init{
        FuelManager.instance.basePath = "https://www.googleapis.com/civicinfo/v2"
        FuelManager.instance.baseParams = listOf("key" to CivicInfoKeyProvider.GetGoogleCivicInfoApiKey())
    }

    fun getRepresentatives(address: Address){
        val response = "/representatives".httpGet(listOf("address" to address.toString()))
                .responseString{
                    _, _, result ->
                    val (data, err) = result
                    if(err == null) {
                        val json = data?.let { parseJsonResponse(it) }
                    }
                }
    }

    fun parseJsonResponse(response : String) : JsonObject{
        val parser = Parser()
        val sb = StringBuilder(response)
        return parser.parse(sb) as JsonObject
    }

}

class GoogleCivicInfoResponse {

}

fun main(args: Array<String>) {
    val repo = VoterInfoRepository()
    val testAddress = Address("3815 Winding Way", "", "Cincinnati", "OH", 45229)

    val representatives = repo.getRepresentatives(testAddress)
    println(representatives)

}
