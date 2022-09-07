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
			<!-- 여기에 forEach 필요하겠죠? -->
			<tr>
				<td>1</td>
				<td><a href="/boards/1">스프링1강</a></td>
				<td>ssar</td>
			</tr>
		</tbody>
	</table>

	<a href="?page=${paging.currentPage+1}">다음페이지</a>

	<div style="background-colar: 'grey';">
		<h3>totalCount : ${paging.totalCount}</h3>
		<h3>totalPage : ${paging.totalPage}</h3>
		<h3>currentPage : ${paging.currentPage}</h3>
		<h3>isLast : ${paging.isLast}</h3> <h3>isFirst : ${paging.isFirst}</h3>
	</div>

<%@ include file="../layout/footer.jsp"%>

