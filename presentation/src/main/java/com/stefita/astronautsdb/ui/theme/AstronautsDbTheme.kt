import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
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
