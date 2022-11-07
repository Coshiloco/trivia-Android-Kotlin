package com.example.android.navigation

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.android.navigation.databinding.FragmentTitleBinding

/**
 * A simple [Fragment] subclass.
 * Use the [TitleFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class TitleFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        val binding: FragmentTitleBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_title, container, false
        )
        binding.playButton.setOnClickListener { v:View ->
            v.findNavController().navigate(TitleFragmentDirections.actionTitleFragment2ToGameFragment())
        }
        // Para ponerle el menu
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.overflow_menu, menu)
    }

    // Para ver si se ha seleciconado esa opcion
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle item selection
        return when (item.itemId) {
            R.id.aboutFragment -> {
                return NavigationUI.onNavDestinationSelected(
                    item,
                    view!!.findNavController()
                )
            }
            R.id.rulesFragment -> {
                return NavigationUI.onNavDestinationSelected(
                    item,
                    view!!.findNavController()
                )
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}