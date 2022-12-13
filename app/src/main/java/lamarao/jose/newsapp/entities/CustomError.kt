package lamarao.jose.newsapp.entities

import androidx.annotation.StringRes

data class CustomError(
    @StringRes val errorTitle: Int,
    @StringRes val errorMessage: Int
)
