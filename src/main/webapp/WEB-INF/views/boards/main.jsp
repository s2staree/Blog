<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="../layout/header.jsp"%>

<div class="container">

	<table class="table table-striped">
		<thead>
			<tr>
				<th>번호</th>
				<th>게시글제목</th>
				<th>작성자이름</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="boards" items="${boardsList}">
				<tr>
					<td>${boards.id}</td>
					<td><a href="/boards/${boards.id}">${boards.title}</a></td>
					<td>${boards.username}</td>
				</tr>
			</c:forEach>

		</tbody>
	</table>
	
	<div class = "d-fex justify-content-center">
   	<ul class="pagination">
      <li class='page-item ${paging.first ? "disabled" : " "}'>
	     <a class="page-link"
	         href="?page=${paging.currentPage-1}">
	         Prev
	     </a>
         </li>
      <c:forEach var="num" begin="${paging.startPageNum}" end="${paging.lastPageNum}" step="1">
         <li class="page-item" ${paging.currentPage == num-1 ? "active" : ""}>
	         <a class="page-link" href="?page=${num-1}">
	         ${num}
	         </a>
         </li>
      </c:forEach>
      <li class='page-item ${paging.last ? "disabled" : " "}'>
	      <a class="page-link"
	         href="?page=${paging.currentPage+1}">
	         Next
         </a>
         </li>
   	</ul>
	</div>
	
	<!-- 
	<a href="?page=${paging.currentPage-1}">이전페이지</a>
	<c:forEach var="num" begin="1" end="${paging.totalPage}" step="1">
		<a href="?page=${num-1}">${num}</a>
	</c:forEach>
	<a href="?page=${paging.currentPage+1}">다음페이지</a>
	 -->

	<div style="background-color: grey">
		<h3>blockCount : ${paging.blockCount}</h3>
		<h3>currentBlock : ${paging.currentBlock}</h3>
		<h3>startPageNum : ${paging.startPageNum}</h3>
		<h3>lastPageNum : ${paging.lastPageNum}</h3>
		<h3>totalCount : ${paging.totalCount}</h3>
		<h3>totalPage : ${paging.totalPage}</h3>
		<h3>currentPage : ${paging.currentPage}</h3>
		<h3>isLast : ${paging.last}</h3>
		<h3>isFirst : ${paging.first}</h3>
	</div>
	
</div>

<%@ include file="../layout/footer.jsp"%>

