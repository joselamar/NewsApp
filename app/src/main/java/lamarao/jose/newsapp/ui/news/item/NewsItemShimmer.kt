package lamarao.jose.newsapp.ui.news.item

import androidx.compose.animation.animateColor
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun NewsItemShimmer() {
    val color by animateColorBetween(Color.LightGray.copy(0.5f), Color.LightGray.copy(0.8f))
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(all = 10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Spacer(
            modifier = Modifier
                .size(95.dp)
                .padding(8.dp)
                .clip(RoundedCornerShape(8.dp))
                .drawBehind {
                    drawRect(color)
                }
        )
        Spacer(modifier = Modifier.width(20.dp))
        Column(
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxHeight(0.9f)
        ) {
            Spacer(
                modifier = Modifier
                    .height(7.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .fillMaxWidth(fraction = 0.3f)
                    .drawBehind {
                        drawRect(color)
                    }
            )
            Spacer(modifier = Modifier.padding(4.dp))
            Spacer(
                modifier = Modifier
                    .height(20.dp)
                    .clip(RoundedCornerShape(3.dp))
                    .fillMaxWidth(fraction = 0.96f)
                    .drawBehind {
                        drawRect(color)
                    }
            )
            Spacer(modifier = Modifier.padding(2.dp))
            Spacer(
                modifier = Modifier
                    .height(20.dp)
                    .clip(RoundedCornerShape(3.dp))
                    .fillMaxWidth(fraction = 0.7f)
                    .drawBehind {
                        drawRect(color)
                    }
            )
            Spacer(modifier = Modifier.padding(4.dp))
            Spacer(
                modifier = Modifier
                    .height(10.dp)
                    .clip(RoundedCornerShape(3.dp))
                    .fillMaxWidth(fraction = 0.6f)
                    .drawBehind {
                        drawRect(color)
                    }
            )
        }
    }
}

@Composable
private fun animateColorBetween(
    initialValue: Color,
    targetValue: Color
): State<Color> {
    val infiniteTransition = rememberInfiniteTransition()
    return infiniteTransition.animateColor(
        initialValue = initialValue,
        targetValue = targetValue,
        animationSpec = infiniteRepeatable(
            animation = tween(350),
            repeatMode = RepeatMode.Reverse
        )
    )
}
