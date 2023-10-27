<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <meta http-equiv="X-UA-Compatible" content="ie=edge">

  <!-- CSS -->
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css">
  <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
  <link rel="stylesheet" href="css/styles.css">
  <title>Courses</title>
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
  <p class="flow-text">Add Course</p>
  <!-- REPLACE THE ACTION VALUE TO SERVLET PATH -->
  <form action="${pageContext.request.contextPath}/course" method="get">
    <button class="waves-effect teal waves-light btn btn-large btn-floating">
      <i class="material-icons">arrow_back</i>
    </button>
  </form>
</div>

<!-------------------------->
<!---------- Form ---------->
<!-------------------------->
<div class="container main">
  <div class="row main-form">

    <!-- REPLACE THE ACTION VALUE TO SERVLET PATH -->
    <!-- CHANGE METHOD FROM GET TO POST -->
    <form action="${pageContext.request.contextPath}/createcourse" method="post" class="col s12">

      <!-- name -->
      <div class="row">
        <div class="input-field col s12">
          <input required id="name" name="name" type="text">
          <label for="name">Name</label>
        </div>
      </div>

      <!-- action button -->
      <div class="row">
        <div class="col s12">
          <button style="width: 100%;" class="btn waves-effect waves-light" type="submit">
            Save
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

</body>
</html>