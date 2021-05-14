## 잇칩
### 마트
- 구현목표: Non-Blocking Spring Boot with Kotlin Coroutines


### 엔티티 및 테이블 설계
<img width="900" alt="mart-table" src="https://user-images.githubusercontent.com/24540759/112621994-be498200-8e6d-11eb-8bb3-e150ded61dc5.png">

---
### 구현 Apis 목록

1. 상품상점 등록
   - 회원 토큰값 인증 확인
   - 미등록 상점인 경우 상점등록 후 상품을 상점에 등록 확인
   - 등록된 상점인 경우 상품을 상점에 등록 확인

2. 상점 등록
   - store_no에 카카오 map api에서 조회한 id(상점 고유값) 저장 확인

3. 상점 조회
   - 카카오 map api에서 조회한 id(상점 고유값)로 상점 조회 확인

4. 상품 등록
   - 부모 상품( ex. 꼬북칩) 등록 확인
   - 자식 상품( ex. 초코맛) 등록 확인

5. 상품 조회
   - 부모상품 조회 확인
   - 자식상품 조회 확인
