package com.kohlwage.ngmovielist.pictures

import android.widget.ImageView

interface PictureLoader {

    fun loadSmallPictureFromToken(imageView: ImageView, token: String?)

    fun loadOriginalPictureFromToken(imageView: ImageView, token: String?)
}