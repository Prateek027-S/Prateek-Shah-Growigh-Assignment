package com.prateekshah.prateekshahgrowghassignment.data

class VideoDataSource {
    fun loadVideoIds(): List<VideoModel> {
        return listOf(
            VideoModel("https://vod.api.video/vod/vi5Ip5AUurYonZrmy1DEpyk/mp4/source.mp4", "Title1", "Desc of Video1"),
            VideoModel("https://vod.api.video/vod/vi7QYzMKMGB02UjdLnN2QN9Z/mp4/source.mp4", "Title2", "Desc of Video2"),
            VideoModel("https://vod.api.video/vod/vi2QqeQn62NcKviLaEdBNxmN/mp4/source.mp4", "Title3", "Desc of Video3"),
            VideoModel("https://vod.api.video/vod/vi1ECSpzymGEx3m5MJ5wpY1U/mp4/source.mp4", "Title4", "Desc of Video4"),
            VideoModel("https://vod.api.video/vod/vi7Fyzci0D1NygNYqVR4VxvH/mp4/source.mp4", "Title5", "Desc of Video5"),
        )
    }
}