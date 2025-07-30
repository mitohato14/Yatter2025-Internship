import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LifecycleEventEffect
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.dmm.bootcamp.yatter2025.ui.timeline.PublicTimelineTemplate
import com.dmm.bootcamp.yatter2025.ui.timeline.PublicTimelineViewModel
import org.koin.androidx.compose.getViewModel

@Composable
fun PublicTimelinePage(
    publicTimelineViewModel: PublicTimelineViewModel = getViewModel(),
) {
    val uiState by publicTimelineViewModel.uiState.collectAsStateWithLifecycle()

    LifecycleEventEffect(event = Lifecycle.Event.ON_RESUME) {
        publicTimelineViewModel.onResume()
    }

    PublicTimelineTemplate(
        yweetList = uiState.yweetList,
        isLoading = uiState.isLoading,
        isRefreshing = uiState.isRefreshing,
        onRefresh = publicTimelineViewModel::onRefresh,
    )
}