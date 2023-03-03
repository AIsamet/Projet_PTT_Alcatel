package com.cnam.pushtotalk.presentation.talk

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.content.res.AppCompatResources.getColorStateList
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.cnam.pushtotalk.R
import com.cnam.pushtotalk.databinding.FragmentTalkBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class TalkFragment : Fragment() {
    private var microphoneRequestCode = 0;
    private lateinit var binding: FragmentTalkBinding
    private val viewModel: TalkViewModel by viewModels()
    private val args: TalkFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentTalkBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.txtRoomName.text = args.roomName
    }

    override fun onStart() {
        super.onStart()
        setObservers()
        setButtonListeners()
        joinConference()
    }

    private fun setObservers() {
        viewModel.error.observe(viewLifecycleOwner) {
            Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
        }
        viewModel.conferenceJoined.observe(viewLifecycleOwner) {
            when (it) {
                true -> Toast.makeText(context, R.string.txt_conference_joined, Toast.LENGTH_SHORT)
                    .show()
                false -> Toast.makeText(context, R.string.txt_conference_left, Toast.LENGTH_SHORT)
                    .show()
            }
        }
        viewModel.isTalking.observe(viewLifecycleOwner) {
            when (it) {
                true -> binding.btnPushToTalk.imageTintList =
                    getColorStateList(requireContext(), R.color.green)
                false -> binding.btnPushToTalk.imageTintList =
                    getColorStateList(requireContext(), R.color.white)
            }
        }
    }

    private fun setButtonListeners() {
        binding.btnLeaveConference.setOnClickListener {
            viewModel.leaveConference()
            val action = TalkFragmentDirections.actionTalkFragmentToRoomsFragment()
            findNavController().navigate(action)
        }
        setPushToTalkButtonListener()
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun setPushToTalkButtonListener() {
        binding.btnPushToTalk.setOnTouchListener { _, event ->
            when (event.action) {
                android.view.MotionEvent.ACTION_DOWN -> {
                    viewModel.startToTalk(args.roomId)
                }
                android.view.MotionEvent.ACTION_UP -> {
                    viewModel.stopToTalk(args.roomId)
                }
            }
            true
        }
    }

    private fun joinConference() {
        requestAudioRecordPermission(
            this.requireActivity(), this.requireContext(), microphoneRequestCode
        )
        viewModel.joinConference(args.roomId)
    }
}