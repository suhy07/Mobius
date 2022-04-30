package edu.fzu.mobius.ui.common

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.*
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import kotlin.math.sqrt

@Composable
fun NoShadowBottomAppBar(
    modifier: Modifier = Modifier,
    backgroundColor: Color =  Color.Unspecified,
    contentColor: Color = contentColorFor(backgroundColor),
    cutoutShape: Shape? = null,
    elevation: Dp = 0.dp,
    contentPadding: PaddingValues = PaddingValues( start = 0.dp, top = 0.dp, end = 0.dp, bottom = 0.dp),
    content: @Composable RowScope.() -> Unit
) {
    val fabPlacement = LocalFabPlacement.current
    val shape = if (cutoutShape != null && fabPlacement?.isDocked == true) {
        BottomAppBarCutoutShape(cutoutShape, fabPlacement)
    } else {
        RectangleShape
    }
    AppBar(
        backgroundColor,
        contentColor,
        elevation,
        contentPadding,
        shape,
        modifier,
        content
    )
}

@Composable
private fun AppBar(
    backgroundColor: Color,
    contentColor: Color,
    elevation: Dp,
    contentPadding: PaddingValues,
    shape: Shape,
    modifier: Modifier = Modifier,
    content: @Composable RowScope.() -> Unit
) {
    Surface(
        color = backgroundColor,
        contentColor = contentColor,
        elevation = elevation,
        shape = shape,
        modifier = modifier
    ) {
        CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
            Row(
                Modifier.fillMaxWidth()
                    .padding(contentPadding)
                    .height(AppBarHeight),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically,
                content = content
            )
        }
    }
}

private val AppBarHeight = 56.dp

internal val LocalFabPlacement = staticCompositionLocalOf<FabPlacement?> { null }

@Immutable
internal class FabPlacement(
    val isDocked: Boolean,
    val left: Int,
    val width: Int,
    val height: Int
)

private data class BottomAppBarCutoutShape(
    val cutoutShape: Shape,
    val fabPlacement: FabPlacement
) : Shape {

    override fun createOutline(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Density
    ): Outline {
        val boundingRectangle = Path().apply {
            addRect(Rect(0f, 0f, size.width, size.height))
        }
        val path = Path().apply {
            addCutoutShape(layoutDirection, density)
            // Subtract this path from the bounding rectangle
            op(boundingRectangle, this, PathOperation.Difference)
        }
        return Outline.Generic(path)
    }

    /**
     * Adds the filled [cutoutShape] to the [Path]. The path can the be subtracted from the main
     * rectangle path used for the app bar, to create the resulting cutout shape.
     */
    private fun Path.addCutoutShape(layoutDirection: LayoutDirection, density: Density) {
        // The gap on all sides between the FAB and the cutout
        val cutoutOffset = with(density) { BottomAppBarCutoutOffset.toPx() }

        val cutoutSize = Size(
            width = fabPlacement.width + (cutoutOffset * 2),
            height = fabPlacement.height + (cutoutOffset * 2)
        )

        val cutoutStartX = fabPlacement.left - cutoutOffset
        val cutoutEndX = cutoutStartX + cutoutSize.width

        val cutoutRadius = cutoutSize.height / 2f
        // Shift the cutout up by half its height, so only the bottom half of the cutout is actually
        // cut into the app bar
        val cutoutStartY = -cutoutRadius

        addOutline(cutoutShape.createOutline(cutoutSize, layoutDirection, density))
        translate(Offset(cutoutStartX, cutoutStartY))

        // TODO: consider exposing the custom cutout shape instead of just replacing circle shapes?
        if (cutoutShape == CircleShape) {
            val edgeRadius = with(density) { BottomAppBarRoundedEdgeRadius.toPx() }
            // TODO: possibly support providing a custom vertical offset?
            addRoundedEdges(cutoutStartX, cutoutEndX, cutoutRadius, edgeRadius, 0f)
        }
    }

    /**
     * Adds rounded edges to the [Path] representing a circular cutout in a BottomAppBar.
     *
     * Adds a curve for the left and right edges, with a straight line drawn between them - this
     * combined with the cutout shape results in the overall cutout path that can be subtracted
     * from the bounding rect of the app bar.
     *
     * @param cutoutStartPosition the absolute start position of the cutout
     * @param cutoutEndPosition the absolute end position of the cutout
     * @param cutoutRadius the radius of the cutout's circular edge - for a typical circular FAB
     * this will just be the radius of the circular cutout, but in the case of an extended FAB, we
     * can model this as two circles on either side attached to a rectangle.
     * @param roundedEdgeRadius how far from the points where the cutout intersects with the app bar
     * should the rounded edges be drawn to.
     * @param verticalOffset how far the app bar is from the center of the cutout circle
     */
    private fun Path.addRoundedEdges(
        cutoutStartPosition: Float,
        cutoutEndPosition: Float,
        cutoutRadius: Float,
        roundedEdgeRadius: Float,
        verticalOffset: Float
    ) {
        // Where the cutout intersects with the app bar, as if the cutout is not vertically aligned
        // with the app bar, the intersect will not be equal to the radius of the circle.
        val appBarInterceptOffset = calculateCutoutCircleYIntercept(cutoutRadius, verticalOffset)
        val appBarInterceptStartX = cutoutStartPosition + (cutoutRadius + appBarInterceptOffset)
        val appBarInterceptEndX = cutoutEndPosition - (cutoutRadius + appBarInterceptOffset)

        // How far the control point is away from the cutout intercept. We set this to be as small
        // as possible so that we have the most 'rounded' curve.
        val controlPointOffset = 1f

        // How far the control point is away from the center of the radius of the cutout
        val controlPointRadiusOffset = appBarInterceptOffset - controlPointOffset

        // The coordinates offset from the center of the radius of the cutout, where we should
        // draw the curve to
        val (curveInterceptXOffset, curveInterceptYOffset) = calculateRoundedEdgeIntercept(
            controlPointRadiusOffset,
            verticalOffset,
            cutoutRadius
        )

        // Convert the offset relative to the center of the cutout circle into an absolute
        // coordinate, by adding the radius of the shape to get a pure relative offset from the
        // leftmost edge, and then positioning it next to the cutout
        val curveInterceptStartX = cutoutStartPosition + (curveInterceptXOffset + cutoutRadius)
        val curveInterceptEndX = cutoutEndPosition - (curveInterceptXOffset + cutoutRadius)

        // Convert the curveInterceptYOffset which is relative to the center of the cutout, to an
        // absolute position
        val curveInterceptY = curveInterceptYOffset - verticalOffset

        // Where the rounded edge starts
        val roundedEdgeStartX = appBarInterceptStartX - roundedEdgeRadius
        val roundedEdgeEndX = appBarInterceptEndX + roundedEdgeRadius

        moveTo(roundedEdgeStartX, 0f)
        quadraticBezierTo(
            appBarInterceptStartX - controlPointOffset,
            0f,
            curveInterceptStartX,
            curveInterceptY
        )
        lineTo(curveInterceptEndX, curveInterceptY)
        quadraticBezierTo(appBarInterceptEndX + controlPointOffset, 0f, roundedEdgeEndX, 0f)
        close()
    }
}
private val BottomAppBarCutoutOffset = 8.dp
private val BottomAppBarRoundedEdgeRadius = 4.dp

