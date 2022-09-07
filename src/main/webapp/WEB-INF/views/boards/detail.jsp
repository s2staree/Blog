<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="../layout/header.jsp"%>

<div class="container">
	<br /> <br />
	
	<div class="d-flex">
	<form>
		<button class="btn btn-warning">
		수정하러가기
		</button>
	</form>
	
	<form action="/boards/${boards.id}/delete" method="post">
	<!-- form에는 delete메서드가 없으므로 혼자 삭제 못함. method를 추가해줘야 함. -->
		<button class="btn btn-danger">
		삭제수행
		</button>
	</form>
	</div>
	
	
	<div>
		<h3>${boards.title}</h3>
	</div>
	<hr/>

	<div>${boards.content}</div>


</div>

<%@ include file="../layout/footer.jsp"%>

