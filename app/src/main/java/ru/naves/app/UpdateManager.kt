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

    fun checkForUpdates(
        repoPath: String,
        currentVersionName: String,
        onUpdateAvailable: (versionName: String, downloadUrl: String) -> Unit,
        onNoUpdate: () -> Unit,
        onError: (Exception) -> Unit
    ) {
        CoroutineScope(Dispatchers.Main).launch {
            try {
                val result = withContext(Dispatchers.IO) {
                    val url = URL("https://api.github.com/repos/$repoPath/releases/latest")
                    val connection = url.openConnection() as HttpURLConnection
                    connection.connectTimeout = 5000
                    
                    if (connection.responseCode == 200) {
                        val response = connection.inputStream.bufferedReader().use { it.readText() }
                        val json = JSONObject(response)
                        val latestTag = json.getString("tag_name")
                        
                        // Ищем прямую ссылку на APK в ассетах
                        val assets = json.getJSONArray("assets")
                        var apkUrl: String? = null
                        for (i in 0 until assets.length()) {
                            val asset = assets.getJSONObject(i)
                            if (asset.getString("name").endsWith(".apk")) {
                                apkUrl = asset.getString("browser_download_url")
                                break
                            }
                        }

                        // Если не нашли APK, берем ссылку на страницу релиза
                        val finalUrl = apkUrl ?: json.getString("html_url")

                        if (isNewerVersion(latestTag, currentVersionName)) {
                            UpdateResult.Available(latestTag, finalUrl)
                        } else {
                            UpdateResult.NoUpdate
                        }
                    } else {
                        UpdateResult.NoUpdate
                    }
                }

                when (result) {
                    is UpdateResult.Available -> onUpdateAvailable(result.versionName, result.downloadUrl)
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
        data class Available(val versionName: String, val downloadUrl: String) : UpdateResult()
        object NoUpdate : UpdateResult()
    }

    fun openUpdate(url: String) {
        try {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url)).apply {
                addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            }
            context.startActivity(intent)
        } catch (e: Exception) { }
    }
}
