package CampParse;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.Data;
/*
<numOfRows>10</numOfRows>
<pageNo>1</pageNo>
<totalCount>2906</totalCount>
 */
@Data
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Body {
	@XmlElementWrapper(name="items")
	@XmlElement(name="item")
	private List<CampVO> item;
	@XmlElement
	private int numOfRows;
	@XmlElement
	private int pageNo;
	@XmlElement
	private int totalCount;
}
