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
            api = container "Data Enhancement/API Application" "Enhances the raw data and provides the data collection via JSON/HTTPS API" "Java and Spring MVC" {
                mainPageController = component "Main Page Controller" "Responsible for controlling the first overview site, where all movies are listed." "Spring MVC Rest Controller"
                tmdbController = component "TMDB API Controller" "Responsible to set up the required API calls." "Spring MVC Rest Controller"
                detailsPageController = component "Details Page Controller" "Responsible for controlling the details page of each movie." "Spring MVC Rest Controller"
                securityController = component "Security Controller" "Provides functionality to load the API key." "Spring Bean"
                dataEnhanceController = component "Data Enhancement Controller" "Responsible for enhancing the collected data such that it can be provided to the end user." "Java"
                apiRouteController = component "API Route Controller" "Provides logic to get the required API routes, to send the data to the frontend" "Java"
                bimdbController = component "BIMDB API Controller" "Responsible to send the data to the web application." "Spring MVC Rest Controller"
            }

            db = container "Database" "Stores user's registration information, hashed authentication credentials, user preferences" "PostgreSQL" {
                tags = "Database"
            }
        }

        tmdb = softwareSystem "TMDB" "Database which  stores all required movie informations" "TMDB" {

        }

        // relationships
        user -> webApp "Views movie informations using"

        webApp -> api "Makes API calls to" "JSON/HTTPS"
        api -> db "Reads from and writes to" "SQL"
        api -> tmdb "Calls API with saved user preferences" "JSON/HTTPS"
        tmdb -> api "Sends the called data to the internal system" "JSON/HTTPS"

        api -> webApp "Sends enhanced data to" "JSON/HTTPS"

        // api
        discoverComponent -> mainPageController "uses"
        detailsComponent -> detailsPageController "uses"
        movieListComponent -> api "uses"
        tvShowListComponent -> api "uses"
        mainPageController -> tmdbController "uses"
        detailsPageController -> tmdbController "uses"
        tmdbController -> securityController "uses"
        tmdbController -> tmdb "Sends API calls to collect required data"


        //dea
        bimdbController -> apiRouteController "uses"
        bimdbController -> webApp "Sends data to"
        bimdbController -> dataEnhanceController "uses"



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

        component api "API"{
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