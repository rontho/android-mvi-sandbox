package com.sitronman.studio.thegameapp.ui.home.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sitronman.studio.thegameapp.R
import com.sitronman.studio.thegameapp.databinding.GameItemBinding
import com.sitronman.studio.thegameapp.ui.home.model.GameUiModel

class GameAdapter(private val games: ArrayList<GameUiModel>) : RecyclerView.Adapter<GameAdapter.GameViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = GameViewHolder(
        LayoutInflater.from(parent.context).inflate(
            R.layout.game_item, parent, false
        )
    )

    override fun getItemCount(): Int = games.size

    override fun onBindViewHolder(
        holder: GameViewHolder,
        position: Int
    ) = holder.bind(games[position])

    fun addData(gameList: List<GameUiModel>) {
        games.addAll(gameList)
    }

    class GameViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(game: GameUiModel) {
            GameItemBinding.bind(itemView).apply {
                name.text = game.name
                Glide.with(image.context)
                    .load(game.image)
                    .into(image)
            }
        }
    }
}