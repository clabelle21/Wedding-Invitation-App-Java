<%-- 
    Document   : RSVPConfirmation
    Created on : 29-Nov-2019, 10:40:12 PM
    Author     : chris
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.9/angular.min.js"></script>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
        <script src="scripts/genericguestScript.js"></script>
        <link rel="stylesheet" href="style/registration_style.css" />
        <link href='http://fonts.googleapis.com/css?family=Great+Vibes' rel='stylesheet' type='text/css'>
        <title>CT Wedding 2020</title>
    </head>
    <body>
        <header>
            <span id="homeLink"><a href="index.html" >Home</a></span>
            <span id="headerTitle">Labelle-Thongvankham Wedding 2020</span>
        </header>
            <div id="rsvpBox"> <!-- RSVP Box -->
                <table>
                    <tr><h3>Thank you for RSVP-ing. Your RSVP details are:</h3></tr>
                    <tr>
                        <td>Name: </td>
                        <td>
                            <%out.print(request.getAttribute("firstName") != null ? request.getAttribute("firstName") : "");%>
                            <%out.print(" ");%>
                            <%out.print(request.getAttribute("lastName") != null ? request.getAttribute("lastName") : "");%>
                        </td>
                    </tr>
                    <tr>
                        <td>Attending/Absent: </td>
                        <td><%out.print(request.getAttribute("attending") != null ? request.getAttribute("attending") : "");%></td>
                    </tr>
                    <tr>
                        <td>Confirmation ID: </td>
                        <td><%out.print(request.getAttribute("confirmationId") != null ? request.getAttribute("confirmationId") : "");%></td>
                    </tr>
                </table>
            </div>
    </body>
</html>
