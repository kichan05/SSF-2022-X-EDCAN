package kr.edcan.ssf2022.ui.main

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toolbar
import androidx.activity.viewModels
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import kr.edcan.ssf2022.R
import kr.edcan.ssf2022.model.data.User
import kr.edcan.ssf2022.util.State
import kr.edcan.ssf2022.util.Url

class MainActivity : AppCompatActivity() {
    val viewModel : MainViewModel by viewModels()

    lateinit var navControl : NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel.userData.value = intent.getParcelableExtra<User>("userData")
        navControl = findNavController(R.id.fr_main)
        findViewById<BottomNavigationView>(R.id.btnv_main).setupWithNavController(navControl)

        findViewById<androidx.appcompat.widget.Toolbar>(R.id.tb_main).setNavigationOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(Url.edcanWeb))
            startActivity(intent)
        }

        viewModel.getDiaryList()
    }

    override fun onRestart() {
        super.onRestart()
        viewModel.getDiaryList()
    }
}