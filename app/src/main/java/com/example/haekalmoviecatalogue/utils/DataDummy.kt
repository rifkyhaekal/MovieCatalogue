package com.example.haekalmoviecatalogue.utils

import com.example.haekalmoviecatalogue.R
import com.example.haekalmoviecatalogue.data.MovieEntity
import com.example.haekalmoviecatalogue.data.TvShowEntity


object DataDummy {

    fun generateDummyMovies(): ArrayList<MovieEntity> {
        val movies = ArrayList<MovieEntity>()

        movies.add(
            MovieEntity("m1",
        "A Star Is Born",
        "Drama, Romance",
                "Seasoned musician Jackson Maine discovers — and falls in love with — struggling artist Ally. She has just about given up on her dream to make it big as a singer — until Jack coaxes her into the spotlight. But even as Ally's career takes off, the personal side of their relationship is breaking down, as Jack fights an ongoing battle with his own internal demons.",
            "2h 16m",
            "75%",
            "10/05/2018",
            "Released",
                R.drawable.poster_a_start_is_born))
        movies.add(
            MovieEntity("m2",
                "Alita: Battle Angel",
                "Action, Science Fiction, Adventure",
                "When Alita awakens with no memory of who she is in a future world she does not recognize, she is taken in by Ido, a compassionate doctor who realizes that somewhere in this abandoned cyborg shell is the heart and soul of a young woman with an extraordinary past.",
                "2h 2m",
                "72%",
                "02/14/2019",
                "Released",
                R.drawable.poster_alita))
        movies.add(
            MovieEntity("m3",
                "Aquaman",
                "Action, Adventure, Fantasy",
                "Once home to the most advanced civilization on Earth, Atlantis is now an underwater kingdom ruled by the power-hungry King Orm. With a vast army at his disposal, Orm plans to conquer the remaining oceanic people and then the surface world. Standing in his way is Arthur Curry, Orm's half-human, half-Atlantean brother and true heir to the throne.",
                "2h 23m",
                "69%",
                "12/26/2018",
                "Released",
                R.drawable.poster_aquaman))
        movies.add(
            MovieEntity("m4",
                "Bohemian Rhapsody",
                "Music, Drama, History",
                "Singer Freddie Mercury, guitarist Brian May, drummer Roger Taylor and bass guitarist John Deacon take the music world by storm when they form the rock 'n' roll band Queen in 1970. Hit songs become instant classics. When Mercury's increasingly wild lifestyle starts to spiral out of control, Queen soon faces its greatest challenge yet – finding a way to keep the band together amid the success and excess.",
                "2h 15m",
                "80%",
                "10/05/2018",
                "Released",
                R.drawable.poster_bohemian))
        movies.add(
            MovieEntity("m5",
                "Creed II",
                "Drama",
                "Between personal obligations and training for his next big fight against an opponent with ties to his family's past, Adonis Creed is up against the challenge of his life.",
                "2h 10m",
                "69%",
                "11/21/2018",
                "Released",
                R.drawable.poster_creed))
        movies.add(
            MovieEntity("m6",
                "Cold Pursuit",
                "Action, Crime, Thriller",
                "The quiet family life of Nels Coxman, a snowplow driver, is upended after his son's murder. Nels begins a vengeful hunt for Viking, the drug lord he holds responsible for the killing, eliminating Viking's associates one by one. As Nels draws closer to Viking, his actions bring even more unexpected and violent consequences, as he proves that revenge is all in the execution.",
                "1h 59m",
                "57%",
                "02/08/2019",
                "Released",
                R.drawable.poster_cold_persuit))
        movies.add(
            MovieEntity("m7",
                "Glass",
                "Thriller, Drama, Science Fiction\n",
                "In a series of escalating encounters, former security guard David Dunn uses his supernatural abilities to track Kevin Wendell Crumb, a disturbed man who has twenty-four personalities. Meanwhile, the shadowy presence of Elijah Price emerges as an orchestrator who holds secrets critical to both men.",
                "2h 9m",
                "67%",
                "01/18/2019",
                "Released",
                R.drawable.poster_glass))
        movies.add(
            MovieEntity("m8",
                "How to Train Your Dragon: The Hidden World",
                "Animation, Family, Adventure",
                "As Hiccup fulfills his dream of creating a peaceful dragon utopia, Toothless’ discovery of an untamed, elusive mate draws the Night Fury away. When danger mounts at home and Hiccup’s reign as village chief is tested, both dragon and rider must make impossible decisions to save their kind.",
                "1h 44m",
                "78%",
                "01/09/2019",
                "Released",
                R.drawable.poster_how_to_train))
        movies.add(
            MovieEntity("m9",
                "Avengers: Infinity War",
                "Adventure, Action, Science Fiction",
                "As the Avengers and their allies have continued to protect the world from threats too large for any one hero to handle, a new danger has emerged from the cosmic shadows: Thanos. A despot of intergalactic infamy, his goal is to collect all six Infinity Stones, artifacts of unimaginable power, and use them to inflict his twisted will on all of reality. Everything the Avengers have fought for has led up to this moment - the fate of Earth and existence itself has never been more uncertain.",
                "2h 29m",
                "83%",
                "04/27/2018",
                "Released",
                R.drawable.poster_infinity_war))
        movies.add(
            MovieEntity("m10",
                "Mary Queen of Scots",
                "Drama, History",
                "In 1561, Mary Stuart, widow of the King of France, returns to Scotland, reclaims her rightful throne and menaces the future of Queen Elizabeth I as ruler of England, because she has a legitimate claim to the English throne. Betrayals, rebellions, conspiracies and their own life choices imperil both Queens. They experience the bitter cost of power, until their tragic fate is finally fulfilled.",
                "2h 4m",
                "66%",
                "12/21/2018",
                "Released",
                R.drawable.poster_marry_queen))
        return movies
    }

