# baseball-03
BASEBALL 서비스 - 3팀

* BE: [Jay](https://github.com/beginin15), [Lynn](https://github.com/beemiel)
* FE: [Sally](https://github.com/sally4405)
* iOS: [Olaf](https://github.com/1Consumption)

## Ground Rule

* [데일리 스크럼](https://github.com/codesquad-member-2020/baseball-03/wiki/데일리-스크럼)
* 매일 오전 11시 30분 만나서 스크럼하고, 스크럼 이후에 스프레드시트에 백로그(to-do-list)를 작성한다.
* 프로젝트 디렉토리를 fe, be, iOS로 구분한다.

## Branch Rule

* `master`: 배포 브랜치
* `dev`: 디폴트 브랜치
* 작업을 시작할 때: 자신의 클래스 개발 브랜치에서 `<클래스>/feature-<기능이름>`로 브랜치 생성
  * `BE/feature-기능이름` : 백엔드 피쳐 개발 브랜치
  * `FE/feature-기능이름` : 프론트엔드 피쳐 개발 브랜치
  * `iOS/feature-기능이름` : 모바일 iOS 피쳐 개발 브랜치
* PR 메시지에 `Closed #n` 등을 포함시켜 이슈를 닫는다.
* 머지를 완료했으면 기능(feature)브랜치는 github과 local git에 모두 삭제한다.

## Commit Message Rules

[#이슈 번호] 커밋 종류: 커밋 내용(한글)

* [#1] feat: The new feature you're adding to a particular application
* [#1] fix: A bug fix
* [#1] style: Feature and updates related to styling
* [#1] refactor: Refactoring a specific section of the codebase
* [#1] test: Everything related to testing
* [#1] docs: Everything related to documentation
* [#1] chore: Regular code maintenance.

## Issue 및 Pull Request 네이밍 규칙

* Issue 네이밍: [클래스] 제목
> [FE] 상세페이지 구현
* Pull Request 네이밍: [이슈번호] 제목
> [#72] 쿠키 패스 지정


## 스크럼

[wiki 스크럼]()

## 팀장 역할
* 팀장의 임기는 1주일이다.
* 조원이 슬랙에 올려놓은 데일리 스크럼 내용을 위키에 취합
* 행아웃 링크 파기

## API 정리 문서

[데이터 요청 API 문서]()
