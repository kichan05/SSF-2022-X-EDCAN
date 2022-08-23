package kr.edcan.ssf2022.util

object State {
    /*
    * 어떤 동작의 상태를 나타내는 변수이다.
    * */
    const val SUCCESS = 0   // 성공 했을때
    const val LOADING = 1   // 진행 중일때, 서버 통신을 시작할때
    const val FAIL = 2      // 실패 했을때
}