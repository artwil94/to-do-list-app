package com.example.todolistapp.ui.theme

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.view.WindowCompat
import com.example.todolistapp.R

private val DarkColorScheme = darkColorScheme(
    primary = Purple80,
    secondary = PurpleGrey80,
    tertiary = Pink80
)

private val LightColorScheme = lightColorScheme(
    primary = Purple40,
    secondary = PurpleGrey40,
    tertiary = Pink40

    /* Other default colors to override
    background = Color(0xFFFFFBFE),
    surface = Color(0xFFFFFBFE),
    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = Color.White,
    onBackground = Color(0xFF1C1B1F),
    onSurface = Color(0xFF1C1B1F),
    */
)

@Composable
fun ToDoListAppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.primary.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = darkTheme
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}

object ToDoTheme {
    val tDTypography: TdTypography
        @Composable get() = TdTypography()

    val tDShapes: TdShapes
        @Composable get() = TdShapes()
    val fonts: Fonts = Fonts()
    val tDDimensions: TdDimensions
        @Composable get() = TdDimensions()
    val tDColors: TdColors
        @Composable get() = TdColors()
}

data class TdTypography(
    val inputField: TextStyle = TextStyle(
        fontSize = 16.sp,
        fontFamily = ToDoTheme.fonts.robotoLight,
        fontWeight = FontWeight(300),
        color = Color(0xFF34303D),
        platformStyle = PlatformTextStyle(
            includeFontPadding = false
        ),
    ),
    val bottomBarLabelSelect: TextStyle = TextStyle(
        fontSize = 10.sp,
        lineHeight = 18.sp,
        fontFamily = ToDoTheme.fonts.robotoLight,
        fontWeight = FontWeight(400),
        color = Color.White,
        textAlign = TextAlign.Center,
    ),
    val bottomBarLabelUnSelect: TextStyle = TextStyle(
        fontSize = 10.sp,
        lineHeight = 18.sp,
        fontFamily = ToDoTheme.fonts.robotoMedium,
        fontWeight = FontWeight(400),
        color = Color(0xFF333333),
        textAlign = TextAlign.Center,
    ),
    val toolbar: TextStyle = TextStyle(
        fontSize = 24.sp,
        lineHeight = 28.sp,
        fontWeight = FontWeight(550),
        color = Color(0xFF333333),
        fontFamily = ToDoTheme.fonts.freigeistMedium
    ),
    val taskTitle: TextStyle = TextStyle(
        fontSize = 18.sp,
        lineHeight = 24.sp,
        fontFamily = ToDoTheme.fonts.freigeistMedium,
        fontWeight = FontWeight(550),
        color = Color(0xFF333333),
    ),
    val taskDescription: TextStyle = TextStyle(
        fontSize = 12.sp,
        lineHeight = 18.sp,
        fontFamily = ToDoTheme.fonts.robotoLight,
        fontWeight = FontWeight(300),
        color = Color(0xFF333333)
    ),
    val screenHeader: TextStyle = TextStyle(
        fontSize = 40.sp,
        lineHeight = 40.sp,
        fontFamily = ToDoTheme.fonts.freigeistMedium,
        fontWeight = FontWeight(550),
        color = Color.White,
    ),
    val confirmationDialogTitle: TextStyle = TextStyle(
        fontSize = 20.sp,
        lineHeight = 28.sp,
        fontWeight = FontWeight(550),
        color = Color.White,
        fontFamily = ToDoTheme.fonts.freigeistMedium,
        textAlign = TextAlign.Center
    ),
    val successDialogTitle: TextStyle = TextStyle(
        fontSize = 20.sp,
        lineHeight = 28.sp,
        fontWeight = FontWeight(550),
        color = Color(0xFF333333),
        fontFamily = ToDoTheme.fonts.freigeistMedium,
        textAlign = TextAlign.Center
    ),
    val confirmationDialogMessage: TextStyle = TextStyle(
        fontSize = 16.sp,
        lineHeight = 24.sp,
        fontWeight = FontWeight(300),
        color = Color.White,
        fontFamily = ToDoTheme.fonts.robotoLight,
        textAlign = TextAlign.Center
    ),
    val dialogMessageInverted: TextStyle = TextStyle(
        fontSize = 16.sp,
        lineHeight = 24.sp,
        fontWeight = FontWeight(300),
        color = Color(0xFF333333),
        fontFamily = ToDoTheme.fonts.robotoLight,
        textAlign = TextAlign.Center
    ),
    val actionButton: TextStyle = TextStyle(
        fontSize = 16.sp,
        lineHeight = 22.sp,
        fontFamily = ToDoTheme.fonts.freigeistMedium,
        fontWeight = FontWeight(550),
        color = Color(0xFF333333),
    ),
    val actionButtonWhite: TextStyle = TextStyle(
        fontSize = 16.sp,
        lineHeight = 22.sp,
        fontFamily = ToDoTheme.fonts.freigeistMedium,
        fontWeight = FontWeight(550),
        color = Color.White,
    ),
    val inputLabel: TextStyle = TextStyle(
        fontSize = 14.sp,
        lineHeight = 20.sp,
        fontWeight = FontWeight(300),
        color = Color(0xFF696878),
        fontFamily = ToDoTheme.fonts.robotoLight
    ),
)

data class Fonts(
    val freigeistMedium: FontFamily = FontFamily(Font(R.font.freigeist_xconmedium)),
    val robotoBold: FontFamily = FontFamily(Font(R.font.roboto_bold)),
    val robotoLight: FontFamily = FontFamily(Font(R.font.roboto_light)),
    val robotoMedium: FontFamily = FontFamily(Font(R.font.roboto_medium)),
)

data class TdShapes(
    val inputField: Shape = RoundedCornerShape(10.dp),
    val cardItem: Shape = RoundedCornerShape(10.dp),
    val confirmationDialog: Shape = RoundedCornerShape(8.dp),
    val tabShape: Shape = RoundedCornerShape(size = 1000.dp),
)

data class TdDimensions(
    val paddingXs: Dp = 4.dp,
    val paddingS: Dp = 8.dp,
    val paddingL: Dp = 12.dp,
    val padding: Dp = 16.dp,
    val paddingMedium: Dp = 20.dp,
    val paddingXL: Dp = 24.dp,
    val paddingXXL: Dp = 30.dp,
    val screenHeaderHeight: Dp = 120.dp,
    val dialogPadding: Dp = 50.dp,
    val priorityIndicator: Dp = 16.dp
)

data class TdColors(
    val textStandard: Color = Color(0xFF333333),
    val shadow: Color = Color(0x0D000000),
    val backgroundScreen: Color = Color(0xFFECECEC),
    val bottomBar: Color = Color(0xFF5C6BC0),
    val selectedItem: Color = Color(0xFF4DB6AC),
    val screenHeader: Color = Color(0xFF3F51B5),
    val actionButton: Color = bottomBar,
    val mediumPriority: Color = Color(0xFFFFCA28)
)