package CampParse;

//vo



import javax.xml.bind.annotation.XmlRootElement;

import lombok.Data;

@Data
@XmlRootElement
public class CampVO {
	private int idx;
	private String firstImageUrl; // 대표이미지
	private String addr1; //주소
	private String addr2; // 상호
	private String facltNm; // 야영장명
	private String lineIntro; // 한줄소개
	private String intro; // 소개
	private String featureNm; // 특징
	private String lctCl; // 입지구분
	private String doNm; // 도 이름
	private String sigunguNm; // 시 이름
	private int autoSiteCo; // 주요시설 자동차 야영장
	private int caravSiteCo; // 주요시설 카라반
	private int glampSiteCo; // 글램핑 개수
	private int gnrlSiteCo; // 야영장 개수
	private String caravAcmpnyAt; // 개인 카라반 동반 여부(Y:사용, N:미사용)
	private String indvdlCaravSiteCo; // 개인 카라반
	private String trlerAcmpnyAt; // 개인 트레일러 동반 여부
	private String eqpmnLendCl; // 제공 
	private String sbrsCl; // 부대시설
	private String sbrsEtc; // 부대시설 기타
	private String posblFcltyCl; // 주변이용 가능시설
	private String themaEnvrnCl; // 테마환경
	private String animalCmgCl; // 애완견 동반여부
	private int siteBottomCl1; // 잔디
	private int siteBottomCl2; // 파쇄석
	private int siteBottomCl3; // 테크
	private int siteBottomCl4; // 자갈
	private int siteBottomCl5; // 맨흙
	private String brazierCl; // 화로대
	private String homepage; // 홈페이지 주소
	private String resveUrl; // 예약주소
	private String tel; // 전화번호
	private String induty; // 업종
	private String operDeCl; // 운영일
	private String facltDivNm; // 사업주체.구분 (민간/지자체)
	private String insrncAt; // 영업보상 책임 보험 가입여부 
	private String mapX;
	private String mapY;
}