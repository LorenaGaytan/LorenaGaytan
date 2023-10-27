<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <meta http-equiv="X-UA-Compatible" content="ie=edge">

  <!-- Compiled and minified CSS -->
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css">
  <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
  <link rel="stylesheet" href="css/styles.css">
  <title>Students</title>
</head>

<body>

<!-------------------------->
<!---------- Menu ---------->
<!-------------------------->
<%@ include file="components/menu.jsp" %>

<!-------------------------->
<!---------- Controls ------>
<!-------------------------->
<div class="container actions">
  <p class="flow-text">Students</p>
  <!-- REPLACE THE ACTION VALUE TO SERVLET PATH -->
  <form action="${pageContext.request.contextPath}/createstudent" method="get">
    <button class="waves-effect teal waves-light btn btn-large btn-floating">
      <i class="material-icons">add</i>
    </button>
  </form>
</div>

<!-------------------------------->
<!---------- Students Table ------>
<!-------------------------------->
<div class="container main">
  <table class="striped">
    <thead>
    <tr>
      <th>#</th>
      <th>Name</th>
      <th>Email</th>
      <th>Actions</th>
    </tr>
    </thead>

    <tbody>
    <c:forEach var="student" items="${students}">
      <tr>
        <!-- id -->
        <td>${student.getId()}</td>

        <!-- name -->
        <td>${student.getName()}</td>

        <!-- email -->
        <td>${student.getEmail()}</td>

        <!-- actions -->
        <td>
          <div class="actions-row">
            <!-- update -->
            <!-- REPLACE THE ACTION VALUE TO SERVLET PATH -->
            <form action="${pageContext.request.contextPath}/studentdetails" method="get">
              <button class="waves-effect waves-light btn btn-floating">
                <i class="material-icons">remove_red_eye</i>
              </button>
              <input type="hidden" name="studentId" value="${student.getId()}"/>
            </form>

            <!-- delete -->
            <!-- REPLACE THE ACTION VALUE TO SERVLET PATH -->
            <form action="${pageContext.request.contextPath}/deletestudent" method="post">
              <button class="waves-effect waves-light btn btn-floating">
                <i class="material-icons">delete</i>
              </button>
              <input type="hidden" name="studentId" value="${student.getId()}"/>
            </form>
          </div>
        </td>
      </tr>
    </c:forEach>
    </tbody>
  </table>
</div>

<!-- Compiled and minified JavaScript -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>
<script src="js/script.js"></script>
</body>
</html>