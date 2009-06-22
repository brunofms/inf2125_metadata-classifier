<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@page import="java.util.Collection"%>

<div id="menu">
    <h2>MENU</h2>
    <a href="build-model.cmd">Build Model</a>
    <br/>
    <a href="classify-metadata.cmd">Classify Metadata</a>
    <br/>
</div>

<script>
    <%
        Collection<String> messages = (Collection<String>)request.getAttribute("messages");
        if(messages != null) {
            for (String message: messages) {
                out.println("alert('"+message+"');");
            }
        }
    %>
</script>