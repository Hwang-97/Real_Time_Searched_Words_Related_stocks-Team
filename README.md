⌨️ 실검 관련주 ⌨️

![2 메인_메세지](https://user-images.githubusercontent.com/85034286/153634721-fcd9ffac-bbde-4437-998c-20f98e0e42fa.png)

>  해당 프로젝트는 실시간 검색어와 관련된 뉴스를 수십여개 확인 후 가장 많이 발견된 주식을 매칭시켜 주는 프로그램 입니다. <br />
>  2인이 진행한 프로젝프이며 수업 종료 후 약간의 시간을 투자하여 11일간 제작했습니다. <br />

<br />

# 📌 Table Of Contents
* [📖 Introduction](#-introduction)
* [🙋 My Role](#-my-role)
* [🔎 Detail](#-detail)
* [💡 Review](#-review)

<br />
<br />
<br />



# 📖 Introduction
### 1. 프로젝트 개요
* 해당 프로젝트는 실시간 검색어와 관련된 뉴스를 수십여개 확인 후 가장 많이 발견된 주식을 매칭시켜 주는 프로그램 입니다.
* 비 정상적으로 많이 등장하는 주식은 제외 되도록 로직을 구현한 프로그램 입니다.
<br />

### 2. 개발 환경
<img src="https://img.shields.io/badge/eclipse ide-2C2255?style=for-the-badge&logo=eclipseide&logoColor=white"><img src="https://img.shields.io/badge/github-181717?style=for-the-badge&logo=github&logoColor=white">  
* 해당 프로젝트는 java에서 JSOUP 라이브러리를 사용해서 제작했습니다.
* 개발 툴로는 Eclipse를 사용했습니다.
* 형상 관리 툴로는 GitHub & SorceTree를 사용했습니다.
<br />

### 3. 프로젝트 내용
해당 프로젝트는 협업툴 사용법 공부에 중점을 두고 제작된 프로젝트 입니다. 
때문에 거의 모든 부분을 업무분담 없이 함께 제작했습니다.
#### 3-1. 매칭 리스트 출력
* 실시간 검색어를 추출한 뒤 > 몇십개의 관련된 최신 뉴스를 크롤링 합니다. > 해당 결과에서 가장 많이 발견된 주식을 매칭시킵니다.
* 비정상 적으로 많이 등잫하는 주식은 자동으로 제외되게 로직을 구현하였습니다.
![2 메인_메세지 (1)](https://user-images.githubusercontent.com/85034286/153637776-c1e2f91d-673b-4f08-963b-44aca3b6141d.png)

#### 3-2. 금일 주식 정보
* 해당 페이지는 매칭된 주식의 금일주식 정보를 출력하는 페이지 이며 뷰클래스와 데이터 클래스를 분리해 구현했습니다.
![2-1 금일_주식_정보](https://user-images.githubusercontent.com/85034286/153639046-000d43c9-f914-4b5e-9051-d99fc76bf6a7.png)

#### 3-3. 전일 주식 정보
* 해당 페이지는 매칭된 주식의 전일주식 정보를 출력하는 페이지 이며 뷰클래스와 데이터 클래스를 분리해 구현했습니다.
![2-2 전일_주식_정보](https://user-images.githubusercontent.com/85034286/153638885-3184d90e-2a46-450f-bfc1-0d3da73e4030.png)

#### 3-4. 당월 주식 정보
* 해당 페이지는 매칭된 주식의 당월주식 정보를 출력하는 페이지 이며 뷰클래스와 데이터 클래스를 분리해 구현했습니다.
![2-3 당월_주식_정보](https://user-images.githubusercontent.com/85034286/153639138-3166b501-591a-4962-9bbc-c9278465d9d1.png)

<br />
<br />
<br />


# 🔎 Detail
### 1. 주요 코드
* 해당 메소드는 뉴스에서 가장 많이 등장한 주식을 검색하는 메소드 입니다.
    ```java
      private static void count(String txt, String keyword) {
          for (String key : CompanyMap.map.keySet()) {
             int index = -1;
             int count = 0;
             while (txt.indexOf(key, index) != -1) {
                index = txt.indexOf(key, index) + key.length() + 1;
                count++;
             }
             CompanyMap.map.put(key, count);
          }
          int max = 0;
          for (String s : CompanyMap.map.keySet()) {
             if (CompanyMap.map.get(s) > max) {
                max = CompanyMap.map.get(s);
             }
          }
          if(max!=0) {
             for (String s : CompanyMap.map.keySet()) {
                if (CompanyMap.map.get(s) == max) {
                   resultMap.put(keyword, s);
                }
             }
          }else {
             resultMap.put(keyword, "not");
          }
          CompanyMap.clearValue();
      }
    ```
* 실시간 검색어를 크롤링 하는 메소드 입니다.
    ```java
    public static void crolling() {
		try {
			body = Jsoup.connect(issunowUrl).get().body().select("div.mdl-card__actions b");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		String[] keywordArr = body.toString().replaceAll("[< > b /]","").split("\n");
		for(int i=0 ; i<keywordArr.length;i++ ) {
			keyword.add(keywordArr[i]);
		}  
	} 
    ```
* 실시간 검색어와 관련된 뉴스의 정보를 String에 누적시키는 메소드 입니다.
    ```
    public static Object checkKeyword() {
      resultMap = new LinkedHashMap<String, String>();
      
      try {
         for (int i = 0; i < KeywordCrolling.keyword.size(); i++) {
            Document d = Jsoup.connect(url + KeywordCrolling.keyword.get(i) + addUrl + recent).get();
            txt = d.body().text();
            

            for(int j=11; j<=21; j+=10) {
            	d = Jsoup.connect(url + KeywordCrolling.keyword.get(i) + addUrl + isTeenrecent + j).get();
            	txt += d.body().text();
            	
            }

            
            count(txt, KeywordCrolling.keyword.get(i));
         }
      } catch (Exception e) {
         return SecondCheck.SecondCheck();
      }
      return "check";
   }
    ```
    
<br />
<br />
<br />

# 💡 Review
### 1. 후기
* 프로젝트를 진행하며 브렌치를 나눠 사용했습니다.
* 하지만 동일한 파일을 수정하다보니 머지 후 충돌이 당연히 많이 일어났습니다.
* 해당 과정에서 충돌을 해결해 보기도 하고 잘못된 푸쉬를 리셋을 해보기도 하며 깃허브에 대한 이해도가 많이 증가하는 계기가 되었습니다. 
* 시간이 촉박해 GUI 또는 Web으로 업그레이드는 못했던 부분이 아쉬웠습니다.

<br />
<br />

### 2. 코드 리뷰
* DB를 사용하지 않고 제작한 점이 아쉽다.

<br />
<br />
<br />

