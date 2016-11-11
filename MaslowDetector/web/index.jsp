<%-- 
    Document   : index
    Created on : 09-oct-2016, 18:12:06
    Author     : Borja Bordel (Proyecto Semola)
--%>

<%-- Directivas--%>
<%@page import="java.util.ArrayList"%>
<%@page import="main.MaslowController"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
  <head>
        <meta http-equiv="content-type" content="text/html; charset=UTF-8">  
        <meta name="robots" content="noindex, nofollow">
        <meta name="googlebot" content="noindex, nofollow">    
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>SEMOLA project - Management panel</title>
	<link rel="icon" href="img/favicon.png">
        <link rel="stylesheet" type="text/css" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap-theme.min.css">    
	<link rel="stylesheet" type="text/css" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">    
        <link rel="stylesheet" type="text/css" href="css/custom.css">
    </head>
    <body>
        <%
            // Set refresh, autoload time as 60 seconds
            response.setIntHeader("Refresh", 60);            
        %>
        <div id="cabecera">
	<div class="logo_title"><img id = "etsit" src="img/etsit_wt.gif"></div>
	<div id="logo_right" class="logo_title"><img id = "upm" src="img/upm_wt.gif"></div>
	</div>	
		
	<h1>	
		SEMOLA PROJECT		
	</h1>
	<h2>AUTOMATIC USER NEEDS ESTIMATION - MANAGEMENT PANEL</h2>
        
        <div id="contenido">            
            <div id="logs">
                <p> <strong>LOG MESSAGES</strong> </p>

                <%-- JSTL--%>
                <c:forEach var="mess" items="${log}">
                    <p> <c:out value="${mess}"/> </p>
                </c:forEach>
            </div>
            <div id="botones">
                <form action="InitialActions" method="post">
                        <button type="submit" name="button" value="Initiate">Start</button>
                        <button type="submit" name="button" value="Stop">Stop</button>
                </form>        

                <a href="affectiva/index.html" target="_blank"><button type="submit" id="openVideo" value="true" >Open video capture panel</button></a>
                <a href="pages/devices.html" target="_blank"><button type="submit" id="openDevices" value="true">Open personal device panel</button></a> 
                <a href="pages/global.html" target="_blank"><button type="submit" id="openDevices" value="true">Open global panel</button></a>
            </div>    
            <div class="nofloating">
                <p> <strong>PERSONAL CONNECTED DEVICES</strong> </p>
                <div id="tablaDevices">			  
                    <table>
                            <tr>
                                <th><strong>Device information</strong></th>
                            </tr>
                            <%
                                MaslowController.createController();
                                ArrayList <String> devices = MaslowController.getController().getConnectedDevices();
                                if (devices != null && devices.size() > 0) {
                                    for(String device : devices) {
                            %>
                                        <tr>
                                            <th class ="idNumber"><%out.println(device);%></th>
                                        </tr>
                            <%        }
                                } else {
                            %>
                                <tr>
                                    <th class ="idNumber"></th>
                                </tr>
                            <%
                                } 
                            %>
                     </table>
                </div>
                <div id="video">
                    <p> <strong>VIDEO CAPTURE SYSTEM</strong> </p>
                    <div id="tablaCameras">			  
                        <table>
                                <tr>
                                    <th><strong>Video capture status</strong></th>                           
                                </tr>
                                <tr>
                                    <th id ="videoStatus"></th>
                                </tr>							  
                         </table>
                    </div>
                </div>
            </div>
            <div class="footer">
                <p>
                    <strong>FUNDING</strong>
                    <br/>
                    This application has been partially funded by the Ministry of Economy and Competitiveness through SEMOLA project (TEC2015-68284-R)
                </p>
                <img class="logo_footer" id="logo_semola" src="img/semola_logo.jpg">
                <img class="logo_footer" id="logo_economia" src="img/economia.jpg">
            </div>
        </div>
    </body>
</html>
