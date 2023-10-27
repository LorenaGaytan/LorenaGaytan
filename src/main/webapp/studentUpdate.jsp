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

<!------------------------------>
<!---------- Controls ---------->
<!------------------------------>
<div class="container actions">
  <p class="flow-text">Update Student</p>

  <!-- REPLACE THE ACTION VALUE TO SERVLET PATH -->
  <form action="${pageContext.request.contextPath}/studentdetails" method="get">
    <button class="waves-effect teal waves-light btn btn-large btn-floating">
      <i class="material-icons">arrow_back</i>
    </button>
    <input type="hidden" name="studentId" value="${student.getId()}"/>
  </form>

</div>

<!-------------------------->
<!---------- Form ---------->
<!-------------------------->
<div class="container main">
  <div class="row main-form">

    <!-- REPLACE THE ACTION VALUE TO SERVLET PATH -->
    <!-- CHANGE METHOD FROM GET TO POST -->
    <form action="${pageContext.request.contextPath}/updatestudent" method="post" class="col s12">

      <!-- name -->
      <div class="row">
        <div class="input-field col s12">
          <input required id="name" value="${student.getName()}" name="name" type="text">
          <label for="name">Name</label>
        </div>
      </div>

      <!-- email -->
      <div class="row">
        <div class="input-field col s12">
          <input required id="email" name="email" value="${student.getEmail()}" type="email">
          <label for="email">Email</label>
        </div>
      </div>

      <div class="row">
        <!-- age -->
        <div class="input-field col s6">
          <input required id="age" value="${student.getAge()}" name="age" type="number">
          <label for="age">Age</label>
        </div>

        <!-- gender -->
        <div class="input-field col s6">
          <select name="gender">
            <option
              value="m"
              <c:if test="${student.getGender().toString() == 'm'}">selected</c:if>
            >
              Male
            </option>
            <option
              value="f"
              <c:if test="${student.getGender().toString() == 'f'}">selected</c:if>
            >
              Female
            </option>
          </select>
          <label>Gender</label>
        </div>
      </div>

      <!-- student id -->
      <input type="hidden" name="studentId" value="${student.getId()}">

      <!-- action button -->
      <div class="row">
        <div class="col s12">
          <button style="width: 100%;" class="btn waves-effect waves-light" type="submit">Update
            <i class="material-icons right">send</i>
          </button>
        </div>
      </div>

    </form>
  </div>
</div>

<!-- Compiled and minified JavaScript -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>
<script src="js/script.js"></script>
<c:if test="${error != null}">
  <script>
      M.toast({html: '<c:out value="${error}" />'})
  </script>
</c:if>
</body>
</html>