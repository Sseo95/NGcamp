package CampParse;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import JDBC.JDBCUtil;

public class CampDAO {
	// -----------------------------------------------------------------------
	// 싱글톤 패턴으로 만들자
	// 1. 자기 자신의 객체를 정적 멥버로 가진다.
	private static CampDAO instance = new CampDAO();

	// 2. 밖에서 객체를 생성하지 못하도록 생성자를 private으로 만든다.
	private CampDAO() {
	}

	// 3. 만들어져 있는 객체를 사용하도록 만든다.
	public static CampDAO getInstance() {
		return instance;
	}

	// -----------------------------------------------------------------------
	// 1. 새로운 투표등록
	public void insert(Connection conn, CampVO item) throws SQLException {
		String sql = "insert into info (idx,firstImageUrl,addr1,addr2,facltNm,lctCl,doNm,sigunguNm,autoSiteCo,caravSiteCo,glampSiteCo,gnrlSiteCo,caravAcmpnyAt,indvdlCaravSiteCo,trlerAcmpnyAt,eqpmnLendCl,sbrsCl,sbrsEtc,posblFcltyCl,themaEnvrnCl,animalCmgCl,siteBottomCl1,siteBottomCl2,siteBottomCl3,siteBottomCl4,siteBottomCl5,brazierCl,homepage,resveUrl,tel,induty,lineIntro,intro,featureNm,operDeCl,facltDivNm,insrncAt,mapX, mapY)" 
				+ "	values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		PreparedStatement pstmt = conn.prepareStatement(sql);

	
	pstmt.setInt(1,item.getIdx());
	pstmt.setString(2,item.getFirstImageUrl());
	pstmt.setString(3,item.getAddr1());
	pstmt.setString(4,item.getAddr2());
	pstmt.setString(5,item.getFacltNm());
	pstmt.setString(6,item.getLctCl());
	pstmt.setString(7,item.getDoNm());
	pstmt.setString(8,item.getSigunguNm());
	pstmt.setInt(9,item.getAutoSiteCo());
	pstmt.setInt(10,item.getCaravSiteCo());
	pstmt.setInt(11,item.getGlampSiteCo());
	pstmt.setInt(12,item.getGnrlSiteCo());
	pstmt.setString(13,item.getCaravAcmpnyAt());
	pstmt.setString(14,item.getIndvdlCaravSiteCo());
	pstmt.setString(15,item.getTrlerAcmpnyAt());
	pstmt.setString(16,item.getEqpmnLendCl());
	pstmt.setString(17,item.getSbrsCl());
	pstmt.setString(18,item.getSbrsEtc());
	pstmt.setString(19,item.getPosblFcltyCl());
	pstmt.setString(20,item.getThemaEnvrnCl());
	pstmt.setString(21,item.getAnimalCmgCl());
	pstmt.setInt(22,item.getSiteBottomCl1());
	pstmt.setInt(23,item.getSiteBottomCl2());
	pstmt.setInt(24,item.getSiteBottomCl3());
	pstmt.setInt(25,item.getSiteBottomCl4());
	pstmt.setInt(26,item.getSiteBottomCl5());
	pstmt.setString(27,item.getBrazierCl());
	pstmt.setString(28,item.getHomepage());
	pstmt.setString(29,item.getResveUrl());
	pstmt.setString(30,item.getTel());
	pstmt.setString(31,item.getInduty());
	pstmt.setString(32,item.getLineIntro());
	pstmt.setString(33,item.getIntro());
	pstmt.setString(34,item.getFeatureNm());
	pstmt.setString(35,item.getOperDeCl());
	pstmt.setString(36,item.getFacltDivNm());
	pstmt.setString(37,item.getInsrncAt());
	pstmt.setString(38,item.getMapX());
	pstmt.setString(39,item.getMapY());
	
	pstmt.executeUpdate();
	JDBCUtil.close(pstmt);
	}	
	}

