<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
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


<!------------------------------>
<!---------- Controls ---------->
<!------------------------------>
<div class="container actions">
  <p class="flow-text">${student.getName()}</p>
  <div class="info-students-actions">

    <!-- REPLACE THE ACTION VALUE TO SERVLET PATH -->
    <form action="${pageContext.request.contextPath}/updatestudent" method="get">
      <button class="waves-effect teal waves-light btn btn-large btn-floating">
        <i class="material-icons">create</i>
      </button>
      <input type="hidden" name="studentId" value="${student.getId()}"/>
    </form>

    <!-- REPLACE THE ACTION VALUE TO SERVLET PATH -->
    <form action="${pageContext.request.contextPath}/student" method="get">
      <button class="waves-effect teal waves-light btn btn-large btn-floating">
        <i class="material-icons">arrow_back</i>
      </button>
    </form>

  </div>
</div>

<!-------------------------->
<!---------- Body ---------->
<!-------------------------->
<div class="container student-info-container">

  <!------------------------------------------>
  <!---------- Personal Information ---------->
  <!------------------------------------------>
  <div class="col s4 personal-info-container">

    <!-- title -->
    <div class="personal-info-title">
      <strong>Personal information</strong>
    </div>

    <!-- name -->
    <div class="personal-info-row">
      <label class="info-label">Name</label>
      <span>${student.getName()}</span>
    </div>

    <!-- email -->
    <div class="personal-info-row">
      <label class="info-label">Email</label>
      <span>${student.getEmail()}</span>
    </div>

    <!-- age -->
    <div class="personal-info-row">
      <label class="info-label">Age</label>
      <span>${student.getAge()}</span>
    </div>

    <!-- gender -->
    <div class="personal-info-row">
      <label class="info-label">Gender</label>
      <c:if test="${student.getGender().toString() == 'f'}">
        <span>Female</span>
      </c:if>
      <c:if test="${student.getGender().toString() == 'm'}">
        <span>Male</span>
      </c:if>
    </div>
  </div>

  <!----------------------------->
  <!---------- Courses ---------->
  <!----------------------------->
  <div class="courses-info-container">

    <!--------------------------------------->
    <!---------- Available Courses ---------->
    <!--------------------------------------->
    <p><strong style="color: #00897b;">Enroll me</strong></p>
    <div class="row">
      <form
        action="${pageContext.request.contextPath}/createinscription"
        method="post"
        class="col s12"
      >
        <div class="row">
          <div class="input-field col s10">
            <select name="courseId">
              <c:forEach var="course" items="${courses}">
                <option value="${course.getId() }">${course.getName()}</option>
              </c:forEach>
            </select>
          </div>

          <input type="hidden" name="studentId" value="${student.getId()}">

          <div class="input-field col s1">
            <button class="btn waves-effect waves-light text-center" type="submit" name="action">
              <i class="material-icons">send</i>
            </button>
          </div>
        </div>
      </form>
    </div>

    <!-------------------------------->
    <!---------- My Courses ---------->
    <!-------------------------------->
    <p><strong style="color: #00897b;">My Courses</strong></p>
    <table class="striped">
      <thead>
      <tr>
        <th>Name</th>
        <th>Actions</th>
      </tr>
      </thead>

      <tbody>
      <c:forEach var="inscription" items="${inscriptions}">
        <tr>
          <td>${inscription.getCourseName()}</td>
          <td>
            <!-- delete -->
            <!-- REPLACE THE ACTION VALUE TO SERVLET PATH -->
            <form action="${pageContext.request.contextPath}/deleteinscription" method="post">
              <button class="waves-effect waves-light btn btn-floating">
                <i class="material-icons">delete</i>
              </button>

              <input type="hidden" name="inscriptionId" value="${inscription.getId()}"/>
              <input type="hidden" name="studentId" value="${student.getId()}"/>
            </form>
          </td>
        </tr>
      </c:forEach>
      </tbody>
    </table>
  </div>
</div>

<!-- Compiled and minified JavaScript -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>
<script src="js/script.js"></script>
</body>
</html>