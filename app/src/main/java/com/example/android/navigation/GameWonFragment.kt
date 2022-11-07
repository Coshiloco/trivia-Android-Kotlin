/*
 * Copyright 2018, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.navigation

import android.content.Intent
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.core.app.ShareCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.android.navigation.databinding.FragmentGameWonBinding


class GameWonFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        val binding: FragmentGameWonBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_game_won, container, false
        )
        binding.nextMatchButton.setOnClickListener { view ->
            view.findNavController()
                .navigate(GameWonFragmentDirections.actionGameWonFragmentToGameFragment())
        }
        // Para compartir los resultados
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        // Le decimos que nos cargue en esta pantalla el menu
        inflater.inflate(R.menu.winner_menu, menu)
        // check if the activity resolves
        if (null == getShareIntent().resolveActivity(activity!!.packageManager)) {
            // hide the menu item if it doesnt resolve
            menu.findItem(R.id.share)?.isVisible = false
        }
    }

    // Funcion que devuelve el intent

    private fun getShareIntent(): Intent {
        val args = GameWonFragmentArgs.fromBundle(arguments!!)
        // Le decimos a android que registramos el Intent es decir la accion de compartir
        val shareIntent = Intent(Intent.ACTION_SEND)
        // Le decimos que tipo de informacion queremos compartir en nuestro caso texto plano
        /*Una Intent es un objeto
        de mensajería que puedes
        usar para solicitar una acción de otro componente de una app.
        *  Si la intent coincide con un filtro de intents,
        el sistema inicia ese componente y le entrega el objeto Intent.
        Si varios filtros de intents son compatibles,
        el sistema muestra un cuadro de diálogo para que el
        usuario pueda elegir la aplicación que se debe usar.*/
        return ShareCompat.IntentBuilder.from(activity!!)
            .setText(getString(R.string.share_success_text, args.numCorrect, args.numQuestions))
            .setType("text/plain")
            .intent
    }

    private fun shareSuccess() {
        // LLama a la que contiene el activity que seria nuestro activity main
        // Y le pasamos el intent
        startActivity(getShareIntent())
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Como ya hemos visto con esta antes esta es para darle
        // Acccion a nuestro menu de compartir que haga algo
        when (item.itemId) {
            R.id.share -> shareSuccess()
        }
        return super.onOptionsItemSelected(item)
    }
}
