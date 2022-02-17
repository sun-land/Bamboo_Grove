# 항해 5기의 대나무숲
항해 5기생들의 고민을 털어놓는 대나무 숲! 어려운 일이나 고민을 털어놓고 댓글로 같이 의논해봐요! 

# 와이어 프레임
 
![전체그림](https://s3.us-west-2.amazonaws.com/secure.notion-static.com/941a462c-709e-4452-adce-625dc8bc4801/%EC%A0%84%EC%B2%B4%EA%B7%B8%EB%A6%BC.png?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Content-Sha256=UNSIGNED-PAYLOAD&X-Amz-Credential=AKIAT73L2G45EIPT3X45%2F20220217%2Fus-west-2%2Fs3%2Faws4_request&X-Amz-Date=20220217T064716Z&X-Amz-Expires=86400&X-Amz-Signature=8c82b538b9696aff565a2be33aa4f9bb83145a74b8f4767eaff47be13977ef27&X-Amz-SignedHeaders=host&response-content-disposition=filename%20%3D%22%25EC%25A0%2584%25EC%25B2%25B4%25EA%25B7%25B8%25EB%25A6%25BC.PNG.png%22&x-id=GetObject)


# 테이블 설계

### Post
|ID|TITLE|POST_USER|CONTENTS|CREATED_AT|
|---|---|---|---|---|
|Long|String|String|String|LocaldateTime|
|id|글제목|작성자아이디|글내용|생성일자|


### Comment
|ID|COMMENT_USER|COMMENT_CONTENTS|POST_ID|CREATED_AT|
|---|---|---|---|---|
|Long|String|String|Post|LocaldateTime|
|id|작성자아이디|댓글내용|댓글을 단 게시글|생성일자|


### User
|ID|USERNAME|PASSWORD|
|---|---|---|
|Long|String|String|
|id|아이디|비밀번호|



# API 명세서

![API](https://user-images.githubusercontent.com/94432600/154421595-138916c1-a0b8-43b7-b0c5-515b386ec3e3.PNG)
![API](https://user-images.githubusercontent.com/94432600/154421737-3ca33844-8877-44d0-a2c2-ebea91a052ad.PNG)
![API](https://user-images.githubusercontent.com/94432600/154421776-b48b8528-0811-4a5d-937c-ae3a11d5186f.PNG)
![API](https://user-images.githubusercontent.com/94432600/154421824-d1854e5b-12d5-486a-aece-0657f30c573a.PNG)
![API](https://user-images.githubusercontent.com/94432600/154421869-442dff40-9f23-4229-bc41-f24df8277738.PNG)



# 시연영상

