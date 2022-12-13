package lamarao.jose.newsapp.ui.news.header

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.material.ScrollableTabRow
import androidx.compose.material.Tab
import androidx.compose.material.TabRowDefaults
import androidx.compose.material.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import lamarao.jose.newsapp.entities.Tabs

@Composable
fun NewsHeaderSection(
    selectedIndex: Int,
    onTabClick: (Int) -> Unit,
    tabs: List<Tabs>
) {
    ScrollableTabRow(
        selectedTabIndex = selectedIndex,
        backgroundColor = colors.primaryVariant,
        contentColor = colors.primary,
        divider = {},
        indicator = { tabPositions ->
            TabRowDefaults.Indicator(
                modifier = Modifier
                    .tabIndicatorOffset(tabPositions[selectedIndex])
                    .padding(horizontal = 35.dp)
                    .background(color = colors.primary, shape = RoundedCornerShape(8.dp)),
                height = 1.dp
            )
        }
    ) {
        tabs.forEachIndexed { tabIndex, tab ->
            Tab(
                modifier = Modifier
                    .clip(RoundedCornerShape(25.dp)),
                selected = selectedIndex == tabIndex,
                onClick = { onTabClick(tabIndex) },
                text = {
                    Text(
                        text = stringResource(id = tab.stringRes),
                        fontWeight = FontWeight.Bold
                    )
                }
            )
        }
    }
}
