# GitHub Actions Workflows

## Claude AI Code Review

PR이 생성되거나 업데이트될 때 자동으로 Claude AI가 코드 리뷰를 진행합니다.

### 설정 방법

1. **Anthropic API Key 발급**
   - [Anthropic Console](https://console.anthropic.com/)에서 API Key 발급
   - Claude Sonnet 4 모델 사용 권한 확인

2. **GitHub Secret 설정**
   - Repository Settings > Secrets and variables > Actions로 이동
   - `New repository secret` 클릭
   - Name: `ANTHROPIC_API_KEY`
   - Secret: 발급받은 Anthropic API Key 입력

3. **사용 방법**
   - PR을 생성하면 자동으로 실행됩니다
   - PR을 업데이트(새로운 커밋 추가)하면 자동으로 재실행됩니다
   - 리뷰 결과는 PR 댓글로 자동 게시됩니다
   - 기존 Claude 리뷰 댓글이 있으면 업데이트됩니다

### 리뷰 항목

Claude AI는 다음 관점에서 코드를 리뷰합니다:

1. **버그 및 잠재적 이슈**
   - 논리적 오류
   - Null pointer 참조
   - 예외 처리 누락

2. **코드 품질**
   - 가독성
   - 유지보수성
   - 중복 코드
   - 복잡도

3. **보안**
   - SQL Injection
   - XSS (Cross-Site Scripting)
   - 인증/인가 문제
   - 민감정보 노출

4. **성능**
   - 비효율적인 쿼리
   - 메모리 누수
   - 불필요한 연산

5. **베스트 프랙티스**
   - 디자인 패턴
   - 코딩 컨벤션
   - 아키텍처

6. **테스트**
   - 테스트 커버리지
   - 엣지 케이스 처리

### Workflow 트리거

```yaml
on:
  pull_request:
    types: [opened, synchronize, reopened]
```

- `opened`: PR이 처음 생성될 때
- `synchronize`: PR에 새로운 커밋이 추가될 때
- `reopened`: 닫혔던 PR이 다시 열릴 때

### 제한사항

- Diff 크기가 50,000자를 초과하면 자동으로 잘립니다
- 매우 큰 PR의 경우 일부 변경사항이 리뷰에서 누락될 수 있습니다
- API 사용량에 따라 Anthropic 요금이 부과됩니다

### 비용 최적화

- 불필요한 파일(generated files, build artifacts)은 `.gitignore`에 추가
- Draft PR에서는 workflow가 실행되지 않도록 조건 추가 가능
- 특정 브랜치에서만 실행되도록 제한 가능

### 문제 해결

**리뷰가 게시되지 않는 경우:**
1. `ANTHROPIC_API_KEY` secret이 올바르게 설정되었는지 확인
2. Actions 탭에서 workflow 로그 확인
3. API Key의 권한 및 크레딧 확인

**Diff가 너무 큰 경우:**
- 작은 단위로 PR을 나누어 생성
- 생성된 파일이나 대용량 파일은 리뷰에서 제외

### 커스터마이징

Workflow 파일(`claude-code-review.yml`)을 수정하여:
- 사용하는 Claude 모델 변경 (`claude-sonnet-4-20250514`)
- 리뷰 프롬프트 커스터마이징
- max_tokens 조정 (더 길거나 짧은 리뷰)
- 트리거 조건 변경
