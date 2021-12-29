package shyam.brekki.splootnewsassignment.fragments

import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.view.*
import androidx.fragment.app.Fragment
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import shyam.brekki.splootnewsassignment.R
import shyam.brekki.splootnewsassignment.databinding.FragmentSplashBinding


class Splash : Fragment() {

    lateinit var binding: FragmentSplashBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSplashBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            requireActivity().window.insetsController?.hide(WindowInsets.Type.statusBars())
        }else {
            requireActivity().window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
        }

        Handler().postDelayed({
            val navOptions = NavOptions.Builder().setPopUpTo(R.id.splash, true).build()
            findNavController()
                .navigate(R.id.topHeadLines, null, navOptions)
        }, 3000)
    }

}