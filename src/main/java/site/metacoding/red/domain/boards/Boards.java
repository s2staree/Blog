package site.metacoding.red.domain.boards;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
public class Boards {
	private Integer id;
	private String title;
	private String content;
	private Integer userId;
	private Timestamp createdAt;
	
	public Boards(String title, String content, Integer userId) {
		this.title = title;
		this.content = content;
		this.userId = userId;
	}
}
