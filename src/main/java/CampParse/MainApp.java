package CampParse;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import JDBC.JDBCUtil;


public class MainApp {
	public static void main(String[] args) throws JAXBException, IOException {
			Connection conn = null;
			String className = "org.mariadb.jdbc.Driver";
			String url = "jdbc:mariadb://localhost:3306/jspdb";
			String user = "jspuser";
			String password = "123456";		
			try {
				Class.forName(className);
				conn = DriverManager.getConnection(url, user, password);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		
		 System.out.println("연결성공" + conn);
		 StringBuilder urlBuilder = new StringBuilder("http://api.visitkorea.or.kr/openapi/service/rest/GoCamping/basedList"); /*URL*/
	     urlBuilder.append("?" + URLEncoder.encode("serviceKey","UTF-8") + "=mkgdmneQXcMVWvwlDS5N5%2FBbu0B%2BgODpGKpbAyNozgtU8HPmXrs17YZycD2D%2BWkTX3miDCzrDCsyS4NWUk0Liw%3D%3D"); /*Service Key*/
	     urlBuilder.append("&" + URLEncoder.encode("pageNo","UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /*현재 페이지번호*/
	     urlBuilder.append("&" + URLEncoder.encode("numOfRows","UTF-8") + "=" + URLEncoder.encode("10", "UTF-8")); /*한 페이지 결과 수*/
	     urlBuilder.append("&" + URLEncoder.encode("MobileOS","UTF-8") + "=" + URLEncoder.encode("ETC", "UTF-8")); /*IOS(아이폰),AND(안드로이드),WIN(윈도우폰),ETC*/
	     urlBuilder.append("&" + URLEncoder.encode("MobileApp","UTF-8") + "=" + URLEncoder.encode("AppTest", "UTF-8")); /*서비스명=어플명*/
	     urlBuilder.append("&" + URLEncoder.encode("mapX","UTF-8") + "=" + URLEncoder.encode("128.6142847", "UTF-8")); /*GPS X좌표(WGS84 경도 좌표)*/
	     urlBuilder.append("&" + URLEncoder.encode("mapY","UTF-8") + "=" + URLEncoder.encode("36.0345423", "UTF-8")); /*GPS Y좌표(WGS84 위도 좌표)*/
	     urlBuilder.append("&" + URLEncoder.encode("radius","UTF-8") + "=" + URLEncoder.encode("2000", "UTF-8")); /*거리 반경(단위:m) Max값 20000m=20km*/
	     URL urld = new URL(urlBuilder.toString());
	        
	     JAXBContext context = JAXBContext.newInstance(Response.class);
	     Unmarshaller um = context.createUnmarshaller();
		 // Unmarshall the provided input XML into an object
		 Response r = (Response) um.unmarshal(new InputStreamReader(urld.openStream()));
		 System.out.println("Response : " + r);
		    
		 int totalCount = r.getBody().getTotalCount();
		 int totalPage = (totalCount-1)/100 + 1;
		 List<CampVO> itemList = new ArrayList<CampVO>();
		 for(int i=1;i<=totalPage;i++) {
			 urlBuilder = new StringBuilder("http://api.visitkorea.or.kr/openapi/service/rest/GoCamping/basedList"); /*URL*/
			 urlBuilder.append("?" + URLEncoder.encode("serviceKey","UTF-8") + "=mkgdmneQXcMVWvwlDS5N5%2FBbu0B%2BgODpGKpbAyNozgtU8HPmXrs17YZycD2D%2BWkTX3miDCzrDCsyS4NWUk0Liw%3D%3D"); /*Service Key*/
			 urlBuilder.append("&" + URLEncoder.encode("pageNo","UTF-8") + "=" + URLEncoder.encode(i+"", "UTF-8")); /*현재 페이지번호*/
			 urlBuilder.append("&" + URLEncoder.encode("numOfRows","UTF-8") + "=" + URLEncoder.encode("100", "UTF-8")); /*한 페이지 결과 수*/
			 urlBuilder.append("&" + URLEncoder.encode("MobileOS","UTF-8") + "=" + URLEncoder.encode("ETC", "UTF-8")); /*IOS(아이폰),AND(안드로이드),WIN(윈도우폰),ETC*/
			 urlBuilder.append("&" + URLEncoder.encode("MobileApp","UTF-8") + "=" + URLEncoder.encode("AppTest", "UTF-8")); /*서비스명=어플명*/
			 urlBuilder.append("&" + URLEncoder.encode("mapX","UTF-8") + "=" + URLEncoder.encode("128.6142847", "UTF-8")); /*GPS X좌표(WGS84 경도 좌표)*/
			 urlBuilder.append("&" + URLEncoder.encode("mapY","UTF-8") + "=" + URLEncoder.encode("36.0345423", "UTF-8")); /*GPS Y좌표(WGS84 위도 좌표)*/
			 urlBuilder.append("&" + URLEncoder.encode("radius","UTF-8") + "=" + URLEncoder.encode("2000", "UTF-8")); /*거리 반경(단위:m) Max값 20000m=20km*/
			 urld = new URL(urlBuilder.toString());
			 r = (Response) um.unmarshal(new InputStreamReader(urld.openStream()));
			 itemList.addAll(r.getBody().getItem());    
			 System.out.println(r.getBody().getItem().size() + "개 읽음");    
		}
		 System.out.println("전체 : " + itemList.size() + "개");
		 PreparedStatement pstmt = null;
		
		 String sql =  " INSERT INTO info(idx, firstImageUrl, addr1, addr2, facltNm, lineIntro, intro, featureNm"
				   +  ", lctCl, doNm, sigunguNm, autoSiteCo, caravSiteCo, glampSiteCo, gnrlSiteCo, caravAcmpnyAt"
				   +  ", indvdlCaravSiteCo, trlerAcmpnyAt, eqpmnLendCl, sbrsCl, sbrsEtc, posblFcltyCl, themaEnvrnCl"
				   +  ", animalCmgCl, siteBottomCl1, siteBottomCl2, siteBottomCl3, siteBottomCl4, siteBottomCl5"
				   +  ", brazierCl, homepage, resveUrl, tel, induty, operDeCl, facltDivNm, insrncAt, mapX, mapY ) "
	               +  " VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) ";
		 
		 for(CampVO item: itemList) {
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, 0); 
				pstmt.setString(2, item.getFirstImageUrl()); 
				pstmt.setString(3, item.getAddr1()); 
				pstmt.setString(4, item.getAddr2());
				pstmt.setString(5, item.getFacltNm()); 
				pstmt.setString(6, item.getLineIntro()); 
				pstmt.setString(7, item.getIntro()); 
				pstmt.setString(8, item.getFeatureNm()); 
				pstmt.setString(9, item.getLctCl()); 
				pstmt.setString(10,item.getDoNm()); 
				pstmt.setString(11, item.getSigunguNm()); 
				pstmt.setInt(12, item.getAutoSiteCo());
				pstmt.setInt(13, item.getCaravSiteCo());
				pstmt.setInt(14, item.getGlampSiteCo());
				pstmt.setInt(15, item.getGnrlSiteCo());
				pstmt.setString(16, item.getCaravAcmpnyAt()); 
				pstmt.setString(17, item.getIndvdlCaravSiteCo()); 
				pstmt.setString(18, item.getTrlerAcmpnyAt()); 
				pstmt.setString(19, item.getEqpmnLendCl()); 
				pstmt.setString(20, item.getSbrsCl()); 
				pstmt.setString(21, item.getSbrsEtc()); 
				pstmt.setString(22, item.getPosblFcltyCl()); 
				pstmt.setString(23, item.getThemaEnvrnCl()); 
				pstmt.setString(24, item.getAnimalCmgCl()); 
				pstmt.setInt(25, item.getSiteBottomCl1());
				pstmt.setInt(26, item.getSiteBottomCl2());
				pstmt.setInt(27, item.getSiteBottomCl3());
				pstmt.setInt(28,item.getSiteBottomCl4());
				pstmt.setInt(29, item.getSiteBottomCl5());
				pstmt.setString(30, item.getBrazierCl()); 
				pstmt.setString(31, item.getHomepage()); 
				pstmt.setString(32, item.getResveUrl()); 
				pstmt.setString(33, item.getTel()); 
				pstmt.setString(34, item.getInduty()); 
				pstmt.setString(35, item.getOperDeCl()); 
				pstmt.setString(36, item.getFacltDivNm()); 
				pstmt.setString(37, item.getInsrncAt()); 
				pstmt.setString(38, item.getMapX()); 
				pstmt.setString(39, item.getMapY()); 
				pstmt.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			 JDBCUtil.close(pstmt);
		 }	
	}
	
}
