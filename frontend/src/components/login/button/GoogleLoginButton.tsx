import {BASE_URI} from "../../../uri.ts";

export default function GoogleLoginButton() {

  function handleLoginClick() {
    console.log(`${BASE_URI}/oauth2/authorization/kakao 으로 리다이렉션 합니다.`)
    window.location.href = BASE_URI + "/oauth2/authorization/kakao";
  }

  return (
    <button>

    </button>
  )
}