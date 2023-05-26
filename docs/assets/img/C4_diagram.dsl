workspace {

    model {
        user = person "User"
        bimdb = softwareSystem "BIMDb" "Allows Users to view detailed informations about movies, tv shows and actors and get personalized recommendations based on the user's favorite media" "BIMDb" {
            webApp = container "Web Application" "Provides the functionality and the frontend for the user to view the required movie informations." "Angular" {
                discoverComponent = component "Discover" "Movie/TV Shows overview" "Angular Component"
                movieListComponent = component "Movie List" "List of movies with filter options" "Angular Component"
                detailsComponent = component "Details" "Displaying detailed information about a movie / tv show" "Angular Component"
                tvShowListComponent = component "TV Show List" "List of tv shows with filter options" "Angular Component"
            }
            backendApp = container "API Application" "Responsible for the data handling associating setup informations via JSON/HTTPS API" "Spring Boot MVC" {
                tvController = component "Tv Controller" "Responsible for controlling the details page of a tv show." "Spring MVC Rest Controller"
                tvService = component "Tv Service" "" "Spring Service"
                movieController = component "Movie Controller" "Responsible for controlling the details page of a movie." "Spring MVC Rest Controller"
                movieService = component "Movie Service" "" "Spring Service"
                configController = component "Config Controller" "Responsible for controlling geo information." "Spring MVC Rest Controller"
                configService = component "Config Service" "" "Spring Service"
                discoverController = component "Discover Controller" "Responsible for controlling the overview sites for movies and tv shows." "Spring MVC Rest Controller"
                favoritesController = component "Favorites Controller" "Allows users to add, remove, query favorites" "Spring MVC Rest Controller"
                favoritesService = component "Favorites Service" "" "Spring Service"
                favoritesRepository = component "Favorites Repository" "" "Spring Repository"
                personController = component "Person Controller" "Responsible for controlling the user." "Spring MVC Rest Controller"
                personService = component "Person Service" "" "Spring Service"
                searchController = component "Search Controller" "Responsible for controlling all searches performed on the website, including movie, tv shows and user searches." "Spring MVC Rest Controller"
                searchService = component "Search Service" "" "Spring Service"
            }
            db = container "Database" "Stores user's information and favorites" "PostgreSQL" {
                tags = "Database"
            }
        }

        keycloak = softwareSystem "KeyCloak" "Identity Access Management System" "KeyCloak" {
        }

        tmdb = softwareSystem "TMDB" "API application that provides information on movies, tv shows and cast/crew members" "TMDB" {
        }


        //within API application
        tvController -> tvService "Uses"
        movieController -> movieService "Uses"
        configController -> configService "Uses"
        discoverController -> tvService "Uses"
        discoverController -> movieService "Uses"
        favoritesController -> favoritesService "Uses"
        favoritesService -> favoritesRepository "Uses"
        personController -> personService "Uses"
        searchController -> searchService "Uses"
        movieService -> favoritesRepository "Uses"
        tvService -> favoritesRepository "Uses"
        favoritesRepository -> db "Reads from and writes to"


        // relationships
        user -> webApp "Views movie informations using"

        webApp -> backendApp "Makes API calls to" "JSON/HTTPS""

        backendApp -> tmdb "Makes API calls to" "JSON/HTTPS"

        webApp -> keycloak "Requests Access Token"

        keycloak -> webApp "Returns Access Token"

        backendApp -> keycloak "Validate Token"

        keycloak -> backendApp "Validate Token"

        // frontend to backend (component BackendApp)
        webApp -> discoverController "makes API calls to"
        webApp -> movieController "makes API calls to"
        webApp -> tvController "makes API calls to"
        webApp -> searchController "makes API calls to"
        webApp -> personController "makes API calls to"
        webApp -> configController "makes API calls to"
        webApp -> favoritesController "makes API calls to"

        // frontend to backend (component Frontend)
        discoverComponent -> backendApp "makes API calls to"
        movieListComponent -> backendApp "makes API calls to"
        detailsComponent -> backendApp "makes API calls to"
        tvShowListComponent -> backendApp "makes API calls to"

        //backend to TMDB
        configService -> tmdb "makes API calls to"
        movieService -> tmdb "makes API calls to"
        personService -> tmdb "makes API calls to"
        searchService -> tmdb "makes API calls to"
        tvService -> tmdb "makes API calls to"

    }

    views {
        systemContext bimdb "SystemContext" {
            include *
            autolayout
        }

        container bimdb "Container" {
            include *
            autolayout
        }

        component webApp "WebApp" {
            include *
            autolayout
        }

        component backendApp "BackendApp" {
            include *
            autolayout
        }

        styles {

            element "Person" {
                background #08427b
                color #ffffff
                fontSize 22
                shape Person
            }

            element "BIMDb" {
                background #1168bd
                color #ffffff
            }

            element "Container" {
                background #438dd5
                color #ffffff
            }

            element "Component" {
                background #85bbf0
                color #000000
            }

            element "TMDB" {
                background #999999
                color #ffffff
            }

            element "KeyCloak" {
                background #999999
                color #ffffff
            }

            element "Database" {
                shape Cylinder
            }

        }

        theme default
    }

}