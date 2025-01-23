package com.tops.kotlin.mediaplayerapp

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import android.media.MediaPlayer
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.tops.kotlin.mediaplayerapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private var mediaPlayer: MediaPlayer? = null
    private val REQUEST_CODE_PICK_AUDIO = 101
    private val REQUEST_PERMISSION = 100

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val btnPlayRaw = binding.btnPlayRaw
        val btnPlayMemory = binding.btnPlayMemory
        val btnPlayServer = binding.btnPlayServer

        btnPlayRaw.setOnClickListener { playFromRaw() }
        btnPlayMemory.setOnClickListener { checkAndPickFile() }
        btnPlayServer.setOnClickListener { playFromServer() }
    }

    @SuppressLint("SetTextI18n")
    private fun playFromRaw() {
        stopMediaPlayer()
        mediaPlayer = MediaPlayer.create(this, R.raw.song)
        mediaPlayer?.apply {
            start()
            setOnCompletionListener {
                binding.tvStatus.text = "Status: Finished playing Raw Resource"
            }
        }
        binding.tvStatus.text = "Status: Playing Raw Resource"
    }

    private fun checkAndPickFile() {
        if (ContextCompat.checkSelfPermission(
                this,
                android.Manifest.permission.READ_EXTERNAL_STORAGE
            )
            != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
                REQUEST_PERMISSION
            )
        } else {
            pickAudioFile()
        }
    }

    private fun pickAudioFile() {
        val intent = Intent(Intent.ACTION_OPEN_DOCUMENT).apply {
            type = "audio/*"
            addCategory(Intent.CATEGORY_OPENABLE)
        }
        startActivityForResult(intent, REQUEST_CODE_PICK_AUDIO)
    }

    @SuppressLint("SetTextI18n")
    private fun playFromServer() {
        stopMediaPlayer()
        val serverUrl =
            "https://drive.google.com/file/d/1q7vuN5hOopUU5K9jqDuDwOLYuhajT6No/view?usp=drive_link"
        mediaPlayer = MediaPlayer().apply {
            setDataSource(serverUrl)
            prepareAsync()
            setOnPreparedListener {
                start()
                binding.tvStatus.text = "Status: Playing from Server"
            }
            setOnCompletionListener {
                binding.tvStatus.text = "Status: Finished playing from Server"
            }
        }
        binding.tvStatus.text = "Status: Preparing Server Audio..."
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == REQUEST_PERMISSION && grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            pickAudioFile()
        } else {
            Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE_PICK_AUDIO && resultCode == RESULT_OK) {
            val uri: Uri? = data?.data
            if (uri != null) {
                playFromMemory(uri)
            } else {
                Toast.makeText(this, "Failed to pick file", Toast.LENGTH_SHORT).show()
            }
        }
    }

    @SuppressLint("SetTextI18n")
    private fun playFromMemory(uri: Uri) {
        stopMediaPlayer()
        mediaPlayer = MediaPlayer().apply {
            setDataSource(this@MainActivity, uri)
            prepare()
            start()
            setOnCompletionListener {
                binding.tvStatus.text = "Status: Finished playing from Memory"
            }
        }
        binding.tvStatus.text = "Status: Playing from Memory"
    }

    private fun stopMediaPlayer() {
        mediaPlayer?.release()
        mediaPlayer = null
    }

    override fun onDestroy() {
        super.onDestroy()
        stopMediaPlayer()
    }
}