<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title><fmt:message key="titulo"/></title>
        <link href="<c:url value="/css/bootstrap.css" />" rel="stylesheet">
        <link href="<c:url value="/css/custom-theme/jquery-ui-1.10.0.custom.css" />" rel="stylesheet">
        <script type="text/javascript" src="<c:url value="/js/jquery-1.9.1.js" />"></script> 
        <script type="text/javascript" src="<c:url value="/js/jquery-ui-1.10.3.custom.js" />"></script> 
        <script type="text/javascript" src="<c:url value="/js/bootstrap.js" />"></script> 
        <style>
            
            body {
                padding-top: 60px;
                padding-bottom: 40px;
            }
            .sidebar-nav {
                padding: 9px 0;
            }
            #geral {
                min-height:100%;
                position:relative;
                width:99%;

            }

            #footer {
                position:absolute;
                bottom:0 !important;
                width:100%;
                text-align: center;
            }

            #conteudo {overflow:hidden;}
            .aside {width:250px;}
            .fleft {float:left;}
        </style>
    </head>
    <body>
        
        
        
        <!-- Barra status superios -->
        <div class="navbar navbar-inverse navbar-fixed-top">
            <div class="navbar-inner">
                <div class="container-fluid">
                    <a class="brand" href="#"><fmt:message key="titulo"/></a>
                    <div class="nav-collapse collapse">
                        <ul class="nav">
                            <li class="active"><a href="#">Home</a></li>
                            <li><a href="#about">About</a></li>
                            <li><a href="#contact">Contact</a></li>
                        </ul>
                        <c:if test="${not empty userInfo.usuario}">
                            <p class="navbar-text pull-right">
                                ${userInfo.usuario.nome} - <a class="navbar-link" href="${pageContext.request.contextPath}/login/logout">Sair</a>
                            </p>
                        </c:if>
                    </div><!--/.nav-collapse -->
                </div>
            </div>
        </div>
        <!-- Barra status superios -->
        
        
        
        <div id="geral" >

            <!-- Menu -->
            <div id="menu" class="aside fleft" >
                <div id="divContainerMenu" class="span3">
                    <div class="well sidebar-nav" style="height: 500px;">
                        <ul class="nav nav-list">
                            <li class="nav-header">Menu</li>
                            <li class="active"><a href="#">Link</a></li>
                            <li><a href="${pageContext.request.contextPath}/usuario"><fmt:message key="user.entity"/></a></li>
                            <li><a href="${pageContext.request.contextPath}/usuario/novo">Novo</a></li>
                            <li><a href="${pageContext.request.contextPath}/usuario/usuarios.json">Json</a></li>
                            <li><a href="#" onclick="testeJson();">Json Jquery</a></li>

                        </ul>
                    </div><!--/.well -->
                </div><!--/span-->
            </div>
            <div id="conteudo">
                <tiles:insertAttribute name="body"/>
            </div>
            <rich:spacer height="20"/>
            
            <!-- Rodape -->
            <div id="footer">
                Powered by Vraptor, Tiles, JQuery, Bootstrap
            </div>
            
        </div>
        
        
    </body>
</html>