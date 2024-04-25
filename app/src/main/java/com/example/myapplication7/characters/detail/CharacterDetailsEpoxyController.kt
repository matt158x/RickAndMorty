package com.example.myapplication7.characters.detail

import com.airbnb.epoxy.EpoxyController
import com.airbnb.epoxy.EpoxyModel
import com.example.myapplication7.R
import com.example.myapplication7.databinding.ModelCharacterDetailsDataPointBinding
import com.example.myapplication7.databinding.ModelCharacterDetailsHeaderBinding
import com.example.myapplication7.epoxy.LoadingEpoxyModel
import com.example.myapplication7.epoxy.ViewBindingKotlinModel
import com.example.myapplication7.network.response.GetCharacterByIdResponse
import com.squareup.picasso.Picasso

class CharacterDetailsEpoxyController: EpoxyController() {

    var isLoading: Boolean = true
        set(value){
            field = value
            if(field) {
                requestModelBuild()
            }
        }

    var characterResponse: GetCharacterByIdResponse? = null
        set(value){
            field = value
            if(field != null) {
                isLoading = false
                requestModelBuild()
            }
        }


    override fun buildModels() {

        if(isLoading) {
            LoadingEpoxyModel().id("loading").addTo(this)
            return
        }

        if(characterResponse == null) {
            // todo error state
            return
        }

        HeaderEpoxyModel(
            name = characterResponse!!.name,
            gender = characterResponse!!.gender,
            status = characterResponse!!.status,
            imageUrl = characterResponse!!.image
        ).id("header", "image").addTo(this)



        DataPointEpoxyModel(
            title = "Origin",
            description = characterResponse!!.origin.name
        ).id("data_point_1").addTo(this)

        DataPointEpoxyModel(
            title = "Species",
            description = characterResponse!!.species
        ).id("data_point_2").addTo(this)
    }

    data class HeaderEpoxyModel(
        val name: String,
        val gender: String,
        val status: String,
        val imageUrl: String
    ) : ViewBindingKotlinModel<ModelCharacterDetailsHeaderBinding>(R.layout.model_character_details_header){

        override fun ModelCharacterDetailsHeaderBinding.bind() {
            nameTextView.text = name
            aliveTextView.text = status

            Picasso.get().load(imageUrl).into(headerImageView)
            if(gender.equals("male", true)) {
                genderImageView.setImageResource(R.drawable.ic_male_24)
            } else {
                genderImageView.setImageResource(R.drawable.ic_female_24)
            }

        }
    }



    data class DataPointEpoxyModel(
        val title: String,
        val description: String
    ) : ViewBindingKotlinModel<ModelCharacterDetailsDataPointBinding>(R.layout.model_character_details_data_point) {

        override fun ModelCharacterDetailsDataPointBinding.bind() {
            labelTextView.text = title
            textView.text = description
        }
    }




}