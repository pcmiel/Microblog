<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<a class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
  <span class="icon-bar"></span>
  <span class="icon-bar"></span>
  <span class="icon-bar"></span>
</a>
<a class="brand" href="#">Microblog</a>
<div class="nav-collapse collapse">  
  <ul class="nav">
    <li class="active"><a href="<c:url value="/" />">Home</a></li>
    <li><a href="<c:url value="/test" />">test1</a></li>
    <li><a href="<c:url value="/test" />">test2</a></li>
    <li><a href="<c:url value="/test" />">test3</a></li>
    <li><a href="<c:url value="/test" />">test4</a></li>
    <li><a href="<c:url value="/test" />">test5</a></li>
  </ul>
</div>