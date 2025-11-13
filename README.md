# Kafka Study - Event-Driven Architecture (EDA)

이벤트 기반 아키텍처(Event-Driven Architecture)와 Apache Kafka를 활용한 실시간 콘텐츠 처리 시스템 학습 프로젝트입니다.

## 📋 목차

- [프로젝트 개요](#-프로젝트-개요)
- [기술 스택](#-기술-스택)
- [아키텍처](#-아키텍처)
- [주요 기능](#-주요-기능)
- [모듈 구조](#-모듈-구조)
- [Kafka 이벤트 플로우](#-kafka-이벤트-플로우)
- [Worker 애플리케이션](#-worker-애플리케이션)
- [인프라 구성](#-인프라-구성)
- [시작하기](#-시작하기)
- [API 문서](#-api-문서)

## 🎯 프로젝트 개요

이 프로젝트는 **Clean Architecture(클린 아키텍처)** 와 **Hexagonal Architecture(헥사고날 아키텍처)** 원칙을 따르는 이벤트 기반 시스템입니다. Apache Kafka를 중심으로 실시간 콘텐츠 조정, 캐싱, 인덱싱, 쿠폰 발급 등의 기능을 구현합니다.

### 주요 학습 목표

- 🔄 이벤트 기반 마이크로서비스 아키텍처
- 📨 Kafka를 이용한 비동기 메시지 처리
- 🗄️ Polyglot Persistence (다중 데이터베이스 전략)
- 🤖 AI 기반 콘텐츠 모더레이션 파이프라인
- 🎫 대용량 트래픽 처리를 위한 쿠폰 발급 시스템
- 🔍 전문 검색(Full-text Search) 기능
- 📊 로그 수집 및 모니터링

## 🛠 기술 스택

### Core Technologies

- **Language:** Kotlin 2.0.20
- **Framework:** Spring Boot 3.2.0
- **JVM:** Java 21
- **Build Tool:** Gradle

### Message Broker

- **Apache Kafka 3.6.0** - 3-broker cluster with Zookeeper
- **Kafka UI** - 토픽 및 메시지 모니터링

### Databases

- **MySQL 8.0.35** - 주요 데이터 저장소
- **MongoDB 7.0.3** - 구독 Inbox 저장
- **Redis 6.0.20** - 캐싱 레이어 (Redisson)
- **Elasticsearch 7.10.2** - 전문 검색 엔진

### External Services

- **ChatGPT API** - 콘텐츠 자동 조정 및 태그 생성
- **Metadata Service** - 사용자/카테고리 메타데이터

### Monitoring & Logging

- **Kibana 7.10.2** - Elasticsearch UI
- **Filebeat 7.10.2** - 로그 수집
- **Logstash 7.10.2** - 로그 처리 파이프라인

## 🏗 아키텍처

### Clean Architecture 레이어

```
┌─────────────────────────────────────────────┐
│              API Module (8081)              │  ← REST API 레이어
├─────────────────────────────────────────────┤
│            UseCase Module                   │  ← 비즈니스 로직
├─────────────────────────────────────────────┤
│            Domain Module                    │  ← 순수 엔티티
├─────────────────────────────────────────────┤
│            Adapter Module                   │  ← 인프라 구현
│  MySQL | MongoDB | Redis | ES | Kafka      │
└─────────────────────────────────────────────┘
         ↓ Kafka Topics ↓
┌─────────────────────────────────────────────┐
│           Worker Modules                    │  ← 비동기 처리
│  Auto-Inspection | Caching | Indexing      │
│  Subscribe | Coupon                         │
└─────────────────────────────────────────────┘
```

### 이벤트 기반 메시지 플로우

```
POST 생성/수정 → MySQL (JPA Listener)
                      ↓
                post.original topic
                      ↓
        ┌─────────────┴─────────────┐
        ↓                           ↓
Auto-Inspection Worker    Content Caching Worker
(ChatGPT 콘텐츠 조정)      (Redis 캐시)
        ↓
  post.inspected topic
        ↓
    ┌───┴────┐
    ↓        ↓
Indexing   Subscribe
Worker     Worker
(ES)       (MongoDB)
```

## 🚀 주요 기능

### 1. 게시물 관리 시스템

- **CRUD 작업** - 게시물 생성, 조회, 수정, 삭제
- **자동 콘텐츠 조정** - ChatGPT API를 통한 부적절한 콘텐츠 감지
- **자동 태그 생성** - AI 기반 게시물 분류 및 태그 추출
- **전문 검색** - Elasticsearch를 활용한 빠른 검색
- **캐싱** - Redis Write-through 캐싱으로 성능 최적화

### 2. 쿠폰 발급 시스템 🎫

고트래픽 환경에서 안정적인 쿠폰 발급을 위한 비동기 처리 시스템

- **쿠폰 이벤트 관리** - 발급 한도 및 만료일이 있는 캠페인
- **비동기 발급** - Kafka를 통한 대량 요청 처리
- **중복 방지** - 사용자당 이벤트별 1회 발급 제한
- **재고 검증** - 발급 한도 초과 방지
- **사용 가능 쿠폰 조회** - 사용자별 쿠폰 목록

**부하 테스트:**
```bash
./send_coupon_issue_requests.sh
```

### 3. 구독 시스템

- 콘텐츠 크리에이터 구독 기능
- MongoDB 기반 알림 Inbox
- 읽음/안 읽음 추적

### 4. 로그 집계 및 모니터링

```
Filebeat → Kafka (access-log topic) → Logstash → Elasticsearch → Kibana
```

## 📦 모듈 구조

### Domain Module

순수한 비즈니스 엔티티 (프레임워크 의존성 없음)

```kotlin
- Post: 게시물 엔티티 (title, content, userId, categoryId)
- ResolvedPost: 메타데이터가 포함된 게시물 (userName, categoryName)
- InspectedPost: 검증된 게시물 (tags, inspectionMetadata)
- Coupon: 쿠폰 엔티티
- CouponEvent: 쿠폰 캠페인 엔티티
```

### API Module (Port: 8081)

REST API 컨트롤러

- `PostController` - 게시물 CRUD
- `CouponController` - 쿠폰 발급 및 조회
- `PostListController` - 게시물 목록 (페이지네이션)
- `InternalController` - 내부 API

### UseCase Module

비즈니스 로직 캡슐화

- `post-usecase` - 게시물 CRUD 로직
- `inspected-post-usecase` - 게시물 검증 로직
- `subscribe-post-usecase` - 구독 관리
- `post-search-usecase` - Elasticsearch 검색
- `coupon-usecase` - 쿠폰 발급 및 검증
- `post-resolving-help-usecase` - 게시물 메타데이터 보강

### Adapter Module

인프라 구현

- `mysql` - JPA 엔티티 및 리포지토리
- `redis` - Redisson 캐싱 (분산 락)
- `mongodb` - 구독 Inbox 저장소
- `elasticsearch` - 전문 검색 인덱싱
- `kafka` - 프로듀서/컨슈머, 토픽 정의
- `metadata-client` - 외부 메타데이터 API 클라이언트
- `chat-gpt-client` - ChatGPT API 통합

### Worker Module

독립적인 Kafka 컨슈머 애플리케이션 (각각 별도의 Spring Boot 앱)

## 📨 Kafka 이벤트 플로우

### Kafka Topics

| Topic | 설명 | Producer | Consumer |
|-------|------|----------|----------|
| `post.original` | 원본 게시물 CDC 이벤트 (CREATE/UPDATE/DELETE) | API Module (JPA Listener) | Auto-Inspection, Caching Workers |
| `post.inspected` | 검증된 게시물 (태그 포함) | Auto-Inspection Worker | Indexing, Subscribe Workers |
| `coupon.request` | 쿠폰 발급 요청 | API Module | Coupon Worker |
| `access-log` | 애플리케이션 접근 로그 | Filebeat | Logstash |

### 이벤트 메시지 구조

**PostOriginalMessage:**
```kotlin
{
  "operationType": "CREATE" | "UPDATE" | "DELETE",
  "post": {
    "postId": Long,
    "title": String,
    "content": String,
    "userId": Long,
    "categoryId": Long,
    "createdAt": Instant,
    "updatedAt": Instant
  }
}
```

**PostInspectedMessage:**
```kotlin
{
  "post": ResolvedPost,
  "tags": List<String>,
  "inspectedAt": Instant
}
```

## 🔧 Worker 애플리케이션

### 1. Auto-Inspection Worker

- **Consumes:** `post.original` topic
- **Purpose:** ChatGPT API를 통한 콘텐츠 자동 조정
- **Produces:** `post.inspected` topic (태그 포함)
- **Consumer Group:** `auto-inspection-consumer-group`
- **Concurrency:** 3

### 2. Content Caching Worker

- **Consumes:** `post.original` topic
- **Purpose:** Redis에 Write-through 캐싱
- **Features:** 메타데이터 포함 (userName, categoryName)
- **Consumer Group:** `cache-post-consumer-group`
- **Concurrency:** 3

### 3. Content Indexing Worker

- **Consumes:** `post.inspected` topic
- **Purpose:** Elasticsearch 인덱싱
- **Indexes:** title, content, categoryName, tags, indexedAt
- **Concurrency:** 3

### 4. Content Subscribe Worker

- **Consumes:** `post.inspected` topic
- **Purpose:** 구독자에게 게시물 알림
- **Storage:** MongoDB (`subscribingInboxPosts` collection)
- **Indexes:** `followerUserId + postCreatedAt`, `postId`

### 5. Coupon Worker

- **Consumes:** `coupon.request` topic
- **Purpose:** 쿠폰 발급 요청 비동기 처리
- **Features:** 중복 발급 방지, 한도 검증
- **Consumer Group:** `coupon-issue-request`
- **Concurrency:** 3

## 🐳 인프라 구성

### Docker Compose Services

**Kafka Cluster:**
- Kafka Broker 1-3 (ports: 9092, 9093, 9094)
- Zookeeper (port: 2181)
- Kafka UI (port: 8089)

**Databases:**
- MySQL 8.0.35 (port: 3306)
  - Database: `campus`
  - User: `myuser` / Password: `mypassword`
- MongoDB 7.0.3 (port: 27017)
  - Database: `campus`
  - User: `rootuser` / Password: `1234`
- Redis 6.0.20 (port: 6379)

**Search & Analytics:**
- Elasticsearch 7.10.2 (ports: 9200, 9300)
- Kibana 7.10.2 (port: 5601)
- Filebeat 7.10.2
- Logstash 7.10.2 (port: 5044)

**External Services:**
- External Server (port: 8088) - Metadata API

## 🚀 시작하기

### 사전 요구사항

- Java 21
- Docker & Docker Compose
- Gradle

### 1. 인프라 실행

```bash
# Docker Compose로 모든 인프라 시작
docker-compose up -d

# 서비스 상태 확인
docker-compose ps
```

### 2. 애플리케이션 빌드

```bash
# 전체 프로젝트 빌드
./gradlew build

# 특정 모듈 빌드
./gradlew :api:build
./gradlew :worker:auto-inspection-worker:build
```

### 3. API 서버 실행

```bash
# API 모듈 실행
./gradlew :api:bootRun
```

API 서버: http://localhost:8081

### 4. Worker 실행

각 워커를 별도의 터미널에서 실행:

```bash
# Auto-Inspection Worker
./gradlew :worker:auto-inspection-worker:bootRun

# Content Caching Worker
./gradlew :worker:content-caching-worker:bootRun

# Content Indexing Worker
./gradlew :worker:content-indexing-worker:bootRun

# Content Subscribe Worker
./gradlew :worker:content-subscribe-worker:bootRun

# Coupon Worker
./gradlew :worker:coupon-worker:bootRun
```

### 5. 모니터링 UI 접근

- **Kafka UI:** http://localhost:8089
- **Kibana:** http://localhost:5601
- **Elasticsearch:** http://localhost:9200

## 📚 API 문서

### 게시물 API

#### 게시물 생성

```bash
POST /posts
Content-Type: application/json

{
  "title": "게시물 제목",
  "content": "게시물 내용",
  "userId": 1,
  "categoryId": 1
}
```

#### 게시물 조회

```bash
GET /posts/{postId}
```

#### 게시물 수정

```bash
PUT /posts/{postId}
Content-Type: application/json

{
  "title": "수정된 제목",
  "content": "수정된 내용"
}
```

#### 게시물 삭제

```bash
DELETE /posts/{postId}
```

#### 게시물 목록 조회

```bash
GET /posts?page=0&size=20&categoryId=1
```

#### 게시물 검색 (Elasticsearch)

```bash
GET /posts/search?query=검색어
```

### 쿠폰 API

#### 쿠폰 발급 요청

```bash
POST /coupons/request
Content-Type: application/json

{
  "userId": 1,
  "couponEventId": 1
}
```

#### 사용 가능한 쿠폰 조회

```bash
GET /coupons/usable?userId=1
```

### OpenAPI/Swagger

API 문서는 Swagger UI를 통해 확인 가능합니다:
- URL: http://localhost:8081/swagger-ui.html

## 🔍 주요 설정

### application.yml

```yaml
# MySQL
spring.datasource.url: jdbc:mysql://localhost:3306/campus

# MongoDB
spring.data.mongodb.uri: mongodb://rootuser:1234@localhost:27017/campus

# Redis
spring.data.redis.host: localhost
spring.data.redis.port: 6379

# Elasticsearch
elasticsearch.host: localhost
elasticsearch.port: 9200

# Kafka
spring.kafka.bootstrap-servers: localhost:9092,localhost:9093,localhost:9094
```

## 📝 최근 개발 히스토리

- **fadf5c6** - 쿠폰 관련 기능 추가 및 패키지 이름 리팩터링
- **7396f0e** - 리팩터링 및 worker 모듈 문서 추가
- **0ec06d5** - post 검색 및 인덱싱을 위한 Elasticsearch 추가
- **9247780** - post 캐싱 추가 및 ResolvedPost 클래스 리팩터링
- **b49d338** - MongoDB 지원 및 새로운 Post 게시물 기능 추가
- **865596f** - ChatGPT 콘텐츠 모더레이션 워크플로우
- **04af188** - ChatGPT API 게시물 검증
- **28ef24b** - ChatGPT API 통합
- **4d69c7a** - Kafka CDC for post 작업
- **48e5b09** - MySQL 모듈 구현

## 🎓 학습 포인트

이 프로젝트를 통해 다음을 학습할 수 있습니다:

1. **이벤트 기반 아키텍처 (EDA)**
   - 이벤트 소싱 패턴
   - CQRS (Command Query Responsibility Segregation)
   - 이벤트 기반 통신

2. **Apache Kafka**
   - 토픽 설계 및 파티셔닝
   - 프로듀서/컨슈머 구현
   - Consumer Group 관리
   - 병렬 처리 및 확장성

3. **Clean Architecture**
   - 레이어 분리 및 의존성 규칙
   - 포트와 어댑터 패턴
   - 비즈니스 로직 독립성

4. **Polyglot Persistence**
   - 각 데이터베이스의 적절한 사용 사례
   - MySQL: 트랜잭셔널 데이터
   - MongoDB: 문서 저장
   - Redis: 캐싱
   - Elasticsearch: 전문 검색

5. **비동기 처리**
   - 고가용성 시스템 설계
   - 백프레셔(Backpressure) 처리
   - 멱등성(Idempotency) 보장

6. **AI 통합**
   - ChatGPT API를 활용한 콘텐츠 조정
   - 자동 태그 생성 파이프라인

## 📄 라이선스

이 프로젝트는 학습 목적으로 제작되었습니다.

## 👥 기여

학습 프로젝트이므로 자유롭게 포크하고 수정하세요.

---

**Happy Learning! 🚀**
