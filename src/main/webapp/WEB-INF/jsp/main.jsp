<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>Title</title>
    <!-- CSS only -->
    <link rel="stylesheet" href="../css/main.css">

    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css">

</head>
    <body>
    <!-- Dropdown Structure -->
    <nav class="black">
        <div class="nav-wrapper">
            <a href="#" class="brand-logo"> ♥ Training Centre</a>
            <ul id="nav-mobile" class="right hide-on-med-and-down">
                <li><a href="/controller?command=show_login">Sign in</a></li>
                <li><a href="/controller?command=sign_up">Sign up</a></li>
                <li><a href="collapsible.html">Watch profile</a></li>
            </ul>
        </div>
    </nav>

    <div class="card-wrapper">
        <c:forEach var="course" items="${requestScope.courses}">
            <div class="row">
                <div class="col s12 m6">
                    <div class="card black darken-1">
                        <div class="card-content white-text">
                            <span class="card-title">${course.title}</span>
                            <p>${course.description}</p>
                        </div>
                        <div class="card-action">
                            <a href="#">Register</a>
                            <a href="#">Execute</a>
                        </div>
                    </div>
                </div>
            </div>
        </c:forEach>
    </div>





<%--    <ul class="collection">--%>
<%--        <li class="collection-item avatar">--%>
<%--            <img src="images/yuna.jpg" alt="" class="circle">--%>
<%--            <span class="title">Title</span>--%>
<%--            <p>First Line <br>--%>
<%--                Second Line--%>
<%--            </p>--%>
<%--            <a href="#!" class="secondary-content"><i class="material-icons">grade</i></a>--%>
<%--        </li>--%>
<%--        <li class="collection-item avatar">--%>
<%--            <i class="material-icons circle">folder</i>--%>
<%--            <span class="title">Title</span>--%>
<%--            <p>First Line <br>--%>
<%--                Second Line--%>
<%--            </p>--%>
<%--            <a href="#!" class="secondary-content"><i class="material-icons">grade</i></a>--%>
<%--        </li>--%>
<%--        <li class="collection-item avatar">--%>
<%--            <i class="material-icons circle green">insert_chart</i>--%>
<%--            <span class="title">Title</span>--%>
<%--            <p>First Line <br>--%>
<%--                Second Line--%>
<%--            </p>--%>
<%--            <a href="#!" class="secondary-content"><i class="material-icons">grade</i></a>--%>
<%--        </li>--%>
<%--        <li class="collection-item avatar">--%>
<%--            <i class="material-icons circle red">play_arrow</i>--%>
<%--            <span class="title">Title</span>--%>
<%--            <p>First Line <br>--%>
<%--                Second Line--%>
<%--            </p>--%>
<%--            <a href="#!" class="secondary-content"><i class="material-icons">grade</i></a>--%>
<%--        </li>--%>
<%--    </ul>--%>

    <ul class="pagination">
        <li class="disabled"><a href="#!"><i class="material-icons"><</i></a></li>
        <li class="active"><a href="#!">1</a></li>
        <li class="waves-effect"><a href="#!">2</a></li>
        <li class="waves-effect"><a href="#!">3</a></li>
        <li class="waves-effect"><a href="#!">4</a></li>
        <li class="waves-effect"><a href="#!">5</a></li>
        <li class="waves-effect"><a href="#!"><i class="material-icons">></i></a></li>
    </ul>

    <footer class="page-footer black">
        <div class="container">
            <div class="row">
                <div class="col l6 s12">
                    <h5 class="white-text">Footer Content</h5>
                    <p class="grey-text text-lighten-4">You can use rows and columns here to organize your footer content.</p>
                </div>
                <div class="col l4 offset-l2 s12">
                    <h5 class="white-text">Links</h5>
                    <ul>
                        <li><a class="grey-text text-lighten-3" href="#!">Link 1</a></li>
                        <li><a class="grey-text text-lighten-3" href="#!">Link 2</a></li>
                        <li><a class="grey-text text-lighten-3" href="#!">Link 3</a></li>
                        <li><a class="grey-text text-lighten-3" href="#!">Link 4</a></li>
                    </ul>
                </div>
            </div>
        </div>
        <div class="footer-copyright">
            <div class="container">
                © 2014 Copyright Text
                <a class="grey-text text-lighten-4 right" href="#!">More Lin


    <%--        <header class="p-3 bg-dark text-white">--%>
<%--            <div class="container">--%>
<%--                <div class="d-flex flex-wrap align-items-center justify-content-center justify-content-lg-start">--%>
<%--                    <a href="/" class="d-flex align-items-center mb-2 mb-lg-0 text-white text-decoration-none">--%>
<%--                        <svg class="bi me-2" width="40" height="32" role="img" aria-label="Bootstrap"><use xlink:href="#bootstrap"></use></svg>--%>
<%--                    </a>--%>

<%--                    <ul class="nav col-12 col-lg-auto me-lg-auto mb-2 justify-content-center mb-md-0">--%>
<%--                        <li><a href="#" class="nav-link px-2 text-secondary">Home</a></li>--%>
<%--                        <li><a href="#" class="nav-link px-2 text-white">Features</a></li>--%>
<%--                        <li><a href="#" class="nav-link px-2 text-white">Pricing</a></li>--%>
<%--                        <li><a href="#" class="nav-link px-2 text-white">About</a></li>--%>
<%--                    </ul>--%>

<%--                    <form class="col-12 col-lg-auto mb-3 mb-lg-0 me-lg-3">--%>
<%--                        <input type="search" class="form-control form-control-dark" placeholder="Search..." aria-label="Search">--%>
<%--                    </form>--%>

<%--                    <div class="text-end">--%>
<%--                        <button type="button" class="btn btn-outline-light me-2" >--%>
<%--                            <a href="/controller?command=show_login">Login</a>--%>
<%--                        </button>--%>
<%--                        <button type="button" class="btn btn-warning">--%>
<%--                            <a href="/controller?command=sign_up">Sign-up</a>--%>
<%--                        </button>--%>
<%--                    </div>--%>

<%--                </div>--%>
<%--            </div>--%>
<%--        </header>--%>




<%--        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>--%>
<%--        <script src="/docs/5.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>--%>

        <script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>






    </body>
</html>
