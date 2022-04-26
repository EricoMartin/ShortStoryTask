package com.basebox.shortstoryapp

import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import android.os.Bundle
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.animation.doOnEnd
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.basebox.shortstoryapp.databinding.FragmentFirstBinding

class FirstFragment : Fragment() {

    private lateinit var binding: FragmentFirstBinding
    lateinit var sun: ImageView
    lateinit var man: ImageView
    private lateinit var scaleX: PropertyValuesHolder
    private lateinit var scaleY: PropertyValuesHolder
    private lateinit var animator: ObjectAnimator
    private var count = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
//            param1 = it.getString(ARG_PARAM1)
//            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentFirstBinding.inflate(layoutInflater, container, false)
        sun = binding.imageSun
        man = binding.imageMan
        startAnimationWithTimer()
        return binding.root
    }

    fun startAnimationWithTimer() {
        scaleX = PropertyValuesHolder.ofFloat(View.SCALE_X, 0.5f)
        scaleY = PropertyValuesHolder.ofFloat(View.SCALE_Y, 0.5f)
        animator = ObjectAnimator.ofPropertyValuesHolder(
            sun, scaleX, scaleY
        )

        val animatorTranslate = ObjectAnimator.ofFloat(
            man, View.TRANSLATION_X,
            0f, 200f, 500f, 700f
        )
        animatorTranslate.repeatCount = 0

        object : CountDownTimer(5000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                animator.repeatCount = 1
                animator.repeatMode = ObjectAnimator.REVERSE
                animator.start()
                count++
            }

            override fun onFinish() {

//                animator.isPaused
                animatorTranslate.start()
                animatorTranslate.doOnEnd {
                    Thread.sleep(2000)
                    findNavController().navigate(R.id.secondFragment)
                }

//


            }
        }.start()

    }

//    companion object {
//        /**
//         * Use this factory method to create a new instance of
//         * this fragment using the provided parameters.
//         *
//         * @param param1 Parameter 1.
//         * @param param2 Parameter 2.
//         * @return A new instance of fragment FirstFragment.
//         */
//        // TODO: Rename and change types and number of parameters
//        @JvmStatic
//        fun newInstance(param1: String, param2: String) =
//            FirstFragment().apply {
//                arguments = Bundle().apply {
//                    putString(ARG_PARAM1, param1)
//                    putString(ARG_PARAM2, param2)
//                }
//            }
//    }
}