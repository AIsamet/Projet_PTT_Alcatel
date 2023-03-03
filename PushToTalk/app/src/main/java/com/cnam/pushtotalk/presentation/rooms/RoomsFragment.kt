package com.cnam.pushtotalk.presentation.rooms

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cnam.pushtotalk.R
import com.cnam.pushtotalk.domain.rooms.Room

class RoomsFragment: Fragment(R.layout.canal_list) {
    private val viewModel: RoomsViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getRooms()
        viewModel.rooms.observe(viewLifecycleOwner) { rooms ->
            val recyclerview = view.findViewById<RecyclerView>(R.id.recyclerCanalList)
            recyclerview.layoutManager = LinearLayoutManager(requireContext())
            val adapter = CanalListAdapter(rooms) { onRoomClicked(it) }
            recyclerview.adapter = adapter
        }

        viewModel.error.observe(viewLifecycleOwner) {
            Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
        }
    }

    private fun onRoomClicked(room: Room) {
        val action = RoomsFragmentDirections.actionRoomsFragmentToTalkFragment(room.id, room.name)
        findNavController().navigate(action)
    }
}