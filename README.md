# 안드로이드 어플리케이션 두번째 프로젝트

## 1. 간단한 설명
본 어플리케이션은 자격증 정보 및 관련 학원 정보 얻기부터 자격증 공부 과정, 내 자격증 정리까지<br>
내 자격증에 대한 관리를 한번에 해주는 서비스를 제공합니다.

## 2. 서비스 설명

1) 기능사, 기사 등 자격증에 대한 전반적인 정보를 제공합니다.<br>
2) 해당 자격증을 교육받을 수 있는 학원 정보를 제공합니다.<br>
3) 자격증 공부하는 과정을 사진으로 등록할 수 있습니다.<br>
4) 내가 취득한 자격증을 등록하고 어플리케이션을 통해 내 자격증 목록을 한번에 확인할 수 있습니다.<br>
5) 본인이 취득한 자격증에 한하여 원하는 모양의 명함을 만들 수 있습니다.<br>

## 3. 프로젝트 기간
2020.3.24 ~ 2020.7.21

## 4. 개발 언어
JAVA

## 5. 활용 서버
본 어플리케이션은 서버리스로써 파이어베이스를 사용하였습니다.

## 6. 사용 데이터 
본 어플리케이션은 공공 데이터를 활용하여 만들어졌습니다.
가공된 데이터는 파이어베이스에 저장되었으며 JSON parsing을 통해서 사용되었습니다.
1) 기능사 종목별 시험정보 현황(출처: 공공데이터 포털)
2) 기술사 종목별 시험정보 현황(출처: 공공데이터 포털)
3) 기사 종목별 시험정보 현황(출처: 공공데이터 포털)
4) 산업기사 종목별 시험정보 현황(출처: 공공데이터 포털)
5) 2020년 학원_교습소 및 개인과외 교습자 현황(출처: 부산교육청)

## 7. 사용 기술
첫번째 프로젝트에서는 사용하지 않았던 기술을 사용하였습니다.<br>
1) SplashActivity 사용<br>
-> 보통 어플리케이션을 실행하면 Splash 화면이 보입니다. 이 구간에서 데이터를 가지고 오게 됩니다. 
2) AsyncTask 사용<br>
-> SplashActivity에서 데이터를 가지고 올 때 AsyncTask로 백그라운드 작업을 실행하였습니다. <br>
-> 스레드를 새롭게 만들어서 백그라운드 작업을 수행하게 됩니다.
비록, 안드로이드 개발에 있어 기본적일 수 있지만 새롭게 시도한 기술들이였습니다.


## 8. 활용 방향
본 어플리케이션은 출시는 하지 않았으며 1번의 창업 경진 대회에 제출하였습니다.
1. 제10회 서강대학교 창업경진대회(스타트업 오디션)

## 9. 결과
서강대학교 창업경진대회에 참가하여 상위 8팀에 포함되어 본선에 진출하였고 입상을 하게 되었습니다.

## 10. 앱 이미지
![ㅋㅊ1](https://user-images.githubusercontent.com/17876424/106129691-d40b3580-61a3-11eb-89ce-158411453bae.PNG)<br>
![ㅋㅊ2](https://user-images.githubusercontent.com/17876424/106129794-f0a76d80-61a3-11eb-8096-3af7ed59074f.PNG)
