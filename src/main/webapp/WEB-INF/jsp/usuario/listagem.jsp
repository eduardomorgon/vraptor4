<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<tiles:insertTemplate template="/WEB-INF/jsp/template.jsp">

    <tiles:putAttribute name="body">
        <style>
            .menu-novo{
                padding-left: 10px;
            }
        </style>
        <br/>
        <div class="menu-novo">
            <a href="${pageContext.request.contextPath}/usuario/novo" title="<fmt:message key="user.novo"/>">
                <i class="icon-file"></i>
            </a>
        </div>
        <br/>
        <table id="tableUsers" class="table table-bordered">
            <thead>
                <tr>
                    <th style="text-align: center;"><fmt:message key="user.email"/></th>
                    <th style="text-align: center;"><fmt:message key="user.nome"/></th>
                    <th style="text-align: center;"><fmt:message key="editar"/></th>
                    <th style="text-align: center;"><fmt:message key="excluir"/></th>
                </tr>
            </thead>
            <tbody>
            <c:forEach items="${listUsuario}" var="_v">
                <tr>
                    <td>${_v.email}</td>
                    <td>${_v.nome}</td>
                    <td style="text-align: center;">
                        <form action="<c:url value='/usuario'/>" method="post">
                            <input type='hidden' name='_method' value='put'/>
                            <input type='hidden' name='usuario.id' value='${_v.id}'/>
                            <input type='hidden' name='cid' value='${javax.enterprise.context.conversation.id}'/>
                            <input class="btn btn-mini" type="submit" value="<fmt:message key="editar"/>"/>
                        </form>
                    </td>
                    <td style="text-align: center;">
                        <form action="<c:url value='/usuario'/>" method="post">
                            <input type='hidden' name='_method' value='delete'/>
                            <input type='hidden' name='usuario.id' value='${_v.id}'/>
                            <input class="btn btn-mini" type="submit" value="<fmt:message key="excluir"/>"/>
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
               cid=${javax.enterprise.context.conversation.id} 
    <div id="myModal" class="modal hide" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
        <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
            <h3 id="myModalLabel">Usuários</h3>
        </div>
        <div class="modal-body">
            <table id="users" class="table table-bordered">
                <thead>
                    <tr class="ui-widget-header ">
                        <th>Email</th>
                        <th>Nome</th>
                    </tr>
                </thead>
                <tbody>

                </tbody>
            </table>
        </div>
        <div class="modal-footer">
            <button class="btn" data-dismiss="modal" aria-hidden="true">Close</button>
            <button class="btn btn-primary">Save changes</button>
        </div>
    </div>
               
        
    <div id="dialog" title="Usuarios">
        <table id="users_" class="table table-bordered">
            <thead>
                <tr class="ui-widget-header ">
                    <th>Email</th>
                    <th>Nome</th>
                </tr>
            </thead>
            <tbody>

            </tbody>
        </table>
    </div> 
    
    <div id="dialogFormUser" title="Cadastrar Usuarios">
        <form id="formUsuario">
            <fmt:message key="user.email"/>:<input type="text" name="usuario.email" value="${usuario.email}" /><br/>
            <fmt:message key="user.nome"/>: <input type="text" name="usuario.nome" value="${usuario.nome}"/><br/>
            <fmt:message key="user.senha"/>:<input type="password" name="usuario.senha"/><br/>
            <c:forEach items="${usuario.telefones}" var="_v">
                <li>${_v.telefonePk.telefone}</li>
                <li>${_v.tipo}</li>
            </c:forEach>
            
            
                <input type="button" value="<fmt:message key="salvar"/>" onclick="salvarUser();"/>
        </form>
    </div>

   <script>
        // <![CDATA[
        function testeJson(){
            $.getJSON("<c:url value='/usuario/usuarios.json'/>", "", function(json) {   
                $("#users tbody tr").remove();
                for(var i=0;i < json.list.length;i++){
                    $( "#users tbody" ).append( "<tr>" +
                        "<td>" + json.list[i].email + "</td>" +
                        "<td>" + json.list[i].nome + "</td>" +
                      "</tr>" );
                }
            });    
            //$( "#dialog" ).dialog( "open" );
            $('#myModal').modal('show');
        }
        $(function() {
            $( "#dialog" ).dialog({autoOpen: false,
                                   resizable: false});
        });
        
       
    
        function salvarUser(){
            alert($("#formUsuario").serialize());
            $.ajax({
                type: 'POST',
                url: '<c:url value="/usuario/ajax"/>',
                data: $("#formUsuario").serialize(),
                success: function(data){
                    $('#dialogFormUser').dialog('close');
                    $.getJSON("<c:url value='/usuario/usuarios.json'/>", "", function(json) {   
                        $("#tableUsers tbody tr").remove();
                        $("#tableUsers").addClass("table table-bordered");
                        for(var i=0;i < json.list.length;i++){
                            $( "#tableUsers tbody" ).append( "<tr>" +
                                "<td>" + json.list[i].email + "</td>" +
                                "<td>" + json.list[i].nome + "</td>" +
                                "<td>  <input type='submit' value='<fmt:message key='excluir'/>'/></td>"+
                                "<td>  <input type='submit' value='<fmt:message key='editar'/>'/></td>"+
                              "</tr>" );
                        }
                        $("#tableUsers td input").addClass("btn btn-mini");
                    });    
                }
            });
        }

       function formUser() {
           $.ajax({
               type: 'GET',
               url: '<c:url value="/usuario/novo"/>',
               cache: false,
               success: function(data){
                    $('#dialogFormUser').dialog('open');
               }
           });
           
       }
       
       $(function() {
            $("#dialogFormUser").dialog({autoOpen: false,
                                   resizable: false});
        });


        // ]]>
    </script>
    </tiles:putAttribute>

</tiles:insertTemplate>