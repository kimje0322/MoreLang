## Search

GET [https://www.googleapis.com/youtube/v3/search](https://www.googleapis.com/youtube/v3/search)

[https://developers.google.com/youtube/v3/docs/search/list?hl=ko](https://developers.google.com/youtube/v3/docs/search/list?hl=ko)

---

1. videoCaption=closedCaption
    - 자막이 있는 동영상만 검색
2. relatedToVideoId={videoId}
    - 해당 영상과 관련된 동영상 검색
3. type={channel/playlist/video}
    - 특정 리소스 유형만 검색

## Videos

GET [https://www.googleapis.com/youtube/v3/videos](https://www.googleapis.com/youtube/v3/videos)

[https://developers.google.com/youtube/v3/docs/videos/list?hl=ko](https://developers.google.com/youtube/v3/docs/videos/list?hl=ko)

---

1. chart=mostPopular&regionCode={code(kr/jp/...)}
    - 각 국가별 인기 동영상 검색
2. myRating=true
    - 내가 좋아요를 누른 동영상 조회

## Subscriptions

GET [https://www.googleapis.com/youtube/v3/subscriptions](https://www.googleapis.com/youtube/v3/subscriptions)

[https://developers.google.com/youtube/v3/docs/subscriptions/list?hl=ko](https://developers.google.com/youtube/v3/docs/subscriptions/list?hl=ko)

---

1. mine=true
    - 내 구독채널 목록 조회

## Playlists

GET [https://www.googleapis.com/youtube/v3/playlists](https://www.googleapis.com/youtube/v3/playlists)

[https://developers.google.com/youtube/v3/docs/playlists/list?hl=ko](https://developers.google.com/youtube/v3/docs/playlists/list?hl=ko)

---

1. mine=true
    - 내 재생목록 조회

## PlaylistItems

GET [https://www.googleapis.com/youtube/v3/playlistItems](https://www.googleapis.com/youtube/v3/playlistItems)

[https://developers.google.com/youtube/v3/docs/playlistItems/list?hl=ko](https://developers.google.com/youtube/v3/docs/playlistItems/list?hl=ko)

---

1. playlistId={id}
    - 해당 재생목록의 동영상을 조회
