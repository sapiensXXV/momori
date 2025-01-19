// import styles from "./KakaoLoginButton.module.css"

import {BASE_URI} from "../../../uri.ts";

export default function KakaoLoginButton() {

  function handleLoginClick() {
    console.log(`${BASE_URI}/oauth2/authorization/kakao redirect`)
    window.location.href = BASE_URI + "/oauth2/authorization/kakao";
  }

  return (
    <button className='kakao login_button' onClick={handleLoginClick}>
      <img src="/img/icon/kakao.svg"/>
      <span>카카오로 로그인</span>
    </button>
  )
}