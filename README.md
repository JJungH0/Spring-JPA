# 🌿 Spring JPA

> 출처: 본 문서는 김영한 강사님의 「JPA ORM 표준」 강의를 기반으로 하며,  개인적인 이해 및 해석을 더해 정리한 자료입니다

📘 **정리 노션 링크:**  
[pure-jap-7 ~ pure-jap-8 JPA 정리](https://bottlenose-balloon-0b4.notion.site/JPA-2025-11-25-275-2b65c23e942f80d49ffee242d4bb23d7?source=copy_link)


## 🧩 H2 Database 설치
- **공식 사이트:** [https://www.h2database.com/](https://www.h2database.com/)
- 상단 메뉴에서 **Download → All Platforms** 선택
- **다운로드 후 압축 해제**
- **터미널에서 `h2/bin` 경로로 이동**
  ```bash
  cd ~/h2/bin
  ./h2.sh
  
- **실행 권한 오류 발생 시**
  ```bash
  chmod 755 h2.sh
  ./h2.sh

