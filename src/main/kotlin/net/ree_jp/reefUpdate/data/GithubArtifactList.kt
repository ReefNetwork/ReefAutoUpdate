package net.ree_jp.reefUpdate.data

data class GithubArtifactList(
    val total_count: Int,
    val artifacts: List<GithubArtifact>
)

data class GithubArtifact(
    val id: Int,
    val node_id: String,
    val name: String,
    val size_in_bytes: Int,
    val url: String,
    val archive_download_url: String,
    val expired: Boolean,
    val created_at: String,
    val expires_at: String
)