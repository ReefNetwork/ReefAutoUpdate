package net.ree_jp.reefUpdate

import cn.nukkit.Server
import net.lingala.zip4j.ZipFile
import java.io.DataInputStream
import java.io.DataOutputStream
import java.io.File
import java.io.FileOutputStream
import java.net.URL
import java.util.*


class Update {

    fun run(url: URL) {
        val dlPath: String = url.path
        val name = "ReefSeichiArtifact"+".zip"
        var size = 0

        val con = url.openConnection()

        con.addRequestProperty("User-Agent", "Mozilla/5.0")
        con.setRequestProperty("Authorization", "Basic " + Base64.getEncoder().encodeToString("Ree-jp:${ReefAutoUpdatePlugin.getInstance().getToken()}".toByteArray()))

        DataInputStream(con.getInputStream()).use { `in` ->
            DataOutputStream(FileOutputStream("$name")).use { out ->
                val buf = ByteArray(8192)
                var len = 0

                //入力ストリームからバイト配列に読み込む。ストリームが終端に達した場合は -1 が返る
                while (`in`.read(buf).also { len = it } != -1) {
                    //バイト配列を出力ストリームに書き込む
                    out.write(buf, 0, len)
                    size += len
                }

                if (!isFree(System.getProperty("java.class.path"), size.toLong())) throw Exception("スペースが足りません")

                //バッファリングされていたすべての出力バイトを強制的に書き込む
                out.flush()
            }
        }
        Server.getInstance().logger.info("$name - $size bytes")

        ZipFile("$name").extractAll("plugins//")
    }

    private fun isFree(path: String, long: Long): Boolean {
        return File(path).freeSpace > long
    }
}