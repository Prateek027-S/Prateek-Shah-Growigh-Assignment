package com.prateekshah.prateekshahgrowghassignment.data

class VideoDataSource {
    fun loadVideoIds(): List<VideoModel> {
        return listOf(
            VideoModel("https://vod.api.video/vod/vi32GHjHdRADU7s7vctDkU79/mp4/source.mp4", "Title1", "Desc of Video1"),
            VideoModel("https://vod.api.video/vod/vi5URIIWoql0wrEWcyU4W80/mp4/source.mp4", "Title2", "Desc of Video2"),
            VideoModel("https://vod.api.video/vod/vim8Z5QJqqQ9I03bjKVRFtQ/mp4/source.mp4", "Title3", "Desc of Video3"),
            VideoModel("https://vod.api.video/vod/vi43E1dP5betkQdbnYiwQRCT/mp4/source.mp4", "Title4", "Desc of Video4"),
            VideoModel("https://vod.api.video/vod/vi7fFopGLNjHmfufDjdVYQyh/mp4/source.mp4", "Title5", "Desc of Video5"),
        )
    }
}