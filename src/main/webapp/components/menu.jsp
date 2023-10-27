<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="jakarta.tags.core" %>
<div>
  <nav class="teal">
    <div class="nav-wrapper container">
      <a href="#" class="brand-logo">SchoolManage</a>
      <ul id="nav-mobile" class="right">
        <li>
          <!-- REPLACE THE ACTION VALUE TO SERVLET PATH -->
          <form action="${pageContext.request.contextPath}/student" method="get">
            <a>
              <input
                type="submit"
                value="students"
                class="btn-flat"
                style="color: #fff; text-transform: capitalize;"
              />
            </a>
          </form>
        </li>
        <li>
          <!-- REPLACE THE ACTION VALUE TO SERVLET PATH -->
          <form action="${pageContext.request.contextPath}/course" method="get">
            <a>
              <input
                type="submit"
                value="courses"
                class="btn-flat"
                style="color: #fff; text-transform: capitalize;"
              />
            </a>
          </form>
        </li>
      </ul>
    </div>
  </nav>
</div>