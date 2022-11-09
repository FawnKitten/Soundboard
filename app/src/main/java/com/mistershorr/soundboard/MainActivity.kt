package com.mistershorr.soundboard

import android.media.AudioManager
import android.media.SoundPool
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.mistershorr.soundboard.databinding.ActivityMainBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    val TAG = "MainActivity"
//    lateinit var buttonA : Button
//    lateinit var buttonBb : Button
//    lateinit var buttonB : Button
//    lateinit var buttonC : Button
    private lateinit var soundPool : SoundPool
    private var aNote = 0
    private var bbNote = 0
    private var bNote = 0
    private var cNote = 0
    private var csNote = 0
    private var dNote = 0
    private var dsNote = 0
    private var eNote = 0
    private var fNote = 0
    private var fsNote = 0
    private var gNote = 0
    private var gsNote = 0

    private var noteMap = mapOf(
        "A" to aNote,
        "Bb" to bbNote,
        "B" to bNote,
        "C" to cNote,
        "Cs" to csNote,
        "D" to dNote,
        "Ds" to dsNote,
        "E" to eNote,
        "F" to fNote,
        "Fs" to fsNote,
        "G" to gNote,
        "Gs" to gsNote
    )

    // instance variable for the view binging
    private lateinit var binding: ActivityMainBinding

    private lateinit var song: List<Note>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        song = loadSong()
        initializeSoundPool()
        setListeners()
    }

    private fun setListeners() {
        val soundBoardListener = SoundBoardListener()
        binding.buttonMainA.setOnClickListener(soundBoardListener)
        binding.buttonMainBb.setOnClickListener(soundBoardListener)
        binding.buttonMainB.setOnClickListener(soundBoardListener)
        binding.buttonMainC.setOnClickListener(soundBoardListener)
        binding.buttonMainCs.setOnClickListener(soundBoardListener)
        binding.buttonMainD.setOnClickListener(soundBoardListener)
        binding.buttonMainDs.setOnClickListener(soundBoardListener)
        binding.buttonMainE.setOnClickListener(soundBoardListener)
        binding.buttonMainF.setOnClickListener(soundBoardListener)
        binding.buttonMainFs.setOnClickListener(soundBoardListener)
        binding.buttonMainG.setOnClickListener(soundBoardListener)
        binding.buttonMainGs.setOnClickListener(soundBoardListener)
        binding.buttonMainPlay.setOnClickListener {
            // launch a coroutine
            Log.d(TAG, "setListeners: *** Play Button Clicked ***")
            GlobalScope.launch {
                playSong(song)
            }
        }
    }

    private fun loadSong(): List<Note> {
        val inputStream = resources.openRawResource(R.raw.song)
        val jsonString = inputStream.bufferedReader().use {
            // the last line of the use function is returned
            it.readText()
        }
        val gson = Gson()
        val type = object : TypeToken<List<Note>>() { }.type
        val questions = gson.fromJson<List<Note>>(jsonString, type)
        return questions
    }

    private fun initializeSoundPool() {

        this.volumeControlStream = AudioManager.STREAM_MUSIC
        soundPool = SoundPool(10, AudioManager.STREAM_MUSIC, 0)
//        soundPool.setOnLoadCompleteListener(SoundPool.OnLoadCompleteListener { soundPool, sampleId, status ->
//           // isSoundPoolLoaded = true
//        })
        aNote  = soundPool.load(this, R.raw.scalea,  1)
        bbNote = soundPool.load(this, R.raw.scalebb, 1)
        bNote  = soundPool.load(this, R.raw.scaleb,  1)
        cNote  = soundPool.load(this, R.raw.scalec,  1)
        csNote = soundPool.load(this, R.raw.scalecs, 1)
        dNote  = soundPool.load(this, R.raw.scaled,  1)
        dsNote = soundPool.load(this, R.raw.scaleds, 1)
        eNote  = soundPool.load(this, R.raw.scalee,  1)
        fNote  = soundPool.load(this, R.raw.scalef,  1)
        fsNote = soundPool.load(this, R.raw.scalefs, 1)
        gNote  = soundPool.load(this, R.raw.scaleg,  1)
        gsNote = soundPool.load(this, R.raw.scalegs, 1)
    }

    private fun playNote(noteId : Int) {
        soundPool.play(noteId, 1f, 1f, 1, 0, 1f)
    }

    // Locks main thread, not ideal.
//    private fun delay(time: Long) {
//        try {
//            Thread.sleep(time)
//        } catch (e: InterruptedException) {
//            e.printStackTrace()
//            Log.e(TAG, "delay: delay interrupted", e)
//        }
//    }

    private suspend fun playSong(song: List<Note>) {
        for (note in song) {
            playNote(noteMap[note.frequency] ?: 0)
            delay(note.duration)
        }
    }

    private inner class SoundBoardListener : View.OnClickListener {
        override fun onClick(v: View?) {
            when(v?.id) {
                R.id.button_main_aLow -> playNote(aNote)
                R.id.button_main_bbLow -> playNote(bbNote)
                R.id.button_main_bLow -> playNote(bNote)
                R.id.button_main_cLow -> playNote(cNote)
                R.id.button_main_csLow -> playNote(csNote)
                R.id.button_main_dLow -> playNote(dNote)
                R.id.button_main_dsLow -> playNote(dsNote)
                R.id.button_main_fLow -> playNote(fNote)
                R.id.button_main_fsLow -> playNote(fsNote)
                R.id.button_main_gLow -> playNote(fsNote)
                R.id.button_main_gsLow -> playNote(fsNote)
                R.id.button_main_a -> playNote(aNote)
                R.id.button_main_bb -> playNote(bbNote)
                R.id.button_main_b -> playNote(bNote)
                R.id.button_main_c -> playNote(cNote)
                R.id.button_main_cs -> playNote(csNote)
                R.id.button_main_d -> playNote(dNote)
                R.id.button_main_ds -> playNote(dsNote)
                R.id.button_main_f -> playNote(fNote)
                R.id.button_main_fs -> playNote(fsNote)
                R.id.button_main_g -> playNote(fsNote)
                R.id.button_main_gs -> playNote(fsNote)
            }
        }
    }
}