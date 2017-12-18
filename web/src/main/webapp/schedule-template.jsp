<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <title>telemaniacs</title>

    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1" />

    <meta http-equiv="cache-control" content="no-cache" />
    <meta http-equiv="pragma" content="no-cache" />
    <meta http-equiv="expires" content="-1" />

    <link rel="stylesheet" href="lib/bootstrap/css/bootstrap.min.css" />
    <link rel="stylesheet" href="lib/font-awesome-4.7.0/css/font-awesome.min.css" />
    <link rel="stylesheet" href="media/style.css" />
</head>


<body id="schedule-layout">
    <header>
        <nav class="navbar navbar-inverse navbar-fixed-top">
            <div class="container">
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
                        <span class="sr-only">Toggle navigation</span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>

                    <a class="navbar-brand" href="#">
                        <img src="media/logo.png" alt="Telemaniacs" class="img-responsive" />
                    </a>
                </div>

                <div id="navbar" class="navbar-collapse collapse">
                    <ul class="nav navbar-nav navbar-right">
                        <li class="action">
                            <a href="#">
                                <i class="fa fa-calendar"></i>
                                TV Schedule
                            </a>
                        </li>

                        <li class="action">
                            <a href="#!/shows/find">
                                <i class="fa fa-search"></i>
                                Find Show
                            </a>
                        </li>

                        <li class="action dropdown">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">
                                <i class="fa fa-unlock-alt"></i> Manage <span class="caret"></span>
                            </a>

                            <ul class="dropdown-menu">
                                <li>
                                    <a href="#">
                                        <i class="fa fa-television"></i> Channels
                                    </a>
                                </li>
                                <li>
                                    <a href="#">
                                        <i class="fa fa-eye"></i> Shows
                                    </a>
                                </li>
                            </ul>
                        </li>

                        <li class="action notification">
                            <a href="#" title="Notifications">
                                <i class="fa fa-bell"></i> <span class="hidden">Notifications</span>
                            </a>
                        </li>

                        <li class="dropdown">
                            <a href="#" class="user dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">
                                John Doe <span class="caret"></span>
                            </a>

                            <ul class="dropdown-menu">
                                <li>
                                    <a href="#">
                                        <i class="fa fa-heart"></i> Followed Channel
                                    </a>
                                </li>
                                <li>
                                    <a href="#">
                                        <i class="fa fa-star"></i> Followed Transmissions
                                    </a>
                                </li>
                                <li role="separator" class="divider"></li>
                                <li>
                                    <a href="#" class="logout">
                                        <i class="fa fa-power-off"></i> Log Out
                                    </a>
                                </li>
                            </ul>
                        </li>
                    </ul>
                </div>
            </div>
        </nav>

        <div class="page-header">
            <div class="container">
                <div class="row">
                    <div class="col-md-3">
                        <h2>TV Schedule</h2>
                    </div>

                    <div class="col-md-6 links">
                        <ul>
                            <li>
                                <a href="" class="selected">My channels</a>
                            </li>

                            <li>
                                <a href="">All channels</a>
                            </li>
                        </ul>
                    </div>

                    <div class="col-md-3 buttons">
                        <div class="pull-right">
                        <a href="" class="btn btn-primary">
                            <i class="fa fa-plus"></i>
                            Add channel
                        </a>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <div id="pick-date">
            <a href="" title="Previous"><i class="fa fa-arrow-left"></i></a>
            <strong>Monday.</strong> 11/28/2017
            <a href="" title="Next"><i class="fa fa-arrow-right"></i></a>
        </div>
    </header>


    <div id="schedule">
        <div class="group channels">
            <div class="column time-clue"></div>

            <div class="column">
                <div class="channel">
                    Discovery Channel

                    <a href="" class="follow"><i class="fa fa-heart"></i></a>
                </div>
            </div>

            <div class="column">
                <div class="channel">
                    Nat Geo

                    <a href="" class="follow"><i class="fa fa-heart"></i></a>
                </div>
            </div>

            <div class="column">
                <div class="channel">
                    Nat Geo Wild

                    <a href="" class="follow"><i class="fa fa-heart"></i></a>
                </div>
            </div>

            <div class="column">
                <div class="channel">
                    Channel

                    <a href="" class="follow"><i class="fa fa-heart"></i></a>
                </div>
            </div>

            <div class="column">
                <div class="channel">
                    Channel

                    <a href="" class="follow"><i class="fa fa-heart"></i></a>
                </div>
            </div>

            <div class="column">
                <div class="channel">
                    Channel

                    <a href="" class="follow"><i class="fa fa-heart"></i></a>
                </div>
            </div>

            <div class="column">
                <div class="channel">
                    Channel

                    <a href="" class="follow"><i class="fa fa-heart"></i></a>
                </div>
            </div>
        </div>



        <div class="group now">
            <div class="column time-clue"></div>

            <div class="column">
                <div class="transmission followed">
                    <div class="heading">
                        <div class="time">Now</div>

                        <div class="status">
                            <div class="progress">
                                <div class="progress-bar" style="width:40%"></div>
                            </div>
                        </div>
                    </div>

                    <h3>
                        <a href="">Shark Bites (1)</a>
                    </h3>

                    <div class="info">
                        <div class="type">
                            <span>Documentary</span>
                        </div>
                        <div class="attributes">
                            <span data-toggle="tooltip" title="Language">EN</span>
                            <span data-toggle="tooltip" title="Age Availability">15+</span>
                            <span data-toggle="tooltip" title="Rerun"><i class="fa fa-refresh"></i></span>
                        </div>
                    </div>

                    <div class="description">
                        Lorem ipsum dolor sit amet, consectetuer adipiscing elit.Integer malesuada. Neque porro quisquam
                        est, qui dolorem ipsum...
                    </div>
                </div>
            </div>

            <div class="column">
                <div class="transmission">
                    <div class="heading">
                        <div class="time">Now</div>

                        <div class="status">
                            <div class="progress">
                                <div class="progress-bar" style="width:40%"></div>
                            </div>
                        </div>
                    </div>

                    <h3>
                        <a href="">Shark Bites (1)</a>
                    </h3>

                    <div class="info">
                        <div class="type">
                            <span>Documentary</span>
                        </div>
                        <div class="attributes">
                            <span data-toggle="tooltip" title="Language">EN</span>
                            <span data-toggle="tooltip" title="Age Availability">15+</span>
                            <span data-toggle="tooltip" title="Rerun"><i class="fa fa-refresh"></i></span>
                        </div>
                    </div>

                    <div class="description">
                        Lorem ipsum dolor sit amet, consectetuer adipiscing elit.Integer malesuada. Neque porro quisquam
                        est, qui dolorem ipsum...
                        Lorem ipsum dolor sit amet, consectetuer adipiscing elit.Integer malesuada. Neque porro quisquam
                        est, qui dolorem ipsum...
                    </div>
                </div>
            </div>

            <div class="column">
                <div class="transmission">
                    <div class="heading">
                        <div class="time">Now</div>

                        <div class="status">
                            <div class="progress">
                                <div class="progress-bar" style="width:40%"></div>
                            </div>
                        </div>
                    </div>

                    <h3>
                        <a href="">Shark Bites (1)</a>
                    </h3>

                    <div class="info">
                        <div class="type">
                            <span>Documentary</span>
                        </div>
                        <div class="attributes">
                            <span data-toggle="tooltip" title="Language">EN</span>
                            <span data-toggle="tooltip" title="Age Availability">15+</span>
                            <span data-toggle="tooltip" title="Rerun"><i class="fa fa-refresh"></i></span>
                        </div>
                    </div>

                    <div class="description">
                        Lorem ipsum dolor sit amet, consectetuer adipiscing elit.Integer malesuada. Neque porro quisquam
                        est, qui dolorem ipsum...
                    </div>
                </div>
            </div>

            <div class="column">
                <div class="transmission">
                    <div class="heading">
                        <div class="time">Now</div>

                        <div class="status">
                            <div class="progress">
                                <div class="progress-bar" style="width:40%"></div>
                            </div>
                        </div>
                    </div>

                    <h3>
                        <a href="">Shark Bites (1)</a>
                    </h3>

                    <div class="info">
                        <div class="type">
                            <span>Documentary</span>
                        </div>
                        <div class="attributes">
                            <span data-toggle="tooltip" title="Language">EN</span>
                            <span data-toggle="tooltip" title="Age Availability">15+</span>
                            <span data-toggle="tooltip" title="Rerun"><i class="fa fa-refresh"></i></span>
                        </div>
                    </div>

                    <div class="description">
                        Lorem ipsum dolor sit amet, consectetuer adipiscing elit.Integer malesuada. Neque porro quisquam
                        est, qui dolorem ipsum...
                    </div>
                </div>
            </div>

            <div class="column">
                <div class="transmission">
                    <div class="heading">
                        <div class="time">Now</div>

                        <div class="status">
                            <div class="progress">
                                <div class="progress-bar" style="width:40%"></div>
                            </div>
                        </div>
                    </div>

                    <h3>
                        <a href="">Shark Bites (1)</a>
                    </h3>

                    <div class="info">
                        <div class="type">
                            <span>Documentary</span>
                        </div>
                        <div class="attributes">
                            <span data-toggle="tooltip" title="Language">EN</span>
                            <span data-toggle="tooltip" title="Age Availability">15+</span>
                            <span data-toggle="tooltip" title="Rerun"><i class="fa fa-refresh"></i></span>
                        </div>
                    </div>

                    <div class="description">
                        Lorem ipsum dolor sit amet, consectetuer adipiscing elit.Integer malesuada. Neque porro quisquam
                        est, qui dolorem ipsum...
                    </div>
                </div>
            </div>

            <div class="column">
                <div class="transmission">
                    <div class="heading">
                        <div class="time">Now</div>

                        <div class="status">
                            <div class="progress">
                                <div class="progress-bar" style="width:40%"></div>
                            </div>
                        </div>
                    </div>

                    <h3>
                        <a href="">Shark Bites (1)</a>
                    </h3>

                    <div class="info">
                        <div class="type">
                            <span>Documentary</span>
                        </div>
                        <div class="attributes">
                            <span data-toggle="tooltip" title="Language">EN</span>
                            <span data-toggle="tooltip" title="Age Availability">15+</span>
                            <span data-toggle="tooltip" title="Rerun"><i class="fa fa-refresh"></i></span>
                        </div>
                    </div>

                    <div class="description">
                        Lorem ipsum dolor sit amet, consectetuer adipiscing elit.Integer malesuada. Neque porro quisquam
                        est, qui dolorem ipsum...
                    </div>
                </div>
            </div>

            <div class="column">
                <div class="transmission">
                    <div class="heading">
                        <div class="time">Now</div>

                        <div class="status">
                            <div class="progress">
                                <div class="progress-bar" style="width:40%"></div>
                            </div>
                        </div>
                    </div>

                    <h3>
                        <a href="">Shark Bites (1)</a>
                    </h3>

                    <div class="info">
                        <div class="type">
                            <span>Documentary</span>
                        </div>
                        <div class="attributes">
                            <span data-toggle="tooltip" title="Language">EN</span>
                            <span data-toggle="tooltip" title="Age Availability">15+</span>
                            <span data-toggle="tooltip" title="Rerun"><i class="fa fa-refresh"></i></span>
                        </div>
                    </div>

                    <div class="description">
                        Lorem ipsum dolor sit amet, consectetuer adipiscing elit.Integer malesuada. Neque porro quisquam
                        est, qui dolorem ipsum...
                    </div>
                </div>
            </div>
        </div>



        <div class="group">
            <div class="column time-clue">
                <div>10:00</div>
            </div>

            <div class="column">
                <div class="transmission">
                    <div class="heading">
                        <div class="time">10:15</div>

                        <div class="options">
                            <a href="" data-toggle="tooltip" title="Details"><i class="fa fa-info-circle"></i></a>
                            <a href="" data-toggle="tooltip" title="Follow" class="follow"><i class="fa fa-star"></i></a>
                        </div>
                    </div>

                    <h3>
                        <a href="">Shark Bites (2)</a>
                    </h3>

                    <div class="info">
                        <div class="type">
                            <span>Documentary</span>
                        </div>
                        <div class="attributes">
                            <span data-toggle="tooltip" title="Language">EN</span>
                            <span data-toggle="tooltip" title="Age Availability">15+</span>
                        </div>
                    </div>

                    <div class="description">
                        Lorem ipsum dolor sit amet, consectetuer adipiscing elit.Integer malesuada. Neque porro quisquam
                        est, qui dolorem ipsum...
                    </div>
                </div>

                <div class="transmission">
                    <div class="heading">
                        <div class="time">10:30</div>

                        <div class="options">
                            <a href="" data-toggle="tooltip" title="Details"><i class="fa fa-info-circle"></i></a>
                            <a href="" data-toggle="tooltip" title="Follow" class="follow"><i class="fa fa-star"></i></a>
                        </div>
                    </div>

                    <h3>
                        <a href="">Shark Bites (3)</a>
                    </h3>

                    <div class="info">
                        <div class="type">
                            <span>Documentary</span>
                        </div>
                        <div class="attributes">
                            <span data-toggle="tooltip" title="Language">EN</span>
                            <span data-toggle="tooltip" title="Age Availability">15+</span>
                        </div>
                    </div>

                    <div class="description">
                        Lorem ipsum dolor sit amet, consectetuer adipiscing elit.Integer malesuada. Neque porro quisquam
                        est, qui dolorem ipsum...
                    </div>
                </div>

                <div class="transmission">
                    <div class="heading">
                        <div class="time">10:30</div>

                        <div class="options">
                            <a href="" data-toggle="tooltip" title="Details"><i class="fa fa-info-circle"></i></a>
                            <a href="" data-toggle="tooltip" title="Follow" class="follow"><i class="fa fa-star"></i></a>
                        </div>
                    </div>

                    <h3>
                        <a href="">Shark Bites (4)</a>
                    </h3>

                    <div class="info">
                        <div class="type">
                            <span>Documentary</span>
                        </div>
                        <div class="attributes">
                            <span data-toggle="tooltip" title="Language">EN</span>
                            <span data-toggle="tooltip" title="Age Availability">15+</span>
                        </div>
                    </div>

                    <div class="description">
                        Lorem ipsum dolor sit amet, consectetuer adipiscing elit.Integer malesuada. Neque porro quisquam
                        est, qui dolorem ipsum...
                    </div>
                </div>
            </div>

            <div class="column">
                <div class="transmission">
                    <div class="heading">
                        <div class="time">10:15</div>

                        <div class="options">
                            <a href="" data-toggle="tooltip" title="Details"><i class="fa fa-info-circle"></i></a>
                            <a href="" data-toggle="tooltip" title="Follow" class="follow"><i class="fa fa-star"></i></a>
                        </div>
                    </div>

                    <h3>
                        <a href="">Shark Bites (2)</a>
                    </h3>

                    <div class="info">
                        <div class="type">
                            <span>Documentary</span>
                        </div>
                        <div class="attributes">
                            <span data-toggle="tooltip" title="Language">EN</span>
                            <span data-toggle="tooltip" title="Age Availability">15+</span>
                        </div>
                    </div>

                    <div class="description">
                        Lorem ipsum dolor sit amet, consectetuer adipiscing elit.Integer malesuada. Neque porro quisquam
                        est, qui dolorem ipsum...
                    </div>
                </div>

                <div class="transmission">
                    <div class="heading">
                        <div class="time">10:30</div>

                        <div class="options">
                            <a href="" data-toggle="tooltip" title="Details"><i class="fa fa-info-circle"></i></a>
                            <a href="" data-toggle="tooltip" title="Follow" class="follow"><i class="fa fa-star"></i></a>
                        </div>
                    </div>

                    <h3>
                        <a href="">Shark Bites (3)</a>
                    </h3>

                    <div class="info">
                        <div class="type">
                            <span>Documentary</span>
                        </div>
                        <div class="attributes">
                            <span data-toggle="tooltip" title="Language">EN</span>
                            <span data-toggle="tooltip" title="Age Availability">15+</span>
                        </div>
                    </div>

                    <div class="description">
                        Lorem ipsum dolor sit amet, consectetuer adipiscing elit.Integer malesuada. Neque porro quisquam
                        est, qui dolorem ipsum...
                    </div>
                </div>

                <div class="transmission">
                    <div class="heading">
                        <div class="time">10:30</div>

                        <div class="options">
                            <a href="" data-toggle="tooltip" title="Details"><i class="fa fa-info-circle"></i></a>
                            <a href="" data-toggle="tooltip" title="Follow" class="follow"><i class="fa fa-star"></i></a>
                        </div>
                    </div>

                    <h3>
                        <a href="">Shark Bites (4)</a>
                    </h3>

                    <div class="info">
                        <div class="type">
                            <span>Documentary</span>
                        </div>
                        <div class="attributes">
                            <span data-toggle="tooltip" title="Language">EN</span>
                            <span data-toggle="tooltip" title="Age Availability">15+</span>
                        </div>
                    </div>

                    <div class="description">
                        Lorem ipsum dolor sit amet, consectetuer adipiscing elit.Integer malesuada. Neque porro quisquam
                        est, qui dolorem ipsum...
                    </div>
                </div>
            </div>

            <div class="column">
                <div class="transmission">
                    <div class="heading">
                        <div class="time">10:15</div>

                        <div class="options">
                            <a href="" data-toggle="tooltip" title="Details"><i class="fa fa-info-circle"></i></a>
                            <a href="" data-toggle="tooltip" title="Follow" class="follow"><i class="fa fa-star"></i></a>
                        </div>
                    </div>

                    <h3>
                        <a href="">Shark Bites (2)</a>
                    </h3>

                    <div class="info">
                        <div class="type">
                            <span>Documentary</span>
                        </div>
                        <div class="attributes">
                            <span data-toggle="tooltip" title="Language">EN</span>
                            <span data-toggle="tooltip" title="Age Availability">15+</span>
                        </div>
                    </div>

                    <div class="description">
                        Lorem ipsum dolor sit amet, consectetuer adipiscing elit.Integer malesuada. Neque porro quisquam
                        est, qui dolorem ipsum...
                    </div>
                </div>

                <div class="transmission">
                    <div class="heading">
                        <div class="time">10:30</div>

                        <div class="options">
                            <a href="" data-toggle="tooltip" title="Details"><i class="fa fa-info-circle"></i></a>
                            <a href="" data-toggle="tooltip" title="Follow" class="follow"><i class="fa fa-star"></i></a>
                        </div>
                    </div>

                    <h3>
                        <a href="">Shark Bites (3)</a>
                    </h3>

                    <div class="info">
                        <div class="type">
                            <span>Documentary</span>
                        </div>
                        <div class="attributes">
                            <span data-toggle="tooltip" title="Language">EN</span>
                            <span data-toggle="tooltip" title="Age Availability">15+</span>
                        </div>
                    </div>

                    <div class="description">
                        Lorem ipsum dolor sit amet, consectetuer adipiscing elit.Integer malesuada. Neque porro quisquam
                        est, qui dolorem ipsum...
                    </div>
                </div>

                <div class="transmission">
                    <div class="heading">
                        <div class="time">10:30</div>

                        <div class="options">
                            <a href="" data-toggle="tooltip" title="Details"><i class="fa fa-info-circle"></i></a>
                            <a href="" data-toggle="tooltip" title="Follow" class="follow"><i class="fa fa-star"></i></a>
                        </div>
                    </div>

                    <h3>
                        <a href="">Shark Bites (4)</a>
                    </h3>

                    <div class="info">
                        <div class="type">
                            <span>Documentary</span>
                        </div>
                        <div class="attributes">
                            <span data-toggle="tooltip" title="Language">EN</span>
                            <span data-toggle="tooltip" title="Age Availability">15+</span>
                        </div>
                    </div>

                    <div class="description">
                        Lorem ipsum dolor sit amet, consectetuer adipiscing elit.Integer malesuada. Neque porro quisquam
                        est, qui dolorem ipsum...
                    </div>
                </div>
            </div>

            <div class="column">
                <div class="transmission">
                    <div class="heading">
                        <div class="time">10:15</div>

                        <div class="options">
                            <a href="" data-toggle="tooltip" title="Details"><i class="fa fa-info-circle"></i></a>
                            <a href="" data-toggle="tooltip" title="Follow" class="follow"><i class="fa fa-star"></i></a>
                        </div>
                    </div>

                    <h3>
                        <a href="">Shark Bites (2)</a>
                    </h3>

                    <div class="info">
                        <div class="type">
                            <span>Documentary</span>
                        </div>
                        <div class="attributes">
                            <span data-toggle="tooltip" title="Language">EN</span>
                            <span data-toggle="tooltip" title="Age Availability">15+</span>
                        </div>
                    </div>

                    <div class="description">
                        Lorem ipsum dolor sit amet, consectetuer adipiscing elit.Integer malesuada. Neque porro quisquam
                        est, qui dolorem ipsum...
                    </div>
                </div>

                <div class="transmission">
                    <div class="heading">
                        <div class="time">10:30</div>

                        <div class="options">
                            <a href="" data-toggle="tooltip" title="Details"><i class="fa fa-info-circle"></i></a>
                            <a href="" data-toggle="tooltip" title="Follow" class="follow"><i class="fa fa-star"></i></a>
                        </div>
                    </div>

                    <h3>
                        <a href="">Shark Bites (3)</a>
                    </h3>

                    <div class="info">
                        <div class="type">
                            <span>Documentary</span>
                        </div>
                        <div class="attributes">
                            <span data-toggle="tooltip" title="Language">EN</span>
                            <span data-toggle="tooltip" title="Age Availability">15+</span>
                        </div>
                    </div>

                    <div class="description">
                        Lorem ipsum dolor sit amet, consectetuer adipiscing elit.Integer malesuada. Neque porro quisquam
                        est, qui dolorem ipsum...
                    </div>
                </div>

                <div class="transmission">
                    <div class="heading">
                        <div class="time">10:30</div>

                        <div class="options">
                            <a href="" data-toggle="tooltip" title="Details"><i class="fa fa-info-circle"></i></a>
                            <a href="" data-toggle="tooltip" title="Follow" class="follow"><i class="fa fa-star"></i></a>
                        </div>
                    </div>

                    <h3>
                        <a href="">Shark Bites (4)</a>
                    </h3>

                    <div class="info">
                        <div class="type">
                            <span>Documentary</span>
                        </div>
                        <div class="attributes">
                            <span data-toggle="tooltip" title="Language">EN</span>
                            <span data-toggle="tooltip" title="Age Availability">15+</span>
                        </div>
                    </div>

                    <div class="description">
                        Lorem ipsum dolor sit amet, consectetuer adipiscing elit.Integer malesuada. Neque porro quisquam
                        est, qui dolorem ipsum...
                    </div>
                </div>
            </div>

            <div class="column">
                <div class="transmission">
                    <div class="heading">
                        <div class="time">10:15</div>

                        <div class="options">
                            <a href="" data-toggle="tooltip" title="Details"><i class="fa fa-info-circle"></i></a>
                            <a href="" data-toggle="tooltip" title="Follow" class="follow"><i class="fa fa-star"></i></a>
                        </div>
                    </div>

                    <h3>
                        <a href="">Shark Bites (2)</a>
                    </h3>

                    <div class="info">
                        <div class="type">
                            <span>Documentary</span>
                        </div>
                        <div class="attributes">
                            <span data-toggle="tooltip" title="Language">EN</span>
                            <span data-toggle="tooltip" title="Age Availability">15+</span>
                        </div>
                    </div>

                    <div class="description">
                        Lorem ipsum dolor sit amet, consectetuer adipiscing elit.Integer malesuada. Neque porro quisquam
                        est, qui dolorem ipsum...
                    </div>
                </div>

                <div class="transmission">
                    <div class="heading">
                        <div class="time">10:30</div>

                        <div class="options">
                            <a href="" data-toggle="tooltip" title="Details"><i class="fa fa-info-circle"></i></a>
                            <a href="" data-toggle="tooltip" title="Follow" class="follow"><i class="fa fa-star"></i></a>
                        </div>
                    </div>

                    <h3>
                        <a href="">Shark Bites (3)</a>
                    </h3>

                    <div class="info">
                        <div class="type">
                            <span>Documentary</span>
                        </div>
                        <div class="attributes">
                            <span data-toggle="tooltip" title="Language">EN</span>
                            <span data-toggle="tooltip" title="Age Availability">15+</span>
                        </div>
                    </div>

                    <div class="description">
                        Lorem ipsum dolor sit amet, consectetuer adipiscing elit.Integer malesuada. Neque porro quisquam
                        est, qui dolorem ipsum...
                    </div>
                </div>

                <div class="transmission">
                    <div class="heading">
                        <div class="time">10:30</div>

                        <div class="options">
                            <a href="" data-toggle="tooltip" title="Details"><i class="fa fa-info-circle"></i></a>
                            <a href="" data-toggle="tooltip" title="Follow" class="follow"><i class="fa fa-star"></i></a>
                        </div>
                    </div>

                    <h3>
                        <a href="">Shark Bites (4)</a>
                    </h3>

                    <div class="info">
                        <div class="type">
                            <span>Documentary</span>
                        </div>
                        <div class="attributes">
                            <span data-toggle="tooltip" title="Language">EN</span>
                            <span data-toggle="tooltip" title="Age Availability">15+</span>
                        </div>
                    </div>

                    <div class="description">
                        Lorem ipsum dolor sit amet, consectetuer adipiscing elit.Integer malesuada. Neque porro quisquam
                        est, qui dolorem ipsum...
                    </div>
                </div>
            </div>

            <div class="column">
                <div class="transmission">
                    <div class="heading">
                        <div class="time">10:15</div>

                        <div class="options">
                            <a href="" data-toggle="tooltip" title="Details"><i class="fa fa-info-circle"></i></a>
                            <a href="" data-toggle="tooltip" title="Follow" class="follow"><i class="fa fa-star"></i></a>
                        </div>
                    </div>

                    <h3>
                        <a href="">Shark Bites (2)</a>
                    </h3>

                    <div class="info">
                        <div class="type">
                            <span>Documentary</span>
                        </div>
                        <div class="attributes">
                            <span data-toggle="tooltip" title="Language">EN</span>
                            <span data-toggle="tooltip" title="Age Availability">15+</span>
                        </div>
                    </div>

                    <div class="description">
                        Lorem ipsum dolor sit amet, consectetuer adipiscing elit.Integer malesuada. Neque porro quisquam
                        est, qui dolorem ipsum...
                    </div>
                </div>
            </div>

            <div class="column">
                <div class="transmission">
                    <div class="heading">
                        <div class="time">10:15</div>

                        <div class="options">
                            <a href="" data-toggle="tooltip" title="Details"><i class="fa fa-info-circle"></i></a>
                            <a href="" data-toggle="tooltip" title="Follow" class="follow"><i class="fa fa-star"></i></a>
                        </div>
                    </div>

                    <h3>
                        <a href="">Shark Bites (2)</a>
                    </h3>

                    <div class="info">
                        <div class="type">
                            <span>Documentary</span>
                        </div>
                        <div class="attributes">
                            <span data-toggle="tooltip" title="Language">EN</span>
                            <span data-toggle="tooltip" title="Age Availability">15+</span>
                        </div>
                    </div>

                    <div class="description">
                        Lorem ipsum dolor sit amet, consectetuer adipiscing elit.Integer malesuada. Neque porro quisquam
                        est, qui dolorem ipsum...
                    </div>
                </div>

                <div class="transmission">
                    <div class="heading">
                        <div class="time">10:30</div>

                        <div class="options">
                            <a href="" data-toggle="tooltip" title="Details"><i class="fa fa-info-circle"></i></a>
                            <a href="" data-toggle="tooltip" title="Follow" class="follow"><i class="fa fa-star"></i></a>
                        </div>
                    </div>

                    <h3>
                        <a href="">Shark Bites (3)</a>
                    </h3>

                    <div class="info">
                        <div class="type">
                            <span>Documentary</span>
                        </div>
                        <div class="attributes">
                            <span data-toggle="tooltip" title="Language">EN</span>
                            <span data-toggle="tooltip" title="Age Availability">15+</span>
                        </div>
                    </div>

                    <div class="description">
                        Lorem ipsum dolor sit amet, consectetuer adipiscing elit.Integer malesuada. Neque porro quisquam
                        est, qui dolorem ipsum...
                    </div>
                </div>
            </div>
        </div>
    </div>



    <script src="lib/jquery-3.2.1.slim.min.js"></script>
    <script src="lib/bootstrap/js/bootstrap.min.js"></script>
    <script src="media/script.js"></script>
</body>

</html>