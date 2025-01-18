import {BASE_URI} from "../../../uri.ts";

export default function GithubLoginButton() {

  function handleLoginClick() {
    console.log(`${BASE_URI}/oauth2/authorization/github 으로 리다이렉션 합니다.`)
    window.location.href = BASE_URI + "/oauth2/authorization/github";
  }

  return (
    <button className='github_light login_button' onClick={handleLoginClick}>
      <img src="/img/icon/github.svg"/>
      <span>깃허브로 로그인</span>
    </button>
  )
}