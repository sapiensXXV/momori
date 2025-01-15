import styles from "./NaverLoginButton.module.css"

export default function NaverLoginButton() {
  return (
    <button className={`naver ${styles.button}`}>
      <img src="/img/icon/naver.svg"/>
      <span>네이버로 로그인</span>
    </button>
  )
}