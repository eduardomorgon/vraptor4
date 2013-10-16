<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
    <head>
        <meta charset="utf-8">
        <title>Login - Bootstrap</title>
        <link href="<c:url value="/css/bootstrap.css" />" rel="stylesheet">
        <script type="text/javascript" src="<c:url value="/js/jquery-1.9.1.js" />"></script> 
        <script type="text/javascript" src="<c:url value="/js/bootstrap.js" />"></script> 
        <style type="text/css">
            /* Override some defaults */
            html, body {
                background-color: #eee;
            }
            body {
                padding-top: 160px;
                padding-bottom: 40px;
            }
            .container {
                width: 300px;
            }

            /* The white background content wrapper */
            .container > .content {
                background-color: #fff;
                padding: 20px;
                margin: 0 -20px; 
                -webkit-border-radius: 10px 10px 10px 10px;
                -moz-border-radius: 10px 10px 10px 10px;
                border-radius: 10px 10px 10px 10px;
                -webkit-box-shadow: 0 1px 2px rgba(0,0,0,.15);
                -moz-box-shadow: 0 1px 2px rgba(0,0,0,.15);
                box-shadow: 0 1px 2px rgba(0,0,0,.15);
            }

            .login-form {
                margin-left: 45px;
            }
            #errors{
                width: 200px;
                text-align: center;
            }
           
            #errors li {
                list-style: none;
                margin: 0 auto;
            }

        </style>

    </head>
    <body>
        <div class="navbar navbar-inverse navbar-fixed-top">
            <div class="navbar-inner">
                <div class="container-fluid">
                    <a class="brand" href="<c:url value="/"/>"><fmt:message key="titulo"/></a>
                </div>
            </div>
        </div>
        <div class="container">
            <div class="content">
                <div class="row">
                    <div class="login-form">
                        <h2>Login</h2>
                        <c:if test="${not empty errors}">
                            <div id="errors" class="alert alert-error">
                                <span class="close" data-dismiss="alert">&times;</span>
                                <c:forEach items="${errors}" var="error">
                                    ${error.message}
                                </c:forEach>
                            </div>
                        </c:if>
                        <form action="<c:url value="/home/login"/>" method="post">
                            <fieldset>
                                <div class="clearfix">
                                    <input type="email" placeholder="Email" name="login" required="true" autofocus="true"/>
                                </div>
                                <div class="clearfix">
                                    <input type="password" placeholder="Senha" name="password"/>
                                </div>
                                <button class="btn btn-primary" type="submit">Entrar</button>
                            </fieldset>
                        </form>
                    </div>
                </div>
            </div>
        </div> <!-- /container -->
    </body>
</html>