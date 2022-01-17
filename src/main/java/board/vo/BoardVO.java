package board.vo;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

import lombok.Data;

/*
create table board(
   		idx int primary key auto_increment,
   		name varchar(30) not null,
   		password varchar(50) not null,
   		subject varchar(100) not null,
   		content text not null,
   		hit int default 0,
   		regDate timestamp default now(),
   		ip varchar(20) not null
); 
 */
@XmlRootElement // 혹시라도  XML로 출력할 일이 발생할때를 대비하기 위해서
@Data // 기본생성자/겟터/셑터/toString ... 을 자동으로 만들어 준다.
public class BoardVO {
	private int    idx;
	private String name;
	private String password;
	private String subject;
	private String content;
	private int    hit;
	private Date   regDate;
	private String ip;
}
