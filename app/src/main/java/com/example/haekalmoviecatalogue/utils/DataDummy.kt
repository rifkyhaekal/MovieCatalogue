package com.example.haekalmoviecatalogue.utils

import com.example.haekalmoviecatalogue.data.source.local.entity.*
import com.example.haekalmoviecatalogue.data.source.remote.response.*
import kotlin.math.floor

object DataDummy {

    fun generateDummyPopularMovies(success: Boolean): PopularMovieEntity {
        val popularMovieResults = ArrayList<MovieItemEntity>()

        popularMovieResults.add(
            MovieItemEntity(
                19610,
                "/wrFpXMNBRj2PBiN4Z5kix51XaIZ.jpg"
            )
        )
        popularMovieResults.add(
            MovieItemEntity(
                17189,
                "/xRWht48C2V8XNfzvPehyClOvDni.jpg"
            )
        )
        popularMovieResults.add(
            MovieItemEntity(
                572802,
                "/xLPffWMhMj1l50ND3KchMjYoKmE.jpg"
            )
        )
        popularMovieResults.add(
            MovieItemEntity(
                424694,
                "/lHu1wtNaczFPGFDTrjCSzeLPTKN.jpg"
            )
        )
        popularMovieResults.add(
            MovieItemEntity(
                480530,
                "/v3QyboWRoA4O9RbcsqH8tJMe8EB.jpg"
            )
        )
        popularMovieResults.add(
            MovieItemEntity(
                438650,
                "/hXgmWPd1SuujRZ4QnKLzrj79PAw.jpg"
            )
        )
        popularMovieResults.add(
            MovieItemEntity(
                663124,
                "/svIDTNUoajS8dLEo7EosxvyAsgJ.jpg"
            )
        )
        popularMovieResults.add(
            MovieItemEntity(
                654571,
                "/xvx4Yhf0DVH8G4LzNISpMfFBDy2.jpg"
            )
        )
        popularMovieResults.add(
            MovieItemEntity(
                299536,
                "/7WsyChQLEftFiDOVTGkv3hFpyyt.jpg"
            )
        )
        popularMovieResults.add(
            MovieItemEntity(
                193006,
                "/9NQh0CGCuvRQoZO3Nhwl7huGSOg.jpg"
            )
        )

        return PopularMovieEntity(success, popularMovieResults)
    }

    fun generateDummyPopularTvShows(success: Boolean): PopularTvShowEntity {
        val popularTvShowResults = ArrayList<TvShowItemEntity>()

        popularTvShowResults.add(
            TvShowItemEntity(
                1412,
                "/gKG5QGz5Ngf8fgWpBsWtlg5L2SF.jpg"
            )
        )
        popularTvShowResults.add(
            TvShowItemEntity(
                79501,
                "/kOAn06LmRCg4YiSStwrGL6UOQ3a.jpg"
            )
        )
        popularTvShowResults.add(
            TvShowItemEntity(
                118011,
                "/xs1BRXnY5kStzwdxyrl2HYJOJCq.jpg"
            )
        )
        popularTvShowResults.add(
            TvShowItemEntity(
                1434,
                "/9RBeCo8QSaoJLmmuzlwzVH3Hi12.jpg"
            )
        )
        popularTvShowResults.add(
            TvShowItemEntity(
                236,
                "/fi1GEdCbyWRDHpyJcB25YYK7fh4.jpg"
            )
        )
        popularTvShowResults.add(
            TvShowItemEntity(
                15185,
                "/4XddcRDtnNjYmLRMYpbrhFxsbuq.jpg"
            )
        )
        popularTvShowResults.add(
            TvShowItemEntity(
                79897,
                "/zPIug5giU8oug6Xes5K1sTfQJxY.jpg"
            )
        )
        popularTvShowResults.add(
            TvShowItemEntity(
                63394,
                "/pe10EUjgO2jgwiu01MAv9l3IjxG.jpg"
            )
        )
        popularTvShowResults.add(
            TvShowItemEntity(
                62127,
                "/4l6KD9HhtD6nCDEfg10Lp6C6zah.jpg"
            )
        )
        popularTvShowResults.add(
            TvShowItemEntity(
                124271,
                "/lSTchtc26YNdOjdKvZtLs22SokL.jpg"
            )
        )

        return PopularTvShowEntity(success, popularTvShowResults)
    }

    fun generateDummyMovieDetail(): MovieDetailEntity {
        return MovieDetailEntity(
            19610,
            "A Star Is Born",
            "Drama, Music, Romance",
            "Seasoned musician Jackson Maine discovers — and falls in love with — struggling artist Ally. She has just about given up on her dream to make it big as a singer — until Jack coaxes her into the spotlight. But even as Ally's career takes off, the personal side of their relationship is breaking down, as Jack fights an ongoing battle with his own internal demons.",
            "2h 19m",
            5.9,
            "10/05/2018",
            "Released",
            "/oVpUzCimMZ0ecni7lDuYPuRdxoQ.jpg"
        )
    }

    fun generateDummyTvShowDetail(): TvShowDetailEntity {
        return TvShowDetailEntity(
            1412,
            "Arrow",
            "Spoiled billionaire playboy Oliver Queen is missing and presumed dead when his yacht is lost at sea. He returns five years later a changed man, determined to clean up the city as a hooded vigilante armed with a bow.",
            "Ended",
            "Scripted",
            "Crime, Drama, Mystery, Action & Adventure",
            "The CW",
            6.7,
            "/gKG5QGz5Ngf8fgWpBsWtlg5L2SF.jpg"
        )
    }

