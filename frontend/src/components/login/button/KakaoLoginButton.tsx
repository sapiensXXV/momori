import styles from "./KakaoLoginButton.module.css"

export default function KakaoLoginButton() {
  return (
    <button className={`kakao ${styles.button}`}>
      <img src="/img/icon/kakao.svg"/>
      <span>카카오로 로그인</span>
    </button>
  )
}