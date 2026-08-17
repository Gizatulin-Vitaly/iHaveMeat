package ru.naves.app

import android.app.DownloadManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.Uri
import android.os.Environment
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONObject
import java.io.File
import java.net.HttpURLConnection
import java.net.URL
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class UpdateManager(private val context: Context) {

    /**
     * Проверка обновлений через GitHub Releases API.
     * @param repoPath Путь к репозиторию, например "username/repo_name"
     */
    fun checkForUpdates(
        repoPath: String,
        currentVersionCode: Int,
        onUpdateAvailable: (versionName: String, apkUrl: String) -> Unit,
        onNoUpdate: () -> Unit,
        onError: (Exception) -> Unit
    ) {
        CoroutineScope(Dispatchers.Main).launch {
            try {
                val result = withContext(Dispatchers.IO) {
                    val url = URL("https://api.github.com/repos/$repoPath/releases/latest")
                    val connection = url.openConnection() as HttpURLConnection
                    connection.requestMethod = "GET"
                    connection.connect()

                    if (connection.responseCode == 200) {
                        val response = connection.inputStream.bufferedReader().use { it.readText() }
                        val json = JSONObject(response)
                        
                        // GitHub использует теги как версии. Обычно это "v1.1.0"
                        val latestVersionName = json.getString("tag_name")
                        
                        // Ищем APK в ассетах релиза
                        val assets = json.getJSONArray("assets")
                        var downloadUrl: String? = null
                        for (i in 0 until assets.length()) {
                            val asset = assets.getJSONObject(i)
                            if (asset.getString("name").endsWith(".apk")) {
                                downloadUrl = asset.getString("browser_download_url")
                                break
                            }
                        }

                        // В GitHub API нет напрямую versionCode, поэтому мы можем 
                        // либо парсить tag_name, либо просто сравнивать их как строки.
                        // Для примера предположим, что если tag_name отличается от текущей - нужно обновление.
                        if (downloadUrl != null) {
                            UpdateResult.Available(latestVersionName, downloadUrl)
                        } else {
                            UpdateResult.NoUpdate
                        }
                    } else {
                        UpdateResult.NoUpdate
                    }
                }

                when (result) {
                    is UpdateResult.Available -> onUpdateAvailable(result.versionName, result.apkUrl)
                    is UpdateResult.NoUpdate -> onNoUpdate()
                }

            } catch (e: Exception) {
                onError(e)
            }
        }
    }

    private sealed class UpdateResult {
        data class Available(val versionName: String, val apkUrl: String) : UpdateResult()
        object NoUpdate : UpdateResult()
    }

    fun downloadAndInstall(apkUrl: String) {
        val downloadManager = context.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
        val destination = File(context.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS), "update.apk")
        
        if (destination.exists()) destination.delete()

        val request = DownloadManager.Request(Uri.parse(apkUrl))
            .setTitle("Обновление I have meat")
            .setDescription("Загрузка новой версии...")
            .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
            .setDestinationUri(Uri.fromFile(destination))

        val downloadId = downloadManager.enqueue(request)
        Toast.makeText(context, "Загрузка началась...", Toast.LENGTH_SHORT).show()

        val onComplete = object : BroadcastReceiver() {
            override fun onReceive(ctxt: Context, intent: Intent) {
                val id = intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, -1)
                if (id == downloadId) {
                    installApk(destination)
                    context.unregisterReceiver(this)
                }
            }
        }
        
        ContextCompat.registerReceiver(
            context, onComplete, 
            IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE), 
            ContextCompat.RECEIVER_EXPORTED
        )
    }

    private fun installApk(file: File) {
        val uri = FileProvider.getUriForFile(context, "${context.packageName}.fileprovider", file)
        val intent = Intent(Intent.ACTION_VIEW).apply {
            setDataAndType(uri, "application/vnd.android.package-archive")
            addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        }
        try {
            context.startActivity(intent)
        } catch (e: Exception) {
            Toast.makeText(context, "Ошибка установки", Toast.LENGTH_LONG).show()
        }
    }
}
