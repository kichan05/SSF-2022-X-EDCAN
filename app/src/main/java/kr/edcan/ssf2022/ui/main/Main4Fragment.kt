package kr.edcan.ssf2022.ui.main

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.google.firebase.auth.FirebaseAuth
import kr.edcan.ssf2022.R
import kr.edcan.ssf2022.databinding.FragmentMain4Binding
import kr.edcan.ssf2022.ui.login.LoginActivity
import kr.edcan.ssf2022.util.Url

class Main4Fragment : Fragment() {
    lateinit var binding : FragmentMain4Binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_main4, container, false)


        with(binding){
            btnMain4OpenSource.setOnClickListener {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(Url.openSource))
                startActivity(intent)
            }

            btnMain4Club.setOnClickListener {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(Url.edcanWeb))
                startActivity(intent)
            }

            btnMain4Developer.setOnClickListener {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(Url.developerGithub))
                startActivity(intent)
            }

            btnMain4Logout.setOnClickListener {
                FirebaseAuth.getInstance().signOut()

                val intent = Intent(binding.root.context, LoginActivity::class.java)
                startActivity(intent)
                activity!!.finish()
            }

            btnMain4Donation.setOnClickListener {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(Url.developerDonation))
                startActivity(intent)
            }

        }



        return binding.root
    }
}