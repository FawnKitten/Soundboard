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
    private var laNote = 0
    private var lbbNote = 0
    private var lbNote = 0
    private var lcNote = 0
    private var lcsNote = 0
    private var ldNote = 0
    private var ldsNote = 0
    private var leNote = 0
    private var lfNote = 0
    private var lfsNote = 0
    private var lgNote = 0
    private var lgsNote = 0
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

    private var noteMap = mapOf<String, Int>(
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
        laNote  = soundPool.load(this, R.raw.scalea,  1)
        lbbNote = soundPool.load(this, R.raw.scalebb, 1)
        lbNote  = soundPool.load(this, R.raw.scaleb,  1)
        lcNote  = soundPool.load(this, R.raw.scalec,  1)
        lcsNote = soundPool.load(this, R.raw.scalecs, 1)
        ldNote  = soundPool.load(this, R.raw.scaled,  1)
        ldsNote = soundPool.load(this, R.raw.scaleds, 1)
        leNote  = soundPool.load(this, R.raw.scalee,  1)
        lfNote  = soundPool.load(this, R.raw.scalef,  1)
        lfsNote = soundPool.load(this, R.raw.scalefs, 1)
        lgNote  = soundPool.load(this, R.raw.scaleg,  1)
        lgsNote = soundPool.load(this, R.raw.scalegs, 1)
        aNote  = soundPool.load(this, R.raw.scalehigha,  1)
        bbNote = soundPool.load(this, R.raw.scalehighbb, 1)
        bNote  = soundPool.load(this, R.raw.scalehighb,  1)
        cNote  = soundPool.load(this, R.raw.scalehighc,  1)
        csNote = soundPool.load(this, R.raw.scalehighcs, 1)
        dNote  = soundPool.load(this, R.raw.scalehighd,  1)
        dsNote = soundPool.load(this, R.raw.scalehighds, 1)
        eNote  = soundPool.load(this, R.raw.scalehighe,  1)
        fNote  = soundPool.load(this, R.raw.scalehighf,  1)
        fsNote = soundPool.load(this, R.raw.scalehighfs, 1)
        gNote  = soundPool.load(this, R.raw.scalehighg,  1)
        gsNote = soundPool.load(this, R.raw.scalehighgs, 1)

        noteMap = mapOf(
            "LA"  to laNote,
            "LBb" to lbbNote,
            "LB"  to lbNote,
            "LC"  to lcNote,
            "LCs" to lcsNote,
            "LD"  to ldNote,
            "LDs" to ldsNote,
            "LE"  to leNote,
            "LF"  to lfNote,
            "LFs" to lfsNote,
            "LG"  to lgNote,
            "LGs" to lgsNote,
            "A"   to aNote,
            "Bb"  to bbNote,
            "B"   to bNote,
            "C"   to cNote,
            "Cs"  to csNote,
            "D"   to dNote,
            "Ds"  to dsNote,
            "E"   to eNote,
            "F"   to fNote,
            "Fs"  to fsNote,
            "G"   to gNote,
            "Gs"  to gsNote
        )
    }

    private fun playNote(noteId : Int) {
        Log.d(TAG, "playNote: played note $noteId")
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
            playNote(noteMap[note.note] ?: 0)
            delay(note.duration)
        }
    }

    private inner class SoundBoardListener : View.OnClickListener {
        override fun onClick(v: View?) {
            when(v?.id) {
                R.id.button_main_aLow  -> playNote(laNote)
                R.id.button_main_bbLow -> playNote(lbbNote)
                R.id.button_main_bLow  -> playNote(lbNote)
                R.id.button_main_cLow  -> playNote(lcNote)
                R.id.button_main_csLow -> playNote(lcsNote)
                R.id.button_main_dLow  -> playNote(ldNote)
                R.id.button_main_dsLow -> playNote(ldsNote)
                R.id.button_main_fLow  -> playNote(lfNote)
                R.id.button_main_fsLow -> playNote(lfsNote)
                R.id.button_main_gLow  -> playNote(lgNote)
                R.id.button_main_gsLow -> playNote(lgsNote)
                R.id.button_main_a  -> playNote(aNote)
                R.id.button_main_bb -> playNote(bbNote)
                R.id.button_main_b  -> playNote(bNote)
                R.id.button_main_c  -> playNote(cNote)
                R.id.button_main_cs -> playNote(csNote)
                R.id.button_main_d  -> playNote(dNote)
                R.id.button_main_ds -> playNote(dsNote)
                R.id.button_main_f  -> playNote(fNote)
                R.id.button_main_fs -> playNote(fsNote)
                R.id.button_main_g  -> playNote(gNote)
                R.id.button_main_gs -> playNote(gsNote)
            }
        }
    }
}