    fun generateRemoteDummyPopularMovies(): List<MovieItem> {
        val popularMovies = ArrayList<MovieItem>()

        popularMovies.add(
            MovieItem(
                19610,
                "/wrFpXMNBRj2PBiN4Z5kix51XaIZ.jpg"
            )
        )
        popularMovies.add(
            MovieItem(
                17189,
                "/xRWht48C2V8XNfzvPehyClOvDni.jpg"
            )
        )
        popularMovies.add(
            MovieItem(
                572802,
                "/xLPffWMhMj1l50ND3KchMjYoKmE.jpg"
            )
        )
        popularMovies.add(
            MovieItem(
                424694,
                "/lHu1wtNaczFPGFDTrjCSzeLPTKN.jpg"
            )
        )
        popularMovies.add(
            MovieItem(
                480530,
                "/v3QyboWRoA4O9RbcsqH8tJMe8EB.jpg"
            )
        )
        popularMovies.add(
            MovieItem(
                438650,
                "/hXgmWPd1SuujRZ4QnKLzrj79PAw.jpg"
            )
        )
        popularMovies.add(
            MovieItem(
                663124,
                "/svIDTNUoajS8dLEo7EosxvyAsgJ.jpg"
            )
        )
        popularMovies.add(
            MovieItem(
                654571,
                "/xvx4Yhf0DVH8G4LzNISpMfFBDy2.jpg"
            )
        )
        popularMovies.add(
            MovieItem(
                299536,
                "/7WsyChQLEftFiDOVTGkv3hFpyyt.jpg"
            )
        )
        popularMovies.add(
            MovieItem(
                193006,
                "/9NQh0CGCuvRQoZO3Nhwl7huGSOg.jpg"
            )
        )

        return popularMovies
    }

    fun generateRemoteDummyPopularTvShows(): List<TvShowItem> {
        val popularTvShow = ArrayList<TvShowItem>()

        popularTvShow.add(
            TvShowItem(
                1412,
                "/gKG5QGz5Ngf8fgWpBsWtlg5L2SF.jpg"
            )
        )
        popularTvShow.add(
            TvShowItem(
                79501,
                "/kOAn06LmRCg4YiSStwrGL6UOQ3a.jpg"
            )
        )
        popularTvShow.add(
            TvShowItem(
                118011,
                "/xs1BRXnY5kStzwdxyrl2HYJOJCq.jpg"
            )
        )
        popularTvShow.add(
            TvShowItem(
                1434,
                "/9RBeCo8QSaoJLmmuzlwzVH3Hi12.jpg"
            )
        )
        popularTvShow.add(
            TvShowItem(
                236,
                "/fi1GEdCbyWRDHpyJcB25YYK7fh4.jpg"
            )
        )
        popularTvShow.add(
            TvShowItem(
                15185,
                "/4XddcRDtnNjYmLRMYpbrhFxsbuq.jpg"
            )
        )
        popularTvShow.add(
            TvShowItem(
                79897,
                "/zPIug5giU8oug6Xes5K1sTfQJxY.jpg"
            )
        )
        popularTvShow.add(
            TvShowItem(
                63394,
                "/pe10EUjgO2jgwiu01MAv9l3IjxG.jpg"
            )
        )
        popularTvShow.add(
            TvShowItem(
                62127,
                "/4l6KD9HhtD6nCDEfg10Lp6C6zah.jpg"
            )
        )
        popularTvShow.add(
            TvShowItem(
                124271,
                "/lSTchtc26YNdOjdKvZtLs22SokL.jpg"
            )
        )

        return popularTvShow
    }

    fun generateRemoteDummyMovieDetail(): MovieDetailResponse {
        val genres = ArrayList<MovieGenresItem>()

        genres.add(
            MovieGenresItem("Drama", 18)
        )
        genres.add(
            MovieGenresItem("Music", 10402)
        )
        genres.add(
            MovieGenresItem("Romance", 10749)
        )

        return MovieDetailResponse(
            "Seasoned musician Jackson Maine discovers — and falls in love with — struggling artist Ally. She has just about given up on her dream to make it big as a singer — until Jack coaxes her into the spotlight. But even as Ally's career takes off, the personal side of their relationship is breaking down, as Jack fights an ongoing battle with his own internal demons.",
            "10/05/2018",
            genres,
            5.9,
            139,
            19610,
            "A Star Is Born",
            "/oVpUzCimMZ0ecni7lDuYPuRdxoQ.jpg",
            "Released"
        )
    }

    fun generateRemoteDummyTvShowDetail(): TvShowDetailResponse {
        val tvShowGenres = ArrayList<TvGenresItem>()
        val tvShowNetworks = ArrayList<NetworksItem>()

        tvShowGenres.add(
            TvGenresItem("Crime", 80)
        )
        tvShowGenres.add(
            TvGenresItem("Drama", 18)
        )
        tvShowGenres.add(
            TvGenresItem("Mystery", 9648)
        )
        tvShowGenres.add(
            TvGenresItem("Action & Adventure", 10759)
        )

        tvShowNetworks.add(
            NetworksItem("The CW", 71)
        )

        return TvShowDetailResponse(
            "Spoiled billionaire playboy Oliver Queen is missing and presumed dead when his yacht is lost at sea. He returns five years later a changed man, determined to clean up the city as a hooded vigilante armed with a bow.",
            tvShowGenres,
            6.7,
            "Arrow",
            1412,
            tvShowNetworks,
            "Scripted",
            "/gKG5QGz5Ngf8fgWpBsWtlg5L2SF.jpg",
            "Ended"
        )
    }

}