package com.caresaas.caresaasmobiletask.core.di

//import io.ktor.client.plugins.auth.*
import android.content.Context
import androidx.compose.ui.util.trace
import coil.ImageLoader
import coil.util.DebugLogger
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.engine.okhttp.OkHttpEngine
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.DEFAULT
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.header
import io.ktor.serialization.kotlinx.json.json
import io.ktor.server.auth.Authentication
import kotlinx.serialization.json.Json
import okhttp3.Call
import okhttp3.OkHttpClient
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun providesNetworkJson(): Json = Json {
        isLenient = true
        ignoreUnknownKeys = true
    }

    private fun getClientEngine() = OkHttp

    @Provides
    @Singleton
    fun providesKtorClient(): HttpClient {
        val client = HttpClient(getClientEngine()) {
            expectSuccess = true
            install(Logging) {
                logger = Logger.DEFAULT
                level = LogLevel.ALL
                logger = object : Logger {
                    override fun log(message: String) {
                        println("HTTP Client: $message")
                    }
                }
            }

            defaultRequest {
                header("Content-Type", "application/json")
                url("https://api.staging.caresaas.co.uk/caresaas/v1/services/")
            }

            install(ContentNegotiation) {
                json(
                    Json {
                        isLenient = true
                        ignoreUnknownKeys = true
                    }
                )
            }

            /*install(Auth){

            }*/
        }
        return client
    }

    @Provides
    @Singleton
    fun okHttpCallFactory(
        ktorClient: HttpClient,
    ): Call.Factory = trace("CareSaaSOkHttpClient") {
        (ktorClient.engine as OkHttpEngine).config.preconfigured
            ?: OkHttpClient.Builder().build()
    }

    @Provides
    @Singleton
    fun imageLoader(
        // We specifically request dagger.Lazy here, so that it's not instantiated from Dagger.
        okHttpCallFactory: dagger.Lazy<Call.Factory>,
        @ApplicationContext application: Context,
    ): ImageLoader = trace("CareSaaSImageLoader") {
        ImageLoader.Builder(application)
            .callFactory { okHttpCallFactory.get() }
//            .components { add(SvgDecoder.Factory()) }
            // Assume most content images are versioned urls
            // but some problematic images are fetching each time
            .respectCacheHeaders(false)
            .apply {
//                if (BuildConfig.DEBUG) {
                logger(DebugLogger())
//                }
            }
            .build()
    }
}