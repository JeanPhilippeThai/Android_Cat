package com.ismin.android

import android.animation.ObjectAnimator
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateDecelerateInterpolator
import android.widget.Button
import android.widget.EditText
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import com.google.android.material.transition.FadeThroughProvider

class CreateAppInfoFragment : Fragment() {
    private var activity: AppInfoCreator? = null;

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_app_info, container, false)
        rootView.setOnClickListener { activity?.closeAppinfoFragment() }
        rootView.findViewById<Button>(R.id.f_create_appinfo_btn_ok).setOnClickListener {
            activity?.closeAppinfoFragment()
        }
        return rootView
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }
    override fun onAttach(context: Context) {
        if (context is AppInfoCreator) {
            activity = context
        }
        super.onAttach(context)
    }
}

interface AppInfoCreator {
    fun closeAppinfoFragment()
}