package ru.vorxa.ncraftmedia.client

import io.ktor.client.HttpClient
import io.ktor.client.features.json.GsonSerializer
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.logging.LogLevel
import io.ktor.client.features.logging.Logger
import io.ktor.client.features.logging.Logging
import io.ktor.client.features.logging.SIMPLE
import io.ktor.http.ContentType

object Api {
    const val url = "https://raw.githubusercontent.com/vorxa/Netology.Kt.Gson/master/output.json"

    val client = HttpClient {
        install(JsonFeature) {
            acceptContentTypes = listOf(
                ContentType.Text.Plain,
                ContentType.Application.Json
            )
            serializer = GsonSerializer()
        }
//        install(Logging){
//            logger = Logger.SIMPLE
//            level = LogLevel.INFO
//        }
    }
}