internal inline fun calculateCutoutCircleYIntercept(
    cutoutRadius: Float,
    verticalOffset: Float
): Float {
    return -sqrt(square(cutoutRadius) - square(verticalOffset))
}


@Suppress("UnnecessaryVariable")
internal fun calculateRoundedEdgeIntercept(
    controlPointX: Float,
    verticalOffset: Float,
    radius: Float
): Pair<Float, Float> {
    val a = controlPointX
    val b = verticalOffset
    val r = radius

    // expands to a2b2r2 + b4r2 - b2r4
    val discriminant = square(b) * square(r) * (square(
        a
    ) + square(b) - square(r))
    val divisor = square(a) + square(b)
    // the '-b' part of the quadratic solution
    val bCoefficient = a * square(r)

    // Two solutions for the x coordinate relative to the midpoint of the circle
    val xSolutionA = (bCoefficient - sqrt(discriminant)) / divisor
    val xSolutionB = (bCoefficient + sqrt(discriminant)) / divisor

    // Get y coordinate from r2 = x2 + y2 -> y2 = r2 - x2
    val ySolutionA = sqrt(
        square(r) - square(
            xSolutionA
        )
    )
    val ySolutionB = sqrt(
        square(r) - square(
            xSolutionB
        )
    )

    // If the vertical offset is 0, the vertical center of the circle lines up with the top edge of
    // the bottom app bar, so both solutions are identical.
    // If the vertical offset is not 0, there are two distinct solutions: one that will meet in the
    // top half of the circle, and one that will meet in the bottom half of the circle. As the app
    // bar is always on the bottom edge of the circle, we are always interested in the bottom half
    // solution. To calculate which is which, it depends on whether the vertical offset is positive
    // or negative.
    val (xSolution, ySolution) = if (b > 0) {
        // When the offset is positive, the top edge of the app bar is below the center of the
        // circle. The largest solution will be the one closest to the bottom of the circle, so we
        // pick that.
        if (ySolutionA > ySolutionB) xSolutionA to ySolutionA else xSolutionB to ySolutionB
    } else {
        // When the offset is negative, the top edge of the app bar is above the center of the
        // circle. The smallest solution will be the one closest to the top of the circle, so we
        // pick that.
        if (ySolutionA < ySolutionB) xSolutionA to ySolutionA else xSolutionB to ySolutionB
    }

    // If the calculated x coordinate is further away from the origin than the control point, the
    // curve will fold back on itself. In this scenario, we actually join the circle above the
    // center, so invert the y coordinate.
    val adjustedYSolution = if (xSolution < controlPointX) -ySolution else ySolution
    return xSolution to adjustedYSolution
}
@Suppress("NOTHING_TO_INLINE")
private inline fun square(x: Float) = x * x