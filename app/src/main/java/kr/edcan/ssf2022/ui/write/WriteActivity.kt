package kr.edcan.ssf2022.ui.write

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
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


        binding.gbWriteWeather.setOnCheckedChangeListener { radioGroup, i ->
            viewModel.weather.value = i
        }

        binding.gbWriteEmotion.setOnCheckedChangeListener { radioGroup, i ->
            viewModel.emotion.value = i
        }

        binding.tbWrite.setNavigationOnClickListener {
            finish()
        }

        // todo Mission 5 : id가 btn_write_save인 Button을 선택해서 saveBtn에 저장하세요.





        /* todo Mission 6 : 선택해온 saveBtn에 Click Evnet를 만들어서 일기 작성을 실행해주세요.
                            일기를 작성할때는 viewModel.writeDiary() 함수를 실행하면 됩니다.
        *                   일기를 작성할때 일기가 비어있다면 "일기를 작성 해주세요."를 토스트 메시지로 보여주세요.   */






        viewModel.state.observe(this){
            when(it){
                State.SUCCESS -> {
                    // todo Mission 7 : "일기를 작성을 했어요"를 토스트 메시지로 보여주세요.


                    finish()
                }
                State.LOADING -> {

                }
                State.FAIL -> {
                    // todo Mission 8 : "일기를 작성을 실패 했어요"를 토스트 메시지로 보여주세요.


                }
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.tool_bar_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.menu_edcan -> {
                // todo Special Mission : EDCAN 웹 사이트로 이동하는 코드를 작성해주세요.
                

            }
        }

        return super.onOptionsItemSelected(item)
    }
}