## worker 
- consumer application


### auto-inspection-worker
- post 작성/수정시에 해당내용에 대해서 메세지 consume 후에 chat-gpt-api호출후에 호출결과(`inspectedPost`)에 대해서 메세지 발행
- post 삭제 처리시에 해당 `post`에 대해서 메세지 consume 후에 삭제 메세지 발행

### content-caching-worker
- post 작성/수정/삭제시 write through 방식으로 캐시와 db의 데이터 일치하기 위하여 메세지 consume

### content-indexing-worker
- `auto-inspection-worker`에서 발행된 메세지를 consume하여 `elasticsearch`에 저장
- 검색을 위해서 tags, categoryName 을 저장한다.

### content-subscribing-worker
- `auto-inspection-worker`에서 발행된 메세지를 consume하여 `mongodb`에 저장
- post를 구독중인 user의 알림함(`SubscribingInboxPost`)에 변경된 post에 대한 내용을 저장
 