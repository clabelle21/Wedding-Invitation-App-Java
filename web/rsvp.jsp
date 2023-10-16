<%-- 
    Document   : genericguest
    Created on : 20-Nov-2019, 5:47:12 PM
    Author     : chris
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width,height=device-height,initial-scale=1.0"/>
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
        
        <form method="post" action="RegisterGuest">
                <div id="infoBox"> <!-- Info box-->
                    <table>
                        <h4>Event Details</h4>
                        <%
                            if(session.getAttribute("guestType") != null && Integer.parseInt(session.getAttribute("guestType").toString()) == 2) {
                                out.println("<td nowrap>Ceremony:</td>");
                                out.println("<td nowrap>May 9 2020 11:00 AM</td>");
                                out.println("<td nowrap>@ 22 Lloydalex Crescent</td>");
                            }
                        %>
                        <tr>
                            <td nowrap>Reception:</td>
                            <td nowrap>May 9 2020 6:30 PM</td>
                            <td nowrap>@ 1000 Byron Avenue</td>
                        </tr>
                    </table>
                </div>
                <div id="nameBox"> <!-- Name Box -->
                    <table>
                        <tr>
                            <td>First name</td><td>Last name</td>
                        </tr>
                        <tr>
                            <td>
                                <input type="text" name="firstName" id="firstName"
                                    <%out.print("value=\"" + (session.getAttribute("firstName") != null ? session.getAttribute("firstName") : "") + "\"");%>
                                >
                            </td>
                            <td>
                                <input type="text" name="lastName" id="lastName"
                                    <%out.print("value=\"" + (session.getAttribute("lastName") != null ? session.getAttribute("lastName") : "") + "\"");%>
                                >
                            </td>
                        </tr>
                    </table>
                </div>
                <div id="rsvpBox"> <!-- RSVP Box -->
                    <h4>RSVP</h4>
                    <table>
                        <tr>
                            <td><label for="attending">Attending</label></td>
                            <td><label for="absent">Absent</label></td>
                        </tr>
                        <tr>
                            <td><input type="radio" name="isAttending" id="attending" value="attending"
                                <%
                                    if(session.getAttribute("attending") != null 
                                        && session.getAttribute("attending").toString().equals("attending")) {
                                        out.print("checked=\"checked\"");
                                    }
                                %>
                            ></td>
                            <td><input type="radio" name="isAttending" id="absent" value="absent"
                                <%
                                    if(session.getAttribute("attending") != null 
                                        && session.getAttribute("attending").toString().equals("absent")) {
                                        out.print("checked=\"checked\"");
                                    }
                                %>
                            ></td>
                        </tr>
                    </table>
                </div>
                <div class="js_hidden" id="allergyBox"> <!-- Allergy Box -->
                    <h4>Food Sensitivities</h4>
                    <table>
                        <tr>
                            <td><label for="vegetarian">Vegetarian</label></td>
                            <td><label for="vegan">Vegan</label></td>
                            <td><label for="gluten">Gluten Intolerance</label></td>
                            <td>Other Allergies</td>
                        </tr>
                        <tr>
                            <td><input type="checkbox" name="vegetarian" id="vegetarian" value="vegetarian"
                                <%
                                    if(session.getAttribute("vegetarian") != null 
                                        && session.getAttribute("vegetarian").toString().equals("true")) {
                                        out.print("checked=\"checked\"");
                                    }
                                %>
                            ></td>
                            <td><input type="checkbox" name="vegan" id="vegan" value="vegan"
                                <%
                                    if(session.getAttribute("vegan") != null 
                                        && session.getAttribute("vegan").toString().equals("true")) {
                                        out.print("checked=\"checked\"");
                                    }
                                %>
                            ></td>
                            <td><input type="checkbox" name="gluten" id="gluten" value="gluten"
                                <%
                                    if(session.getAttribute("gluten") != null 
                                        && session.getAttribute("gluten").toString().equals("true")) {
                                        out.print("checked=\"checked\"");
                                    }
                                %>
                            ></td>
                            <td><input type="text" name="otherAllergy" id="otherAllergy"
                                <%
                                    if(session.getAttribute("otherAllergy") != null) {
                                        out.print("value=\"" + session.getAttribute("otherAllergy") + "\"");
                                    }
                                %>
                            ></td>
                        </tr>
                    </table>
                </div>
                <div class="js_hidden" id="emailBox"> <!-- Email Box -->
                    <h4>Enter your email to receive updates and reminders</h4>
                    <input type="text" name="email" placeholder="(Optional)" id="email"
                        <%
                            if(session.getAttribute("email") != null) {
                                out.print("value=\"" + session.getAttribute("email") + "\"");
                            }
                        %>
                    ></input>
                </div>
                <div class="spacing"></div>
                <div id="buttonWrapper">
                    <table>
                        <tr><td><input type="submit" value="RSVP"></td></tr>
                            <%
                                if(session.getAttribute("errorMessage") != null) {
                                    String[] errors = session.getAttribute("errorMessage").toString().split("`");
                                    for(String s : errors) {
                                        out.println("<tr><td style=\"color: #ac6052\">" + s + "</td></tr>");
                                    }
                                }
                            %>
                    </table>
                </div>
        </form>
    </body>
</html>
