import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import com.stefita.astronautsdb.ui.theme.ColorPalette
import com.stefita.astronautsdb.ui.theme.Typography

@Composable
fun AstronautsDbTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = ColorPalette,
        typography = Typography,
        content = content
    )
}
