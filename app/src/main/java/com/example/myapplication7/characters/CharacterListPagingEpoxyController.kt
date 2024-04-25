package com.example.myapplication7.characters

import com.airbnb.epoxy.EpoxyModel
import com.airbnb.epoxy.paging.PagedListEpoxyController
import com.example.myapplication7.R
import com.example.myapplication7.databinding.ModelCharacterListItemBinding
import com.example.myapplication7.epoxy.ViewBindingKotlinModel
import com.example.myapplication7.network.response.GetCharacterByIdResponse
import com.squareup.picasso.Picasso

class CharacterListPagingEpoxyController(

    private val onCharacterSelected: (Int) -> Unit
): PagedListEpoxyController<GetCharacterByIdResponse>() {

    override fun buildItemModel(
        currentPosition: Int,
        item: GetCharacterByIdResponse?
    ): EpoxyModel<*> {
        return  CharacterGridItemEpoxyModel(
            characterId = item!!.id,
            imageUrl = item.image,
            name = item.name,
            status = item.status,
            onCharacterSelected = onCharacterSelected
            ).id(item.id)
    }

    data class CharacterGridItemEpoxyModel(

        val imageUrl: String,
        val name: String,
        val status: String,
        val onCharacterSelected: (Int) -> Unit,
        val characterId: Int

    ): ViewBindingKotlinModel<ModelCharacterListItemBinding>(R.layout.model_character_list_item) {

        override fun ModelCharacterListItemBinding.bind() {
            Picasso.get().load(imageUrl).into(characterImageView)
            characterNameTextView.text = name
            characterStatusTextView.text = status

            root.setOnClickListener {
                onCharacterSelected(characterId)
            }

        }
    }

}