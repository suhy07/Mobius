package edu.fzu.mobius.compose

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import edu.fzu.mobius.R
import edu.fzu.mobius.base.NoShadowButton
import edu.fzu.mobius.ui.common.UnspecifiedIcon

@Composable
fun CloseButton(
    onClick:()->Unit,
    modifier: Modifier = Modifier,
) {
    NoShadowButton(
        onClick = onClick,
        modifier = modifier
    ) {
        UnspecifiedIcon(
            painter = painterResource(id = R.mipmap.close_icon),
            modifier = Modifier
                .width(20.dp)
                .height(20.dp)
        )
    }
}

@Preview
@Composable
fun PreviewCloseButton(){
    CloseButton({})
}