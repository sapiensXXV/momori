import {BASE_URI} from "../../../uri.ts";

export default function NaverLoginButton() {

  function handleLoginClick() {
    console.log(`${BASE_URI}/oauth2/authorization/naver redirect`)
    window.location.href = BASE_URI + "/oauth2/authorization/naver";
  }

  return (

    <button className='naver login_button' onClick={handleLoginClick}>
      <img src="/img/icon/naver.svg"/>
      <span>네이버로 로그인</span>
    </button>
  )
}