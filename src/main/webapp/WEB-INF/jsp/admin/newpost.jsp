<%-- 
    Document   : newpost
    Created on : Feb 27, 2017, 2:16:50 PM
    Author     : Truong
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8" />
        <link rel="apple-touch-icon" sizes="76x76" href="<%=request.getContextPath()%>/resources/img/apple-icon.png">
        <link rel="icon" type="image/png" sizes="96x96" href="<%=request.getContextPath()%>/resources/img/favicon.png">
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
        
        <title>Site Name</title>
        
        <meta content='width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0' name='viewport' />
        <meta name="viewport" content="width=device-width" />
        <!-- Bootstrap core CSS     -->
        <link href="<%=request.getContextPath()%>/resources/css/bootstrap.min.css" rel="stylesheet" />
        
        <!-- Animation library for notifications   -->
        <link href="<%=request.getContextPath()%>/resources/css/animate.min.css" rel="stylesheet"/>

        <!--  Paper Dashboard core CSS    -->
        <link href="<%=request.getContextPath()%>/resources/css/paper-dashboard.css" rel="stylesheet"/>

        <!--  CSS for Demo Purpose, don't include it in your project     -->
        <link href="<%=request.getContextPath()%>/resources/css/demo.css" rel="stylesheet" />

        <!--  Fonts and icons     -->
        <link href="http://maxcdn.bootstrapcdn.com/font-awesome/latest/css/font-awesome.min.css" rel="stylesheet">
        <link href='https://fonts.googleapis.com/css?family=Muli:400,300' rel='stylesheet' type='text/css'>
        <link href="<%=request.getContextPath()%>/resources/css/themify-icons.css" rel="stylesheet">

    </head>
    <body>

        <div class="wrapper">
            <div class="sidebar" data-background-color="white" data-active-color="danger">

                <!--
                            Tip 1: you can change the color of the sidebar's background using: data-background-color="white | black"
                            Tip 2: you can change the color of the active button using the data-active-color="primary | info | success | warning | danger"
                -->

                <div class="sidebar-wrapper">
                    <div class="logo">
                        <a href="google.com" class="simple-text">
                            NQT
                        </a>
                    </div>

                    <ul class="nav">
                        <li>
                            <a href="dashboard.html">
                                <i class="ti-panel"></i>
                                <p>Dashboard</p>
                            </a>
                        </li>
                        <li class="active">
                            <a href="user.html">
                                <i class="ti-user"></i>
                                <p>User Profile</p>
                            </a>
                        </li>
                        <li>
                            <a href="table.html">
                                <i class="ti-view-list-alt"></i>
                                <p>Table List</p>
                            </a>
                        </li>
                        <li>
                            <a href="typography.html">
                                <i class="ti-text"></i>
                                <p>Typography</p>
                            </a>
                        </li>
                        <li>
                            <a href="icons.html">
                                <i class="ti-pencil-alt2"></i>
                                <p>Icons</p>
                            </a>
                        </li>
                        <li>
                            <a href="maps.html">
                                <i class="ti-map"></i>
                                <p>Maps</p>
                            </a>
                        </li>
                        <li>
                            <a href="notifications.html">
                                <i class="ti-bell"></i>
                                <p>Notifications</p>
                            </a>
                        </li>
                    </ul>
                </div>
            </div>

            <div class="main-panel">
                <nav class="navbar navbar-default">
                    <div class="container-fluid">
                        <div class="navbar-header">
                            <button type="button" class="navbar-toggle">
                                <span class="sr-only">Toggle navigation</span>
                                <span class="icon-bar bar1"></span>
                                <span class="icon-bar bar2"></span>
                                <span class="icon-bar bar3"></span>
                            </button>
                            <a class="navbar-brand" href="">New Post</a>
                        </div>
                        <div class="collapse navbar-collapse">
                            <ul class="nav navbar-nav navbar-right">
                                <li>
                                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                                        <i class="ti-panel"></i>
                                        <p>Stats</p>
                                    </a>
                                </li>
                                <li class="dropdown">
                                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                                        <i class="ti-bell"></i>
                                        <p class="notification">5</p>
                                        <p>Notifications</p>
                                        <b class="caret"></b>
                                    </a>
                                    <ul class="dropdown-menu">
                                        <li><a href="#">Notification 1</a></li>
                                        <li><a href="#">Notification 2</a></li>
                                        <li><a href="#">Notification 3</a></li>
                                        <li><a href="#">Notification 4</a></li>
                                        <li><a href="#">Another notification</a></li>
                                    </ul>
                                </li>
                                <li>
                                    <a href="#">
                                        <i class="ti-settings"></i>
                                        <p>Settings</p>
                                    </a>
                                </li>
                            </ul>

                        </div>
                    </div>
                </nav>

                <div class="content  col-md-10">
                    <form>
                        <div class="form-group col-sm-12">
                            <input type="text" id="post-title" placeholder="Enter title here" value="" class="form-control border-input">
                        </div>
                        <div class="form-group col-sm-12">
                            <label>https://siteurl.com/</label>
                            <input type="text" style="width: 50%; border: solid 1px #d8d8d8;" id="post-url" placeholder="" ondblclick="this.readOnly=''" value="" class="url" readonly="readonly"  class="form-control">
                        </div>
                        <div class="form-group col-sm-12">
                            <textarea class="post-content"></textarea>
                        </div>
                    </form>
                </div>
                
                <div class="right-sidebar col-md-2" data-background-color="white" data-active-color="danger">
                    đàadsfadsfadfadsfadsfadsfd
                </div>
            </div>
            
        </div>


    </body>

    <!--   Core JS Files   -->
    <script src="<%=request.getContextPath()%>/resources/js/jquery-1.10.2.js" type="text/javascript"></script>
    <script src="<%=request.getContextPath()%>/resources/js/bootstrap.min.js" type="text/javascript"></script>

    <!--  Checkbox, Radio & Switch Plugins -->
    <script src="<%=request.getContextPath()%>/resources/js/bootstrap-checkbox-radio.js"></script>

    <!--  Charts Plugin -->
    <script src="<%=request.getContextPath()%>/resources/js/chartist.min.js"></script>

    <!--  Notifications Plugin    -->
    <script src="<%=request.getContextPath()%>/resources/js/bootstrap-notify.js"></script>

    <!--  Google Maps Plugin    -->
    <script type="text/javascript" src="https://maps.googleapis.com/maps/api/js"></script>

    <!-- Paper Dashboard Core javascript and methods for Demo purpose -->
    <script src="<%=request.getContextPath()%>/resources/js/paper-dashboard.js"></script>
    
    <!-- Paper Dashboard DEMO methods, don't include it in your project! -->
    <script src="<%=request.getContextPath()%>/resources/js/demo.js"></script>
    
    
    <script src="<%=request.getContextPath()%>/resources/js/tinymce/tinymce.min.js"></script>
    
    <script src="<%=request.getContextPath()%>/resources/js/init-tinymce.js"></script>
    
    <script>
        $( "#post-url" ).blur(function() {
            this.readOnly='readonly';
        });
    </script>

</html>
