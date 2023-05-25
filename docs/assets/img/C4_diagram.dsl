workspace {

    model {
        user = person "User"
        bimdb = softwareSystem "BIMDb" "Allows Users to view detailed informations about a movie and get personalized recommendations based on the user's known preferences" "BIMDb" {
            webApp = container "Web Application" "Provides the functionality and the frontend for the user to view the required movie informations." "Angular" {
                discoverComponent = component "Discover" "Movie/TV Shows overview" "Angular Component"
                movieListComponent = component "Movie List" "List of movies with filter options" "Angular Component"
                detailsComponent = component "Details" "Displaying detailed information about a movie / tv show" "Angular Component"
                tvShowListComponent = component "TV Show List" "List of tv shows with filter options" "Angular Component"
            }
            backendApp = container "Backend Application" "Responsible for the data handling associating setup informations via JSON/HTTPS API" "Java and Spring MVC" {
                tvController = component "Tv Controller" "Responsible for controlling the details page of a tv show." "Spring MVC Rest Controller"
                movieController = component "Movie Controller" "Responsible for controlling the details page of a movie." "Spring MVC Rest Controller"
                configController = component "Config Controller" "Responsible for controlling geo information." "Spring MVC Rest Controller"
                discoverController = component "Discover Controller" "Responsible for controlling the overview sites for movies and tv shows." "Spring MVC Rest Controller"
                favoritesController = component "Favorites Controller" "Responsible for controlling the user specific favorites site." "Spring MVC Rest Controller"
                personController = component "Person Controller" "Responsible for controlling the user." "Spring MVC Rest Controller"
                searchController = component "Search Controller" "Responsible for controlling all searches performed on the website, including movie, tv shows and user searches." "Spring MVC Rest Controller"
            }
        }

        tmdb = softwareSystem "TMDB" "Database which  stores all required movie informations" "TMDB" {

        }

        // relationships
        user -> webApp "Views movie informations using"

        webApp -> backendApp "Makes API calls to" "JSON/HTTPS""

        backendApp -> tmdb "Calls API with saved user preferences" "JSON/HTTPS"

        tmdb -> backendApp "Sends the called data to the internal system" "JSON/HTTPS"

        backendApp -> webApp "Sends data to" "JSON/HTTPS"

        // frontend to backend (component BackendApp)
        webApp -> discoverController "sends APPI calls to"
        webApp -> movieController "sends APPI calls to"
        webApp -> tvController "sends APPI calls to"
        webApp -> searchController "sends APPI calls to"
        webApp -> personController "sends APPI calls to"
        webApp -> configController "sends APPI calls to"
        webApp -> favoritesController "sends APPI calls to"

        //backend to TMDB
        configController -> tmdb "Sends API calls to collect required data"
        discoverController -> tmdb "Sends API calls to collect required data"
        favoritesController -> tmdb "Sends API calls to collect required data"
        movieController -> tmdb "Sends API calls to collect required data"
        personController -> tmdb "Sends API calls to collect required data"
        searchController -> tmdb "Sends API calls to collect required data"
        tvController -> tmdb "Sends API calls to collect required data"

        //backend to frontend
        configController -> webApp "Sends data to" "JSON/HTTPS"
        discoverController -> webApp "Sends data to" "JSON/HTTPS"
        favoritesController -> webApp "Sends data to" "JSON/HTTPS"
        movieController -> webApp "Sends data to" "JSON/HTTPS"
        personController -> webApp "Sends data to" "JSON/HTTPS"
        searchController -> webApp "Sends data to" "JSON/HTTPS"
        tvController -> webApp "Sends data to" "JSON/HTTPS"


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

            element "Database" {
                shape Cylinder
            }

        }

        theme default
    }

}