package com.freecast.thatmovieapp.presentation.playvideo

import android.annotation.SuppressLint
import android.net.Uri
import android.os.Bundle
import android.util.SparseArray
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import at.huber.youtubeExtractor.VideoMeta
import at.huber.youtubeExtractor.YouTubeExtractor
import at.huber.youtubeExtractor.YtFile
import com.freecast.thatmovieapp.databinding.ActivityPlayVideoBinding
import com.freecast.thatmovieapp.presentation.playvideo.viewmodel.PlayerViewModel
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.source.MediaSource
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.freecast.thatmovieapp.data.result.Result
import com.freecast.thatmovieapp.helper.const.ExtraBundleConst
import com.freecast.thatmovieapp.helper.extension.extra
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import org.koin.androidx.viewmodel.ext.android.viewModel

private const val TAG_720 = 133
private const val TAG_1080 = 137

class PlayVideoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPlayVideoBinding

    private var player: SimpleExoPlayer? = null

    private val viewModel: PlayerViewModel by viewModel()

    private val movieId by extra<Int>(ExtraBundleConst.MOVIE_ID)
    private val isMovie by extra<Boolean>(ExtraBundleConst.IS_MOVIE)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPlayVideoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        bindViews()
        observe()
        setupRequest()
    }

    fun setupRequest() {
        movieId?.let { viewModel.getVideo(it, isMovie ?: false) } ?: kotlin.run { finish() }
    }

    private fun observe() = lifecycleScope.launchWhenCreated {
        viewModel.videoFlow.collect { result ->
            when (result) {
                is Result.Success -> onSuccess(result.data.videoKey)
                else -> {}
            }
        }
    }

    private fun bindViews() = binding.apply {
        player = SimpleExoPlayer.Builder(this@PlayVideoActivity).build()
        playerView.player = player
    }

    @SuppressLint("StaticFieldLeak")
    private fun onSuccess(videoUrl: String) {
        object : YouTubeExtractor(this) {

            override fun onExtractionComplete(
                ytFiles: SparseArray<YtFile>?, videoMeta: VideoMeta?
            ) {
                ytFiles?.let { files ->
                    try {
                        val video720 = files[TAG_720]
                        val video1080 = files[TAG_1080]
                        video1080?.let { prepareToPlayVideo(video1080.url) }
                            ?: kotlin.run { prepareToPlayVideo(video720.url) }
                    } catch (ex: java.lang.NullPointerException) {
                        ex.printStackTrace()
                    }
                }
                binding.loadingVideo.isVisible = false
            }
        }.extract(videoUrl)
    }

    private fun prepareToPlayVideo(url: String) {
        try {
            val dataSourceFactory = DefaultDataSourceFactory(this, "sample")
            val ms: MediaSource = ProgressiveMediaSource.Factory(dataSourceFactory)
                .createMediaSource(MediaItem.fromUri(Uri.parse(url)))
            player?.setMediaSource(ms)
            player?.prepare()
            player?.playWhenReady = true
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}