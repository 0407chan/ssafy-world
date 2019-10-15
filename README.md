# ssafy_world

심화 프로젝트 ssafy_world 입니다.

# 기술 스택
- back : tomcat, spring
- front : vue.js vuex vuetify
- DB : mySql, MyBatis

# 역할분담
- 팀장 : 이찬호
- backend : 박규빈, 최재형, 이찬호, 이규찬, 이준범
- frontend : 이준범, 최재형, 이찬호
- DB : 이규찬, 최재형, 박규빈

# 일정
- 1주 : 환경설정, 백, 프론트 스켈레톤 생성, aws 올리기, DB설계, 웹소켓(?)
- 2주 : UI layout, REST설계 
- 3주 : 
- 4주 : 피티, 영상

# 깃 commit 메세지 규칙
- 처음 `CREATE`,`DELETE`,`UPDATE` 붙이기
- 작성한 함수, 구현한 내용 적기
- 마지막에 jira : 연동 적기
```
git commit -m "UPDATE 함수이름, 기능, 이준범기능
- 이준범이 춤추게 수정했습니다.
jira : S1P1233017-7"
```

# 코딩 규칙
- 업데이트 한 사람이 최신 날짜와 이름 적고, 기능과 수정한것 간략하게 적기
- 작성예시
```
/* 2019.10.15 이준범
기능 : 무비를 가져온다.
입력 : 영화 index
출력 : 없음
*/
getMovie(int index){
    index = a;
    a = ;
}
```

- 함수 이름은 Camel 규칙에 따라 띄어쓰기 마다 대문자로 쓴다.
- 현재형으로 쓰고 하나만 가져오는 경우 단수, 여러개는 s를 써서 복수형으로 쓴다.
```
public void getUserController(){
    
}
public List[] getUsers(){
    
}
public User getUser(int userIdx){
    
}
```

# 기능
- 기본 = 기본은 그냥 다 db저장, 올 공개, 보안 취약
- 추가 = 비대칭키로 암호화, db도 30일후 삭제

