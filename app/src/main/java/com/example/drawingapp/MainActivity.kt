package com.example.drawingapp

import android.app.Dialog
import android.os.Bundle
import android.widget.ImageButton
import android.widget.SeekBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private lateinit var drawingView: DrawingView
    private lateinit var brushButton: ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        drawingView = findViewById(R.id.drawing_view)
        brushButton = findViewById(R.id.brush_button)

        drawingView.changeBrushSize(20f)

        brushButton.setOnClickListener {
            showBrushChooserDialog()
        }
    }

    private fun showBrushChooserDialog() {
        val brushDialog = Dialog(this, android.R.style.Theme_DeviceDefault_Light_Dialog)
        brushDialog.setContentView(R.layout.dialog_brush)

        val seekBar = brushDialog.findViewById<SeekBar>(R.id.dialog_seek_bar)
        val textView = brushDialog.findViewById<TextView>(R.id.dialog_text_view_progress)

        seekBar.progress = 20
        textView.text = "20"

        seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                drawingView.changeBrushSize(progress.toFloat())
                textView.text = progress.toString()
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })

        brushDialog.show()
    }
}
