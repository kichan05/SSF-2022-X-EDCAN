package kr.edcan.ssf2022.ui.write

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import kr.edcan.ssf2022.R
import kr.edcan.ssf2022.databinding.ActivityWriteBinding
import kr.edcan.ssf2022.model.data.User
import kr.edcan.ssf2022.util.ExtraKey
import kr.edcan.ssf2022.util.State

class WriteActivity : AppCompatActivity() {
    lateinit var binding : ActivityWriteBinding
    val viewModel : WriteViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_write)
        binding.lifecycleOwner = this
        binding.vm = viewModel

        setSupportActionBar(binding.tbWrite)
        viewModel.userData.value = intent.getParcelableExtra(ExtraKey.userData)!!

        viewModel.state.observe(this){
            when(it){
                State.SUCCESS -> {
                    Toast.makeText(this, "일기를 작성했어요", Toast.LENGTH_SHORT).show()
                    finish()
                }
                State.LOADING -> {

                }
                State.FAIL -> {
                    Toast.makeText(this, "일기를 작성을 실패 했어요", Toast.LENGTH_SHORT).show()
                }
            }
        }

        binding.gbWriteWeather.setOnCheckedChangeListener { radioGroup, i ->
            viewModel.weather.value = i
        }

        binding.gbWriteEmotion.setOnCheckedChangeListener { radioGroup, i ->
            viewModel.emotion.value = i
        }

        binding.tbWrite.setNavigationOnClickListener {
            finish()
        }

        binding.btnWriteSave.setOnClickListener {
            if(viewModel.content.value.isNullOrBlank()){
                Toast.makeText(this, "일기를 작성 해주세요.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            viewModel.writeDiary()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.tool_bar_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.menu_edcan -> {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.edcan.kr"))
                startActivity(intent)
            }
        }

        return super.onOptionsItemSelected(item)
    }
}