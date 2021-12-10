<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>Title</title>
    <!-- CSS only -->

    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css">
    <link rel="stylesheet" href="../css/main.css">

</head>
    <body>


    <nav class="black">
        <div class="nav-wrapper">
            <ul id="nav-mobile" class="right hide-on-med-and-down">
                <c:choose>
                    <c:when test="${not empty sessionScope.account}">
                        <li><a href="/controller?command=logout ">Logout</a></li>
                    </c:when>
                    <c:otherwise>
                        <li><a href="/controller?command=show_login ">Sign in</a></li>
                        <li><a href="/controller?command=show_signup">Sign up</a></li>
                    </c:otherwise>
                </c:choose>
                <li><a href="/controller?command=show_profile">Watch profile</a></li>
            </ul>
        </div>
    </nav>

    <div class="after_nav">
        <div class="typing animate"></div>
        <c:if  test="${not empty sessionScope.account}">
            <p><span>Start learning, </span> ${sessionScope.account.email} <span>!</span></p>
        </c:if>
    </div>

    <div>
        <c:forEach var="course" items="${requestScope.courses}">
            <div class="valign-wrapper">
                <div class="row">
                    <div>
                        <div class="card black darken-1 crad-size">
                            <div class="card-content white-text">
                                <span class="card-title">${course.title}</span>
                                <p>${course.description}</p>

                                <br>
                                <hr>
                                <br>
                                <p><span>Курсы ведет ментор: </span>${course.mentor.pen_name}</p>
                                <p><span>Позиция ментора:</span>${course.mentor.position}</p>
                                <p>Опыт работы:  <span>${course.mentor.experience}</span>
                                    <span>года</span></p>

                            </div>
                            <div class="card-action">
                                <a href="#">Register</a>
                                <a href="#">Execute</a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </c:forEach>
    </div>

    <footer class="page-footer black">
        <div class="container">
            <div class="row">
                <div class="col l6 s12">
                    <h5 class="orange-text">Footer Content</h5>
                    <p class="grey-text text-lighten-4">You can use rows and columns here to organize your footer content.</p>
                </div>
                <div class="col l4 offset-l2 s12">
                    <h5 class="orange-text">Links</h5>
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





<%--        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>--%>
<%--        <script src="/docs/5.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>--%>

                    <script src="../js/main.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>




    </body>
</html>
