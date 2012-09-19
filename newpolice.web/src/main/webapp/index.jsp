<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>New Police CI project</title>
</head>
<body>
  <p>
  Welcome to New Police @ pkgplan! With CI, Life is better.
  </p>
  <p>
  <ul>
    <c:forEach var="message" items="${messages}">
      <li>
        <div><c:out value="${message.text}"/></div>
        <div><a href="formText?id=<c:out value="${message.id}"/>">Delete</a></div>
      </li>
    </c:forEach>
  </ul>
  </p>
  <p>
    <form action="formText" method="post">
        <input type="text" name="text" />
        <input type="submit" value="Create" />
    </form>
  </p>
</body>
</html>
<script type="text/javascript" src="<c:url value="/common/json2.js" />"></script>