    fun generateDummyTvShows(): ArrayList<TvShowEntity> {
        val tvShows = ArrayList<TvShowEntity>()

        tvShows.add(
            TvShowEntity( "ts1",
                "Arrow",
                "Spoiled billionaire playboy Oliver Queen is missing and presumed dead when his yacht is lost at sea. He returns five years later a changed man, determined to clean up the city as a hooded vigilante armed with a bow.",
                "Ended",
                "Scripted",
                "Crime, Drama, Mystery, Action & Adventure",
                "The CW",
                "67%",
                R.drawable.poster_arrow))
        tvShows.add(
            TvShowEntity( "ts2",
                "Doom Patrol",
                "The Doom Patrol’s members each suffered horrible accidents that gave them superhuman abilities — but also left them scarred and disfigured. Traumatized and downtrodden, the team found purpose through The Chief, who brought them together to investigate the weirdest phenomena in existence — and to protect Earth from what they find.",
                "Returning Series",
                "Scripted",
                "Sci-Fi & Fantasy, Drama",
                "MBO MAX",
                "77%",
                R.drawable.poster_doom_patrol))
        tvShows.add(
            TvShowEntity( "ts3",
                "Dragon Ball",
                "Long ago in the mountains, a fighting master known as Gohan discovered a strange boy whom he named Goku. Gohan raised him and trained Goku in martial arts until he died. The young and very strong boy was on his own, but easily managed. Then one day, Goku met a teenage girl named Bulma, whose search for the mystical Dragon Balls brought her to Goku's home. Together, they set off to find all seven and to grant her wish.",
                "Ended",
                "Scripted",
                "Animation, Action & Adventure, Sci-Fi & Fantasy",
                "Fuji TV",
                "82%",
                R.drawable.poster_dragon_ball))
        tvShows.add(
            TvShowEntity( "ts4",
                "Family Guy",
                "Sick, twisted, politically incorrect and Freakin' Sweet animated series featuring the adventures of the dysfunctional Griffin family. Bumbling Peter and long-suffering Lois have three kids. Stewie (a brilliant but sadistic baby bent on killing his mother and taking over the world), Meg (the oldest, and is the most unpopular girl in town) and Chris (the middle kid, he's not very bright but has a passion for movies). The final member of the family is Brian - a talking dog and much more than a pet, he keeps Stewie in check whilst sipping Martinis and sorting through his own life issues.",
                "Returning Series",
                "Scripted",
                "Animation, Comedy",
                "FOX",
                "71%",
                R.drawable.poster_family_guy))
        tvShows.add(
            TvShowEntity( "ts5",
                "The Flash",
                "After a particle accelerator causes a freak storm, CSI Investigator Barry Allen is struck by lightning and falls into a coma. Months later he awakens with the power of super speed, granting him the ability to move through Central City like an unseen guardian angel. Though initially excited by his newfound powers, Barry is shocked to discover he is not the only \"meta-human\" who was created in the wake of the accelerator explosion -- and not everyone is using their new powers for good. Barry partners with S.T.A.R. Labs and dedicates his life to protect the innocent. For now, only a few close friends and associates know that Barry is literally the fastest man alive, but it won't be long before the world learns what Barry Allen has become...The Flash.",
                "Returning Series",
                "Scripted",
                "Drama, Sci-Fi & Fantasy",
                "The CW",
                "78%",
                R.drawable.poster_flash))
        tvShows.add(
            TvShowEntity( "ts6",
                "Gotham",
                "Everyone knows the name Commissioner Gordon. He is one of the crime world's greatest foes, a man whose reputation is synonymous with law and order. But what is known of Gordon's story and his rise from rookie detective to Police Commissioner? What did it take to navigate the multiple layers of corruption that secretly ruled Gotham City, the spawning ground of the world's most iconic villains? And what circumstances created them – the larger-than-life personas who would become Catwoman, The Penguin, The Riddler, Two-Face and The Joker?",
                "Ended",
                "Scripted",
                "Drama, Crime, Sci-Fi & Fantasy",
                "FOX",
                "76%",
                R.drawable.poster_gotham))
        tvShows.add(
            TvShowEntity( "ts7",
                "Grey's Anatomy",
                "Follows the personal and professional lives of a group of doctors at Seattle’s Grey Sloan Memorial Hospital.",
                "Returning Series",
                "Scripted",
                "Drama",
                "ABC",
                "82%",
                R.drawable.poster_grey_anatomy))
        tvShows.add(
            TvShowEntity( "ts8",
                "Hanna",
                "This thriller and coming-of-age drama follows the journey of an extraordinary young girl as she evades the relentless pursuit of an off-book CIA agent and tries to unearth the truth behind who she is. Based on the 2011 Joe Wright film.",
                "Ended",
                "Scripted",
                "Action & Adventure, Drama",
                "Prime Video",
                "76%",
                R.drawable.poster_hanna))
        tvShows.add(
            TvShowEntity( "ts9",
                "Marvel's Iron Fist ",
                "Danny Rand resurfaces 15 years after being presumed dead. Now, with the power of the Iron Fist, he seeks to reclaim his past and fulfill his destiny.",
                "Canceled",
                "Scripted",
                "Action & Adventure, Drama, Sci-Fi & Fantasy",
                "Netflix",
                "66%",
                R.drawable.poster_iron_fist))
        tvShows.add(
            TvShowEntity( "ts10",
                "NCIS",
                "From murder and espionage to terrorism and stolen submarines, a team of special agents investigates any crime that has a shred of evidence connected to Navy and Marine Corps personnel, regardless of rank or position.",
                "Returning Series",
                "Scripted",
                "Crime, Action & Adventure, Drama",
                "CBS",
                "75%",
                R.drawable.poster_ncis))

        return tvShows
    }
}