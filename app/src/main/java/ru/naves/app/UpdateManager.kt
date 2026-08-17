package ru.naves.app

import android.content.Context
import android.content.Intent
import android.net.Uri
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.json.JSONObject
import java.net.HttpURLConnection
import java.net.URL

class UpdateManager(private val context: Context) {

    /**
     * Проверка обновлений через GitHub Releases API.
     */
    fun checkForUpdates(
        repoPath: String,
        currentVersionName: String,
        onUpdateAvailable: (versionName: String, releaseUrl: String) -> Unit,
        onNoUpdate: () -> Unit,
        onError: (Exception) -> Unit
    ) {
        CoroutineScope(Dispatchers.Main).launch {
            try {
                val result = withContext(Dispatchers.IO) {
                    val url = URL("https://api.github.com/repos/$repoPath/releases/latest")
                    val connection = url.openConnection() as HttpURLConnection
                    connection.connectTimeout = 5000
                    connection.readTimeout = 5000
                    
                    if (connection.responseCode == 200) {
                        val response = connection.inputStream.bufferedReader().use { it.readText() }
                        val json = JSONObject(response)
                        
                        val latestTag = json.getString("tag_name")
                        val releaseUrl = json.getString("html_url") // Ссылка на страницу релиза

                        // Сравниваем версии
                        if (isNewerVersion(latestTag, currentVersionName)) {
                            UpdateResult.Available(latestTag, releaseUrl)
                        } else {
                            UpdateResult.NoUpdate
                        }
                    } else {
                        UpdateResult.NoUpdate
                    }
                }

                when (result) {
                    is UpdateResult.Available -> onUpdateAvailable(result.versionName, result.releaseUrl)
                    is UpdateResult.NoUpdate -> onNoUpdate()
                }

            } catch (e: Exception) {
                onError(e)
            }
        }
    }

    private fun isNewerVersion(latest: String, current: String): Boolean {
        val latestClean = latest.replace("v", "").trim()
        val currentClean = current.replace("v", "").trim()
        return latestClean != currentClean
    }

    private sealed class UpdateResult {
        data class Available(val versionName: String, val releaseUrl: String) : UpdateResult()
        object NoUpdate : UpdateResult()
    }

    /**
     * Просто открывает страницу релиза в браузере.
     * Это безопасно и не блокируется Google Play.
     */
    fun openUpdatePage(url: String) {
        try {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url)).apply {
                addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            }
            context.startActivity(intent)
        } catch (e: Exception) {
            // Игнорируем или логируем
        }
    }
}
