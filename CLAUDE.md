# JavaProgrammingStudy — Claude 핸드오프 가이드

윤성우 **열혈 Java 프로그래밍** 교재(34장)를 따라가는 **개인 학습 레포**.
사용자: Dorazee0209 / 김상진 / tkdwls0619@naver.com (한국어로 대화).

> 이 파일은 "다음 Claude를 위한 인수인계서"다. 세션을 이어받으면 먼저 이 문서를 읽고
> 아래 규칙대로 가르친다. (`.omc/`는 플러그인 자동 캐시일 뿐 가르치는 맥락이 없음.)

## ⭐️ 가장 중요한 규칙 (반드시 지킬 것)
1. **사용자가 직접 코드를 작성한다. 나는 "방법"만 가르친다.** ("직접 할게, 알려만줘")
   - 문제 풀이를 대신 작성하지 말 것. Answer 파일의 `main`은 **빈 골격**으로만 만들어 둔다.
   - 막히면 힌트·접근법만 주고, 정답 코드를 통째로 적어주지 않는다.
2. **C 언어 비교를 자주 곁들인다.** (사용자가 C 출신, 자주 요청함)
3. 가르치는 톤: 감점 주는 분위기 X, 친근하게. **표/이모지** 활용, 끝에 **정리** 섹션.
4. 사용자는 **Git/GitHub/zsh/Vim** 도 함께 배우는 중 — 관련 질문 나오면 같이 설명한다.

## 📂 네이밍 규칙
- 예제 파일: `P###_Name.java` (### = **교재 페이지 번호**). public 클래스명 = 파일명.
- 문제 폴더: `P###_QuestionCC-N` (CC=장, N=문제번호). 예: `P118_Question05-3`
- 폴더 안 답안: `Answer01.java`, `Answer02.java` … (**하위문제별 1개**).
- Answer 파일 상단에 **문제 지문을 주석 블록**(`/* ... */`)으로 넣고, `main`은 비워 둔다(사용자가 풀 부분).

## ✍️ 문제 폴더 만들기 절차 ("문제 NN-M 만들어줘" 요청 시)
1. 교재 PDF에서 해당 문제 지문 페이지를 찾아 렌더 후 읽는다.
   - PDF: `~/Library/Mobile Documents/com~apple~CloudDocs/Documents/e-book/프로그래밍 언어/JAVA프로그래밍/JAVA프로그래밍.pdf`
   - **스캔본**(이미지 PDF), page offset = 0. 렌더 예:
     `pdftoppm -f N -l N -png -r 140 "$PDF" out` → 생성된 png를 Read로 본다.
2. 답안 키 폴더에서 정답·하위문제 개수를 확인한다(채점용 — **지문엔 정답 노출 금지**).
   - 답안 루트: `~/Library/Mobile Documents/com~apple~CloudDocs/Documents/e-book/프로그래밍 언어/JAVA프로그래밍/소스코드와 답안/답안/`
   - 폴더·파일명·내용이 **CP949 인코딩** → `iconv -f CP949 -t UTF-8` 로 읽는다.
   - 폴더는 glob 매칭으로 접근(한글 깨짐 회피): `"$KEYDIR"/*05-3*`
3. `P{페이지}_Question{장}-{번호}/AnswerNN.java` 생성 — **지문 주석 + 빈 main**.

## ✅ 채점 워크플로우 ("채점" 요청 시)
1. 해당 Answer 파일을 읽는다.
2. 내 지식 + **답안 키**(위 경로, iconv)와 비교한다.
3. **실제로 컴파일·실행해 출력 확인**: `javac Answer01.java && java Answer01`
4. ⭕️/❌ 로 결과 표시, 틀린 점은 친절히 설명. 더 나은 풀이는 칭찬과 함께 제안.

## 🔀 Git / PR 워크플로우 (사용자가 연습 중)
- 현재 브랜치: `feature/ch05`. `main`은 깨끗하고 origin과 동기화됨.
- **1 PR = 1 챕터.** Chapter 05 작업을 **모두 끝낸 뒤** PR을 한 번 생성한다.
- **Chapter 05 PR → GitHub 브라우저로**, **Chapter 06 PR → `gh` CLI로** (gh 미설치 → `brew install gh` 필요).
- **commit/push 는 사용자가 직접 한다**(내가 대신 하지 않음). 문제/예제 단위로 atomic commit 권장.
- 되돌리기: push 전이면 `git reset`(`--soft`/`--mixed`/`--hard`), push 후면 `git revert`.

## 📈 진행 상황 (이 핸드오프 시점: 2026-06-30)
- 완료: **Chapter 01~04** (README 체크됨). Chapter 04 문제: 04-1(5개)·04-2(2개) 모두 ⭕️.
- **Chapter 05 진행 중**:
  - 05-1 (P110) ⭕️ 완료·커밋됨 (`58a8e80`)
  - 05-2 (P112) ⭕️ 완료·커밋됨 (`e773392`)
  - 05-3 (P118) — **폴더/골격 방금 생성, 사용자가 푸는 중** (Answer01: switch→if/else, Answer02: if/else→switch). **아직 미채점.**
- 미추적 파일(아직 커밋 안 됨): `05_Chapter/P114_SwitchBasic.java`, `P116_SwitchBreak.java`, `P118_Question05-3/`
- 남은 Chapter 05 문제: **답안 키 폴더를 `ls` 해서 05-4 이후 목록을 확인**한다.
  알려진 것: **05-7 (P135) [반복문 중첩의 활용]** — 하위문제 2개(짝수단 구구단 2/4/6/8단, "A B + B A = 99" 퍼즐).

## ⚠️ 알려진 디테일/주의
- **Vim 버퍼는 디스크 변경을 자동 reload 안 함** → 내가 Write로 파일을 고친 뒤엔 사용자에게 `:e!`(버퍼 재로드)를 안내한다.
- 교재 페이지 위치(이미 찾음): 05-1 = p110, 05-2 = p112, 05-3 = p118, 05-7 = p135.
- public 클래스명 ≠ 파일명이면 컴파일 에러 — Answer 파일은 클래스명을 `AnswerNN`으로 맞춘다.
