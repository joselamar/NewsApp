package lamarao.jose.newsapp.entities

import androidx.annotation.StringRes
import jose.newsapp.R

enum class Tabs(@StringRes val stringRes: Int) {
    GENERAL(R.string.tabs_general),
    ENTERTAINMENT(R.string.tabs_entertainment),
    SPORTS(R.string.tabs_sports)
}
