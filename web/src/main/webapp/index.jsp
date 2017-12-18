<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html lang="en" ng-app="telemaniacsApp" ng-controller="CommonController">

<head>
    <title>{{ pageService.getTitle() }}</title>

    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1" />

    <link rel="stylesheet" type="text/css" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
    <link rel="stylesheet" href="lib/bootstrap/css/bootstrap.min.css" />
    <link rel="stylesheet" href="lib/font-awesome/css/font-awesome.min.css" />
    <link rel="stylesheet" href="media/style.css" />
</head>


<body>
    <tm-header page-service="pageService"></tm-header>
    <ng-view></ng-view>


    <!-- Scripts -->
    <script src="lib/jquery/jquery-3.2.1.slim.min.js"></script>
    <script src="lib/bootstrap/js/bootstrap.min.js"></script>
    <script src="lib/angular/angular.min.js"></script>
    <script src="lib/angular/angular-route.min.js"></script>
    <script src="lib/angular/angular-cookies.min.js"></script>
    <script src="media/telemaniacs.js"></script>

    <!-- AngularJS -->
    <script src="app/common/common.js"></script>
    <script src="app/common/router.js"></script>

    <script src="app/services/page.js"></script>

    <script src="app/schedule/controller.js"></script>
    <script src="app/channels/controller.js"></script>
    <script src="app/transmission/controller.js"></script>
    <script src="app/transmissionOccurrence/controller.js"></script>
    <script src="app/channels/channelsListController.js"></script>
    <script src="app/channels/myChannelController.js"></script>
    <script src="app/transmission/myTransmissionController.js"></script>
    <script src="app/transmission/upcomingTransmissionsController.js"></script>
    <script src="app/user/controller.js"></script>
</body>

</html>